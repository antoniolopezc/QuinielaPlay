/**
 * Contine todas las clase definidas para la parte local de javscript
 */
function Resultado(Id,Definicion) {
	this.Id=Id;
	this.Resultado=null;
    this.Definicion=Definicion;
}

function Partido(Id,EquipoA,EquipoB){
	this.Id=Id;
	this.EquipoA=EquipoA;
	this.EquipoB=EquipoB;
    this.Resultados=[];

    this.AgregarResultado= function(Id,Definicion) {
    	this.Resultados[Definicion]=new Resultado(Id,Definicion);
    }
    this.GA=function(){
    	try {
    	return this.Resultados.GA.Resultado;
    	} catch(err) {
    		return null;
    	}
    }
    this.GB=function(){
    	try {
    	return this.Resultados.GB.Resultado;
    	} catch(err) {
    		return null;
    	}
    }
    this.Completo=function(){
    	try {
    		return !(this.Resultados.GA.Resultado==null || this.Resultados.GB.Resultado==null); 
        	} catch(err) {
        		return false;
        	}
    }
    this.QuienGana=function(){
    	if(this.Completo()) {
    		if(this.Resultados.GA.Resultado>this.Resultados.GB.Resultado)
    			return this.EquipoA;
    		else if(this.Resultados.GA.Resultado<this.Resultados.GB.Resultado)
    			return this.EquipoB;
    		else
    			return -1;
    	} else {
    		return null
    	}
    }
    
}

function Equipo(Equipo,EquipoId,C) {
	  this.Clasificacion = C;
	  this.Equipo=Equipo;
	  this.EquipoId=EquipoId;
	  this.jg=0;
	  this.je=0;
	  this.jp=0;
	  this.gf=0;
	  this.gc=0;
	  this.gd=function() { return this.gf-this.gc; };
	  this.Pts= function() { return this.jg*3+this.je; };
	  
	  this.jj=function() { return this.jg+this.je+this.jp; };
	  this.Arreglo= function() {
	    return [this.Clasificacion,
	            this.Equipo,
	            this.jj(),
	            this.jg,
	            this.je,
	            this.jp,
	            this.gf,
	            this.gc,
	            this.gd(),
	            this.Pts(),
	           ];
	  };
	  
	  this.AgregarPartido=function(RM,RO) {
	     this.gf+=RM;
	     this.gc+=RO;
	     if(RM>RO) this.jg++;     
	     if(RM==RO) this.je++;
	     if(RM<RO) this.jp++;
	  };
	    
	  this.QuitarPartido=function(RM,RO) {
	     this.gf-=RM;
	     this.gc-=RO;
	     if(RM>RO) this.jg--;     
	     if(RM==RO) this.je--;
	     if(RM<RO) this.jp--;
	  };

	}

function Porcion(Id){
	this.Id=Id;
    this.Resultados=[];
    this.Partidos=[];
    this.Equipos=[];
    this.NEquipos=0;
    this.Posiciones=[];

    this.AgregarResultado= function(Id,Definicion) {
    	this.Resultados[Definicion]=new Resultado(Id,Definicion);
    }
    this.AgregarPartido= function(P,EquipoA,EquipoB) {
    	this.Partidos[P.Id]=P;
    	if(this.Equipos[P.EquipoA]==null) {
    		this.Equipos[P.EquipoA]=new Equipo(EquipoA,P.EquipoA,++this.NEquipos);
    		this.Posiciones[this.NEquipos]=this.Equipos[P.EquipoA];
    	}
    	if(this.Equipos[P.EquipoB]==null) {
    		this.Equipos[P.EquipoB]=new Equipo(EquipoB,P.EquipoB,++this.NEquipos);
    		this.Posiciones[this.NEquipos]=this.Equipos[P.EquipoB];
    	}
    	if(P.Completo()) {
    		//TODO: Para actualizacion pensar si se puede.
    	}
  	
    }
    this.ActualizarPartido=function(P,Quien,Goles){
        var Dif=0;
        var GAnt=P.QuienGana();

        if(P.Completo()) {
          this.Equipos[P.EquipoA].QuitarPartido(P.GA(),P.GB());
          this.Equipos[P.EquipoB].QuitarPartido(P.GB(),P.GA());
        }
        
        switch(Quien){
    	case "A":
           Dif=(P.GA()-Goles>0?1:-1);
           P.Resultados.GA.Resultado=Goles;
           break;
    	case "B":
            Dif=(P.GB()-Goles>0?1:-1);
            P.Resultados.GB.Resultado=Goles;
           break;
        }
        
        var GNue=P.QuienGana();
        
        if(P.Completo()) {
          this.Equipos[P.EquipoA].AgregarPartido(P.GA(),P.GB());
          this.Equipos[P.EquipoB].AgregarPartido(P.GB(),P.GA());
        }
        this.Partidos[P.Id]=P;
        this.ActualizarClasificación(P.EquipoA, P.EquipoB, GAnt, GNue, Quien, Dif);
      };
      
      this.ActualizarClasificación=function(EquipoA,EquipoB,GanoAnt,GanoNue,Quien,Dif){
       var IA=0;
       var IB=0;
       
       switch(GanoNue) {
       case GanoAnt:
          if(GanoNue==null) return; // no cambio nada
          switch(Quien){
    	  case "A":
             IA=Dif;
             IB=-Dif;
             break;
    	  case "B":
             IA=-Dif;
             IB=Dif;
             break;
          }
          break;
       case EquipoA:
          IA=-1;
          IB=1;
          break;
       case EquipoB:
          IA=1;
          IB=-1;
          break;
       case -1:   
          switch(GanoAnt) {
          case EquipoA:
             IA=1;
             IB=-1;
             break;
          case EquipoB:
             IA=-1;
             IB=1;
             break;
          case null:
             IA=-1;
             IB=-1;
             break;
          }
          break;
       case null:
          switch(GanoAnt) {
          case EquipoA:
             IA=-1;
             IB=1;
             break;
          case EquipoB:
             IA=1;
             IB=-1;
             break;
          case null:
             return; // no cambio nada
          };
       } 
       this.MoverPuesto(this.Equipos[EquipoA], IA);
       this.MoverPuesto(this.Equipos[EquipoB], IB);

      };
      this.MoverPuesto=function(E,D) {
       for(var o=E.Clasificacion+D;o>0&&o<=this.NEquipos;o+=D) {
        switch(this.Comparar(E,this.Posiciones[o])) {
              case 0:
              case D:
                E.Clasificacion=o-D;
                this.Posiciones[o-D]=E;
                return true;
              case -D:
            	  this.Posiciones[o].Clasificacion=o-D;
                  this.Posiciones[o-D]=this.Posiciones[o];
                break;
                
        };
       };
       E.Clasificacion=o-D;
       this.Posiciones[o-D]=E;
       return true;
      };
      this.Comparar=function(EA,EB) {
    	    var r=EA.Pts()-EB.Pts();
    	    if(r==0) {
    	      r=EA.gd()-EB.gd();
    	      if(r==0) {
    	        r=(EA.gf-EB.gf);
    	      }
    	    }
    	    return r > 0 ? 1 : (r < 0 ? -1 : 0);
    	  };
}

var Porciones=[];

