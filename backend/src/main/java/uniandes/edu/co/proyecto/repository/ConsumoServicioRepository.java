package uniandes.edu.co.proyecto.repository;

import java.util.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.ConsumoServicio;

public interface ConsumoServicioRepository extends JpaRepository<ConsumoServicio, Long> {

        public interface consumosHabitacion {
                float getRecaudo();
                Long getHabitacion();
        }

        public interface serviciosMasSolicitados {
                String getNombre();
                Long getCantidad();
        }

        public interface ocupacionHabitacion {
                Long getDias();
                Long getHabitacion();
                double getPorcentaje();
        }

        public interface ConsumoServicioCliente {
                String getNombre();
                String getDocumento();
                double getTotal();
        }

        public interface BuenosClientes{
                String getNombre();
                String getDocumento();
                Long getDias();
                double getGasto();
        }


        @Query(value = "SELECT * FROM consumos_servicios", nativeQuery = true)
        public List<ConsumoServicio> getAll();

        @Query(value = "SELECT * FROM consumos_servicios WHERE id = :id", nativeQuery = true)
        public ConsumoServicio getById(@Param("id") Long id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO consumos_servicios (id, estado, fecha_inicio, fecha_fin, costo, reservas_alojamiento_id, servicios_id, emisor) VALUES (:id, :estado, :fecha_inicio, :fecha_fin, :costo, :reserva_alojamiento, :servicio, :emisor)", nativeQuery = true)
        public void insert(@Param("id") Long id, @Param("estado") String estado,
                        @Param("fecha_inicio") Date fecha_inicio,
                        @Param("fecha_fin") Date fecha_fin, @Param("costo") double costo,
                        @Param("reserva_alojamiento") Long reserva_alojamiento, @Param("servicio") Long servicio,
                        @Param("emisor") Long emisor);

        @Modifying
        @Transactional
        @Query(value = "UPDATE consumos_servicios SET estado = :estado, fecha_inicio = :fecha_inicio, fecha_fin = :fecha_fin, costo = :costo, reservas_alojamiento_id = :reserva_alojamiento, servicios_id = :servicio, emisor = :emisor WHERE id = :id", nativeQuery = true)
        public void update(@Param("id") Long id, @Param("estado") String estado,
                        @Param("fecha_inicio") Date fecha_inicio,
                        @Param("fecha_fin") Date fecha_fin, @Param("costo") double costo,
                        @Param("reserva_alojamiento") Long reserva_alojamiento, @Param("servicio") Long servicio,
                        @Param("emisor") Long emisor);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM consumos_servicios WHERE id = :id", nativeQuery = true)
        public void delete(@Param("id") Long id);


        
        /**permite obtener el total recaudado por los servicios en un rango de fechas
        
                SELECT SUM(costo) as recaudo, RA.habitacion FROM consumos_servicios CS 
                INNER JOIN reservas_alojamiento RA ON CS.reservas_alojamiento_id = RA.id
                WHERE fecha_inicio BETWEEN '01/01/23' AND '31/12/23'
                GROUP BY RA.habitacion;

                LISTOOO
         */
        @Query(value ="select sum(saldo) as recaudo, habitacion from reservas_alojamiento\n" + //
                        "where fecha_entrada between :fecha_inicio and :fecha_fin\n" + //
                        "and fecha_salida <= :fecha_fin\n" + //
                        "group by habitacion", nativeQuery = true)
        public Collection<consumosHabitacion> getRecaudoServicios(@Param("fecha_inicio") String fecha_inicio,
                        @Param("fecha_fin") String fecha_fin);




        /** permite obtener los servicios mas solicitados en orden descendente
         
                SELECT s.nombre, count(*) AS cantidad FROM consumos_servicios CS INNER JOIN
                servicios S ON cs.servicios_id = s.id
                GROUP BY s.nombre
                ORDER BY cantidad DESC;
        */

        @Query(value = "SELECT s.nombre as nombre, count(*) AS cantidad FROM consumos_servicios CS INNER JOIN\n" + //
                        "                servicios S ON cs.servicios_id = s.id\n" + //
                        "                WHERE fecha_inicio BETWEEN :fecha_inicio AND :fecha_fin\n" + //
                        "                AND fecha_fin BETWEEN :fecha_inicio AND :fecha_fin\n" +//
                        "                GROUP BY s.nombre\n" + //
                        "                ORDER BY cantidad DESC", nativeQuery = true)
        public Collection<serviciosMasSolicitados> getServiciosMasSolicitados(@Param("fecha_inicio") String fecha_inicio,
                        @Param("fecha_fin") String fecha_fin);


        /**
         * permite obtener la ocupacion de cada habitacion en un rango de fechas dado

                SELECT SUM(fecha_salida - fecha_entrada) AS dias_ocupado, habitacion,
                ROUND(SUM(fecha_salida - fecha_entrada)/365,4)*100 AS ocupacion_por
                FROM reservas_alojamiento
                WHERE fecha_entrada BETWEEN '01/01/23' AND '31/12/23' 
                AND fecha_salida BETWEEN '01/01/23' AND '31/12/23'
                GROUP BY habitacion;
         */

        @Query(value = "SELECT SUM(fecha_salida - fecha_entrada) AS dias, habitacion as habitacion,\n" + //
                        "                ROUND(SUM(fecha_salida - fecha_entrada)/365,4)*100 AS porcentaje\n" + //
                        "                FROM reservas_alojamiento\n" + //
                        "                WHERE fecha_entrada BETWEEN :fecha_inicio AND :fecha_fin \n" + //
                        "                AND fecha_salida BETWEEN :fecha_inicio AND :fecha_fin\n" + //
                        "                GROUP BY habitacion", nativeQuery = true)
        public Collection<ocupacionHabitacion> getOcupacionHabitacion(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin);



        /** permite MOSTRAR EL CONSUMO EN HOTELANDES POR UN USUARIO DADO, EN UN RANGO DE FECHAS INDICADO.
                SELECT SUM(ra.saldo) FROM reservas_alojamiento RA 
                INNER JOIN usuarios U ON ra.cliente = u.id
                WHERE u.numero_documento = '1000796343'
                AND ra.fecha_entrada >= '01/01/23' AND fecha_salida <= '31/12/23';
         */

        @Query(value = "SELECT SUM(ra.saldo) as total, u.nombre as nombre, u.numero_documento as documento FROM reservas_alojamiento RA \n" + //
                        "                INNER JOIN usuarios U ON ra.cliente = u.id\n" + //
                        "                WHERE u.numero_documento = :numero_documento\n" + //
                        "                AND ra.fecha_entrada >= :fecha_inicio AND fecha_salida <= :fecha_fin\n" + //
                        "                GROUP BY u.numero_documento, u.nombre", nativeQuery = true)
            public Collection<ConsumoServicioCliente> getConsumoServicio(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("numero_documento") String numero_documento);


        /*
         * Encontrar buenos clientes
        select sum(fecha_salida-fecha_entrada) as dias, sum(saldo) as gasto, u.nombre as nombre, u.numero_documento as documento 
        from reservas_alojamiento RA inner join usuarios U
        on u.id = ra.cliente
        group by u.nombre, u.numero_documento
        having sum(fecha_salida-fecha_entrada) >= 7 and sum(saldo) > 1;
         */

        @Query(value = "select sum(fecha_salida-fecha_entrada) as dias, sum(saldo) as gasto, u.nombre as nombre, u.numero_documento as documento \n" + //
                        "from reservas_alojamiento RA inner join usuarios U\n" + //
                        "on u.id = ra.cliente\n" + //
                        "where ra.fecha_entrada >= :fecha_inicio AND fecha_salida <= :fecha_fin\n" + //
                        "group by u.nombre, u.numero_documento\n" + //
                        "having sum(fecha_salida-fecha_entrada) >= 7 and sum(saldo) > 1500", nativeQuery = true)
        public Collection<BuenosClientes> getBuenosClientes(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin);
}

