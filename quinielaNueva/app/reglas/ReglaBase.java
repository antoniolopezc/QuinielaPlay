/**
 * 
 */
package reglas;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import models.*;

/**
 * @author alopez1
 *
 */
public abstract class ReglaBase {
	
	
	/*
	 * Crea la regla por primera vez basado en los parametros que se les pase que se encuentran en formato YAML.
	 */
	public ReglaBase(String Parametro) {
	}
	
	/*Debe realizar los caculos necesario y 
	 * devolver los puntos de diferencia entre las dos quienielas
	 * */
	public abstract Long comparar(Pronostico Pronostico, Pronostico Pronostico2);
	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular( Pronostico Pronostico);
	
	/*Debe realizar los caculos necesario y actualizar los Resultados Pertinentes
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long cacular(Torneo Torneo);
	
	/*Genera los resultados necesario para su futuro Caculo para un Pronostico 
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract long Generar( Pronostico Pronostico);
	
	/*Genera los resultados necesario para su futuro Caculo para un Pronostico 
	 * devuelve un posible valor de error o 0 si esta todo correcto
	 * */
	public abstract String IncluirJS();
	
	public final static Long comparar(Regla Regla,Pronostico Pronostico, Pronostico Pronostico2){
		try {
			Class<?> R=Class.forName(Regla.getClase());
			Constructor<?> C=R.getConstructor(String.class);
			reglas.ReglaBase O=(ReglaBase) C.newInstance(Regla.getParametros());
			return O.comparar(Pronostico,Pronostico2);  
		} catch (IllegalAccessException  | IllegalArgumentException
				| InstantiationException | ClassNotFoundException  
				| SecurityException      | NoSuchMethodException 
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static long cacular(Regla Regla,Pronostico Pronostico){
		try {
			Class<?> R=Class.forName(Regla.getClase());
			Constructor<?> C=R.getConstructor(String.class);
			reglas.ReglaBase O=(ReglaBase) C.newInstance(Regla.getParametros());
			return O.cacular(Pronostico);  
		} catch (IllegalAccessException  | IllegalArgumentException
				| InstantiationException | ClassNotFoundException  
				| SecurityException      | NoSuchMethodException 
				| InvocationTargetException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public final static long cacular(Regla Regla,Torneo Torneo){
		try {
			Class<?> R=Class.forName(Regla.getClase());
			Constructor<?> C=R.getConstructor(String.class);
			reglas.ReglaBase O=(ReglaBase) C.newInstance(Regla.getParametros());
			return O.cacular(Torneo);  
		} catch (IllegalAccessException  | IllegalArgumentException
				| InstantiationException | ClassNotFoundException  
				| SecurityException      | NoSuchMethodException 
				| InvocationTargetException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public final static long generar(Regla Regla,Pronostico Pronostico){
		try {
			Class<?> R=Class.forName(Regla.getClase());
			Constructor<?> C=R.getConstructor(String.class);
			reglas.ReglaBase O=(ReglaBase) C.newInstance(Regla.getParametros());
			return O.Generar(Pronostico);  
		} catch (IllegalAccessException  | IllegalArgumentException
				| InstantiationException | ClassNotFoundException  
				| SecurityException      | NoSuchMethodException 
				| InvocationTargetException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public final static String IncluirJS(Regla Regla){
		try {
			Class<?> R=Class.forName(Regla.getClase());
			Constructor<?> C=R.getConstructor(String.class);
			reglas.ReglaBase O=(ReglaBase) C.newInstance(Regla.getParametros());
			return O.IncluirJS();  
		} catch (IllegalAccessException  | IllegalArgumentException
				| InstantiationException | ClassNotFoundException  
				| SecurityException      | NoSuchMethodException 
				| InvocationTargetException e) {
			e.printStackTrace();
			return "";
		}
	}
}
