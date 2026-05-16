# Historia de Usuario - HU01

## Gestionar Carreras

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU01 |
| **Nombre** | Gestionar Carreras |
| **Módulo** | Administración |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos |
| **Sprint** | 1 |
| **Dependencias** | Ninguna |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador del sistema |
| **Quiero** | Gestionar el catálogo de carreras académicas |
| **Para** | Mantener la información actualizada de las carreras que ofrece la institución |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Carrera (Entity)
├── T-02: Crear clase CarreraDAO con métodos CRUD
├── T-03: Crear formulario de ingreso de carrera en JavaFX
├── T-04: Implementar validación de datos (ID único, nombre obligatorio)
├── T-05: Crear TableView para mostrar lista de carreras
├── T-06: Implementar función de búsqueda por ID
├── T-07: Crear función de modificación de carrera
├── T-08: Implementar confirmación de eliminación
├── T-09: Probar integración con PostgreSQL
└── T-10: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Crear Carrera

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | El sistema debe permitir crear una nueva carrera | El formulario muestra todos los campos requeridos |
| CA-02 | El campo ID debe ser único | El sistema muestra error si ya existe |
| CA-03 | El campo nombre es obligatorio | El sistema muestra error si está vacío |
| CA-04 | El campo registro de calificación es opcional | Se puede guardar sin este campo |
| CA-05 | Los datos se persisten en PostgreSQL | Verificar en la base de datos |

#### Criterio 2: Consultar Carrera

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | El sistema debe mostrar todas las carreras | TableView con lista completa |
| CB-02 | Se puede buscar por ID | Campo de búsqueda funcional |
| CB-03 | Se puede ordenar por nombre | Click en encabezado de columna |

#### Criterio 3: Modificar Carrera

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar carrera de la lista | Botón editar activa el formulario |
| CC-02 | Los datos se actualizan en PostgreSQL | Verificar UPDATE en BD |

#### Criterio 4: Eliminar Carrera

| ID | Descripción | Validación |
|----|-------------|------------|
| CD-01 | Confirmar antes de eliminar | Ventana de confirmación |
| CD-02 | No se puede eliminar si tiene estudiantes | Mensaje de error apropiado |
| CD-03 | Eliminación exitosa | Registro eliminado de la BD |

---

### Flujo de Prueba

```
ESCENARIO: Crear nueva carrera

DADO que estoy en el panel de gestión de carreras
CUANDO hago clic en el botón "Agregar Carrera"
Y lleno los campos:
  - ID: "ARQ"
  - Nombre: "Arquitectura"
  - Registro: "RES-2026-005"
Y hago clic en "Guardar"
ENTONCES el sistema muestra el mensaje "Carrera creada exitosamente"
Y la nueva carrera aparece en la lista
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | El ID de carrera debe ser único | Integridad |
| VN-02 | El nombre no puede estar vacío | Obligatoriedad |
| VN-03 | No se puede eliminar carrera con estudiantes asociados | Integridad referencial |

---

### Pantallas Relacionadas

- Panel de Gestión de Carreras (FXML)
- Formulario de Carrera (Dialog)
- Mensajes de validación (Alert)

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Carreras`
- Clave primaria: `id_carr VARCHAR(10)`
- El ID se maneja como String para permitir códigos alfanuméricos

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo