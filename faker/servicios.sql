INSERT INTO servicios(id, nombre) VALUES (1, 'gimnasio');
INSERT INTO servicios(id, nombre) VALUES (2, 'piscina');
INSERT INTO servicios(id, nombre) VALUES (3, 'internet');
INSERT INTO servicios(id, nombre) VALUES (4, 'spa');
INSERT INTO servicios(id, nombre) VALUES (5, 'lavanderia');
INSERT INTO servicios(id, nombre) VALUES (6, 'bar');
INSERT INTO servicios(id, nombre) VALUES (7, 'restaurante');
INSERT INTO servicios(id, nombre) VALUES (8, 'tienda');
INSERT INTO servicios(id, nombre) VALUES (9, 'market');
INSERT INTO servicios(id, nombre) VALUES (10, 'salon');
INSERT INTO servicios(id, nombre) VALUES (11, 'prestamo');

INSERT INTO consumos_servicios(id, estado, fecha_inicio, fecha_fin, costo, reservas_alojamiento_id, servicios_id, emisor) 

I hace these insert:
INSERT INTO consumos_servicios(id, estado, fecha_inicio, fecha_fin, costo, reservas_alojamiento_id, servicios_id, emisor)

i need to replicate this 7500 times with the following parameters:
1. id is a number between 1 and 7500
2.estado is between [reservado, consumido]
3. reservas_alojamiento_id is a random number between 1 and 7500 
4. servicios_id is a random number between 1 and 11 
5. emisor is a number between 14 and 7500
6. fecha_inicio is a random date between 2020-01-01 and 2023-12-31 and cant be greater than fecha_fin and 30 days less than fecha_fin
7. fecha_fin is a random date between 2020-01-01 and 2023-12-31
7. costo is a random number between 0 and 1000000 (1 million)
8. for each insert generate and update the table reservas_alojamiento and update the field saldo with the sum of the field costo of the table consumos_servicios where the reservas_alojamiento_id is the same
