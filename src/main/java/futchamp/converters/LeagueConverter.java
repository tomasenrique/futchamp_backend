package futchamp.converters;

import futchamp.entities.League;
import futchamp.generics.GConverter;
import futchamp.models.LeagueModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


import static futchamp.contants.Converters.CON_LEAGUE;

@Component(CON_LEAGUE)
public class LeagueConverter implements GConverter<LeagueModel, League> {

    private static final Logger logLeagueConverter = LoggerFactory.getLogger(LeagueConverter.class);

    @Override
    public List<LeagueModel> converterListG(List<League> elementList) {
        List<LeagueModel> leagueModelList = new ArrayList<>();
        for (League l : elementList) {
            leagueModelList.add(new LeagueModel(l));
        }
        logLeagueConverter.info("Cargando lista de leagues.");
        return leagueModelList;
    }

    @Override
    public LeagueModel converterElementG(League elemento) {
        logLeagueConverter.info("Cargando League.");
        return new LeagueModel(elemento);
    }
}
