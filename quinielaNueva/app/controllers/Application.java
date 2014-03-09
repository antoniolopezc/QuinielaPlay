package controllers;

import play.mvc.*;

import views.html.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.Identity;


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
		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
				
		if(Usuario==null){
			return ok(logear.render());
		}

		return ok(Logeado.render(Usuario.fullName(), Usuario.avatarUrl().get()));
	}
	
}
