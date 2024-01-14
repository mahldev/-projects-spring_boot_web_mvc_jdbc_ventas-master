package org.iesbelen.service;

import java.util.List;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {

    private final ComercialDAO comercialDAO;

    @Autowired
    public ComercialService(final ComercialDAO comercialDAO) {
        this.comercialDAO = comercialDAO;
    }

    public List<Comercial> getAll() {
        return comercialDAO.getAll();
    }
}
