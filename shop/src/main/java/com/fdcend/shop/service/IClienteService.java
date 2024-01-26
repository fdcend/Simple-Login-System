package com.fdcend.shop.service;

import com.fdcend.shop.model.Cliente;
import java.util.List;

public interface IClienteService {

    //retrieve client
    public Cliente traerCliente(Long id_cliente);

    //retrieve clients
    public List<Cliente> traerClientes();

    //create client
    public void crearCliente(Cliente cliente);

    //delete client
    public void borrarCliente(Long id_cliente);

    //edit client
    public void editarCliente(Long id_cliente, Long id_cliente_updated, String nombre_updated, String apellido_updated, String dni_updated);

}
