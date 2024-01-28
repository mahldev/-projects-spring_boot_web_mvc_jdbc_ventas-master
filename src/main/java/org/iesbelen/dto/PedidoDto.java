package org.iesbelen.dto;

import java.sql.Date;

public record PedidoDto(

        int id,

        Double total,

        Date fecha,

        int idComercial,

        int idCliente,

        String nombreCliente) {
}
