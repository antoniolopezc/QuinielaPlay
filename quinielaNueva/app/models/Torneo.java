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
public class Torneo extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	Long Id;
	
	@Constraints.Required
	String Nombre;
	
	@Constraints.Required
	String NombreCorto;
	
	@Constraints.Required
	String Abreviatura;
	
	String Descricion;
	
	@Constraints.Required
	Date Inicio;
	
	@Constraints.Required
	Date Fin;
	
	@Lob
	byte[] Imagen;
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Equipo> Equipos=new ArrayList<Equipo>();
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Partido> Partidos=new ArrayList<Partido>();

	@ManyToMany 
	List<Regla> Reglas=new ArrayList<Regla>();
	
	/*
	 * Representa las fases o agrupaciones de partidos del torneo
	 */
	@OneToMany(cascade={CascadeType.ALL})
	List<Porcion> Porciones=new ArrayList<Porcion>();
	
	@ManyToOne
	Usuario Propietario;
	
	@ManyToMany 
	List<Usuario> Administradores=new ArrayList<Usuario>();
	
	public static Finder<Long,Torneo> find = new Finder<Long,Torneo>(
			    Long.class, Torneo.class
			  );
	
	public Map<String,List<ValidationError>> validate(){
		
		return null;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String arg0) {
		Nombre=arg0;
	}
	
	public String getNombreCorto() {
		return NombreCorto;
	}
	public void setNombreCorto(String arg0) {
		NombreCorto=arg0;
	}

	public String getAbreviatura() {
		return Abreviatura;
	}
	public void setAbreviatura(String arg0) {
		Abreviatura=arg0;
	}
	
	public String getDescricion() {
		return Descricion;
	}
	public void setDescricion(String arg0) {
		Abreviatura=arg0;
	}

	public Date getInicio() {
		return Inicio;
	}
	public void setInicio(Date arg0) {
		Inicio=arg0;
	}

	public Date getFin() {
		return Fin;
	}
	public void setFin(Date arg0) {
		Fin=arg0;
	}
	/**
	 * @return the partidos
	 */
	public List<Partido> getPartidos() {
		return Partidos;
	}
	/**
	 * @param partidos the partidos to set
	 */
	public void setPartidos(List<Partido> partidos) {
		Partidos = partidos;
	}
	/**
	 * @return the reglas
	 */
	public List<Regla> getReglas() {
		return Reglas;
	}
	/**
	 * @param reglas the reglas to set
	 */
	public void setReglas(List<Regla> reglas) {
		Reglas = reglas;
	}
	/**
	 * @return the porciones
	 */
	public List<Porcion> getPorciones() {
		return Porciones;
	}
	/**
	 * @param porciones the porciones to set
	 */
	public void setPorciones(List<Porcion> porciones) {
		Porciones = porciones;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public byte[] getImagen() {
		return Imagen;
	}
	public void setImagen(byte[] imagen) {
		Imagen = imagen;
	}
	public List<Equipo> getEquipos() {
		return Equipos;
	}
	public void setEquipos(List<Equipo> equipos) {
		Equipos = equipos;
	}
	public Usuario getPropietario() {
		return Propietario;
	}
	public void setPropietario(Usuario propietario) {
		Propietario = propietario;
	}
	public List<Usuario> getAdministradores() {
		return Administradores;
	}
	public void setAdministradores(List<Usuario> administradores) {
		Administradores = administradores;
	}

}
