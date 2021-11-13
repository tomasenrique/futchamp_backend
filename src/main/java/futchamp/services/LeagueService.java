package futchamp.services;

import futchamp.DAO.LeagueDAO;
import futchamp.converters.LeagueConverter;
import futchamp.entities.League;
import futchamp.generics.GService;
import futchamp.models.LeagueModel;
import futchamp.serviceSI.LeagueSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_LEAGUE;
import static futchamp.contants.Qualifiers.SER_LEAGUE;
import static futchamp.contants.Repositories.DAO_LEAGUE;

@Service(SER_LEAGUE)
public class LeagueService implements GService<LeagueModel, League>, LeagueSI {

    private static final Logger logLeagueService = LoggerFactory.getLogger(LeagueService.class);

    @Autowired
    @Qualifier(DAO_LEAGUE)
    private LeagueDAO leagueDAO; // Para realizar CRUD a la BBDD

    @Autowired
    @Qualifier(CON_LEAGUE)
    private LeagueConverter leagueConverter; // Clase de tipo componente para convertir de model a entidad.


    // MÉTODOS GENERICOS

    @Override
    public ResponseEntity<League> addElementListG(League element) {
        if (leagueDAO.existsLeagueByName(element.getName())) {
            logLeagueService.info("El nombre de la league ya existe.");
            throw new ResponseStatusException(HttpStatus.FOUND, "La league a crear ya existe.");
        } else {
            try {
                leagueDAO.save(element);
                logLeagueService.info("League creado y guardado.");
                return ResponseEntity.status(HttpStatus.CREATED).body(element);
            } catch (Exception e) {
                logLeagueService.info("No se pudo crear la league: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.FOUND, "No se puedo crear la league: " + e.getMessage());
            }
        }
    }

    @Override
    public List<LeagueModel> getAllElementListG() {
        try {
            List<LeagueModel> leagueModelList = leagueConverter.converterListG(leagueDAO.findAll());
            if (leagueModelList.isEmpty()) {
                logLeagueService.info("Lista de leagues vacia.");
            } else {
                logLeagueService.info("Lista de leagues encontrada.");
            }
            return leagueModelList;
        } catch (Exception e) {
            logLeagueService.info("Error al buscar la lista de leagues: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar la lista de leagues: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<League> updateElementListG(League element) {
        try {
            if (leagueDAO.existsById(element.getId())) {
                League league = leagueDAO.findById(element.getId()).get();
                league.setName(element.getName());
                league.setLogo(element.getLogo());
                leagueDAO.save(league);
                logLeagueService.info("League encontrada y actualizada.");
                return ResponseEntity.status(HttpStatus.OK).body(league);
            } else {
                logLeagueService.info("No existe la League a actualizar.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la League a actualizar.");
            }
        } catch (Exception e) {
            logLeagueService.info("Error al actualizar la League: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar la League: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        try {
            if (leagueDAO.existsById(idElement)) {
                League league = leagueDAO.findById(idElement).get();
                leagueDAO.deleteById(league.getId());
                logLeagueService.info("League encontrada y eliminada.");
                return ResponseEntity.status(HttpStatus.OK).body(league);
            } else {
                logLeagueService.info("La league a eliminar no existe.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La league a eliminar no existe.");
            }
        } catch (Exception e) {
            logLeagueService.info("Error al eliminar la league: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar la league: " + e.getMessage());
        }

    }

    // MÉTODOS NO GENERICOS
    @Override
    public ResponseEntity<LeagueModel> getLeagueByNameSI(String nameLeague) {
        try {
            if (leagueDAO.existsLeagueByName(nameLeague)) {
                LeagueModel leagueModel = leagueConverter.converterElementG(leagueDAO.findLeagueByName(nameLeague));
                logLeagueService.info("League encontrada.");
                return ResponseEntity.status(HttpStatus.OK).body(leagueModel);
            } else {
                logLeagueService.info("No existe la liga con el nombre: " + nameLeague);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la liga con el nombre: " + nameLeague);
            }
        } catch (Exception e) {
            logLeagueService.info("Error al obtener la liga: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener la liga: " + e.getMessage());
        }
    }


}
