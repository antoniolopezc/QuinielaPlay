/**
 * 
 */

function ActualizarPuntoPartido(PronosticoId,PartidoId,Valor,Maximo) {
	var P=$("div#Pronostico-"+PronosticoId+" tr#Partido-"+PartidoId+" > td.Puntos");
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

function ActualizarPuntoPorcion(PronosticoId,PorcionId,Valor,Maximo) {
	var P=$("div#Pronostico-"+PronosticoId+" div#Porcion-"+PorcionId+" >table>thead>tr> th.Puntos");
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

function ActualizarPuntoResultado(PronosticoId,PorcionId,ResultadoId,Valor,Maximo) {
	var P=$("div#Pronostico-"+PronosticoId+" table#P-"+PorcionId+" >tbody>tr#R-"+ResultadoId+" > td.Resumen-Celda-Puntos" );
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

function ActualizarPuntoPronostico(PronosticoId,Tiene,Jugados,Faltan) {
	var STiene=$("span#Puntos-Tiene-"+PronosticoId );
	var SJugados=$("span#Puntos-Jugados-"+PronosticoId );
	var SFaltan=$("span#Puntos-Faltan-"+PronosticoId );
	STiene.html(parseInt(STiene.html())+Tiene)
	SJugados.html(parseInt(SJugados.html())+Jugados)
	SFaltan.html(parseInt(SFaltan.html())+Faltan)
}
