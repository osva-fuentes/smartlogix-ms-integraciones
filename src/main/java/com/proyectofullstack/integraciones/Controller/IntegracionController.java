package com.proyectofullstack.integraciones.Controller;

import com.proyectofullstack.integraciones.Adapter.MercadoLibreAdapter;
import com.proyectofullstack.integraciones.Adapter.ShopifyAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/integraciones")
public class IntegracionController {

    private final MercadoLibreAdapter mercadoLibre;

    private final ShopifyAdapter shopify;

    IntegracionController(ShopifyAdapter shopify) {
        this.mercadoLibre = new MercadoLibreAdapter();
        this.shopify = shopify;
    }

    @PostMapping("/sincronizar")
    public ResponseEntity<String> sincronizar(
            @RequestParam Long productoId,
            @RequestParam Integer nuevoStock,
            @RequestParam(defaultValue = "ambos") String plataforma) {

        switch (plataforma.toLowerCase()) {
            case "mercadolibre" -> mercadoLibre.sincronizarStock(productoId, nuevoStock);
            case "shopify"      -> shopify.sincronizarStock(productoId, nuevoStock);
            default -> {
                mercadoLibre.sincronizarStock(productoId, nuevoStock);
                shopify.sincronizarStock(productoId, nuevoStock);
            }
        }

        return ResponseEntity.ok("Sincronización completada via Adapter Pattern en: " + plataforma);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ms-integraciones activo - Adapter Pattern operativo");
    }
}