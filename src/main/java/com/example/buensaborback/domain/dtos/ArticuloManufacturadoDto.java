package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloManufacturadoDto extends BaseDto {

    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private String denominacion;
    private Double precioVenta;
    private Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new HashSet<>();
    private Set<ImagenArticuloManufacturado> imagenes = new HashSet<>();
    private UnidadMedida unidadMedida;
    private Categoria categoria;
    private Set<DetallePedido> detallePedidos = new HashSet<>();
    private Set<Promocion> estaEnPromociones = new HashSet<>();
}
