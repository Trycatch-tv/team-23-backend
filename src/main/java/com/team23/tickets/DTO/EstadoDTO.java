package com.team23.tickets.DTO;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstadoDTO {

    private Integer idEstado;
    private String nombre;
    private Set<MovimientosEstadoDTO> movimientosEstados;


}
