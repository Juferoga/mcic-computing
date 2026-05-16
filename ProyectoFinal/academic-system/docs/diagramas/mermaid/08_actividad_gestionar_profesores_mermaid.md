# Diagrama de Actividades - Gestionar Profesores (Mermaid)
## CU-04: Gestionar Profesores

---

## Descripción del Flujo

El usuario accede al panel de gestión de profesores y puede realizar tres acciones principales: **agregar**, **consultar** o **eliminar** un profesor. Cada acción tiene su propio subflujo con validaciones y manejo de errores. La eliminación puede disparar eliminación en cascada de sus asignaciones.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Gestionar Profesores'"]
    A1 --> A2["Mostrar Panel de<br/>Gestión de Profesores"]
    A2 --> D1{"¿Qué acción desea<br/>realizar?"}

    D1 -->|AGREGAR| A3a["Mostrar form<br/>de nuevo profesor"]
    D1 -->|CONSULTAR| A3b["Ingresar ID<br/>de profesor a buscar"]
    D1 -->|ELIMINAR| A3c["Seleccionar<br/>profesor de la lista"]

    A3a --> A4a["Llenar datos:<br/>- ID<br/>- Nombre<br/>- Dirección<br/>- Teléfono<br/>- Profesión"]
    A4a --> A5a["Validar datos<br/>(ID único,<br/>nombre no vacío)"]
    A5a --> A6a["Guardar en<br/>base de datos"]
    A6a --> D2a{"¿Guardado<br/>exitosamente?"}
    D2a -->|SÍ| A7a["Mostrar mensaje<br/>'Profesor creado'"]
    D2a -->|NO| A8a["Mostrar mensaje<br/>'Error al guardar'"]
    A7a --> Fin
    A8a --> Fin

    A3b --> A4b["Buscar en<br/>base de datos"]
    A4b --> D2b{"¿Profesor<br/>encontrado?"}
    D2b -->|SÍ| A5b["Mostrar<br/>datos del profesor"]
    D2b -->|NO| A6b["Mostrar mensaje<br/>'No encontrado'"]
    A5b --> Fin
    A6b --> Fin

    A3c --> A4c["Confirmar<br/>eliminación"]
    A4c --> A5c["Eliminar de<br/>base de datos"]
    A5c --> A6c["Mostrar mensaje<br/>'Profesor eliminado'"]
    A6c --> Fin

    Fin([FIN DEL PROCESO])
```

---

## Notas

- **Campos opcionales**: Dirección, teléfono y profesión son opcionales.
- **Integridad referencial**: Al eliminar un profesor, se eliminan también sus asignaciones en la tabla `Imparte`.
- **Carga académica**: Si el profesor tiene carga académica, se debe advertir y confirmar la eliminación en cascada.
- Las tres ramas convergen al final del proceso.

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
