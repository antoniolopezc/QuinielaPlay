/**
 * 
 */
package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * @author alopez1
 *
 */
@Entity
public class Porcion extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long id;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	public String Descricion;
	
	public Date Inicio;
	
	public Date Fin;
	
	@ManyToOne
	public Torneo Torneo;
	
	@ManyToMany
	public List<Partido> Partidos;
	
	@ManyToMany
	public List<Regla> Reglas;
    	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Resultado> Resultados;


}
