package controllers;

import java.util.HashMap;
import java.util.List;

import models.Punto;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import reglas.ReglaBase;
import scala.Tuple3;
import scala.Tuple4;
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
    private static HashMap<Tuple3<Long,Long,Long>,Tuple4<Long,Long,Long,Long>> obtenerPuntos() {
    	List<models.Punto> Puntos=Punto.find.all();
    	HashMap<Tuple3<Long,Long,Long>,Tuple4<Long,Long,Long,Long>> Resultado= 
    			new HashMap<Tuple3<Long,Long,Long>,Tuple4<Long,Long,Long,Long>>();
    	Tuple3<Long,Long,Long> PrClave;
    	Tuple3<Long,Long,Long> PoClave;
    	Tuple3<Long,Long,Long> PaClave;
    	Tuple4<Long,Long,Long,Long> PrValor;
    	Tuple4<Long,Long,Long,Long> PoValor;
    	Tuple4<Long,Long,Long,Long> PaValor;
    	
    	for(models.Punto P:Puntos){
    		PaValor=null;
    		PaClave=null;
    		//Obtener Valor Pronostico
    		PrClave =new Tuple3<Long,Long,Long>(P.Pronostico.Id,null,null);
    		PrValor =Resultado.remove(PrClave);
    		if(PrValor==null){ //si no existe Pronostico no existe ninguno
    			PrValor=new Tuple4<Long,Long,Long,Long>(new Long(0),new Long(0),new Long(0),new Long(0));
    		}
    		PoClave =new Tuple3<Long,Long,Long>(P.Pronostico.Id,P.Porcion.Id,null);
    		PoValor =Resultado.remove(PoClave);
    		if(PoValor==null){ //si no existe Porcion no existe Partido
    			PoValor=new Tuple4<Long,Long,Long,Long>(new Long(0),new Long(0),new Long(0),new Long(0));
    		}
    		if(P.Partido!=null) {
    			PaClave =new Tuple3<Long,Long,Long>(P.Pronostico.Id,P.Porcion.Id,P.Partido.Id);
        		PaValor =Resultado.remove(PaClave);
        		if(PaValor==null){ 
        			PaValor=new Tuple4<Long,Long,Long,Long>(new Long(0),new Long(0),new Long(0),new Long(0));
        		}	
    		}

    		switch(P.Estado) {
			case Final:
    			PrValor=new Tuple4<Long,Long,Long,Long>(PrValor._1()+P.getValor(),PrValor._2()+P.getValor(),PrValor._3()+P.getMaximo(),PrValor._4()+P.getMaximo());
    			PoValor=new Tuple4<Long,Long,Long,Long>(PoValor._1()+P.getValor(),PoValor._2()+P.getValor(),PoValor._3()+P.getMaximo(),PoValor._4()+P.getMaximo());
    			if(PaValor!=null) {
    				PaValor=new Tuple4<Long,Long,Long,Long>(PaValor._1()+P.getValor(),PaValor._2()+P.getValor(),PaValor._3()+P.getMaximo(),PaValor._4()+P.getMaximo());
    			}
				break;
			case Nuevo:
    			PrValor=new Tuple4<Long,Long,Long,Long>(PrValor._1(),PrValor._2(),PrValor._3(),PrValor._4()+P.getMaximo());
    			PoValor=new Tuple4<Long,Long,Long,Long>(PoValor._1(),PoValor._2(),PoValor._3(),PoValor._4()+P.getMaximo());
    			if(PaValor!=null) {
    				PaValor=new Tuple4<Long,Long,Long,Long>(PaValor._1(),PaValor._2(),PaValor._3(),PaValor._4()+P.getMaximo());
    			}
				break;
			case Parcial:
			default:
				PrValor=new Tuple4<Long,Long,Long,Long>(PrValor._1(),PrValor._2()+P.getValor(),PrValor._3(),PrValor._4()+P.getMaximo());
    			PoValor=new Tuple4<Long,Long,Long,Long>(PoValor._1(),PoValor._2()+P.getValor(),PoValor._3(),PoValor._4()+P.getMaximo());
    			if(PaValor!=null) {
    				PaValor=new Tuple4<Long,Long,Long,Long>(PaValor._1(),PaValor._2()+P.getValor(),PaValor._3(),PaValor._4()+P.getMaximo());
    			}
				break;
    		}
    		Resultado.put(PrClave, PrValor);
    		Resultado.put(PoClave, PoValor);
			if(PaValor!=null&& PaClave!=null) {
				Resultado.put(PaClave, PaValor);
			}
    	}
		return Resultado;
    }
    
    
    @SecureSocial.UserAwareAction 
    public static Result listar(Long Id) {
    	if(Id==-1) {
    		List<models.Pronostico> Pronosticos=models.Pronostico.find.all();
    		HashMap<Tuple3<Long,Long,Long>,Tuple4<Long,Long,Long,Long>> Puntos=obtenerPuntos();
    	
    		return ok(Listar.render(Pronosticos,Puntos));
    	} else {
    		models.Pronostico Pronostico=models.Pronostico.find.byId(Id);
    		HashMap<Tuple3<Long,Long,Long>,Tuple4<Long,Long,Long,Long>> Puntos=obtenerPuntos();
    		return ok(ListarUno.render(Pronostico,Puntos));
    	}
    }
}
