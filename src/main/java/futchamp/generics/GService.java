package futchamp.generics;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Esta interface generica servira para declarar los metodos basicos en comun de todas las entidades para poder
 * realizar CRUD a la BBDD.
 * <p>
 * Se usaran dentro del los metodos que estan en los controladores
 *
 * @param <M> Class Model
 * @param <E> Class Entity
 */
public interface GService<M, E> {
    /**
     * Este metodo agrega un elemento o registro a la lista(BBDD)
     *
     * @param element Sera el objeto de tipo entidad
     * @return Sera el objeto a guardar
     */
    ResponseEntity<E> addElementListG(E element);

    /**
     * Este metodo mostrara todos los elementos o registros disponibles en la base de datos
     *
     * @return Una lista de elementos de tipo modelo
     */
    // List<M> getAllElementListG();
    ResponseEntity<List<M>> getAllElementListG();

    /**
     * Este metodo actualizara un registro del tipo entidad de la base de datos
     *
     * @param element Sera el objeto de tipo entidad a actualizar
     * @return sera el objeto de tipo entidad actualizado.
     */
    ResponseEntity<E> updateElementListG(E element);

    /**
     * Este metodo eliminara un elemento o registro de la base de datos
     *
     * @param idElement sera el numero identificador de tipo Long para realizar la busqueda y eliminar de la BBDD
     * @return sera el elemento o registro eliminado
     */
    ResponseEntity<?> deleteElementListG(Long idElement);
}
