# MediStock - QA - Semana 4 - Sesion 1

## Validacion del esqueleto funcional del backend

**Proyecto:** MediStock  
**Responsable:** Julian Alberto Trujillo Bonilla  
**Rama de trabajo:** feature/week4-session1-walking-skeleton-qa  
**Tipo de validacion:** Revision tecnica y documental  
**Sesion validada:** Semana 4 - Sesion 1  
**Arquitectura definida:** Monolito modular  
**Tecnologia validada:** Java con Spring Boot  

## 1. Objetivo de QA

Verificar que el entregable de la Semana 4 - Sesion 1 cree correctamente el primer esqueleto funcional del backend de MediStock.

Esta validacion confirma que el proyecto Spring Boot fue creado, que la aplicacion puede compilar, que existen endpoints minimos de salud y que la documentacion del avance se mantiene coherente con el estado real del proyecto.

## 2. Alcance de la revision

La validacion se enfoca en los siguientes archivos y carpetas:

- `README.md`
- `MediStock/pom.xml`
- `MediStock/src/main/java/com/MediStock/MediStock/MediStockApplication.java`
- `MediStock/src/main/java/com/MediStock/MediStock/health/HealthController.java`
- `MediStock/src/main/java/com/MediStock/MediStock/security/SecurityConfig.java`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/java/com/MediStock/MediStock/MediStockApplicationTests.java`
- `MediStock/src/test/java/com/MediStock/MediStock/health/HealthControllerTests.java`
- `docs/week-04/session-01/session-01.md`
- `docs/week-04/session-01/qa-session-01.md`

No se valida persistencia real, autenticacion con JWT ni operaciones de negocio porque no hacen parte del alcance de esta sesion.

## 3. Criterios de aceptacion

| ID | Criterio | Estado |
| --- | --- | --- |
| CA-001 | El proyecto Spring Boot existe dentro de la carpeta `MediStock` | Aprobado |
| CA-002 | El backend usa Java y Spring Boot como tecnologia base | Aprobado |
| CA-003 | El proyecto usa Maven como gestor de dependencias | Aprobado |
| CA-004 | La configuracion principal se encuentra en formato YAML | Aprobado |
| CA-005 | Existe una clase principal para iniciar la aplicacion | Aprobado |
| CA-006 | Existe un endpoint `GET /health` para validar disponibilidad | Aprobado |
| CA-007 | El endpoint `/health` responde con estado `UP` | Aprobado |
| CA-008 | Actuator expone el endpoint `/actuator/health` | Aprobado |
| CA-009 | La configuracion de seguridad permite acceso publico a los endpoints de salud | Aprobado |
| CA-010 | El resto de endpoints queda protegido por defecto | Aprobado |
| CA-011 | Existe prueba automatizada para validar carga de contexto | Aprobado |
| CA-012 | Existe prueba automatizada para validar el endpoint `/health` | Aprobado |
| CA-013 | La persistencia queda documentada como pendiente | Aprobado |
| CA-014 | El README refleja el nuevo estado del proyecto | Aprobado |
| CA-015 | Los archivos Markdown del proyecto se mantienen en espanol | Aprobado |

## 4. Checklist de revision

- [x] Se identifica la rama QA de la sesion.
- [x] Se revisa la estructura inicial del backend.
- [x] Se valida la existencia del archivo `pom.xml`.
- [x] Se revisan las dependencias iniciales del proyecto.
- [x] Se valida la configuracion de `application.yaml`.
- [x] Se revisa el controlador de salud.
- [x] Se revisa la configuracion minima de seguridad.
- [x] Se confirma que `/health` queda publico.
- [x] Se confirma que `/actuator/health` queda publico.
- [x] Se valida que no se implementa persistencia real en esta fase.
- [x] Se revisan las pruebas automatizadas creadas.
- [x] Se valida que el README incluya el avance de Semana 4 - Sesion 1.
- [x] Se revisa que la documentacion de la sesion explique el alcance y las limitaciones.

## 5. Pruebas automatizadas ejecutadas

Comando usado desde la carpeta `MediStock`:

```text
mvn test
```

Resultado esperado y validado:

```text
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
Build: SUCCESS
```

Las pruebas cubren:

- carga del contexto de Spring Boot;
- respuesta correcta del endpoint `/health`;
- validacion del estado `UP`;
- validacion del nombre de aplicacion `MediStock`.

## 6. Pruebas manuales sugeridas con Postman

Para ejecutar estas pruebas se debe iniciar el backend:

```text
./mvnw spring-boot:run
```

En Windows PowerShell:

```text
.\mvnw.cmd spring-boot:run
```

Luego se pueden realizar las siguientes peticiones:

| Metodo | Endpoint | Resultado esperado |
| --- | --- | --- |
| GET | `http://localhost:8080/health` | Codigo 200 y estado `UP` |
| GET | `http://localhost:8080/actuator/health` | Codigo 200 y estado `UP` |

Ejemplo de respuesta esperada para `/health`:

```json
{
  "status": "UP",
  "application": "MediStock",
  "timestamp": "fecha-hora"
}
```

## 7. Observaciones

El entregable cumple con el objetivo de la Semana 4 - Sesion 1 porque el proyecto deja de ser solo documental y pasa a tener una base tecnica ejecutable.

La decision de iniciar con un endpoint de salud es adecuada porque permite validar que el servidor arranca, responde y puede ser probado antes de agregar reglas de negocio, persistencia, autenticacion real o integraciones externas.

La configuracion de seguridad es minima y temporal. Su funcion en esta fase es permitir los endpoints de salud y proteger el resto de rutas por defecto. La autenticacion real se implementara en una sesion posterior.

## 8. Riesgos pendientes

| Riesgo | Estado | Accion futura |
| --- | --- | --- |
| No existe autenticacion real | Pendiente | Implementar flujo de autenticacion y autorizacion en una fase posterior |
| No existe JWT | Pendiente | Agregar soporte JWT cuando se implemente seguridad real |
| No existe persistencia con base de datos | Pendiente | Agregar PostgreSQL, JPA y migraciones cuando se implemente el modelo de datos |
| No existen controladores de negocio | Pendiente | Crear endpoints de catalogo e inventario en las siguientes sesiones |
| No existen entidades de dominio implementadas | Pendiente | Implementarlas cuando se avance hacia los modulos internos |
| No existe Docker Compose activo | Pendiente | Incorporarlo cuando sea necesaria la base de datos local |

## 9. Resultado de QA

El entregable de la Semana 4 - Sesion 1 queda **aprobado tecnicamente y documentalmente**.

La rama puede continuar hacia revision o integracion, considerando que el alcance validado corresponde al esqueleto funcional inicial del backend.

## 10. Evidencia

Archivos revisados:

- `README.md`
- `MediStock/pom.xml`
- `MediStock/src/main/java/com/MediStock/MediStock/health/HealthController.java`
- `MediStock/src/main/java/com/MediStock/MediStock/security/SecurityConfig.java`
- `MediStock/src/main/resources/application.yaml`
- `MediStock/src/test/java/com/MediStock/MediStock/health/HealthControllerTests.java`
- `docs/week-04/session-01/session-01.md`
- `docs/week-04/session-01/qa-session-01.md`

Resultado:

```text
Validacion documental: aprobada
Validacion tecnica: aprobada
Pruebas automatizadas: aprobadas
Estado para integracion: listo para revision
```
