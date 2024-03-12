package com.example.demo.repository;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.Entity.Mensaje;
import com.example.demo.Entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MensajeRepositoryTest extends AbstractIntegrationDBTest {
    MensajeRepository mensajeRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    void initMockMensajes() {

        Mensaje mensaje = Mensaje.builder()
                .creador("ABC")
                .destinatario("XYZ")
                .contenido("Hola XYZ")
                .usuario(
                        usuarioRepository.save(Usuario.builder()
                                .nombre("BCD")
                                .build()))
                .build();
        mensajeRepository.save(mensaje);

        Mensaje mensaje1 = Mensaje.builder()
                .creador("DEF")
                .destinatario("IJK")
                .contenido("Mensaje de prueba")
                .usuario(
                        usuarioRepository.save(Usuario.builder()
                                .build())
                )
                .build();
        mensajeRepository.save(mensaje1);

        mensajeRepository.flush();
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Dado un conjunto de mensajes, buscar si los mensajes existen en la base de datos")
    void givenId_whenMensaje_thenExists() {
        // given

        initMockMensajes();
        // when
//        for (Mensaje m : mensajeRepository.findAll()) {
//            // then
//            assertThat(m.getId()).isNotNull();
//        }

        mensajeRepository.findAll().forEach(m -> {
            assertThat(m.getId()).isNotNull();
        });
    }

    @Test
    @DisplayName("Dado un conjunto de mensajes, se obtienen los mensajes que tengan un creador y destinatario definidos")
    void mensajePorCreadorYDestinatario() {
        // given
        initMockMensajes();
        // when
        List<Mensaje> mensajes = mensajeRepository.findByCreadorAndDestinatario("ABC", "XYZ");
        // then
        assertThat(mensajes).isNotEmpty();
        assertThat(mensajes).first().hasFieldOrPropertyWithValue("creador", "ABC");
    }

    @Test
    void givenMensajes_whenComienzaCon_thenListaMensajes() {
        // given
        initMockMensajes();
        // when
        List<Mensaje> mensajes = mensajeRepository.findByContenidoStartingWith("Hola");
        // then
        assertThat(mensajes).isNotNull();
    }
}