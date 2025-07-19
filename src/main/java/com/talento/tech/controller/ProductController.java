package com.talento.tech.controller;

import com.talento.tech.controller.modelos.productos.ListaProductosDTO;
import com.talento.tech.controller.modelos.productos.ProductoDTO;
import com.talento.tech.service.ProductService;
import com.talento.tech.service.excepciones.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> addProduct(@RequestBody @Valid ProductoDTO producto) {
        return ResponseEntity.ok().body(this.service.save(producto));
    }

    @GetMapping("/productos")
    public ResponseEntity<ListaProductosDTO> getAll(@RequestParam("name") String name) {
        if (name != null) {
            List<ProductoDTO> prods = this.service.getByName(name);
            return ResponseEntity.ok().body(new ListaProductosDTO(prods));
        }
        return ResponseEntity.ok().body(new ListaProductosDTO(this.service.getAll()));
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> getByName(@PathVariable Long id) {
        Optional<ProductoDTO> producto = this.service.findById(id);
        return (producto.isPresent()?ResponseEntity.ok().body(producto.get()):ResponseEntity.notFound().build());
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> edit(@PathVariable Long id, @RequestBody @Valid ProductoDTO producto) {
        try {
            return ResponseEntity.ok().body(this.service.update(id,producto));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.delete(id));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
