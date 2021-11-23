package futchamp.controllers;

import futchamp.entities.Match;
import futchamp.models.MatchModel;
import futchamp.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_MATCH;

@RestController
@RequestMapping(MATCH)
public class MatchController {

    @Autowired
    @Qualifier(SER_MATCH)
    private MatchService matchService;


    @PostMapping(SLASH)
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        return matchService.addElementListG(match);
    }

    @GetMapping(SLASH + LIST)
    public ResponseEntity<List<MatchModel>> getAllMatches() {
        return matchService.getAllElementListG();
    }

}
