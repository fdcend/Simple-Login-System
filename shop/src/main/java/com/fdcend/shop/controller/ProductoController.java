package com.fdcend.shop.controller;

import com.fdcend.shop.model.Producto;
import com.fdcend.shop.service.IProductoService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductoService iProductoService;

    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto producto) {
        iProductoService.crearProducto(producto);
        return "producto creado correctamente";
    }

    @GetMapping("/productos")
    public List<Producto> traerProductos() {
        return iProductoService.traerProductos();
    }

    @GetMapping("/productos/{codigo_producto}")
    public Producto traerProducto(@PathVariable Long codigo_producto) {
        return iProductoService.traerProducto(codigo_producto);
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String borrarProducto(@PathVariable Long codigo_producto) {
        iProductoService.borrarProducto(codigo_producto);
        return "Producto borrado correctamente";
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto editarProducto(@PathVariable Long codigo_producto,
            @RequestParam(required = false, name = "codigo_producto_updated") Long codigo_producto_updated,
            @RequestParam(required = false, name = "nombre") String nombre_updated,
            @RequestParam(required = false, name = "marca") String marca_updated,
            @RequestParam(required = false, name = "costo") Double costo_updated,
            @RequestParam(required = false, name = "cantidad_disponible") Double cantidad_disponible_updated) {

        iProductoService.editarProducto(codigo_producto, codigo_producto_updated, nombre_updated, marca_updated, costo_updated, cantidad_disponible_updated);
        return iProductoService.traerProducto(codigo_producto_updated);
    }

    @GetMapping("/productos/falta_stock")
    public List<Producto> faltaStock() {
        List<Producto> listaFaltaStock = iProductoService.productosBajosStock();
        List<Producto> listaFaltaStockSinVentas = new ArrayList<>();

        for (Producto producto : listaFaltaStock) {
            Producto productoSinVentas = modelMapper.map(producto, Producto.class);
            productoSinVentas.setListaVentas(null);

            listaFaltaStockSinVentas.add(productoSinVentas);
        }

        return listaFaltaStockSinVentas;
    }

}
