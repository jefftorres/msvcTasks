package com.example.demo.repository;

import com.example.demo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreAndApellidos(String nombre, String apellidos);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = ?1 AND u.apellidos = ?2")
    List<Usuario> buscarPorNombreYApellidos(String nombre, String apellidos);
    List<Usuario> findByUsernameOrEmail(String username, String email);

    List<Usuario> findByNombreLike(String nombre);
    @Query("select u from Usuario u where u.nombre like %?1")
    List<Usuario> buscarPorNombre(String criterio);
}
