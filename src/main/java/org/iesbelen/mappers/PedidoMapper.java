package org.iesbelen.mappers;

import org.iesbelen.dto.PedidoDto;
import org.iesbelen.modelo.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "nombreCliente", source = "nombreClienteIn")
    PedidoDto toDto(Pedido pedido, String nombreClienteIn);

}
