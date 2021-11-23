package futchamp.models;

import futchamp.entities.Player;
import futchamp.entities.Team;

import java.io.Serializable;
import java.util.Calendar;

public class PlayerModel implements Serializable {

    private Long id;
    private String name;
    private String lastname;
    private String dni;
    private String email;
    private String image;
    private String position;
    private Byte dorsal;
    private Team team;
    private Calendar createdAt;
    private Calendar updatedAt;

    // Builder
    public PlayerModel(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.lastname = player.getLastname();
        this.dni = player.getDni();
        this.email = player.getEmail();
        this.image = player.getImage();
        this.position = player.getPosition();
        this.dorsal = player.getDorsal();
        this.team = player.getTeam();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
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
