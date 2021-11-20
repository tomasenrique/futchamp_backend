package futchamp.DAO;

import futchamp.entities.Match;
import futchamp.entities.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static futchamp.contants.Repositories.DAO_SCOREBOARD;

@Repository(DAO_SCOREBOARD)
public interface ScoreboardDAO extends JpaRepository<Scoreboard, Serializable> {

    /**
     * Verifica si existe el marcador de un partido
     *
     * @param match Sera el obteto de tipo Match(partido) con su identificador
     * @return Devuelve True si existe o False si no
     */
    boolean existsScoreboardByMatch(Match match);

}
