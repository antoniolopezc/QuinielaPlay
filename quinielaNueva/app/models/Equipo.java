/**
 * 
 */
package models;

import javax.persistence.*;

import play.db.ebean.*;


/**
 * @author alopez1
 * Contiene los equipos que participan en un torneo
 */
@Entity
public class Equipo extends Model {
	/**
	 * Agregado por Eclipse no estoy seguro si sirve de algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	@Lob
	public byte[] Escudo;
	
	@Lob
	public byte[] Bandera;
	
	@ManyToOne
	public Equipo Final;
	
	public static Finder<Long,Equipo> find = new Finder<Long,Equipo>(
		    Long.class, Equipo.class
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

	public byte[] getEscudo() {
		return Escudo;
	}

	public void setEscudo(byte[] escudo) {
		Escudo = escudo;
	}

	public byte[] getBandera() {
		return Bandera;
	}

	public void setBandera(byte[] bandera) {
		Bandera = bandera;
	}

	public Equipo getFinal() {
		return Final;
	}

	public void setFinal(Equipo final1) {
		Final = final1;
	}

	
}
