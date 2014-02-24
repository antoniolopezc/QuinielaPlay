/**
 * 
 */
package reglas;


import play.libs.Yaml;
import models.*;

/**
 * @author alopez1
 *
 */
public abstract class ReglaBase {
	
	public class Parametros {
		/* 
		 * deben colocarse tood los parametros que se requieran 
		 */
	}
	private Parametros Parametros;
	
	/*
	 * Crea la regla por primera vez basado en los parametros que se les pase que se encuentran en formato YAML.
	 */
	final void Reglabase(String Parametro) {
		if(!Parametro.isEmpty())
			this.Parametros=(reglas.ReglaBase.Parametros) Yaml.load(Parametro);
	}
	
	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular( Porcion Porcion );

	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular( Pronostico Pronostico);
	
	/*Agrega los resultados necesario para su futuro Caculo dentro de un grupo
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long Generar( Porcion Porcion );

	/*Genera los resultados necesario para su futuro Caculo para un Pronostico 
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long GenerarPronostico( Quiniela Quiniela, Pronostico Pronostico);

	/**
	 * @return the parametros
	 */
	public Parametros getParametros() {
		return Parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(Parametros parametros) {
		Parametros = parametros;
	}
}
