/**
 * @author alopez1
 * Pagina de Logeo unicamente
 * TODO: Formato de los Puntos en Pronostico
 * TODO: Refactorizar JAvascript que aparece en las hojas.
 * TODO: Comprimir resultados para html mas pequeño
 * TODO: Mejorar mensaje del salvado
 * TODO: Mensaje automatico de Correo Para Aprobacion
 * TODO: Mensaje automatico de Nuevos Resultados
 * TODO: Ver doble funcionamiento de  configuración google.
 * TODO: Generar Respaldo Excel, o PDF para usuarios
 * 
 */
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
