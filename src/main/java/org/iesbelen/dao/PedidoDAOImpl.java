package org.iesbelen.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoDAOImpl implements PedidoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pedido> listAll() {
        return jdbcTemplate.query("SELECT * FROM pedido",
                (rs, _unsed) -> pedidoMapper(rs));
    }

    @Override
    public Optional<Pedido> find(final int id) {
        final var pedido = jdbcTemplate.queryForObject("SELECT * FROM pedido WHERE id = ?",
                (rs, rowNum) -> pedidoMapper(rs), id);
        return Optional.ofNullable(pedido);
    }

    @Override
    public synchronized void create(final Pedido pedido) {
        final var insertSql = "INSERT INTO pedido (total, fecha, id_comercial, id_cliente)";
        final var KeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final var ps = connection.prepareStatement(insertSql, new String[] { "id" });
            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, pedido.getFecha());
            ps.setInt(idx++, pedido.getIdComercial());
            ps.setInt(idx, pedido.getIdCliente());
            return ps;
        }, KeyHolder);

        pedido.setId(KeyHolder.getKey().intValue());
    }

    @Override
    public void remove(final int id) {
        jdbcTemplate.update("DELETE pedido WHERE id = ?", id);
    }

    @Override
    public void update(final Pedido pedido) {
        jdbcTemplate.update("""
                    UPDATE pedido SET
                        total = ?,
                        fecha = ?,
                        id_comercial = ?,
                        id_cliente = ?
                    WHERE id = ?
                """,
                pedido.getTotal(),
                pedido.getFecha(),
                pedido.getIdComercial(),
                pedido.getIdCliente(),
                pedido.getId());
    }

    private static Pedido pedidoMapper(final ResultSet rs) {
        try {
            return new Pedido(rs.getInt("id"),
                    rs.getDouble("total"),
                    rs.getDate("fecha"),
                    rs.getInt("id_comercial"),
                    rs.getInt("id_cliente"));
        } catch (final SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pedido> findAllByIdComercial(final int id) {
        return jdbcTemplate.query("SELECT * FROM pedido WHERE id_comercial = ?",
                (rs, _unsed) -> pedidoMapper(rs), id);
    }
}
