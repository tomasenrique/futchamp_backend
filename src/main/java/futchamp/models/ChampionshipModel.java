package futchamp.models;


import futchamp.entities.Championship;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;


public class ChampionshipModel implements Serializable {

    private Long id;
    private String nameLeague;
    private LocalDate date;
    private LocalTime time;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public ChampionshipModel(Championship championship) {
        this.id = championship.getId();
        this.nameLeague = championship.getNameLeague();
        this.date = championship.getDate();
        this.time = championship.getTime();
        this.createdAt = championship.getCreatedAt();
        this.updatedAt = championship.getUpdatedAt();
    }


    // Setter and geter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameLeague() {
        return nameLeague;
    }

    public void setNameLeague(String nameLeague) {
        this.nameLeague = nameLeague;
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
