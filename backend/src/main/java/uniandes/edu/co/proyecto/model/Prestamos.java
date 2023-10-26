package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamos")
public class Prestamos {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "consumo_servicio_id", referencedColumnName = "id")
    private ConsumoServicio consumoServicio;

    @ManyToOne
    @JoinColumn(name = "utensilio", referencedColumnName = "id")
    private Utensilio utensilio;

    public Prestamos() {
        ;
    }

    public Prestamos(ConsumoServicio consumoServicio, Utensilio utensilio) {
        this.consumoServicio = consumoServicio;
        this.utensilio = utensilio;
    }

    public ConsumoServicio getConsumoServicio() {
        return consumoServicio;
    }

    public void setConsumoServicio(ConsumoServicio consumoServicio) {
        this.consumoServicio = consumoServicio;
    }

    public Utensilio getUtensilio() {
        return utensilio;
    }

    public void setUtensilio(Utensilio utensilio) {
        this.utensilio = utensilio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumoServicio == null) ? 0 : consumoServicio.hashCode());
        result = prime * result + ((utensilio == null) ? 0 : utensilio.hashCode());
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
        Prestamos other = (Prestamos) obj;
        if (consumoServicio == null) {
            if (other.consumoServicio != null)
                return false;
        } else if (!consumoServicio.equals(other.consumoServicio))
            return false;
        if (utensilio == null) {
            if (other.utensilio != null)
                return false;
        } else if (!utensilio.equals(other.utensilio))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Prestamos [consumoServicio=" + consumoServicio.getId() + ", utensilio=" + utensilio.getNombre() + "]";
    }

    

}
