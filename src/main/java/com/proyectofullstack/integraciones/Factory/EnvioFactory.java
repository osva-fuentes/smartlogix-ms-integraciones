package com.proyectofullstack.integraciones.Factory;
import com.proyectofullstack.integraciones.Strategy.EnvioStrategy;
import com.proyectofullstack.integraciones.Strategy.EnvioEstandar;
import com.proyectofullstack.integraciones.Strategy.EnvioExpress;
public class EnvioFactory {
    public static EnvioStrategy getEstrategia(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "express"    -> new EnvioExpress();
            default           -> new EnvioEstandar();
        };
    }
}