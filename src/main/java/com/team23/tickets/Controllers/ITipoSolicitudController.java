package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ITipoSolicitudController {


    ResponseEntity<GenericResponseDTO> listarTiposSolicitud();
}
