package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
public class ArticuloManufacturado extends Base {

    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private String denominacion;
    private Double precioVenta;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude//Permite evitar problemas de recursividad
    @Builder.Default
    private Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new HashSet<>();


    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="articulo_manufacturado_id")
    @Builder.Default
    private Set<ImagenArticuloManufacturado> imagenes = new HashSet<>();


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMedida_ID")
    private UnidadMedida unidadMedida;

    @ToString.Exclude//Permite evitar problemas de recursividad
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "Categoria_ID")
    private Categoria categoria;

    @OneToMany(mappedBy = "articuloManufacturado")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @ManyToMany(mappedBy = "articulosManufacturado", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Promocion> estaEnPromociones = new HashSet<>();
}
