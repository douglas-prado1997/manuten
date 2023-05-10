package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class TaskModel {
    private UUID uuid;
    private String title;
    private String description;
    private UUID republicUuid;
    private UUID userUuid;
    private boolean isDone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresAt;
    
    public TaskModel() { }
    
    public void setUuid(String uuid) {
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (Exception error) {
            this.uuid = null;
        }
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
        
    public void setUserUuid(String userUuid) {
        try {
            this.userUuid = UUID.fromString(userUuid);
        } catch (Exception error) {
            this.userUuid = null;
        }
    }
    
    public void setRepublicUuid(String republicUuid) {
        try {
            this.republicUuid = UUID.fromString(republicUuid);
        } catch (Exception error) {
            this.republicUuid = null;
        }
    }
    
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public UUID getUserUuid() {
        return this.userUuid;
    }
    
    public UUID getRepublicUuid() {
        return this.republicUuid;
    }
    
    public boolean getIsDone() {
        return this.isDone;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    
    public LocalDateTime getExpiresAt() {
        return this.expiresAt;
    }
}
