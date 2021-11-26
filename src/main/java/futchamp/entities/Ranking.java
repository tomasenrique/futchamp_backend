package futchamp.entities;

import futchamp.configuration.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;

import static futchamp.contants.DataType.TINYINT;
import static futchamp.contants.Keys.FK_TEAM_RANKING;
import static futchamp.contants.Keys.ID_TEAM;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Ranking extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte pj; // Partidos jugados

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte pg; // Partidos ganados

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte pe; // Partidos empatados

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte pp; // Partidos perdidos

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte gf; // Goles a favor

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte gc; // Goles en contra

    @Column(nullable = false, columnDefinition = TINYINT)
    private Byte pts; // Puntos totales

    // Relación 1:1 desde Team
    @OneToOne(targetEntity = Team.class, fetch = FetchType.EAGER, cascade = {ALL})
    @JoinColumn(name = ID_TEAM, foreignKey = @ForeignKey(name = FK_TEAM_RANKING), nullable = false)
    private Team team;


    // Builder
    public Ranking() {
    }

    // Setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getPj() {
        return pj;
    }

    public void setPj(Byte pj) {
        this.pj = pj;
    }

    public Byte getPg() {
        return pg;
    }

    public void setPg(Byte pg) {
        this.pg = pg;
    }

    public Byte getPe() {
        return pe;
    }

    public void setPe(Byte pe) {
        this.pe = pe;
    }

    public Byte getPp() {
        return pp;
    }

    public void setPp(Byte pp) {
        this.pp = pp;
    }

    public Byte getGf() {
        return gf;
    }

    public void setGf(Byte gf) {
        this.gf = gf;
    }

    public Byte getGc() {
        return gc;
    }

    public void setGc(Byte gc) {
        this.gc = gc;
    }

    public Byte getPts() {
        return pts;
    }

    public void setPts(Byte pts) {
        this.pts = pts;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
