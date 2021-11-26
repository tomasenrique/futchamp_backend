package futchamp.controllers;

import futchamp.entities.League;
import futchamp.models.LeagueModel;
import futchamp.services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_LEAGUE;

@RestController
@RequestMapping(LEAGUE)
public class LeagueController {

    @Autowired
    @Qualifier(SER_LEAGUE)
    private LeagueService leagueService;


    @PostMapping(SLASH)
    public ResponseEntity<League> addLeague(@RequestBody League league) {
        return leagueService.addElementListG(league);
    }

    @GetMapping(SLASH + LIST)
    public ResponseEntity<List<LeagueModel>> getAllLeagues() {
        return leagueService.getAllElementListG();
    }

    @GetMapping(SLASH + REGISTER)
    public ResponseEntity<LeagueModel> getLeagueByName(@RequestParam String nameLeague) {
        return leagueService.getLeagueByNameSI(nameLeague);
    }

    @PutMapping(SLASH)
    public ResponseEntity<League> updateLeague(@RequestBody League league) {
        return leagueService.updateElementListG(league);
    }


    @DeleteMapping(SLASH + ID_LEAGUE)
    public ResponseEntity<?> deleteLeague(@PathVariable Long idLeague) {
        return leagueService.deleteElementListG(idLeague);
    }


}
