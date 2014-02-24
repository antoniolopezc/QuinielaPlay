/**
 * 
 */

package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.*;


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
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<ResultadoPronostico> Resultados;
	
	
	public static Finder<Long,DetallePronostico> find = new Finder<Long,DetallePronostico>(
			    Long.class, DetallePronostico.class
			  );
}
