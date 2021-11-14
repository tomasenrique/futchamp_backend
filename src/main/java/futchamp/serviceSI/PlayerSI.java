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
     * @return Sera una lista de jugadores de un mismo equipo de tipo Model
     */
    ResponseEntity<List<PlayerModel>> getAllPlayersByNameTeamSI(String nameTeam);

    /**
     * Obtiene un jugador por medio de su dni o su email
     *
     * @param dni   Sera el documento de identidad del jugador
     * @param email Sera el correo electronico del jugador
     * @return Sera un objeto de tipo Model del jugador
     */
    ResponseEntity<PlayerModel> getPlayerByDniOrEmailSI(String dni, String email);

    /**
     * Obtiene un jugador o jugadores por medio de su nombre o apellido del jugador, segun las letras que contengan estos.
     * Método similar a LIKE
     *
     * @param name     Sera el nombre del jugador
     * @param lastname Sera el apellido del jugador
     * @return Sera una lista con uno o varios objetos de tipo PlayerModel
     */
    ResponseEntity<List<PlayerModel>> getPlayersByNameOrLastnameContainingSI(String name, String lastname);

}
