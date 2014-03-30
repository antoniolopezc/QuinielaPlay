/**
 * 
 */
package reglas;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import models.*;

import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.*;

/**
 * @author lopez Esta Regla sirve para Generar un Pronostico en Base al Torneo
 *         de la quiniela para todos los resultados.
 * 
 */
public class PuntuarEstandar extends ReglaBase {

	HashMap<String,Condicion> Condiciones;

	@SuppressWarnings("unchecked")
	public PuntuarEstandar(String Parametro) {
		super(Parametro);
		Yaml y = new org.yaml.snakeyaml.Yaml(new CustomClassLoaderConstructor(
				play.Play.application().classloader()));
		
		Condiciones= (HashMap<String,Condicion>) y.load(Parametro);
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		Condicion Condicion;
		HashMap<Long,ResultadoPronostico> RPronostico=ConvertirMap(Pronostico.getResultados());
		for(Punto Punto:Pronostico.getPuntos()){
			if(Punto.getEstado()!=TipoEstado.Final){
				Condicion=Condiciones.get(Punto.getReferenciaRegla());
				switch(Condicion.CondicionSuma){
					case "Igual":
						ProcesarIgual(Punto,RPronostico);
						break;
					case "IgualPartido":
						ProcesarIgualPartido(Punto,RPronostico);
						break;
					default:
						return -1;
				}
				
			}
		}
		return 0;
	}

	private HashMap<Long, ResultadoPronostico> ConvertirMap(
			List<ResultadoPronostico> resultados) {
		HashMap<Long, ResultadoPronostico> Resultado=new HashMap<Long, ResultadoPronostico>();
		for(ResultadoPronostico RP:resultados){
			Resultado.put(RP.Resultado.Id, RP );
		}
		return Resultado;
	}

	private void ProcesarIgualPartido(Punto Punto,HashMap<Long,ResultadoPronostico> RPronostico) {
		Long T=new Long(0);
		Long P=new Long(0);
		TipoEstado TE=TipoEstado.Final;
		if(Punto.getPartido()!=null) {
			for(Resultado R:Punto.getPartido().getResultados()) {
				P=RPronostico.get(R.getId()).getEntero()-P;
				T=R.getEntero()-T;
				if(R.getEstado()!=TipoEstado.Final){
					TE=R.getEstado();
				}
			}
			Punto.setValor(Long.signum(P)==Long.signum(T)?Punto.getMaximo():0);
			Punto.setEstado(TE);
		}
	}
	/*
	 * Todos los resultados involucrados son iguales.
	 */
	private void ProcesarIgual(Punto Punto,HashMap<Long,ResultadoPronostico> RPronostico) {
		ResultadoPronostico P;
		boolean B=true;
		TipoEstado TE=TipoEstado.Final;
		if(Punto.getResultado()!=null) {
			Resultado R=Punto.getResultado();
			P=RPronostico.get(R.getId());
			B&=((R.getEntero()!=null&&R.getEntero()==P.getEntero())||
			    (R.getEquipo()!=null&&R.getEquipo().getId()==P.getEquipo().getId()));
			TE=R.getEstado();	
		} else if(Punto.getPartido()!=null) {
			for(Resultado R:Punto.getPartido().getResultados()) {
				P=RPronostico.get(R.getId());
				B&=((R.getEntero()!=null&&R.getEntero()==P.getEntero())||
				    (R.getEquipo()!=null&&R.getEquipo().getId()==P.getEquipo().getId()));
				if(R.getEstado()!=TipoEstado.Final){
					TE=R.getEstado();
				}
			}
		} else { //Porcion no puede ser nula
			for(Resultado R:Punto.getPorcion().getResultados()) {
				P=RPronostico.get(R.getId());
				B&=((R.getEntero()!=null&&R.getEntero()==P.getEntero())||
				    (R.getEquipo()!=null&&R.getEquipo().getId()==P.getEquipo().getId()));
				if(R.getEstado()!=TipoEstado.Final){
					TE=R.getEstado();
				}
			}
		}
		Punto.setValor(B?Punto.getMaximo():0);
		Punto.setEstado(TE);
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		for (Entry<String,Condicion> e:Condiciones.entrySet()) {
			Condicion C = e.getValue();
			for (Porcion Porcion : Pronostico.getQuiniela().getTorneo()
					.getPorciones()) {
				if (!Porcion.getNombre().matches(C.CondicionPorcion))
					continue;
				if (C.CondicionPartido == "" || C.CondicionPartido == null) {
					Pronostico.Puntos.add(new Punto(e.getKey(), null, null,
							Porcion,C.Puntos));
					continue;
				}
				for (Partido Partido : Porcion.getPartidos()) {
					if (!Partido.getNombre().matches(C.CondicionPartido))
						continue;
					if (C.CondicionResultado == ""
							|| C.CondicionResultado == null) {
						Pronostico.Puntos.add(new Punto(e.getKey(), null,
								Partido, Porcion,C.Puntos));
						continue;
					}

					for (Resultado Resultado : Partido.getResultados()) {
						if (!Resultado.getDefinicion().getNombre()
								.matches(C.CondicionResultado))
							continue;
						Pronostico.Puntos.add(new Punto(e.getKey(),
								Resultado, Partido, Porcion,C.Puntos));
					}
				}
				for (Resultado Resultado : Porcion.getResultados()) {
					if (!Resultado.getDefinicion().getNombre()
							.matches(C.CondicionResultado))
						continue;
					Pronostico.Puntos.add(new Punto(e.getKey(),
							Resultado, null, Porcion,C.Puntos));
				}

			}
		}
		return 0;
	}

	@Override
	public String IncluirJS() {
		return null;
	}

	@Override
	public long cacular(Torneo Torneo) {
		return 0;
	}
}
