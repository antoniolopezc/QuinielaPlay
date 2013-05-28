/**
 * 
 */
package controllers;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;

import views.html.usuario.*;

/**
 * @author alopez1
 *
 */
public class Usuario extends Controller {
	/*
	 * Crea las rutas para los java scritp
	 */
	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	 /*  No se esta usando     
	  * 		Routes.javascriptRouter("jsRoutesUsuario",
	            controllers.routes.javascript.Usuario.loggeado()
	        )
	   */ );
	}

	/*
	 * Registra la información necesaria en la Sesion
	 */
	private static void iniciaSesion(models.Usuario Usuario) {
		session("Usuario.Alias", Usuario.Alias);
		session("Usuario.Correo", Usuario.Correo);
	}
	
	/**
	 * Listar usuario (Hace Falta?
	 */
	public static Result listar() {
		return ok();
	}

	
	/**
	 * Guarda el usuario y lo coloca como autentico
	 */
	public static Result registrar() {
		Form<models.Usuario> FormaLlena =Form.form(models.Usuario.class).bindFromRequest();
        if(FormaLlena.hasErrors()) {   
            return badRequest(Registra.render(FormaLlena));
        } else {
        	models.Usuario nuevo = FormaLlena.get();
            nuevo.save();
            iniciaSesion(nuevo);
            return  redirect("/");
        }
	}

	/**
     * Funcion para registro de Usuario.
     */
	public static Result registra() {
    	Form<models.Usuario> Forma=new  Form<models.Usuario>(models.Usuario.class);
        return ok(Registra.render(Forma));
	}
	
	/**
     * Funcion para Verificar el usuario o solicitar clave.
     */
	public static Result loggeado() {
		if(session("Usuario.Alias") != null) {
			return ok(Logeado.render(session("Usuario.Alias")));
		}
		DynamicForm  Forma=new  DynamicForm();
		return ok(Logging.render(Forma));
	}

	/**
	 * Guarda el usuario y lo coloca como autentico
	 */
	public static Result login() {
		DynamicForm FormaLlena =Form.form().bindFromRequest();
		models.Usuario nuevo;
		boolean r;
       	nuevo = models.Usuario.find.where().eq("Correo", FormaLlena.get("Correo")).findUnique();
       	if(nuevo != null ){
       		r=nuevo.Validad(FormaLlena.get("Clave"));
       		if(r) {
       			iniciaSesion(nuevo);
       			return ok(Logeado.render(session("Usuario.Alias")));
       		}
       	}
        FormaLlena.reject("Correo","La Clave o Correo son invalidos");
   		return badRequest(Logging.render(FormaLlena));
    }

	public static Result salir() {
		session().clear();
		DynamicForm  Forma=new  DynamicForm();
		return ok(Logging.render(Forma));
	}
}
