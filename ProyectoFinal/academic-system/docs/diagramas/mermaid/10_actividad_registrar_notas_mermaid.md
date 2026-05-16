# Diagrama de Actividades - Registrar Notas (Mermaid)
## CU-07: Registrar Notas

---

## Descripción del Flujo

El usuario (profesor o administrador) accede al panel de registro de notas, selecciona una asignatura y grupo, y luego califica a cada estudiante inscrito de forma iterativa. Para cada estudiante, se ingresan las tres notas parciales (`n1`, `n2`, `n3`), se valida que estén en el rango permitido (0.0 - 5.0), se actualizan en la base de datos, y el trigger `trg_calculo_notas` calcula automáticamente la nota definitiva.

---

## Diagrama Mermaid

```mermaid
flowchart TD
    Inicio([INICIO DEL PROCESO]) --> A1["Seleccionar opción<br/>'Registrar Notas'"]
    A1 --> A2["Mostrar Panel de<br/>Registro de Notas"]
    A2 --> A3["Seleccionar<br/>Asignatura y Grupo"]
    A3 --> A4["Mostrar lista de<br/>estudiantes inscritos<br/>con sus notas"]

    A4 --> A5["Seleccionar<br/>estudiante"]
    A5 --> D1{"¿Desea modificar<br/>las notas?"}

    D1 -->|NO| D2{"¿Continuar con otro<br/>estudiante?"}

    D1 -->|SÍ| A6["Ingresar notas:<br/>- n1 (corte 1 - 35%)<br/>- n2 (corte 2 - 35%)<br/>- n3 (corte 3 - 30%)"]
    A6 --> A7["Validar notas<br/>(entre 0.0 y 5.0)"]
    A7 --> A8["Actualizar en BD<br/>(UPDATE Inscribe)<br/>- Se actualizan n1, n2, n3<br/>- El TRIGGER calcula def automáticamente"]

    A8 --> D3{"¿Actualización<br/>exitosa?"}
    D3 -->|SÍ| A9["Mostrar<br/>nota definitiva calculada"]
    D3 -->|NO| A10["Mostrar<br/>mensaje de error"]

    A9 --> D2
    A10 --> D2

    D2 -->|SÍ| A5
    D2 -->|NO| Fin([FIN DEL PROCESO])
```

---

## Notas

- **Rango de notas**: `n1`, `n2`, `n3` deben estar entre **0.0 y 5.0**.
- **Cálculo automático**: La nota definitiva se calcula mediante trigger en PostgreSQL.
- **Fórmula de cálculo**: `definitiva = (n1 × 0.35) + (n2 × 0.35) + (n3 × 0.30)`
- **Precisión**: Las notas se almacenan con 2 decimales (`NUMERIC(3,2)`).
- **Permisos**: Solo el profesor asignado puede modificar las notas de su grupo.

---

## Flujo del Trigger

```
TRIGGER: trg_calculo_notas
──────────────────────────

SE DISPARA: ANTES de INSERT o UPDATE de n1, n2, n3 en la tabla Inscribe

FUNCIÓN: fn_calcular_definitiva()

  NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30);
  RETURN NEW;

EJEMPLO:
  n1 = 4.0, n2 = 3.5, n3 = 4.2

  def = (4.0 * 0.35) + (3.5 * 0.35) + (4.2 * 0.30)
  def = 1.40 + 1.225 + 1.26
  def = 3.885
```

---

## Permisos por Rol

| Rol | Acción Permitida |
|-----|------------------|
| Administrador | Puede modificar todas las notas |
| Profesor | Solo puede modificar notas de sus grupos (tabla Imparte) |
| Estudiante | Solo puede ver sus propias notas |

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 10 de mayo de 2026
