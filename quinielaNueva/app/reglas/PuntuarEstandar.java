/**
 * 
 */
package reglas;

import java.util.ArrayList;

import models.*;
import play.libs.Yaml;
import reglas.PuntuarEstandar.Condicion;

/**
 * @author lopez
 * Esta Regla sirve para Generar un Pronostico en Base al Torneo de la quiniela para todos los resultados.
 * 
 */
public class PuntuarEstandar extends ReglaBase {

	public class Condicion {
		/*
		 * 	String =reg exprsion que se compara con el Nombre de la Porcion
		 */
		String CondicionPorcion;
		/*
		 * 	String =reg exprsion que se compara con el Nombre de la Partido
		 */
		String CondicionPartido;
		/*
		 * 	String =reg exprsion que se compara con el Nombre de la Resultado
		 */
		String CondicionResultado;
		/*
		 * 	String =reg exprsion que se compara con los valores para verificar si suma
		 */
		String CondicionSuma;
		/*
		 * Los puntos a sumar
		 */
		Long Puntos;
	}

	ArrayList<Condicion> Condiciones;

	public PuntuarEstandar(String Parametro) {
		super(Parametro);
		Condiciones=(ArrayList<Condicion>) Yaml.load(Parametro);
	}
	
	@Override
	public long cacular(Pronostico Pronostico) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public long Generar(Pronostico Pronostico) {
		Torneo Torneo=Pronostico.Quiniela.Torneo;
		for(Condicion C: Condiciones) {		
			for(Porcion Porcion: Torneo.Porciones) {
				if(!Porcion.Nombre.matches(C.CondicionPorcion))
					continue;
				for(Partido Partido: Porcion.Partidos) {
					if(!Partido.Nombre.matches(C.CondicionPartido))
						continue;
					for(Resultado Resultado: Partido.Resultados){
						if(!Resultado.Definicion.Nombre.matches(C.CondicionPartido))
							continue;
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
