package futchamp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static futchamp.contants.Keys.MAPPEDBY_LEAGUE;

@Entity
public class League implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String logo;

    // Relacion entre entidades

    // Relacion 1:N hacia Team
    @OneToMany(mappedBy = MAPPEDBY_LEAGUE, cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Team.class)
    private List<League> teams;


    //Builder
    public League() {
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
}
