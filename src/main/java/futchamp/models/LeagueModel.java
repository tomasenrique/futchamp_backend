package futchamp.models;

import futchamp.entities.League;

import java.io.Serializable;

public class LeagueModel implements Serializable {

    private Long id;
    private String name;
    private String logo;

    // Builder
    public LeagueModel(League league) {
        this.id = league.getId();
        this.name = league.getName();
        this.logo = league.getLogo();
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
}
