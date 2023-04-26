package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EstadoController implements IEstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @Override
    @GetMapping("/v1/srvTeam23/buscarEstadosFinales/{id}")
    public ResponseEntity<GenericResponseDTO> listaEstadoFinales(@PathVariable Integer id) {
        return estadoService.listarEstdosFinales(id);
    }

    @Override
    @GetMapping("/listarEstados")
    public ResponseEntity<GenericResponseDTO> listarEstados() {
        return estadoService.findAll();
    }

    @Override
    @GetMapping("/v1/srvTeam23/listarPorNombre/{nombre}/{activo}")
    public ResponseEntity<GenericResponseDTO> listaNombreActivo(@PathVariable String nombre, @PathVariable boolean activo ) {
        return estadoService.findByNombreAndactivo(nombre, activo);
    }


}
