package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IEstadoController {


    ResponseEntity<GenericResponseDTO> listaEstadoFinales(Integer idEstado);
    ResponseEntity<GenericResponseDTO> listarEstados();
}
