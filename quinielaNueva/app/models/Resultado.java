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
public class Resultado extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	@ManyToOne
	public DefinicionResultado Definicion;

	enum Estado {
		Nuevo,
		Parcial,
		Final
	}
	
	public Estado Estado;
	
	public Long Entero;
	
	@ManyToOne
	public Equipo Equipo;
	
	@ManyToOne
	public Partido Partido;
	
	@ManyToOne
	public Porcion Porcion;

	public static Finder<Long,Resultado> find = new Finder<Long,Resultado>(
		    Long.class, Resultado.class
		  );

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public DefinicionResultado getDefinicion() {
		return Definicion;
	}

	public void setDefinicion(DefinicionResultado definicion) {
		Definicion = definicion;
	}

	public Estado getEstado() {
		return Estado;
	}

	public void setEstado(Estado estado) {
		Estado = estado;
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
