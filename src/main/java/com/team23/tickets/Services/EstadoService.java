package com.team23.tickets.Services;

import com.team23.tickets.DTO.EstadoDTO;
import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Entities.Estado;
import com.team23.tickets.Repositories.IEstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoService implements IEstadoService{

    private final IEstadoRepository iEstadoRepository;
    private String mensaje;

    public EstadoService(IEstadoRepository iEstadoRepository) {
        this.iEstadoRepository = iEstadoRepository;
    }


    @Override
    public ResponseEntity<GenericResponseDTO> listarEstdosFinales(Integer idEstado) {

        try{

            List<Estado> listaEstados = iEstadoRepository.findEstadosByIdEstado(idEstado);

            if (listaEstados.isEmpty()){
                mensaje = "No se encontraron registros de Estados";
            }

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(listaEstados)
                    .message(mensaje).
                            statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(null)
                    .message("Error en el Servidor").
                            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<GenericResponseDTO> findAll() {
        try{

            List<Estado> listaEstados = iEstadoRepository.findAll();
            List<EstadoDTO> lstDto = new ArrayList<>();
            if (listaEstados.isEmpty()){
                mensaje = "No se encontraron registros de Estados";
            }else{
                ModelMapper mapper =  new ModelMapper();
                listaEstados.forEach(e ->{
                    EstadoDTO dto = mapper.map(e, EstadoDTO.class);
                    lstDto.add(dto);

                });
                mensaje = "Se listan todos los Estados Activos";
            }

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(lstDto )
                    .message(mensaje).
                            statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(null)
                    .message("Error en el Servidor").
                            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}