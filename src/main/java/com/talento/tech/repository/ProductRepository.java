package com.talento.tech.repository;

import com.talento.tech.service.productos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Producto, Long> {
}
