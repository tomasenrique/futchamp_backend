package futchamp.controllers;

import futchamp.entities.Team;
import futchamp.models.TeamModel;
import futchamp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_TEAM;

@RestController
@RequestMapping(TEAM)
public class TeamController {

    @Autowired
    @Qualifier(SER_TEAM)
    private TeamService teamService;


    @PostMapping(SLASH)
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        return teamService.addElementListG(team);
    }

/*    @GetMapping(SLASH + LIST)
    public List<TeamModel> getAllTeams() {
        return teamService.getAllElementListG();
    }*/

    @GetMapping(SLASH + REGISTER + SLASH + NAME_TEAM)
    public TeamModel getTeamByName(@PathVariable String nameTeam) {
        return teamService.mostrarEquipoPorNombreSI(nameTeam);
    }

    @GetMapping(SLASH + LIST + SLASH + NAME_LEAGUE)
    public List<TeamModel> getAllTeamsByNAmeLeague(@PathVariable String nameLeague) {
        return teamService.mostrarListaEquiposPorLeagueSI(nameLeague);
    }

    @DeleteMapping(SLASH + ID_TEAM)
    public ResponseEntity<?> deleteTeam(@PathVariable Long idTeam) {
        return teamService.deleteElementListG(idTeam);
    }


}
