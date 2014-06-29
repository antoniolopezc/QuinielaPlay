/**
 * Funcion que asigna valor
 */
 
function AsignaValorResultado(padre,Id,Valor) {
	
	$(padre+' [name="'+Id+'"]').val(Valor);
	$(padre+' [name="'+Id+'"]').trigger("onchange");
}

function DesabilitarSelect(padre,Id) {
	
	$(padre+' select[name="'+Id+'"]').prop( "disabled",true);
	
}