# Historia de Usuario - HU06

## Gestionar Inscripciones

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU06 |
| **Nombre** | Gestionar Inscripciones |
| **Módulo** | Académico |
| **Prioridad** | Crítica |
| **Estimación** | 13 puntos |
| **Sprint** | 2 |
| **Dependencias** | HU02 (Estudiantes), HU05 (Imparte) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Inscribir a los estudiantes en las asignaturas de los grupos disponibles |
| **Para** | Gestionar la matrícula académica asegurando el cumplimiento de prerrequisitos |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Inscripcion (Entity)
├── T-02: Crear clase InscripcionDAO con métodos CRUD
├── T-03: Crear formulario de inscripción
├── T-04: Crear sistema de búsqueda de estudiante
├── T-05: Crear sistema de búsqueda de asignatura/grupo
├── T-06: Integrar llamada al Stored Procedure sp_inscribir_estudiante
├── T-07: Manejar excepción de prerrequisitos no aprobados
├── T-08: Inicializar notas en 0.00 al inscribir
├── T-09: Crear TableView de inscripciones
├── T-10: Filtrar por estudiante, asignatura o grupo
├── T-11: Crear función de cancelación de inscripción
├── T-12: Probar integración con PostgreSQL (SP + Trigger)
└── T-13: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Inscribir Estudiante

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | Buscar estudiante por código | Autocomplete o búsqueda |
| CA-02 | Mostrar grupos disponibles (con profesor asignado) | Solo grupos con carga académica |
| CA-03 | Validar prerrequisitos mediante SP | Llama a sp_inscribir_estudiante |
| CA-04 | Mostrar error si prerrequisito no aprobado | Indica cuál prerrequisito falta |
| CA-05 | Crear registro con n1=n2=n3=def=0.00 | Notas inicializadas |
| CA-06 | No permitir inscripción duplicada | Mismo estudiante, misma asignatura, mismo grupo |

#### Criterio 2: Consultar Inscripciones

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Ver todas las inscripciones | Tabla completa |
| CB-02 | Filtrar por estudiante | Muestra inscripciones de un estudiante |
| CB-03 | Filtrar por asignatura | Muestra estudiantes inscritos |
| CB-04 | Ver detalles de inscripción | Notas parciales y definitiva |

#### Criterio 3: Cancelar Inscripción

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar inscripción a cancelar | De la lista |
| CC-02 | Confirmar cancelación | Ventana de confirmación |
| CC-03 | Eliminar registro de Inscribe | DELETE en BD |

---

### Flujo de Prueba

```
ESCENARIO: Inscripción exitosa

DADO que el estudiante "Carlos Martínez" (E100) ha aprobado "Programación Básica"
Y existe un grupo de "Programación Orientada a Objetos" (PROG2) asignado al profesor "Grace Hopper"
CUANDO inscribo al estudiante E100 en PROG2, grupo 1
ENTONCES el sistema:
1. Llama al SP sp_inscribir_estudiante('E100', 'PROG2', 'P002', 1)
2. El SP verifica que PROG1 (prerrequisito) tiene def >= 3.0
3. El SP inserta el registro en Inscribe
4. El sistema muestra "Inscripción exitosa"
Y el estudiante aparece en la lista de PROG2 grupo 1

ESCENARIO: Prerrequisito no aprobado

DADO que el estudiante "Ana Rojas" (E101) NO ha aprobado "Programación Básica"
CUANDO intento inscribirla en "Programación Orientada a Objetos"
ENTONCES el SP lanza excepción: "Fallo de inscripción: El estudiante E101 no ha aprobado el prerrequisito PROG1"
Y el sistema muestra el mensaje de error
Y la inscripción no se realiza

ESCENARIO: Inscripción duplicada

DADO que el estudiante E100 ya está inscrito en PROG1 grupo 1
CUANDO intento inscribirlo nuevamente en el mismo grupo
ENTONCES el sistema muestra error: "El estudiante ya está inscrito en este grupo"
```

---

### Integración con Stored Procedure

```sql
-- El sistema llama este procedure desde Java:
CALL sp_inscribir_estudiante('E100', 'PROG2', 'P002', 1);

-- El SP valida prerrequisitos y luego inserta:
INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo)
VALUES ('E100', 'PROG2', 'P002', 1);
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | Prerrequisitos aprobados (def >= 3.0) | Regla de negocio |
| VN-02 | No duplicidad (cod_e, cod_a, id_p, grupo) | Integridad |
| VN-03 | Grupo debe tener profesor asignado | Existencia de Imparte |
| VN-04 | Estudiante debe existir | FK válida |

---

### Pantallas Relacionadas

- Panel de Inscripciones (FXML)
- Formulario de Inscripción (Dialog)
- Buscador de Estudiantes
- Selector de Grupo

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Inscribe`
- Clave primaria compuesta: `(cod_e, cod_a, id_p, grupo)`
- FK: `(id_p, cod_a, grupo)` → `Imparte(id_p, cod_a, grupo)`
- Trigger: `trg_calculo_notas` calcula `def` automáticamente

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo