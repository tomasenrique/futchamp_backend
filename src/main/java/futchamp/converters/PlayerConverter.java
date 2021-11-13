package futchamp.converters;

import futchamp.entities.Player;
import futchamp.generics.GConverter;
import futchamp.models.LeagueModel;
import futchamp.models.PlayerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static futchamp.contants.Converters.CON_PLAYER;
import static futchamp.contants.Texts.LOADING_LIST;
import static futchamp.contants.Texts.LOADING_REGISTER;

@Component(CON_PLAYER)
public class PlayerConverter implements GConverter<PlayerModel, Player> {

    private static final Logger logPlayerConverter = LoggerFactory.getLogger(PlayerConverter.class);

    @Override
    public List<PlayerModel> converterListG(List<Player> elementList) {
        List<PlayerModel> playerModelList = new ArrayList<>();
        for (Player p : elementList) {
            playerModelList.add(new PlayerModel(p));
        }
        logPlayerConverter.info(LOADING_LIST + "jugadores");
        return playerModelList;
    }

    @Override
    public PlayerModel converterElementG(Player elemento) {
        logPlayerConverter.info(LOADING_REGISTER + "jugador");
        return new PlayerModel(elemento);
    }
}
