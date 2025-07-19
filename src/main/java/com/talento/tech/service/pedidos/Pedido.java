package com.talento.tech.service.pedidos;

import com.talento.tech.service.excepciones.InvalidException;
import com.talento.tech.service.excepciones.StockInsuficienteException;
import com.talento.tech.service.productos.Producto;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    private Long usuarioId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itemsPedido;

    public Pedido() {}

    public Pedido(List<Item> itemsPedido, Long usuarioId) {
        this.itemsPedido = itemsPedido;
        this.usuarioId = usuarioId;
    }

    public void agregarProducto(int cantidad, Producto producto) throws StockInsuficienteException {
        boolean found = false;
        for (Item item: this.itemsPedido) {
            if (producto.getId() == item.getProducto().getId()) {
                if (cantidad > (producto.getStock() - item.getCantidad())) {
                    throw new StockInsuficienteException("Cantidad ingresada menor a la cantidad restante");
                } else {
                    found = true;
                    item.agregarCantidad(cantidad);
                }
            }
        }
        if (!found) {
            if (cantidad > producto.getStock()) {
                throw new StockInsuficienteException("Cantidad ingresada menor a la cantidad restante");
            }
            this.itemsPedido.add(new Item(cantidad, producto, this));
        }
    }

    public Long getId(){return this.id;}

    public List<Item> getItems() {
        return this.itemsPedido;
    }

    public double getCostoTotal() {
        double resultado = 0.0;
        for (Item item:this.itemsPedido) {
            resultado += item.getCosto();
        }
        return resultado;
    }

    public void setItems(List<Item> items) {
        this.itemsPedido = items;
    }

    public void confirmarPedido() throws InvalidException {
        for (Item item:this.itemsPedido) {
            item.confirmar();
        }
    }

    @Override
    public String toString() {
        return "\nPedido {" +
                "\n    items = " + itemsPedido +
                "\n    total = "+ this.getCostoTotal() +
                "\n}\n";
    }

    public void mostrarPedido() {
        System.out.println(this.toString());
    }

    public Long getUsuarioId() {
        return this.usuarioId;
    }
}
