# Diagramas de Casos de Uso - Mermaid
## Sistema de Gestión Académica

### Descripción
Diagrama de casos de uso que representa las funcionalidades del sistema agrupadas por actor: Administrador (acceso total), Profesor (acceso limitado) y Estudiante (solo lectura).

```mermaid
useCaseDiagram
    actor "Administrador" as Admin
    actor "Profesor" as Prof
    actor "Estudiante" as Est

    package "Sistema de Gestión Académica" {
        usecase "CU-01\nGestionar Carreras" as CU01
        usecase "CU-02\nGestionar Estudiantes" as CU02
        usecase "CU-03\nGestionar Asignaturas" as CU03
        usecase "CU-04\nGestionar Profesores" as CU04
        usecase "CU-05\nAsignar Carga Académica" as CU05
        usecase "CU-06\nGestionar Inscripciones" as CU06
        usecase "CU-07\nRegistrar Notas" as CU07
        usecase "CU-08\nGestionar Prerrequisitos" as CU08
        usecase "CU-09\nConsultar Información" as CU09
        usecase "CU-10\nGestionar Pensum" as CU10
    }

    Admin --> CU01
    Admin --> CU02
    Admin --> CU03
    Admin --> CU04
    Admin --> CU05
    Admin --> CU06
    Admin --> CU07
    Admin --> CU08
    Admin --> CU09
    Admin --> CU10

    Prof --> CU07
    Prof --> CU09

    Est --> CU09
```

### Detalle por Actor

#### ADMINISTRADOR (Acceso Total)
- **CU-01 Gestionar Carreras**: Crear, consultar, modificar, eliminar.
- **CU-02 Gestionar Estudiantes**: Crear, consultar, modificar, eliminar.
- **CU-03 Gestionar Asignaturas**: Crear, consultar, modificar, eliminar.
- **CU-04 Gestionar Profesores**: Crear, consultar, modificar, eliminar.
- **CU-05 Asignar Carga Académica**: Asignar, consultar, modificar.
- **CU-06 Gestionar Inscripciones**: Inscribir, consultar, cancelar.
- **CU-07 Registrar Notas**: Actualizar n1, n2, n3.
- **CU-08 Gestionar Prerrequisitos**: Agregar, consultar, eliminar.
- **CU-09 Consultar Información**: Ver carreras, asignaturas, estudiantes.
- **CU-10 Gestionar Pensum**: Agregar, consultar, eliminar asignaturas de carrera.

#### PROFESOR (Acceso Limitado)
- **CU-07 Registrar Notas**: Actualizar n1, n2, n3.
- **CU-09 Consultar Información**: Ver carreras, asignaturas, estudiantes.

#### ESTUDIANTE (Solo Lectura)
- **CU-09 Consultar Información**:
  - Ver carreras disponibles.
  - Ver pensum de mi carrera.
  - Ver mis asignaturas inscritas.
  - Ver mis notas (n1, n2, n3, def).
  - Ver prerrequisitos de asignaturas.

### Matriz de Trazabilidad

| Caso de Uso | Admin | Profesor | Estudiante |
|-------------|-------|----------|------------|
| CU-01 Gestionar Carreras | ● | | |
| CU-02 Gestionar Estudiantes | ● | | |
| CU-03 Gestionar Asignaturas | ● | | |
| CU-04 Gestionar Profesores | ● | | |
| CU-05 Asignar Carga Académica | ● | | |
| CU-06 Gestionar Inscripciones | ● | | |
| CU-07 Registrar Notas | ● | ● | |
| CU-08 Gestionar Prerrequisitos | ● | | |
| CU-09 Consultar Información | ● | ● | ● |
| CU-10 Gestionar Pensum | ● | | |

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 9 de mayo de 2026
**Autor**: Proyecto Académico
