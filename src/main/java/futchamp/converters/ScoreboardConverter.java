package futchamp.converters;

import futchamp.entities.Scoreboard;
import futchamp.generics.GConverter;
import futchamp.models.ScoreboardModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static futchamp.contants.Converters.CON_SCOREBOARD;
import static futchamp.contants.Texts.LOADING_LIST;
import static futchamp.contants.Texts.LOADING_REGISTER;

@Component(CON_SCOREBOARD)
public class ScoreboardConverter implements GConverter<ScoreboardModel, Scoreboard> {

    private static final Logger logScoreboardConverter = LoggerFactory.getLogger(ScoreboardConverter.class);

    @Override
    public List<ScoreboardModel> converterListG(List<Scoreboard> elementList) {
        List<ScoreboardModel> scoreboardModels = new ArrayList<>();
        for (Scoreboard s : elementList) {
            scoreboardModels.add(new ScoreboardModel(s));
        }
        logScoreboardConverter.info(LOADING_LIST + "Scoreboard");
        return scoreboardModels;
    }

    @Override
    public ScoreboardModel converterElementG(Scoreboard element) {
        logScoreboardConverter.info(LOADING_REGISTER + "Scoreboard");
        return new ScoreboardModel(element);
    }
}
