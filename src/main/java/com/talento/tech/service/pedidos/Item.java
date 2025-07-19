package com.talento.tech.service.pedidos;

import com.talento.tech.controller.modelos.pedidos.ItemDTO;
import com.talento.tech.service.excepciones.InvalidException;
import com.talento.tech.service.productos.Producto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private int cantidad;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    @JoinColumn
    private Pedido pedido;

    public Item(){}

    public Item(int cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
    }

    public Item(@NotNull ItemDTO item) {
        this.cantidad = item.getCantidad();
        this.producto = new Producto(item.getProducto());
    }

    public void agregarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public double getCosto() {
        return this.producto.getPrecio() * this.cantidad;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "\nItem {" +
                "\n    cantidad = " + cantidad +
                ",\n    producto = " + producto +
                "\n}\n";
    }

    public void confirmar() throws InvalidException {
        try {
            this.producto.restarCantidad(this.cantidad);
        } catch (InvalidException e) {
            throw e;
        }
    }
}
