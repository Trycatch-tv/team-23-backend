package com.team23.tickets.Controllers;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Services.TipoSolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/srvTeam23")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class TipoSolicitudController implements ITipoSolicitudController{

    private final TipoSolicitudService tipoSolicitudService;

    public TipoSolicitudController(TipoSolicitudService tipoSolicitudService) {
        this.tipoSolicitudService = tipoSolicitudService;
    }

    @GetMapping("/listarTipoSolicitud")
    @Override
    public ResponseEntity<GenericResponseDTO> listarTiposSolicitud() {
        return tipoSolicitudService.listarTipoSolicitud();
    }
}
