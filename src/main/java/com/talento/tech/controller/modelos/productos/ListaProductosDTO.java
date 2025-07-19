package com.talento.tech.controller.modelos.productos;

import lombok.Getter;

import java.util.List;

@Getter
public class ListaProductosDTO {
    private int limit;
    private List<ProductoDTO> products;
    private int skip;
    private int total;
    public ListaProductosDTO(List<ProductoDTO> products) {
        this.limit = 30;
        this.products = products;
        this.skip = 0;
        this.total = products.size();
    }
}
