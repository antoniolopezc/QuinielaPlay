/**
 * 
 */
function PublicarResultado(PId,Id,Valor) {
	$('article#Pronostico-'+PId+' var#Resultado-'+Id).text(Valor);
}