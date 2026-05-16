# Historia de Usuario - HU05

## Asignar Carga Académica (Imparte)

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU05 |
| **Nombre** | Asignar Carga Académica |
| **Módulo** | Administración |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |
| **Sprint** | 2 |
| **Dependencias** | HU03 (Gestionar Asignaturas), HU04 (Gestionar Profesores) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Asignar qué profesor imparte qué asignatura en qué grupo y horario |
| **Para** | Gestionar la carga académica de cada docente y permitir las inscripciones a grupos específicos |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Imparte (Entity)
├── T-02: Crear clase ImparteDAO con métodos CRUD
├── T-03: Crear formulario de asignación de carga
├── T-04: Crear dropdowns de profesor y asignatura
├── T-05: Implementar selector de grupo (número)
├── T-06: Implementar campo de horario
├── T-07: Validar组合 única (profesor, asignatura, grupo)
├── T-08: Crear TableView de carga académica
├── T-09: Filtrar por profesor o asignatura
├── T-10: Crear función de modificación
├── T-11: Implementar eliminación de carga
├── T-12: Probar integración con PostgreSQL
└── T-13: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Asignar Carga

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | Seleccionar profesor del dropdown | Solo profesores registrados |
| CA-02 | Seleccionar asignatura del dropdown | Solo asignaturas del catálogo |
| CA-03 | Ingresar número de grupo | Entero positivo |
| CA-04 | Ingresar horario | Texto libre (ej: "Lunes 8-10am") |
| CA-05 | La combinación (profesor, asignatura, grupo) debe ser única | Error si ya existe |

#### Criterio 2: Consultar Carga Académica

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Mostrar todas las asignaciones | Tabla con profesor, asignatura, grupo, horario |
| CB-02 | Filtrar por profesor | Selection de profesor muestra sus cargas |
| CB-03 | Filtrar por asignatura | Selection de asignatura muestra quién la dicta |

#### Criterio 3: Modificar Carga

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar para editar | Datos cargan en formulario |
| CC-02 | Cambiar profesor, asignatura, grupo o horario | Se actualiza en BD |

#### Criterio 4: Eliminar Carga

| ID | Descripción | Validación |
|----|-------------|------------|
| CD-01 | Confirmar eliminación | Ventana de confirmación |
| CD-02 | Advertir si hay inscripciones activas | Mostrar cantidad de estudiantes inscritos |
| CD-03 | Eliminación elimina inscripciones | Verificar antes de proceder |

---

### Flujo de Prueba

```
ESCENARIO: Asignar carga académica

DADO que estoy en el panel de gestión de carga académica
CUANDO hago clic en "Asignar Carga"
Y selecciono:
  - Profesor: "Alan Turing"
  - Asignatura: "Programación Básica"
  - Grupo: 2
  - Horario: "Martes 10-12m"
Y hago clic en "Guardar"
ENTONCES el sistema muestra "Carga académica asignada exitosamente"
Y la asignación aparece en la lista

ESCENARIO: Asignar grupo duplicado

DADO que el profesor Alan Turing ya imparte "Programación Básica" en grupo 1
CUANDO intento asignar el mismo profesor a la misma asignatura en grupo 1
ENTONCES el sistema muestra error: "Esta carga académica ya existe"
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 |组合 única (id_p, cod_a, grupo) | Integridad |
| VN-02 | Profesor debe existir | FK válida |
| VN-03 | Asignatura debe existir | FK válida |
| VN-04 | Grupo debe ser entero positivo | Validación de tipo |
| VN-05 | Advertencia antes de eliminar con inscripciones | Integridad |

---

### Pantallas Relacionadas

- Panel de Gestión de Carga Académica (FXML)
- Formulario de Asignación (Dialog)
- Dropdowns de Profesor y Asignatura

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Imparte`
- Clave primaria compuesta: `(id_p, cod_a, grupo)`
- FK: `id_p` → `Profesores(id_p)`, `cod_a` → `Asignaturas(cod_a)`

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo