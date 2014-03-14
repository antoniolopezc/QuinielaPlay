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
	
	/*
	 * La regla que crea el registro de puntos puede usar este valor como referencia para
	 * simplificar el futuro calculo.
	 */
	public String ReferenciaRegla;
	
	@ManyToOne
	public Resultado Resultado;
	
	@ManyToOne
	public Partido Partido;
	
	@ManyToOne
	public Porcion Porcion;

	public static Finder<Long,Punto> find = new Finder<Long,Punto>(
		    Long.class, Punto.class
		  );

	/**
	 * @param referenciaRegla
	 * @param resultado
	 * @param partido
	 * @param porcion
	 */
	public Punto(String referenciaRegla, models.Resultado resultado,
			models.Partido partido, models.Porcion porcion) {
		super();
		ReferenciaRegla = referenciaRegla;
		Resultado = resultado;
		Partido = partido;
		Porcion = porcion;
	}

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