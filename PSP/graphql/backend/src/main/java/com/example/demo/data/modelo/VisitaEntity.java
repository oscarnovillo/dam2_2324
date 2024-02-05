package com.example.demo.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "visitas")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class VisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "fecha_inicial", nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;


    @ManyToMany
    @JoinTable(
        name = "visitas_pois",
        joinColumns = @JoinColumn(name = "visita_id"),
        inverseJoinColumns = @JoinColumn(name = "poi_id"))
    private Set<PoiEntity> pois;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
