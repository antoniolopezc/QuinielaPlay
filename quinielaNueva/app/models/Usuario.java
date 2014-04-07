/**
 * 
 */
package models;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.*;
import scala.Option;
import play.libs.Scala;
import securesocial.core.AuthenticationMethod;
import securesocial.core.Identity;
import securesocial.core.IdentityId;
import securesocial.core.OAuth1Info;
import securesocial.core.OAuth2Info;
import securesocial.core.PasswordInfo;


/**
 * @author alopez1
 * Contiene los equipos que participan en un torneo
 */
@Entity
public class Usuario extends Model implements Identity {
	/**
	 * Agregado por Eclipse no estoy seguro si sirve de algo
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long Id;
	
	@Constraints.Required
	@Column(unique=true)
	public String email;
	
	public String nombre;
	
	public String Avatar;
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(
		    Long.class, Usuario.class
		  );

	@Override
	public AuthenticationMethod authMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option<String> avatarUrl() {
		// TODO Auto-generated method stub
		return Scala.Option(Avatar);
	}

	@Override
	public Option<String> email() {
		// TODO Auto-generated method stub
		return Scala.Option(email);
	}

	@Override
	public String firstName() {
		// Solo nombre Completo
		return null;
	}

	@Override
	public String fullName() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public IdentityId identityId() {
		IdentityId I=new IdentityId(Id.toString(),"Quiniela");
		return I;
	}

	@Override
	public String lastName() {
		// Solo nombre Completo
		return null;
	}

	@Override
	public Option<OAuth1Info> oAuth1Info() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option<OAuth2Info> oAuth2Info() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option<PasswordInfo> passwordInfo() {
		// No uso password interno
		return null;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	
}
