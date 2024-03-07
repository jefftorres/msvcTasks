package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "partidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partida implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String creador;
    private String deporte;
    private String ciudad;
    private String provincia;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;

    @ManyToMany
    @JoinTable(
            name = "partidas_usuarios",
            joinColumns = @JoinColumn(name = "id_partida", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    )
    private List<Usuario> usuarios;
}
