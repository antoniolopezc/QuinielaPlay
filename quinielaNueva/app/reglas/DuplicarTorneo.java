/**
 * 
 */
package reglas;

import models.*;

/**
 * @author lopez
 * Esta Regla sirve para Generar un Pronostico en Base al Torneo de la quiniela para todos los resultados.
 * 
 */
public class DuplicarTorneo extends ReglaBase {


	public DuplicarTorneo(String Parametro) {
		super(Parametro);
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		return 0;
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		Torneo Torneo=Pronostico.getQuiniela().getTorneo();
		for(Partido Partido: Torneo.getPartidos()) {
			for(Resultado Resultado: Partido.getResultados()){
				ResultadoPronostico RP= new ResultadoPronostico();
				RP.setResultado(Resultado);
				Pronostico.getResultados().add(RP);
			}
		}
		for(Porcion Porcion: Torneo.getPorciones()) {
			for(Resultado Resultado: Porcion.getResultados()){
				ResultadoPronostico RP= new ResultadoPronostico();
				RP.setResultado(Resultado);
				Pronostico.getResultados().add(RP);
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

	@Override
	public Long comparar(Pronostico Pronostico, Pronostico Pronostico2) {
		return new Long(0);
	}


}
