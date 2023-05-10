package Controllers;

import DAO.FeedbackDAO;
import DAO.RepublicDAO;
import DAO.TaskDAO;
import DAO.UserDAO;
import Models.FeedbackModel;
import Models.RepublicModel;
import Models.TaskModel;
import Models.UserModel;
import Views.AddTaskView;
import Views.ChooserView;
import Views.EditTaskView;
import Views.RepublicView;
import Views.TaskView;
import Views.UserView;
import com.password4j.Hash;
import com.password4j.Password;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class RepublicController {
    private String userUuid;
    private RepublicDAO republicDAO;
    private UserDAO userDAO;
    private TaskDAO taskDAO;
    private RepublicView republicView;
    private SignInController signInController;
    private UserModel user;
    private RepublicModel republic;
    private ArrayList<TaskModel> tasks;
    private ArrayList<UserModel> users;
    private MyProfileController myProfileController;
    private MyTasksController myTasksController;
    private TaskView taskView;
    private UserView userView;
    private FeedbackDAO feedbackDAO;
    private AddTaskView addTaskView;
    private EditTaskView editTaskView;
    private ChooserView chooserView;
    
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
    
    public String getUserUuid() {
        return this.userUuid;
    }
    
    public void exitRepublic() {
        if (this.republic.getUserUuid().equals(this.userUuid)) {
            this.republicDAO.removeUserAdmin(this.republic);

            ArrayList<UserModel> users = this.userDAO.findAllByRepublicUuid(this.republic.getUuid().toString());
            for (UserModel user : users) {
                this.userDAO.removeRepublic(user, this.republic);
            }

            ArrayList<TaskModel> tasks = this.taskDAO.findAllTasksByRepublicUuid(this.republic.getUuid().toString());
            for (TaskModel task : tasks) {
                if (!task.getIsDone()) {
                    this.taskDAO.delete(task);
                }
            }

            JOptionPane.showMessageDialog(null, "República removida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.republic = null;
            this.user.setRepublicUuid(null);
            this.load();
            return;
        }

        ArrayList<TaskModel> tasks = this.taskDAO.findAllTasksByRepublicUuid(this.republic.getUuid().toString());

        for (TaskModel task : tasks) {
            if (!task.getIsDone()) {
                task.setUserUuid(this.republic.getUserUuid().toString());
                this.taskDAO.update(task);
            }
        }
        
        this.userDAO.removeRepublic(this.user, this.republic);
        this.user.setRepublicUuid(null);
        this.load();
        JOptionPane.showMessageDialog(null, "Você saiu da república com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public RepublicController(SignInController signInController) {
        this.signInController = signInController;
        this.republicView = new RepublicView(this);
        this.republicDAO = new RepublicDAO();
        this.userDAO = new UserDAO();
        this.taskDAO = new TaskDAO();
        this.myProfileController = new MyProfileController(this);
        this.myTasksController = new MyTasksController(this);
        this.taskView = new TaskView(this);
        this.userView = new UserView(this);
        this.feedbackDAO = new FeedbackDAO();
        this.addTaskView = new AddTaskView(this);
        this.editTaskView = new EditTaskView(this);


        this.chooserView = new ChooserView(this);
        this.republicView.invisibleContentPanel();
    }
    
    public void view() {
        this.load();
        this.republicView.setVisible(true);
    }
    
    public void close() {
        this.republicView.setVisible(false);
    }
    
    public void load() {
        this.user = this.userDAO.findByUuid(this.userUuid);
        
        if (this.user != null) {
            this.republicView.setIsLoggedInLabel(this.user.getName());
            
            this.republicView.setUser(user);
            
            if (this.user.getRepublicUuid() == null) {
                this.chooserView.setVisible(true);
                this.republicView.load();
                this.republicView.invisibleContentPanel();
                this.republicView.setNoRepublic();
                return;
            }
            
            this.republicView.setRepublic();
            
            this.republicView.visibleContentPanel();
            
            this.republic = this.republicDAO.findByUuid(this.user.getRepublicUuid().toString());
            
            this.republicView.setRepublic(republic);
            
            this.tasks = this.taskDAO.findAllTasksByRepublicUuid(this.republic.getUuid().toString());
            this.users = this.userDAO.findAllByRepublicUuid(this.republic.getUuid().toString());
            
            this.republicView.setTasks(tasks);
            this.republicView.setUsers(users);
            
            double score = 0.0;
            double i = 0;
            
            for (FeedbackModel feedback : this.feedbackDAO.findByUuidUserTask(this.user.getUuid().toString())) {
                score += feedback.getScore();
                i++;
            }
            
            if (i != 0) {
                score /= i;
            }
            
            this.republicView.setScore(score);
            
            if (!this.user.getUuid().equals(this.republic.getUserUuid())) {
                this.republicView.setNotAdministrator();
            }
            
            this.republicView.load();
        } else {
            this.logout();
            this.close();
        }
    }
    
    public void create(String name, String password) {
        RepublicModel republic = this.republicDAO.findByName(name);
        
        if (republic != null) {
            JOptionPane.showMessageDialog(null, "República existente!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Senha da república requer no mínimo 6 caracteres!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        republic = new RepublicModel();
        republic.setName(name);
        Hash passwordHashed = Password.hash(password).addRandomSalt().withArgon2();
        republic.setPassword(passwordHashed.getResult());
        republic.setUserUuid(this.user.getUuid().toString());
        
        if (this.republicDAO.create(republic)) {
            republic = null;
            republic = this.republicDAO.findByName(name);

            this.user.setRepublicUuid(republic.getUuid().toString());
            this.user = this.userDAO.update(this.user);
            this.load();

            JOptionPane.showMessageDialog(null, "República criada com sucesso!", "República", JOptionPane.INFORMATION_MESSAGE);
            //this.republicView.viewTasksView();
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Não foi possível criar a república!", "República", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    public void openUserView(String uuidUser) {
        UserModel user = this.userDAO.findByUuid(uuidUser);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.userView.setUser(user, this.republic);
        this.userView.setVisible(true);
    }
    
    public void taskDone(TaskModel task) {
        if (task.getUuid() == null) {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Tarefa", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        task = this.taskDAO.findByUuid(task.getUuid().toString());
        
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Tarefa não encontrada!", "Tarefa", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        task.setIsDone(true);
        task = this.taskDAO.update(task);
        
        JOptionPane.showMessageDialog(null, "Tarefa completada com sucesso!", "Tarefa", JOptionPane.INFORMATION_MESSAGE);
        this.load();
        this.taskView.closeView();
    }
    
    public void openTaskView(String uuidTask) {
        TaskModel task = this.taskDAO.findByUuid(uuidTask);
        FeedbackModel feedback = this.feedbackDAO.findByTaskUuidAndUserUuid(uuidTask, this.userUuid);
        
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Tarefa inexistente!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.taskView.setTaskModel(task);
        this.taskView.setUserModel(this.user);
        this.taskView.setFeedbackModel(feedback);
        this.taskView.load();
        this.taskView.setVisible(true);
    }
    
    public RepublicModel getRepublic() {
        return this.republic;
    }
    
    public void addTask(String title, String description, String userUuid, LocalDateTime expiresAt) {
        TaskModel task = new TaskModel();
        
        task.setTitle(title);
        task.setDescription(description);
        task.setUserUuid(userUuid);
        task.setRepublicUuid(this.republic.getUuid().toString());
        task.setExpiresAt(expiresAt);
        
        if (!expiresAt.isAfter(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(null, "Data de expiração inválida!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!this.taskDAO.create(task)) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar a tarefa!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.addTaskView.closeTask();
        this.load();
        JOptionPane.showMessageDialog(null, "Tarefa criada com sucesso!", "Tarefas", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    public void editTask(String uuid, String title, String description, String userUuid, LocalDateTime expiresAt) {
        TaskModel task = this.taskDAO.findByUuid(uuid);
        
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Tarefa inexistente!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!expiresAt.isAfter(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(null, "Data de expiração inválida!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setExpiresAt(expiresAt);
        task.setUserUuid(userUuid);
        
        task = this.taskDAO.update(task);
        this.editTaskView.closeView();
        this.load();
        JOptionPane.showMessageDialog(null, "Tarefa editada com sucesso!", "Tarefas", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    public void deleteTask(String uuid) {
        TaskModel task = this.taskDAO.findByUuid(uuid);
        
        if (task == null) {
            JOptionPane.showMessageDialog(null, "Tarefa inexistente!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (task.getIsDone()) {
            JOptionPane.showMessageDialog(null, "Tarefa já foi concluída!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!this.taskDAO.delete(task)) {
            JOptionPane.showMessageDialog(null, "Não foi possível deletar a tarefa!", "Tarefas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.load();
        JOptionPane.showMessageDialog(null, "Tarefa deletada com sucesso!", "Tarefas", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    public void createRepublic(String name, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(null, "As senhas não se coincide", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (name.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar a república!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        RepublicModel republicModel = new RepublicModel();
        republicModel.setName(name);
        Hash passwordHashed = Password.hash(password).addRandomSalt().withArgon2();
        republicModel.setPassword(passwordHashed.getResult());
        republicModel.setUserUuid(this.user.getUuid().toString());
        
        if (!this.republicDAO.create(republicModel)) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar a república!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.republic = this.republicDAO.findByName(republicModel.getName());
        
        this.user.setRepublicUuid(republic.getUuid().toString());
        
        this.user = this.userDAO.update(this.user);
        
        this.chooserView.closeView();
        this.load();
        JOptionPane.showMessageDialog(null, "República criada com sucesso!", "República", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    public void enterRepublic(String name, String password) {
        RepublicModel republic = this.republicDAO.findByName(name);
        
        if (republic == null) {
            JOptionPane.showMessageDialog(null, "República inexistente!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!Password.check(password, republic.getPassword()).withArgon2()) {
            JOptionPane.showMessageDialog(null, "Senha inválida!", "República", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.user.setRepublicUuid(republic.getUuid().toString());
        this.user = this.userDAO.update(this.user);
        this.load();
        this.chooserView.closeView();
        JOptionPane.showMessageDialog(null, "Você entrou na república com sucesso!", "República", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    public void openAddTaskView() {
        if (this.republic.getUserUuid().equals(this.user.getUuid())) {
            this.addTaskView.emptyFields();
            this.addTaskView.setUsers(this.users);
            this.addTaskView.setVisible(true);
        }
    }
    
    public void openEditTaskView(String uuidTask) {
        if (this.republic.getUserUuid().equals(this.user.getUuid())) {
            this.editTaskView.setUsers(this.users);
            TaskModel task = this.taskDAO.findByUuid(uuidTask);
            
            if (task == null) {
                JOptionPane.showMessageDialog(null, "Não foi possível encontrar a tarefa!", "Tarefa", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (task.getIsDone()) {
                JOptionPane.showMessageDialog(null, "Tarefa já foi concluída!", "Tarefa", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.editTaskView.setTaskModel(task);
            
            if (task.getUserUuid() != null) {
                UserModel selectedUser = this.userDAO.findByUuid(task.getUserUuid().toString());
                this.editTaskView.setSelectedUser(selectedUser);
            }
            
            this.editTaskView.load();
            this.editTaskView.setVisible(true);
        }
    }
    
    public UserModel findUserByUuid(String uuid) {
        return this.userDAO.findByUuid(uuid);
    }
    
    public void deleteUser(String userUuid) {
        UserModel user = this.userDAO.findByUuid(userUuid);
        
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Usuário inexistente!", "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!user.getRepublicUuid().equals(this.republic.getUuid())) {
            JOptionPane.showMessageDialog(null, "Usuário não pertence à república!", "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userUuid.equals(this.user.getUuid().toString())) {
            JOptionPane.showMessageDialog(null, "Você não pode deletar a si mesmo!", "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!this.userDAO.removeRepublic(user, this.republic)) {
            JOptionPane.showMessageDialog(null, "Não foi possível deletar o usuário!", "Usuário", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.taskDAO.updateAllTasksOfUser(user, this.republic);
        
        this.load();
        JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!", "Usuário", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    public void logout() {
        this.close();
        this.signInController.viewDeleteSession();
    }
    
    public void openTasksView() {
        this.close();
        this.myTasksController.view();
    }
    
    public void openProfile() {
        this.myProfileController.setProfile(user);
        this.myProfileController.view();
    }
    
    public void sendFeedback(TaskModel task, String comment, double score) {
        FeedbackModel feedback = this.feedbackDAO.findByTaskUuidAndUserUuid(task.getUuid().toString(), this.user.getUuid().toString());
        
        if (feedback != null) {
            JOptionPane.showMessageDialog(null, "Você já enviou feedback", "Feedback", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        feedback = new FeedbackModel();
        feedback.setComment(comment);
        feedback.setScore(score);
        feedback.setUserUuid(this.user.getUuid().toString());
        feedback.setTaskUuid(task.getUuid().toString());
        
        this.feedbackDAO.create(feedback);
        JOptionPane.showMessageDialog(null, "Feedback criado com sucesso!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    public void updateFeedback(TaskModel task, String comment, double score) {
        FeedbackModel feedback = this.feedbackDAO.findByTaskUuidAndUserUuid(task.getUuid().toString(), this.user.getUuid().toString());
        
        if (feedback == null) {
            JOptionPane.showMessageDialog(null, "Você não enviou feedback ainda!", "Feedback", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        feedback.setComment(comment);
        feedback.setScore(score);
        
        this.feedbackDAO.update(feedback);
        JOptionPane.showMessageDialog(null, "Feedback atualizado com sucesso!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
}
