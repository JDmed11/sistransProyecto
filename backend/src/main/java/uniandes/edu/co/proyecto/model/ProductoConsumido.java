package uniandes.edu.co.proyecto.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos_consumidos")
public class ProductoConsumido {
    
    @EmbeddedId
    private ProductoConsumidoPK id;

    public ProductoConsumido() {
        ;
    }

    public ProductoConsumido(ConsumoProducto consumoProductoId, Producto productoId, int cantidad, double costo_total) {
        this.id = new ProductoConsumidoPK(consumoProductoId, productoId, cantidad, costo_total);
    }

    public ProductoConsumidoPK getId() {
        return id;
    }

    public void setId(ProductoConsumidoPK id) {
        this.id = id;
    }
}
