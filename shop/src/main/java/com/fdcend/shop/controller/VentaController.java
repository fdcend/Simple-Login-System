package com.fdcend.shop.controller;

import com.fdcend.shop.model.Cliente;
import com.fdcend.shop.dto.MayorVentaDTO;
import com.fdcend.shop.model.Producto;
import com.fdcend.shop.model.Venta;
import com.fdcend.shop.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private IVentaService iVentaService;

    @PostMapping("/ventas/crear")
    public String crearVenta(@RequestBody Venta venta) {
        iVentaService.crearVenta(venta);
        return "venta creada correctamente";
    }

    @GetMapping("/ventas")
    public List<Venta> traerVentas() {
        return iVentaService.traerVentas();
    }

    @GetMapping("/ventas/{codigo_venta}")
    public Venta traerVenta(@PathVariable Long codigo_venta) {
        return iVentaService.traerVenta(codigo_venta);
    }

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String borrarVenta(@PathVariable Long codigo_venta) {
        iVentaService.borrarVenta(codigo_venta);
        return "Venta borrada correctamente";
    }

    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta editarVenta(@PathVariable Long codigo_venta,
            @RequestParam(required = false, name = "codigo_venta_updated") Long codigo_producto_updated,
            @RequestParam(required = false, name = "fecha_venta") LocalDate fecha_venta_updated,
            @RequestParam(required = false, name = "total") Double total_updated,
            @RequestParam(required = false, name = "cliente") Cliente unCliente_updated,
            @RequestParam(required = false, name = "lista_productos") List<Producto> listaProductos_updated) {

        iVentaService.editarVenta(codigo_venta, codigo_producto_updated, fecha_venta_updated, total_updated, unCliente_updated, listaProductos_updated);
        return iVentaService.traerVenta(codigo_producto_updated);
    }

    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> listaProductosDeUnaVenta(@PathVariable Long codigo_venta) {
        List<Producto> listaProductos = iVentaService.ProductosDeUnaVenta(codigo_venta);
        return listaProductos;
    }

    @GetMapping("/ventas/fecha/{fechaVenta}")
    public String traerVentaPorFecha(@PathVariable("fechaVenta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Venta> listaVentasDia = iVentaService.traerVentaPorFecha(date);
        double montoTotal = 0;

        //obtener cantidad de ventas del dia
        int cantidadTotal = listaVentasDia.size();

        //obtener total $ de todas las ventas del dia
        for (Venta unaVenta : listaVentasDia) {
            montoTotal += unaVenta.getTotal();
        }

        return "Las cantidad de ventas fueron: " + cantidadTotal + "\n"
                + "El total de todas las ventas es: " + montoTotal;
    }

    @GetMapping("ventas/mayor_venta")
    public MayorVentaDTO mayorVenta() {

        Venta ventaMayor = iVentaService.getVentaConMayorMonto();

        MayorVentaDTO mayorVentaDto = new MayorVentaDTO();

        mayorVentaDto.setCodigoVenta(ventaMayor.getCodigo_venta());
        mayorVentaDto.setTotalVenta(ventaMayor.getTotal());
        mayorVentaDto.setCantidadProductos(ventaMayor.getListaProductos().size());
        mayorVentaDto.setNombreCliente(ventaMayor.getUnCliente().getNombre());
        mayorVentaDto.setApellidoCliente(ventaMayor.getUnCliente().getApellido());

        return mayorVentaDto;
    }

}