# ms-integraciones

Microservicio de integraciones externas de SmartLogix SaaS. Implementa el patrón Adapter para sincronizar el stock con plataformas de e-commerce externas sin acoplar el núcleo del sistema a cada API particular.

## Patrón de diseño implementado

| Patrón | Ubicación | Propósito |
|---|---|---|
| Adapter | `MarketplaceAdapter`, `MercadoLibreAdapter`, `ShopifyAdapter` | Estandariza la comunicación con plataformas externas detrás de una interfaz común, permitiendo agregar nuevas plataformas sin modificar el código existente |

## Tecnologías

- Java 21
- Spring Boot 3.5.14
- Lombok
- Docker

## DTO implementados

- `SincronizacionRequestDTO`: datos de entrada (productoId, nuevoStock, plataforma).
- `SincronizacionResponseDTO`: confirmación de la sincronización.

## Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/integraciones/sincronizar?productoId=N&nuevoStock=N&plataforma=mercadolibre\|shopify\|ambos` | Sincroniza el stock con la plataforma elegida |
| GET | `/api/integraciones/health` | Verifica que el servicio está activo |

## Cómo ejecutar localmente

### Requisitos
- Java 21
- Maven 3.9+

### Con Maven

```bash
mvn clean package -DskipTests
java -jar target/ms-integraciones-0.0.1-SNAPSHOT.jar
```

El servicio queda disponible en `http://localhost:8085`.

### Con Docker

```bash
docker build -t ms-integraciones .
docker run -p 8085:8085 ms-integraciones
```

## Cómo probar

```bash
curl -X POST "http://localhost:8085/api/integraciones/sincronizar?productoId=1&nuevoStock=10&plataforma=ambos"
```

Respuesta esperada:
```
Sincronización completada via Adapter Pattern en: ambos
```

Revisa los logs — deben aparecer ambos Adapters (MercadoLibre y Shopify) ejecutando la transformación de datos por separado.

## Estructura del proyecto

```
src/main/java/com/proyectofullstack/integraciones/
├── Controller/   IntegracionController
├── Adapter/      MarketplaceAdapter, MercadoLibreAdapter, ShopifyAdapter
└── IntegracionesApplication.java
```
