package controllers;

import java.util.*;

import models.*;
import models.Pronostico;
import play.mvc.*;
import reglas.ReglaBase;
import utilitario.Resumen;
import views.html.Quiniela.*;


public class Quiniela extends Controller {
	
	public static Result resumen(Long Id) {
		if(Id==-1) {
			List<models.Quiniela> Quinielas=models.Quiniela.find.all();
			return ok(Elegir.render(Quinielas,"Elija Quiniela para ver el Resumen","/Quiniela/Resumen"));
		}
		models.Quiniela Quiniela=models.Quiniela.find.byId(Id);
		List<utilitario.Resumen> Resumenes= GenerarResumen(Quiniela);
		return ok(ListarResumenes.render(Quiniela,Resumenes));
    }

	private static List<Resumen> GenerarResumen(models.Quiniela quiniela) {
		List<utilitario.Resumen> Resumenes=new ArrayList<utilitario.Resumen>();
		List<models.Pronostico> Pronosticos=models.Pronostico.find.where().eq("Quiniela", quiniela).findList();
		Long Posicion=new Long(0);
		Long PosicionSig=new Long(1);
		Long Anterior=Long.MAX_VALUE;
		
		for(models.Pronostico P: Pronosticos) {
			Resumen R=new Resumen();
			R.setJugador(P.getPropietario());
			R.setPronostico(P);
			Totalizar(R,P.getPuntos());
			R.setParticipa(P.isAprobado());
			R.setPosicion(new Long(0));
			Resumenes.add(R);
		}
		Collections.sort(Resumenes);
		for(Resumen R:Resumenes){
			Posicion=(Anterior.equals(R.getPunto())?Posicion:PosicionSig);
			PosicionSig++;
			R.setPosicion(Posicion);
			Anterior=R.getPunto();
		}
		return Resumenes;
	}

	private static void Totalizar(Resumen r, List<Punto> puntos) {
		Float Total=new Float(0);
		Long TotalMaximo=new Long(0);
		r.setMaximo(new Long(0));
		r.setPunto(new Long(0));
		r.setJugados(new Long(0));
		for(Punto P:puntos){
			switch(P.getEstado()) {
			case Final:
				r.setPunto(r.getPunto()+P.getValor());
				r.setMaximo(r.getMaximo()+P.getValor());
				r.setJugados(r.getJugados()+P.getMaximo());
				Total+=P.getMaximo();
				break;
			case Nuevo:
			case Parcial:
			default:
				r.setMaximo(r.getMaximo()+P.getMaximo());
				break;
			}
			TotalMaximo+=P.getMaximo();
		}
		if(Total==0)
			r.setPorcentajeTotal(new Float(0));
		else
			r.setPorcentajeTotal(r.getPunto()/Total);
		r.setTotalMaximo(TotalMaximo);
	}
	
	public static Result comparar(Long Id, Long IdPronostico) {
		if(Id==-1) {
			List<models.Quiniela> Quinielas=models.Quiniela.find.all();
			return ok(Elegir.render(Quinielas,"Elija Quiniela para ver pronosticos","/Quiniela/Comparar"));
		}
		if(IdPronostico==-1) {
			List<models.Pronostico> Pronosticos=models.Pronostico.find.where().eq("quiniela_id", Id).findList();
			return ok(views.html.Pronostico.Elegir.render(Pronosticos,"Elija Quiniela para ver pronosticos","/Quiniela/Comparar/"+Id.toString()));
		}
		models.Quiniela Quiniela=models.Quiniela.find.byId(Id);
		models.Pronostico Pronostico=models.Pronostico.find.byId(IdPronostico);
		List<utilitario.Resumen> Resumenes= GenerarResumen(Quiniela);
		ActualizarResumen(Resumenes,Pronostico,Quiniela);
		return ok(Comparar.render(Quiniela,Resumenes));
    }

	private static void ActualizarResumen(List<Resumen> Resumenes,
			Pronostico Pronostico,models.Quiniela Quiniela) {
		Long Posicion=new Long(0);
		Long PosicionSig=new Long(1);
		Long Anterior=Long.MAX_VALUE;
		
		for(Resumen R: Resumenes) {
			if(R.getPronostico().getId()==Pronostico.getId()) {
				R.setPunto(R.getMaximo());
			}
			else 
				for(Regla Rg:Quiniela.getReglas()){
					R.setPunto(R.getPunto()+ReglaBase.comparar(Rg, Pronostico, R.getPronostico()));
				}
		}
		Collections.sort(Resumenes);
		for(Resumen R:Resumenes){
			Posicion=(Anterior.equals(R.getPunto())?Posicion:PosicionSig);
			PosicionSig++;
			R.setPosicion(Posicion);
			Anterior=R.getPunto();
		}
		
	}

}
