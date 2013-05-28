/**
 *  @author alopez1
 */
function subtituirsubmit(frm) {
	form = $(frm);
	function f(data) {
		$form.replaceWith(data);
	}
	function e(ev) {
		form.replaceWith(ev.responseText);
	}
	
	/* envia lada data al servidor en formato POST */
	var posting = $.post( form.attr("action"), form.serialize()).done(f).fail(e);
	return false;
}

