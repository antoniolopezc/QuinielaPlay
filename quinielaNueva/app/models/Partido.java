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
	public Long Id;
	
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
		Final;
	}
	
	public Tiempo TiempoActual = Tiempo.NoIniciado;
	
	@ManyToOne
	public Equipo EquipoA;
	
	@ManyToOne
	public Equipo EquipoB;
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Resultado> Resultados=new ArrayList<Resultado>();

	public static Finder<Long,Partido> find = new Finder<Long,Partido>(
		    Long.class, Partido.class
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

	public String getNombreCorto() {
		return NombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		NombreCorto = nombreCorto;
	}

	public String getAbreviatura() {
		return Abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		Abreviatura = abreviatura;
	}

	public String getDescricion() {
		return Descricion;
	}

	public void setDescricion(String descricion) {
		Descricion = descricion;
	}

	public String getLugar() {
		return Lugar;
	}

	public void setLugar(String lugar) {
		Lugar = lugar;
	}

	public java.sql.Timestamp getFecha() {
		return Fecha;
	}

	public void setFecha(java.sql.Timestamp fecha) {
		Fecha = fecha;
	}

	public Tiempo getTiempoActual() {
		return TiempoActual;
	}

	public void setTiempoActual(Tiempo tiempoActual) {
		TiempoActual = tiempoActual;
	}
	
	public void setTiempoActual(String S) {
		TiempoActual = Tiempo.valueOf(S);
	}

	public Equipo getEquipoA() {
		return EquipoA;
	}

	public void setEquipoA(Equipo equipoA) {
		EquipoA = equipoA;
	}

	public Equipo getEquipoB() {
		return EquipoB;
	}

	public void setEquipoB(Equipo equipoB) {
		EquipoB = equipoB;
	}

	public List<Resultado> getResultados() {
		return Resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		Resultados = resultados;
	}
}
