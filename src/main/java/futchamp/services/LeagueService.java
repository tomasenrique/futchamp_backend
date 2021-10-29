package futchamp.services;

import futchamp.DAO.LeagueDAO;
import futchamp.converters.LeagueConverter;
import futchamp.entities.League;
import futchamp.generics.GService;
import futchamp.models.LeagueModel;
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
public class LeagueService implements GService<LeagueModel, League> {

    private static final Logger logLeagueService = LoggerFactory.getLogger(LeagueService.class);

    @Autowired
    @Qualifier(DAO_LEAGUE)
    private LeagueDAO leagueDAO; // Para realizar CRUD a la BBDD

    @Autowired
    @Qualifier(CON_LEAGUE)
    private LeagueConverter leagueConverter; // Clase de tipo componente para convertir de model a entidad.


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
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }
}
