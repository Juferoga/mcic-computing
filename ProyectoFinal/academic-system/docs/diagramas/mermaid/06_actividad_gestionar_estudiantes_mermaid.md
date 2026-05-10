# Diagrama de Actividades - Gestionar Estudiantes (Mermaid)
## CU-02: Gestionar Estudiantes

---

## Descripción del Flujo

El usuario accede al panel de gestión de estudiantes y puede realizar tres acciones principales: **agregar**, **consultar** o **eliminar** un estudiante. Cada acción tiene su propio subflujo con validaciones específicas, incluyendo verificación de carrera existente y manejo de errores.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Gestionar Estudiantes'"]
    A1 --> A2["Mostrar Panel de<br/>Gestión de Estudiantes"]
    A2 --> D1{"¿Qué acción desea<br/>realizar?"}

    D1 -->|AGREGAR| A3a["Mostrar form<br/>de nuevo estudiante"]
    D1 -->|CONSULTAR| A3b["Ingresar<br/>código de estudiante"]
    D1 -->|ELIMINAR| A3c["Seleccionar<br/>estudiante de la lista"]

    A3a --> A4a["Llenar datos:<br/>- Código<br/>- Nombre<br/>- Dirección<br/>- Teléfono<br/>- Fecha nac.<br/>- Carrera"]
    A4a --> A5a["Validar datos<br/>(código único,<br/>carrera existente)"]
    A5a --> A6a["Guardar en<br/>base de datos"]
    A6a --> D2a{"¿Guardado<br/>exitosamente?"}
    D2a -->|SÍ| A7a["Mostrar mensaje<br/>'Estudiante creado'"]
    D2a -->|NO| A8a["Mostrar mensaje<br/>'Error al guardar'"]
    A7a --> Fin
    A8a --> Fin

    A3b --> A4b["Buscar en<br/>base de datos"]
    A4b --> D2b{"¿Estudiante<br/>encontrado?"}
    D2b -->|SÍ| A5b["Mostrar<br/>datos del estudiante"]
    D2b -->|NO| A6b["Mostrar mensaje<br/>'No encontrado'"]
    A5b --> Fin
    A6b --> Fin

    A3c --> A4c["Confirmar<br/>eliminación"]
    A4c --> A5c["Eliminar de<br/>base de datos"]
    A5c --> A6c["Mostrar mensaje<br/>'Estudiante eliminado'"]
    A6c --> Fin

    Fin([FIN DEL PROCESO])
```

---

## Notas

- **Validación**: Se verifica código único, carrera existente y campos obligatorios.
- **Fecha de nacimiento**: Debe ser una fecha válida y no futura.
- **Eliminación en cascada**: Al eliminar un estudiante, se eliminan también sus inscripciones.
- Las tres ramas convergen al final del proceso.

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
