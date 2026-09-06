# MediStock

**Responsable:** Julian Alberto Trujillo Bonilla  
**Asignatura:** Sistemas Distribuidos  
**Arquitectura objetivo:** Monolito modular  
**Estado actual:** base documental del proyecto

MediStock es un sistema de gestion de inventario de medicamentos orientado a controlar existencias, lotes, fechas de vencimiento, entradas, salidas, proveedores, alertas y reportes.

El proyecto se desarrollara por semanas y sesiones, conservando evidencia documental de las decisiones tomadas antes de avanzar a implementaciones tecnicas.

## Estructura de trabajo

```text
docs/
+-- week-01/
    +-- session-01.md
```

## Entregables

| Semana | Sesion | Entregable | Estado |
| --- | --- | --- | --- |
| Semana 1 | Sesion 1 | Fundamentos de sistemas distribuidos aplicados a MediStock | Completado |

## Documentos principales

- [Especificacion inicial del sistema](Sistema%20de%20Gesti%C3%B3n%20de%20Inventario%20de%20Medicamentos.md)
- [Semana 1 - Sesion 1](docs/week-01/session-01.md)

## Decisiones iniciales

- El inventario requiere consistencia fuerte para evitar cantidades negativas o inconsistentes.
- Las operaciones de entrada y salida deben considerar idempotencia para manejar reintentos.
- Las alertas de stock bajo y vencimiento pueden admitir consistencia eventual.
- No se incorporaran tecnologias distribuidas innecesarias sin una justificacion concreta.
- La arquitectura objetivo sera monolito modular; su justificacion formal se realizara en la semana correspondiente.
