/**
 * 
 */
package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.*;
//import play.db.ebean.Model.Finder;

/**
 * @author alopez1
 * Contiene los equipos que participan en un torneo
 */
@Entity
public class Usuario extends Model {
	/**
	 * Agregado por Eclipse no estoy seguro si sirve de algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long Id;
	
	@Constraints.Required
	@Column(unique=true)
	public String email;
	
	public String nombre;
	
	public String Avatar;
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(
		    Long.class, Usuario.class
		  );
	
}
