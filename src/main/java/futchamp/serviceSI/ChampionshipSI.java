package futchamp.serviceSI;

import futchamp.entities.Championship;
import futchamp.entities.Match;
import futchamp.entities.Team;
import futchamp.models.ChampionshipModel;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Esta interface sera para declarar los metodos a implementar de la entidad Coordinator y que no sean basicos como los
 * de 'GService' y que dependera de cada entidad y sus atributos. Seran para la clase service y usarlos en el controlador.
 */
public interface ChampionshipSI {

    /**
     * Genera una lista de partidos con sus respectivos encuentros entre ellos.
     *
     * @param teamList     Sera una lista de equipos de una misma league.
     * @param championship Sera el objeto campeonato con los datos de la league, fecha y hora de inicio de este.
     * @return Sera una lista de partidos generados para los encuentros entre estos.
     */
    List<Match> generateChampionshipsSI(List<Team> teamList, Championship championship);

    /**
     * Obtiene un registro de un campeonato por medio de su nombre de league y fecha de inicio
     *
     * @param nameLeague            Sera el nombre de la league que crea el campeonato
     * @param dateStartShampionship Sera la fecha de inicio del campeonato
     * @return Sera el Objeto de tipo modelo ChampionshipModel con los datos del campeonato
     */
    ResponseEntity<ChampionshipModel> getChampioshipByNameleagueAndDateSI(String nameLeague, LocalDate dateStartShampionship);


    /**
     * Genera y ordena los encuentros de fulbol
     * Se rellenan las matrices auxiliares
     * <p>
     * Matriz 1    	 Matriz 2
     * 1   2   3		6   5   4
     * 4   5   1		6   3   2
     * 2   3   4		6   1   5
     * 5   1   2		6   4   3
     * 3   4   5		6   2   1
     *
     * @param teamList    Sera una lista de equipos de una misma league.
     * @param matrix1     Sera la matriz auxiliar 1 de encuentros de futbol
     * @param matrix2     Sera la matriz auxiliar 2 de encuentros de futbol
     * @param going       Seran los encuentros de futbol de ida
     * @param returning   Sera  los encuentros de futbol de vuelta
     * @param numberTeams Sera la cantidad de equipos disponibles
     */
    void generatingAndOrderingMatchesSI(List<Team> teamList, int[][] matrix1, int[][] matrix2, Match[][] going, Match[][] returning, int numberTeams);

    /**
     * Aqui se agrega la jornada a la que pertenece cada par de partidos generados
     *
     * @param going       Seran los encuentros de futbol de ida
     * @param returning   Sera  los encuentros de futbol de vuelta
     * @param numberTeams Sera la cantidad de equipos disponibles
     */
    void addJourneyMatchesSI(Match[][] going, Match[][] returning, int numberTeams);

    /**
     * Este metodo servira para asignar hora a cada partido
     *
     * @param time        Sera la hora para generar el partido
     * @param matches     Sera el array de partidos disponibles para asignarle hora
     * @param numberTeams Sera la cantidad de equipos disponibles
     */
    void addtimeMatchesSI(LocalTime time, Match[][] matches, int numberTeams);

    /**
     * Este metodo servira para añadir la fecha a cada partido.
     *
     * @param date        Sera la fecha para generar el partido.
     * @param matches     Sera el array de partidos disponibles para asignarle fecha
     * @param numberTeams Sera la cantidad de equipos disponibles
     */
    void addDateMatchesSI(LocalDate date, Match[][] matches, int numberTeams);

    /**
     * Genera una fecha de encuentro teniendo en cuenta la del ultimo partido generado, generando la fecha para
     * el siguiente partido
     *
     * @param date        Sera la ultima fecha asignada al ultimo partido creado.
     * @param numberTeams Sera la cantidad de equipos disponibles
     * @return Sera una fecha distinta a la ultima asignada de un encuentro de futbol.
     */
    LocalDate getLastDateMatchSI(LocalDate date, int numberTeams);

    /**
     * Agrega el identificador de campeonato(championship) al partido, de esta forma se podra saber numero de
     * campeonato que genero los partidos
     *
     * @param matches      Sera el array de partidos disponibles para asignarle el id de calendario que los genero
     * @param championship Sera el objeto Calendarios para poder asignar el id de este
     * @param numberTeams  Sera la cantidad de equipos disponibles
     */
    void addIdChampionshipMatchesSI(Match[][] matches, Championship championship, int numberTeams);

    /**
     * Este metodo agregara un encuentro de equipos generado aleatoriamente a la lista de partidos
     *
     * @param soccerMatch Sera el array bidimensional con el par de equipos que protagonizaran el partido(encuentro)
     * @param matches     Sera el array de partidos disponibles para generar los encuentros.
     * @param numberTeams Sera la cantidad de equipos disponibles
     */
    void addMatchListMatchesSI(Match[][] soccerMatch, List<Match> matches, int numberTeams);

}
