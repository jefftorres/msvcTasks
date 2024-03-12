package com.example.demo.repository;

import com.example.demo.Entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByCreadorAndDestinatario(String creador, String destinatario);
    List<Mensaje> findByCreadorNotLike(String creador);
    List<Mensaje> findByContenidoStartingWith(String contenido);
}
