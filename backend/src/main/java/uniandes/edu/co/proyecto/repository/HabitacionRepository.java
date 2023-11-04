package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    public List<Habitacion> getAll();

    @Query(value = "SELECT * FROM habitaciones WHERE numero_habitacion = :numeroHabitacion", nativeQuery = true)
    public Habitacion getById(@Param("numeroHabitacion") Long numeroHabitacion);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO habitaciones (numero_habitacion, tipo_habitacion ) VALUES (:numeroHabitacion, :tipoHabitacionId)", nativeQuery = true)
    public void insert(@Param("numeroHabitacion") Long numeroHabitacion,
            @Param("tipoHabitacionId") Long tipoHabitacionId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE habitaciones SET tipo_habitacion = :tipoHabitacionId WHERE numero_habitacion = :numeroHabitacion", nativeQuery = true)
    public void update(@Param("numeroHabitacion") Long numeroHabitacion,
            @Param("tipoHabitacionId") Long tipoHabitacionId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM habitaciones WHERE numero_habitacion = :numeroHabitacion", nativeQuery = true)
    public void delete(@Param("numeroHabitacion") Long numeroHabitacion);

}
