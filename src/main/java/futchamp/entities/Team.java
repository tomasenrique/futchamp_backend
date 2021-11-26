package futchamp.entities;

import futchamp.configuration.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static futchamp.contants.Keys.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Team extends Auditable implements Serializable {

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
    @OneToMany(mappedBy = MAPPEDBY_TEAM, cascade = ALL, fetch = LAZY, targetEntity = Player.class)
    private List<Player> players;

    // Relación 1:N hacia Match (Partido local)
    @OneToMany(mappedBy = MAPPEDBY_TEAM_LOCAL, cascade = {MERGE, DETACH, PERSIST, REFRESH}, fetch = LAZY, targetEntity = Match.class)
    private List<Team> teamListLocal;

    // Relación 1:N hacia Match (Partido visitor)
    @OneToMany(mappedBy = MAPPEDBY_TEAM_VISITOR, cascade = {MERGE, DETACH, PERSIST, REFRESH}, fetch = LAZY, targetEntity = Match.class)
    private List<Team> teamListVisitor;

    // Relación 1:1 hacia Ranking (tabla)
    @OneToOne(mappedBy = MAPPEDBY_TEAM,  cascade = {ALL}, fetch = LAZY, targetEntity = Ranking.class)
    private Ranking ranking;


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
