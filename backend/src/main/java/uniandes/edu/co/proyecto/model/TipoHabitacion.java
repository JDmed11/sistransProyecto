package uniandes.edu.co.proyecto.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_habitacion")
public class TipoHabitacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int capacidad;
    private double costo_noche;
    private String tiene_jacuzzi;
    private String tiene_cocina;
    private String tiene_comedor;

    public TipoHabitacion() {
        ;
    }

    public TipoHabitacion(Long id, String nombre, int capacidad, double costo_noche, String tiene_jacuzzi, String tiene_cocina, String tiene_comedor) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.costo_noche = costo_noche;
        this.tiene_jacuzzi = tiene_jacuzzi;
        this.tiene_cocina = tiene_cocina;
        this.tiene_comedor = tiene_comedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getCosto_noche() {
        return costo_noche;
    }

    public void setCosto_noche(double costo_noche) {
        this.costo_noche = costo_noche;
    }

    public String getTiene_jacuzzi() {
        return tiene_jacuzzi;
    }

    public void setTiene_jacuzzi(String tiene_jacuzzi) {
        this.tiene_jacuzzi = tiene_jacuzzi;
    }

    public String getTiene_cocina() {
        return tiene_cocina;
    }

    public void setTiene_cocina(String tiene_cocina) {
        this.tiene_cocina = tiene_cocina;
    }

    public String getTiene_comedor() {
        return tiene_comedor;
    }

    public void setTiene_comedor(String tiene_comedor) {
        this.tiene_comedor = tiene_comedor;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + capacidad;
        long temp;
        temp = Double.doubleToLongBits(costo_noche);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((tiene_jacuzzi == null) ? 0 : tiene_jacuzzi.hashCode());
        result = prime * result + ((tiene_cocina == null) ? 0 : tiene_cocina.hashCode());
        result = prime * result + ((tiene_comedor == null) ? 0 : tiene_comedor.hashCode());
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
        TipoHabitacion other = (TipoHabitacion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (capacidad != other.capacidad)
            return false;
        if (Double.doubleToLongBits(costo_noche) != Double.doubleToLongBits(other.costo_noche))
            return false;
        if (tiene_jacuzzi == null) {
            if (other.tiene_jacuzzi != null)
                return false;
        } else if (!tiene_jacuzzi.equals(other.tiene_jacuzzi))
            return false;
        if (tiene_cocina == null) {
            if (other.tiene_cocina != null)
                return false;
        } else if (!tiene_cocina.equals(other.tiene_cocina))
            return false;
        if (tiene_comedor == null) {
            if (other.tiene_comedor != null)
                return false;
        } else if (!tiene_comedor.equals(other.tiene_comedor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "tipoHabitacion [id=" + id + ", nombre=" + nombre + ", capacidad=" + capacidad + ", costo_noche="
                + costo_noche + ", tiene_jacuzzi=" + tiene_jacuzzi + ", tiene_cocina=" + tiene_cocina
                + ", tiene_comedor=" + tiene_comedor + "]";
    }

    
}
