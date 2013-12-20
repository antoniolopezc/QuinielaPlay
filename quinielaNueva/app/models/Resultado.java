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
	long Id;

	/*
	 * A quien Pertnece
	 */
	@ManyToOne
	public Partido Partido;
	
	@ManyToOne
	public Porcion Porcion;
	
	/*
	 * Opcional si el Resultado esta asociado a Equipo que participa
	 */
	@ManyToOne
	public Equipo Equipo;
	
	public String Nombre;
	
	public String NombreCorto;
	
	public String Abreviatura;
	
	enum Estado {
		Nuevo,
		Parcial,
		Final
	}
	
	public Estado Estado;
	
	/*
	 * Solo uno de los resultados estara lleno
	 */
	@ManyToOne
	public Equipo ResultadoEquipo;
	
	public long ResultadoNumerico;


}
