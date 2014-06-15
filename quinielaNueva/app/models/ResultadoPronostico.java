/**
 * 
 */
package models;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * @author alopez1
 * No tiene el resultado por que lo hay de varios tipos revisar 
 *
 */
@Entity
public class ResultadoPronostico extends Model {

	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	@ManyToOne
	public Resultado Resultado;
	
	
	public Long Entero;
	
	@ManyToOne
	public Equipo Equipo;
	
	@ManyToOne
	public Pronostico Pronostico;
	
	public static Finder<Long,ResultadoPronostico> find = new Finder<Long,ResultadoPronostico>(
		    Long.class, ResultadoPronostico.class
		  );

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Resultado getResultado() {
		return Resultado;
	}

	public void setResultado(Resultado resultado) {
		Resultado = resultado;
	}

	public Long getEntero() {
		return Entero;
	}

	public void setEntero(Long entero) {
		Entero = entero;
	}

	public Equipo getEquipo() {
		return Equipo;
	}

	public void setEquipo(Equipo equipo) {
		Equipo = equipo;
	}

	public Pronostico getPronostico() {
		return Pronostico;
	}

	public void setPronostico(Pronostico pronostico) {
		Pronostico = pronostico;
	}

}
