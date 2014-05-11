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
public class Pronostico extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	@Constraints.Required
	public String Nombre;
	
	public boolean Aprobado=false;

	@ManyToOne
	public Usuario Propietario;
	
	@ManyToOne
	public Quiniela Quiniela;
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<ResultadoPronostico> Resultados= new ArrayList<ResultadoPronostico>();
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Punto> Puntos= new ArrayList<Punto>();
	
	public static Finder<Long,Pronostico> find = new Finder<Long,Pronostico>(
			    Long.class, Pronostico.class
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

	public Usuario getPropietario() {
		return Propietario;
	}

	public void setPropietario(Usuario propietario) {
		Propietario = propietario;
	}

	public Quiniela getQuiniela() {
		return Quiniela;
	}

	public void setQuiniela(Quiniela quiniela) {
		Quiniela = quiniela;
	}

	public List<ResultadoPronostico> getResultados() {
		return Resultados;
	}

	public void setResultados(List<ResultadoPronostico> resultados) {
		Resultados = resultados;
	}

	public List<Punto> getPuntos() {
		return Puntos;
	}

	public void setPuntos(List<Punto> puntos) {
		Puntos = puntos;
	}

	public boolean isAprobado() {
		return Aprobado;
	}

	public void setAprobado(boolean aprobado) {
		Aprobado = aprobado;
	}
}
