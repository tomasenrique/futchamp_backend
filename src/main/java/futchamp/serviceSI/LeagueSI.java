package futchamp.serviceSI;

import futchamp.models.LeagueModel;
import org.springframework.http.ResponseEntity;

/**
 * Esta interface sera para declarar los metodos a implementar de la entidad Coordinator y que no sean basicos como los
 * de 'GService' y que dependera de cada entidad y sus atributos. Seran para la clase service y usarlos en el controlador.
 */
public interface LeagueSI {

    /**
     * Obtiene un registro de una League por medio de su nombre.
     *
     * @param nameLeague Sera el nombre de la liga que es un dato de tipo unico.
     * @return Sera un objeto de tipo LeagueModel
     */
    ResponseEntity<LeagueModel> getLeagueByNameSI(String nameLeague);
}
