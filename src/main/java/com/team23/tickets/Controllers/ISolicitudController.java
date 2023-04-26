package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ISolicitudController {

    ResponseEntity<GenericResponseDTO> buscarSolicitudActivas();
    ResponseEntity<GenericResponseDTO> eliminarSolicitud(Long idSolicitud);
    ResponseEntity<GenericResponseDTO> crearSolicitud(String json_entrada);
    ResponseEntity<GenericResponseDTO> buscarSolicitudPorId(Long idSolicitud);
    ResponseEntity<GenericResponseDTO> cerrarSolicitud(String json_in);
    ResponseEntity<GenericResponseDTO> cambiarEstado(Long idSolicitud, Integer idEstado);
}
