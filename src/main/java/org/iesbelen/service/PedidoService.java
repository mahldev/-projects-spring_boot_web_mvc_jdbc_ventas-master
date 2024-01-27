package org.iesbelen.service;

import java.util.List;

import org.iesbelen.dao.PedidoDAO;
import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Pedido> getAll() {
        return pedidoDAO.listAll();
    }

    public void create(final Pedido pedido) {
        pedidoDAO.create(pedido);
    }

}
