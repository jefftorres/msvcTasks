package com.example.demo.repository;

import com.example.demo.AbstractIntegrationDBTest;
import com.example.demo.Entity.Partida;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartidaRepositoryTest extends AbstractIntegrationDBTest {
    PartidaRepository partidaRepository;

    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    void initMockPartidas() {
        Partida partida = Partida.builder()
                .creador("ABCD")
                .ciudad("Ciudad1")
                .provincia("Prov1")
                .build();
        partidaRepository.save(partida);

        Partida partida1 = Partida.builder()
                .creador("XYZ")
                .ciudad("Ciudad2")
                .provincia("Prov2")
                .build();
        partidaRepository.save(partida1);

        partidaRepository.flush();
    }

    @Test
    void givenPartidas_whenFindCreador_thenListaPartidas() {
        // given
        initMockPartidas();
        // when
        List<Partida> partidas = partidaRepository.findByCreadorLike("B");
        // then
        assertThat(partidas).isNotNull();
    }
}