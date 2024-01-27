package org.iesbelen.modelo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private int id;

    private Double total;

    private Date fecha;

    private int idComercial;

    private int idCliente;

}
