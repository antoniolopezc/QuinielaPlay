/**
 * 
 */
package controllers;



import java.util.List;

import models.Porcion;
import models.TipoEstado;

import com.avaje.ebean.Expr;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import reglas.ReglaBase;
import securesocial.core.java.SecureSocial;
import views.html.Torneo.*;

/**
 * @author alopez1
 *
 */
public class Torneo extends Controller {
	  
    /**
     * Actualizar resultados torneo
     */
    @SecureSocial.SecuredAction
    public static Result ActualizarResultados(Long id) {
    	if(id==-1) {
 //   		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    		List<models.Torneo> Torneos = models.Torneo.find.all(); //where().eq("Propietario", (models.Usuario) Usuario).findList();
    		return ok(SelecionarTorneo.render(Torneos,"/Torneo/Resultado/Actualizar"));
    	}
    	models.Usuario Usuario = (models.Usuario) ctx().args.get(SecureSocial.USER_KEY);
    	models.Torneo Torneo = models.Torneo.find.byId(id);
    	return ok(ActualizarResultados.render(Torneo,new Boolean(Torneo.getPropietario().getId()!=Usuario.getId())));
    }
    /**
     * Guadar los resultados actualizados torneo
     */
    @SecureSocial.SecuredAction
    public static Result GuadarResultados() {
    	DynamicForm  FormaLlena =Form.form().bindFromRequest();
    	String s;
    	models.TipoEstado TE=models.TipoEstado.Final;
    	models.Usuario Usuario = (models.Usuario) ctx().args.get(SecureSocial.USER_KEY);
    	models.Torneo Torneo=models.Torneo.find.byId(Long.parseLong(FormaLlena.get("Id"))); 
    	List<models.Resultado> Resultados=models.Resultado.find.where()
    										.or(Expr.in("Porcion",Torneo.getPorciones()),
    											Expr.in("Partido",Torneo.getPartidos())).findList();
    	if(Torneo.getPropietario().getId()!=Usuario.Id){
    		return ok(ActualizarResultados.render(Torneo,new Boolean(true)));
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
    		if(s!=null) 
    			Partido.setTiempoActual(s);
    		switch(Partido.getTiempoActual()){
			case Final:
	    		for(models.Resultado Resultado: Partido.getResultados()){
	    			Resultado.setEstado(TipoEstado.Final);
	    			Resultado.save();
	    		}
				break;
			case NoIniciado:
	    		for(models.Resultado Resultado: Partido.getResultados()){
	    			Resultado.setEstado(TipoEstado.Nuevo);
	    			Resultado.save();
	    		}
				break;
			default:
	    		for(models.Resultado Resultado: Partido.getResultados()){
	    			Resultado.setEstado(TipoEstado.Parcial);
	    			Resultado.save();
	    		}
				break;
    		}
    		Partido.save();
    	}
    	
    	for(models.Porcion Porcion:Torneo.getPorciones()){
    		TE=obtenerEstado(Porcion);
    		for(models.Resultado Resultado: Porcion.getResultados()){
    			Resultado.setEstado(TE);
    			Resultado.save();
    		}
    		Porcion.save();
    	}
    	
    	if(CacularIndicadores(Torneo)) {
    		Torneo.save();
    		List<models.Quiniela> Quinielas=models.Quiniela.find.where().eq("Torneo", Torneo).findList();
    		for(models.Quiniela Q:Quinielas){
    			Q.calcular();
    		}
    		return ok(ActualizarResultados.render(Torneo,new Boolean(false)));
    	} else 
    		return ok("error");

    }
    
    private static TipoEstado obtenerEstado(Porcion Porcion) {
		for(models.Partido Partido:Porcion.getPartidos()) {
			switch(Partido.getTiempoActual()){
			case Final:
				break;
			case NoIniciado:
				return TipoEstado.Nuevo;
			default:
				return TipoEstado.Parcial;
			}
		}
		return TipoEstado.Final;
    }
		
	static private boolean CacularIndicadores(models.Torneo Torneo){

    	
    	for(models.Regla Regla: Torneo.getReglas()){
    			if(ReglaBase.cacular(Regla,Torneo)!=0) return false; 
    	}
		return true;
	}
    public static Result Listar(Long id) {
    	models.Torneo Torneo = models.Torneo.find.byId(id);
    	return ok(views.html.Torneo.Torneo.render(Torneo,true));
    }
}