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
public class Punto extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	public Long Valor;
	
	@ManyToOne
	public Resultado Resultado;
	
	@ManyToOne
	public Partido Partido;
	
	@ManyToOne
	public Porcion Porcion;

	public static Finder<Long,Punto> find = new Finder<Long,Punto>(
		    Long.class, Punto.class
		  );

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getValor() {
		return Valor;
	}

	public void setValor(Long valor) {
		Valor = valor;
	}

	public Resultado getResultado() {
		return Resultado;
	}

	public void setResultado(Resultado resultado) {
		Resultado = resultado;
	}

	public Partido getPartido() {
		return Partido;
	}

	public void setPartido(Partido partido) {
		Partido = partido;
	}

	public Porcion getPorcion() {
		return Porcion;
	}

	public void setPorcion(Porcion porcion) {
		Porcion = porcion;
	}

}
