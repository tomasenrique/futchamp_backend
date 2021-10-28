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

    // Métodos genericos

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
                throw new ResponseStatusException(HttpStatus.FOUND, "El coordinador a crear ya existe: " + e.getMessage());
            }
        }
    }

    @Override
    public List<CoordinatorModel> getAllElementListG() {
        try {
            logCoordinatorService.info("Lista de coordinadores encontrada.");
            return coordinatorConverter.converterListG(coordinatorDAO.findAll());
        } catch (Exception e) {
            logCoordinatorService.info("Lista no encontrada: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay registros para mostrar: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Coordinator> updateElementListG(Coordinator element) {

        // TODO ==>> Aqui hay que configurar para que pida la clave para actualizar el registro


        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }


    // Métodos no genericos
    @Override
    public ResponseEntity<Boolean> verificarAutorizacion(String user, String password) {
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
}
