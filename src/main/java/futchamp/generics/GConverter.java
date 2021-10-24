package futchamp.generics;


import java.util.List;

/**
 * Esta interface generica servira para implememtar lo metodos que pasaran de clases de tipo entidad a modelo.
 * Estos metodos se usaran en la clases services
 *
 * @param <M> Class Model
 * @param <E> Class Entity
 */

public interface GConverter<M, E> {

    /**
     * Este metodo generico servira para convertir una lista de elementos de tipo entidad a modelo para poder trabajar
     * con clases services y controllers
     *
     * @param elementList Sera la lista de elementos de tipo entidad a convertir a modelo.
     * @return una lista de entidades de tipo modelo.
     */
    List<M> converterListG(List<E> elementList);

    /**
     * Este metodo generico sirve para convertir un elemento de tipo entidad a modelo para poder trabajar con las
     * clases services y controllers
     *
     * @param elemento Sera un elemento de tipo entidad a convertir a modelo
     * @return Objeto elemento de tipo modelo
     */
    M converterElementG(E elemento);
}
