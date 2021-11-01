package futchamp.entities;


import futchamp.contants.Keys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static futchamp.contants.Keys.*;
import static javax.persistence.CascadeType.*;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String logo;

    // Relacion entre entidades

    // Relacion N:1 desde League
    @ManyToOne(targetEntity = League.class, fetch = FetchType.EAGER, cascade = {MERGE, DETACH, PERSIST, REFRESH})
    @JoinColumn(name = ID_LEAGUE, foreignKey = @ForeignKey(name = FK_LEAGUE_TEAM), nullable = false)
    private League league;

    // Relacion de 1:N hacia Player
    @OneToMany(mappedBy = MAPPEDBY_TEAM, cascade = ALL, fetch = FetchType.LAZY, targetEntity = Player.class)
    private List<Player> players;


    // Builder
    public Team() {
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}
