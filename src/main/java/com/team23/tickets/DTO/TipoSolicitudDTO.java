package com.team23.tickets.DTO;


import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoSolicitudDTO {

    private Integer idTipoSolicitud;

    private String nombre;

}
