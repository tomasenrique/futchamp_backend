package futchamp.DAO;

import futchamp.entities.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static futchamp.contants.Repositories.DAO_CHAMPIONSHIP;

@Repository(DAO_CHAMPIONSHIP)
public interface ChampionshipDAO extends JpaRepository<Championship, Serializable> {
}
