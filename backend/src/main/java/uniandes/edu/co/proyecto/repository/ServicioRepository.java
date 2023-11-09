package uniandes.edu.co.proyecto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

    // Método para buscar servicios especificos
    default List<Servicio> findWithFilters(
            Double precioMin, Double precioMax,
            LocalDate fechaInicio, LocalDate fechaFin,
            Long empleadoId, Long tipoServicioId, Long categoriaServicioId) {

        // Retorno siempre true 
        Specification<Servicio> spec = (root, query, cb) -> cb.isTrue(cb.literal(true));

        // Filtros para los parametros
        if (precioMin != null && precioMax != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("precio"), precioMin, precioMax));
        }
        if (fechaInicio != null && fechaFin != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("fechaConsumo"), fechaInicio, fechaFin));
        }
        if (empleadoId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("empleado").get("id"), empleadoId));
        }
        if (tipoServicioId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipoServicio").get("id"), tipoServicioId));
        }
        if (categoriaServicioId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("categoriaServicio").get("id"), categoriaServicioId));
        }
        // Ejecutar consulta segun especificación
        return findAll(spec);
    }
}
