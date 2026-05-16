# Diagrama de Secuencia - Gestionar Inscripciones (Mermaid)
## CU-06: Gestionar Inscripciones

---

## 1. Diagrama de Secuencia - Inscribir Estudiante (Con SP)

Este diagrama representa el flujo principal de inscripción, donde un stored procedure valida prerrequisitos antes de insertar el registro.

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as View/FXML
    participant C as Controlador
    participant D as InscripcionDAO
    participant DB as PostgreSQL

    U->>V: Seleccionar estudiante
    activate V
    V->>C: Buscar estudiante
    activate C
    C->>D: SELECT Estudiante
    activate D
    D->>DB: Ejecutar
    activate DB
    DB-->>D: Resultado
    deactivate DB
    D-->>C: Objeto Estudiante
    deactivate D
    C-->>V: Mostrar estudiante
    deactivate C
    V-->>U: Mostrar estudiante
    deactivate V

    U->>V: Seleccionar asignatura y grupo
    activate V
    V->>C: Obtener carga académica (grupos)
    activate C
    C->>D: SELECT Imparte
    activate D
    D->>DB: Ejecutar
    activate DB
    DB-->>D: Grupos disponibles
    deactivate DB
    D-->>C: Grupos disponibles
    deactivate D
    C-->>V: Mostrar grupos
    deactivate C
    V-->>U: Mostrar grupos
    deactivate V

    U->>V: Confirmar inscripción
    activate V
    V->>C: validarYinscribir()
    activate C
    C->>D: CALL sp_inscribir_estudiante()
    activate D
    D->>DB: Ejecutar SP
    activate DB
    Note right of DB: SP: sp_inscribir_estudiante<br/>Verificar prerrequisitos<br/>Validar cada prerrequisito<br/>Si todo OK: INSERT INTO Inscribe
    DB-->>D: Resultado: Éxito
    deactivate DB
    D-->>C: Resultado
    deactivate D
    C-->>V: Resultado
    deactivate C
    V-->>U: Mostrar mensaje de éxito
    deactivate V
```

---

## 2. Diagrama de Secuencia - Prerrequisito No Aprobado

Este diagrama ilustra el escenario de error cuando el stored procedure detecta que el estudiante no ha aprobado algún prerrequisito.

```mermaid
sequenceDiagram
    actor U as Usuario
    participant V as View/FXML
    participant C as Controlador
    participant D as InscripcionDAO

    V->>C: validarYinscribir()
    activate C
    C->>D: CALL sp_inscribir_estudiante()
    activate D
    D-->>C: RAISE EXCEPTION (prerrequisito no aprobado)
    deactivate D
    C-->>V: Capturar excepción
    deactivate C
    V-->>U: Mostrar mensaje de error
    activate V
    deactivate V
```

---

## 3. Descripción de Mensajes Clave

| # | Mensaje | Descripción |
|---|---------|-------------|
| 1-7 | Seleccionar estudiante | El usuario busca y selecciona un estudiante |
| 8-13 | Seleccionar asignatura/grupo | Se muestran los grupos disponibles con profesor asignado |
| 14-21 | Proceso de inscripción | Se invoca el Stored Procedure para validar prerrequisitos e insertar |

---

## 4. Flujo del Stored Procedure (Detalle)

```
SP: sp_inscribir_estudiante

PARÁMETROS DE ENTRADA:
- p_cod_e: VARCHAR  → Código del estudiante
- p_cod_a: VARCHAR  → Código de la asignatura
- p_id_p: VARCHAR   → ID del profesor
- p_grupo: INT      → Número de grupo

LÓGICA:

1. FOR v_prereq IN (SELECT cod_a_r FROM Requiere WHERE cod_a = p_cod_a)
   Por cada prerrequisito:
   
   2. SELECT EXISTS (
        SELECT 1 FROM Inscribe
        WHERE cod_e = p_cod_e
          AND cod_a = v_prereq
          AND def >= 3.0
      ) INTO v_aprobada;
   
   3. IF NOT v_aprobada THEN
        RAISE EXCEPTION 'El estudiante no ha aprobado el prerrequisito %', v_prereq;
      END IF;

4. Si todos los prerrequisitos están aprobados:
   INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo)
   VALUES (p_cod_e, p_cod_a, p_id_p, p_grupo);

5. RAISE NOTICE 'Inscripción realizada con éxito';
```

---

## 5. Casos de Prueba

| ID | Escenario | Entrada | Resultado Esperado |
|----|-----------|---------|-------------------|
| CP01 | Inscripción exitosa | Estudiante E100, Asignatura PROG2, Prerrequisito PROG1 aprobado | Inscripción creada |
| CP02 | Prerrequisito no aprobado | Estudiante E101, Asignatura PROG2, Prerrequisito PROG1 no aprobado | Error: "Fallo de inscripción" |
| CP03 | Grupo sin profesor | Asignatura sin carga académica | Error: "No hay grupo disponible" |
| CP04 | Inscripción duplicada | Estudiante ya inscrito en mismo grupo | Error: "Ya está inscrito" |

---

**Versión**: 1.0 (Mermaid)
**Fecha**: 9 de mayo de 2026
