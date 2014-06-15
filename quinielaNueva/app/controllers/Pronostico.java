package controllers;

import java.util.List;
import java.util.Map.Entry;

import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import reglas.ReglaBase;
import securesocial.core.Identity;
import securesocial.core.java.SecureSocial;
import views.html.Pronostico.*;

public class Pronostico extends Controller {
    /**
     * Agregar un nuevo Pronostico
     * @return 
     */
	static private boolean GeneraIndicadores(models.Pronostico Pronostico){
    	for(models.Regla Regla: Pronostico.getQuiniela().getReglas()){
     		if(ReglaBase.generar(Regla, Pronostico)!=0) return false; 
    	}
		return true;
	}
	
	@SecureSocial.SecuredAction
    public static Result agregar(Long id) {
    	if(id==-1) { 
    		List<models.Quiniela> Quinielas = models.Quiniela.find.all();    		
    		return ok(views.html.Quiniela.Elegir.render(Quinielas,"Eliga Quiniela sobre la que crear el Pronostico","/Pronostico/Agregar"));
    	}
		models.Quiniela Quiniela=models.Quiniela.find.byId(id);
    	models.Pronostico Pronostico=new models.Pronostico();
    	Pronostico.setQuiniela(Quiniela);
    	
    	if (!GeneraIndicadores(Pronostico))
    		return ok("<p>Error</p>");
    	
    	return ok(Agregar.render(Pronostico));
    }
	
    /*
     * Actauliza el Pronsotico o solicita elegir uno 
     */
    @SecureSocial.SecuredAction
    public static Result actualizar(Long id) {
    	if(id==-1) { 
    		Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    		List<models.Pronostico> Pronosticos = models.Pronostico.find.where().eq("Propietario", (models.Usuario) Usuario).findList();
    		return ok(Elegir.render(Pronosticos,"Eliga Pronostico para Actualizarlo:","/Pronostico/Actualizar"));
    	}
    	models.Pronostico Pronostico= models.Pronostico.find.byId(id);
    	return ok(Agregar.render(Pronostico));
    }
	
    /*
     * Muestra los pronostico que falta aprobar 
     */
    @SecureSocial.SecuredAction
    public static Result Aprobar() {
    	
    	Identity Usuario = (Identity) ctx().args.get(SecureSocial.USER_KEY);
    	List<models.Quiniela> Quinielas =models.Quiniela.find.where().eq("Propietario", (models.Usuario) Usuario).findList();
    	List<models.Pronostico> Pronosticos = models.Pronostico.find.where().in("Quiniela", Quinielas).findList();
    	return ok(Aprobar.render(Pronosticos));
    }
    /*
     * Guarda Aprobaciones
     */
    @SecureSocial.SecuredAction
    public static Result Aprobados() {
    	DynamicForm  FormaLlena =Form.form().bindFromRequest();
    	
    	for(Entry<String, String> P:FormaLlena.data().entrySet()) {
    		models.Pronostico Pronostico=models.Pronostico.find.byId(Long.parseLong(P.getKey()));
    		Pronostico.setAprobado(P.getValue().equals("Si"));
    		Pronostico.save();
    	}
      	return ok("<div>Aprobados</div>");
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
    		models.Quiniela Quiniela=models.Quiniela.find.byId(Long.parseLong(FormaLlena.get("Quiniela")));
    		Pronostico=new models.Pronostico();
        	Pronostico.setQuiniela(Quiniela);
    		Pronostico.setPropietario((models.Usuario) Usuario);
    		Quiniela.agregarParticipante((models.Usuario) Usuario);
    		if (!GeneraIndicadores(Pronostico))
    			return ok("<p>Error</p>");
    		Quiniela.save();	
    	} else {
    		Pronostico= models.Pronostico.find.byId(Long.parseLong(FormaLlena.get("Id")));
     	}
    	
		Pronostico.setNombre(FormaLlena.get("Nombre"));
    	for(models.ResultadoPronostico Resultado: Pronostico.getResultados()){
    		s=FormaLlena.get(Long.toString(Resultado.getResultado().getId()));
    		switch(Resultado.getResultado().getDefinicion().getTipo()) {
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
    	return ok(Mensaje.render(Pronostico));
    }
    
    @SecureSocial.UserAwareAction 
    public static Result listar(Long Id) {
    	if(Id==-1) {
    		List<models.Pronostico> Pronosticos=models.Pronostico.find.all();
    		return ok(Listar.render(Pronosticos));
    	} else {
    		models.Pronostico Pronostico=models.Pronostico.find.byId(Id);
    		return ok(ListarUno.render(Pronostico));
    	}
    }
    
    public static Result listarPartidos(Long QuinielaID, Long PartidoID){
    	if(QuinielaID==-1){
       		List<models.Quiniela> Quinielas = models.Quiniela.find.all();    		
    		return ok(views.html.Quiniela.Elegir.render(Quinielas,"Eliga Quiniela Para Ver Los Partidos","/Pronostico/ListarPartido"));
     	}
    	if(PartidoID==-1){
    		models.Quiniela Quiniela= models.Quiniela.find.byId(QuinielaID);    		
    		return ok(SelecionarPartido.render(Quiniela));
     	}
    	models.Quiniela Quiniela= models.Quiniela.find.byId(QuinielaID);
     	models.Partido Partido=models.Partido.find.byId(PartidoID);
    	List<models.Pronostico> Pronosticos=models.Pronostico.find.where().eq("Quiniela",Quiniela).findList();
    	List<models.ResultadoPronostico> ResultadosPronostico=models.ResultadoPronostico.find.where().in("Resultado",Partido.getResultados()).findList();
    	List<models.Punto> Puntos=models.Punto.find.where().eq("Partido",Partido).findList();
    	return ok(ListarPartidos.render(Partido,Pronosticos,ResultadosPronostico,Puntos));
    }

	
}
