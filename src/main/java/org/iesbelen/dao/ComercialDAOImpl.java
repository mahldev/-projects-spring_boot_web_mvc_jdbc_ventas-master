package org.iesbelen.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesbelen.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ComercialDAOImpl implements ComercialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public synchronized void create(final Comercial comercial) {

        final var insert = """
                INSERT INTO comercial (nombre, apellido1, apellido2, comisión)
                VALUES (?, ?, ?, ?)
                """;
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insert, new String[] { "id" });
            int idx = 0;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido1());
            ps.setString(idx++, comercial.getApellido2());
            ps.setFloat(idx, comercial.getComision());
            return ps;
        }, keyHolder);

        comercial.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Comercial> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión")));
    }

    @Override
    public Optional<Comercial> find(final int id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT * FROM comercial WHERE id = ?",
                        (rs, rowNum) -> new Comercial(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido1"),
                                rs.getString("apellido2"),
                                rs.getFloat("comisión")),
                        id));
    }

    @Override
    public void update(final Comercial comercial) {
        jdbcTemplate.update("""
                    UPDATE comercial SET
                            nombre = ?,
                            apellido1 = ?,
                            apellido2 = ?,
                            comisión = ?
                    WHERE id = ?
                """, comercial.getNombre(), comercial.getApellido1(),
                comercial.getApellido2(), comercial.getComision(), comercial.getId());
    }

    @Override
    public void delete(final long id) {
        jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
    }
}
