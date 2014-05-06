/**
 * Guarda y muestra el mensaje
 * 
 */

function mostrarMensaje(data) {
	$("#Mensaje").html(data);
	$("#Mensaje").dialog("open");
	$("#Mensaje").dialog("close");
	$("input.Guardar").hide();
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
/* Inicializacion */
$(function() {
	$("#Mensaje").dialog({
		autoOpen : false,
		appendTo:"form", 
		title: "Guardado",
		hide: { effect: "explode", delay:1000, duration:500 },
	});
	$("form").submit(enviar);
	$("input.Guardar").hide();
	$("form").change(function(){
		$("input.Guardar").fadeIn();
	})
});