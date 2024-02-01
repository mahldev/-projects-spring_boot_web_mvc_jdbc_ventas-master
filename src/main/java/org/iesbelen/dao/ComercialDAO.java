package org.iesbelen.dao;

import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Comercial;

public interface ComercialDAO {

    public void create(Comercial comercial);

    public List<Comercial> getAll();

    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(long id);

    List<Comercial> getAllComercial(int idCliente);

}
