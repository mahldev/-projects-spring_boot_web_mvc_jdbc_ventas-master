package org.iesbelen.service;

import java.util.List;
import java.util.Optional;

import org.iesbelen.dao.ClienteDAO;
import org.iesbelen.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public List<Cliente> listAll() {
        return clienteDAO.getAll();
    }

    public void create(final Cliente cliente) {
        clienteDAO.create(cliente);
    }

    public Optional<Cliente> find(final int id) {
        return clienteDAO.find(id);
    }

    public void update(final Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public void delete(final int id) {
        clienteDAO.delete(id);
    }

}
