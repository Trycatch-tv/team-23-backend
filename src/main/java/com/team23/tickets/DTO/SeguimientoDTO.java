package com.team23.tickets.DTO;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeguimientoDTO {

    private long idSeguimiento;

    private String descripcion;

    private boolean activo;

    private Date fechaCreacion;

    private UsuarioDTO usuario;

}
