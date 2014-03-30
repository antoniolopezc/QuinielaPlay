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
	
	public Long Maximo;
	
	TipoEstado Estado=TipoEstado.Nuevo;
	
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
			models.Partido partido, models.Porcion porcion, Long maximo) {
		super();
		ReferenciaRegla = referenciaRegla;
		Resultado = resultado;
		Partido = partido;
		Porcion = porcion;
		Maximo=maximo;
		Valor = new Long(0);
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

	/**
	 * @return the maximo
	 */
	public Long getMaximo() {
		return Maximo;
	}

	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(Long maximo) {
		Maximo = maximo;
	}

	/**
	 * @return the referenciaRegla
	 */
	public String getReferenciaRegla() {
		return ReferenciaRegla;
	}

	/**
	 * @param referenciaRegla the referenciaRegla to set
	 */
	public void setReferenciaRegla(String referenciaRegla) {
		ReferenciaRegla = referenciaRegla;
	}

	/**
	 * @return the estado
	 */
	public TipoEstado getEstado() {
		return Estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(TipoEstado estado) {
		Estado = estado;
	}

}