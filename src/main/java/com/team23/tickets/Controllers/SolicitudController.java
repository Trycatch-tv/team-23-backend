package com.team23.tickets.Controllers;


import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Services.SolicitudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SolicitudController implements ISolicitudController{

    private final SolicitudService solicitudService;

    @Override
    @GetMapping("/v1/srvTeam23/Solicitud/listarActivas")
    public ResponseEntity<GenericResponseDTO> buscarSolicitudActivas() {
        return solicitudService.buscarSolicitudActivas();
    }

    @Override
    @DeleteMapping("/v1/srvTeam23/Solicitud/eliminarSolicitud/{idSolicitud}")
    public ResponseEntity<GenericResponseDTO> eliminarSolicitud(@PathVariable Long idSolicitud) {
        return solicitudService.eliminarSolicitud(idSolicitud);
    }

    @Override
    @PostMapping("/v1/srvTeam23/Solicitud/Crear")
    public ResponseEntity<GenericResponseDTO> crearSolicitud(@RequestBody String json_entrada) {
        return solicitudService.crearSolicitud(json_entrada);
    }

    @Override
    @GetMapping("/v1/srvTeam23/Solicitud/buscarSolicitudPorId/{idSolicitud}")
    public ResponseEntity<GenericResponseDTO> buscarSolicitudPorId(@PathVariable Long idSolicitud) {
        return solicitudService.buscarSolicitudPorId(idSolicitud);
    }

    @Override
    @PutMapping("/v1/srvTeam23/Solicitud/CerrarSocilitud")
    public ResponseEntity<GenericResponseDTO> cerrarSolicitud(@RequestBody String json_in) {
        return solicitudService.cerrarSolicitud(json_in);
    }

    @Override
    @PatchMapping("/v1/srvTeam23/Solicitud/cambiarEstado/{idSolicitud}/{idEstado}")
    public ResponseEntity<GenericResponseDTO> cambiarEstado(@PathVariable Long idSolicitud, @PathVariable Integer idEstado) {
        return solicitudService.cambiarEstado(idSolicitud, idEstado);
    }
}
