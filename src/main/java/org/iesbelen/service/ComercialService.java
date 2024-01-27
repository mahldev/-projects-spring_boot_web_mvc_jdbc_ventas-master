package org.iesbelen.service;

import java.util.List;
import java.util.Optional;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    public List<Comercial> getAll() {
        return comercialDAO.getAll();
    }

    public Optional<Comercial> find(final int id) {
        return comercialDAO.find(id);
    }

    public void create(final Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public void update(final Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void remove(final int id) {
        comercialDAO.delete(id);
    }
}
