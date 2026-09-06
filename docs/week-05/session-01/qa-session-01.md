# MediStock - QA - Semana 5 - Sesion 1

## Validacion de persistencia real con base de datos

**Proyecto:** MediStock  
**Responsable:** Julian Alberto Trujillo Bonilla  
**Rama de trabajo:** feature/week5-session1-database-persistence-qa  
**Tipo de validacion:** Revision tecnica, funcional y documental  
**Sesion validada:** Semana 5 - Sesion 1  
**Arquitectura definida:** Monolito modular  
**Tecnologia validada:** Java con Spring Boot  
**Base de datos validada:** PostgreSQL  

## 1. Objetivo de QA

Verificar que el entregable de la Semana 5 - Sesion 1 implemente correctamente persistencia real para medicamentos y proveedores.

Esta validacion confirma que la API deja de depender exclusivamente de memoria y queda conectada a una base de datos PostgreSQL mediante Spring Data JPA.

## 2. Alcance de la revision

La validacion se enfoca en:

- dependencias de persistencia;
- configuracion de datasource PostgreSQL;
- configuracion H2 para pruebas automatizadas;
- entidades JPA de medicamentos y proveedores;
- repositorios Spring Data;
- adaptadores JPA para los puertos existentes;
- repositorios en memoria conservados bajo perfil `memory`;
- prueba de integracion de persistencia;
- contrato de API actualizado;
- documentacion de la sesion;
- actualizacion del README.

Archivos principales revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-05/session-01/session-01.md`
- `docs/week-05/session-01/qa-session-01.md`
- `MediStock/pom.xml`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/resources/application.yaml`
- `MediStock/src/main/java/com/MediStock/MediStock/catalog/infrastructure/persistence/jpa/`
- `MediStock/src/main/java/com/MediStock/MediStock/suppliers/infrastructure/persistence/jpa/`
- `MediStock/src/test/java/com/MediStock/MediStock/persistence/ApiPersistenceIntegrationTests.java`

## 3. Criterios de aceptacion

| ID | Criterio | Estado |
| --- | --- | --- |
| CA-001 | El proyecto incluye Spring Data JPA | Aprobado |
| CA-002 | El proyecto incluye driver PostgreSQL | Aprobado |
| CA-003 | El proyecto incluye H2 para pruebas automatizadas | Aprobado |
| CA-004 | La aplicacion principal apunta a PostgreSQL | Aprobado |
| CA-005 | La configuracion usa variables de entorno para conexion | Aprobado |
| CA-006 | El puerto por defecto queda alineado con el contenedor PostgreSQL `5433` | Aprobado |
| CA-007 | Existe entidad JPA para medicamentos | Aprobado |
| CA-008 | Existe entidad JPA para proveedores | Aprobado |
| CA-009 | Existe repositorio Spring Data para medicamentos | Aprobado |
| CA-010 | Existe repositorio Spring Data para proveedores | Aprobado |
| CA-011 | Los adaptadores JPA implementan los puertos existentes | Aprobado |
| CA-012 | Los repositorios en memoria se conservan bajo perfil `memory` | Aprobado |
| CA-013 | Las pruebas automatizadas usan H2 | Aprobado |
| CA-014 | Existe prueba de integracion para persistencia de medicamentos | Aprobado |
| CA-015 | Existe prueba de integracion para persistencia de proveedores | Aprobado |
| CA-016 | El contrato de API documenta las tablas esperadas | Aprobado |
| CA-017 | El README refleja la persistencia JPA | Aprobado |
| CA-018 | Los archivos Markdown del proyecto se mantienen en espanol | Aprobado |

## 4. Checklist de revision

- [x] Se identifica la sesion validada.
- [x] Se revisa `pom.xml`.
- [x] Se valida la configuracion PostgreSQL en `application.yaml`.
- [x] Se valida la configuracion H2 para pruebas.
- [x] Se revisan las entidades JPA de medicamentos.
- [x] Se revisan las entidades JPA de proveedores.
- [x] Se revisan los repositorios Spring Data.
- [x] Se revisan los adaptadores JPA.
- [x] Se confirma que los puertos de aplicacion se mantienen.
- [x] Se confirma que los repositorios en memoria no se eliminan.
- [x] Se revisa la prueba de integracion de persistencia.
- [x] Se valida que Docker Compose no hace parte del alcance de esta sesion.
- [x] Se revisa el contrato de API.
- [x] Se revisa la documentacion de la sesion.
- [x] Se revisa la actualizacion del README.

## 5. Casos de prueba automatizados

### CP-001 - Validar carga de contexto con persistencia

Resultado esperado:

- Spring Boot inicia el contexto de pruebas;
- JPA encuentra los repositorios;
- H2 se usa como base de datos de pruebas.

Estado: Aprobado.

### CP-002 - Persistir medicamento mediante API y JPA

Flujo validado:

```text
POST /api/medications
GET  /api/medications
```

Resultado esperado:

- el medicamento se crea correctamente;
- el medicamento queda disponible al listar;
- la operacion pasa por el adaptador JPA.

Estado: Aprobado.

### CP-003 - Persistir proveedor mediante API y JPA

Flujo validado:

```text
POST /api/suppliers
GET  /api/suppliers
```

Resultado esperado:

- el proveedor se crea correctamente;
- el proveedor queda disponible al listar;
- la operacion pasa por el adaptador JPA.

Estado: Aprobado.

## 6. Pruebas automatizadas ejecutadas

Comando usado desde la carpeta `MediStock`:

```text
mvn test
```

Resultado esperado:

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
Build: SUCCESS
```

Las pruebas cubren:

- carga del contexto de Spring Boot;
- endpoint de salud;
- controladores de medicamentos y proveedores;
- validaciones de request;
- persistencia JPA con H2;
- integracion entre API, servicios, puertos y adaptadores JPA.

## 7. Pruebas manuales sugeridas

Para probar la aplicacion con PostgreSQL en Docker:

```text
docker start medistock-postgres
```

Luego ejecutar el backend:

```text
cd MediStock
.\mvnw.cmd spring-boot:run
```

Peticiones principales en Postman:

| Metodo | Endpoint | Resultado esperado |
| --- | --- | --- |
| GET | `http://localhost:8080/health` | Codigo 200 y estado `UP` |
| POST | `http://localhost:8080/api/medications` | Codigo 201 y medicamento persistido |
| GET | `http://localhost:8080/api/medications` | Codigo 200 y listado desde base de datos |
| POST | `http://localhost:8080/api/suppliers` | Codigo 201 y proveedor persistido |
| GET | `http://localhost:8080/api/suppliers` | Codigo 200 y listado desde base de datos |

Conexion sugerida en pgAdmin 4:

| Campo | Valor |
| --- | --- |
| Host name/address | `localhost` |
| Port | `5433` |
| Maintenance database | `medistock` |
| Username | `medistock` |
| Password | `medistock` |

Tablas esperadas:

```text
medications
suppliers
```

## 8. Observaciones

El entregable cumple el objetivo de la Semana 5 - Sesion 1 porque medicamentos y proveedores ya cuentan con persistencia real mediante JPA.

La configuracion principal apunta a PostgreSQL y el entorno de pruebas usa H2 para evitar dependencia obligatoria de Docker durante la ejecucion de pruebas automatizadas.

El puerto `5433` queda documentado como valor por defecto para evitar conflicto con instalaciones locales de PostgreSQL que suelen usar `5432`.

## 9. Riesgos pendientes

| Riesgo | Estado | Accion futura |
| --- | --- | --- |
| El esquema se actualiza con `ddl-auto: update` | Pendiente | Implementar migraciones versionadas con Flyway |
| Backend y base de datos no se levantan juntos | Pendiente | Crear Dockerfile y Docker Compose |
| No existe autenticacion real | Pendiente | Implementar login y permisos |
| No existe JWT | Pendiente | Agregar seguridad JWT en una fase posterior |
| No existe control de lotes y stock | Pendiente | Implementar inventario real y movimientos |
| No existe relacion fisica entre medicamentos y proveedores | Pendiente | Definirla cuando se implementen compras o entradas |

## 10. Resultado de QA

El entregable de la Semana 5 - Sesion 1 queda **aprobado tecnicamente, funcionalmente y documentalmente**.

La rama puede continuar hacia revision o integracion, considerando que esta validacion corresponde a persistencia JPA con PostgreSQL como base de datos objetivo.

## 11. Evidencia

Archivos revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-05/session-01/session-01.md`
- `docs/week-05/session-01/qa-session-01.md`
- `MediStock/pom.xml`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/resources/application.yaml`
- `MediStock/src/main/java/com/MediStock/MediStock/catalog/infrastructure/persistence/jpa/`
- `MediStock/src/main/java/com/MediStock/MediStock/suppliers/infrastructure/persistence/jpa/`
- `MediStock/src/test/java/com/MediStock/MediStock/persistence/ApiPersistenceIntegrationTests.java`

Resultado:

```text
Validacion documental: aprobada
Validacion tecnica: aprobada
Validacion funcional: aprobada
Pruebas automatizadas: aprobadas
Estado para integracion: listo para revision
```
