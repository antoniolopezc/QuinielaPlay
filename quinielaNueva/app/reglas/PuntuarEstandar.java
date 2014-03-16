/**
 * 
 */
package reglas;

import java.util.Map;
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
		// TODO Auto-generated method stub
		return -1;
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
			}
		}
		return 0;
	}

	@Override
	public String IncluirJS() {
		// TODO Auto-generated method stub
		return null;
	}
}
