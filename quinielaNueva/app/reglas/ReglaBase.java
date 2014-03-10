/**
 * 
 */
package reglas;


import models.*;

/**
 * @author alopez1
 *
 */
public abstract class ReglaBase {
	
	
	/*
	 * Crea la regla por primera vez basado en los parametros que se les pase que se encuentran en formato YAML.
	 */
	public ReglaBase(String Parametro) {
	}

	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular( Pronostico Pronostico);
	

	/*Genera los resultados necesario para su futuro Caculo para un Pronostico 
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long Generar( Pronostico Pronostico);
	
	/*Genera los resultados necesario para su futuro Caculo para un Pronostico 
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract String IncluirJS();
	

}
