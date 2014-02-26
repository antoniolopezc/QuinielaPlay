package controllers.quiniela;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.data.Form;
import views.html.quiniela.Pronostico.*;



public class Pronostico extends Controller {
    /**
     * Agregar un nuevo Pronostico
     */
    public static Result agregar(long id) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
    	if(id==-1) { 
    		List<models.Quiniela> Quinielas = models.Quiniela.find.all();
    		return ok(Agregar.render(Quinielas));
    	}
    	models.Quiniela Quiniela=models.Quiniela.find.byId(id);
    	Ebean.refresh(Quiniela.Torneo);
    	models.Pronostico Pronostico=new models.Pronostico();
    	Pronostico.Quiniela=Quiniela;
    	
    	for(models.Regla Regla: Quiniela.Reglas){
    		try {
    			Class<?> R=Class.forName(Regla.Clase);
    			Object O=R.newInstance();
    			Method M=R.getMethod("GenerarPronostico",models.Quiniela.class,models.Pronostico.class); 
				long error=(long) M.invoke(O, Quiniela,Pronostico);
				if(error>0) return ok("<p>"+error+"</p>");
				
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException |InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Form<models.Pronostico> Forma=new Form<models.Pronostico>(models.Pronostico.class);
    	Forma.fill(Pronostico);

    	return ok(AgregarDetalle.render(Quiniela,Pronostico,Forma));
    }
    
    /*
     * Guarda un Pronostico
     */
    public static Result guardar() {
    	return ok();
    }
	
}
