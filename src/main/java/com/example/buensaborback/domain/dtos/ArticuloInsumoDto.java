package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class ArticuloInsumoDto extends BaseDto {

    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    private String denominacion;
    private Double precioVenta;
    private Set<ArticuloInsumo> articuloManufacturadoDetalles = new HashSet<>();
    private Set<ImagenArticuloInsumo> imagenes = new HashSet<>();
    private UnidadMedida unidadMedida;
    private Categoria categoria;
    private Set<DetallePedido> detallePedidos = new HashSet<>();
    private Set<Promocion> estaEnPromociones = new HashSet<>();
}
