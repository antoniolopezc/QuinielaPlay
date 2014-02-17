/**
 * 
 */

package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;


/**
 * @author alopez1
 *
 */
@Entity
public class DetallePronostico extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long id;
	
	@Constraints.Required
	public String Nombre;

	@ManyToOne
	public Usuario Dueño;
	
	@ManyToOne
	public Quiniela Quiniela;
	
	
	public static Finder<Long,DetallePronostico> find = new Finder<Long,DetallePronostico>(
			    Long.class, DetallePronostico.class
			  );
}
