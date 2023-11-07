/**
CONSULTAS SQL
*/

/**
RF1
*/
"SELECT SUM(saldo) AS recaudo, habitacion FROM reservas_alojamiento
WHERE fecha_entrada BETWEEN :fecha_inicio AND :fecha_fin
AND fecha_salida <= :fecha_fin
GROUP BY habitacion"

/**
RF2
*/
"SELECT s.nombre AS nombre, count(*) AS cantidad FROM consumos_servicios CS INNER JOIN
servicios S ON cs.servicios_id = s.id
WHERE fecha_inicio BETWEEN :fecha_inicio AND :fecha_fin
AND fecha_fin BETWEEN :fecha_inicio AND :fecha_fin
GROUP BY s.nombre
ORDER BY cantidad DESC"

/**
RF3

*/

"SELECT SUM(fecha_salida - fecha_entrada) AS dias, habitacion AS habitacion, ROUND(SUM(fecha_salida - fecha_entrada)/365,4)*100 AS porcentaje
FROM reservas_alojamiento
WHERE fecha_entrada BETWEEN :fecha_inicio AND :fecha_fin 
AND fecha_salida BETWEEN :fecha_inicio AND :fecha_fin
GROUP BY habitacion"

/**
RF5
*/

"SELECT SUM(ra.saldo) AS total, u.nombre AS nombre, u.numero_documento AS documento FROM reservas_alojamiento RA 
INNER JOIN usuarios U ON ra.cliente = u.id
WHERE u.numero_documento = :numero_documento
AND ra.fecha_entrada >= :fecha_inicio AND fecha_salida <= :fecha_fin
GROUP BY u.numero_documento, u.nombre"

/**
RF7
*/

"SELECT SUM(fecha_salida-fecha_entrada) AS dias, sum(saldo) AS gasto, u.nombre AS nombre, u.numero_documento AS documento 
FROM reservas_alojamiento RA INNER JOIN usuarios U ON u.id = ra.cliente
WHERE ra.fecha_entrada >= :fecha_inicio AND fecha_salida <= :fecha_fin
group by u.nombre, u.numero_documento
HAVING SUM(fecha_salida-fecha_entrada) >= 7 AND SUM(saldo) > 15000000"