/**
 * 
 */

package models;

import javax.persistence.*;
import play.db.ebean.*;

import org.jasypt.util.password.*;


/**
 * @author alopez1
 *
 */
@Entity
public class Usuario extends Model {
	/**
	 * Generado por el epclise no se sitene utilidad
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public long Id;
	
	@Column(unique = true)
	public String Alias;
	
	public String Correo;
	
	public String Clave;
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(
			    Long.class, Usuario.class
			  );

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		Clave = passwordEncryptor.encryptPassword(clave);
	}
	
	public boolean Validad(String clave) {
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		return passwordEncryptor.checkPassword(clave,Clave);
	}
	
}
