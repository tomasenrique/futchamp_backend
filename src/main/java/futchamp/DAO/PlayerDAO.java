package futchamp.DAO;

import futchamp.entities.Player;
import futchamp.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

import static futchamp.contants.Repositories.DAO_PLAYER;

@Repository(DAO_PLAYER)
public interface PlayerDAO extends JpaRepository<Player, Serializable> {

    /**
     * Verifica que exista un jugador por medio de su dni
     *
     * @param dni Sera el dato alfanumerico de tipo unico.
     * @return Sera el dato de tipo boleano True si existe y False si no es así
     */
    boolean existsPlayerByDni(String dni);

    /**
     * Verifica que exista un jugador por medio de su email
     *
     * @param email Sera el dato alfanumerico de tipo unico.
     * @return Sera el dato de tipo boleano True si existe y False si no es así
     */
    boolean existsPlayerByEmail(String email);

    /**
     * Devuelve una lista de jugadores de un mismo equipo
     *
     * @param team Sera el objeto Equipo con el identificador para obtener los jugadores
     * @return Sera la lista de jugadores de un mismo equipo.
     */
    List<Player> findPlayerByTeam(Team team);

    /**
     * Obtiene un jugador por cualquiera de los dos siguientes datos.
     *
     * @param dni   Sera el dato alfanumerico de tipo unico del jugador.
     * @param email Sera el correo electronico, dato de tipo unico de jugador.
     * @return Sera el objeto Player
     */
    Player findPlayerByDniOrEmail(String dni, String email);

    /**
     * Obtiene un jugador o jugadores(lista) por medio del nombre del jugador, segun las letras que contengan este.
     * Método similar a LIKE
     *
     * @param name Sera el nombre del jugador
     * @return Sera una lista con uno o varios objetos de tipo Player
     */
    List<Player> findPlayerByNameContaining(String name);

    /**
     * Obtiene un jugador o jugadores(lista) por medio del apellido del jugador, segun las letras que contengan este.
     * Método similar a LIKE
     *
     * @param lastname Sera el apellido del jugador
     * @return Sera una lista con uno o varios objetos de tipo Player
     */
    List<Player> findPlayerByLastnameContaining(String lastname);


}
