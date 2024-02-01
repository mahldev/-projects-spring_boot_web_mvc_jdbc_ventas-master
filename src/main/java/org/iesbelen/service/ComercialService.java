package org.iesbelen.service;

import java.util.List;
import java.util.Optional;

import org.iesbelen.dao.ComercialDAO;
import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Comercial> getAll() {
        return comercialDAO.getAll();
    }

    public Optional<Comercial> find(int id) {
        return comercialDAO.find(id);
    }

    public void create(Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public void update(Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void remove(int id) {
        comercialDAO.delete(id);
    }

    public List<Pedido> findAllPedidos(int id) {
        return pedidoDAO.findAllByIdComercial(id);
    }

    public List<Comercial> getAllComercial(int idCliente) {
        return comercialDAO.getAllComercial(idCliente);
    }
}
