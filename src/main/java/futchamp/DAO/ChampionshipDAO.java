package futchamp.DAO;

import futchamp.entities.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;

import static futchamp.contants.Repositories.DAO_CHAMPIONSHIP;

@Repository(DAO_CHAMPIONSHIP)
public interface ChampionshipDAO extends JpaRepository<Championship, Serializable> {


    /**
     * Obtiene un campeonato por medio del nombre de la League y su fecha de inicio
     *
     * @param nameLeague            Sera el nombre de la League.
     * @param dateStartShampionship Sera la fecha de inicio del campeonato.
     * @return Sera un objeto campeonato con todos sus datos
     */
    Championship findChampioshipBynameLeagueAndDate(String nameLeague, LocalDate dateStartShampionship);

    /**
     * Verifica si existe un campeonato por medio de su nombre y fecha
     *
     * @param nameLeague            Sera el nombre de la League.
     * @param dateStartShampionship Sera la fecha de inicio del campeonato.
     * @return Devuelve True si existe o False si no
     */
    boolean existsChampioshipBynameLeagueAndDate(String nameLeague, LocalDate dateStartShampionship);

}
