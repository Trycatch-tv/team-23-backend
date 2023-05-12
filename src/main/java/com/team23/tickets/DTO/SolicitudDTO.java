package com.team23.tickets.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team23.tickets.Entities.Seguimiento;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {

    private long idSolicitud;

    private TipoSolicitudDTO tipoSolicitud;

    private EstadoDTO estado;

    private String descripcionSolicitud;

    private String descripcionSolucion;

    private Date fechaCreacion;

    private Date fechaCierre;

    private UsuarioDTO usuarioCrea;

    private UsuarioDTO usuarioCierre;

    private UsuarioDTO usuarioAgisgando;

    private String titulo;

    private String urlRespuesta;

    private List<SeguimientoDTO> seguimiento;
}
