package DAO;

import Models.FeedbackModel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.postgresql.util.PGobject;
import utils.Database;


public class FeedbackDAO extends Database {
    public FeedbackDAO() {
        super();
    }
    
    public boolean create(FeedbackModel feedback) {
        boolean isCreated = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Feedbacks(user_uuid, task_uuid, comment, score) VALUES (?, ?, ?, ?)");
            PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(feedback.getUserUuid().toString());

            PGobject taskUuidObject = new PGobject();
            taskUuidObject.setType("uuid");
            taskUuidObject.setValue(feedback.getTaskUuid().toString());
            
            this.preparedStatement.setObject(1, userUuidObject);
            this.preparedStatement.setObject(2, taskUuidObject);
            this.preparedStatement.setString(3, feedback.getComment());
            this.preparedStatement.setDouble(4, feedback.getScore());
            int response = this.preparedStatement.executeUpdate();
            
            if (response == 1) {
                isCreated = true;
                this.connection.commit();
            } else {
                isCreated = false;
                this.connection.rollback();
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return isCreated;
        }
    }
    
    public boolean update(FeedbackModel feedback) {
        boolean isUpdated = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("UPDATE Feedbacks SET comment = ?, score = ? WHERE uuid = ?");
            this.preparedStatement.setString(1, feedback.getComment());
            this.preparedStatement.setDouble(2, feedback.getScore());
            
            /*PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(feedback.getUserUuid().toString());
            
            PGobject taskUuidObject = new PGobject();
            taskUuidObject.setType("uuid");
            taskUuidObject.setValue(feedback.getTaskUuid().toString());
            
            this.preparedStatement.setObject(3, userUuidObject);
            this.preparedStatement.setObject(4, taskUuidObject);*/
            
            PGobject feedbackUuidObject = new PGobject();
            feedbackUuidObject.setType("uuid");
            feedbackUuidObject.setValue(feedback.getUuid().toString());
            
            this.preparedStatement.setObject(3, feedbackUuidObject);
            int response = this.preparedStatement.executeUpdate();
            
            if (response == 1) {
                isUpdated = true;
                this.connection.commit();
            } else {
                isUpdated = false;
                this.connection.rollback();
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return isUpdated;
        }
    }
    
    public ArrayList<FeedbackModel> findByTaskUuid(String taskUuid) {
        ArrayList<FeedbackModel> feedbacks = new ArrayList<>();
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Feedbacks WHERE task_uuid = ?");
            PGobject taskUuidObject = new PGobject();
            taskUuidObject.setType("uuid");
            taskUuidObject.setValue(taskUuid);
            this.preparedStatement.setObject(1, taskUuidObject);
            this.resultSet = this.preparedStatement.executeQuery();
            
            while (this.resultSet.next()) {
                FeedbackModel feedback = new FeedbackModel();
                feedback.setUuid(this.resultSet.getString("uuid"));
                feedback.setUserUuid(this.resultSet.getString("user_uuid"));
                feedback.setTaskUuid(this.resultSet.getString("task_uuid"));
                feedback.setComment(this.resultSet.getString("comment"));
                feedback.setScore(this.resultSet.getDouble("score"));
                feedback.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    feedback.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                feedbacks.add(feedback);
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return feedbacks;
        }
    }

    public FeedbackModel findByTaskUuidAndUserUuid(String taskUuid, String userUuid) {
        FeedbackModel feedback = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Feedbacks WHERE task_uuid = ? AND user_uuid = ?");
            PGobject taskUuidObject = new PGobject();
            taskUuidObject.setType("uuid");
            taskUuidObject.setValue(taskUuid);

            PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(userUuid);
            this.preparedStatement.setObject(1, taskUuidObject);
            this.preparedStatement.setObject(2, userUuidObject);
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                feedback = new FeedbackModel();
                feedback.setUuid(this.resultSet.getString("uuid"));
                feedback.setUserUuid(this.resultSet.getString("user_uuid"));
                feedback.setTaskUuid(this.resultSet.getString("task_uuid"));
                feedback.setComment(this.resultSet.getString("comment"));
                feedback.setScore(this.resultSet.getDouble("score"));
                feedback.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    feedback.setUpdatedAt(updatedAt.toLocalDateTime());
                }
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return feedback;
        }   
    }

    public ArrayList<FeedbackModel> findAllByUserUuid(String userUuid) {
        ArrayList<FeedbackModel> feedbacks = new ArrayList<>();
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Feedbacks WHERE user_uuid = ?");
            PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(userUuid);
            this.preparedStatement.setObject(1, userUuidObject);
            this.resultSet = this.preparedStatement.executeQuery();
            
            while (this.resultSet.next()) {
                FeedbackModel feedback = new FeedbackModel();
                feedback.setUuid(this.resultSet.getString("uuid"));
                feedback.setUserUuid(this.resultSet.getString("user_uuid"));
                feedback.setTaskUuid(this.resultSet.getString("task_uuid"));
                feedback.setComment(this.resultSet.getString("comment"));
                feedback.setScore(this.resultSet.getDouble("score"));
                feedback.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    feedback.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                feedbacks.add(feedback);
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return feedbacks;
        }
    }
    
    public ArrayList<FeedbackModel> findByUuidUserTask(String userUuid) {
        ArrayList<FeedbackModel> feedbacks = new ArrayList<>();
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT Feedbacks.* FROM Feedbacks INNER JOIN Tasks ON Feedbacks.task_uuid = Tasks.uuid WHERE Tasks.user_uuid = ?");
            PGobject userUuidObject = new PGobject();
            userUuidObject.setType("uuid");
            userUuidObject.setValue(userUuid);
            this.preparedStatement.setObject(1, userUuidObject);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                FeedbackModel feedback = new FeedbackModel();
                feedback.setUuid(this.resultSet.getString("uuid"));
                feedback.setUserUuid(this.resultSet.getString("user_uuid"));
                feedback.setTaskUuid(this.resultSet.getString("task_uuid"));
                feedback.setComment(this.resultSet.getString("comment"));
                feedback.setScore(this.resultSet.getDouble("score"));
                feedback.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    feedback.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                feedbacks.add(feedback);
            }
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return feedbacks;
        }
    }
}
