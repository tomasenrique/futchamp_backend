package futchamp.controllers;

import futchamp.entities.Coordinator;
import futchamp.models.CoordinatorModel;
import futchamp.services.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_COORDINATOR;

@RestController
@RequestMapping(COORDINATOR)
public class CoordinatorController {

    @Autowired
    @Qualifier(SER_COORDINATOR)
    private CoordinatorService coordinatorService;

    @PostMapping(SLASH)
    public ResponseEntity<Coordinator> addCoordinator(@RequestBody Coordinator coordinator) {
        return coordinatorService.addElementListG(coordinator);
    }

    @GetMapping(SLASH + LIST)
    public ResponseEntity<List<CoordinatorModel>> getAllCoordinators() {
        return coordinatorService.getAllElementListG();
    }

    @GetMapping(SLASH + REGISTER)
    public ResponseEntity<CoordinatorModel> getCoordinatorByUserOrEmail(@RequestBody Coordinator coordinator) {
        return coordinatorService.getCoordinatorByUserOrEmailSI(coordinator.getUser(), coordinator.getEmail());
    }

    @GetMapping(SLASH + VERIFICATION_COORDINATOR)
    public ResponseEntity<Boolean> verifyAuthorization(@RequestBody Coordinator coordinator) {
        return coordinatorService.verifyAuthorizationUserSI(coordinator.getUser(), coordinator.getPassword());
    }

    @PutMapping(SLASH)
    public ResponseEntity<Coordinator> updateCoordinator(@RequestBody Coordinator coordinator) {
        return coordinatorService.updateElementListG(coordinator);
    }

    @DeleteMapping(SLASH + ID_COORDINATOR)
    public ResponseEntity<?> deleteCoordinator(@PathVariable Long idCoordinator) {
        return coordinatorService.deleteElementListG(idCoordinator);
    }

}
