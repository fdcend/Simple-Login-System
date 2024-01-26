package com.fdcend.shop.repository;

import com.fdcend.shop.model.Venta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
    
    List<Venta> findAllByFechaVenta(LocalDate date);
    
}
