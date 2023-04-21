package com.team23.tickets.Services;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IEstadoService {

    ResponseEntity<GenericResponseDTO>listarEstdosFinales(Integer idEstado);
    ResponseEntity<GenericResponseDTO>findAll();

}
