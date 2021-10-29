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
    public List<LeagueModel> getAllLeagues() {
        return leagueService.getAllElementListG();
    }


}
