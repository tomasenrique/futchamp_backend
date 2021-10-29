package futchamp.contants;

/**
 * Esta Interface Almacenara las links o rutas para acceder a la api y esta relacionada directamente con las clases
 * controller de cada entidad
 */

public interface Links {

    String VERSION = "/api/V2"; // Version api
    String SLASH = "/";
    String NAME_API = "/futchamp"; // nombre de empresa o proyecto

    /**
     * Tipos de devoluciones
     */
    String LIST = "list";  // lista
    String REGISTER = "register";  // registro
    String DELETE = "eliminar"; // eliminar


    /**
     * Enlaces para las rutas de acceso para el controlador de COORDINATOR
     */
    String COORDINATOR = NAME_API + VERSION + SLASH + "coordinator";
    String ID_COORDINATOR = "{idCoordinator}";
    String VERIFICATION_COORDINATOR = "verificarAutorizacion";

    /**
     * Enlaces para las rutas de acceso para el controlador de LEAGUE
     */
    String LEAGUE = NAME_API + VERSION + SLASH + "league";
    String ID_LEAGUE = "{idLeague}";

}
