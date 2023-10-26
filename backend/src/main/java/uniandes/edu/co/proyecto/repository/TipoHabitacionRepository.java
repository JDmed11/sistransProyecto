package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {

    @Query(value = "SELECT * FROM tipos_habitacion", nativeQuery = true)
    public List<TipoHabitacion> getAll();

    @Query(value = "SELECT * FROM tipos_habitacion WHERE id = :id", nativeQuery = true)
    public TipoHabitacion getById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tipos_habitacion (id, nombre, capacidad, costo_noche, tiene_jacuzzi, tiene_cocina, tiene_comedor) VALUES (:id, :nombre, :capacidad, :costo_noche, :tiene_jacuzzi, :tiene_cocina, :tiene_comedor)", nativeQuery = true)
    public void insert(@Param("id") Long id, @Param("nombre") String nombre, @Param("capacidad") int capacidad,
            @Param("costo_noche") double costo_noche, @Param("tiene_jacuzzi") String tiene_jacuzzi,
            @Param("tiene_cocina") String tiene_cocina, @Param("tiene_comedor") String tiene_comedor);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tipos_habitacion SET nombre = :nombre, capacidad = :capacidad, costo_noche = :costo_noche, tiene_jacuzzi = :tiene_jacuzzi, tiene_cocina = :tiene_cocina, tiene_comedor = :tiene_comedor WHERE id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("nombre") String nombre, @Param("capacidad") int capacidad,
            @Param("costo_noche") double costo_noche, @Param("tiene_jacuzzi") String tiene_jacuzzi,
            @Param("tiene_cocina") String tiene_cocina, @Param("tiene_comedor") String tiene_comedor);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tipos_habitacion WHERE id = :id", nativeQuery = true)
    public void delete(@Param("id") Long id);
}
