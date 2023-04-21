package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/srvTeam23")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EstadoController implements IEstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @Override
    @GetMapping("/buscarEstadosFinales/{id}")
    public ResponseEntity<GenericResponseDTO> listaEstadoFinales(@PathVariable Integer id) {
        return estadoService.listarEstdosFinales(id);
    }

    @Override
    @GetMapping("/listarEstados")
    public ResponseEntity<GenericResponseDTO> listarEstados() {
        return estadoService.findAll();
    }
}
