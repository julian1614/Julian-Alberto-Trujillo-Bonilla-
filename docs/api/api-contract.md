# Contrato inicial de API de MediStock

## Base URL

```text
http://localhost:8080
```

## Salud

### Verificar estado del backend

```http
GET /health
```

Respuesta `200 OK`:

```json
{
  "status": "UP",
  "application": "MediStock",
  "timestamp": "fecha-hora"
}
```

### Verificar estado con Actuator

```http
GET /actuator/health
```

Respuesta `200 OK`:

```json
{
  "status": "UP"
}
```

## Medicamentos

### Crear medicamento

```http
POST /api/medications
Content-Type: application/json
```

Request:

```json
{
  "code": "MED-001",
  "name": "Acetaminofen 500 mg",
  "activeIngredient": "Acetaminofen",
  "pharmaceuticalForm": "Tableta",
  "concentration": "500 mg",
  "unitOfMeasure": "Caja"
}
```

Response `201 Created`:

```json
{
  "id": 1,
  "code": "MED-001",
  "name": "Acetaminofen 500 mg",
  "activeIngredient": "Acetaminofen",
  "pharmaceuticalForm": "Tableta",
  "concentration": "500 mg",
  "unitOfMeasure": "Caja",
  "active": true
}
```

Header:

```text
Location: /api/medications/1
```

Errores:

| Status | Causa |
| --- | --- |
| `400 Bad Request` | Cuerpo invalido o codigo duplicado |

### Listar medicamentos

```http
GET /api/medications
```

Response `200 OK`:

```json
[
  {
    "id": 1,
    "code": "MED-001",
    "name": "Acetaminofen 500 mg",
    "activeIngredient": "Acetaminofen",
    "pharmaceuticalForm": "Tableta",
    "concentration": "500 mg",
    "unitOfMeasure": "Caja",
    "active": true
  }
]
```

### Consultar medicamento por ID

```http
GET /api/medications/1
```

Response `200 OK`:

```json
{
  "id": 1,
  "code": "MED-001",
  "name": "Acetaminofen 500 mg",
  "activeIngredient": "Acetaminofen",
  "pharmaceuticalForm": "Tableta",
  "concentration": "500 mg",
  "unitOfMeasure": "Caja",
  "active": true
}
```

Errores:

| Status | Causa |
| --- | --- |
| `400 Bad Request` | Medicamento no encontrado |

## Proveedores

### Crear proveedor

```http
POST /api/suppliers
Content-Type: application/json
```

Request:

```json
{
  "documentNumber": "900123456",
  "name": "Distribuidora Salud Total",
  "phone": "3001234567",
  "email": "ventas@saludtotal.com",
  "address": "Calle 10 # 15-20"
}
```

Response `201 Created`:

```json
{
  "id": 1,
  "documentNumber": "900123456",
  "name": "Distribuidora Salud Total",
  "phone": "3001234567",
  "email": "ventas@saludtotal.com",
  "address": "Calle 10 # 15-20",
  "active": true
}
```

Header:

```text
Location: /api/suppliers/1
```

Errores:

| Status | Causa |
| --- | --- |
| `400 Bad Request` | Cuerpo invalido o documento duplicado |

### Listar proveedores

```http
GET /api/suppliers
```

Response `200 OK`:

```json
[
  {
    "id": 1,
    "documentNumber": "900123456",
    "name": "Distribuidora Salud Total",
    "phone": "3001234567",
    "email": "ventas@saludtotal.com",
    "address": "Calle 10 # 15-20",
    "active": true
  }
]
```

### Consultar proveedor por ID

```http
GET /api/suppliers/1
```

Response `200 OK`:

```json
{
  "id": 1,
  "documentNumber": "900123456",
  "name": "Distribuidora Salud Total",
  "phone": "3001234567",
  "email": "ventas@saludtotal.com",
  "address": "Calle 10 # 15-20",
  "active": true
}
```

Errores:

| Status | Causa |
| --- | --- |
| `400 Bad Request` | Proveedor no encontrado |

## Persistencia

Desde la Semana 5 - Sesion 1, medicamentos y proveedores se almacenan mediante JPA sobre PostgreSQL.

Tablas esperadas:

```text
medications
suppliers
```

Durante pruebas automatizadas se usa H2 en memoria para validar la integracion JPA sin depender de una base de datos externa.
