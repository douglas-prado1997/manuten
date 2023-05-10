package DAO;

import Models.RepublicModel;
import Models.UserModel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.JOptionPane;
import org.postgresql.util.PGobject;
import utils.Database;


public class UserDAO extends Database {
    public UserDAO() {
        super();
    }
    
    public boolean create(UserModel user) {
        boolean isCreated = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Users(name, username, password) VALUES (?, ?, ?)");
            this.preparedStatement.setString(1, user.getName());
            this.preparedStatement.setString(2, user.getUsername());
            this.preparedStatement.setString(3, user.getPassword());
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
    
    public UserModel findByUuid(String uuid) {
        UserModel userModel = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Users WHERE uuid = ?");
            PGobject uuidObject = new PGobject();
            uuidObject.setType("uuid");
            uuidObject.setValue(uuid);
            this.preparedStatement.setObject(1, uuidObject);
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                userModel = new UserModel();
                userModel.setUuid(this.resultSet.getString("uuid"));
                userModel.setName(this.resultSet.getString("name"));
                userModel.setUsername(this.resultSet.getString("username"));
                userModel.setPassword(this.resultSet.getString("password"));
                userModel.setRepublicUuid(this.resultSet.getString("republic_uuid"));
                userModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    userModel.setUpdatedAt(updatedAt.toLocalDateTime());
                }
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o usuário", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return userModel;
        }
    }
    
    public UserModel findByUsername(String username) {
        UserModel userModel = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Users WHERE username = ?");
            this.preparedStatement.setString(1, username);
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                userModel = new UserModel();
                userModel.setUuid(this.resultSet.getString("uuid"));
                userModel.setName(this.resultSet.getString("name"));
                userModel.setUsername(this.resultSet.getString("username"));
                userModel.setPassword(this.resultSet.getString("password"));
                userModel.setRepublicUuid(this.resultSet.getString("republic_uuid"));
                userModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    userModel.setUpdatedAt(updatedAt.toLocalDateTime());
                }
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível buscar o usuário!", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return userModel;
        }
    }
    
    public UserModel update(UserModel userModel) {
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

    public ArrayList<UserModel> findAllByRepublicUuid(String republicUuid) {
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Users WHERE republic_uuid = ?");
            PGobject republicUuidObject = new PGobject();
            republicUuidObject.setType("uuid");
            republicUuidObject.setValue(republicUuid);
            this.preparedStatement.setObject(1, republicUuidObject);
            this.resultSet = this.preparedStatement.executeQuery();
            
            while (this.resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setUuid(this.resultSet.getString("uuid"));
                userModel.setName(this.resultSet.getString("name"));
                userModel.setUsername(this.resultSet.getString("username"));
                userModel.setPassword(this.resultSet.getString("password"));
                userModel.setRepublicUuid(this.resultSet.getString("republic_uuid"));
                userModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                Timestamp updatedAt = this.resultSet.getTimestamp("updated_at");
                if (updatedAt != null) {
                    userModel.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                users.add(userModel);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível buscar o usuário!", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return users;
        }
    }

    public boolean removeRepublic(UserModel user, RepublicModel republic) {
        try {
            this.preparedStatement = this.connection.prepareStatement("UPDATE Users SET republic_uuid = null WHERE uuid = ? and republic_uuid = ?");
            PGobject userUuid = new PGobject();
            userUuid.setType("uuid");
            userUuid.setValue(user.getUuid().toString());
            this.preparedStatement.setObject(1, userUuid);
            PGobject republicUuid = new PGobject();
            republicUuid.setType("uuid");
            republicUuid.setValue(republic.getUuid().toString());
            this.preparedStatement.setObject(2, republicUuid);
            int result = this.preparedStatement.executeUpdate();
            
            if (result == 1) {
                this.connection.commit();
                return true;
            } else {
                this.connection.rollback();
                return false;
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possível buscar o usuário!", "Banco de dados", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
