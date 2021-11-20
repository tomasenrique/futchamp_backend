package futchamp.DAO;


import futchamp.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static futchamp.contants.Repositories.DAO_MATCH;

@Repository(DAO_MATCH)
public interface MatchDAO extends JpaRepository<Match, Serializable> {
}
