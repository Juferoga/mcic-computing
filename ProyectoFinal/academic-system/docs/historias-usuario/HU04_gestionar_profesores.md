# Historia de Usuario - HU04

## Gestionar Profesores

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU04 |
| **Nombre** | Gestionar Profesores |
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
| **Quiero** | Registrar y administrar la información de los profesores |
| **Para** | Mantener actualizada la base de datos docente y permitir la asignación de carga académica |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Profesor (Entity)
├── T-02: Crear clase ProfesorDAO con métodos CRUD
├── T-03: Crear formulario de registro de profesor
├── T-04: Validar ID único y nombre obligatorio
├── T-05: Crear TableView con lista de profesores
├── T-06: Implementar búsqueda por ID y nombre
├── T-07: Crear función de modificación
├── T-08: Implementar eliminación con verificación de carga
├── T-09: Probar integración con PostgreSQL
└── T-10: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Registrar Profesor

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | El sistema debe permitir registrar un nuevo profesor | Formulario completo |
| CA-02 | El ID de profesor debe ser único | Error si ya existe |
| CA-03 | El nombre es obligatorio | Validación requerida |
| CA-04 | Dirección, teléfono y profesión son opcionales | Se puede guardar sin ellos |

#### Criterio 2: Consultar Profesores

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Mostrar todos los profesores | Tabla con ID, nombre, teléfono, profesión |
| CB-02 | Buscar por ID | Búsqueda exacta |
| CB-03 | Buscar por nombre | Búsqueda parcial |

#### Criterio 3: Modificar Profesor

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar para editar | Datos cargan en formulario |
| CC-02 | Modificar todos los campos excepto ID | ID inmutable |

#### Criterio 4: Eliminar Profesor

| ID | Descripción | Validación |
|----|-------------|------------|
| CD-01 | Verificar si tiene carga académica asignada | Advertir si tiene asignaturas |
| CD-02 | Confirmar eliminación | Ventana de confirmación |
| CD-03 | Eliminación en cascada de Imparte | Se eliminan las asignaciones |

---

### Flujo de Prueba

```
ESCENARIO: Registrar nuevo profesor

DADO que estoy en el panel de gestión de profesores
CUANDO hago clic en "Agregar Profesor"
Y lleno los campos:
  - ID: "P003"
  - Nombre: "Luis Fernando Gómez"
  - Dirección: "Carrera 25 #40-15"
  - Teléfono: "3109876543"
  - Profesión: "Ingeniero de Sistemas"
Y hago clic en "Guardar"
ENTONCES el sistema muestra "Profesor registrado exitosamente"
Y el profesor aparece en la lista
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | ID único en tabla Profesores | Integridad |
| VN-02 | Nombre obligatorio | Obligatoriedad |
| VN-03 | Eliminación elimina carga académica | Cascada en Imparte |

---

### Pantallas Relacionadas

- Panel de Gestión de Profesores (FXML)
- Formulario de Profesor (Dialog)

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Profesores`
- Clave primaria: `id_p VARCHAR(15)`
- Campos: `nom_p`, `dir_p`, `tel_p`, `profesion`

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo