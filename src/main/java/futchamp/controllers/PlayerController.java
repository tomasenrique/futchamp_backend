package futchamp.controllers;

import futchamp.entities.Player;
import futchamp.models.PlayerModel;
import futchamp.services.PlayerService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static futchamp.contants.Links.*;
import static futchamp.contants.Qualifiers.SER_PLAYER;

@RestController
@RequestMapping(PLAYER)
public class PlayerController {

    @Autowired
    @Qualifier(SER_PLAYER)
    private PlayerService playerService;


    @PostMapping(SLASH)
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        return playerService.addElementListG(player);
    }

    @GetMapping(SLASH + LIST)
    public ResponseEntity<List<PlayerModel>> getAllPlayers() {
        return playerService.getAllElementListG();
    }

    @GetMapping(SLASH + LIST + SLASH + NAME_TEAM)
    public ResponseEntity<List<PlayerModel>> getAllPlayerByNameTeam(@PathVariable String nameTeam) {
        return playerService.getAllPlayersByNameTeamSI(nameTeam);
    }


}
