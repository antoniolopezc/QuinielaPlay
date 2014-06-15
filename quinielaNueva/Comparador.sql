select rp1.pronostico_id,rp2.pronostico_id,count(*) total, sum(
			CASE WHEN ( (rp1.entero=rp2.entero and rp1.equipo_id is null) 
     OR (rp1.equipo_id = rp2.equipo_id and rp1.entero is null)) then 1 else 0 end) iguales,
	sum(
			CASE WHEN ( (rp1.entero=rp2.entero and rp1.equipo_id is null) 
     OR (rp1.equipo_id = rp2.equipo_id and rp1.entero is null)) then 1 else 0 end) / count(*) fracion
from resultado_pronostico as rp1, resultado_pronostico as rp2
where rp1.pronostico_id<rp2.pronostico_id
 and  rp1.resultado_id=rp2.resultado_id
 and rp1.resultado_id not in ( select id from resultado as r where r.estado = 2)
group by rp1.pronostico_id,rp2.pronostico_id
