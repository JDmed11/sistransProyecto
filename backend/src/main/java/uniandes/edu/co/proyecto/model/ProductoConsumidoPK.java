package uniandes.edu.co.proyecto.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProductoConsumidoPK implements Serializable{
    

    @ManyToOne
    @JoinColumn(name = "consumos_productos_id", referencedColumnName = "id")
    private ConsumoProducto consumoProductoId;

    @ManyToOne
    @JoinColumn(name = "productos_id", referencedColumnName = "id")
    private Producto productoId;
    private int cantidad;
    private double costo_total;

    public ProductoConsumidoPK() {
        ;
    }

    public ProductoConsumidoPK(ConsumoProducto consumoProductoId, Producto productoId, int cantidad,
            double costo_total) {
        super();
        this.consumoProductoId = consumoProductoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.costo_total = costo_total;
    }

    public ConsumoProducto getConsumoProductoId() {
        return consumoProductoId;
    }

    public void setConsumoProductoId(ConsumoProducto consumoProductoId) {
        this.consumoProductoId = consumoProductoId;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((consumoProductoId == null) ? 0 : consumoProductoId.hashCode());
        result = prime * result + ((productoId == null) ? 0 : productoId.hashCode());
        result = prime * result + cantidad;
        long temp;
        temp = Double.doubleToLongBits(costo_total);
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
        ProductoConsumidoPK other = (ProductoConsumidoPK) obj;
        if (consumoProductoId == null) {
            if (other.consumoProductoId != null)
                return false;
        } else if (!consumoProductoId.equals(other.consumoProductoId))
            return false;
        if (productoId == null) {
            if (other.productoId != null)
                return false;
        } else if (!productoId.equals(other.productoId))
            return false;
        if (cantidad != other.cantidad)
            return false;
        if (Double.doubleToLongBits(costo_total) != Double.doubleToLongBits(other.costo_total))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProductoConsumidoPK [consumoProductoId=" + consumoProductoId + ", productoId=" + productoId
                + ", cantidad=" + cantidad + ", costo_total=" + costo_total + "]";
    }

    

}
