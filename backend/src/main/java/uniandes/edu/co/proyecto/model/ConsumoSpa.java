package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumos_spa")
public class ConsumoSpa {

    @Id
    @ManyToOne
    @JoinColumn(name = "consumos_servicios_id", referencedColumnName = "id")
    private ConsumoServicio consumoServicioId;

    @ManyToOne
    @JoinColumn(name = "servicio_spa", referencedColumnName = "id")
    private ServicioSpa servicioSpa;

    public ConsumoSpa() {
        ;
    }

    public ConsumoSpa(ConsumoServicio consumoServicioId, ServicioSpa servicioSpa) {
        this.consumoServicioId = consumoServicioId;
        this.servicioSpa = servicioSpa;
    }

    public ConsumoServicio getConsumoServicioId() {
        return consumoServicioId;
    }

    public void setConsumoServicioId(ConsumoServicio consumoServicioId) {
        this.consumoServicioId = consumoServicioId;
    }

    public ServicioSpa getServicioSpa() {
        return servicioSpa;
    }

    public void setServicioSpa(ServicioSpa servicioSpa) {
        this.servicioSpa = servicioSpa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumoServicioId == null) ? 0 : consumoServicioId.hashCode());
        result = prime * result + ((servicioSpa == null) ? 0 : servicioSpa.hashCode());
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
        ConsumoSpa other = (ConsumoSpa) obj;
        if (consumoServicioId == null) {
            if (other.consumoServicioId != null)
                return false;
        } else if (!consumoServicioId.equals(other.consumoServicioId))
            return false;
        if (servicioSpa == null) {
            if (other.servicioSpa != null)
                return false;
        } else if (!servicioSpa.equals(other.servicioSpa))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConsumoSpa [consumoServicioId=" + consumoServicioId.getId() + ", servicioSpa=" + servicioSpa.getNombre() + "]";
    }

    
    
}
