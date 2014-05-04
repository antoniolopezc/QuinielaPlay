/**
 *  Actualiza los Equipos Dependientes
 *  
 */

/*
$(".GA").change({Partido:P},CambioGol);
*/


function CambioTiempo(Grupo,Puesto,Nuevo){
	// Caso EquipoA $('script:contains(P,"1A") ~td.Partido-EquipoA')
	$('script:contains(P,"'+Puesto+Grupo+'") ~td.Partido-EquipoA').empty().html(Nuevo);
	$('script:contains(P,"'+Puesto+Grupo+'") ~td.Partido-Resultado > select > option:nth-child(2)').empty().html(Nuevo);
	// Caso EquipoB $('script:contains(","2B") ~td.Partido-EquipoB')
	$('script:contains(\'","'+Puesto+Grupo+'"\') ~td.Partido-EquipoB').empty().html(Nuevo);
	$('script:contains(\'","'+Puesto+Grupo+'"\') ~td.Partido-Resultado > select > option:nth-child(3)').empty().html(Nuevo);
	
}
