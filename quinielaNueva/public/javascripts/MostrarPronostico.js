/**
 * Muestra los Pronosticos en los Resumenes
 *  
 */


  function MostrarPronostico(id,nombre) {
	  var num_tabs = 0;
	  var a=$("a[href='#Pronostico" + id + "']");
	  if(a.length==0){
		num_tabs = $("#tabs ul li").length;
        $("#tabs ul").append(
            "<li><a href='#Pronostico" + id + "'>"+nombre+"</a></li>"
        );
  		$("#tabs").append(
            "<article id='Pronostico" + id + "'></article>"
        );
  		$("#Pronostico" + id).load("/Pronostico/Listar/"+id);
        $("#tabs").tabs("refresh");
	  } else {
		  num_tabs = a.parent().prevAll().length; 
	  }
        $("#tabs").tabs({ active: num_tabs });
        
  }