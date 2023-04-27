package com.team23.tickets.Services;

import com.team23.tickets.DTO.EstadoDTO;
import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.Entities.Estado;
import com.team23.tickets.Repositories.IEstadoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService implements IEstadoService{

    private final IEstadoRepository iEstadoRepository;
    private String mensaje;
    private final ModelMapper mapper;

    public EstadoService(IEstadoRepository iEstadoRepository, ModelMapper mapper) {
        this.iEstadoRepository = iEstadoRepository;
        this.mapper = mapper;
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
            Type listTypeDestino = new TypeToken<List<EstadoDTO>>(){}.getType();
            List<EstadoDTO> lstDto =  mapper.map(listaEstados,listTypeDestino);
            mensaje = listaEstados.isEmpty() ? "No se encontraron registros de Estados" : "Se listan todos los Estados Activos";
            /*
               //List<EstadoDTO> lstDto = new ArrayList<>();listaEstados.forEach(e ->{
                    EstadoDTO dto = mapper.map(e, EstadoDTO.class);
                    lstDto.add(dto);
                });
            */
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

    @Override
    public ResponseEntity<GenericResponseDTO> findByNombreAndactivo(String nombre, boolean activo) {

        try{

            Optional<Estado> estado = iEstadoRepository.findEstadoByNombreAndActivo(nombre, activo);
           mensaje =  (estado.isEmpty() ? "No se encontraron resultados" :  "Se encontro resultado");
           EstadoDTO estadoDTO = mapper.map(estado.orElseGet(Estado::new), EstadoDTO.class);

            return  new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(estadoDTO)
                    .message(mensaje).statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(null)
                    .message("Error Interno del Server").
                            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                            build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
