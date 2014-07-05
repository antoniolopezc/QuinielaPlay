SELECT rp.pronostico_id,p.nombre , count(*) FROM laquiniela.resultado_pronostico rp left join pronostico p on rp.pronostico_id=p.id
where resultado_id in (
 select id from resultado r
  where r.partido_id between 57 and 60
) and entero is null 
  and equipo_id is null
group by rp.pronostico_id,p.nombre