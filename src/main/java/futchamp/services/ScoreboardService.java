package futchamp.services;

import futchamp.entities.Scoreboard;
import futchamp.generics.GService;
import futchamp.models.ScoreboardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static futchamp.contants.Qualifiers.SER_SCOREBOARD;

@Service(SER_SCOREBOARD)
public class ScoreboardService implements GService<ScoreboardModel, Scoreboard> {

    private static final Logger logScoreboardService = LoggerFactory.getLogger(ScoreboardService.class);


    @Override
    public ResponseEntity<Scoreboard> addElementListG(Scoreboard element) {
        return null;
    }

    @Override
    public ResponseEntity<List<ScoreboardModel>> getAllElementListG() {
        return null;
    }

    @Override
    public ResponseEntity<Scoreboard> updateElementListG(Scoreboard element) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }
}
