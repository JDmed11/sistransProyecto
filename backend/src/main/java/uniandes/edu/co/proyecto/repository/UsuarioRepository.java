package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.model.Usuario;

/**
 * Clase que representa el repositorio de la tabla usuarios en la base de datos
 * COMPLETA
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        /**
         * Método que retorna todos los usuarios de la base de datos
         * 
         * @return Lista de usuarios
         */
        @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
        public List<Usuario> getAll();

        /**
         * Método que retorna un usuario dado su tipo y número de documento
         * 
         * @param id id del usuario
         * @return Usuario
         */
        @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
        public Usuario getById(@Param("id") Long id);

        @Query(value = "SELECT * FROM usuarios WHERE numero_documento = :numeroDocumento", nativeQuery = true)
        public Usuario getByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO usuarios (id, tipo_documento, numero_documento, nombre, correo, tipos_usuario_id) VALUES (:id, :tipoDocumento, :numeroDocumento, :nombre, :correo, :tipoUsuarioId)", nativeQuery = true)
        public void insert(@Param("id") Long id, @Param("tipoDocumento") String tipoDocumento,
                        @Param("numeroDocumento") String numeroDocumento, @Param("nombre") String nombre,
                        @Param("correo") String correo, @Param("tipoUsuarioId") Long tipoUsuarioId);

        @Transactional
        @Modifying
        @Query(value = "UPDATE usuarios SET tipo_documento = :tipoDocumento, numero_documento = :numeroDocumento, nombre = :nombre, correo = :correo, tipos_usuario_id = :tipoUsuarioId WHERE id = :id", nativeQuery = true)
        public void update(@Param("id") Long id, @Param("tipoDocumento") String tipoDocumento,
                        @Param("numeroDocumento") String numeroDocumento, @Param("nombre") String nombre,
                        @Param("correo") String correo, @Param("tipoUsuarioId") Long tipoUsuarioId);

        @Transactional
        @Modifying
        @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
        public void delete(@Param("id") Long id);

        /*
         * AVANZADAS
         */

        @Query(value = "SELECT usuarios.id, usuarios.tipo_documento, usuarios.numero_documento, usuarios.nombre, usuarios.correo, usuarios.tipos_usuario_id, tipos_usuario.id as TPID, tipos_usuario.nombre as TPNOMBRE"
                        +
                        " FROM usuarios INNER JOIN tipos_usuario ON usuarios.tipos_usuario_id = tipos_usuario.id" +
                        " WHERE tipos_usuario.nombre = :tipoUsuarioNombre", nativeQuery = true)
        public List<Usuario> getByTipo(@Param("tipoUsuarioNombre") String tipoUsuarioNombre);
}
