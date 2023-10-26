package uniandes.edu.co.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.ConsumoServicio;

public interface ConsumoServicioRepository extends JpaRepository<ConsumoServicio, Long> {

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

        /*
         * permite obtener el total recaudado por los servicios en un rango de fechas
         * select sum(costo) as recaudo from consumos_servicios where fecha_inicio
         * between '01/01/23' and '31/12/23';
         * Permite obtener lo recaudado por los servicios entre dos fechas
         */

        // permite obtener los servicios mas solicitados en orden descendente
        // SELECT s.nombre, count(*) AS cantidad FROM consumos_servicios CS INNER JOIN
        // servicios S
        // ON cs.servicios_id = s.id
        // GROUP BY s.nombre
        // ORDER BY cantidad DESC;

        /**
         * permite obtener la ocupacion de cada habitacion en un rango de fechas dado
         * 
         * select sum(fecha_salida - fecha_entrada) as dias_ocupado, habitacion,
         * round(sum(fecha_salida - fecha_entrada)/365,2) as ocupacion
         * from reservas_alojamiento
         * where fecha_entrada between '01/01/23' and '31/12/23' and fecha_salida
         * between '01/01/23' and '31/12/23'
         * group by habitacion;
         */
}
