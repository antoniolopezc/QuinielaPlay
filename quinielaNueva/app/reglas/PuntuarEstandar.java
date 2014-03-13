/**
 * 
 */
package reglas;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import models.*;
import play.libs.Classpath;

import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.*;

/**
 * @author lopez Esta Regla sirve para Generar un Pronostico en Base al Torneo
 *         de la quiniela para todos los resultados.
 * 
 */
public class PuntuarEstandar extends ReglaBase {

	Condicion Condiciones[];

	public PuntuarEstandar(String Parametro) {
		super(Parametro);
		Yaml y = new org.yaml.snakeyaml.Yaml(new CustomClassLoaderConstructor(
				play.Play.application().classloader()));
		ArrayList<?> Temp = (ArrayList<?>) y.load(Parametro);
		Condiciones = new Condicion[Temp.size()];
		Condiciones = Temp.toArray(Condiciones);
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		for (Integer i = 0; i < Condiciones.length; i++) {
			Condicion C = Condiciones[i];
			for (Porcion Porcion : Pronostico.getQuiniela().getTorneo()
					.getPorciones()) {
				if (!Porcion.getNombre().matches(C.CondicionPorcion))
					continue;
				if (C.CondicionPartido == "" || C.CondicionPartido == null) {
					Pronostico.Puntos.add(new Punto(i.toString(), null, null,
							Porcion));
					continue;
				}
				for (Partido Partido : Porcion.getPartidos()) {
					if (!Partido.getNombre().matches(C.CondicionPartido))
						continue;
					if (C.CondicionResultado == ""
							|| C.CondicionResultado == null) {
						Pronostico.Puntos.add(new Punto(i.toString(), null,
								Partido, Porcion));
						continue;
					}

					for (Resultado Resultado : Partido.getResultados()) {
						if (!Resultado.getDefinicion().getNombre()
								.matches(C.CondicionResultado))
							continue;
						Pronostico.Puntos.add(new Punto(i.toString(),
								Resultado, Partido, Porcion));
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
