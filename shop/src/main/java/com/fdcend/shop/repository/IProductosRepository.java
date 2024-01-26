package com.fdcend.shop.repository;

import com.fdcend.shop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductosRepository extends JpaRepository<Producto, Long> {

}
