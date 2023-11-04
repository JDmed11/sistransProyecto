package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    
    @Id
    private Long numero_habitacion;

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion", referencedColumnName = "id")
    private TipoHabitacion tipo_habitacion;


    public Habitacion() {
    }

    public Habitacion(Long numero_habitacion, TipoHabitacion tipo_habitacion_id) {
        this.numero_habitacion = numero_habitacion;
        this.tipo_habitacion = tipo_habitacion_id;
    }

    public Long getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(Long numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public TipoHabitacion getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(TipoHabitacion tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero_habitacion == null) ? 0 : numero_habitacion.hashCode());
        result = prime * result + ((tipo_habitacion == null) ? 0 : tipo_habitacion.hashCode());
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
        Habitacion other = (Habitacion) obj;
        if (numero_habitacion == null) {
            if (other.numero_habitacion != null)
                return false;
        } else if (!numero_habitacion.equals(other.numero_habitacion))
            return false;
        if (tipo_habitacion == null) {
            if (other.tipo_habitacion != null)
                return false;
        } else if (!tipo_habitacion.equals(other.tipo_habitacion))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Habitacion [numero_habitacion=" + numero_habitacion + ", tipo_habitacion=" + tipo_habitacion + "]";
    }

    



    

    
}
