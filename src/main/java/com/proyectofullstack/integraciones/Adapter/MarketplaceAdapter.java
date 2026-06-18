package com.proyectofullstack.integraciones.Adapter;

public interface MarketplaceAdapter {
    void sincronizarStock(Long idProducto, Integer nuevoStock);
    String getNombrePlataforma();
}