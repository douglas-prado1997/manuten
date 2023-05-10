package DAO;

import Models.RepublicModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.postgresql.util.PGobject;
import utils.Database;


public class RepublicDAO extends Database {
    public RepublicDAO() {
        super();
    }
    
    public RepublicModel findByName(String name) {
        RepublicModel republicModel = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Republic WHERE name = ?");
            this.preparedStatement.setString(1, name);
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                republicModel = new RepublicModel();
                republicModel.setUuid(this.resultSet.getString("uuid"));
                republicModel.setName(this.resultSet.getString("name"));
                republicModel.setPassword(this.resultSet.getString("password"));
                republicModel.setUserUuid(this.resultSet.getString("user_uuid"));
                republicModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                republicModel.setUpdatedAt(this.resultSet.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar a república", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return republicModel;
        }
    }
    
    public RepublicModel findByUuid(String uuid) {
        RepublicModel republicModel = null;
        try {
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM Republic WHERE uuid = ?");
            PGobject uuidObj = new PGobject();
            uuidObj.setType("uuid");
            uuidObj.setValue(uuid);
            this.preparedStatement.setObject(1, uuidObj);
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                republicModel = new RepublicModel();
                republicModel.setUuid(this.resultSet.getString("uuid"));
                republicModel.setName(this.resultSet.getString("name"));
                republicModel.setPassword(this.resultSet.getString("password"));
                republicModel.setUserUuid(this.resultSet.getString("user_uuid"));
                republicModel.setCreatedAt(this.resultSet.getTimestamp("created_at").toLocalDateTime());
                republicModel.setUpdatedAt(this.resultSet.getTimestamp("updated_at").toLocalDateTime());
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar a república", "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return republicModel;
        }
    }
    
    public boolean create(RepublicModel republic) {
        boolean isCreated = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("INSERT INTO Republic(name, password, user_uuid) VALUES (?, ?, ?)");
            this.preparedStatement.setString(1, republic.getName());
            this.preparedStatement.setString(2, republic.getPassword());
            PGobject uuidObj = new PGobject();
            uuidObj.setType("uuid");
            uuidObj.setValue(republic.getUserUuid().toString());
            this.preparedStatement.setObject(3, uuidObj);
            int response = this.preparedStatement.executeUpdate();
            
            if (response == 1) {
                isCreated = true;
                this.connection.commit();
            } else {
                isCreated = false;
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
        } finally {
            return isCreated;
        }
    }

    public boolean removeUserAdmin(RepublicModel republic) {
        boolean isRemoved = false;
        try {
            this.preparedStatement = this.connection.prepareStatement("UPDATE Republic SET user_uuid = NULL WHERE uuid = ?");
            PGobject uuidObj = new PGobject();
            uuidObj.setType("uuid");
            uuidObj.setValue(republic.getUuid().toString());
            this.preparedStatement.setObject(1, uuidObj);
            int response = this.preparedStatement.executeUpdate();
            
            if (response == 1) {
                isRemoved = true;
                this.connection.commit();
            } else {
                isRemoved = false;
                this.connection.rollback();
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            this.connection.rollback();
        } finally {
            return isRemoved;
        }
    }
}
