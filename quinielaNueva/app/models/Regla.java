/**
 * 
 */
package models;

import javax.persistence.*;

import play.db.ebean.*;




/**
 * @author alopez1
 *
 */
@Entity
public class Regla extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	public String Descricion;
	
	/*Debe ser una clase que hereda de ReglaBase*/
	public String Clase;
	
	/*Texto en formato JSON*/
	public String Parametros;

	public static Finder<Long,Regla> find = new Finder<Long,Regla>(
		    Long.class, Regla.class
		  );

}
