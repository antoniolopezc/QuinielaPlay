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

function PasaPartido(Partido,Nuevo) {
	$('[original="G'+Partido+'"]').empty().html(Nuevo);
}

function PierdePartido(Partido,Nuevo) {
// Caso EquipoA  
	$('[original="P'+Partido+'"]').empty().html(Nuevo);
}

function CambioSelect(Aqui,Partido) 
{
	var Nuevo=Aqui.options[Aqui.selectedIndex].innerHTML;
	PasaPartido(Partido,Nuevo);
	if(Aqui.selectedIndex==1) 
		Nuevo=Aqui.options[2].innerHTML;
	else 
		Nuevo=Aqui.options[1].innerHTML;
	PierdePartido(Partido,Nuevo)
}