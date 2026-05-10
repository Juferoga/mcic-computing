# Historia de Usuario - HU03

## Gestionar Asignaturas

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU03 |
| **Nombre** | Gestionar Asignaturas |
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
| **Quiero** | Administrar el catálogo de asignaturas o materias |
| **Para** | Mantener la información académica actualizada y permitir la configuración del pensum |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear modelo de datos Asignatura (Entity)
├── T-02: Crear clase AsignaturaDAO con métodos CRUD
├── T-03: Crear formulario de ingreso de asignatura
├── T-04: Validar código único y nombre obligatorio
├── T-05: Validar rango de intensidad horaria (0-20)
├── T-06: Validar rango de créditos (1-10)
├── T-07: Crear TableView con lista de asignaturas
├── T-08: Implementar búsqueda por código y nombre
├── T-09: Crear función de modificación
├── T-10: Implementar verificación de eliminación (prerrequisitos, pensum)
├── T-11: Probar integración con PostgreSQL
└── T-12: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Crear Asignatura

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | El sistema debe permitir crear una nueva asignatura | Formulario completo |
| CA-02 | El código debe ser único | Error si ya existe |
| CA-03 | El nombre es obligatorio | Validación requerida |
| CA-04 | Intensidad horaria entre 0 y 20 | Validación de rango |
| CA-05 | Créditos entre 1 y 10 | Validación de rango |

#### Criterio 2: Consultar Asignaturas

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Mostrar todas las asignaturas | Tabla con código, nombre, intensidad, créditos |
| CB-02 | Buscar por código | Búsqueda exacta |
| CB-03 | Buscar por nombre | Búsqueda parcial |

#### Criterio 3: Modificar Asignatura

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Seleccionar para editar | Datos cargan en formulario |
| CC-02 | Modificar todos los campos excepto código | Código inmutable |

#### Criterio 4: Eliminar Asignatura

| ID | Descripción | Validación |
|----|-------------|------------|
| CD-01 | Verificar si es prerrequisito de otra | Advertir relaciones |
| CD-02 | Verificar si está en pensum | Advertir si está asignada a carreras |
| CD-03 | Confirmar eliminación | Ventana de confirmación |

---

### Flujo de Prueba

```
ESCENARIO: Crear nueva asignatura

DADO que estoy en el panel de gestión de asignaturas
CUANDO hago clic en "Agregar Asignatura"
Y lleno los campos:
  - Código: "BD2"
  - Nombre: "Bases de Datos Avanzadas"
  - Intensidad horaria: 4
  - Créditos: 4
Y hago clic en "Guardar"
ENTONCES el sistema muestra "Asignatura creada exitosamente"
Y aparece en la lista de asignaturas
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | Código único en tabla Asignaturas | Integridad |
| VN-02 | Nombre obligatorio y no vacío | Obligatoriedad |
| VN-03 | Intensidad horaria: entero entre 0 y 20 | Rango válido |
| VN-04 | Créditos: entero entre 1 y 10 | Rango válido |
| VN-05 | No eliminar si es prerrequisito de otra | Integridad referencial |
| VN-06 | No eliminar si está en el pensum | Integridad referencial |

---

### Pantallas Relacionadas

- Panel de Gestión de Asignaturas (FXML)
- Formulario de Asignatura (Dialog)

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Asignaturas`
- Clave primaria: `cod_a VARCHAR(10)`
- Campos: `nom_a`, `int_h`, `creditos`

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo