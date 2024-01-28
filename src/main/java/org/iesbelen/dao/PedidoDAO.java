package org.iesbelen.dao;

import org.iesbelen.modelo.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoDAO {

    List<Pedido> listAll();

    Optional<Pedido> find(final int id);

    void create(final Pedido pedido);

    void remove(final int id);

    void update(final Pedido pedido);

    List<Pedido> findAllByIdComercial(final int id);

}
