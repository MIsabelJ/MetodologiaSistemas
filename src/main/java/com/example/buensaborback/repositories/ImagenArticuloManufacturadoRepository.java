package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ImagenArticuloInsumo;
import com.example.buensaborback.domain.entities.ImagenArticuloManufacturado;
import com.example.buensaborback.domain.entities.ImagenCliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenArticuloManufacturadoRepository extends BaseRepository<ImagenArticuloManufacturado,Long> {
}
