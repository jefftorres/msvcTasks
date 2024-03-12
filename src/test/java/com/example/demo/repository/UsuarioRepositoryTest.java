package com.example.demo.repository;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.Entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsuarioRepositoryTest extends AbstractIntegrationDBTest {
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    void initMockUsuarios() {
        Usuario usuario = Usuario.builder()
                .nombre("A")
                .apellidos("B C")
                .username("abc")
                .password("1234")
                .build();
        usuarioRepository.save(usuario);

        Usuario usuario1 = Usuario.builder()
                .nombre("X")
                .apellidos("Y Z")
                .username("xyz")
                .password("4321")
                .build();
        usuarioRepository.save(usuario1);

        usuarioRepository.flush();
    }

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    void givenAnUser_whenSave_thenUserWithId() {
        //given
        Usuario usuario = Usuario.builder()
                .nombre("A")
                .apellidos("B C")
                .username("abc")
                .password("1234")
                .build();
        // when
        Usuario userSaved = usuarioRepository.save(usuario);
        // then
        assertThat(userSaved.getId()).isNotNull();

    }

    @Test
    @DisplayName("Dado un conjunto de usuarios, al buscarlos todos obtenemos la lista de los usuarios en la base de datos.")
    void shouldGetAllUsers() {
        // given
        initMockUsuarios();
        // when
        List<Usuario> usuarios = usuarioRepository.findAll();
        // then
        assertThat(usuarios).hasSize(2);
    }

    @Test
    void givenUsuarios_whenBuscarPorNombresYApellido_thenObtienesUnaListaDeUsuarios() {
        // given
        initMockUsuarios();
        // when
        List<Usuario> usuarios = usuarioRepository.findByNombreAndApellidos("X", "Y Z");
        // then
        assertThat(usuarios).isNotEmpty();
        assertThat(usuarios).first().hasFieldOrPropertyWithValue("nombre", "X");
    }
}