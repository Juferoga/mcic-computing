# Diagrama de Actividades - Gestionar Inscripciones (Mermaid)
## CU-06: Gestionar Inscripciones

---

## Descripción del Flujo

El usuario accede al panel de inscripciones y puede realizar tres acciones principales: **inscribir estudiante**, **consultar inscripciones** o **cancelar inscripción**. La inscripción incluye validación de prerrequisitos mediante un stored procedure (`sp_inscribir_estudiante`) que verifica que el estudiante haya aprobado todas las asignaturas requeridas.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Gestionar Inscripciones'"]
    A1 --> A2["Mostrar Panel de<br/>Inscripciones"]
    A2 --> D1{"¿Qué acción desea<br/>realizar?"}

    D1 -->|INSCRIBIR ESTUDIANTE| A3a["Seleccionar<br/>estudiante a inscribir"]
    D1 -->|CONSULTAR INSCRIPCIONES| A3b["Ingresar<br/>código de estudiante"]
    D1 -->|CANCELAR INSCRIPCIÓN| A3c["Seleccionar<br/>inscripción a cancelar"]

    A3a --> A4a["Seleccionar<br/>asignatura y grupo"]
    A4a --> A5a["Validar prerrequisitos<br/>(llamar SP)"]
    A5a --> D2a{"¿Resultado<br/>validación?"}
    D2a -->|APROBADO| A6a["Invocar<br/>sp_inscribir_estudiante()"]
    D2a -->|RECHAZADO| A7a["Mostrar mensaje<br/>'No cumple prerreq.'"]
    A7a --> Fin

    A6a --> A8a["Insertar en<br/>tabla Inscribe"]
    A8a --> D3a{"¿Inscripción<br/>exitosa?"}
    D3a -->|SÍ| A9a["Mostrar mensaje<br/>'Inscrito correctam.'"]
    D3a -->|NO| A10a["Mostrar mensaje<br/>'Error al inscribir'"]
    A9a --> Fin
    A10a --> Fin

    A3b --> A4b["Mostrar lista de<br/>inscripciones del estudiante"]
    A4b --> A5b["Seleccionar<br/>inscripción"]
    A5b --> A6b["Mostrar<br/>detalle de inscripción"]
    A6b --> Fin

    A3c --> A4c["Confirmar<br/>cancelación"]
    A4c --> A5c["Eliminar de<br/>base de datos"]
    A5c --> A6c["Mostrar mensaje de<br/>cancelación exitosa"]
    A6c --> Fin

    Fin([FIN DEL PROCESO])
```

---

## Notas

- **Stored Procedure**: `sp_inscribir_estudiante` verifica que el estudiante tenga aprobados (def >= 3.0) todos los prerrequisitos de la asignatura.
- **No duplicidad**: Un estudiante no puede inscribirse dos veces a la misma asignatura/grupo.
- **Carga académica**: Solo se puede inscribir a grupos que tengan profesor asignado.
- **Notas iniciales**: Al inscribirse, `n1`, `n2`, `n3` y `def` se inicializan en `0.00`.
- **Trigger**: `trg_calculo_notas` calcula la definitiva automáticamente al actualizar notas.

---

## Flujo del Stored Procedure

```
PROCEDIMIENTO: sp_inscribir_estudiante
─────────────────────────────────────────

1. Obtener lista de prerrequisitos de la asignatura a inscribir
   SELECT cod_a_r FROM Requiere WHERE cod_a = p_cod_a

2. Para cada prerrequisito:
   2.1 Verificar si el estudiante tiene inscripción aprobada en ese prerreq.
       SELECT EXISTS (
           SELECT 1 FROM Inscribe
           WHERE cod_e = p_cod_e AND cod_a = v_prereq AND def >= 3.0
       )

   2.2 Si no está aprobada (def < 3.0 o no existe):
       RAISE EXCEPTION 'Fallo de inscripción: Estudiante X no ha
                        aprobado el prerrequisito Y'

3. Si todos los prerrequisitos están aprobados:
   INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo)
   VALUES (p_cod_e, p_cod_a, p_id_p, p_grupo)

4. Confirmar éxito:
   RAISE NOTICE 'Inscripción realizada con éxito'
```

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
