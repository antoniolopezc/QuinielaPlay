/**
 * Guarda y muestra el mensaje
 * 
 */

function mostrarMensaje(data) {
	$("#Mensaje").html(data);
	$("#Mensaje").dialog("open");
	
};
function enviar(e) {
	e.preventDefault();
	$.ajax({
		type : "POST",
		/*
		 * controllers.routes.Pronostico.guardar() esta es la accion no se si
		 * vale la pena implementar javascript route
		 */
		url : "/Pronostico/guardar",
		data : $("form").serialize(),
		success : mostrarMensaje
	});
};
