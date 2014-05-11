/**
 * 
 */

package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;
import reglas.ReglaBase;


/**
 * @author alopez1
 *
 */
@Entity
public class Quiniela extends Model {
	/**
	 * Agregado por Eclipse no se si sirve para algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	@Constraints.Required
	public String Nombre;
	
	@Constraints.Required
	public String NombreCorto;
	
	@Constraints.Required
	public String Abreviatura;
	
	public String Descricion;
	
	@Constraints.Required
	public Date Inicio;
	
	@Constraints.Required
	public Date Fin;
	
	@Lob
	public byte[] Imagen;
	
	@ManyToOne
	public Torneo Torneo;
	
	@ManyToMany 
	public List<Regla> Reglas=new ArrayList<Regla>();	
	
	@ManyToOne
	public Usuario Propietario;
	
	@ManyToMany 
	public List<Usuario> Administradores=new ArrayList<Usuario>();
	
	@ManyToMany 
	public List<Usuario> Participantes=new ArrayList<Usuario>();
	
	public static Finder<Long,Quiniela> find = new Finder<Long,Quiniela>(
			    Long.class, Quiniela.class
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

	public byte[] getImagen() {
		return Imagen;
	}

	public void setImagen(byte[] imagen) {
		Imagen = imagen;
	}

	public Torneo getTorneo() {
		return Torneo;
	}

	public void setTorneo(Torneo torneo) {
		Torneo = torneo;
	}

	public List<Regla> getReglas() {
		return Reglas;
	}

	public void setReglas(List<Regla> reglas) {
		Reglas = reglas;
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

	public List<Usuario> getParticipantes() {
		return Participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		Participantes = participantes;
	}

	public void calcular() {
		List<Pronostico> Pronosticos=Pronostico.find.where().eq("Quiniela", this).findList();
		for(Pronostico P: Pronosticos){
			for(Regla Regla:this.getReglas()){
				ReglaBase.cacular(Regla,P);
			}
			P.save();
		}
	}

	public void agregarParticipante(Usuario usuario) {
		if(!this.Participantes.contains(usuario)){
			this.Participantes.add(usuario);
		}
		
	}
}
