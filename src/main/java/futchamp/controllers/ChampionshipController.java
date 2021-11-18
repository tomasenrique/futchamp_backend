package futchamp.controllers;

import futchamp.entities.Championship;
import futchamp.services.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static futchamp.contants.Links.CHAMPIONSHIP;
import static futchamp.contants.Links.SLASH;
import static futchamp.contants.Qualifiers.SER_CHAMPIONSHIP;

@RestController
@RequestMapping(CHAMPIONSHIP)
public class ChampionshipController {

    @Autowired
    @Qualifier(SER_CHAMPIONSHIP)
    private ChampionshipService championshipService;


    @PatchMapping(SLASH)
    public ResponseEntity<Championship> addChampionship(@RequestBody Championship championship) {
        return championshipService.addElementListG(championship);
    }


}
