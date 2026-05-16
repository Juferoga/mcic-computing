# Historia de Usuario - HU02

## Gestionar Estudiantes

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU02 |
| **Nombre** | Gestionar Estudiantes |
| **Módulo** | Administración |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |
| **Sprint** | 1 |
| **Dependencias** | HU01 (Gestionar Carreras) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Registrar y administrar la información de los estudiantes |
| **Para** | Mantener un control actualizado de la población estudiantil y su vinculación con las carreras |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Estudiante (Entity)
├── T-02: Crear clase EstudianteDAO con métodos CRUD
├── T-03: Crear formulario de ingreso de estudiante en JavaFX
├── T-04: Implementar dropdown de selección de carrera
├── T-05: Implementar selector de fecha de nacimiento
├── T-06: Crear TableView para mostrar lista de estudiantes
├── T-07: Implementar búsqueda por código y nombre
├── T-08: Crear función de modificación de estudiante
├── T-09: Implementar eliminación con verificación de inscripciones
├── T-10: Integrar validación de carrera existente (FK)
├── T-11: Probar integración con PostgreSQL
└── T-12: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Registrar Estudiante

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | El sistema debe permitir registrar un nuevo estudiante | Formulario con todos los campos |
| CA-02 | El código de estudiante debe ser único | Error si ya existe |
| CA-03 | El nombre es obligatorio | Validación de campo requerido |
| CA-04 | La fecha de nacimiento no puede ser futura | Validación de fecha |
| CA-05 | La carrera debe existir en el sistema | Dropdown con solo carreras válidas |
| CA-06 | Los campos dirección y teléfono son opcionales | Se puede guardar sin ellos |

#### Criterio 2: Consultar Estudiantes

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Mostrar todos los estudiantes en una tabla | TableView con columnas: código, nombre, carrera, teléfono |
| CB-02 | Buscar por código | Campo de búsqueda funcional |
| CB-03 | Buscar por nombre | Búsqueda parcial aceptada |
| CB-04 | Filtrar por carrera | ComboBox de filtro |

#### Criterio 3: Modificar Estudiante

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar estudiante para editar | Datos se cargan en formulario |
| CC-02 | Modificar datos excepto código | El código no se puede cambiar |
| CC-03 | Cambiar de carrera | Actualiza la FK correctamente |

#### Criterio 4: Eliminar Estudiante

| ID | Descripción | Validación |
|----|-------------|------------|
| CD-01 | Confirmar eliminación | Ventana de confirmación |
| CD-02 | Advertencia si tiene inscripciones | Mostrar cantidad de inscripciones |
| CD-03 | Eliminar en cascada | Elimina inscripciones relacionadas |

---

### Flujo de Prueba

```
ESCENARIO: Registrar nuevo estudiante

DADO que estoy en el panel de gestión de estudiantes
CUANDO hago clic en "Agregar Estudiante"
Y lleno los campos:
  - Código: "E200"
  - Nombre: "Maria García"
  - Dirección: "Calle 50 #30-20"
  - Teléfono: "3001234567"
  - Fecha de nacimiento: "15/03/2002"
  - Carrera: "Ingeniería de Sistemas"
Y hago clic en "Guardar"
ENTONCES el sistema muestra "Estudiante registrado exitosamente"
Y el estudiante aparece en la lista
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | Código único en tabla Estudiantes | Integridad |
| VN-02 | La carrera asociada debe existir | FK válida |
| VN-03 | Fecha de nacimiento no puede ser futura | Validación de fecha |
| VN-04 | El código no se puede modificar después de creado | Inmutabilidad |

---

### Pantallas Relacionadas

- Panel de Gestión de Estudiantes (FXML)
- Formulario de Estudiante (Dialog)
- Selector de Carrera (ComboBox)
- Selector de Fecha (DatePicker)

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Estudiantes`
- Clave primaria: `cod_e VARCHAR(15)`
- FK: `id_carr` → `Carreras(id_carr)`
- Relación: Un estudiante pertenece a una carrera (N:1)

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo