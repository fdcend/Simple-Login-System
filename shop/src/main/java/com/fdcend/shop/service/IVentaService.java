package com.fdcend.shop.service;

import com.fdcend.shop.model.Cliente;
import com.fdcend.shop.model.Producto;
import com.fdcend.shop.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    //retrieve sales
    public Venta traerVenta(Long codigo_producto);

    //retrieve sale
    public List<Venta> traerVentas();

    //create sale
    public void crearVenta(Venta venta);

    //delete sale
    public void borrarVenta(Long codigo_venta);

    //edit sale
    public void editarVenta(Long codigo_venta, Long codigo_venta_updated, LocalDate fechaVenta_updated, Double total_updated, Cliente unCliente_updated, List<Producto> listaProductos_updated);

    //retrieve sales by date
    public List<Venta> traerVentaPorFecha(LocalDate date);
    
    //get highest value sale
    public Venta getVentaConMayorMonto();
    
    //get products from a sale
    public List<Producto> ProductosDeUnaVenta(Long codigo_venta);
}
   