package futchamp.DAO;

import futchamp.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static futchamp.contants.Repositories.DAO_LEAGUE;

@Repository(DAO_LEAGUE)
public interface LeagueDAO extends JpaRepository<League, Serializable> {

    /**
     * Verifica si existe una League por medio de su nombre
     *
     * @param name Sera el nombre que es un dato de tipo unico.
     * @return Devuelve True si existe o False si no
     */
    boolean existsLeagueByName(String name);

    /**
     * Verifica que existe una League por medio de su ID
     *
     * @param idLeague Sera el Id de la League
     * @return Devuelve True si existe o False si no
     */
    boolean existsLeagueById(Long idLeague);

    /**
     * Busca una League por medio de su nombre.
     *
     * @param nameLeague Sera el nombre de la League que es un dato de tipo unico.
     * @return Sera un objeto de tipo League
     */
    League findLeagueByName(String nameLeague);

}
