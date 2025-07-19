package com.talento.tech.controller;

import com.talento.tech.controller.modelos.pedidos.ItemDTO;
import com.talento.tech.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ItemController {
    ItemService service;
//    @PostMapping("/items")
//    public ResponseEntity<ItemDTO> addItem(@RequestBody @Valid NewItemDTO item){
//        try {
//            return ResponseEntity.ok(this.service.save(item));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> findAll() {
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable Long id) {
        Optional<ItemDTO> item =this.service.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok().body(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
