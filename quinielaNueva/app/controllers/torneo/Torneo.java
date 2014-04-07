/**
 * 
 */
package controllers.torneo;



import java.util.List;

import models.TipoEstado;

import com.avaje.ebean.Expr;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import reglas.ReglaBase;
import securesocial.core.java.SecureSocial;
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
    /**
     * Actualizar resultados torneo
     */
    @SecureSocial.SecuredAction
    public static Result ActualizarResultados(Long id) {
    	if(id==-1) {
 //   		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    		List<models.Torneo> Torneos = models.Torneo.find.all(); //where().eq("Propietario", (models.Usuario) Usuario).findList();
    		return ok(SelecionarTorneo.render(Torneos));
    	}
    	models.Usuario Usuario = (models.Usuario) ctx().args.get(SecureSocial.USER_KEY);
    	models.Torneo Torneo = models.Torneo.find.byId(id);
    	List<models.Resultado> Resultados=models.Resultado.find.where()
				.or(Expr.in("Porcion",Torneo.getPorciones()),
					Expr.in("Partido",Torneo.getPartidos())).findList();
    	return ok(ActualizarResultados.render(Torneo,Resultados,new Boolean(Torneo.getPropietario().getId()!=Usuario.Id)));
    }
    /**
     * Guadar los resultados actualizados torneo
     */
    @SecureSocial.SecuredAction
    public static Result GuadarResultados() {
    	DynamicForm  FormaLlena =Form.form().bindFromRequest();
    	String s;
    	models.Usuario Usuario = (models.Usuario) ctx().args.get(SecureSocial.USER_KEY);
    	models.Torneo Torneo=models.Torneo.find.byId(Long.parseLong(FormaLlena.get("Id"))); 
    	List<models.Resultado> Resultados=models.Resultado.find.where()
    										.or(Expr.in("Porcion",Torneo.getPorciones()),
    											Expr.in("Partido",Torneo.getPartidos())).findList();
    	if(Torneo.getPropietario().getId()!=Usuario.Id){
    		return ok(ActualizarResultados.render(Torneo,Resultados,new Boolean(true)));
    	}
    	for(models.Resultado Resultado: Resultados){
    		s=FormaLlena.get(Long.toString(Resultado.getId()));
    		if(s==null|| s.length()==0){
    			Resultado.setEntero(null);
    			Resultado.setEquipo(null);
    			Resultado.setEstado(TipoEstado.Nuevo);
    		} else
    		switch(Resultado.getDefinicion().getTipo()) {
			case Entero:
				Resultado.setEntero(Long.parseLong(s));
				break;
			case Equipo:
				Resultado.setEquipo(models.Equipo.find.byId(Long.parseLong(s))); 					
				break;
			default:
				break;
    		}
    		Resultado.save();
    	}
    	for(models.Partido Partido:Torneo.getPartidos()){
    		s=FormaLlena.get("Tiempo-"+Long.toString(Partido.getId()));
    		Partido.setTiempoActual(s);
    		switch(Partido.getTiempoActual()){
			case Final:
	    		for(models.Resultado Resultado: Resultados){
	    			Resultado.setEstado(TipoEstado.Final);
	    		}
				break;
			case NoIniciado:
	    		for(models.Resultado Resultado: Resultados){
	    			Resultado.setEstado(TipoEstado.Nuevo);
	    		}
				break;
			default:
	    		for(models.Resultado Resultado: Resultados){
	    			Resultado.setEstado(TipoEstado.Parcial);
	    		}
				break;
    		}
    		Partido.save();
    	} 
    	if(CacularIndicadores(Torneo)) {
    		Torneo.save();
    		List<models.Quiniela> Quinielas=models.Quiniela.find.where().eq("Torneo", Torneo).findList();
    		for(models.Quiniela Q:Quinielas){
    			Q.calcular();
    		}
    		return ok(ActualizarResultados.render(Torneo,Resultados,new Boolean(false)));
    	} else 
    		return ok("error");

    }
    
    static private boolean CacularIndicadores(models.Torneo Torneo){

    	
    	for(models.Regla Regla: Torneo.getReglas()){
    			if(ReglaBase.cacular(Regla,Torneo)!=0) return false; 
    	}
		return true;
	}
}