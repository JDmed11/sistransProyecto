package uniandes.edu.co.proyecto.model;

import java.sql.Date;

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
import uniandes.edu.co.proyecto.model.Enumeraciones.EstadosConsumosServicios;

@Entity
@Table(name = "consumos_servicios")
public class ConsumoServicio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadosConsumosServicios estado;

    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    @Column(name = "fecha_fin")
    private Date fecha_fin;

    @Column(name = "costo")
    private double costo;

    @ManyToOne
    @JoinColumn(name = "reservas_alojamiento_id", referencedColumnName = "id")
    private ReservaAlojamiento reserva_alojamiento;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "emisor", referencedColumnName = "id")
    private Usuario emisor;

    public ConsumoServicio() {
        ;
    }

    public ConsumoServicio(Long id, EstadosConsumosServicios estado, Date fecha_inicio, Date fecha_fin, double costo,
            ReservaAlojamiento reserva_alojamiento, Servicio servicio, Usuario emisor) {
        this.id = id;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.costo = costo;
        this.reserva_alojamiento = reserva_alojamiento;
        this.servicio = servicio;
        this.emisor = emisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadosConsumosServicios getEstado() {
        return estado;
    }

    public void setEstado(EstadosConsumosServicios estado) {
        this.estado = estado;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ReservaAlojamiento getReserva_alojamiento() {
        return reserva_alojamiento;
    }

    public void setReserva_alojamiento(ReservaAlojamiento reserva_alojamiento) {
        this.reserva_alojamiento = reserva_alojamiento;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((fecha_inicio == null) ? 0 : fecha_inicio.hashCode());
        result = prime * result + ((fecha_fin == null) ? 0 : fecha_fin.hashCode());
        long temp;
        temp = Double.doubleToLongBits(costo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((reserva_alojamiento == null) ? 0 : reserva_alojamiento.hashCode());
        result = prime * result + ((servicio == null) ? 0 : servicio.hashCode());
        result = prime * result + ((emisor == null) ? 0 : emisor.hashCode());
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
        ConsumoServicio other = (ConsumoServicio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (estado != other.estado)
            return false;
        if (fecha_inicio == null) {
            if (other.fecha_inicio != null)
                return false;
        } else if (!fecha_inicio.equals(other.fecha_inicio))
            return false;
        if (fecha_fin == null) {
            if (other.fecha_fin != null)
                return false;
        } else if (!fecha_fin.equals(other.fecha_fin))
            return false;
        if (Double.doubleToLongBits(costo) != Double.doubleToLongBits(other.costo))
            return false;
        if (reserva_alojamiento == null) {
            if (other.reserva_alojamiento != null)
                return false;
        } else if (!reserva_alojamiento.equals(other.reserva_alojamiento))
            return false;
        if (servicio == null) {
            if (other.servicio != null)
                return false;
        } else if (!servicio.equals(other.servicio))
            return false;
        if (emisor == null) {
            if (other.emisor != null)
                return false;
        } else if (!emisor.equals(other.emisor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConsumoServicio [id=" + id + ", estado=" + estado + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
                + fecha_fin + ", costo=" + costo + ", reserva_alojamiento=" + reserva_alojamiento.getId() + ", servicio="
                + servicio.getNombre() + ", emisor=" + emisor.getId() + "]";
    }

    
    
    
}
