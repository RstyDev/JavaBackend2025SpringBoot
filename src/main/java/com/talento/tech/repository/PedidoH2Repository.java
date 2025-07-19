package com.talento.tech.repository;

import com.talento.tech.controller.modelos.pedidos.NewPedidoDTO;
import com.talento.tech.service.pedidos.Item;
import com.talento.tech.service.pedidos.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PedidoH2Repository {
    PedidoRepository repo;
    ItemH2Repository itemRepo;

    public PedidoH2Repository(PedidoRepository repo, ItemH2Repository itemRepo) {
        this.repo = repo;
        this.itemRepo = itemRepo;
    }

    public List<Pedido> findAll() {
        List<Pedido> pedidos = this.repo.findAll();
        for (Pedido pedido : pedidos) {
            List<Item> items = this.itemRepo.findByPedidoId(pedido.getId());
            for (Item item : items) {

            }
            pedido.setItems(items);
        }
        return pedidos;
    }

    public Pedido save(NewPedidoDTO pedido) {
        List<Item> items = this.itemRepo.getIfAvailable(pedido);
        Pedido pedidoGuardado = this.repo.save(new Pedido(items, pedido.usuarioId));
        items.stream().map(item -> {
                    item.setPedido(pedidoGuardado);
                    return this.itemRepo.save(item);
                })
                .collect(Collectors.toUnmodifiableList());
        return pedidoGuardado;
    }

    public List<Pedido> findByUsuarioId(Long usuarioId) {
        return this.repo.findByUsuarioId(usuarioId);
    }
}