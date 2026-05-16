# Diagrama de Actividades - Gestionar Inscripciones
## CU-06: Gestionar Inscripciones

---

## 1. Diagrama de Actividades

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         INICIO DEL PROCESO                                  │
└────────────────────────────────┬────────────────────────────────────────────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │  Seleccionar opción   │
                    │"Gestionar Inscripciones"│
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar Panel de    │
                    │  Inscripciones        │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  ¿Qué acción desea    │
                    │       realizar?       │
                    └───────────┬────────────┘
                                │
           ┌────────────────────┼────────────────────┐
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │  INSCRIBIR    │    │   CONSULTAR   │    │  CANCELAR    │
   │   ESTUDIANTE  │    │  INSCRIPCIONES│    │  INSCRIPCIÓN │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Seleccionar   │    │ Ingresar     │    │ Seleccionar   │
   │ estudiante    │    │ código de    │    │ inscripción   │
   │ a inscribir   │    │ estudiante    │    │ a cancelar    │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Seleccionar   │    │ Mostrar lista │    │ Confirmar     │
   │ asignatura    │    │ de inscripciones│  │ cancelación   │
   │ y grupo       │    │ del estudiante│    └───────┬───────┘
   └───────┬───────┘    └───────┬───────┘            │
           │                    │                    ▼
           ▼                    ▼            ┌───────────────┐
   ┌───────────────┐    ┌───────────────┐    │ Eliminar de   │
   │ Validar       │    │ Seleccionar   │    │ base de datos │
   │ prerrequisitos│    │ inscripción   │    └───────┬───────┘
   │ (llamar SP)   │    └───────┬───────┘            │
   └───────┬───────┘            │                    ▼
           │                    ▼            ┌───────────────┐
    ┌──────┴──────┐     ┌───────────────┐    │ Mostrar       │
    │             │     │ Mostrar       │    │ mensaje de    │
    ▼             ▼     │ detalle de    │    │ cancelación    │
  APROBADO   RECHAZADO │   inscripción  │    │ exitosa       │
    │             │     └───────────────┘    └───────────────┘
    ▼             ▼
┌───────────┐ ┌───────────┐
│ Invocar   │ │ Mostrar   │
│ sp_inscr │ │ mensaje   │
│ _estudian │ │ "No       │
│ te()      │ │ cumple   │
└───────┬───┘ │ prerreq." │
        │     └───────────┘
        ▼
┌───────────────┐
│ Insertar en  │
│ tabla Inscribe│
└───────┬───────┘
        │
        ▼
┌───────────────┐
│ ¿Inscripción  │
│ exitosa?      │
└───────┬───────┘
   ┌────┴────┐
   │         │
   ▼         ▼
 SÍ         NO
   │         │
   ▼         ▼
┌───────────┐ ┌───────────┐
│ Mostrar   │ │ Mostrar   │
│ mensaje   │ │ mensaje   │
│ "Inscrito │ │ "Error al │
│ correctam."│ │ inscribir"│
└───────────┘ └───────────┘
        │
        ▼
┌───────────────────────────────────────────┐
│            FIN DEL PROCESO                 │
└───────────────────────────────────────────┘
```

---

## 2. Actividades del Flujo

| ID | Actividad | Descripción |
|----|-----------|-------------|
| A1 | Seleccionar opción | El usuario hace clic en "Gestionar Inscripciones" |
| A2 | Mostrar panel | Se carga la interfaz con lista de inscripciones |
| A3 | Seleccionar acción | El usuario elige: inscribir, consultar o cancelar |
| A4 | Seleccionar estudiante | El usuario busca y selecciona un estudiante |
| A5 | Seleccionar asignatura/grupo | El usuario elige la asignatura y grupo disponible |
| A6 | Validar prerrequisitos | Se invoca el SP `sp_inscribir_estudiante` en PostgreSQL |
| A7 | Verificar aprobación | El SP valida que el estudiante cumpla los prerrequisitos |
| A8 | Insertar inscripción | Se registra en la tabla Inscribe con notas en 0.00 |
| A9 | Buscar estudiante | Se consultan las inscripciones por código de estudiante |
| A10 | Mostrar lista | Se exiben todas las inscripciones del estudiante |
| A11 | Seleccionar inscripción | El usuario elige una inscripción para ver detalles |
| A12 | Confirmar cancelación | Se pide confirmación antes de cancelar |
| A13 | Eliminar inscripción | Se ejecuta el DELETE en la base de datos |

---

## 3. Reglas de Negocio Aplicadas

1. **Prerrequisitos obligatorios**: El estudiante debe aprobar (def >= 3.0) todas las asignaturas requeridas
2. **No duplicidad**: Un estudiante no puede inscribirse dos veces a la misma asignatura/grupo
3. **Carga académica existente**: Solo se puede inscribir a grupos que tengan profesor asignado
4. **Notas iniciales**: Al inscribirse, n1, n2, n3 y def se inicializan en 0.00
5. **Cálculo automático**: El trigger `trg_calculo_notas` calcula la definitiva al actualizar notas

---

## 4. Flujo del Stored Procedure

```
┌─────────────────────────────────────────────────────────────────────────────┐
│              PROCEDIMIENTO: sp_inscribir_estudiante                       │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  1. Obtener lista de prerrequisitos de la asignatura a inscribir           │
│     SELECT cod_a_r FROM Requiere WHERE cod_a = p_cod_a                     │
│                                                                             │
│  2. Para cada prerrequisito:                                               │
│     ┌──────────────────────────────────────────────────────────────────┐    │
│     │ 2.1 Verificar si el estudiante tiene inscripción en ese prerreq.│    │
│     │     SELECT EXISTS (SELECT 1 FROM Inscribe                       │
│     │         WHERE cod_e = p_cod_e AND cod_a = v_prereq AND def >= 3.0│   │
│     │                                                                     │
│     │ 2.2 Si no está aprobada (def < 3.0 o no existe):                  │
│     │     RAISE EXCEPTION 'Fallo de inscripción: Estudiante X no        │
│     │          ha aprobado el prerrequisito Y'                            │
│     └──────────────────────────────────────────────────────────────────┘    │
│                                                                             │
│  3. Si todos los prerrequisitos están aprobados:                          │
│     INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo)                       │
│     VALUES (p_cod_e, p_cod_a, p_id_p, p_grupo)                             │
│                                                                             │
│  4. Confirmar éxito: RAISE NOTICE 'Inscripción realizada con éxito'        │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 5. Excepciones

| Código | Descripción | Acción |
|--------|-------------|--------|
| E01 | Prerrequisito no aprobado | Mostrar mensaje indicando cuál prerrequisito falta |
| E02 | Inscripción duplicada | Mostrar mensaje indicando que ya está inscrito |
| E03 | Grupo sin profesor asignado | Mostrar mensaje indicando que no hay carga académica |
| E04 | Estudiante no existe | Mostrar mensaje de error |
| E05 | Asignatura no existe | Mostrar mensaje de error |
| E06 | Error de conexión a BD | Mostrar mensaje de error |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026