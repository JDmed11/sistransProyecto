package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long>{
    
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    public List<Servicio> getAll();

    @Query(value = "SELECT * FROM servicios WHERE id = :id", nativeQuery = true)
    public Servicio findById(@Param("id")long id);

    @Query(value = "INSERT INTO servicios (id, nombre) VALUES (:id, :nombre)", nativeQuery = true)
    public void insert(@Param("id") Long id, @Param("nombre")String nombre);

    @Query(value = "UPDATE servicios SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("nombre")String nombre);

    @Query(value = "DELETE FROM servicios WHERE id = :id", nativeQuery = true)
    public void delete(@Param("id") Long id);
}
