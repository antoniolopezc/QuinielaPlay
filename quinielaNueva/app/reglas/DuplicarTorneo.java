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

	/* (non-Javadoc)
	 * @see reglas.ReglaBase#cacular(models.Porcion)
	 */
	@Override
	public long cacular(Porcion Porcion) {
		// TODO Auto-generated method stub
		return -1;
	}

	/* (non-Javadoc)
	 * @see reglas.ReglaBase#Generar(models.Porcion)
	 */
	@Override
	public long Generar(Porcion P) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public long cacular(Pronostico Pronostico) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public long GenerarPronostico(Quiniela Quiniela, Pronostico Pronostico) {
		Torneo Torneo=Quiniela.Torneo;
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
		// TODO Auto-generated method stub
		return null;
	}
}
