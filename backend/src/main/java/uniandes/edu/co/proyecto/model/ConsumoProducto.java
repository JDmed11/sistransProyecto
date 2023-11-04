package uniandes.edu.co.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import uniandes.edu.co.proyecto.model.Enumeraciones.Establecimiento;

@Entity
@Table(name = "consumos_productos")
public class ConsumoProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumos_servicios_id", referencedColumnName = "id")
    private ConsumoServicio consumoServicioId;

    @Column(name = "establecimiento")
    @Enumerated(EnumType.STRING)
    private Establecimiento establecimiento;


    public ConsumoProducto() {
        ;
    }

    public ConsumoProducto(Long id, ConsumoServicio consumoServicioId, Establecimiento establecimiento) {
        this.id = id;
        this.consumoServicioId = consumoServicioId;
        this.establecimiento = establecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsumoServicio getConsumoServicioId() {
        return consumoServicioId;
    }

    public void setConsumoServicioId(ConsumoServicio consumoServicioId) {
        this.consumoServicioId = consumoServicioId;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((consumoServicioId == null) ? 0 : consumoServicioId.hashCode());
        result = prime * result + ((establecimiento == null) ? 0 : establecimiento.hashCode());
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
        ConsumoProducto other = (ConsumoProducto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (consumoServicioId == null) {
            if (other.consumoServicioId != null)
                return false;
        } else if (!consumoServicioId.equals(other.consumoServicioId))
            return false;
        if (establecimiento != other.establecimiento)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConsumoProducto [id=" + id + ", consumoServicioId=" + consumoServicioId + ", establecimiento="
                + establecimiento + "]";
    }

    


    

}
