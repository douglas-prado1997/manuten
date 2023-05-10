package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class UserModel {
    private UUID uuid;
    private String name;
    private String username;
    private String password;
    private UUID republicUuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public UserModel() { }
    
    public void setUuid(String uuid) {
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (Exception error) {
            this.uuid = null;
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRepublicUuid(String republicUuid) {
        try {
            this.republicUuid = UUID.fromString(republicUuid);
        } catch (Exception error) {
            this.republicUuid = null;
        }
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public UUID getRepublicUuid() {
        return this.republicUuid;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
