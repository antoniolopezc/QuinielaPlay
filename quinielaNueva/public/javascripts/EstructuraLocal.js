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
}

function Porcion(Id){
	this.Id=Id;
    this.Resultados=[];
    this.Partidos=[];

    this.AgregarResultado= function(Id,Definicion) {
    	this.Resultados[Id]=new Resultado(Id,Definicion);
    }
    this.AgregarPartido= function(P) {
    	this.Partidos[P.Id]=P;
    }
}

var Porciones=[];

