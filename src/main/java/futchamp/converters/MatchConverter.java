package futchamp.converters;

import futchamp.entities.Match;
import futchamp.generics.GConverter;
import futchamp.models.MatchModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static futchamp.contants.Converters.CON_MATCH;
import static futchamp.contants.Texts.LOADING_LIST;
import static futchamp.contants.Texts.LOADING_REGISTER;

@Component(CON_MATCH)
public class MatchConverter implements GConverter<MatchModel, Match> {

    private static final Logger logMatchConverter = LoggerFactory.getLogger(MatchConverter.class);

    @Override
    public List<MatchModel> converterListG(List<Match> elementList) {
        List<MatchModel> matchModels = new ArrayList<>();
        for (Match m : elementList) {
            matchModels.add(new MatchModel(m));
        }
        logMatchConverter.info(LOADING_LIST + "Partidos");
        return matchModels;
    }

    @Override
    public MatchModel converterElementG(Match element) {
        logMatchConverter.info(LOADING_REGISTER + "Partido");
        return new MatchModel(element);
    }
}
