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
 var GolesAnt=Resultados[Quien].Resultado;
 Resultados[Quien].Resultado=($(t).val()?parseInt($(t).val()):null);;
 
 if(keys.length==3) {
	 ActualizaPasa(Porciones[Po].Partidos[Pa]);
 }
  
};

function ActualizaPasa(Partido){
	
	console.log(JSON.stringify({P: Partido,R:Partido.Resultado }))
	var GA=Partido.Resultados.GA.Resultado;
	var GB=Partido.Resultados.GB.Resultado;
	if(GA==null){
		$('[name="'+Partido.Resultados.Paso.Id+'"]').val("").prop( "disabled", true );
	} else	if(GB==null){
		$('[name="'+Partido.Resultados.Paso.Id+'"]').val("").prop( "disabled", true );
	} else if(GA>GB){
		$('[name="'+Partido.Resultados.Paso.Id+'"]').val(Partido.EquipoA).prop( "disabled", true );
	} else if(GA<GB){
		$('[name="'+Partido.Resultados.Paso.Id+'"]').val(Partido.EquipoB).prop( "disabled", true );
	} else if(GA==GB){
		$('[name="'+Partido.Resultados.Paso.Id+'"]').val("").prop( "disabled", false );
	}
};

function CambioGolGrupo(P,G,Quien,Goles){
	console.log(JSON.stringify({P: P, Quien:Quien,Goles:Goles}))
};