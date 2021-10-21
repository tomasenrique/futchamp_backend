package futchamp.entities;


import javax.persistence.*;
import java.io.Serializable;

import static futchamp.contants.Keys.FK_TEAM_PLAYER;
import static futchamp.contants.Keys.ID_TEAM;
import static javax.persistence.CascadeType.*;

@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String dni;

    private String email;

    private String image;

    private String position;

    private Byte dorsal;

    // Relacion entre entidades

    // Relacion N:1 desde Team
    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER, cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = ID_TEAM, foreignKey = @ForeignKey(name = FK_TEAM_PLAYER))
    private Team team;


    // Builder
    public Player() {
    }

    // Seter and getter
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Byte getDorsal() {
        return dorsal;
    }

    public void setDorsal(Byte dorsal) {
        this.dorsal = dorsal;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
