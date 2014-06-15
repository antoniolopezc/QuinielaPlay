/**
 * 
 */

function ActualizarPuntoPartido(PronosticoId,PartidoId,Valor,Maximo) {
	var P=$("div#PartidoResumen-"+PartidoId+" tr#Pronostico-"+PronosticoId+" > td.Puntos");
	if(P.length==1) {
		var Actual=new String(P.html());
		if(Actual.indexOf("/")>0){ //Habia Valores actualizo
			Act=Actual.split("/",2);
			Valor+=parseInt(Act[0]);
			Maximo+=parseInt(Act[1]);
		}
		P.html(Valor+"/"+Maximo);
	}
}

function ActualizarResultadosPartido(PronosticoId,PartidoId,ResultadoId,Valor) {
	var P=$("div#PartidoResumen-"+PartidoId+" tr#Pronostico-"+PronosticoId+" input[name="+ResultadoId+"]");
	if(P.length==1) {
		P.val(Valor);
	}
} 
function ActualizarResultadosPartidoEquipo(PronosticoId,PartidoId,ResultadoId,Valor) {
	var P=$("div#PartidoResumen-"+PartidoId+" tr#Pronostico-"+PronosticoId+" span#Resultado-"+ResultadoId);
	if(P.length==1) {
		P.html(Valor);
	}
}