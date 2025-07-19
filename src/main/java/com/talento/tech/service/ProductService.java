package com.talento.tech.service;

import com.talento.tech.controller.modelos.productos.ProductoDTO;
import com.talento.tech.repository.ProductH2Repository;
import com.talento.tech.service.excepciones.NotFoundException;
import com.talento.tech.service.productos.Producto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductH2Repository repository;

    public ProductService(ProductH2Repository repository) {
        this.repository = repository;
    }

    public ProductoDTO save(ProductoDTO producto) {
        return new ProductoDTO(this.repository.save(new Producto(producto)));
    }

    public List<ProductoDTO> getAll() {
        return this.repository.getAll().stream().map(prod->new ProductoDTO(prod)).collect(Collectors.toList());
    }

    public List<ProductoDTO> getByName(String name) {
        return this.repository.getByName(name).stream().map(producto -> new ProductoDTO(producto)).collect(Collectors.toUnmodifiableList());
    }

    public ProductoDTO update(Long id,@Valid ProductoDTO producto) throws NotFoundException {
        Optional<Producto> productResult = this.repository.findById(id);
        if (productResult.isEmpty()) {
            throw new NotFoundException("Not found product with id "+id);
        }
        Producto updatedProd = productResult.get();
        updatedProd.updateAll(producto);
        return new ProductoDTO(this.repository.save(updatedProd));
    }

    public ProductoDTO delete(Long id) {
        Optional<Producto> productResult = this.repository.findById(id);
        if (productResult.isEmpty()) {
            throw new NotFoundException("Not found product with id "+id);
        }
        Producto deletedProd = productResult.get();
        this.repository.delete(deletedProd);
        return new ProductoDTO(deletedProd);
    }

    public Optional<ProductoDTO> findById(Long id) {
        return this.repository.findById(id).map(prod-> new ProductoDTO(prod));
    }
}
