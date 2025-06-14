import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = now();
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = now();
    }

    private String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    @Override
    public String toString(){
        return String.format("ID: %d | Desc: %s | Status: %s | Created: %s | Updated: %s",
                id, description, status, createdAt, updatedAt);
    }
}
