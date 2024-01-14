package org.iesbelen.service;

import java.util.List;

import org.iesbelen.dao.ClienteDAO;
import org.iesbelen.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;

    // Se utiliza inyección automática por constructor del framework Spring.
    // Por tanto, se puede omitir la anotación Autowired
    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listAll() {

        return clienteDAO.getAll();

    }

}
