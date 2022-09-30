package com.ecommerce.springecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.springecommerce.entity.Producto;

// Esta interface sirve para aplicar los métodos CRUD

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}
