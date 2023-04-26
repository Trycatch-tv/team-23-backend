package com.team23.tickets.Services;

import com.team23.tickets.DTO.GenericResponseDTO;
import com.team23.tickets.DTO.UsuarioDTO;
import com.team23.tickets.Entities.Usuario;
import com.team23.tickets.Enum.Roles;
import com.team23.tickets.Repositories.IUsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    ModelMapper mapper = new ModelMapper();

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    public ResponseEntity crearUsuario(String json_in) {

        try{

            JSONObject json_entrada = new JSONObject(json_in);


            if (usuarioRepository.existsByEmail(json_entrada.getString("email")))
                return new ResponseEntity<>(GenericResponseDTO.builder()
                        .objectResponse(null)
                        .message("El email a ingresar ya existe..!!")
                        .statusCode(HttpStatus.OK.value()).build(),HttpStatus.OK);

            String pass = new BCryptPasswordEncoder().encode(json_entrada.getString("password"));
            Usuario usuario = new Usuario();
            usuario.setNombres(json_entrada.getString("nombres"));
            usuario.setApellidos(json_entrada.getString("apellidos"));
            usuario.setPassword(pass);
            usuario.setRol(json_entrada.getEnum(Roles.class, "rol"));
            usuario.setIdUsuarioCrea(json_entrada.getInt("usuario_crea"));
            usuario.setEmail(json_entrada.getString("email"));
            usuario.setNombre_avatar(json_entrada.getString("avatar"));
            usuario.setTelefono(json_entrada.getString("telefono"));
            usuario.setFechaCreacion(new Date());
            usuario.setActivo(true);
            usuarioRepository.save(usuario);


            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(usuario, UsuarioDTO.class))
                    .message("Usuario creado con exito!!")
                    .statusCode(HttpStatus.CREATED.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Error al intertar Crear el Usuario!! "
                    + e.getMessage()+
                    " \n "+ e.getLocalizedMessage());
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(new Usuario())
                    .message("Error Interno del servidor al crear el usuario.!!")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    public ResponseEntity buscarUsuario(Integer idUsuario) {
        try{

            Optional<Usuario> user = usuarioRepository.findById(idUsuario);

            if(!user.isPresent())
                return new ResponseEntity(GenericResponseDTO.builder()
                        .objectResponse(new Usuario())
                        .message("No se encroatron registros para ese Usuario " + idUsuario)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
                //mapper.map(user.get(), UsuarioDTO.class
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(user.get(), UsuarioDTO.class))
                    .message("Se ha encontrado un Usuario con id : "+ idUsuario)
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Error al buscar el usuario!! "
                    + e.getMessage()+
                    " \n "+ e.getLocalizedMessage());
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(new Usuario())
                    .message("Error en el servidor al buscar el usuario")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Transactional
    public ResponseEntity elimandoLogicoUsuario(Integer idUsuario) {

        try{
            Optional<Usuario> user = usuarioRepository.findById(idUsuario);

            if(!user.isPresent())
                return new ResponseEntity(GenericResponseDTO.builder()
                        .objectResponse(new Usuario())
                        .message("No se encroatron registros para el id " + idUsuario)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            user.get().setActivo(false);
            usuarioRepository.save(user.get());

            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(user.get(), UsuarioDTO.class))
                    .message("Usuario eliminado con exito!!")
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Error al eliminar el usuario!! "
                    + e.getMessage()+
                    " \n "+ e.getLocalizedMessage());
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(new Usuario())
                    .message("Error en el servidor al eliminar el usuario")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public ResponseEntity<GenericResponseDTO> activarUsuario(Integer idUsuario) {
        try{
            Optional<Usuario> user = usuarioRepository.findById(idUsuario);
            if(!user.isPresent())
                return new ResponseEntity(GenericResponseDTO.builder()
                        .objectResponse(new Usuario())
                        .message("No se encroatron registros para el id " + idUsuario)
                        .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);
            user.get().setActivo(true);
            usuarioRepository.save(user.get());
            //mapper.map(user.get(), UsuarioDTO.class
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(mapper.map(user.get(), UsuarioDTO.class))
                    .message("Usuario activado con exito!!")
                    .statusCode(HttpStatus.OK.value()).build(), HttpStatus.OK);

        }catch (Exception e){
            log.error("Error al activar el usuario!! "
                    + e.getMessage()+
                    " \n "+ e.getLocalizedMessage());
            return new ResponseEntity(GenericResponseDTO.builder()
                    .objectResponse(new Usuario())
                    .message("Error en el servidor al activar el usuario")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GenericResponseDTO> listarUsuarios() {
        try{
            List<Usuario> ltsUser  = usuarioRepository.findAll();
            List<UsuarioDTO> listDto = new ArrayList<>();
            ltsUser.forEach(u ->{
                UsuarioDTO userDto = mapper.map(u , UsuarioDTO.class);
                listDto.add(userDto);
            });

            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Listado de Usuarios")
                    .objectResponse(listDto).build(), HttpStatus.OK);

        }catch (Exception e){

            e.printStackTrace();
            log.error(e.getMessage());
            return new ResponseEntity<>(GenericResponseDTO.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error en el Servidor")
                    .objectResponse(new ArrayList<UsuarioDTO>()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
