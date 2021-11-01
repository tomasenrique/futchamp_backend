package futchamp.DAO;

import futchamp.entities.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import static futchamp.contants.Repositories.DAO_COORDINATOR;

@Repository(DAO_COORDINATOR)
public interface CoordinatorDAO extends JpaRepository<Coordinator, Serializable> {

    /**
     * Verifica si existe un coordinator por medio de su email
     *
     * @param email Sera el correo que es un dato de tipo unico.
     * @return Devuelve True si existe o False si no
     */
    boolean existsCoordinatorByEmail(String email);

    /**
     * Verifica si existe un coordinator por medio de su nombre de usuario
     *
     * @param user Sera el nombre de usuario que es un dato de tipo unico.
     * @return Devuelve True si existe o False si no
     */
    boolean existsCoordinatorByUser(String user);

    /**
     * Devuelve un coordinador
     *
     * @param user Sera el nombre de usuario que es un dato de tipo unico.
     * @return Sera un objeto de tipo Coordinator con los datos.
     */
    Coordinator findCoordinatorByUser(String user);
}
