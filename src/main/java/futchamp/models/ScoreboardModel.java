package futchamp.models;

import futchamp.entities.Championship;
import futchamp.entities.Match;
import futchamp.entities.Scoreboard;

import java.io.Serializable;
import java.util.Calendar;

public class ScoreboardModel implements Serializable {

    private Long id;
    private Integer local;
    private Integer visitor;
    private Match match;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public ScoreboardModel(Scoreboard scoreboard) {
        this.id = scoreboard.getId();
        this.local = scoreboard.getLocal();
        this.visitor = scoreboard.getVisitor();
        this.match = scoreboard.getMatch();
        this.createdAt = scoreboard.getCreatedAt();
        this.updatedAt = scoreboard.getUpdatedAt();
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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
