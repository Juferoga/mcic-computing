# Diagrama de Actividades - Gestionar Asignaturas (Mermaid)
## CU-03: Gestionar Asignaturas

---

## Descripción del Flujo

El usuario accede al panel de gestión de asignaturas y puede realizar tres acciones principales: **agregar**, **consultar** o **eliminar** una asignatura. Cada acción tiene su propio subflujo con validaciones específicas de rangos numéricos y manejo de errores.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Gestionar Asignaturas'"]
    A1 --> A2["Mostrar Panel de<br/>Gestión de Asignaturas"]
    A2 --> D1{"¿Qué acción desea<br/>realizar?"}

    D1 -->|AGREGAR| A3a["Mostrar form<br/>de nueva asignatura"]
    D1 -->|CONSULTAR| A3b["Ingresar<br/>código de asignatura"]
    D1 -->|ELIMINAR| A3c["Seleccionar<br/>asignatura de la lista"]

    A3a --> A4a["Llenar datos:<br/>- Código<br/>- Nombre<br/>- Intensidad<br/>- Créditos"]
    A4a --> A5a["Validar datos<br/>(código único,<br/>nombre no vacío)"]
    A5a --> A6a["Guardar en<br/>base de datos"]
    A6a --> D2a{"¿Guardado<br/>exitosamente?"}
    D2a -->|SÍ| A7a["Mostrar mensaje<br/>'Asignatura creada'"]
    D2a -->|NO| A8a["Mostrar mensaje<br/>'Error al guardar'"]
    A7a --> Fin
    A8a --> Fin

    A3b --> A4b["Buscar en<br/>base de datos"]
    A4b --> D2b{"¿Asignatura<br/>encontrada?"}
    D2b -->|SÍ| A5b["Mostrar<br/>datos de asignatura"]
    D2b -->|NO| A6b["Mostrar mensaje<br/>'No encontrada'"]
    A5b --> Fin
    A6b --> Fin

    A3c --> A4c["Confirmar<br/>eliminación"]
    A4c --> A5c["Eliminar de<br/>base de datos"]
    A5c --> A6c["Mostrar mensaje<br/>'Asignatura eliminada'"]
    A6c --> Fin

    Fin([FIN DEL PROCESO])
```

---

## Notas

- **Validación numérica**: Intensidad horaria debe ser entre 0-20; créditos entre 1-10.
- **Integridad referencial**: No se puede eliminar una asignatura si tiene prerrequisitos o está en el pensum.
- Las tres ramas convergen al final del proceso.

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
