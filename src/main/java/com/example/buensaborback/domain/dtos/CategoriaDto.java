package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaDto extends BaseDto{

    private String denominacion;
    private Set<Categoria> subCategorias = new HashSet<>();
    private Categoria categoria;
    private Set<Sucursal> sucursales = new HashSet<>();
    private Set<ArticuloManufacturado> articulosManufacturado = new HashSet<>();
    private Set<ArticuloInsumo> articulosInsumo = new HashSet<>();
}
