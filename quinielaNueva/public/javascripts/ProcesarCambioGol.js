/**
 *  Controla el campo Pasa
 *  
 */

/*
$(".GA").change({Partido:P},CambioGol);
$(".GB").change({Partido:P},CambioGol);
*/

function CambioGol(Po,Pa,t){
 var Quien=$(t).attr('class');
 var Resultados=Porciones[Po].Partidos[Pa].Resultados;
 var keys = Object.keys(Resultados);
 var Goles=($(t).val()?parseInt($(t).val()):null);
 
 if(keys.length==3) {
	 Resultados[Quien].Resultado=Goles;
	 ActualizaPasa(Porciones[Po].Partidos[Pa]);
 } else {
	 Porciones[Po].ActualizarPartido(Porciones[Po].Partidos[Pa],Quien.charAt(1),Goles);
	 ActualizaPorcion(Porciones[Po],$(t).parents("table").nextAll("table#P-"+Po).children("tbody"));
 }
  
};

function ActualizaPasa(Partido){
	var Q=Partido.QuienGana();

	$('[name="'+Partido.Resultados.Paso.Id+'"]').val((Q==-1?"":Q));
	$('select[name="'+Partido.Resultados.Paso.Id+'"]').prop( "disabled", Q!=-1 );
	
};

function ActualizaPorcion(Porcion, destino){
//	var destino=$("table#P-"+Porcion.Id+" > tbody");
	var fila;
	var Actual;
	var i;
	var Equipo;
	
	destino.empty();
	for( i=1;i<=4;i++){
		Actual=Porcion.Posiciones[i];
		Equipo ='<input class="Resumen-Equipo" type="text" readonly value="'+Actual.Equipo+'">';
		Equipo +='<input class="Resumen-Equipo" type="hidden"  name="'+Porcion.Resultados[i].Id+'" value='+Actual.EquipoId+'>';
			
		fila=$("<tr>");
		fila.append('<td class="Resumen-Celda-Clasificacion">'+Actual.Clasificacion+'</td>');
		fila.append('<td class="Resumen-Celda-EQUIPOS">'+Equipo+'</td>');
		fila.append('<td class="Resumen-Celda-JJ">'+Actual.jj()+'</td>');
		fila.append('<td class="Resumen-Celda-JG">'+Actual.jg+'</td>');
		fila.append('<td class="Resumen-Celda-JE">'+Actual.je+'</td>');
		fila.append('<td class="Resumen-Celda-JP">'+Actual.jp+'</td>');
		fila.append('<td class="Resumen-Celda-GF">'+Actual.gf+'</td>');
		fila.append('<td class="Resumen-Celda-GC">'+Actual.gc+'</td>');
		fila.append('<td class="Resumen-Celda-GD">'+Actual.gd()+'</td>');
		fila.append('<td class="Resumen-Celda-Pts">'+Actual.Pts()+'</td>');
		destino.append(fila);
	};
};