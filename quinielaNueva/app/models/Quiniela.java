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
public class Quiniela extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long Id;
	
	@Constraints.Required
	public String Nombre;
	
	@Constraints.Required
	public String NombreCorto;
	
	@Constraints.Required
	public String Abreviatura;
	
	public String Descricion;
	
	@Constraints.Required
	public Date Inicio;
	
	@Constraints.Required
	public Date Fin;
	
	@Lob
	public byte[] Imagen;
	
	@ManyToOne
	public Torneo Torneo;
	
	@ManyToMany 
	public List<Regla> Reglas;	
	
	@ManyToOne
	public Usuario Dueño;
	
	@ManyToMany 
	public List<Usuario> Administradores;
	
	@ManyToMany 
	public List<Usuario> Participantes;
	
	public static Finder<Long,Quiniela> find = new Finder<Long,Quiniela>(
			    Long.class, Quiniela.class
			  );
}
