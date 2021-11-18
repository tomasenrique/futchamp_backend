package futchamp.converters;

import futchamp.entities.Championship;
import futchamp.generics.GConverter;
import futchamp.models.ChampionshipModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static futchamp.contants.Converters.CON_CHAMPIONSHIP;
import static futchamp.contants.Texts.LOADING_LIST;
import static futchamp.contants.Texts.LOADING_REGISTER;

@Component(CON_CHAMPIONSHIP)
public class ChampionshipConverter implements GConverter<ChampionshipModel, Championship> {

    private static final Logger logChampionshipConverter = LoggerFactory.getLogger(ChampionshipConverter.class);

    @Override
    public List<ChampionshipModel> converterListG(List<Championship> elementList) {
        List<ChampionshipModel> championshipModelList = new ArrayList<>();
        for (Championship c : elementList) {
            championshipModelList.add(new ChampionshipModel(c));
        }
        logChampionshipConverter.info(LOADING_LIST + "campeonatos");
        return championshipModelList;
    }

    @Override
    public ChampionshipModel converterElementG(Championship elemento) {
        logChampionshipConverter.info(LOADING_REGISTER + "campeonato");
        return new ChampionshipModel(elemento);
    }
}
