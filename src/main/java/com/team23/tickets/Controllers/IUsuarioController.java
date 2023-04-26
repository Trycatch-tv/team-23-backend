package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUsuarioController {



    ResponseEntity<GenericResponseDTO> crearUsuario(String json_in);
    ResponseEntity<GenericResponseDTO> buscarUsuario(Integer idUsuario);
    ResponseEntity<GenericResponseDTO> elimandoLogicoUsuario(Integer idUsuario);
    ResponseEntity<GenericResponseDTO>  activarUsuario(Integer idusuario);
    ResponseEntity<GenericResponseDTO> listarUsuarios();



}
