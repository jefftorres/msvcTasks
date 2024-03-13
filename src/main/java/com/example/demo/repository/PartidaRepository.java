package com.example.demo.repository;

import com.example.demo.Entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByCreadorLike(String creador);
    List<Partida> findByCiudadOrProvincia(String ciudad, String provincia);
}
