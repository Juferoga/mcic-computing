# Historia de Usuario - HU08

## Gestionar Prerrequisitos

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU08 |
| **Nombre** | Gestionar Prerrequisitos |
| **Módulo** | Administración Académica |
| **Prioridad** | Media |
| **Estimación** | 5 puntos |
| **Sprint** | 2 |
| **Dependencias** | HU03 (Gestionar Asignaturas) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Definir qué asignaturas son prerrequisito de otras |
| **Para** | Validar que los estudiantes cumplan los requisitos académicos antes de inscribirse a una materia avanzada |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Requiere (Entity)
├── T-02: Crear clase RequiereDAO con métodos CRUD
├── T-03: Crear formulario de gestión de prerrequisitos
├── T-04: Dropdown de asignatura (la que requiere)
├── T-05: Dropdown de prerrequisito (la requerida)
├── T-06: Validar que no sea el mismo curso
├── T-07: Validar que no exista duplicado
├── T-08: Crear lista de prerrequisitos por asignatura
├── T-09: Permitir eliminar prerrequisito
├── T-10: Prohibir eliminar asignatura que es prerrequisito
├── T-11: Probar integración con PostgreSQL
└── T-12: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Agregar Prerrequisito

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | Seleccionar asignatura que requiere | Dropdown de asignaturas |
| CA-02 | Seleccionar asignatura prerrequisito | Dropdown de asignaturas |
| CA-03 | Validar que no sea la misma asignatura | Error si son iguales |
| CA-04 | Validar que no exista duplicado | Error si ya está relacionado |
| CA-05 | Guardar en tabla Requiere | INSERT en BD |

#### Criterio 2: Consultar Prerrequisitos

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Ver prerrequisitos de una asignatura | Lista de "Requiere" |
| CB-02 | Ver en qué asignaturas es prerrequisito | Lista de "Es requerido por" |

#### Criterio 3: Eliminar Prerrequisito

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar prerrequisito a eliminar | De la lista |
| CC-02 | Confirmar eliminación | Ventana de confirmación |
| CC-03 | Eliminar de la tabla | DELETE en BD |

---

### Flujo de Prueba

```
ESCENARIO: Agregar prerrequisito

DADO que estoy en el panel de gestión de prerrequisitos
CUANDO selecciono:
  - Asignatura: "Programación Orientada a Objetos" (PROG2)
  - Prerrequisito: "Programación Básica" (PROG1)
Y hago clic en "Agregar"
ENTONCES el sistema guarda el registro en la tabla Requiere
Y muestra "Prerrequisito agregado exitosamente"
Y PROG2 aparece con PROG1 como prerrequisito

ESCENARIO: Prerrequisito duplicado

DADO que PROG2 ya tiene como prerrequisito PROG1
CUANDO intento agregar nuevamente el mismo prerrequisito
ENTONCES el sistema muestra error: "Este prerrequisito ya está registrado"

ESCENARIO: Same curso

DADO que selecciono "Programación Básica" como prerrequisito de sí misma
CUANDO hago clic en "Agregar"
ENTONCES el sistema muestra error: "Una asignatura no puede ser prerrequisito de sí misma"

ESCENARIO: Eliminar prerrequisito

DADO que PROG2 tiene prerrequisito PROG1
CUANDO selecciono eliminar ese prerrequisito
Y confirmo la eliminación
ENTONCES el sistema elimina el registro de Requiere
Y PROG2 ya no tiene prerrequisitos
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | Una asignatura no puede ser prerrequisito de sí misma | Regla de negocio |
| VN-02 | No permitir duplicados en la relación | Integridad |
| VN-03 | La FK debe existir | Asignatura válida |
| VN-04 | No eliminar si es prerrequisito de otra | Advertencia |

---

### Pantallas Relacionadas

- Panel de Prerrequisitos (FXML)
- Formulario de Agregar Prerrequisito (Dialog)
- Tabla de prerrequisitos por asignatura

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica Requiere`
- Clave primaria compuesta: `(cod_a, cod_a_r)`
- FK: `cod_a` → `Asignaturas(cod_a)`, `cod_a_r` → `Asignaturas(cod_a)`
- Relación recursiva: Una asignatura puede requerir varias y ser requerida por varias

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo