/**
 * 
 */
package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.libs.*;

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
	public long id;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	public String Descricion;
	
	public String Lugar;
	
	public Date Fecha;
	
	public Time Hora;
	
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
	public Torneo Torneo;
	
	
	@OneToOne(cascade={CascadeType.ALL})
	public Resultado ResultadoEquipoA;
	
	@ManyToOne
	public Equipo EquipoA;
	
	@OneToOne(cascade={CascadeType.ALL})
	public Resultado ResultadoEquipoB;
	
	@ManyToOne
	public Equipo EquipoB;

	@OneToMany(cascade={CascadeType.ALL}) 
	public List<Resultado> Resultados;
	

}
