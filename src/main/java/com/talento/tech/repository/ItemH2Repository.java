package com.talento.tech.repository;

import com.talento.tech.controller.modelos.pedidos.NewItemDTO;
import com.talento.tech.controller.modelos.pedidos.NewPedidoDTO;
import com.talento.tech.service.excepciones.NotFoundException;
import com.talento.tech.service.excepciones.StockInsuficienteException;
import com.talento.tech.service.pedidos.Item;
import com.talento.tech.service.pedidos.Pedido;
import com.talento.tech.service.productos.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Repository
public class ItemH2Repository {
    private ItemRepository repo;
    private ProductH2Repository productRepo;
    public Item save(Item item) {

        return this.repo.save(item);
    }

    public Item save(NewItemDTO item, Pedido pedido) {
        Optional<Producto> productResult = productRepo.findById(item.getProductId());
        if (productResult.isEmpty()){
            throw new StockInsuficienteException("No hay stock suficiente del producto "+item.getProductId());
        } else {
            Producto product = productResult.get();
            product.setStock(product.getStock()-item.getCantidad());
            productRepo.save(product);
            Item savingItem = new Item(item.getCantidad(),product, pedido);
            return this.repo.save(savingItem);
        }
    }

//    public Item save(Item item) {
//
//    }

    public Optional<Item> findById(Long id) {
        Optional<Item> item = this.repo.findById(id);
        return this.repo.findById(id);
    }

    public List<Item> findAll() {
        return this.repo.findAll();
    }

    public List<Item> findByPedidoId(Long pedidoId){
        List<Item> items = this.repo.findByPedidoId(pedidoId);
        //TODO check if ok
        return items;
    }

    public List<Item> getIfAvailable(NewPedidoDTO pedido) throws NotFoundException, StockInsuficienteException {
        return pedido.itemsPedido.stream().map(item->{
            Optional<Producto> prod = this.productRepo.findById(item.getProductId());
            if (prod.isEmpty()) throw new NotFoundException("No product found with id: "+item.getProductId());
            if (prod.get().getStock()<item.cantidad) throw new StockInsuficienteException("Not enough stock. \nCount: "+prod.get().getStock()+" \nRequested: "+item.cantidad);
            Producto producto = prod.get();
            producto.setStock(producto.getStock()-item.cantidad);
            return new Item(item.getCantidad(),producto,null);
        }).collect(Collectors.toUnmodifiableList());
    }

}
