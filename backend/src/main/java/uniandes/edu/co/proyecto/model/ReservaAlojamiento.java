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
import uniandes.edu.co.proyecto.model.Enumeraciones.EstadosReservasAlojamiento;

@Entity
@Table(name = "reservas_alojamiento")
public class ReservaAlojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_entrada")
    private Date fecha_entrada;

    @Column(name = "fecha_salida")
    private Date fecha_salida;

    @Column(name = "acompaniantes")
    private int acompaniantes;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadosReservasAlojamiento estado;

    @ManyToOne
    @JoinColumn(name = "habitacion", referencedColumnName = "numero_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "plan_consumo", referencedColumnName = "id")
    private PlanConsumo plan_consumo;

    @Column(name = "saldo")
    private double saldo;

    public ReservaAlojamiento() {
        ;
    }

    public ReservaAlojamiento(Long id, Date fecha_entrada, Date fecha_salida, int numero_acompaniantes, double saldo_actual, EstadosReservasAlojamiento estado, Habitacion habitacion, PlanConsumo planes_consumo_id) {
        this.id = id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.acompaniantes = numero_acompaniantes;
        this.saldo = saldo_actual;
        this.estado = estado;
        this.habitacion = habitacion;
        this.plan_consumo = planes_consumo_id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getAcompaniantes() {
        return acompaniantes;
    }

    public void setAcompaniantes(int numero_acompaniantes) {
        this.acompaniantes = numero_acompaniantes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo_actual) {
        this.saldo = saldo_actual;
    }

    public EstadosReservasAlojamiento getEstado() {
        return estado;
    }

    public void setEstado(EstadosReservasAlojamiento estado) {
        this.estado = estado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public PlanConsumo getPlan_consumo() {
        return plan_consumo;
    }

    public void setPlan_consumo(PlanConsumo planes_consumo_id) {
        this.plan_consumo = planes_consumo_id;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fecha_entrada == null) ? 0 : fecha_entrada.hashCode());
        result = prime * result + ((fecha_salida == null) ? 0 : fecha_salida.hashCode());
        result = prime * result + acompaniantes;
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((habitacion == null) ? 0 : habitacion.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((plan_consumo == null) ? 0 : plan_consumo.hashCode());
        long temp;
        temp = Double.doubleToLongBits(saldo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        ReservaAlojamiento other = (ReservaAlojamiento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fecha_entrada == null) {
            if (other.fecha_entrada != null)
                return false;
        } else if (!fecha_entrada.equals(other.fecha_entrada))
            return false;
        if (fecha_salida == null) {
            if (other.fecha_salida != null)
                return false;
        } else if (!fecha_salida.equals(other.fecha_salida))
            return false;
        if (acompaniantes != other.acompaniantes)
            return false;
        if (estado != other.estado)
            return false;
        if (habitacion == null) {
            if (other.habitacion != null)
                return false;
        } else if (!habitacion.equals(other.habitacion))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (plan_consumo == null) {
            if (other.plan_consumo != null)
                return false;
        } else if (!plan_consumo.equals(other.plan_consumo))
            return false;
        if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReservaAlojamiento [id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida
                + ", numero_acompaniantes=" + acompaniantes + ", saldo_actual=" + saldo + ", estado="
                + estado + ", habitacion=" + habitacion.getNumero_habitacion() + ", cliente_tipo_documento=" + cliente.getTipoDocumento() + "cliente_numero_documento=" + cliente.getNumeroDocumento() + ", planes_consumo_id="
                + plan_consumo.getNombre() + "]";
    }

    
}
