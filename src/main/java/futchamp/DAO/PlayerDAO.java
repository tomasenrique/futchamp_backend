package futchamp.DAO;

import futchamp.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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

}
