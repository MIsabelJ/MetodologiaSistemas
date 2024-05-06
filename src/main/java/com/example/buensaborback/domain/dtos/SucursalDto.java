package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
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
    private Set<Categoria> categorias = new HashSet<>();
    private Set<Pedido> pedidos = new HashSet<>();
    private Set<Promocion> promociones = new HashSet<>();
    private Empresa empresa;

}
