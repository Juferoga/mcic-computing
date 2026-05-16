# Diagrama de Secuencia - Registrar Notas (Mermaid)
## CU-07: Registrar Notas

---

## 1. Diagrama de Secuencia - Actualizar Notas

Este diagrama describe el flujo de actualización de notas para un grupo determinado, destacando la ejecución del trigger que calcula automáticamente la nota definitiva.

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as View/FXML
    participant C as Controlador
    participant D as InscripcionDAO
    participant DB as PostgreSQL

    U->>V: Seleccionar asignatura y grupo
    activate V
    V->>C: obtenerInscripciones()
    activate C
    C->>D: SELECT Inscribe (WHERE cod_a, grupo)
    activate D
    D->>DB: Ejecutar
    activate DB
    DB-->>D: Lista estudiantes inscritos
    deactivate DB
    D-->>C: Lista de inscripciones
    deactivate D
    C-->>V: Mostrar tabla con estudiantes y notas
    deactivate C
    V-->>U: Mostrar tabla
    deactivate V

    U->>V: Seleccionar estudiante y editar notas
    activate V
    V-->>U: Mostrar campos de edición
    deactivate V

    U->>V: Ingresar notas: n1=4.0, n2=3.5, n3=4.2
    activate V
    V->>C: Validar rango (0-5)
    activate C
    C->>D: actualizarNotas()
    activate D
    D->>DB: UPDATE Inscribe SET n1=?, n2=?, n3=?
    activate DB
    Note right of DB: TRIGGER: trg_calculo_notas<br/>NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30)
    DB-->>D: Resultado (con def calculada)
    deactivate DB
    D-->>C: Resultado
    deactivate D
    C-->>V: Resultado
    deactivate C
    V-->>U: Mostrar resultado con nota def=3.885
    deactivate V
```

---

## 2. Diagrama de Secuencia - Validación de Rango

Este diagrama muestra el flujo alternativo cuando el usuario intenta ingresar una nota fuera del rango permitido (0-5).

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as View/FXML
    participant C as Controlador

    U->>V: Ingresa n1=6.0 (inválido)
    activate V
    V->>C: Validar rango
    activate C
    C-->>V: Error: "Nota fuera de rango (0-5)"
    deactivate C
    V-->>U: Mostrar error
    deactivate V
```

---

## 3. Descripción de Mensajes Clave

| # | Mensaje | Descripción |
|---|---------|-------------|
| 1-2 | Seleccionar asignatura/grupo | El usuario elige qué materia y grupo va a calificar |
| 3-6 | Cargar inscripciones | Se consultan los estudiantes inscritos en ese grupo |
| 7 | Mostrar tabla | La vista presenta la lista con notas actuales |
| 8-9 | Seleccionar estudiante | El usuario hace clic en un estudiante para editar |
| 10-11 | Ingresar notas | El usuario ingresa n1, n2, n3 |
| 12-14 | Actualizar en BD | Se ejecuta UPDATE; el trigger calcula automáticamente la definitiva |

---

## 4. Flujo del Trigger (Detalle)

```
TRIGGER: trg_calculo_notas

SE DISPARA: ANTES de INSERT o UPDATE en la tabla Inscribe

EVENTO: UPDATE OF n1, n2, n3 ON Inscribe

FUNCIÓN: fn_calcular_definitiva()

BEGIN
    -- Cálculo de nota definitiva
    NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30);

    -- Retornar el nuevo registro con la definitiva calculada
    RETURN NEW;
END;

EJEMPLO DE EJECUCIÓN:
  Entrada:  n1 = 4.0, n2 = 3.5, n3 = 4.2

  Cálculo:
    def = (4.0 * 0.35) + (3.5 * 0.35) + (4.2 * 0.30)
    def = 1.40 + 1.225 + 1.26
    def = 3.885

  Resultado almacenado: def = 3.89 (redondeado a 2 decimales)
```

---

## 5. Escenarios de Uso

### 5.1 Actualización Exitosa

| Paso | Descripción |
|------|-------------|
| 1 | El profesor selecciona "Programación I - Grupo 1" |
| 2 | El sistema muestra 15 estudiantes con notas actuales |
| 3 | El profesor hace clic en "Carlos Martínez" |
| 4 | El sistema muestra campos de edición: n1, n2, n3 |
| 5 | El profesor ingresa: n1=4.0, n2=3.5, n3=4.2 |
| 6 | El sistema calcula def=3.885 y lo muestra |
| 7 | El sistema guarda en PostgreSQL |

---

## 6. Permisos por Rol

| Rol | Tabla Inscribe | Acción |
|-----|----------------|--------|
| Administrador | ALL | Puede modificar cualquier nota |
| Profesor | UPDATE (n1, n2, n3) | Solo notas de sus grupos |
| Estudiante | SELECT | Solo puede ver sus propias notas |

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 9 de mayo de 2026
