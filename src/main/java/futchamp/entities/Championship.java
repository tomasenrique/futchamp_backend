package futchamp.entities;

import futchamp.configuration.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static futchamp.contants.Keys.MAPPEDBY_CHAMPIONSHIP;
import static javax.persistence.CascadeType.*;

@Entity
public class Championship extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nameLeague;

    private LocalDate date;

    private LocalTime time;

    // Relacion entre entidades

    // Relacion 1:N hacia Match(matches en BD)
    @OneToMany(mappedBy = MAPPEDBY_CHAMPIONSHIP, cascade = {ALL}, fetch = FetchType.LAZY, targetEntity = Match.class)
    private List<Match> matchList;


    // Builder
    public Championship() {
    }

    // Setter and getter
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
}
