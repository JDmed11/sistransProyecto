package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.model.Utensilio;

public interface UtensilioRepository extends JpaRepository<Utensilio, Long>{
    
    @Query(value = "SELECT * FROM utensilios", nativeQuery = true)
    public List<Utensilio> getAll();

    @Query(value = "SELECT * FROM utensilios WHERE id = :id", nativeQuery = true)
    public Utensilio getById(@Param("id")Long id);
}
