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
	
	public class Parametros {
		/* 
		 * deben colocarse tood los parametros que se requieran 
		 */
	}
	private Parametros Parametros= new Parametros();
	
	/*
	 * Crea la regla por primera vez basado en los parametros que se les pase que se encuentran en formato JSON.
	 */
	final void Reglabase(String Parametro) {
//		Gson g= new Gson();
//		this.Parametros = g.fromJson(Parametro, Parametros.class);
	}
	
	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular( Porcion Porcion );
	
	/*Genera los resultados necesario para su futuro Caculo
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long Generar( Porcion Porcion );

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
