package controllers.quiniela;

import java.util.List;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import reglas.ReglaBase;
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
    	Quiniela.Torneo.refresh();
    	Pronostico.Quiniela=Quiniela;
    	
    	for(models.Regla Regla: Quiniela.Reglas){
    		try {
    			Class<?> R=Class.forName(Regla.Clase);
    			reglas.ReglaBase O=(ReglaBase) R.newInstance();
    			if(O.GenerarPronostico(Quiniela, Pronostico)!=0) return false; 
			} catch (IllegalAccessException  | IllegalArgumentException
					| InstantiationException | ClassNotFoundException  
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
    		List<models.Pronostico> Pronosticos = models.Pronostico.find.where().eq("Propietario", (models.Usuario) Usuario).findList();
    		return ok(EscogerPronostico.render(Pronosticos));
    	}
    	models.Pronostico Pronostico= models.Pronostico.find.byId(id);
		Pronostico.Quiniela.refresh();
		Pronostico.Quiniela.Torneo.refresh();    	
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
    		Pronostico.Propietario=(models.Usuario) Usuario;
    		if (!GeneraIndicadores(Pronostico,Long.parseLong(FormaLlena.get("Quiniela"))))
    			return ok("<p>Error</p>");
    	} else {
    		Pronostico= models.Pronostico.find.byId(Long.parseLong(FormaLlena.get("Id")));
    		Pronostico.Quiniela.refresh();
    		Pronostico.Quiniela.Torneo.refresh();
    	}
    	
		Pronostico.setNombre(FormaLlena.get("Nombre"));
    	for(models.ResultadoPronostico Resultado: Pronostico.Resultados){
    		Resultado.Resultado.refresh();
    		Resultado.Resultado.Definicion.refresh();
    		s=FormaLlena.get(Long.toString(Resultado.Resultado.Id));
    		switch(Resultado.Resultado.Definicion.Tipo) {
				case Entero:
					Resultado.setEntero(s==null||s==""? null: Long.parseLong(s));
					break;
				case Equipo:
					Resultado.setEquipo(s==null||s==""? null: models.Equipo.find.byId(Long.parseLong(s))); 					
					break;
				default:
					break;
    		} 
    	}
       	Pronostico.save();
    	return ok(AgregarDetalle.render(Pronostico));
    }
    @SecureSocial.UserAwareAction 
    public static Result listar() {
    	List<models.Pronostico> Pronosticos=models.Pronostico.find.all();
    	return ok(Listar.render(Pronosticos));
    }
}
