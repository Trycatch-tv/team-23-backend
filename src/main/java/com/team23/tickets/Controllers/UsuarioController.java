package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UsuarioController implements IUsuarioController{


    private  final UsuarioService usuarioService;


    @Override
    @PostMapping("v1/srvTeam23/User/Crear")
    public ResponseEntity<GenericResponseDTO> crearUsuario(@RequestBody String json_in) {
        return usuarioService.crearUsuario(json_in);
    }

    @GetMapping("v1/srvTeam23/User/buscarUsuario/{idUsuario}")
    @Override
    public ResponseEntity<GenericResponseDTO> buscarUsuario(@PathVariable Integer idUsuario) {
        return usuarioService.buscarUsuario(idUsuario);
    }

    @DeleteMapping("v1/srvTeam23/User/Eliminar/{idUsuario}")
    @Override
    public ResponseEntity<GenericResponseDTO> elimandoLogicoUsuario(@PathVariable Integer idUsuario) {
        return usuarioService.elimandoLogicoUsuario(idUsuario);
    }

    @PatchMapping("v1/srvTeam23/User/ActivarUsuario/{idUsuario}")
    @Override
    public ResponseEntity<GenericResponseDTO> activarUsuario( @PathVariable Integer idUsuario) {
        return usuarioService.activarUsuario(idUsuario);
    }

    @Override
    @GetMapping("v1/srvTeam23/User/MostrarUsuarios")
    public ResponseEntity<GenericResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
