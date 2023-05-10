package utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Database {
    protected static Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    protected DatabaseMetaData metaData = null;

    protected static final String path = System.getProperty("user.dir");
    protected static final File configFile = new File(path + "/src/main/java/utils/config.properties");

    public Database() {
        try {
            JDBC.init(configFile);
            this.connection = JDBC.getConnection();
            this.connection.setAutoCommit(false);
            this.metaData = connection.getMetaData();
    
            //System.out.println("Connected to database");
            //System.out.println("Database name: " + metaData.getDatabaseProductName());
            //System.out.println("Database version: " + metaData.getDatabaseProductVersion());
            //System.out.println("Driver name: " + metaData.getDriverName());
            //System.out.println("Driver version: " + metaData.getDriverVersion());
            //System.out.println("URL: " + metaData.getURL());
            //System.out.println("User name: " + metaData.getUserName());
        } catch (ClassNotFoundException error) {
            JOptionPane.showMessageDialog(null, "Class not found: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "IO error: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "SQL error: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean tableExists(String tableName) {
        try {
            this.resultSet = this.metaData.getTables(null, null, tableName, null);
            
            if (this.resultSet.next()) {
                return true;
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro: " + error.getMessage(), "Banco de dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            return false;
        }
    }
    
    public boolean createTable(String query) {
        try {            
            this.preparedStatement = this.connection.prepareStatement(query);
            int response = this.preparedStatement.executeUpdate();

            if (response == 1) {
                this.connection.commit();
                JOptionPane.showMessageDialog(null, "Tabela Evolution criada com sucesso!", "Banco de dados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.connection.rollback();
            }
            return true;
        } catch (SQLException error) {
            this.connection.rollback();
        } finally {
            return false;
        }
    }
}
