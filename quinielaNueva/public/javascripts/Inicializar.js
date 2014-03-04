/**
 * Funcion que asigna valor
 */

function AsignaValorResultado(Id,Valor) {
	$('[name="'+Id+'"]').val(Valor);
	$('[name="'+Id+'"]').trigger("onchange");
}