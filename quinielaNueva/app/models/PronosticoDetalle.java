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
public class PronosticoDetalle extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long id;
	

	@ManyToOne
	public Pronostico Pronostico;
	
	@ManyToOne
	public Partido Partido;
	
	@ManyToOne
	public Porcion Porcion;
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Resultado> Resultados;
	
	
	public static Finder<Long,PronosticoDetalle> find = new Finder<Long,PronosticoDetalle>(
			    Long.class, PronosticoDetalle.class
			  );
}
