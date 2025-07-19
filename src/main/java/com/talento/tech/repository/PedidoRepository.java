package com.talento.tech.repository;

import com.talento.tech.service.pedidos.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    public List<Pedido> findByUsuarioId(Long usuarioId);
}
