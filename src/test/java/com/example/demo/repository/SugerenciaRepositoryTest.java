package com.example.demo.repository;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.Entity.Sugerencia;
import com.example.demo.Entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SugerenciaRepositoryTest extends AbstractIntegrationDBTest {
    SugerenciaRepository sugerenciaRepository;
    UsuarioRepository usuarioRepository;

    @Autowired
    public SugerenciaRepositoryTest(SugerenciaRepository sugerenciaRepository, UsuarioRepository usuarioRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    void initMockSugerencias() {
        Sugerencia sugerencia = Sugerencia.builder()
                .descripcion("Descripcion 1234")
                .usuario(
                        usuarioRepository.save(Usuario.builder()
                                .nombre("user")
                                .build()))
                .build();
        sugerenciaRepository.save(sugerencia);

        Sugerencia sugerencia1 = Sugerencia.builder()
                .descripcion("Sugerencia abcd")
                .usuario(
                        usuarioRepository.save(Usuario.builder()
                                .build()))
                .build();
        sugerenciaRepository.save(sugerencia1);

        sugerenciaRepository.flush();
    }

    @Test
    void givenSugerencia_whenComienzaCon_thenListaSugerencias() {
        // given
        initMockSugerencias();
        // when
        List<Sugerencia> sugerencias = sugerenciaRepository.findByDescripcionStartingWith("Sugerencia");
        // then
        assertThat(sugerencias).hasSizeGreaterThanOrEqualTo(1);
    }
}