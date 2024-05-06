package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Categoria extends Base{

    private String denominacion;
    @ToString.Exclude
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Categoria> subCategorias = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private Set<ArticuloManufacturado> articulosManufacturado = new HashSet<>();

    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private Set<ArticuloInsumo> articulosInsumo = new HashSet<>();
}
