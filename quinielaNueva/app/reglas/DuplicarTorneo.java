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
		return -1;
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		Torneo Torneo=Pronostico.Quiniela.Torneo;
		for(Partido Partido: Torneo.Partidos) {
			for(Resultado Resultado: Partido.Resultados){
				ResultadoPronostico RP= new ResultadoPronostico();
				RP.Resultado=Resultado;
				Pronostico.Resultados.add(RP);
			}
		}
		for(Porcion Porcion: Torneo.Porciones) {
			for(Resultado Resultado: Porcion.Resultados){
				ResultadoPronostico RP= new ResultadoPronostico();
				RP.Resultado=Resultado;
				Pronostico.Resultados.add(RP);
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
		// TODO Auto-generated method stub
		return 0;
	}


}
