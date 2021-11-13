package futchamp.serviceSI;

import futchamp.entities.League;
import futchamp.entities.Team;
import futchamp.models.TeamModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Esta interface sera para declarar los metodos a implementar de la entidad Coordinator y que no sean basicos como los
 * de 'GService' y que dependera de cada entidad y sus atributos. Seran para la clase service y usarlos en el controlador.
 */
public interface TeamSI {

    /**
     * Busca un equipo por medio de su nombre.
     *
     * @param nameTeam Sera el nombre del equipo a buscar.
     * @return Sera un objeto de tipo Team(equipo)
     */
    ResponseEntity<TeamModel> getTeamByNameSI(String nameTeam);

    /**
     * Busca una lista de eauipos de una misma league.
     *
     * @param nameLeague Sera el nombre de la league al que pertenecen los equipos.
     * @return Sera una lista de equipos de una misma League
     */
    List<TeamModel> getAllTeamsByLeagueSI(String nameLeague);

}
