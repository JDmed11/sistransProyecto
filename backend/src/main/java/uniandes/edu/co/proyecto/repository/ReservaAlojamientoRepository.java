package uniandes.edu.co.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.ReservaAlojamiento;

public interface ReservaAlojamientoRepository extends JpaRepository<ReservaAlojamiento, Long> {

        @Query(value = "SELECT * FROM reservas_alojamiento", nativeQuery = true)
        public List<ReservaAlojamiento> getAll();

        @Query(value = "SELECT * FROM reservas_alojamiento WHERE id = :id", nativeQuery = true)
        public ReservaAlojamiento getById(@Param("id") Long id);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO reservas_alojamiento (id, fecha_entrada, fecha_salida, acompaniantes, estado, habitacion, plan_consumo, cliente, saldo) VALUES (:id, :fecha_entrada, :fecha_salida, :acompaniantes, :estado, :numero_habitacion, :plan_consumo, :cliente, 0)", nativeQuery = true)
        public void insert(@Param("id") Long id, @Param("fecha_entrada") Date fecha_entrada,
                @Param("fecha_salida") Date fecha_salida, @Param("acompaniantes") int acompaniantes,
                @Param("estado") String estado, @Param("numero_habitacion") Long numero_habitacion,
                @Param("plan_consumo") Long plan_consumo, @Param("cliente") Long cliente);

        @Transactional
        @Modifying
        @Query(value = "UPDATE reservas_alojamiento SET fecha_entrada = :fecha_entrada, fecha_salida = :fecha_salida, acompaniantes = :acompaniantes, estado = :estado, habitacion = :numero_habitacion, plan_consumo = :plan_consumo, saldo = :saldo WHERE id = :id", nativeQuery = true)
        public void update(@Param("id") Long id, @Param("fecha_entrada") Date fecha_entrada,
                @Param("fecha_salida") Date fecha_salida, @Param("acompaniantes") int acompaniantes,
                @Param("estado") String estado, @Param("numero_habitacion") Long numero_habitacion,
                @Param("plan_consumo") Long plan_consumo, @Param("saldo") double saldo);

        @Transactional
        @Modifying
        @Query(value = "DELETE FROM reservas_alojamiento WHERE id = :id", nativeQuery = true)
        public void delete(@Param("id") Long id);

        @Query(value = "SELECT * FROM reservas_alojamiento WHERE fecha_entrada >= :fecha_entrada AND fecha_salida <= :fecha_salida AND habitacion = :habitacion", nativeQuery = true)
        public List<ReservaAlojamiento> getReservasHabitacion(@Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida, @Param("habitacion") Long habitacion);

}

