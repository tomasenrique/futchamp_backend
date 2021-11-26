package futchamp.services;

import futchamp.DAO.ChampionshipDAO;
import futchamp.DAO.MatchDAO;
import futchamp.DAO.ScoreboardDAO;
import futchamp.DAO.TeamDAO;
import futchamp.converters.MatchConverter;
import futchamp.entities.Championship;
import futchamp.entities.Match;
import futchamp.entities.Scoreboard;
import futchamp.entities.Team;
import futchamp.generics.GService;
import futchamp.models.MatchModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_MATCH;
import static futchamp.contants.Qualifiers.SER_MATCH;
import static futchamp.contants.Repositories.*;

@Service(SER_MATCH)
public class MatchService implements GService<MatchModel, Match> {

    private static final Logger logMatchService = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    @Qualifier(DAO_MATCH)
    private MatchDAO matchDAO;  // Para realizar CRUD a la base de datos de MATCH

    @Autowired
    @Qualifier(CON_MATCH)
    private MatchConverter matchConverter; // Clase de tipo componente para convertir de model a entidad.


    @Autowired
    @Qualifier(DAO_TEAM)
    private TeamDAO teamDAO; // Para realizar CRUD a la base de datos de TEAM

    @Autowired
    @Qualifier(DAO_CHAMPIONSHIP)
    private ChampionshipDAO championshipDAO; // Para realizar CRUD a la base de datos de CHAMPIONSHIP

    @Autowired
    @Qualifier(DAO_SCOREBOARD)
    private ScoreboardDAO scoreboardDAO; // Para realizar CRUD a la base de datos de SCOREBOARD


    @Override
    public ResponseEntity<Match> addElementListG(Match element) {
        try {
            if (teamDAO.existsTeamByName(element.getLocal().getName()) && teamDAO.existsTeamByName(element.getVisitor().getName())) {
                logMatchService.info("Localizado equipos para campeonato.");
                if (championshipDAO.existsChampioshipBynameLeagueAndDate(element.getChampionship().getNameLeague(), element.getChampionship().getDate())) {
                    logMatchService.info("Localizado campeonato.");
                    Team local = teamDAO.findTeamByName(element.getLocal().getName());
                    Team visitor = teamDAO.findTeamByName(element.getVisitor().getName());
                    Championship championship = championshipDAO.findChampioshipBynameLeagueAndDate(element.getChampionship().getNameLeague(), element.getChampionship().getDate());
                    element.setLocal(local);
                    element.setVisitor(visitor);
                    element.setChampionship(championship);
                    logMatchService.info("Guardando partido en el campeonato e inicializado marcador.");
                    scoreboardDAO.save(new Scoreboard(0, 0, false, matchDAO.save(element))); // Se inicializa el marcador y se guarda el partido
                    logMatchService.info("Partido guardado.");
                    return ResponseEntity.status(HttpStatus.OK).body(element);
                } else {
                    logMatchService.info("ELSE CHAMPIONSHIP: No existen el campeonato para agregar el partido.");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE CHAMPIONSHIP: No existen el campeonato para agregar el partido.");
                }
            } else {
                logMatchService.info("ELSE TEAM: No existen los equipos para agregar el partido.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE TEAM: No existen los equipos para agregar el partido.");
            }
        } catch (Exception e) {
            logMatchService.info("CATCH: Error al agregar partido: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al agregar partido: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<MatchModel>> getAllElementListG() {
        try {
            List<MatchModel> matchModels = matchConverter.converterListG(matchDAO.findAll());
            if (!matchModels.isEmpty()) {
                logMatchService.info("Lista de partidos obtenidos.");
            } else {
                logMatchService.info("Lista de partidos vacia.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(matchModels);
        } catch (Exception e) {
            logMatchService.info("CATCH: Error al obtener los partidos: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al obtener los partidos: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Match> updateElementListG(Match element) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }
}
