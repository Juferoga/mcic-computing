# Diagrama de Actividades - Gestionar Carreras (Mermaid)
## CU-01: Gestionar Carreras

---

## Descripción del Flujo

El usuario accede al panel de gestión de carreras y puede realizar tres acciones principales: **agregar**, **consultar** o **eliminar** una carrera. Cada acción tiene su propio subflujo con validaciones y manejo de errores, convergiendo todas en el fin del proceso.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Gestionar Carreras'"]
    A1 --> A2["Mostrar Panel de<br/>Gestión de Carreras"]
    A2 --> D1{"¿Qué acción desea<br/>realizar?"}

    D1 -->|AGREGAR| A3a["Mostrar form<br/>de nueva carrera"]
    D1 -->|CONSULTAR| A3b["Ingresar ID<br/>de carrera a buscar"]
    D1 -->|ELIMINAR| A3c["Seleccionar<br/>carrera de la lista"]

    A3a --> A4a["Llenar datos:<br/>- ID<br/>- Nombre<br/>- Registro"]
    A4a --> A5a["Validar datos<br/>(ID único, nombre no vacío)"]
    A5a --> A6a["Guardar en<br/>base de datos"]
    A6a --> D2a{"¿Guardado<br/>exitosamente?"}
    D2a -->|SÍ| A7a["Mostrar mensaje<br/>'Carrera creada'"]
    D2a -->|NO| A8a["Mostrar mensaje<br/>'Error al guardar'"]
    A7a --> Fin
    A8a --> Fin

    A3b --> A4b["Buscar en<br/>base de datos"]
    A4b --> D2b{"¿Carrera<br/>encontrada?"}
    D2b -->|SÍ| A5b["Mostrar<br/>datos de carrera"]
    D2b -->|NO| A6b["Mostrar mensaje<br/>'No encontrada'"]
    A5b --> Fin
    A6b --> Fin

    A3c --> A4c["Confirmar<br/>eliminación"]
    A4c --> A5c["Eliminar de<br/>base de datos"]
    A5c --> A6c["Mostrar mensaje<br/>'Carrera eliminada'"]
    A6c --> Fin

    Fin([FIN DEL PROCESO])
```

---

## Notas

- **Validación**: Se verifica que el ID sea único y el nombre no esté vacío antes de guardar.
- **Consulta**: Si la carrera no existe, se muestra un mensaje informativo.
- **Eliminación**: Se requiere confirmación explícita antes de ejecutar el DELETE.
- Las tres ramas convergen al final del proceso.

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
