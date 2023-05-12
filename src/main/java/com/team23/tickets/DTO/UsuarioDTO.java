package com.team23.tickets.DTO;

import com.team23.tickets.Enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsuarioDTO {

        private Integer idUsuario;

        private String nombres;

        private String apellidos;

        private String telefono;

        private String email;

        private boolean activo;

        private Date fechaCreacion;

        private Integer idUsuarioCrea;

        private Roles rol;

        private String nombre_avatar;

        //private List<SolicitudDTO> solicitudes_credas;


    }

