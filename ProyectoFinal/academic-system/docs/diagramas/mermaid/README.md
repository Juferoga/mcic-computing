# Diagramas en Mermaid

Colección de diagramas del Sistema de Gestión Académica renderizables directamente en GitHub/GitLab mediante [Mermaid](https://mermaid.js.org/).

> Los archivos originales en formato ASCII/texto se conservan en los directorios padre (`diagramas/`, `actividades/`, `secuencia/`).

---

## Modelado de Datos

| Diagrama | Archivo | Tipo |
|----------|---------|------|
| Entidad-Relación | [01_diagrama_ER_mermaid.md](./01_diagrama_ER_mermaid.md) | `erDiagram` |
| Relacional (BD) | [02_diagrama_relacional_mermaid.md](./02_diagrama_relacional_mermaid.md) | `erDiagram` |

## Diagramas UML

| Diagrama | Archivo | Tipo |
|----------|---------|------|
| Clases | [03_diagrama_clases_mermaid.md](./03_diagrama_clases_mermaid.md) | `classDiagram` |
| Casos de Uso | [04_diagrama_casos_uso_mermaid.md](./04_diagrama_casos_uso_mermaid.md) | `useCase` |

## Diagramas de Comportamiento

### Actividades

| Caso de Uso | Archivo | Tipo |
|-------------|---------|------|
| Gestionar Carreras | [05_actividad_gestionar_carreras_mermaid.md](./05_actividad_gestionar_carreras_mermaid.md) | `flowchart TD` |
| Gestionar Estudiantes | [06_actividad_gestionar_estudiantes_mermaid.md](./06_actividad_gestionar_estudiantes_mermaid.md) | `flowchart TD` |
| Gestionar Asignaturas | [07_actividad_gestionar_asignaturas_mermaid.md](./07_actividad_gestionar_asignaturas_mermaid.md) | `flowchart TD` |
| Gestionar Profesores | [08_actividad_gestionar_profesores_mermaid.md](./08_actividad_gestionar_profesores_mermaid.md) | `flowchart TD` |
| Gestionar Inscripciones | [09_actividad_gestionar_inscripciones_mermaid.md](./09_actividad_gestionar_inscripciones_mermaid.md) | `flowchart TD` |
| Registrar Notas | [10_actividad_registrar_notas_mermaid.md](./10_actividad_registrar_notas_mermaid.md) | `flowchart TD` |

### Secuencia

| Caso de Uso | Archivo | Tipo |
|-------------|---------|------|
| Gestionar Carreras | [11_secuencia_gestionar_carreras_mermaid.md](./11_secuencia_gestionar_carreras_mermaid.md) | `sequenceDiagram` |
| Gestionar Estudiantes | [12_secuencia_gestionar_estudiantes_mermaid.md](./12_secuencia_gestionar_estudiantes_mermaid.md) | `sequenceDiagram` |
| Gestionar Inscripciones | [15_secuencia_gestionar_inscripciones_mermaid.md](./15_secuencia_gestionar_inscripciones_mermaid.md) | `sequenceDiagram` |
| Registrar Notas | [16_secuencia_registrar_notas_mermaid.md](./16_secuencia_registrar_notas_mermaid.md) | `sequenceDiagram` |

---

## Cómo visualizar

GitHub renderiza los bloques ` ```mermaid ` automáticamente. Abrí cualquier archivo `.md` de esta carpeta y los diagramas se mostrarán como imágenes vectoriales.

Para editar o previsualizar localmente, podés usar:
- [Mermaid Live Editor](https://mermaid.live/)
- Extensiones de VS Code: *Markdown Preview Mermaid Support*

---

**Versión**: 1.0  
**Fecha**: 10 de mayo de 2026
