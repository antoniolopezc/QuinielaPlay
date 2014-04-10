/**
 * 
 */
package models;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * @author alopez1
 * No tiene el resultado por que lo hay de varios tipos revisar 
 *
 */
@Entity
public class DefinicionResultado extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;

	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	public enum Tipos {
		Entero,
		Equipo
	};
	
	public Tipos Tipo;
	
	public static Finder<Long,DefinicionResultado> find = new Finder<Long,DefinicionResultado>(
		    Long.class, DefinicionResultado.class
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

	public Tipos getTipo() {
		return Tipo;
	}

	public void setTipo(Tipos tipo) {
		Tipo = tipo;
	}

}
