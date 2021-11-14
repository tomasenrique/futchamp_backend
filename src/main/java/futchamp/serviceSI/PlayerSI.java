package futchamp.serviceSI;

import futchamp.models.PlayerModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Esta interface sera para declarar los metodos a implementar de la entidad Coordinator y que no sean basicos como los
 * de 'GService' y que dependera de cada entidad y sus atributos. Seran para la clase service y usarlos en el controlador.
 */
public interface PlayerSI {

    /**
     * Obtiene una lista de jugadores de un mismo equipo por medio de su nombre
     *
     * @param nameTeam Sera el nombre del equipo que es un dato de tipo unico.
     * @return Sera una lista de juagdores de un mismo equipo.
     */
    ResponseEntity<List<PlayerModel>> getAllPlayersByNameTeamSI(String nameTeam);

}
