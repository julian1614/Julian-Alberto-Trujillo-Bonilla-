# MediStock

**Responsable:** Julian Alberto Trujillo Bonilla  
**Programa:** Ingenieria de Sistemas  
**Asignatura:** Sistemas Distribuidos  
**Arquitectura objetivo:** Monolito modular  
**Estado actual:** API inicial con backend y base de datos dockerizados

MediStock es un sistema de gestion de inventario de medicamentos orientado a controlar existencias, lotes, fechas de vencimiento, entradas, salidas, proveedores, alertas y reportes.

El proyecto se desarrollara por semanas y sesiones, conservando evidencia documental de las decisiones tomadas antes de avanzar a implementaciones tecnicas.

## Problematica

En farmacias, clinicas, hospitales o instituciones de salud, el control de medicamentos puede realizarse de forma manual o mediante hojas de calculo. Esta forma de trabajo puede generar errores en el registro de cantidades, perdida de informacion, dificultad para identificar medicamentos proximos a vencer y poca trazabilidad sobre los movimientos de inventario.

El problema principal es que la institucion puede no conocer con precision cuantas unidades reales tiene disponibles, que lotes estan cerca de vencer, que productos necesitan reposicion o quien realizo una entrada o salida determinada.

Cuando estas operaciones se trasladan a un sistema conectado por red, tambien aparecen riesgos propios de los sistemas distribuidos: peticiones duplicadas, respuestas perdidas, fallos parciales, concurrencia y datos desactualizados. Por eso, MediStock debe disenar cuidadosamente sus operaciones criticas de inventario.

## Objetivo general

Desarrollar un sistema que permita administrar y controlar el inventario de medicamentos, proporcionando informacion actualizada sobre cantidades, lotes, fechas de vencimiento, movimientos, proveedores y alertas.

## Objetivos especificos

- Registrar y consultar medicamentos.
- Controlar lotes y fechas de vencimiento.
- Registrar entradas y salidas de inventario.
- Evitar cantidades negativas o salidas superiores al inventario disponible.
- Generar alertas de stock bajo.
- Generar alertas de medicamentos proximos a vencer.
- Gestionar proveedores.
- Registrar trazabilidad de movimientos.
- Generar reportes de inventario.
- Controlar usuarios y permisos segun rol.

## Alcance inicial

El alcance inicial de MediStock se centrara en la definicion del problema, analisis del dominio, decisiones arquitectonicas, diseno del backlog y construccion progresiva de una primera version funcional.

Durante las primeras sesiones no se incorporaran tecnologias innecesarias. Cada avance debera estar justificado por el contenido de la semana correspondiente y por una necesidad real del proyecto.

## Actores principales

| Actor | Responsabilidad principal |
| --- | --- |
| Administrador | Gestionar usuarios, medicamentos, proveedores y configuracion |
| Encargado de inventario | Registrar entradas, salidas y consultar existencias |
| Supervisor | Consultar inventario, vencimientos, movimientos y reportes |
| Usuario de consulta | Consultar informacion basica del inventario |

## Funcionalidades principales

- Inicio de sesion y control de acceso.
- Gestion de medicamentos.
- Gestion de lotes.
- Registro de entradas de inventario.
- Registro de salidas de inventario.
- Alertas de bajo inventario.
- Alertas de vencimiento.
- Gestion de proveedores.
- Busqueda y filtros.
- Reportes.

## Enfoque arquitectonico

La arquitectura objetivo del proyecto es un **monolito modular**.

Esta decision permite construir una aplicacion organizada por responsabilidades internas sin asumir desde el inicio la complejidad de microservicios. La justificacion formal de esta arquitectura se documentara en la semana correspondiente mediante un ADR.

Division conceptual inicial:

```text
MediStock
+-- Acceso e identidad
+-- Catalogo
+-- Inventario
+-- Proveedores
+-- Alertas
+-- Reportes
```

## Consideraciones de sistemas distribuidos

Aunque MediStock iniciara como monolito modular, el sistema debera considerar escenarios donde existan fronteras de red, por ejemplo entre usuario, aplicacion y base de datos.

Decisiones iniciales:

- El inventario requiere consistencia fuerte para evitar cantidades negativas o inconsistentes.
- Las operaciones de entrada y salida deben considerar idempotencia para manejar reintentos.
- Las alertas de stock bajo y vencimiento pueden admitir consistencia eventual.
- No se incorporaran tecnologias distribuidas innecesarias sin una justificacion concreta.
- La arquitectura objetivo sera monolito modular; su justificacion formal se realizara en la semana correspondiente.

## Estructura de trabajo

```text
docs/
+-- adr/
|   +-- ADR-001-monolito-modular.md
+-- api/
|   +-- api-contract.md
+-- setup/
|   +-- guia-ejecucion-proyecto.md
+-- week-01/
|   +-- session-01/
|   |   +-- session-01.md
|   +-- session-02/
|       +-- session-02.md
+-- week-02/
|   +-- session-01/
|   |   +-- session-01.md
|   +-- session-02/
|       +-- session-02.md
+-- week-03/
    +-- session-01/
    |   +-- session-01.md
    +-- session-02/
        +-- session-02.md
+-- week-04/
    +-- session-01/
        +-- session-01.md
    +-- session-02/
        +-- session-02.md
+-- week-05/
    +-- session-01/
        +-- session-01.md
    +-- session-02/
        +-- session-02.md
MediStock/
+-- Dockerfile
+-- compose.yaml
+-- pom.xml
+-- src/
```

## Avance por sesiones

| Semana | Sesion | Entregable | Estado |
| --- | --- | --- | --- |
| Semana 1 | Sesion 1 | Fundamentos de sistemas distribuidos aplicados a MediStock | Completado |
| Semana 1 | Sesion 2 | Fundamentos de ingenieria aplicados a MediStock | Completado |
| Semana 2 | Sesion 1 | Estudio de alternativas arquitectonicas | Completado |
| Semana 2 | Sesion 2 | Contextos delimitados y decision arquitectonica | Completado |
| Semana 3 | Sesion 1 | Diseno de dominio y arquitectura hexagonal | Completado |
| Semana 3 | Sesion 2 | Diseno de modulos, datos y contratos | Completado |
| Semana 4 | Sesion 1 | Construccion del servicio y esqueleto funcional | Completado |
| Semana 4 | Sesion 2 | Primeros endpoints de negocio para medicamentos y proveedores | Completado |
| Semana 5 | Sesion 1 | Persistencia real con base de datos para medicamentos y proveedores | Completado |
| Semana 5 | Sesion 2 | Dockerizacion del backend y base de datos | Completado |

## Documentos principales

- [Especificacion inicial del sistema](Sistema%20de%20Gesti%C3%B3n%20de%20Inventario%20de%20Medicamentos.md)
- [Semana 1 - Sesion 1](docs/week-01/session-01/session-01.md)
- [Semana 1 - Sesion 2](docs/week-01/session-02/session-02.md)
- [Semana 2 - Sesion 1](docs/week-02/session-01/session-01.md)
- [Semana 2 - Sesion 2](docs/week-02/session-02/session-02.md)
- [Semana 3 - Sesion 1](docs/week-03/session-01/session-01.md)
- [Semana 3 - Sesion 2](docs/week-03/session-02/session-02.md)
- [Semana 4 - Sesion 1](docs/week-04/session-01/session-01.md)
- [Semana 4 - Sesion 2](docs/week-04/session-02/session-02.md)
- [Semana 5 - Sesion 1](docs/week-05/session-01/session-01.md)
- [Semana 5 - Sesion 2](docs/week-05/session-02/session-02.md)
- [Guia de ejecucion del proyecto](docs/setup/guia-ejecucion-proyecto.md)
- [Contrato inicial de API](docs/api/api-contract.md)
- [ADR-001 - Monolito modular](docs/adr/ADR-001-monolito-modular.md)

## Estado actual del proyecto

| Elemento | Estado |
| --- | --- |
| Problema del negocio | Definido |
| Objetivos del sistema | Definidos |
| Requisitos iniciales | Definidos |
| Reglas de negocio | Definidas |
| Backlog inicial | Definido en Semana 1 - Sesion 1 |
| Estrategia inicial de pruebas | Definida en Semana 1 - Sesion 2 |
| Flujo Git del proyecto | Definido en Semana 1 - Sesion 2 |
| Uso de ADR | Definido en Semana 1 - Sesion 2 |
| Estudio de alternativas arquitectonicas | Definido en Semana 2 - Sesion 1 |
| Contextos delimitados iniciales | Definidos en Semana 2 - Sesion 2 |
| Diseno de dominio inicial | Definido en Semana 3 - Sesion 1 |
| Arquitectura hexagonal conceptual | Definida en Semana 3 - Sesion 1 |
| Diseno de modulos, datos y contratos | Definido en Semana 3 - Sesion 2 |
| Arquitectura objetivo | Monolito modular |
| ADR de arquitectura | Definido en ADR-001 |
| Tecnologia del servidor de aplicacion | Java con Spring Boot |
| Implementacion del servidor de aplicacion | API inicial con persistencia JPA |
| Endpoint de salud | Implementado |
| API de medicamentos | Implementada con persistencia JPA |
| API de proveedores | Implementada con persistencia JPA |
| Base de datos | Configurada para PostgreSQL |
| Pruebas automatizadas | Pruebas de salud, medicamentos, proveedores y persistencia |
| Docker | Backend y PostgreSQL configurados con Docker Compose |
| Primera version funcional | API inicial de negocio con persistencia y contenedores |

## Ejecucion con Docker

Desde la carpeta `MediStock`:

```powershell
docker compose up --build
```

Servicios expuestos:

| Servicio | URL o puerto |
| --- | --- |
| Backend | `http://localhost:8080` |
| PostgreSQL | `localhost:5433` |

Credenciales de base de datos:

```text
Database: medistock
Username: medistock
Password: medistock
```

## Forma de trabajo

El repositorio se trabajara por ramas desde `Develop`. Cada sesion generara evidencia documental o tecnica segun corresponda.

Flujo recomendado:

```text
main
  |
QA
  |
Develop
  |
rama de trabajo por sesion
```

Los commits se escribiran en ingles. Los archivos Markdown del proyecto se mantendran en espanol.
