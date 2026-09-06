# MediStock - QA - Semana 4 - Sesion 2

## Validacion de primeros endpoints de negocio

**Proyecto:** MediStock  
**Responsable:** Julian Alberto Trujillo Bonilla  
**Rama de trabajo:** feature/week4-session2-medications-suppliers-api-qa  
**Tipo de validacion:** Revision tecnica, funcional y documental  
**Sesion validada:** Semana 4 - Sesion 2  
**Arquitectura definida:** Monolito modular  
**Tecnologia validada:** Java con Spring Boot  

## 1. Objetivo de QA

Verificar que el entregable de la Semana 4 - Sesion 2 implemente correctamente los primeros endpoints de negocio de MediStock para medicamentos y proveedores.

Esta validacion confirma que la API inicial permite crear, listar y consultar por ID los dos modelos funcionales definidos para esta fase, manteniendo persistencia en memoria y documentando los pendientes para fases posteriores.

## 2. Alcance de la revision

La validacion se enfoca en los siguientes componentes:

- API de medicamentos;
- API de proveedores;
- validaciones de entrada;
- manejo de errores REST;
- repositorios en memoria;
- configuracion de seguridad para pruebas por Postman;
- pruebas automatizadas;
- contrato inicial de API;
- documentacion de la sesion;
- actualizacion del README.

Archivos principales revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-04/session-02/session-02.md`
- `docs/week-04/session-02/qa-session-02.md`
- `MediStock/src/main/java/com/MediStock/MediStock/catalog/`
- `MediStock/src/main/java/com/MediStock/MediStock/suppliers/`
- `MediStock/src/main/java/com/MediStock/MediStock/shared/`
- `MediStock/src/main/java/com/MediStock/MediStock/security/SecurityConfig.java`
- `MediStock/src/test/java/com/MediStock/MediStock/catalog/`
- `MediStock/src/test/java/com/MediStock/MediStock/suppliers/`

## 3. Criterios de aceptacion

| ID | Criterio | Estado |
| --- | --- | --- |
| CA-001 | Existe el modelo de dominio `Medication` | Aprobado |
| CA-002 | Existe el modelo de dominio `Supplier` | Aprobado |
| CA-003 | Existe endpoint para crear medicamentos | Aprobado |
| CA-004 | Existe endpoint para listar medicamentos | Aprobado |
| CA-005 | Existe endpoint para consultar medicamento por ID | Aprobado |
| CA-006 | Existe endpoint para crear proveedores | Aprobado |
| CA-007 | Existe endpoint para listar proveedores | Aprobado |
| CA-008 | Existe endpoint para consultar proveedor por ID | Aprobado |
| CA-009 | Las peticiones validan campos obligatorios | Aprobado |
| CA-010 | El proveedor valida formato de correo electronico | Aprobado |
| CA-011 | Se evita duplicidad de codigo de medicamento | Aprobado |
| CA-012 | Se evita duplicidad de documento de proveedor | Aprobado |
| CA-013 | Se maneja error para consultas por ID inexistente | Aprobado |
| CA-014 | Los endpoints se pueden probar por Postman sin autenticacion real | Aprobado |
| CA-015 | La persistencia se mantiene en memoria segun el alcance de la fase | Aprobado |
| CA-016 | El contrato de API documenta medicamentos y proveedores | Aprobado |
| CA-017 | El README refleja el avance de Semana 4 - Sesion 2 | Aprobado |
| CA-018 | Las pruebas automatizadas finalizan correctamente | Aprobado |
| CA-019 | Los archivos Markdown del proyecto se mantienen en espanol | Aprobado |

## 4. Checklist de revision

- [x] Se identifica la rama QA de la sesion.
- [x] Se valida que medicamentos y proveedores sean los dos modelos funcionales de la fase.
- [x] Se revisa la separacion por paquetes `catalog`, `suppliers` y `shared`.
- [x] Se revisan los modelos de dominio.
- [x] Se revisan los puertos de repositorio.
- [x] Se revisan los servicios de aplicacion.
- [x] Se revisan los repositorios en memoria.
- [x] Se revisan los controladores REST.
- [x] Se revisan los objetos request y response.
- [x] Se valida el manejo uniforme de errores.
- [x] Se confirma que los endpoints definidos quedan permitidos por seguridad.
- [x] Se confirma que la base de datos no hace parte del alcance de esta sesion.
- [x] Se revisan las pruebas automatizadas de controladores.
- [x] Se valida el contrato inicial de API.
- [x] Se revisa la documentacion de la sesion.
- [x] Se revisa la actualizacion del README.

## 5. Casos de prueba automatizados

### CP-001 - Crear, listar y consultar medicamento

Endpoint validado:

```text
POST /api/medications
GET  /api/medications
GET  /api/medications/{id}
```

Resultado esperado:

- codigo `201 Created` al crear;
- header `Location` con el recurso creado;
- codigo `200 OK` al listar;
- codigo `200 OK` al consultar por ID;
- datos del medicamento conservados en memoria.

Estado: Aprobado.

### CP-002 - Rechazar medicamento invalido

Endpoint validado:

```text
POST /api/medications
```

Resultado esperado:

- codigo `400 Bad Request`;
- respuesta con error `invalid request body`.

Estado: Aprobado.

### CP-003 - Crear, listar y consultar proveedor

Endpoint validado:

```text
POST /api/suppliers
GET  /api/suppliers
GET  /api/suppliers/{id}
```

Resultado esperado:

- codigo `201 Created` al crear;
- header `Location` con el recurso creado;
- codigo `200 OK` al listar;
- codigo `200 OK` al consultar por ID;
- datos del proveedor conservados en memoria.

Estado: Aprobado.

### CP-004 - Rechazar proveedor invalido

Endpoint validado:

```text
POST /api/suppliers
```

Resultado esperado:

- codigo `400 Bad Request`;
- respuesta con error `invalid request body`.

Estado: Aprobado.

## 6. Pruebas automatizadas ejecutadas

Comando usado desde la carpeta `MediStock`:

```text
mvn test
```

Resultado obtenido:

```text
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
Build: SUCCESS
```

Las pruebas cubren:

- carga del contexto de Spring Boot;
- endpoint de salud;
- controlador de medicamentos;
- controlador de proveedores;
- validaciones de request;
- respuestas HTTP esperadas.

## 7. Pruebas manuales sugeridas con Postman

Para iniciar el backend:

```text
cd MediStock
.\mvnw.cmd spring-boot:run
```

Peticiones principales:

| Metodo | Endpoint | Resultado esperado |
| --- | --- | --- |
| GET | `http://localhost:8080/health` | Codigo 200 y estado `UP` |
| POST | `http://localhost:8080/api/medications` | Codigo 201 y medicamento creado |
| GET | `http://localhost:8080/api/medications` | Codigo 200 y listado de medicamentos |
| GET | `http://localhost:8080/api/medications/1` | Codigo 200 y medicamento encontrado |
| POST | `http://localhost:8080/api/suppliers` | Codigo 201 y proveedor creado |
| GET | `http://localhost:8080/api/suppliers` | Codigo 200 y listado de proveedores |
| GET | `http://localhost:8080/api/suppliers/1` | Codigo 200 y proveedor encontrado |

## 8. Observaciones

El entregable cumple con el objetivo de la Semana 4 - Sesion 2 porque agrega una API inicial de negocio con dos recursos funcionales.

Medicamentos representa el catalogo principal del sistema y proveedores prepara el camino para futuras entradas de inventario, compras o abastecimiento.

La persistencia en memoria es adecuada para esta fase porque permite validar comportamiento HTTP, estructura modular y reglas basicas sin introducir todavia base de datos, JPA, Flyway o Docker.

## 9. Riesgos pendientes

| Riesgo | Estado | Accion futura |
| --- | --- | --- |
| Los datos se pierden al reiniciar la aplicacion | Pendiente | Implementar persistencia con base de datos |
| No existe relacion fisica entre medicamentos y proveedores | Pendiente | Definir relacion cuando se implementen entradas o compras |
| No existe control de stock | Pendiente | Implementar lotes, cantidades y movimientos de inventario |
| No existe autenticacion real | Pendiente | Implementar login y control de permisos |
| No existe JWT | Pendiente | Agregar JWT cuando se implemente seguridad real |
| No existe Docker activo para backend y base de datos | Pendiente | Preparar Docker Compose en una fase posterior |

## 10. Resultado de QA

El entregable de la Semana 4 - Sesion 2 queda **aprobado tecnicamente, funcionalmente y documentalmente**.

La rama puede continuar hacia revision o integracion, considerando que esta validacion corresponde a endpoints iniciales con persistencia en memoria.

## 11. Evidencia

Archivos revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-04/session-02/session-02.md`
- `docs/week-04/session-02/qa-session-02.md`
- `MediStock/src/main/java/com/MediStock/MediStock/catalog/`
- `MediStock/src/main/java/com/MediStock/MediStock/suppliers/`
- `MediStock/src/main/java/com/MediStock/MediStock/shared/`
- `MediStock/src/test/java/com/MediStock/MediStock/catalog/`
- `MediStock/src/test/java/com/MediStock/MediStock/suppliers/`

Resultado:

```text
Validacion documental: aprobada
Validacion tecnica: aprobada
Validacion funcional: aprobada
Pruebas automatizadas: aprobadas
Estado para integracion: listo para revision
```
