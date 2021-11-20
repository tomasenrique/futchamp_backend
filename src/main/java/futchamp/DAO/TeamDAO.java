package futchamp.DAO;

import futchamp.entities.League;
import futchamp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

import static futchamp.contants.Repositories.DAO_TEAM;

@Repository(DAO_TEAM)
public interface TeamDAO extends JpaRepository<Team, Serializable> {

    /**
     * Busca un equipo por su nombre que es un dato de tipo unico.
     *
     * @param name Sera el nombre del eequipo.
     * @return Sera un objeto de tipo Equipo (Team)
     */
    Team findTeamByName(String name);

    /**
     * Devuelve una lista de equipos de una misma league
     *
     * @param league Sera el dato de tipo objeto League
     * @return Sera una lista de equipos(Teams) de una misma league.
     */
    List<Team> findTeamByLeague(League league);

    /**
     * Verifica si existe un equipo por medio de su nombre
     *
     * @param nameTeam Sera el nombre del equipo que es un dato de tipo unico.
     * @return Devuelve True si existe o False si no
     */
    boolean existsTeamByName(String nameTeam);


}
