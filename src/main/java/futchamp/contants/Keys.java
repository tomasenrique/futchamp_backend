package futchamp.contants;

/**
 * Esta interface almacenara las claves necesarias para las relaciones entre las entidades
 */
public interface Keys {

    /**
     * Claves para las relaciones entre las entidades (mappedBy)
     */
    String MAPPEDBY_LEAGUE =  "league";
    String MAPPEDBY_TEAM =  "team";


    /**
     * Nombres de campos creados por las relaciones entre las entidades
     */
    String ID_LEAGUE = "id_league";
    String ID_TEAM = "id_team";

    /**
     * Claves foraneas para las relaciones entre las entidades
     */

    String FK_LEAGUE_TEAM = "fk_league_team";
    String FK_TEAM_PLAYER = "fk_team_player";


}
