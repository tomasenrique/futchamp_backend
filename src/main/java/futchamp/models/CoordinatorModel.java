package futchamp.models;

import futchamp.entities.Coordinator;

import java.io.Serializable;
import java.util.Calendar;

public class CoordinatorModel implements Serializable {

    private Long id;
    private String user;
    private String email;
    private String password;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public CoordinatorModel(Coordinator coordinator) {
        this.id = coordinator.getId();
        this.user = coordinator.getUser();
        this.email = coordinator.getEmail();
        this.password = coordinator.getPassword();
        this.createdAt = coordinator.getCreatedAt();
        this.updatedAt = coordinator.getUpdatedAt();
    }


    // Setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}
