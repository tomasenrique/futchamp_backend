package futchamp.services;

import futchamp.DAO.LeagueDAO;
import futchamp.DAO.TeamDAO;
import futchamp.converters.TeamConverter;
import futchamp.entities.League;
import futchamp.entities.Team;
import futchamp.generics.GService;
import futchamp.models.TeamModel;
import futchamp.serviceSI.TeamSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_TEAM;
import static futchamp.contants.Qualifiers.SER_TEAM;
import static futchamp.contants.Repositories.DAO_LEAGUE;
import static futchamp.contants.Repositories.DAO_TEAM;

@Service(SER_TEAM)
public class TeamService implements GService<TeamModel, Team>, TeamSI {

    private static final Logger logTeamService = LoggerFactory.getLogger(TeamService.class);


    @Autowired
    @Qualifier(DAO_TEAM)
    private TeamDAO teamDAO; // Para realizar CRUD a la BBDD de TEAM

    @Autowired
    @Qualifier(DAO_LEAGUE)
    private LeagueDAO leagueDAO; // Para realizar CRUD a la BBDD de LEAGUE

    @Autowired
    @Qualifier(CON_TEAM)
    private TeamConverter teamConverter; // Clase de tipo componente para convertir de model a entidad.


    // MÉTODOS GENERICOS

    @Override
    public ResponseEntity<Team> addElementListG(Team element) {
        if (teamDAO.existsTeamByName(element.getName())) {
            logTeamService.info("El nombre de equipo ya existe.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nombre de equipo ya existe.");
        } else {
            try {
                if (leagueDAO.existsLeagueByName(element.getLeague().getName())) {
                    League league = leagueDAO.findLeagueByName(element.getLeague().getName());
                    element.setLeague(league);
                    teamDAO.save(element);
                    logTeamService.info("Equipo creado y guardado.");
                    return ResponseEntity.status(HttpStatus.CREATED).body(element);
                } else {
                    logTeamService.info("El nombre de la league no existe.");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nombre de la league no existe.");
                }
            } catch (Exception e) {
                logTeamService.info("Error al crear equipo: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al crear equipo: " + e.getMessage());
            }
        }
    }

    @Override
    public List<TeamModel> getAllElementListG() {
        try {
            List<TeamModel> teamModelList = teamConverter.converterListG(teamDAO.findAll());
            if (teamModelList.isEmpty()) {
                logTeamService.info("Lista de Teams Vacia.");
            } else {
                logTeamService.info("Lista de Teams encontrada.");
            }
            return teamModelList;
        } catch (Exception e) {
            logTeamService.info("Error al buscar la lista de equipos: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar la lista de equipos: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Team> updateElementListG(Team element) {
        if (teamDAO.existsById(element.getId())) {
            logTeamService.info("Equipo encontrado.");
            if (leagueDAO.existsById(element.getLeague().getId())) {
                logTeamService.info("League de equipo encontrada.");
                try {
                    Team team = teamDAO.findById(element.getId()).get();
                    team.setName(element.getName());
                    team.setLogo(element.getLogo());
                    League league = leagueDAO.findById(element.getLeague().getId()).get();
                    team.setLeague(league); // Se actualiza League del equipo
                    teamDAO.save(team);
                    logTeamService.info("Equipo actualizado.");
                    return ResponseEntity.status(HttpStatus.OK).body(team);
                } catch (Exception e) {
                    logTeamService.info("CATCH: Error al actualizar el equipo: " + e.getMessage());
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al actualizar el equipo: " + e.getMessage());
                }
            } else {
                logTeamService.info("ELSE: No existe la league del equipo a actualizar");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE: No existe la league del equipo a actualizar");
            }
        } else {
            logTeamService.info("ELSE: No existe el equipo a actualizar.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE: No existe el equipo a actualizar.");
        }
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        try {
            if (teamDAO.existsById(idElement)) {
                Team team = teamDAO.findById(idElement).get();
                teamDAO.deleteById(team.getId());
                logTeamService.info("Equipo encontrado y elimindo.");
                return ResponseEntity.status(HttpStatus.OK).body(team);
            } else {
                logTeamService.info("El equipo a eliminar no existe.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El equipo a eliminar no existe.");
            }
        } catch (Exception e) {
            logTeamService.info("Error al eleiminar el equipo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eleiminar el equipo: " + e.getMessage());
        }
    }

    // MÉTODOS NO GENERICOS
    @Override
    public ResponseEntity<TeamModel> getTeamByNameSI(String nameTeam) {
        try {
            if (teamDAO.existsTeamByName(nameTeam)) {
                logTeamService.info("Equipo encontrado");
                return ResponseEntity.status(HttpStatus.OK).body(teamConverter.converterElementG(teamDAO.findTeamByName(nameTeam)));
            } else {
                logTeamService.info("No existe equipo con ese nombre.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE: No existe equipo con ese nombre.");
            }
        } catch (Exception e) {
            logTeamService.info("Error al buscar el equipo: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al buscar el equipo: " + e.getMessage());
        }
    }

    @Override
    public List<TeamModel> getAllTeamsByLeagueSI(String nameLeague) {
        try {
            League league = leagueDAO.findLeagueByName(nameLeague);
            if (leagueDAO.existsLeagueByName(nameLeague)) {
                logTeamService.info("Lista de equipos por League encontrada.");
            } else {
                logTeamService.info("Lista de equipos por League vacia.");
            }
            return teamConverter.converterListG(teamDAO.findTeamByLeague(league));
        } catch (Exception e) {
            logTeamService.info("Error al encontrar los equipo por nombre de League: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al encontrar los equipo por nombre de League: " + e.getMessage());
        }
    }


}
