package View;
import javax.swing.*;
import java.awt.*;
public class EmployeeView extends JFrame {

        public JTable employeeTable;
        public JButton addButton, listButton, deleteButton, modifyButton;
        public JTextField nameField, surnameField, emailField, phoneField, salaryField;
        public JComboBox<String> roleCombo, posteCombo;

        public EmployeeView() {
            setTitle("Gestion des Employés");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 0 lignes et 2 colonnes

            inputPanel.add(new JLabel("Nom:"));
            nameField = new JTextField();
            inputPanel.add(nameField);

            inputPanel.add(new JLabel("Prénom:"));
            surnameField = new JTextField();
            inputPanel.add(surnameField);

            inputPanel.add(new JLabel("Email:"));
            emailField = new JTextField();
            inputPanel.add(emailField);

            inputPanel.add(new JLabel("Téléphone:"));
            phoneField = new JTextField();
            inputPanel.add(phoneField);

            inputPanel.add(new JLabel("Salaire:"));
            salaryField = new JTextField();
            inputPanel.add(salaryField);

            inputPanel.add(new JLabel("Rôle:"));
            roleCombo = new JComboBox<>(new String[]{"Admin", "Employe"});
            inputPanel.add(roleCombo);

            inputPanel.add(new JLabel("Poste:"));
            posteCombo = new JComboBox<>(new String[]{"INGENIEURE_ETUDE_ET_DEVELOPPEMENT", "TEAM_LEADER", "PILOTE"});
            inputPanel.add(posteCombo);

            add(inputPanel, BorderLayout.NORTH);

            employeeTable = new JTable();
            add(new JScrollPane(employeeTable), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            addButton = new JButton("Ajouter");
            buttonPanel.add(addButton);
            listButton = new JButton("Lister");
            buttonPanel.add(listButton);
            deleteButton = new JButton("Supprimer");
            buttonPanel.add(deleteButton);
            modifyButton = new JButton("Modifier");
            buttonPanel.add(modifyButton);


            add(buttonPanel, BorderLayout.SOUTH);
        }
    }