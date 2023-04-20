package com.team23.tickets.Repositories;

import com.team23.tickets.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("Select usuario from Usuario usuario where usuario.activo = true")
    List<Usuario> listarUsuariosActivos();
}
