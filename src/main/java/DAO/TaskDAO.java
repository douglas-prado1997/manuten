package DAO;

import Models.RepublicModel;
import Models.TaskModel;
import Models.UserModel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.postgresql.util.PGobject;
import utils.Database;

public class TaskDAO extends Database {
    public TaskDAO() {
        super();
    }

    public ArrayList<TaskModel> findAllTasksByRepublicUuid(String uuid) {
        ArrayList<TaskModel> tasks = new ArrayList<>();
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Tasks WHERE republic_uuid = ?");
            PGobject uuidObject = new PGobject();
            uuidObject.setType("uuid");
            uuidObject.setValue(uuid);
            this.preparedStatement.setObject(1, uuidObject);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                System.out.println(this.resultSet.getTimestamp("updated_at"));
                TaskModel task = new TaskModel();
                task.setUuid(this.resultSet.getString("uuid"));
                task.setTitle(this.resultSet.getString("title"));
                task.setDescription(this.resultSet.getString("description"));
                task.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    task.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                task.setExpiresAt(this.resultSet.getTimestamp("expires_at").toLocalDateTime());
                task.setIsDone(this.resultSet.getBoolean("is_done"));
                task.setRepublicUuid(this.resultSet.getString("republic_uuid"));
                task.setUserUuid(this.resultSet.getString("user_uuid"));
                tasks.add(task);
                System.out.println("Chegou!");
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
        } finally {
            return tasks;
        }
    }

    public TaskModel findByUuid(String uuid) {
        TaskModel taskModel = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Tasks WHERE uuid = ?");
            PGobject uuidObject = new PGobject();
            uuidObject.setType("uuid");
            uuidObject.setValue(uuid);
            this.preparedStatement.setObject(1, uuidObject);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                taskModel = new TaskModel();
                taskModel.setUuid(this.resultSet.getString("uuid"));
                taskModel.setTitle(this.resultSet.getString("title"));
                taskModel.setDescription(this.resultSet.getString("description"));
                taskModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                taskModel.setExpiresAt(this.resultSet.getTimestamp("expires_at").toLocalDateTime());
                if (updatedAt != null) {
                    taskModel.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                taskModel.setIsDone(this.resultSet.getBoolean("is_done"));
                taskModel.setRepublicUuid(this.resultSet.getString("republic_uuid"));
                taskModel.setUserUuid(this.resultSet.getString("user_uuid"));
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return taskModel;
        }
    }

    public boolean create(TaskModel taskModel) {
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Tasks (title, description, republic_uuid, user_uuid, expires_at) VALUES (?, ?, ?, ?, ?)");

            PGobject republicUuidObject = new PGobject();
            republicUuidObject.setType("uuid");
            republicUuidObject.setValue(taskModel.getRepublicUuid().toString());

            PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(taskModel.getUserUuid().toString());

            this.preparedStatement.setString(1, taskModel.getTitle());
            this.preparedStatement.setString(2, taskModel.getDescription());
            this.preparedStatement.setObject(3, republicUuidObject);
            this.preparedStatement.setObject(4, userUuidObject);
            this.preparedStatement.setTimestamp(5, Timestamp.valueOf(taskModel.getExpiresAt()));
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
            return false;
        } finally {
            return true;
        }
    }
    /*
     * public UserModel update(UserModel userModel) {
        try {
            Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
            userModel.setUpdatedAt(updatedAt.toLocalDateTime());
            
            PGobject userUuid = new PGobject();
            PGobject republicUuid = new PGobject();
            
            userUuid.setType("uuid");
            userUuid.setValue(userModel.getUuid().toString());
            
            republicUuid.setType("uuid");
            republicUuid.setValue(userModel.getRepublicUuid().toString());
            
            this.preparedStatement = this.connection.prepareStatement("UPDATE Users SET name = ?, username = ?, password = ?, republic_uuid = ?, updated_at = ? WHERE uuid = ?");
            this.preparedStatement.setString(1, userModel.getName());
            this.preparedStatement.setString(2, userModel.getUsername());
            this.preparedStatement.setString(3, userModel.getPassword());
            this.preparedStatement.setObject(4, republicUuid);
            this.preparedStatement.setTimestamp(5, updatedAt);
            this.preparedStatement.setObject(6, userUuid);
            int result = this.preparedStatement.executeUpdate();
            
            if (result == 1) {
                this.connection.commit();
            } else {
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível buscar o usuário!", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return userModel;
        }
    }
     */

    public TaskModel update(TaskModel taskModel) {
        try {
            Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
            taskModel.setUpdatedAt(updatedAt.toLocalDateTime());

            PGobject taskUuid = new PGobject();
            PGobject republicUuid = new PGobject();
            PGobject userUuid = new PGobject();

            taskUuid.setType("uuid");
            taskUuid.setValue(taskModel.getUuid().toString());

            republicUuid.setType("uuid");
            republicUuid.setValue(taskModel.getRepublicUuid().toString());

            userUuid.setType("uuid");
            userUuid.setValue(taskModel.getUserUuid().toString());

            this.preparedStatement = this.connection.prepareStatement("UPDATE Tasks SET title = ?, description = ?, republic_uuid = ?, user_uuid = ?, expires_at = ?, updated_at = ?, is_done = ? WHERE uuid = ?");
            this.preparedStatement.setString(1, taskModel.getTitle());
            this.preparedStatement.setString(2, taskModel.getDescription());
            this.preparedStatement.setObject(3, republicUuid);
            this.preparedStatement.setObject(4, userUuid);
            this.preparedStatement.setTimestamp(5, Timestamp.valueOf(taskModel.getExpiresAt()));
            this.preparedStatement.setTimestamp(6, updatedAt);
            this.preparedStatement.setBoolean(7, taskModel.getIsDone());
            this.preparedStatement.setObject(8, taskUuid);
            int result = this.preparedStatement.executeUpdate();

            if (result == 1) {
                this.connection.commit();
            } else {
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
        } finally {
            return taskModel;
        }
    }

    public boolean delete(TaskModel taskModel) {
        boolean isDeleted = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("DELETE FROM Tasks WHERE uuid = ?");
            PGobject taskUuid = new PGobject();
            taskUuid.setType("uuid");
            taskUuid.setValue(taskModel.getUuid().toString());
            this.preparedStatement.setObject(1, taskUuid);
            int result = this.preparedStatement.executeUpdate();

            if (result == 1) {
                isDeleted = true;
                this.connection.commit();
            } else {
                isDeleted = false;
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
            return isDeleted;
        } finally {
            return isDeleted;
        }
    }
    
    public boolean updateAllTasksOfUser(UserModel userModel, RepublicModel republicModel) {
        try {
            Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
            this.preparedStatement = this.connection.prepareStatement("UPDATE Tasks SET user_uuid = ?, updated_at = ? WHERE user_uuid = ? AND republic_uuid = ? AND expires_at > now() AND is_done = false");
            PGobject userUuid = new PGobject();
            userUuid.setType("uuid");
            userUuid.setValue(republicModel.getUserUuid().toString());
            PGobject republicUuid = new PGobject();
            republicUuid.setType("uuid");
            republicUuid.setValue(republicModel.getUuid().toString());
            PGobject userUuid2 = new PGobject();
            userUuid2.setType("uuid");
            userUuid2.setValue(userModel.getUuid().toString());
            this.preparedStatement.setObject(1, userUuid);
            this.preparedStatement.setTimestamp(2, updatedAt);
            this.preparedStatement.setObject(3, userUuid2);
            this.preparedStatement.setObject(4, republicUuid);
            int result = this.preparedStatement.executeUpdate();
            
            if (result == 1) {
                this.connection.commit();
            } else {
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
            return false;
        } finally {
            return true;
        }
    }
}
