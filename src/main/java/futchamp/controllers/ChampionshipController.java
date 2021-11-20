package futchamp.controllers;

import futchamp.entities.Championship;
import futchamp.models.ChampionshipModel;
import futchamp.services.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_CHAMPIONSHIP;

@RestController
@RequestMapping(CHAMPIONSHIP)
public class ChampionshipController {

    @Autowired
    @Qualifier(SER_CHAMPIONSHIP)
    private ChampionshipService championshipService;


    @PostMapping(SLASH)
    public ResponseEntity<Championship> addChampionship(@RequestBody Championship championship) {
        return championshipService.addElementListG(championship);
    }

    @GetMapping(SLASH+ LIST)
    public ResponseEntity<List<ChampionshipModel>> getAllChampionships() {
        return championshipService.getAllElementListG();
    }


}
