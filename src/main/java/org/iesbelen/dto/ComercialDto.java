package org.iesbelen.dto;

import java.util.List;

public record ComercialDto(

        int id,

        String nombre,

        String apellido1,

        String apellido2,

        float comision,

        List<PedidoDto> listaPedidos,

        int total,

        double mediaTotalPedidos) {
}
