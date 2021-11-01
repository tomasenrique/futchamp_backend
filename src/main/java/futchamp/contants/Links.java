package futchamp.contants;

import sun.security.timestamp.TSRequest;

/**
 * Esta Interface Almacenara las links o rutas para acceder a la api y esta relacionada directamente con las clases
 * controller de cada entidad
 */

public interface Links {

    String NAME_API = "/futchamp"; // nombre de empresa o proyecto
    String VERSION = "/api/V2"; // Version api
    String SLASH = "/";
    String ROUTE = NAME_API + VERSION + SLASH;

    /**
     * Tipos de devoluciones
     */
    String LIST = "list";  // lista
    String REGISTER = "register";  // registro
    String DELETE = "eliminar"; // eliminar


    /**
     * Enlaces para las rutas de acceso para el controlador de COORDINATOR
     */
    String COORDINATOR = ROUTE + "coordinator";
    String ID_COORDINATOR = "{idCoordinator}";
    String VERIFICATION_COORDINATOR = "verificarAutorizacion";

    /**
     * Enlaces para las rutas de acceso para el controlador de LEAGUE
     */
    String LEAGUE = ROUTE + "league";
    String ID_LEAGUE = "{idLeague}";
    String NAME_LEAGUE = "{nameLeague}";

    /**
     * Enlaces para las rutas de acceso para el controlador de TEAM
     */

    String TEAM = ROUTE + "team";
    String ID_TEAM = "{idTeam}";
    String NAME_TEAM = "{nameTeam}";

}
