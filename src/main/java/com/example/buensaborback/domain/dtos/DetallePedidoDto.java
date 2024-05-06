package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedidoDto extends BaseDto{

    private Integer cantidad;
    private Double subTotal;
    private ArticuloManufacturado articuloManufacturado;
    private ArticuloInsumo articuloInsumo;

}
