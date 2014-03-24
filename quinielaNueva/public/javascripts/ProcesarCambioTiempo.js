/**
 *  Actualiza los Equipos Dependientes
 *  
 */

/*
$(".GA").change({Partido:P},CambioGol);
*/
$(function() {$(".TiempoActual").change(CambioTiempo);})


function TipoDePorcion(e) {
	var P=$(e).parent().parent().children("td.Partido-Resultado").children("select.Paso").length;
	return P;
}
function ObtenerIdPartido(e) {
	var P=$(e).parent().parent().children("td.Partido-Id").html();
	return P;
}
function ObtenerQuienPasa(e) {
	var P=$(e).parent().parent().children("td.Partido-Resultado").children("select.Paso").val();
	P=$(e).parent().parent().children("td.Partido-Resultado").children("select.Paso").children("[value="+P+"]").html();
	return P;
}
function SustituirAfectados(e,IdPartido,EquipoFinal) {
	$("td[class^=Partido-EquipoA]:contains('"+IdPartido.substring(1)+"'),td[class^=Partido-EquipoB]:contains('"+IdPartido.substring(1)+"')").html(EquipoFinal);
}
function CambioTiempo(e){
	if($(e.target).val()=="Final") {

		switch(TipoDePorcion(e.target)) {
		case 1: //De tipo Partido
			var IdPartido=ObtenerIdPartido(e.target);
			var EquipoFinal=ObtenerQuienPasa(e.target);
			SustituirAfectados(e.target,IdPartido,EquipoFinal);
			break;
		case 0: // De tipo Grupo
			break;
		}
	}
}