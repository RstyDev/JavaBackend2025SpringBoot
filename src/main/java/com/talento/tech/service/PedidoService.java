package com.talento.tech.service;

import com.talento.tech.controller.modelos.pedidos.NewPedidoDTO;
import com.talento.tech.controller.modelos.pedidos.PedidoDTO;
import com.talento.tech.repository.PedidoH2Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    PedidoH2Repository repo;

    public PedidoService(PedidoH2Repository repo){ this.repo = repo; }

    public List<PedidoDTO> findAll() {return this.repo.findAll().stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toUnmodifiableList());}

    public PedidoDTO save(NewPedidoDTO pedido) {
        return new PedidoDTO(this.repo.save(pedido));
    }

    public List<PedidoDTO> findByUsuarioId(Long usuarioId) {
        return this.repo.findByUsuarioId(usuarioId).stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toUnmodifiableList());
    }
}
