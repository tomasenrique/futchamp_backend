package futchamp.serviceSI;

import org.springframework.http.ResponseEntity;

import futchamp.models.CoordinatorModel;

/**
 * Esta interface sera para declarar los metodos a implementar de la entidad Coordinator y que no sean basicos como los
 * de 'GService' y que dependera de cada entidad y sus atributos. Seran para la clase service y usarlos en el controlador.
 */

public interface CoordinatorSI {

    /**
     * Este metodo se encarga de verificar si existe un usuario y si este tiene autorizacion devolviendo un Boleano
     *
     * @param user     Sera le nombre unico de un usuario.
     * @param password Sera su contraseña.
     * @return Dato de tipo boleano, True si existe y False si no.
     */
    ResponseEntity<Boolean> verificarAutorizacionSI(String user, String password);


    /**
     * Busca un coordinador por medio de su nombre de usuario
     * 
     * @param user Sera el nombre de usuario que es un datos de tupo unico.
     * @return Sera un objeto de tipo CoordinatorModel con los datos de un coordinador.
     */
    ResponseEntity<CoordinatorModel> getCoordinatorByUserOrEmailSI(String user, String email);
}
