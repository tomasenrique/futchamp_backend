package futchamp.models;

import futchamp.entities.Championship;
import futchamp.entities.Match;
import futchamp.entities.Team;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MatchModel implements Serializable {

    private Long id;
    private LocalDate date; // Fecha del partido
    private LocalTime time; // Hora de inicio
    private Integer journey; // sera el numero de la jornada en donde se encuentra el partido
    private Team local;
    private Team visitor;
    private Championship championship;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public MatchModel(Match match) {
        this.id = match.getId();
        this.date = match.getDate();
        this.time = match.getTime();
        this.journey = match.getJourney();
        this.local = match.getLocal();
        this.visitor = match.getVisitor();
        this.championship = match.getChampionship();
        this.createdAt = match.getCreatedAt();
        this.updatedAt = match.getUpdatedAt();
    }

    // Setter and getter
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
