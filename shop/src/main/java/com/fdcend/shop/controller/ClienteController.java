package com.fdcend.shop.controller;

import com.fdcend.shop.model.Cliente;
import com.fdcend.shop.service.IClienteService;
import java.util.List;
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
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cliente) {
        iClienteService.crearCliente(cliente);
        return "Cliente creado correctamente";
    }

    @GetMapping("/clientes")
    public List<Cliente> traerClientes() {
        return iClienteService.traerClientes();
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente traerCliente(@PathVariable Long id_cliente) {
        return iClienteService.traerCliente(id_cliente);
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente){
        iClienteService.borrarCliente(id_cliente);
        return "Cliente eliminado correctamente";
    }

    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editarCliente(@PathVariable Long id_cliente,
                                @RequestParam(required = false, name = "id_cliente_updated") Long id_cliente_updated,
                                @RequestParam(required = false, name = "nombre") String nombre_updated,
                                @RequestParam(required = false, name = "apellido") String apellido_updated,
                                @RequestParam(required = false, name = "dni") String dni_updated){
    
        iClienteService.editarCliente(id_cliente, id_cliente_updated, nombre_updated, apellido_updated, dni_updated);
        return iClienteService.traerCliente(id_cliente_updated);
    }
}
