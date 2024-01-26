package com.fdcend.shop.service;

import com.fdcend.shop.model.Cliente;
import com.fdcend.shop.model.Producto;
import com.fdcend.shop.model.Venta;
import com.fdcend.shop.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public Venta traerVenta(Long codigo_producto) {
        Venta venta = ventaRepo.findById(codigo_producto).orElse(null);
        return venta;
    }

    @Override
    public List<Venta> traerVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas;
    }

    @Override
    public void crearVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    @Override
    public void borrarVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public void editarVenta(Long codigo_venta, Long codigo_venta_updated, LocalDate fechaVenta_updated, Double total_updated, Cliente cliente_updated, List<Producto> Productos_updated) {

        //retrieve sale
        Venta venta = this.traerVenta(codigo_venta);

        //edit sale
        venta.setCodigo_venta(codigo_venta_updated);
        venta.setFechaVenta(fechaVenta_updated);
        venta.setTotal(total_updated);
        venta.setUnCliente(cliente_updated);
        venta.setListaProductos(Productos_updated);

        //save sale
        this.crearVenta(venta);
    }

    @Override
    public List<Venta> traerVentaPorFecha(LocalDate date) {
        return ventaRepo.findAllByFechaVenta(date);
    }

    @Override
    public Venta getVentaConMayorMonto() {
        List<Venta> listaVentas = this.traerVentas();

        //partimos de venta nro. (0)
        Venta ventaMasAlta = listaVentas.get(0);

        for (Venta venta : listaVentas) {
            if (venta.getTotal() > ventaMasAlta.getTotal()) {
                ventaMasAlta = venta;
            }
        }

        return ventaMasAlta;
    }

    @Override
    public List<Producto> ProductosDeUnaVenta(Long codigo_venta) {
        Venta venta = this.traerVenta(codigo_venta);

        // Crear una nueva lista de productos sin la propiedad "listaVentas"
        List<Producto> listaProductos = new ArrayList<>();

        for (Producto producto : venta.getListaProductos()) {
            // Crear una copia del producto sin la propiedad "listaVentas"
            Producto nuevoProducto = new Producto();

            nuevoProducto.setCodigo_producto(producto.getCodigo_producto());
            nuevoProducto.setNombre(producto.getNombre());
            nuevoProducto.setMarca(producto.getMarca());
            nuevoProducto.setCosto(producto.getCosto());
            nuevoProducto.setCantidad_disponible(producto.getCantidad_disponible());

            // Agregar el nuevo producto a la lista
            listaProductos.add(nuevoProducto);
        }
        return listaProductos;
    }

}
