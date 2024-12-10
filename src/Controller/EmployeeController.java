package Controller;
import DAO.EmployeeDAOI;
import Model.Employee;
import View.EmployeeView;
import Model.Poste;
import Model.Role;
import DAO.EmployeeDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class EmployeeController {

        private final EmployeeView view;
        private final EmployeeDAOI dao;

        public EmployeeController(EmployeeView view) {
            this.view = view;
            this.dao = new EmployeeDAOImpl();

            view.addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEmployee();
                }
            });

            view.listButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listEmployees();
                }
            });

            view.deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteEmployee();
                }
            });

            view.modifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateEmployee();
                }
            });
        }

        private void addEmployee() {
            try {
                String nom = view.nameField.getText();
                String prenom = view.surnameField.getText();
                String email = view.emailField.getText();
                String phone = view.phoneField.getText();
                double salaire = Double.parseDouble(view.salaryField.getText());
                Role role = Role.valueOf(view.roleCombo.getSelectedItem().toString().toUpperCase());
                Poste poste = Poste.valueOf(view.posteCombo.getSelectedItem().toString().toUpperCase());

                Employee employee = new Employee(nom, prenom, email, phone, salaire, role, poste);
                dao.add(employee);
                JOptionPane.showMessageDialog(view, "Employé ajouté avec succès.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
            }
        }

        private void listEmployees() {
            List<Employee> employees = dao.listAll();
            String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Salaire", "Rôle", "Poste"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Employee emp : employees) {
                Object[] row = {emp.getId(), emp.getNom(), emp.getPrenom(), emp.getEmail(), emp.getPhone(), emp.getSalaire(), emp.getRole(), emp.getPoste()};
                model.addRow(row);
            }

            view.employeeTable.setModel(model);
        }

        private void deleteEmployee() {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'employé à supprimer :"));
                dao.delete(id);
                JOptionPane.showMessageDialog(view, "Employé supprimé avec succès.");
                listEmployees(); // Actualiser la liste
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
            }
        }

        public void updateEmployee() {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(view, "Entrez l'ID de l'employé à modifier :"));
                Employee existingEmployee = dao.findById(id);

                if (existingEmployee != null) {
                    existingEmployee.setNom(view.nameField.getText());
                    existingEmployee.setPrenom(view.surnameField.getText());
                    existingEmployee.setEmail(view.emailField.getText());
                    existingEmployee.setPhone(view.phoneField.getText());
                    existingEmployee.setSalaire(Double.parseDouble(view.salaryField.getText()));

                    existingEmployee.setRole(Role.valueOf(view.roleCombo.getSelectedItem().toString().toUpperCase()));
                    existingEmployee.setPoste(Poste.valueOf(view.posteCombo.getSelectedItem().toString().toUpperCase()));

                    dao.update(existingEmployee, id);
                    JOptionPane.showMessageDialog(view, "Employé mis à jour avec succès.");
                    listEmployees();
                } else {
                    JOptionPane.showMessageDialog(view, "Aucun employé trouvé avec cet ID.");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(view, "Erreur : rôle ou poste invalide.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur: " + ex.getMessage());
            }
        }

    }
