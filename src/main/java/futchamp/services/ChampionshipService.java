package futchamp.services;

import futchamp.DAO.ChampionshipDAO;
import futchamp.DAO.LeagueDAO;
import futchamp.DAO.TeamDAO;
import futchamp.converters.ChampionshipConverter;
import futchamp.entities.Championship;
import futchamp.entities.League;
import futchamp.entities.Team;
import futchamp.generics.GService;
import futchamp.models.ChampionshipModel;
import futchamp.serviceSI.ChampionshipSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_CHAMPIONSHIP;
import static futchamp.contants.Qualifiers.SER_CHAMPIONSHIP;
import static futchamp.contants.Repositories.*;

@Service(SER_CHAMPIONSHIP)
public class ChampionshipService implements GService<ChampionshipModel, Championship>, ChampionshipSI {

    private static final Logger logChampionshipService = LoggerFactory.getLogger(ChampionshipService.class);

    @Autowired
    @Qualifier(DAO_CHAMPIONSHIP)
    private ChampionshipDAO championshipDAO; // Para realizar CRUD a la base de datos de CHAMPIONSHIP

    @Autowired
    @Qualifier(CON_CHAMPIONSHIP)
    private ChampionshipConverter championshipConverter; // Clase de tipo componente para convertir de model a entidad.

    @Autowired
    @Qualifier(DAO_LEAGUE)
    private LeagueDAO leagueDAO; // Para realizar CRUD a la base de datos de LEAGUE

    @Autowired
    @Qualifier(DAO_TEAM)
    private TeamDAO teamDAO; // Para realizar CRUD a la base de datos de TEAM



    // MÉTODOS GENERICOS
    @Override
    public ResponseEntity<Championship> addElementListG(Championship element) {
        try {

            if (leagueDAO.existsLeagueByName(element.getNameLeague())) {
                League league = leagueDAO.findLeagueByName(element.getNameLeague());
                List<Team> teamList = teamDAO.findTeamByLeague(league);

                if (teamList.isEmpty()) {


                    logChampionshipService.info("");

                } else {
                    logChampionshipService.info("Lista de equipos vacio.");
                }


            } else {
                logChampionshipService.info("No existe league.");
            }
        } catch (Exception e) {
            logChampionshipService.info("CATCH: Error al crear el campeonato: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al crear el campeonato: " + e.getMessage());
        }


        return null;
    }


    @Override
    public ResponseEntity<List<ChampionshipModel>> getAllElementListG() {
        return null;
    }

    @Override
    public ResponseEntity<Championship> updateElementListG(Championship element) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }

    // MÉTODOS NO GENERICOS
}
