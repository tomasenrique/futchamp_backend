package futchamp.services;

import futchamp.DAO.*;
import futchamp.converters.ChampionshipConverter;
import futchamp.entities.*;
import futchamp.generics.GService;
import futchamp.models.ChampionshipModel;
import futchamp.serviceSI.ChampionshipSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static futchamp.contants.Converters.CON_CHAMPIONSHIP;
import static futchamp.contants.Qualifiers.SER_CHAMPIONSHIP;
import static futchamp.contants.Repositories.*;

@Service(SER_CHAMPIONSHIP)
public class ChampionshipService implements GService<ChampionshipModel, Championship>, ChampionshipSI {

    private static final Logger logChampionshipService = LoggerFactory.getLogger(ChampionshipService.class);

    @Autowired
    @Qualifier(DAO_CHAMPIONSHIP)
    private ChampionshipDAO championshipDAO; // Para realizar CRUD a la base de datos de CHAMPIONSHIP

    @Autowired
    @Qualifier(CON_CHAMPIONSHIP)
    private ChampionshipConverter championshipConverter; // Clase de tipo componente para convertir de model a entidad.

    @Autowired
    @Qualifier(DAO_LEAGUE)
    private LeagueDAO leagueDAO; // Para realizar CRUD a la base de datos de LEAGUE

    @Autowired
    @Qualifier(DAO_TEAM)
    private TeamDAO teamDAO; // Para realizar CRUD a la base de datos de TEAM

    @Autowired
    @Qualifier(DAO_MATCH)
    private MatchDAO matchDAO; // Para realizar CRUD a la base de datos de MATCH(matches en BD)

    @Autowired
    @Qualifier(DAO_SCOREBOARD)
    private ScoreboardDAO scoreboardDAO; // Para realizar CRUD a la base de datos de SCOREBOARD


    // MÉTODOS GENERICOS
    @Override
    public ResponseEntity<Championship> addElementListG(Championship element) {
        try {
            if (leagueDAO.existsLeagueByName(element.getNameLeague())) {
                League league = leagueDAO.findLeagueByName(element.getNameLeague());
                List<Team> teamList = teamDAO.findTeamByLeague(league);
                if (!teamList.isEmpty()) {
                    List<Match> matchList = generateChampionshipsSI(teamList, element); // Genera los partidos
                    if (!matchList.isEmpty()) {
                        matchDAO.saveAll(matchList); // Guarda los partidos
                        logChampionshipService.info("Partidos generados y guardados.");
                        startScoreboard(); // Inicializando marcador de partidos generados
                        championshipDAO.save(element); // Guarda el campeonato
                        logChampionshipService.info("Campeonado guardado.");
                    } else {
                        logChampionshipService.info("Lista de partidos vacia, campeonato no guardado.");
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(element);
                } else {
                    logChampionshipService.info("ELSE LIST: Lista de equipos vacio.");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista de equipos vacio.");
                }
            } else {
                logChampionshipService.info("ELSE LEAGUE: No existe league.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe league.");
            }
        } catch (Exception e) {
            logChampionshipService.info("CATCH: Error al crear el campeonato: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al crear el campeonato: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<ChampionshipModel>> getAllElementListG() {
        try {
            List<ChampionshipModel> championshipModelList = championshipConverter.converterListG(championshipDAO.findAll());
            if (championshipModelList.isEmpty()) {
                logChampionshipService.info("Lista de campeonatos encontrada.");
            } else {
                logChampionshipService.info("Lista de campeonatos vacia.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(championshipModelList);
        } catch (Exception e) {
            logChampionshipService.info("CATCH: Error al obtener los campeonatos: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al obtener los campeonatos: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Championship> updateElementListG(Championship element) {
        // TODO ==>> OJO, aqui si se cambia un dato tambien habria que actualizar los datos de los partidos generados
        return null;
    }

    @Override
    public ResponseEntity<?> deleteElementListG(Long idElement) {
        return null;
    }


    // MÉTODOS NO GENERICOS
    @Override
    public List<Match> generateChampionshipsSI(List<Team> teamList, Championship championship) {
        List<Match> matchList = new ArrayList<>(); // Para almacenar los partidos generados
        int numberTeams = teamList.size();// Numero de equipos disponibles
        int[][] matrix1, matrix2; // Variables auxiliares para generar los encuentros de futbol
        Match[][] going, returning; // Para almacenar los encuentros de futbol de ida y vuelta entre los equipos

        if (numberTeams % 2 == 0) { // si el numero de equipos no es par no se crea el campeonato
            logChampionshipService.info("Lista de equipos pares, listo para generar campeonatos.");
            Collections.shuffle(teamList); // Se desordena la lista de equipos
            // Se asigna un tamaño a cada matriz auxiliar para poder generar los encuentros de futbol
            matrix1 = new int[numberTeams - 1][numberTeams / 2];
            matrix2 = new int[numberTeams - 1][numberTeams / 2];
            // Se asigna un tamaño a cada matriz de ida y vuelta con los encuentros de futbol a guardar
            going = new Match[numberTeams - 1][numberTeams / 2];
            returning = new Match[numberTeams - 1][numberTeams / 2];
            // Aqui se inicia la generación y ordenado de los encuentros de futbol
            generatingAndOrderingMatchesSI(teamList, matrix1, matrix2, going, returning, numberTeams);
            // Aqui se agrega la jornada a la que pertenece cada par de partidos generados
            addJourneyMatchesSI(going, returning, numberTeams);
            // Se agrega La hora al Partido
            addtimeMatchesSI(championship.getTime(), going, numberTeams);
            addtimeMatchesSI(championship.getTime(), returning, numberTeams);
            // Se agrega la fecha al partido
            addDateMatchesSI(championship.getDate(), going, numberTeams);
            addDateMatchesSI(getLastDateMatchSI(championship.getDate(), numberTeams), returning, numberTeams);
            // Se agrega identificador de campeonato
            addIdChampionshipMatchesSI(going, championship, numberTeams);
            addIdChampionshipMatchesSI(returning, championship, numberTeams);
            // Se agrega a la lista de partidos para luego guardarlo en la BBDD
            addMatchListMatchesSI(going, matchList, numberTeams);
            addMatchListMatchesSI(returning, matchList, numberTeams);
            logChampionshipService.info("Lista de campeonato generado.");
        } else {
            logChampionshipService.info("Lista de equipos impares, no se puede generar campeonato.");
        }
        return matchList;
    }

    @Override
    public void generatingAndOrderingMatchesSI(List<Team> teamList, int[][] matrix1, int[][] matrix2, Match[][] going, Match[][] returning, int numberTeams) {
        logChampionshipService.info("Generando campeonatos");
        int cont = 0, cont2 = numberTeams - 2;
        // Aqui se inicia la generacion automatica de partidos
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                // A la matriz[i][j] se le pasa el valor del cont
                matrix1[i][j] = cont;
                cont++; // Se suma cont +1
                if (cont == (numberTeams - 1)) {
                    cont = 0; // Si el cont es igual a n - 1 cont recibe el valor a 0
                }
                if (j == 0) { // si j es 0 la segunda matriz en la posicion [i][j] recibe el valor de n - 1
                    matrix2[i][j] = numberTeams - 1;
                } else {
                    matrix2[i][j] = cont2; // matriz2 [i][j] recibe el valor de cont2
                    cont2--; // se le quita uno a cont2
                    if (cont2 == -1) { // si cont2 es igual a -1 cont2 recibirá n - 2
                        cont2 = numberTeams - 2;
                    }
                }
                // Aqui se organizan los partidos
                if (j == 0) {
                    // En la matriz de ida se añade los partidos
                    if (i % 2 == 0) { // Si los equipos son pares se agrega los nombres de estos a la tabla Partido y se inicializa el marcador a cero
                        going[i][j] = new Match(teamList.get(matrix2[i][j]), teamList.get(matrix1[i][j])); // ==>> VER COMO AGREGAR EL ID DE PARTIDO AL MARCADOR
                        returning[i][j] = new Match(teamList.get(matrix1[i][j]), teamList.get(matrix2[i][j]));
                    } else {
                        going[i][j] = new Match(teamList.get(matrix1[i][j]), teamList.get(matrix2[i][j]));
                        returning[i][j] = new Match(teamList.get(matrix2[i][j]), teamList.get(matrix1[i][j]));
                    }
                } else {
                    going[i][j] = new Match(teamList.get(matrix1[i][j]), teamList.get(matrix2[i][j]));
                    returning[i][j] = new Match(teamList.get(matrix2[i][j]), teamList.get(matrix1[i][j]));
                }
            }
        }
    }

    @Override
    public void addJourneyMatchesSI(Match[][] going, Match[][] returning, int numberTeams) {
        logChampionshipService.info("Agregando jornada a partidos.");
        int jorn = 1, jorn2 = numberTeams;
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                going[i][j].setJourney(jorn);
                returning[i][j].setJourney(jorn2);
            }
            jorn++;
            jorn2++;
        }
    }

    @Override
    public void addtimeMatchesSI(LocalTime time, Match[][] matches, int numberTeams) {
        logChampionshipService.info("Agregando hora de inicio a partidos.");
        int aux = time.getHour();
        int hour = time.getHour();
        int min = time.getMinute();
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                matches[i][j].setTime(LocalTime.of(hour, min));
                hour += 3;
            }
            hour = aux;
        }
    }

    @Override
    public void addDateMatchesSI(LocalDate date, Match[][] matches, int numberTeams) {
        logChampionshipService.info("Agregando fecha de encuentro de futbol entre equipos.");
        LocalDate aux = date;
        int dia = 0;
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                matches[i][j].setDate(aux);
            }
            dia += 7;
            aux = date.plusDays(dia);
        }
    }

    @Override
    public LocalDate getLastDateMatchSI(LocalDate date, int numberTeams) {
        logChampionshipService.info("Obteniendo ultima fecha de partidos generado para crear el siguente evento de futbol.");
        LocalDate aux = date;
        int dia = 0;
        for (int i = 0; i < numberTeams - 1; i++) {
            dia += 7;
            aux = date.plusDays(dia);
        }
        return aux;
    }

    @Override
    public void addIdChampionshipMatchesSI(Match[][] matches, Championship championship, int numberTeams) {
        logChampionshipService.info("Agregando identificador de campeonato a partidos.");
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                matches[i][j].setChampionship(championship);
            }
        }
    }

    @Override
    public void addMatchListMatchesSI(Match[][] soccerMatch, List<Match> matches, int numberTeams) {
        logChampionshipService.info("Agregando partidos a la lista de partidos.");
        for (int i = 0; i < numberTeams - 1; i++) {
            for (int j = 0; j < numberTeams / 2; j++) {
                matches.add(soccerMatch[i][j]);
            }
        }
    }

    @Override
    public void startScoreboard() {
        try {
            List<Match> matchList = matchDAO.findAll();
            if (!matchList.isEmpty()) {
                List<Scoreboard> scoreboardList = new ArrayList<>();
                for (Match match : matchList) {
                    if (scoreboardDAO.existsScoreboardByMatch(match)) {
                        logChampionshipService.info("Marcador de partido ya iniciado: " + match.getId());
                    } else {
                        logChampionshipService.info("Marcador de partido iniciado: " + match.getId());
                        scoreboardList.add(new Scoreboard(0, 0, match));
                    }
                }
                scoreboardDAO.saveAll(scoreboardList); // Se guarda los marcadores inicializados
                logChampionshipService.info("Inicializado marcadores de partidos.");
            } else {
                logChampionshipService.info("Lista de partidos vacia. No se puede iniciar marcadores.");
            }
        } catch (Exception e) {
            logChampionshipService.info("CATCH: Error al obtener la lista de partidos: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATCH: Error al obtener la lista de partidos: " + e.getMessage());
        }
    }


}
