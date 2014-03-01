/**
 * 
 */
package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

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
	public long Id;
	
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

	
}
