# MediStock - QA - Semana 5 - Sesion 2

## Validacion de backend y base de datos dockerizados

**Proyecto:** MediStock  
**Responsable:** Julian Alberto Trujillo Bonilla  
**Rama de trabajo:** feature/week5-session2-dockerized-backend-qa  
**Tipo de validacion:** Revision tecnica, funcional y documental  
**Sesion validada:** Semana 5 - Sesion 2  
**Arquitectura definida:** Monolito modular  
**Tecnologia validada:** Java con Spring Boot  
**Base de datos validada:** PostgreSQL  
**Contenedores validados:** Docker y Docker Compose  

## 1. Objetivo de QA

Verificar que el entregable de la Semana 5 - Sesion 2 permita ejecutar MediStock mediante contenedores.

Esta validacion confirma que el backend puede construirse como imagen Docker, que PostgreSQL puede levantarse con Docker Compose y que ambos servicios se comunican correctamente dentro de la red interna creada por Compose.

## 2. Alcance de la revision

La validacion se enfoca en:

- `Dockerfile` del backend;
- archivo `.dockerignore`;
- configuracion de `compose.yaml`;
- servicio PostgreSQL en Docker;
- servicio backend en Docker;
- variables de entorno para conexion a base de datos;
- red interna entre backend y PostgreSQL;
- exposicion del backend en el puerto `8080`;
- exposicion de PostgreSQL en el puerto `5433`;
- guia de ejecucion para otros companeros;
- contrato de API actualizado;
- documentacion de la sesion;
- actualizacion del README.

Archivos principales revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-05/session-02/session-02.md`
- `docs/week-05/session-02/qa-session-02.md`
- `docs/setup/guia-ejecucion-proyecto.md`
- `MediStock/Dockerfile`
- `MediStock/.dockerignore`
- `MediStock/compose.yaml`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/resources/application.yaml`

## 3. Criterios de aceptacion

| ID | Criterio | Estado |
| --- | --- | --- |
| CA-001 | Existe `Dockerfile` para construir el backend | Aprobado |
| CA-002 | Existe `.dockerignore` para evitar copiar archivos innecesarios | Aprobado |
| CA-003 | Existe `compose.yaml` para levantar backend y PostgreSQL | Aprobado |
| CA-004 | PostgreSQL usa la base de datos `medistock` | Aprobado |
| CA-005 | PostgreSQL se expone en el puerto local `5433` | Aprobado |
| CA-006 | El backend se expone en el puerto local `8080` | Aprobado |
| CA-007 | El backend usa `postgres` como host interno de base de datos | Aprobado |
| CA-008 | El backend espera que PostgreSQL este saludable antes de iniciar | Aprobado |
| CA-009 | Docker Compose define volumen persistente para PostgreSQL | Aprobado |
| CA-010 | El endpoint `/health` responde con estado `UP` desde Docker | Aprobado |
| CA-011 | La API conserva endpoints de medicamentos y proveedores | Aprobado |
| CA-012 | Las pruebas automatizadas siguen usando H2 | Aprobado |
| CA-013 | Existe guia de ejecucion para otro companero | Aprobado |
| CA-014 | El README documenta la ejecucion con Docker | Aprobado |
| CA-015 | El contrato de API documenta conexion para pgAdmin | Aprobado |
| CA-016 | Los archivos Markdown del proyecto se mantienen en espanol | Aprobado |

## 4. Checklist de revision

- [x] Se identifica la rama QA de la sesion.
- [x] Se revisa la documentacion funcional de la Semana 5 - Sesion 2.
- [x] Se valida que el backend tenga `Dockerfile`.
- [x] Se valida que Docker Compose incluya servicio `postgres`.
- [x] Se valida que Docker Compose incluya servicio `backend`.
- [x] Se valida que PostgreSQL tenga credenciales documentadas.
- [x] Se valida que PostgreSQL se pueda consultar desde pgAdmin usando `localhost:5433`.
- [x] Se valida que el backend se conecte internamente a `postgres:5432`.
- [x] Se valida que el puerto `8080` quede reservado para una sola instancia del backend.
- [x] Se valida que las pruebas automatizadas no dependan de Docker.
- [x] Se revisa la guia de ejecucion para companeros.
- [x] Se revisa el contrato de API.
- [x] Se revisa la actualizacion del README.

## 5. Casos de prueba funcionales

### CP-001 - Validar configuracion de Docker Compose

Comando:

```text
docker compose config
```

Resultado esperado:

- la configuracion se procesa sin errores;
- se identifican los servicios `backend` y `postgres`;
- se identifica el volumen `medistock-postgres-data`;
- se identifican los puertos `8080` y `5433`.

Estado: Aprobado.

### CP-002 - Construir imagen del backend

Comando:

```text
docker compose build backend
```

Resultado esperado:

- la imagen `medistock-backend` se construye correctamente;
- Maven empaqueta la aplicacion;
- el contenedor final ejecuta `app.jar`.

Estado: Aprobado.

### CP-003 - Levantar backend y PostgreSQL

Comando:

```text
docker compose up --build -d
```

Resultado esperado:

- `medistock-postgres` inicia correctamente;
- `medistock-postgres` queda en estado saludable;
- `medistock-backend` inicia correctamente;
- ambos servicios quedan en la red interna de Docker Compose.

Estado: Aprobado.

### CP-004 - Validar salud del backend

Endpoint:

```text
GET http://localhost:8080/health
```

Resultado esperado:

```json
{
  "application": "MediStock",
  "status": "UP"
}
```

Estado: Aprobado.

### CP-005 - Validar acceso a PostgreSQL desde pgAdmin

Configuracion:

| Campo | Valor |
| --- | --- |
| Host name/address | `localhost` |
| Port | `5433` |
| Maintenance database | `medistock` |
| Username | `medistock` |
| Password | `medistock` |

Resultado esperado:

- pgAdmin se conecta a la base de datos;
- se visualizan las tablas generadas por JPA;
- las tablas esperadas son `medications` y `suppliers`.

Estado: Aprobado.

## 6. Pruebas automatizadas ejecutadas

Comando usado desde la carpeta `MediStock`:

```text
mvn test
```

Resultado obtenido:

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
Build: SUCCESS
```

Las pruebas cubren:

- carga del contexto de Spring Boot;
- endpoint de salud;
- controladores de medicamentos;
- controladores de proveedores;
- validaciones de request;
- persistencia JPA con H2;
- integracion entre API, servicios, puertos y adaptadores JPA.

## 7. Pruebas manuales sugeridas con Postman

Con Docker Compose activo:

```text
docker compose up --build
```

Peticiones principales:

| Metodo | Endpoint | Resultado esperado |
| --- | --- | --- |
| GET | `http://localhost:8080/health` | Codigo 200 y estado `UP` |
| POST | `http://localhost:8080/api/medications` | Codigo 201 y medicamento persistido |
| GET | `http://localhost:8080/api/medications` | Codigo 200 y listado desde PostgreSQL |
| POST | `http://localhost:8080/api/suppliers` | Codigo 201 y proveedor persistido |
| GET | `http://localhost:8080/api/suppliers` | Codigo 200 y listado desde PostgreSQL |

## 8. Observaciones

El entregable cumple con el objetivo de la Semana 5 - Sesion 2 porque el backend y la base de datos ya pueden levantarse juntos mediante Docker Compose.

La base de datos queda disponible para pgAdmin desde `localhost:5433`, mientras que el backend usa la URL interna `jdbc:postgresql://postgres:5432/medistock` dentro de la red de Docker Compose.

Si el backend esta activo en Docker, no se debe ejecutar simultaneamente con `mvn spring-boot:run`, porque ambos intentarian usar el puerto `8080`.

## 9. Riesgos pendientes

| Riesgo | Estado | Accion futura |
| --- | --- | --- |
| La imagen base puede reportar vulnerabilidades por analisis de Docker | Pendiente | Revisar imagen base o usar una variante mas especifica en una fase posterior |
| El esquema se actualiza con `ddl-auto: update` | Pendiente | Implementar migraciones versionadas con Flyway |
| No existe autenticacion real | Pendiente | Implementar login y permisos |
| No existe JWT | Pendiente | Agregar seguridad JWT en una fase posterior |
| No existe control de lotes y stock | Pendiente | Implementar inventario real y movimientos |
| No existe frontend integrado al backend | Pendiente | Definir e implementar interfaz de usuario en una fase posterior |

## 10. Resultado de QA

El entregable de la Semana 5 - Sesion 2 queda **aprobado tecnicamente, funcionalmente y documentalmente**.

La rama puede continuar hacia revision o integracion, considerando que esta validacion corresponde a la dockerizacion del backend y la base de datos PostgreSQL.

## 11. Evidencia

Archivos revisados:

- `README.md`
- `docs/api/api-contract.md`
- `docs/week-05/session-02/session-02.md`
- `docs/week-05/session-02/qa-session-02.md`
- `docs/setup/guia-ejecucion-proyecto.md`
- `MediStock/Dockerfile`
- `MediStock/.dockerignore`
- `MediStock/compose.yaml`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/resources/application.yaml`

Resultado:

```text
Validacion documental: aprobada
Validacion tecnica: aprobada
Validacion funcional: aprobada
Pruebas automatizadas: aprobadas
Estado para integracion: listo para revision
```
