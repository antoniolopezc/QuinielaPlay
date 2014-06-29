/**
 *  Actualiza los Equipos Dependientes
 *  
 */

/*
$(".GA").change({Partido:P},CambioGol);
*/


function CambioGrupo(Grupo,Puesto,Nuevo){
	$('[original="'+Puesto+Grupo+'"]').empty().html(Nuevo);
}

function PasaPartido(Partido,Nuevo,Padre) {
	Padre.find('[original="G'+Partido+'"]').empty().html(Nuevo);
}

function PierdePartido(Partido,Nuevo,Padre) {
// Caso EquipoA  
	Padre.find('[original="P'+Partido+'"]').empty().html(Nuevo);
}

function CambioSelect(Aqui,Partido) 
{
	var Gana;
	var Pierde;
	var Padre=$(Aqui).parents("div[id^='Pronostico']");
	if(Aqui.selectedIndex==1) { 
		Gana = $(Aqui).parent().prevAll(".Partido-EquipoA").html();
	 	Pierde= $(Aqui).parent().prevAll(".Partido-EquipoB").html();
	} else { 
		Gana = $(Aqui).parent().prevAll(".Partido-EquipoB").html();
 		Pierde= $(Aqui).parent().prevAll(".Partido-EquipoA").html();
	}
	PasaPartido(Partido,Gana,Padre);
	PierdePartido(Partido,Pierde,Padre)
}