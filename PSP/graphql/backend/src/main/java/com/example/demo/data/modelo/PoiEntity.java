package com.example.demo.data.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Table(name = "pois")
public class PoiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "latitud", nullable = false)
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    private Double longitud;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @ManyToOne()
    @JoinColumn(name = "ciudad_id", nullable = false)
    private CiudadEntity ciudad;

    @ManyToMany(mappedBy = "pois")
    private List<VisitaEntity> visitas;
}
