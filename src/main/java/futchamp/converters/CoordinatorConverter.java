package futchamp.converters;

import futchamp.entities.Coordinator;
import futchamp.generics.GConverter;
import futchamp.models.CoordinatorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


import static futchamp.contants.Converters.CON_COORDINATOR;
import static futchamp.contants.Texts.LOADING_LIST;
import static futchamp.contants.Texts.LOADING_REGISTER;

@Component(CON_COORDINATOR)
public class CoordinatorConverter implements GConverter<CoordinatorModel, Coordinator> {

    private static final Logger logCoordinatorConverter = LoggerFactory.getLogger(CoordinatorConverter.class);

    @Override
    public List<CoordinatorModel> converterListG(List<Coordinator> elementList) {

        List<CoordinatorModel> coordinatorModelList = new ArrayList<>();
        for (Coordinator c : elementList) {
            coordinatorModelList.add(new CoordinatorModel(c));
        }
        logCoordinatorConverter.info(LOADING_LIST + "coordinadores");
        return coordinatorModelList;
    }

    @Override
    public CoordinatorModel converterElementG(Coordinator elemento) {
        logCoordinatorConverter.info(LOADING_REGISTER + "coordinador");
        return new CoordinatorModel(elemento);
    }


}
