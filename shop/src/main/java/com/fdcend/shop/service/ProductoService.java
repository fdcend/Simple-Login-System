package com.fdcend.shop.service;

import com.fdcend.shop.model.Producto;
import com.fdcend.shop.repository.IProductosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductosRepository productoRepo;

    @Override
    public Producto traerProducto(Long codigo_producto) {
        Producto producto = productoRepo.findById(codigo_producto).orElse(null);
        return producto;
    }

    @Override
    public List<Producto> traerProductos() {
        List<Producto> listaProductos = productoRepo.findAll();
        return listaProductos;
    }

    @Override
    public void crearProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void borrarProducto(Long codigo_producto) {
        productoRepo.deleteById(codigo_producto);
    }

    @Override
    public void editarProducto(Long codigo_producto, Long codigo_producto_updated, String nombre_updated, String marca_updated, Double costo_updated, Double cantidad_disponible_updated) {

        //retrieve product
        Producto producto = this.traerProducto(codigo_producto);

        //edit product
        producto.setCodigo_producto(codigo_producto_updated);
        producto.setNombre(nombre_updated);
        producto.setMarca(marca_updated);
        producto.setCosto(costo_updated);
        producto.setCantidad_disponible(cantidad_disponible_updated);

        //save product
        this.crearProducto(producto);
    }

    @Override
    public List<Producto> productosBajosStock() {
        List<Producto> listaProductos = this.traerProductos();
        List<Producto> listaFaltaStock = new ArrayList<>();

        for (Producto product : listaProductos) {
            if (product.getCantidad_disponible() <= 5) {
                listaFaltaStock.add(product);
            }
        }
        return listaFaltaStock;
    }

}


