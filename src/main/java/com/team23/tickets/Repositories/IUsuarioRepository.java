package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("Select usuario from Usuario usuario where usuario.activo = true")
    List<Usuario> listarUsuariosActivos();

    Optional<Usuario> findByEmail(@Param("email") String email);

    boolean existsByEmail(@Param("email") String email);




}
