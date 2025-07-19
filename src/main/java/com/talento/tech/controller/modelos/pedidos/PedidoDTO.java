package com.talento.tech.controller.modelos.pedidos;

import com.talento.tech.service.pedidos.Pedido;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PedidoDTO {
    Long id;
    Long usuarioId;
    List<ItemDTO> items;
    public PedidoDTO(){}
    public PedidoDTO(List<ItemDTO> items){ this.items = items; }



    public PedidoDTO(Pedido pedido) {
        this.usuarioId = pedido.getUsuarioId();
        this.id = pedido.getId();
        this.items = pedido.getItems().stream().map(item -> new ItemDTO(item)).collect(Collectors.toUnmodifiableList());
    }
}
