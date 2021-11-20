package futchamp.entities;

import futchamp.configuration.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static futchamp.contants.Keys.*;
import static futchamp.contants.Links.CHAMPIONSHIP;
import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "matches")
public class Match extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Fecha del partido

    private LocalTime time; // Hora de inicio

    private Integer journey; // sera el numero de la jornada en donde se encuentra el partido

    // Relacion entre entidades

    // Relación N:1 desde Team (Partido local)
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = ID_TEAM_LOCAL, foreignKey = @ForeignKey(name = FK_TEAM_MATCH_LOCAL), nullable = false)
    private Team local;

    // Relación N:1 desde Team (Partido visitor)
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = ID_TEAM_VISITOR, foreignKey = @ForeignKey(name = FK_TEAM_MATCH_VISITOR), nullable = false)
    private Team visitor;

    // Relación N:1 desde Championship
    @ManyToOne(targetEntity = Championship.class, fetch = FetchType.EAGER, cascade = {DETACH, MERGE, PERSIST, PERSIST})
    @JoinColumn(name = ID_CHAMPIONSHIP, foreignKey = @ForeignKey(name = FK_CHAMPIONSHIP_MATCH), nullable = false)
    private Championship championship;

    // Relación 1:1 hacia Scoreboard (marcador)
    @OneToOne(mappedBy = MAPPEDBY_MATCH, cascade = {DETACH, MERGE, PERSIST,REFRESH}, fetch = FetchType.LAZY, targetEntity = Scoreboard.class)
    private Scoreboard scoreboard;


    // Builder
    public Match() {
    }

    public Match(Team local, Team visitor) {
        this.local = local;
        this.visitor = visitor;
    }

    // Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getJourney() {
        return journey;
    }

    public void setJourney(Integer journey) {
        this.journey = journey;
    }

    public Team getLocal() {
        return local;
    }

    public void setLocal(Team local) {
        this.local = local;
    }

    public Team getVisitor() {
        return visitor;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
