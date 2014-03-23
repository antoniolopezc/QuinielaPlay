/**
 * 
 */
package reglas;

import models.*;

/**
 * @author lopez
 * Esta Regla sirve para cacular el Final de los equipo basado en la Abreviacion de Equipo
 * 
 */
public class QuienPasa extends ReglaBase {

	enum enumTipo {
		Grupo,
		PartidoGanador,
		PartidoPerdido,
		NoAplica,
	}
	class DependeDe {
		enumTipo Tipo;
		String Dato;
	}
	
	public QuienPasa(String Parametro) {
		super(Parametro);
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		return -1;
	}
	public DependeDe DependeDe(Equipo Equipo){
		DependeDe Resultado= new DependeDe();
		Resultado.Tipo=enumTipo.NoAplica;
		if(Equipo.caculable) {
			if(Equipo.Abreviatura.charAt(0)=='G') {
				Resultado.Tipo=enumTipo.PartidoGanador;
				Resultado.Dato=Equipo.Abreviatura.substring(1);		
			} else if(Equipo.Abreviatura.charAt(0)=='P') { 
				Resultado.Tipo=enumTipo.PartidoPerdido;
				Resultado.Dato=Equipo.Abreviatura.substring(1);		
			} else { 
				Resultado.Tipo=enumTipo.Grupo;
				Resultado.Dato=Equipo.Abreviatura;		
			}
		}
		return Resultado;
		
	}
	@Override
	public long cacular(Torneo Torneo) {
		Partido Partido;
		Porcion Grupo;
		for(Equipo Equipo: Torneo.getEquipos()){
			DependeDe DD=DependeDe(Equipo);
			switch(DD.Tipo) {
			case Grupo:
				Grupo=BuscarPorcion(DD.Dato.substring(1));
				Equipo.setFinal(ProcesarDatos(Grupo,DD.Dato.substring(0, 1)));
				break;
			case PartidoGanador:
				Partido=BuscarPartido(DD.Dato);
				Equipo.setFinal(ProcesarDatos(Partido,enumTipo.PartidoGanador));
				break;
			case PartidoPerdido:
				Partido=BuscarPartido(DD.Dato);
				Equipo.setFinal(ProcesarDatos(Partido,enumTipo.PartidoPerdido));
				break;
			case NoAplica: //no se hace nada
				break;
			}
		}
		return 0;
	}

	private models.Equipo ProcesarDatos(Partido partido, enumTipo Tipo) {
		if(partido.getTiempoActual()==Partido.Tiempo.Final) {
			for(Resultado R:partido.getResultados()){
				if(R.getDefinicion().getNombreCorto().compareTo("Paso")==0){
					if(Tipo==enumTipo.PartidoGanador)
						return R.getEquipo();
					else if(Tipo==enumTipo.PartidoPerdido) {
					if(partido.getEquipoA()==R.getEquipo()) {
						return partido.getEquipoB();
					}
					if(partido.getEquipoB()==R.getEquipo()) {
						return partido.getEquipoA();
					}
					}
				}
			}
		}
		return null;
	}

	private models.Equipo ProcesarDatos(Porcion grupo, String quien) {
		// Validar que el grupo Termino
		for(Partido P:grupo.getPartidos()) {
			if(P.getTiempoActual()!=Partido.Tiempo.Final) {
				return null;
			}
		}
		//buscar el puesto buscado
		for(Resultado R: grupo.getResultados()){
			if(R.getDefinicion().getAbreviatura()==quien){
				return R.getEquipo();
			}
		}
		return null;
	}

	private models.Partido BuscarPartido(String dato) {
		return Partido.find.where().eq("Abreviatura","P"+dato).findUnique();
	}

	private Porcion BuscarPorcion(String dato) {
		return Porcion.find.where().eq("Abreviatura",dato).findUnique();
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		return -1;
	}

	@Override
	public String IncluirJS() {
		return null;
	}


}
