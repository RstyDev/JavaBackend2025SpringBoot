package com.talento.tech.service.productos;

import com.talento.tech.controller.modelos.productos.ProductoDTO;
import com.talento.tech.service.excepciones.InvalidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Producto {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    @Column(name = "categoria_id")
    private int categoriaId;
    @Column(name = "imagen_url")
    private String imagenUrl;
    private int stock;

    public Producto(){}

    public Producto(String nombre, String descripcion, double precio, int categoriaId, String imagenUrl, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.imagenUrl= imagenUrl;
        this.stock = stock;
    }

    public Producto (@NotNull ProductoDTO producto){
        if (null!=producto.getId()) {
            this.id = producto.getId();
        }
        this.stock = producto.getStock();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.descripcion = producto.getDescripcion();
        this.categoriaId = producto.getCategoriaId();
        this.imagenUrl = producto.getImagenUrl();
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) throws InvalidException {
        if (stock < 0 ){
            throw new InvalidException("El stock no puede ser menor a 0");
        }
        this.stock = stock;
    }

    public void restarCantidad(int stock) throws InvalidException {
        if (stock < this.stock) {
            throw new InvalidException("El stock no puede ser menor a 0");
        }
        this.stock -= stock;
    }

    public void updateAll(ProductoDTO producto) {
        this.nombre = producto.getNombre();
        this.imagenUrl = producto.getImagenUrl();
        this.categoriaId = producto.getCategoriaId();
        this.descripcion = producto.getDescripcion();
        this.stock = producto.getStock();
        this.precio = producto.getPrecio();
    }

    @Override
    public String toString() {
        return "\nProducto {" +
                "\n    id = " + id +
                ",\n    nombre = '" + nombre + '\'' +
                ",\n    descripcion = '" + descripcion + '\'' +
                ",\n    precio = " + precio +
                ",\n    categoriaId = " + categoriaId +
                ",\n    imagenUrl = '" + imagenUrl + '\'' +
                ",\n    stock = " + stock +
                "\n}\n";
    }

}
