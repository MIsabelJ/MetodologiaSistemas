package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SucursalDto extends BaseDto {

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;
    private Domicilio domicilio;
    private Set<Categoria> categorias;
    private Set<Pedido> pedidos;
    private Set<Promocion> promociones;
    private Empresa empresa;

}
