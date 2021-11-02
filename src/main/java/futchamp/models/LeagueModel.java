package futchamp.models;

import futchamp.entities.League;

import java.io.Serializable;
import java.util.Calendar;

public class LeagueModel implements Serializable {

    private Long id;
    private String name;
    private String logo;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public LeagueModel(League league) {
        this.id = league.getId();
        this.name = league.getName();
        this.logo = league.getLogo();
        this.createdAt = league.getCreatedAt();
        this.updatedAt = league.getUpdatedAt();
    }

    // Setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
