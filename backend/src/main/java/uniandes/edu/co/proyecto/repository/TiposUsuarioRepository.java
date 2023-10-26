package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.TipoUsuario;

/**
 * Clase que representa el repositorio de tipos de usuario
 * COMPLETA
 */

public interface TiposUsuarioRepository extends JpaRepository<TipoUsuario, Integer>{
    
    /**
     * Retorna todos los tipos de usuario
     * @return Lista de tipos de usuario
     */
    @Query(value = "SELECT * FROM tipos_usuario", nativeQuery = true)
    public List<TipoUsuario> getAll();

    /**
     * Retorna un tipo de usuario dado su id
     * @param id Id del tipo de usuario
     * @return Tipo de usuario
     */
    @Query(value = "SELECT * FROM tipos_usuario WHERE id = :id", nativeQuery = true)
    public TipoUsuario getById(@Param("id") Long id);

    /**
     * Inserta un tipo de usuario en la base de datos
     * @param id Id del tipo de usuario
     * @param nombre Nombre del tipo de usuario
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tipos_usuario (id, nombre) VALUES (:id, :nombre)", nativeQuery = true)
    public void insert(@Param("id") Long id, @Param("nombre") String nombre);

    /**
     * Actualiza un tipo de usuario en la base de datos
     * @param id Id del tipo de usuario
     * @param nombre Nombre del tipo de usuario
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE tipos_usuario SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    public void update(@Param("id") Long id, @Param("nombre") String nombre);

    /**
     * Elimina un tipo de usuario de la base de datos
     * @param id Id del tipo de usuario
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tipos_usuario WHERE id = :id", nativeQuery = true)
    public void delete(@Param("id") Long id);
}
