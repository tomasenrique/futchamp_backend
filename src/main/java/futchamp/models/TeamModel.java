package futchamp.models;

import futchamp.entities.League;
import futchamp.entities.Team;

import java.io.Serializable;

public class TeamModel implements Serializable {

    private Long id;
    private String name;
    private String logo;
    private League league;

    //Builder
    public TeamModel(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.logo = team.getLogo();
        this.league = team.getLeague();
    }

    // Setter and Getter
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
