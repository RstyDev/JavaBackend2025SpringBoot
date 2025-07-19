package com.talento.tech.controller.modelos.pedidos;

import com.talento.tech.service.pedidos.Item;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class NewItemDTO {
        @Positive
        public int cantidad;
        @NotNull
        public Long productId;

        public NewItemDTO(){}

        public NewItemDTO(int cantidad, Long productId){
            this.cantidad = cantidad;
            this.productId = productId;
        }

        public NewItemDTO(Item item) {
            this.cantidad = item.getCantidad();
            this.productId = item.getProducto().getId();
        }

}
