
package utils;

import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Migration extends Database {
    private String[] migration;
    
    public Migration() {
        String[] migration = {
            "CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\";",
            "CREATE TABLE IF NOT EXISTS public.Users("
                + "uuid UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(), "
                + "name VARCHAR(255) NOT NULL, "
                + "username VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL, "
                + "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "updated_at TIMESTAMP NULL"
                + ");",
            "CREATE TABLE IF NOT EXISTS public.Republic("
                + "uuid UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(), "
                + "name VARCHAR(255) NOT NULL, "
                + "password VARCHAR(255) NOT NULL, "
                + "user_uuid UUID NOT NULL,"
                + "FOREIGN KEY (user_uuid) REFERENCES Users(uuid), "
                + "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "updated_at TIMESTAMP NULL"
                + ");",
            "ALTER TABLE public.Users "
                + "ADD COLUMN republic_uuid UUID NULL DEFAULT NULL, "
                + "ADD FOREIGN KEY (republic_uuid) REFERENCES Republic(uuid);",
            "CREATE TABLE IF NOT EXISTS public.Tasks("
                + "uuid UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(), "
                + "title VARCHAR(255) NOT NULL, "
                + "description VARCHAR(255) NOT NULL, "
                + "republic_uuid UUID NOT NULL, "
                + "user_uuid UUID NOT NULL, "
                + "is_done SMALLINT NOT NULL DEFAULT 0, "
                + "FOREIGN KEY (republic_uuid) REFERENCES Republic(uuid), "
                + "FOREIGN KEY (user_uuid) REFERENCES Users(uuid), "
                + "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "updated_at TIMESTAMP NULL,"
                + "expires_at TIMESTAMP NOT NULL"
                + ");",
            "CREATE TABLE IF NOT EXISTS public.Feedbacks("
                + "uuid UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(), "
                + "comment TEXT NULL, "
                + "score DECIMAL NOT NULL, "
                + "user_uuid UUID NOT NULL, "
                + "task_uuid UUID NOT NULL, "
                + "FOREIGN KEY (user_uuid) REFERENCES Users(uuid), "
                + "FOREIGN KEY (task_uuid) REFERENCES Tasks(uuid), "
                + "created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "updated_at TIMESTAMP NULL"
                + ");"
        };
        
        this.migration = migration;
    }
    
    public void run() {
        boolean hasError = false;
        for (String migrate : this.migration) {
            try {
                this.connection.createStatement().execute(migrate);
                this.connection.commit();
            } catch (SQLException error) {
                hasError = true;
                System.out.println(error.getMessage());
            }
        }
        if (!hasError) {
            JOptionPane.showMessageDialog(null, "Migração efetuada com sucesso!", "Migração", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
