package Models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


public class FeedbackModel {
    private UUID uuid;
    private String comment;
    private double score;
    private UUID userUuid;
    private UUID taskUuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public FeedbackModel() { }
    
    public void setUuid(String uuid) {
        try {
            this.uuid = UUID.fromString(uuid);
        } catch (Exception error) {
            this.uuid = null;
        }
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    
    public void setUserUuid(String userUuid) {
        try {
            this.userUuid = UUID.fromString(userUuid);
        } catch (Exception error) {
            this.userUuid = null;
        }
    }
    
    public void setTaskUuid(String taskUuid) {
        try {
            this.taskUuid = UUID.fromString(taskUuid);
        } catch (Exception error) {
            this.taskUuid = null;
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
    
    public String getComment() {
        return this.comment;
    }
    
    public double getScore() {
        return this.score;
    }
    
    public UUID getUserUuid() {
        return this.userUuid;
    }
    
    public UUID getTaskUuid() {
        return this.taskUuid;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
