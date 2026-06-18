package com.proyectofullstack.integraciones.Adapter;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MercadoLibreAdapter implements MarketplaceAdapter {

    @Override
    public void sincronizarStock(Long idProducto, Integer nuevoStock) {
        log.info("[Adapter - MercadoLibre]: Transformando datos al formato ML... " +
                 "Producto {} actualizado a {} unidades.", idProducto, nuevoStock);
    }

    @Override
    public String getNombrePlataforma() {
        return "MercadoLibre";
    }
}