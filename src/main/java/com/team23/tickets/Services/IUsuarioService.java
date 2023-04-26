package com.team23.tickets.Services;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {

    ResponseEntity<GenericResponseDTO> crearUsuario(String json_in);
    ResponseEntity<GenericResponseDTO> buscarUsuario(Integer idUsuario);
    ResponseEntity<GenericResponseDTO> elimandoLogicoUsuario(Integer idUsuario);
    ResponseEntity<GenericResponseDTO> activarUsuario(Integer idusuario);
    ResponseEntity<GenericResponseDTO> listarUsuarios();

}
