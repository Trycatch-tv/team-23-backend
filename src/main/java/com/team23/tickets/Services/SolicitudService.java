package com.team23.tickets.Services;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.DTO.SolicitudDTO;
import com.team23.tickets.Entities.Estado;
import com.team23.tickets.Entities.Solicitud;
import com.team23.tickets.Entities.TipoSolicitud;
import com.team23.tickets.Entities.Usuario;
import com.team23.tickets.Repositories.IEstadoRepository;
import com.team23.tickets.Repositories.ISolicitudRepository;
import com.team23.tickets.Repositories.ITipoSolicitudRepository;
import com.team23.tickets.Repositories.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SolicitudService implements ISolicitudService{

    private final ISolicitudRepository iSolicitudRepository;
    private final IUsuarioRepository usuarioRepository;
    private final IEstadoRepository iEstadoRepository;
    private final ITipoSolicitudRepository tipoSolicitudRepository;

    private String mensaje;
    private final ModelMapper mapper;

    public SolicitudService(ISolicitudRepository iSolicitudRepository, IUsuarioRepository usuarioRepository, IEstadoRepository iEstadoRepository, ITipoSolicitudRepository tipoSolicitudRepository, ModelMapper mapper) {
        this.iSolicitudRepository = iSolicitudRepository;
        this.usuarioRepository = usuarioRepository;
        this.iEstadoRepository = iEstadoRepository;
        this.tipoSolicitudRepository = tipoSolicitudRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<GenericResponseDTO> buscarSolicitudActivas() {
        try {

            List<Solicitud> ltsSolicitud = iSolicitudRepository.listarSolicitudes();
            mensaje = ltsSolicitud.isEmpty() ?  "No se encontraron resultados" : "Se listan las Solicitudes";
            Type listTypeDestino = new TypeToken<List<SolicitudDTO>>(){}.getType();
            List<SolicitudDTO> ltsSolitictudDTO = mapper.map(ltsSolicitud, listTypeDestino );

            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(ltsSolitictudDTO).message(mensaje)
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
        log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                e.getLocalizedMessage());

        return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new ArrayList<Solicitud>())
                .message("Error en el Servidor!!. ")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<GenericResponseDTO> eliminarSolicitud(Long idSolicitud) {
        try {

            Optional<Solicitud> solicitud = iSolicitudRepository.findById(idSolicitud);

            if(!solicitud.isPresent())
                mensaje= "No se encontro la solicitud con ese id : " + idSolicitud;

            solicitud.get().setEstado(iEstadoRepository.findById(6).get());
            iSolicitudRepository.save(solicitud.get());

            mensaje = "Se encontro un resgistro";

            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(solicitud, SolicitudDTO.class))
                    .message(mensaje)
                    .statusCode(HttpStatus.OK.value()).build(),
                    HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                    e.getLocalizedMessage());

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new Exception("Error"))
                    .message("Error en el Servidor!!. ")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> crearSolicitud(String json_entrada) {
        try {


            JSONObject json_in = new JSONObject(json_entrada);
            Integer idUsuario = json_in.getInt("usuario_id");
            Optional<Usuario> userOpt = usuarioRepository.findById(idUsuario);
            if(!userOpt.isPresent()){
                mensaje = "El Usuario no existe";
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .objectResponse(null)
                        .statusCode(HttpStatus.OK.value())
                        .message(mensaje).build(), HttpStatus.OK);
            }
            Optional<TipoSolicitud> tipoOpt = tipoSolicitudRepository.findById(json_in.getInt("tipo_solicitud_id"));
            if(!tipoOpt.isPresent()){
                mensaje = "El Tipo de Solicitud no existe";
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .objectResponse(null)
                        .statusCode(HttpStatus.OK.value())
                        .message(mensaje).build(), HttpStatus.OK);
            }
            final Integer estado = 1;
            Optional<Estado> estOpt = iEstadoRepository.findById(estado);
            Solicitud  solicitud = Solicitud.builder().build();
            solicitud.setUsuarioCrea(userOpt.get());
            solicitud.setTipoSolicitud(tipoOpt.get());
            solicitud.setEstado(estOpt.get());
            solicitud.setFechaCreacion(new Date());
            solicitud.setDescripcionSolicitud(json_in.getString("descripcion_solicitud"));
            iSolicitudRepository.save(solicitud);
            mensaje = "Solicitud creada con exito!!";
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(solicitud, SolicitudDTO.class)).message(mensaje)
                    .statusCode(HttpStatus.CREATED.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                    e.getLocalizedMessage());

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new SolicitudDTO())
                    .message("Error en el Servidor!!. ")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> buscarSolicitudPorId(Long idSolicitud) {
        try {

            Optional<Solicitud> solicitud = iSolicitudRepository.findById(idSolicitud);
            mensaje = (solicitud.isEmpty()) ?  "No se encontro la solicitud" : "Se obtiene solicitud con exito!!";

            System.out.println(solicitud.get().toString());
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(solicitud.get(), SolicitudDTO.class)).message(mensaje)
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                    e.getLocalizedMessage());

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new SolicitudDTO())
                    .message("Error en el Servidor!!. ")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> cerrarSolicitud(String json_entrada) {
        try {

            JSONObject json_in = new JSONObject(json_entrada);
            Solicitud  solicitud = iSolicitudRepository.findById(json_in.getLong("id_solicitud")).get();
            Integer idUsuario = json_in.getInt("usuario_id");
            Optional<Usuario> userOpt = usuarioRepository.findById(idUsuario);
            if(userOpt.isEmpty()){
                mensaje = "El Usuario no existe";
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .objectResponse(null)
                        .statusCode(HttpStatus.OK.value())
                        .message(mensaje).build(), HttpStatus.OK);
            }
            final Integer estado = 5;
            Optional<Estado> estOpt = iEstadoRepository.findById(estado);
            solicitud.setUsuarioCierre(userOpt.get());
            solicitud.setEstado(estOpt.get());
            solicitud.setFechaCierre(new Date());
            solicitud.setDescripcionSolucion(json_in.getString("descripcion_solucion"));
            iSolicitudRepository.save(solicitud);
            mensaje = "Se cierra la Solicitud con exito!!. ";

            //mapper.map(solicitud, SolicitudDTO.class)
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(solicitud).message(mensaje)
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                    e.getLocalizedMessage());

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new SolicitudDTO())
                    .message("Error en el Servidor!!. ")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> cambiarEstado(Long idSolicitud, Integer idEstado) {
        try {

            Optional<Solicitud> solicitud = iSolicitudRepository.findById(idSolicitud);
            if (solicitud.isEmpty())
                mensaje = "No se encontro la solicitud";

            if(idEstado!= 5){
                solicitud.get().setEstado(iEstadoRepository.findById(idEstado).get());
            }else{
                return  new ResponseEntity<>(GenericResponseDTO.builder()
                        .objectResponse(null)
                        .message("La solitud no puede ser cerrada sin una descricion de cierre")
                        .statusCode(HttpStatus.OK.value()).build(),HttpStatus.OK);
            }

            mensaje = "Se cambio el estado con exito!!";
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(solicitud.get(), SolicitudDTO.class)).message(mensaje)
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Error : en Solicitud " + e.getMessage()+"\n"+
                    e.getLocalizedMessage());

            return new ResponseEntity<>(GenericResponseDTO.builder().objectResponse(new SolicitudDTO())
                    .message("Error en el Servidor!!. ")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
