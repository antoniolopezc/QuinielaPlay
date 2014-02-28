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
public class Partido extends Model {
	/**
	 *  Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long Id;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	public String Descricion;
	
	public String Lugar;
	
	public java.sql.Timestamp Fecha;
	
	public enum Tiempo {
		NoIniciado,
		PrimerTiempo,
		SegundoTiempo,
		Prologa,
		Penatil,
		Final,
	}
	public Tiempo TiempoActual = Tiempo.NoIniciado;
	
	@ManyToOne
	public Equipo EquipoA;
	
	@ManyToOne
	public Equipo EquipoB;
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Resultado> Resultados=new ArrayList<Resultado>();

}
