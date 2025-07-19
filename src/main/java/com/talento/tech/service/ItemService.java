package com.talento.tech.service;

import com.talento.tech.controller.modelos.pedidos.ItemDTO;
import com.talento.tech.repository.ItemH2Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {
    ItemH2Repository repo;

//    public ItemDTO save(NewItemDTO item) throws StockInsuficienteException, NotFoundException {
//        return new ItemDTO(this.repo.save(item));
//    }

    public List<ItemDTO> findAll() {
        return this.repo.findAll().stream().map(item -> new ItemDTO(item)).collect(Collectors.toList());
    }

    public Optional<ItemDTO> findById(Long id) {
        return (this.repo.findById(id)).map(item -> new ItemDTO(item));
    }

}
