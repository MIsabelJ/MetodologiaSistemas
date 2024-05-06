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
public class ArticuloInsumo extends Base {
    
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    private String denominacion;
    private Double precioVenta;

    @OneToMany(mappedBy = "articuloInsumo")
    @ToString.Exclude //Permite evitar los problemas de recursividad.
    @Builder.Default
    private Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new HashSet<>();



    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="articulo_insumo_id")
    @Builder.Default
    private Set<ImagenArticuloInsumo> imagenes = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMedida_ID")
    private UnidadMedida unidadMedida;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @ToString.Exclude //Permite evitar problemas de recursividad
    @JoinColumn(name = "Categoria_ID")
    private Categoria categoria;

    @OneToMany(mappedBy = "articuloInsumo")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @ManyToMany(mappedBy = "articulosInsumo", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Promocion> estaEnPromociones = new HashSet<>();
}
