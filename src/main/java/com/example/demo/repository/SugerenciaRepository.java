package com.example.demo.repository;

import com.example.demo.Entity.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
    List<Sugerencia> findByDescripcionStartingWith(String desc);
}
