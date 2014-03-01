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
	
	
}
