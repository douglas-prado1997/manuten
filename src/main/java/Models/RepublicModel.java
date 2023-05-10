package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class RepublicModel {
    private UUID uuid;
    private String name;
    private String password;
    private UUID userUuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public RepublicModel() { }
    
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
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUserUuid(String userUuid) {
        try {
            this.userUuid = UUID.fromString(userUuid);
        } catch (Exception error) {
            this.userUuid = null;
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
    
    public String getName() {
        return this.name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public UUID getUserUuid() {
        return this.userUuid;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
