# Historia de Usuario - HU09

## Gestionar Pensum

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU09 |
| **Nombre** | Gestionar Pensum |
| **Módulo** | Administración Académica |
| **Prioridad** | Media |
| **Estimación** | 5 puntos |
| **Sprint** | 2 |
| **Dependencias** | HU01 (Carreras), HU03 (Asignaturas) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Definir qué asignaturas pertenecen a cada carrera |
| **Para** | Establecer el plan de estudios de cada programa académico y permitir que los estudiantes consulten su pensum |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Incluye (Entity)
├── T-02: Crear clase IncluyeDAO con métodos CRUD
├── T-03: Crear formulario de gestión de pensum
├── T-04: Dropdown de carrera
├── T-05: Dropdown de asignatura
├── T-06: Validar que no exista duplicado
├── T-07: Crear lista de asignaturas por carrera
├── T-08: Mostrar carreras en el pensum
├── T-09: Permitir eliminar asignatura del pensum
├── T-10: Prohibir eliminar carrera con pensum
├── T-11: Probar integración con PostgreSQL
└── T-12: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Agregar Asignatura al Pensum

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | Seleccionar carrera | Dropdown de carreras |
| CA-02 | Seleccionar asignatura | Dropdown de asignaturas |
| CA-03 | Validar que no exista duplicado | Error si ya está en el pensum |
| CA-04 | Guardar en tabla Incluye | INSERT en BD |

#### Criterio 2: Consultar Pensum

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Ver todas las asignaturas de una carrera | Tabla con código, nombre, créditos |
| CB-02 | Ver en qué carreras está una asignatura | Lista de carreras |

#### Criterio 3: Eliminar del Pensum

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar asignatura a eliminar | De la lista |
| CC-02 | Confirmar eliminación | Ventana de confirmación |
| CC-03 | Eliminar de la tabla | DELETE en BD |

---

### Flujo de Prueba

```
ESCENARIO: Agregar asignatura al pensum

DADO que estoy en el panel de gestión de pensum
CUANDO selecciono:
  - Carrera: "Ingeniería de Sistemas" (SIS)
  - Asignatura: "Bases de Datos" (BD1)
Y hago clic en "Agregar al Pensum"
ENTONCES el sistema guarda el registro en la tabla Incluye
Y muestra "Asignatura agregada al pensum"
Y BD1 aparece en la lista de asignaturas de SIS

ESCENARIO: Asignatura ya está en el pensum

DADO que "Programación Básica" ya está en el pensum de SIS
CUANDO intento agregarla nuevamente
ENTONCES el sistema muestra error: "Esta asignatura ya está en el pensum de esta carrera"

ESCENARIO: Eliminar del pensum

DADO que "Programación Básica" está en el pensum de SIS
CUANDO selecciono eliminarla del pensum
Y confirmo la eliminación
ENTONCES el sistema elimina el registro de Incluye
Y "Programación Básica" ya no aparece en el pensum de SIS
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | No permitir duplicados (misma carrera y asignatura) | Integridad |
| VN-02 | La carrera debe existir | FK válida |
| VN-03 | La asignatura debe existir | FK válida |
| VN-04 | Eliminar carrera elimina su pensum | Cascada |

---

### Pantallas Relacionadas

- Panel de Gestión de Pensum (FXML)
- Formulario de Agregar al Pensum (Dialog)
- Tabla de asignaturas por carrera

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Incluye`
- Clave primaria compuesta: `(id_carr, cod_a)`
- FK: `id_carr` → `Carreras(id_carr)`, `cod_a` → `Asignaturas(cod_a)`
- Relación: Una carrera incluye muchas asignaturas, una asignatura puede estar en varias carreras

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo