package futchamp.converters;

import futchamp.entities.Team;
import futchamp.generics.GConverter;
import futchamp.models.TeamModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static futchamp.contants.Converters.CON_TEAM;

@Component(CON_TEAM)
public class TeamConverter implements GConverter<TeamModel, Team> {

    private static final Logger logTeamConverter = LoggerFactory.getLogger(TeamConverter.class);


    @Override
    public List<TeamModel> converterListG(List<Team> elementList) {
        List<TeamModel> teamModelList = new ArrayList<>();
        for (Team t : elementList) {
            teamModelList.add(new TeamModel(t));
        }
        logTeamConverter.info("Cargando lista de teams.");
        return teamModelList;
    }

    @Override
    public TeamModel converterElementG(Team elemento) {
        logTeamConverter.info("Cargando Team.");
        return new TeamModel(elemento);
    }
}
