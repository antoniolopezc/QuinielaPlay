package controllers;

import play.mvc.*;

import views.html.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SocialUser;

public class Application extends Controller {
	
    public static Result index() {
        return ok(index.render("Quiniela"));
    }
    
	/**
     * Funcion para Verificar el usuario o solicitar clave.
     */
	@SecureSocial.UserAwareAction
	public static Result loggeado() {
		/*como obtengo el usuario*/
		SocialUser Usuario= (SocialUser) ctx().args.get(SecureSocial.USER_KEY);
		if(Usuario==null){
			return ok(logear.render());
		}
		if(Usuario.fullName==null) {
			return ok(Logeado.render(Usuario.firstName+" "+Usuario.lastName, Usuario.avatarUrl));
		}
		return ok(Logeado.render(Usuario.fullName, Usuario.avatarUrl));
	}
	
	@SecureSocial.SecuredAction()
	public static Result rloggeado() {
		return loggeado();
	}
}
