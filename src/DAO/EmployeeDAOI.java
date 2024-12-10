package DAO;
import Model.Employee;
import java.util.List;


public interface EmployeeDAOI {

        void add(Employee employee);
        void delete(int id);
        List<Employee> listAll();
        Employee findById(int id);
        void update(Employee employee, int id);
    }