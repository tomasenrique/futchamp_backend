package futchamp.services;


import futchamp.DAO.CoordinatorDAO;
import futchamp.converters.CoordinatorConverter;
import futchamp.entities.Coordinator;
import futchamp.generics.GService;
import futchamp.models.CoordinatorModel;
import futchamp.serviceSI.CoordinatorSI;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_COORDINATOR;
import static futchamp.contants.Qualifiers.SER_COORDINATOR;
import static futchamp.contants.Repositories.DAO_COORDINATOR;

@Service(SER_COORDINATOR)
public class CoordinatorService implements GService<CoordinatorModel, Coordinator>, CoordinatorSI {

    private static final Logger logCoordinatorService = LoggerFactory.getLogger(CoordinatorService.class);

    @Autowired
    @Qualifier(DAO_COORDINATOR)
    private CoordinatorDAO coordinatorDAO; // Para realizar CRUD a la base de datos

    @Autowired
    @Qualifier(CON_COORDINATOR)
    private CoordinatorConverter coordinatorConverter; // Clase de tipo componente para convertir de model a entidad.

    // MÉTODOS GENERICOS

    @Override
    public ResponseEntity<Coordinator> addElementListG(Coordinator element) {
        if (coordinatorDAO.existsCoordinatorByEmail(element.getEmail()) || coordinatorDAO.existsCoordinatorByUser(element.getUser())) {
            logCoordinatorService.info("El coordinador a crear ya existe.");
            throw new ResponseStatusException(HttpStatus.FOUND, "El coordinador a crear ya existe");
        } else {
            try {
                String claveHash = BCrypt.hashpw(element.getPassword(), BCrypt.gensalt()); // Encryptado estandar a 10
                element.setPassword(claveHash); // Se aplica la clave encriptada.
                coordinatorDAO.save(element); // Se guarda el coordinador creado.
                logCoordinatorService.info("Coordinador creado y guardado.");
                return ResponseEntity.status(HttpStatus.CREATED).body(element);
            } catch (Exception e) {
                logCoordinatorService.info("No se pudo crear el coordinador: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.FOUND, "No se pudo crear el coordinador: " + e.getMessage());
            }
        }
    }

    @Override
    public List<CoordinatorModel> getAllElementListG() {
        try {
            List<CoordinatorModel> coordinatorModelList = coordinatorConverter.converterListG(coordinatorDAO.findAll());
            if (coordinatorModelList.isEmpty()) {
                logCoordinatorService.info("Lista de coordinadores vacia.");
            } else {
                logCoordinatorService.info("Lista de coordinadores encontrada.");
            }
            return coordinatorModelList;
        } catch (Exception e) {
            logCoordinatorService.info("Error al buscar la lista de coordinadores: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar la lista de coordinadores: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Coordinator> updateElementListG(Coordinator element) {
        try {
            if (coordinatorDAO.existsCoordinatorByUser(element.getUser()) || coordinatorDAO.existsCoordinatorByEmail(element.getEmail())) {
                Coordinator coordinator = coordinatorDAO.findCoordinatorByUser(element.getUser());
                coordinator.setUser(element.getUser());
                coordinator.setEmail(element.getEmail());
                coordinator.setPassword(BCrypt.hashpw(element.getPassword(), BCrypt.gensalt())); // Encryptado estandar a 10
                coordinatorDAO.save(coordinator);
                logCoordinatorService.info("Coordinador encontrado y actualizado.");
                return ResponseEntity.status(HttpStatus.OK).body(coordinator);
            } else {
                logCoordinatorService.info("No existe el coordinador a actualizar.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coordinador a actualizar.");
            }
        } catch (Exception e) {
            logCoordinatorService.info("Error al actualizar Coordinador: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar Coordinador: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        try {
            if (coordinatorDAO.existsById(idElement)) {
                Coordinator coordinator = coordinatorDAO.findById(idElement).get();
                coordinatorDAO.deleteById(coordinator.getId());
                logCoordinatorService.info("Coordinador encontrado y borrado.");
                return ResponseEntity.status(HttpStatus.OK).body(coordinator);
            } else {
                logCoordinatorService.info("El coordinador a eliminar no existe.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El coordinador a eliminar no existe.");
            }
        } catch (Exception e) {
            logCoordinatorService.info("Error al eliminar el coordinador: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar el coordinador: " + e.getMessage());
        }
    }


    // MÉTODOS NO GENERICOS
    @Override
    public ResponseEntity<Boolean> verificarAutorizacionSI(String user, String password) {
        try {
            if (coordinatorDAO.existsCoordinatorByUser(user)) { // verifica la existencia del coordinador
                Coordinator coordinator = coordinatorDAO.findCoordinatorByUser(user);

                if (BCrypt.checkpw(password, coordinator.getPassword())) { // Compara las claves encriptadas
                    logCoordinatorService.info("Clave verificada OK.");
                    return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE); // Si es asi, tiene acceso
                } else {
                    logCoordinatorService.info("Las claves no coinciden.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
                }
            } else {
                logCoordinatorService.info("El nombre de usuario de coordinador no existe.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
            }
        } catch (Exception e) {
            logCoordinatorService.info("Error al buscar el usuario coordinador: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE);
        }
    }

    @Override
    public ResponseEntity<CoordinatorModel> getCoordinatorByUserOrEmailSI(String user, String email) {
        if (coordinatorDAO.existsCoordinatorByUser(user) || coordinatorDAO.existsCoordinatorByEmail(email)) {
            try {
                CoordinatorModel coordinatorModel = coordinatorConverter.converterElementG(coordinatorDAO.findCoordinatorByUserOrEmail(user, email));
                logCoordinatorService.info("Coordinador encontrado.");
                return ResponseEntity.status(HttpStatus.OK).body(coordinatorModel);
            } catch (Exception e) {
                logCoordinatorService.info("Error al buscar el coordinador: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar el coordinador: " + e.getMessage());
            }
        } else {
            logCoordinatorService.info("No existe el coordinador buscado.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el coordinador buscado.");
        }
    }
}
