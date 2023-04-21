package com.team23.tickets.Services;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.DTO.TipoSolicitudDTO;
import com.team23.tickets.Entities.TipoSolicitud;
import com.team23.tickets.Repositories.ITipoSolicitudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoSolicitudService implements ITipoSolicitudService{
    private final ITipoSolicitudRepository tipoSolicitudRepository;
    private String mensaje;

    public TipoSolicitudService(ITipoSolicitudRepository tipoSolicitudRepository) {
        this.tipoSolicitudRepository = tipoSolicitudRepository;
    }

    @Override
    public ResponseEntity<GenericResponseDTO> listarTipoSolicitud() {

        try{

            List<TipoSolicitud> ltsTipoSolicitud =  tipoSolicitudRepository.listarTiposSolicitud();
            List<TipoSolicitudDTO> lstDTO = new ArrayList<>();
            if (ltsTipoSolicitud.isEmpty()){
                mensaje = "No se encontro resultados";
            }else{
                ModelMapper mapper =  new ModelMapper();
                ltsTipoSolicitud.forEach(t ->{
                    TipoSolicitudDTO tipoDTO = mapper.map(t, TipoSolicitudDTO.class);
                    lstDTO.add(tipoDTO);
                });
                mensaje = "Lista de Tipos de solicitud";
            }

            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(ltsTipoSolicitud.isEmpty() ? new ArrayList<TipoSolicitud>() : lstDTO)
                    .message(mensaje).statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(null)
                    .message("Error Interno del Servidor").statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
