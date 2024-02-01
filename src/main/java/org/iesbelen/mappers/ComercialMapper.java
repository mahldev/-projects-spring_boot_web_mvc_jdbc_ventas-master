package org.iesbelen.mappers;

import java.util.List;

import org.iesbelen.dto.ComercialDto;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.dto.PedidoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {

    @Mapping(source = "listaPedidosIn", target = "listaPedidos")
    @Mapping(source = "totalIn", target = "total")
    @Mapping(source = "mediaTotalPedidosIn", target = "mediaTotalPedidos")
    ComercialDto ToDto(
            Comercial comercial,
            List<PedidoDto> listaPedidosIn,
            int totalIn,
            double mediaTotalPedidosIn);

}
