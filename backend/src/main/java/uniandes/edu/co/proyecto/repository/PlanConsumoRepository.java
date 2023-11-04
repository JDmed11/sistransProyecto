package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.PlanConsumo;

public interface PlanConsumoRepository extends JpaRepository<PlanConsumo, Long> {

    @Query(value = "SELECT * FROM planes_consumo", nativeQuery = true)
    public List<PlanConsumo> getAll();

    @Query(value = "SELECT * FROM planes_consumo WHERE id = :id", nativeQuery = true)
    public PlanConsumo getById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO planes_consumo (id, nombre, descripcion) VALUES (:id, :nombre, :descripcion)", nativeQuery = true)
    public void insert(@Param("id") Long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Transactional
    @Modifying
    @Query(value = "UPDATE planes_consumo SET nombre = :nombre, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM planes_consumo WHERE id = :id", nativeQuery = true)
    public void delete(@Param("id") Long id);
}
