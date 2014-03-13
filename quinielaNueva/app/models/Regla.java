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
	
	/*Texto en formato YAML*/
	@Lob
	public String Parametros;

	public static Finder<Long,Regla> find = new Finder<Long,Regla>(
		    Long.class, Regla.class
		  );

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombreCorto() {
		return NombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		NombreCorto = nombreCorto;
	}

	public String getAbreviatura() {
		return Abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		Abreviatura = abreviatura;
	}

	public String getDescricion() {
		return Descricion;
	}

	public void setDescricion(String descricion) {
		Descricion = descricion;
	}

	public String getClase() {
		return Clase;
	}

	public void setClase(String clase) {
		Clase = clase;
	}

	public String getParametros() {
		return Parametros;
	}

	public void setParametros(String parametros) {
		Parametros = parametros;
	}

}
