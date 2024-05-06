package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class ImagenArticuloManufacturado extends Base{
    private String url;
}
