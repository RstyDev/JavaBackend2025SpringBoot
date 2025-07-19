package com.talento.tech.controller.modelos.pedidos;

import com.talento.tech.controller.modelos.productos.ProductoDTO;
import com.talento.tech.service.pedidos.Item;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ItemDTO {
    @Positive
    private int cantidad;
    private ProductoDTO producto;

    public ItemDTO(){}

    public ItemDTO(Item item) {
        this.cantidad = item.getCantidad();
        this.producto = new ProductoDTO(item.getProducto());
    }
}
