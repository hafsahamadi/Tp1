import Controller.EmployeeController;
import View.EmployeeView;
import DAO.EmployeeDAOImpl;

public class main {


        public static void main(String[] args) {
            EmployeeView view = new EmployeeView();
            new EmployeeController(view);
            view.setVisible(true);
        }
    }
