package futchamp.services;

import futchamp.DAO.PlayerDAO;
import futchamp.DAO.TeamDAO;
import futchamp.converters.PlayerConverter;
import futchamp.entities.Player;
import futchamp.entities.Team;
import futchamp.generics.GService;
import futchamp.models.PlayerModel;
import futchamp.serviceSI.PlayerSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static futchamp.contants.Converters.CON_PLAYER;
import static futchamp.contants.Qualifiers.SER_PLAYER;
import static futchamp.contants.Repositories.DAO_PLAYER;
import static futchamp.contants.Repositories.DAO_TEAM;

@Service(SER_PLAYER)
public class PlayerService implements GService<PlayerModel, Player>, PlayerSI {

    private static final Logger logPlayerService = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    @Qualifier(DAO_PLAYER)
    private PlayerDAO playerDAO; // Para realizar CRUD a la base de datos de PLAYER

    @Autowired
    @Qualifier(DAO_TEAM)
    private TeamDAO teamDAO; // Para realizar CRUD a la base de datos de TEAM

    @Autowired
    @Qualifier(CON_PLAYER)
    private PlayerConverter playerConverter; // Clase de tipo componente para convertir de model a entidad.


    // MÉTODOS GENERICOS
    @Override
    public ResponseEntity<Player> addElementListG(Player element) {
        try {
            if (playerDAO.existsPlayerByDni(element.getDni()) || playerDAO.existsPlayerByEmail(element.getEmail())) {
                logPlayerService.info("IF: El jugador a crear ya existe.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IF: El jugador a crear ya existe.");
            } else {
                logPlayerService.info("No existe el jugador a crear.");
                if (teamDAO.existsTeamByName(element.getTeam().getName())) {
                    logPlayerService.info("El equipo del jugador a crear si existe.");
                    Team team = teamDAO.findTeamByName(element.getTeam().getName());
                    element.setTeam(team);
                    playerDAO.save(element);
                    logPlayerService.info("Jugador creado y guardado.");
                    return ResponseEntity.status(HttpStatus.CREATED).body(element);
                } else {
                    logPlayerService.info("ELSE: El equipo del jugador a crear no existe.");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE: El equipo del jugador a crear no existe.");
                }
            }
        } catch (Exception e) {
            logPlayerService.info("CATCH: Error al crear el jugador: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al crear el jugador: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<PlayerModel>> getAllElementListG() {
        try {
            List<PlayerModel> playerModelList = playerConverter.converterListG(playerDAO.findAll());
            if (playerModelList.isEmpty()) {
                logPlayerService.info("Lista de jugadores Vacia.");
            } else {
                logPlayerService.info("Lista de jugadores encontrada.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(playerModelList);
        } catch (Exception e) {
            logPlayerService.info("CATCH: Error al obtener la lisa de jugadores: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al obtener la lisa de jugadores: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Player> updateElementListG(Player element) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }


    // MÉTODOS NO GENERICOS
    @Override
    public ResponseEntity<List<PlayerModel>> getAllPlayersByNameTeamSI(String nameTeam) {
        if (teamDAO.existsTeamByName(nameTeam)) {
            try {
                Team team = teamDAO.findTeamByName(nameTeam);
                List<PlayerModel> playerModelList = playerConverter.converterListG(playerDAO.findPlayerByTeam(team));
                if (playerModelList.isEmpty()) {
                    logPlayerService.info("Lista de jugadores por equipo vacia.");
                } else {
                    logPlayerService.info("Lista de jugadores por equipo encontrada.");
                }
                return ResponseEntity.status(HttpStatus.OK).body(playerModelList);
            } catch (Exception e) {
                logPlayerService.info("CATCH: Error al buscar la lista de jugadores de un mismo equipo: " + e.getMessage());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al buscar la lista de jugadores de un mismo equipo: " + e.getMessage());
            }
        } else {
            logPlayerService.info("ELSE: No existe el equipo buscado.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ELSE: No existe el equipo buscado.");
        }
    }


}
