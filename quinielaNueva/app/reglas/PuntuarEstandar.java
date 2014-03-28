/**
 * 
 */
package reglas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.*;
import models.Punto.EstadoEnum;

import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.*;

import scala.Tuple2;

/**
 * @author lopez Esta Regla sirve para Generar un Pronostico en Base al Torneo
 *         de la quiniela para todos los resultados.
 * 
 */
public class PuntuarEstandar extends ReglaBase {

	Map<String,Condicion> Condiciones;

	public PuntuarEstandar(String Parametro) {
		super(Parametro);
		Yaml y = new org.yaml.snakeyaml.Yaml(new CustomClassLoaderConstructor(
				play.Play.application().classloader()));
		Map<String,Condicion> Temp= (Map<String,Condicion>) y.load(Parametro);
		 Condiciones=Temp;
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		Condicion Condicion;
		Tuple2<String,models.Punto.EstadoEnum> CondicionSuma;
		for(Punto Punto:Pronostico.getPuntos()){
			if(Punto.getEstado()!=models.Punto.EstadoEnum.Final){
				Condicion=Condiciones.get(Punto.getReferenciaRegla());
				CondicionSuma=GenerarCondicionSuma(Pronostico,Condicion);
				if(CondicionSuma._1.matches(Condicion.CondicionSuma)){
					Punto.setValor(Condicion.Puntos);
					Punto.setEstado(CondicionSuma._2);
				}
			}
		}
		return 0;
	}

	private Tuple2<String, EstadoEnum> GenerarCondicionSuma(
			Pronostico Pronostico, Condicion condicion) {
		HashMap<Long,ResultadoPronostico> Resultados=new HashMap<Long,ResultadoPronostico>();
		Resultados= GenerarResultados(Pronostico.Resultados);
		Tuple2<String, EstadoEnum> Return=new Tuple2<String, EstadoEnum>(new String(), EstadoEnum.Parcial);
		for (Porcion Porcion : Pronostico.getQuiniela().getTorneo()
				.getPorciones()) {
			if (!Porcion.getNombre().matches(condicion.CondicionPorcion))
				continue;
			for (Partido Partido : Porcion.getPartidos()) {
				if(!(condicion.CondicionPartido == "" || condicion.CondicionPartido == null)) {
					if (!Partido.getNombre().matches(condicion.CondicionPartido))
						continue;
				}
				for (Resultado Resultado : Partido.getResultados()) {
					if(!(condicion.CondicionResultado == ""
							|| condicion.CondicionResultado == null)) {
						if (!Resultado.getDefinicion().getNombre()
								.matches(condicion.CondicionResultado))
							continue;
					}
					switch(Resultado.getDefinicion().getTipo()) {
					case Entero:
						Return._1.concat(
								String.format("[%s](T:%l,P:%l)",
									Resultado.getDefinicion().Nombre,
									Resultado.getEntero(),
									Resultados.get(Resultado.getId()).Entero
									));
						break;
					case Equipo:
						break;
					default:
						break;
					
					}
							
							
					
				}
			}
			for (Resultado Resultado : Porcion.getResultados()) {
				if(!(condicion.CondicionResultado == ""
						|| condicion.CondicionResultado == null)) {
					if (!Resultado.getDefinicion().getNombre()
							.matches(condicion.CondicionResultado))
						continue;
				}
			}
		}
		return null;
	}

	private HashMap<Long, ResultadoPronostico> GenerarResultados(
			List<ResultadoPronostico> resultados) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long cacular(Torneo Torneo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
