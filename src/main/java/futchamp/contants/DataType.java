package futchamp.contants;


/**
 * Esta interface servira para contener los tipos de datos que se usaran para los mapeos a las base de datos
 */
public interface DataType {

    String TINYINT = "TINYINT(11) UNSIGNED";  // Tipo de dato Byte sin signo a 255
    String SMALLINT = "SMALLINT(11) UNSIGNED"; // Tipo de dato Short  sin signo a 65536
    String MEDIUMINT = "MEDIUMINT(11) UNSIGNED"; // Tipo de dato entero sin signo a  16777215
}
