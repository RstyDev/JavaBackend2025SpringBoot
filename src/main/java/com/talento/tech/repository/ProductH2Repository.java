package com.talento.tech.repository;

import com.talento.tech.service.productos.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class ProductH2Repository {
    ProductRepository repository;


    public ProductH2Repository(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Producto> findById(Long id) {
        return this.repository.findById(id);
    }

    public Producto save(Producto producto) {
        return this.repository.save(producto);
    }

    public List<Producto> getAll(){
        return this.repository.findAll();
    }

    public List<Producto> getByName(String name){
        return this.repository.findAll().stream().filter(prod->prod.getNombre().contains(name)).collect(Collectors.toUnmodifiableList());
    }

    public void delete(Producto deletedProd) {
        this.repository.delete(deletedProd);
    }
}
