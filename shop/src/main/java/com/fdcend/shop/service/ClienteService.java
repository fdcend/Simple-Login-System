package com.fdcend.shop.service;

import com.fdcend.shop.model.Cliente;
import com.fdcend.shop.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienterepo;

    @Override
    public Cliente traerCliente(Long id_cliente) {
        Cliente cliente = clienterepo.findById(id_cliente).orElse(null);
        return cliente;
    }

    @Override
    public List<Cliente> traerClientes() {
        List<Cliente> clientes = clienterepo.findAll();
        return clientes;
    }

    @Override
    public void crearCliente(Cliente cliente) {
        clienterepo.save(cliente);
    }

    @Override
    public void borrarCliente(Long id_cliente) {
        clienterepo.deleteById(id_cliente);
    }

    @Override
    public void editarCliente(Long id_cliente, Long id_cliente_updated, String nombre_updated, String apellido_updated, String dni_updated) {

        //retrieve client
        Cliente cliente = this.traerCliente(id_cliente);
        
        //edit client
        cliente.setId_cliente(id_cliente_updated);
        cliente.setNombre(nombre_updated);
        cliente.setApellido(apellido_updated);
        cliente.setDni(dni_updated);
        
        //save cliente
        this.crearCliente(cliente);
    }
}
