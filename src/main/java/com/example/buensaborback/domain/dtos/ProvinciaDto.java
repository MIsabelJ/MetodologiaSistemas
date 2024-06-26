package com.example.buensaborback.domain.dtos;

import com.example.buensaborback.domain.entities.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProvinciaDto extends BaseDto{

    private String nombre;
    private Pais pais;

}
