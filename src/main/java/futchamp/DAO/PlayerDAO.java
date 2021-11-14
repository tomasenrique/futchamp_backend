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
     * @param dniPlayer Sera el dato alfanumerico de tipo unico.
     * @return Sera el dato de tipo boleano True si existe y False si no es así
     */
    boolean existsPlayerByDni(String dniPlayer);

    /**
     * Verifica que exista un jugador por medio de su email
     *
     * @param emailPlayer Sera el dato alfanumerico de tipo unico.
     * @return Sera el dato de tipo boleano True si existe y False si no es así
     */
    boolean existsPlayerByEmail(String emailPlayer);


    /**
     * Devuelve una lista de jugadores de un mismo equipo
     *
     * @param team Sera el objeto Equipo con el identificador para obtener los jugadores
     * @return Sera la lista de jugadores de un mismo equipo.
     */
    List<Player> findPlayerByTeam(Team team);

}
