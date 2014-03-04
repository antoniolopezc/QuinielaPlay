package controllers.quiniela;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;
import views.html.quiniela.Pronostico.*;



public class Pronostico extends Controller {
    /**
     * Agregar un nuevo Pronostico
     * @return 
     */
	static private boolean GeneraIndicadores(models.Pronostico Pronostico,Long  id){

		models.Quiniela Quiniela=models.Quiniela.find.byId(id);
    	Ebean.refresh(Quiniela.Torneo);
    	Pronostico.Quiniela=Quiniela;
    	
    	for(models.Regla Regla: Quiniela.Reglas){
    		try {
    			Class<?> R=Class.forName(Regla.Clase);
    			Object O=R.newInstance();
    			Method M=R.getMethod("GenerarPronostico",models.Quiniela.class,models.Pronostico.class); 
				long error=(long) M.invoke(O, Quiniela,Pronostico);
				if(error>0) return false;
				
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException |InstantiationException 
					| ClassNotFoundException | NoSuchMethodException 
					| SecurityException e) {
				e.printStackTrace();
				return false;
			}
    	}
		return true;
	
	}
	
	@SecureSocial.SecuredAction
    public static Result agregar(Long id) {
    	if(id==-1) { 
    		List<models.Quiniela> Quinielas = models.Quiniela.find.all();
    		return ok(Agregar.render(Quinielas));
    	}
    	models.Pronostico Pronostico=new models.Pronostico();
    	if (!GeneraIndicadores(Pronostico,id))
    		return ok("<p>Error</p>");
    	
    	return ok(AgregarDetalle.render(Pronostico));
    }
    
    @SecureSocial.SecuredAction
    public static Result actualizar(Long id) {
    	if(id==-1) { 
    		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    		List<models.Pronostico> Pronosticos = models.Pronostico.find.where().eq("Dueño", (models.Usuario) Usuario).findList();
    		return ok(EscogerPronostico.render(Pronosticos));
    	}
    	models.Pronostico Pronostico= models.Pronostico.find.byId(id);
		Ebean.refresh(Pronostico.Quiniela);
		Ebean.refresh(Pronostico.Quiniela.Torneo);    	
    	return ok(AgregarDetalle.render(Pronostico));
    }
    
    /*
     * Guarda un Pronostico
     */
    @SecureSocial.SecuredAction
    public static Result guardar() {
    	DynamicForm  FormaLlena =Form.form().bindFromRequest();
    	String s;
    	models.Pronostico Pronostico;
    	
    	if(FormaLlena.get("Id")=="") {
    		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    		Pronostico=new models.Pronostico();
    		Pronostico.Nombre=FormaLlena.get("Nombre");
    		Pronostico.Dueño=(models.Usuario) Usuario;
    		if (!GeneraIndicadores(Pronostico,Long.parseLong(FormaLlena.get("Quiniela"))))
    			return ok("<p>Error</p>");
    	} else {
    		Pronostico= models.Pronostico.find.byId(Long.parseLong(FormaLlena.get("Id")));
    		Ebean.refresh(Pronostico.Quiniela);
    		Ebean.refresh(Pronostico.Quiniela.Torneo);
    	}

    	for(models.ResultadoPronostico Resultado: Pronostico.Resultados){
    		Ebean.refresh(Resultado.Resultado);
    		Ebean.refresh(Resultado.Resultado.Definicion);
    		s=FormaLlena.get(Long.toString(Resultado.Resultado.Id));
    		if(s==""||s==null) 
    			continue;
    		switch(Resultado.Resultado.Definicion.Tipo) {
				case Entero:
					Resultado.Entero= Long.parseLong(s);
					break;
				case Equipo:
					Resultado.Equipo=models.Equipo.find.byId(Long.parseLong(s)); 					
					break;
				default:
					break;
    		}
    	}
    	Ebean.save(Pronostico);
    	return ok(AgregarDetalle.render(Pronostico));
    }
	
}
