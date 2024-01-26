package com.fdcend.shop.service;


import com.fdcend.shop.model.Producto;
import java.util.List;

public interface IProductoService {

    //retrieve product
    public Producto traerProducto(Long codigo_producto);

    //retrieve product
    public List<Producto> traerProductos();

    //create product
    public void crearProducto(Producto producto);

    //delete product
    public void borrarProducto(Long codigo_producto);

    //edit product
    public void editarProducto(Long codigo_producto, Long codigo_producto_updated, String nombre_updated, String marca_updated, Double costo_updated, Double cantidad_disponible_updated);

    //retrieve low stock products
    public List<Producto> productosBajosStock ();
    
}