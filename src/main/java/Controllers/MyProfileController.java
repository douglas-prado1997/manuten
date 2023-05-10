
package Controllers;

import DAO.FeedbackDAO;
import DAO.RepublicDAO;
import DAO.TaskDAO;
import DAO.UserDAO;
import Models.FeedbackModel;
import Models.RepublicModel;
import Models.TaskModel;
import Models.UserModel;
import Views.MyProfileView;
import com.password4j.Hash;
import com.password4j.Password;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class MyProfileController {
    private RepublicController republicController;
    private MyProfileView myProfileView;
    private RepublicModel republic;
    private UserModel user;
    private RepublicDAO republicDAO;
    private FeedbackDAO feedbackDAO;
    private TaskDAO taskDAO;
    private UserDAO userDAO;
    private ArrayList<FeedbackModel> feedbacks;

    public TaskModel getTaskFeedback(String uuid) {
        return this.taskDAO.findByUuid(uuid);
    }

    public UserModel getUserFeedback(String uuid) {
        return this.userDAO.findByUuid(uuid);
    }

    public void updateUser(String name, String oldPassword, String newPassword, String confirmNewPassword) {
        if (!newPassword.equals(confirmNewPassword)) {
            JOptionPane.showMessageDialog(null, "As senhas não conferem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (oldPassword.equals(newPassword)) {
            JOptionPane.showMessageDialog(null, "A nova senha não pode ser igual a antiga!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Password.check(oldPassword, this.user.getPassword()).withArgon2()) {
            JOptionPane.showMessageDialog(null, "A senha antiga está incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Hash passwordHashed = Password.hash(newPassword).addRandomSalt().withArgon2();

        this.user.setName(name);
        this.user.setPassword(passwordHashed.getResult());
        this.userDAO.update(this.user);
        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setProfile(UserModel user) {
        if (user.getRepublicUuid() != null) {
            this.republic = republicDAO.findByUuid(user.getRepublicUuid().toString());
        }
        this.user = user;
        ArrayList<FeedbackModel> feedbacks = this.feedbackDAO.findByUuidUserTask(user.getUuid().toString());
        System.out.println(feedbacks);
        this.feedbacks = feedbacks;
        this.myProfileView.setProfile(this.user, this.republic, this.feedbacks);
    }
    
    public MyProfileController(RepublicController republicController) {
        this.republicController = republicController;
        this.myProfileView = new MyProfileView(this);
        this.republicDAO = new RepublicDAO();
        this.feedbackDAO = new FeedbackDAO();
        this.userDAO = new UserDAO();
        this.taskDAO = new TaskDAO();
    }
    
    public void view() {
        this.load();
        this.myProfileView.setVisible(true);
    }
    
    public void load() {
        
    }
    
    public void close() {
        this.myProfileView.setVisible(false);
    }
}
