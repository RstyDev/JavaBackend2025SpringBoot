package com.talento.tech.controller.modelos.productos;

import com.talento.tech.service.productos.Producto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductoDTO {
    private Long id;
    @Size(min = 5, message = "The name is too small")
    private String nombre;
    private String descripcion;
    @Positive
    private double precio;
    private int categoriaId;
    private String imagenUrl;
    private int stock;

    public ProductoDTO(){}

    public ProductoDTO(Producto prod) {
        this.id = prod.getId();
        this.nombre = prod.getNombre();
        this.descripcion = prod.getDescripcion();
        this.precio = prod.getPrecio();
        this.categoriaId = prod.getCategoriaId();
        this.imagenUrl = prod.getImagenUrl();
        this.stock = prod.getStock();;
    }

//    @Override
//    public String toString() {
//        return " {" +
//                "'id' = " + id +
//                ",'nombre' = '" + nombre + '\'' +
//                ",'descripcion' = '" + descripcion + '\'' +
//                ",'precio' = " + precio +
//                ",'categoriaId' = " + categoriaId +
//                ",'imagenUrl' = '" + imagenUrl + '\'' +
//                ",'stock' = " + stock +
//                "}";
//    }
}
