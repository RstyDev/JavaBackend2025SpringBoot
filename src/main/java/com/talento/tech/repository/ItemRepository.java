package com.talento.tech.repository;

import com.talento.tech.service.pedidos.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByPedidoId(Long pedidoId);
}
