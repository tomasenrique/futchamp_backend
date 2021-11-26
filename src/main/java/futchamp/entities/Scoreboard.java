package futchamp.entities;

import futchamp.configuration.auditable.Auditable;

import javax.persistence.*;
import java.io.Serializable;

import static futchamp.contants.DataType.TINYINT;
import static futchamp.contants.Keys.FK_MATCH_SCOREBOARD;
import static futchamp.contants.Keys.ID_MATCH;
import static javax.persistence.CascadeType.*;

@Entity
public class Scoreboard extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = TINYINT)
    private Integer local;

    @Column(nullable = false, columnDefinition = TINYINT)
    private Integer visitor;

    @Column(nullable = false)
    private Boolean realized;

    // Relacion entre entidades

    // Relación 1:1 desde Match (matches ne BD)
    @OneToOne(targetEntity = Match.class, fetch = FetchType.EAGER, cascade = {ALL})
    @JoinColumn(name = ID_MATCH, foreignKey = @ForeignKey(name = FK_MATCH_SCOREBOARD), nullable = false)
    private Match match;

    // Builder
    public Scoreboard() {
    }

    public Scoreboard(Integer local, Integer visitor, Boolean realized, Match match) {
        this.local = local;
        this.visitor = visitor;
        this.realized = realized;
        this.match = match;
    }

    // Setter and getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Boolean getRealized() {
        return realized;
    }

    public void setRealized(Boolean realized) {
        this.realized = realized;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
