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
public class Porcion extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	Long Id;
	
	String Nombre;
	
	String NombreCorto;
	
	String Abreviatura;
	
	String Descricion;
	
	Date Inicio;
	
	Date Fin;
	
	@ManyToMany
	List<Partido> Partidos=new ArrayList<Partido>();
   	
	@OneToMany(cascade={CascadeType.ALL})
	List<Resultado> Resultados=new ArrayList<Resultado>();

	public static Finder<Long,Porcion> find = new Finder<Long,Porcion>(
		    Long.class, Porcion.class
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

	public Date getInicio() {
		return Inicio;
	}

	public void setInicio(Date inicio) {
		Inicio = inicio;
	}

	public Date getFin() {
		return Fin;
	}

	public void setFin(Date fin) {
		Fin = fin;
	}

	public List<Partido> getPartidos() {
		return Partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		Partidos = partidos;
	}

	public List<Resultado> getResultados() {
		return Resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		Resultados = resultados;
	}
}
