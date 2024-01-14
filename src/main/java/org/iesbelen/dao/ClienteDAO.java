package org.iesbelen.dao;

import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Cliente;

public interface ClienteDAO {

    public void create(Cliente cliente);

    public List<Cliente> getAll();

    public Optional<Cliente> find(int id);

    public void update(Cliente cliente);

    public void delete(long id);

}
