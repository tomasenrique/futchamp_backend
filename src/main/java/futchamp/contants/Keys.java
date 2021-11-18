package futchamp.contants;

/**
 * Esta interface almacenara las claves necesarias para las relaciones entre las entidades
 */
public interface Keys {

    /**
     * Claves para las relaciones entre las entidades (mappedBy)
     */
    String MAPPEDBY_LEAGUE = "league";
    String MAPPEDBY_TEAM = "team";
    String MAPPEDBY_TEAM_LOCAL = "local";
    String MAPPEDBY_TEAM_VISITOR = "visitor";
    String MAPPEDBY_CHAMPIONSHIP = "championship";


    /**
     * Nombres de campos creados por las relaciones entre las entidades
     */
    String ID_LEAGUE = "id_league";
    String ID_TEAM = "id_team";
    String ID_TEAM_LOCAL= "id_team_local";
    String ID_TEAM_VISITOR= "id_team_visitor";
    String ID_CHAMPIONSHIP= "id_championship";

    /**
     * Claves foraneas para las relaciones entre las entidades
     */

    String FK_LEAGUE_TEAM = "fk_league_team";
    String FK_TEAM_PLAYER = "fk_team_player";
    String FK_TEAM_MATCH_LOCAL = "fk_team_match_local";
    String FK_TEAM_MATCH_VISITOR = "fk_team_match_visitor";
    String FK_CHAMPIONSHIP_MATCH = "fk_championship_match";


}
