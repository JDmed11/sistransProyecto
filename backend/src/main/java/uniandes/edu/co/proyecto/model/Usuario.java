package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 * Clase que representa la tabla usuarios en la base de datos
 * COMPLETA
 */

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @ManyToOne
    @JoinColumn(name = "tipos_usuario_id", referencedColumnName = "id")
    private TipoUsuario tipoUsuario;

    public Usuario() {
        ;
    }

    public Usuario(Long id, String tipoDocumento, String numeroDocumento, String nombre, String correo,
            TipoUsuario tipoUsuario) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tipoDocumento == null) ? 0 : tipoDocumento.hashCode());
        result = prime * result + ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((correo == null) ? 0 : correo.hashCode());
        result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tipoDocumento == null) {
            if (other.tipoDocumento != null)
                return false;
        } else if (!tipoDocumento.equals(other.tipoDocumento))
            return false;
        if (numeroDocumento == null) {
            if (other.numeroDocumento != null)
                return false;
        } else if (!numeroDocumento.equals(other.numeroDocumento))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (correo == null) {
            if (other.correo != null)
                return false;
        } else if (!correo.equals(other.correo))
            return false;
        if (tipoUsuario == null) {
            if (other.tipoUsuario != null)
                return false;
        } else if (!tipoUsuario.equals(other.tipoUsuario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento
                + ", nombre=" + nombre + ", correo=" + correo + ", tipoUsuario=" + tipoUsuario + "]";
    }

    

    



    
}
