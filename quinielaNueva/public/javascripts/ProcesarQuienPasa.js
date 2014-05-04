/**
 *  Actualiza los Equipos Dependientes
 *  
 */

/*
$(".GA").change({Partido:P},CambioGol);
*/


function CambioGrupo(Grupo,Puesto,Nuevo){
	// Caso EquipoA $('script:contains(P,"1A") ~td.Partido-EquipoA')
	$('script:contains(P,"'+Puesto+Grupo+'") ~td.Partido-EquipoA').empty().html(Nuevo);
	$('script:contains(P,"'+Puesto+Grupo+'") ~td.Partido-Resultado > select > option:nth-child(2)').empty().html(Nuevo);
	// Caso EquipoB $('script:contains(","2B") ~td.Partido-EquipoB')
	$('script:contains(\'","'+Puesto+Grupo+'"\') ~td.Partido-EquipoB').empty().html(Nuevo);
	$('script:contains(\'","'+Puesto+Grupo+'"\') ~td.Partido-Resultado > select > option:nth-child(3)').empty().html(Nuevo);
	
}

function PasaPartido(Partido,Nuevo) {
	// Caso EquipoA Gana 
	$('script:contains(P,"Gan '+Partido+'") ~td.Partido-EquipoA').empty().html(Nuevo);
	$('script:contains(P,"Gan '+Partido+'") ~td.Partido-Resultado > select > option:nth-child(2)').empty().html(Nuevo);
	// Caso EquipoB Gana
	$('script:contains(\'","Gan '+Partido+'"\') ~td.Partido-EquipoB').empty().html(Nuevo);
	$('script:contains(\'","Gan '+Partido+'"\') ~td.Partido-Resultado > select > option:nth-child(3)').empty().html(Nuevo);

	// Caso EquipoA Pierde 
	$('script:contains(P,"PER '+Partido+'") ~td.Partido-EquipoA').empty().html(Nuevo);
	$('script:contains(P,"PER '+Partido+'") ~td.Partido-Resultado > select > option:nth-child(2)').empty().html(Nuevo);
	// Caso EquipoB Pierde
	$('script:contains(\'","PER '+Partido+'"\') ~td.Partido-EquipoB').empty().html(Nuevo);
	$('script:contains(\'","PER '+Partido+'"\') ~td.Partido-Resultado > select > option:nth-child(3)').empty().html(Nuevo);
	
}

function CambioSelect(Aqui,Partido) 
{
	var Nuevo=Aqui.options[Aqui.selectedIndex].innerHTML;
	PasaPartido(Partido,Nuevo);
}