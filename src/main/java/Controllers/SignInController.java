package Controllers;

import DAO.UserDAO;
import Models.UserModel;
import Views.SignInView;
import com.password4j.Hash;
import com.password4j.Password;
import javax.swing.JOptionPane;


public class SignInController {   
    private SignInView signInView;
    private SignUpController signUpController;
    private RepublicController republicController;
    private UserModel userModel;
    private UserDAO userDAO;
    
    public SignInController() {
        this.userModel = new UserModel();
        this.signInView = new SignInView(this);
        this.signUpController = new SignUpController(this);
        this.republicController = new RepublicController(this);
        this.userDAO = new UserDAO();
    }
    
    public void view() {
        this.signInView.setVisible(true);
    }
    
    public void close() {
        this.signInView.setVisible(false);
    }
    
    public void toSignUp() {
        this.signInView.emptyFields();
        this.close();
        this.signUpController.view();
    }
    
    public void toRepublic() {
        this.signInView.emptyFields();
        this.close();
        this.republicController.view();
    }
    
    public void login(String username, String password) {
        UserModel user = this.userDAO.findByUsername(username);
        
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar o login!", "Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!Password.check(password, user.getPassword()).withArgon2()) {
            JOptionPane.showMessageDialog(null, "Senha inválida!", "Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.close();
        this.republicController.setUserUuid(user.getUuid().toString());
        this.republicController.view();
        this.republicController.setUserUuid(user.getUuid().toString());
        this.republicController.load();
        return;
    }
    
    public void viewDeleteSession() {
        this.signInView.emptyFields();
        this.view();
        this.republicController = null;
        this.republicController = new RepublicController(this);
    }
}
