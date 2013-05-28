/**
 * 
 */
package controllers.torneo;



import java.util.List;

import play.mvc.*;
import play.data.Form;

import views.html.torneo.torneo.*;

/**
 * @author alopez1
 *
 */
public class Torneo extends Controller {
	  
    /**
     * Muestra los torneos.
     */
    public static Result lista() {
    	List<models.Torneo> Torneos = models.Torneo.find.all();
        return ok(Lista.render(Torneos));
    }

    /**
     * Muestra un torneo de forma breve.
     */
    public static Result breve(long id) {
    	models.Torneo T = models.Torneo.find.byId(id);
        return ok(Breve.render(T));
    }

    /**
     * Agregar un nuevo torneo
     */
    public static Result agregar() {
    	Form<models.Torneo> FormaTorneo=new  Form<models.Torneo>(models.Torneo.class);
        return ok(Agregar.render(FormaTorneo));
    }
    
    /*
     * Guarda un torneo
     */
    public static Result guardar() {
    	Form<models.Torneo> FormaLlena =Form.form(models.Torneo.class).bindFromRequest();
        if(FormaLlena.hasErrors()) {   
            return badRequest(Agregar.render(FormaLlena));
        } else {
        	models.Torneo nuevo = FormaLlena.get();
            nuevo.save();
            return ok(Breve.render(nuevo));
        }
    }
}