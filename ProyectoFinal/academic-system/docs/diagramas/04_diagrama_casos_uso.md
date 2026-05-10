# Diagramas de Casos de Uso
## Sistema de Gestión Académica

---

## 1. Diagrama General de Casos de Uso

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        SISTEMA DE GESTIÓN ACADÉMICA                         │
│                                                                             │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                         ACTORES                                       │  │
│  │                                                                       │  │
│  │   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐              │  │
│  │   │  ADMINISTRADOR│    │  PROFESOR   │    │  ESTUDIANTE │              │  │
│  │   └─────────────┘    └─────────────┘    └─────────────┘              │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                    │                                         │
│                                    ▼                                         │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                      CASOS DE USO                                     │  │
│  │                                                                       │  │
│  │  ┌──────────────────┐ ┌──────────────────┐ ┌──────────────────┐      │  │
│  │  │  Gestionar      │ │  Gestionar      │ │  Gestionar      │      │  │
│  │  │  Carreras       │ │  Estudiantes    │ │  Asignaturas    │      │  │
│  │  │  (CU-01)        │ │  (CU-02)        │ │  (CU-03)        │      │  │
│  │  └──────────────────┘ └──────────────────┘ └──────────────────┘      │  │
│  │                                                                       │  │
│  │  ┌──────────────────┐ ┌──────────────────┐ ┌──────────────────┐      │  │
│  │  │  Gestionar      │ │  Asignar Carga  │ │  Gestionar      │      │  │
│  │  │  Profesores     │ │  Académica      │ │  Inscripciones  │      │  │
│  │  │  (CU-04)        │ │  (CU-05)        │ │  (CU-06)        │      │  │
│  │  └──────────────────┘ └──────────────────┘ └──────────────────┘      │  │
│  │                                                                       │  │
│  │  ┌──────────────────┐ ┌──────────────────┐ ┌──────────────────┐      │  │
│  │  │  Registrar      │ │  Gestionar      │ │  Consultar      │      │  │
│  │  │  Notas         │ │  Prerrequisitos │ │  Información    │      │  │
│  │  │  (CU-07)        │ │  (CU-08)        │ │  (CU-09)        │      │  │
│  │  └──────────────────┘ └──────────────────┘ └──────────────────┘      │  │
│  │                                                                       │  │
│  │  ┌──────────────────┐                                                 │  │
│  │  │  Gestionar      │                                                 │  │
│  │  │  Pensum         │                                                 │  │
│  │  │  (CU-10)        │                                                 │  │
│  │  └──────────────────┘                                                 │  │
│  │                                                                       │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Detalle de Casos de Uso por Actor

### 2.1 ADMINISTRADOR (Acceso Total)

```
┌─────────────────────────────────────┐
│         ADMINISTRADOR               │
└──────────────┬──────────────────────┘
               │
       ┌───────┴───────┐
       │               │
       ▼               ▼
┌─────────────┐   ┌─────────────┐
│ CU-01       │   │ CU-02       │
│ Gestionar   │   │ Gestionar   │
│ Carreras    │   │ Estudiantes │
│             │   │             │
│ • Crear     │   │ • Crear     │
│ • Consultar │   │ • Consultar │
│ • Modificar │   │ • Modificar │
│ • Eliminar  │   │ • Eliminar  │
└─────────────┘   └─────────────┘
       │
       ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐
│ CU-03       │   │ CU-04       │   │ CU-05       │
│ Gestionar   │   │ Gestionar   │   │ Asignar     │
│ Asignaturas │   │ Profesores   │   │ Carga       │
│             │   │             │   │ Académica   │
│ • Crear     │   │ • Crear     │   │             │
│ • Consultar │   │ • Consultar │   │ • Asignar   │
│ • Modificar │   │ • Modificar │   │ • Consultar │
│ • Eliminar  │   │ • Eliminar  │   │ • Modificar │
└─────────────┘   └─────────────┘   └─────────────┘
       │
       ▼
┌─────────────┐   ┌─────────────┐   ┌─────────────┐
│ CU-06       │   │ CU-08       │   │ CU-10       │
│ Gestionar   │   │ Gestionar   │   │ Gestionar   │
│ Inscripciones│  │ Prerrequis. │   │ Pensum      │
│             │   │             │   │             │
│ • Inscribir │   │ • Agregar   │   │ • Agregar   │
│ • Consultar │   │ • Consultar │   │ • Consultar │
│ • Cancelar  │   │ • Eliminar  │   │ • Eliminar  │
└─────────────┘   └─────────────┘   └─────────────┘
```

### 2.2 PROFESOR (Acceso Limitado)

```
┌─────────────────────────────────────┐
│           PROFESOR                  │
└──────────────┬──────────────────────┘
               │
       ┌───────┴───────┐
       │               │
       ▼               ▼
┌─────────────┐   ┌─────────────┐
│ CU-09       │   │ CU-07       │
│ Consultar   │   │ Registrar   │
│ Información │   │ Notas       │
│             │   │             │
│ • Ver       │   │ • Actualizar│
│   carreras  │   │   n1        │
│ • Ver       │   │ • Actualizar│
│   asignat.  │   │   n2        │
│ • Ver       │   │ • Actualizar│
│   estudiantes│  │   n3        │
└─────────────┘   └─────────────┘
```

### 2.3 ESTUDIANTE (Solo Lectura)

```
┌─────────────────────────────────────┐
│          ESTUDIANTE                 │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│             CU-09                   │
│       Consultar Información         │
│                                     │
│ • Ver carreras disponibles         │
│ • Ver pensum de mi carrera         │
│ • Ver mis asignaturas inscritas    │
│ • Ver mis notas (n1, n2, n3, def)   │
│ • Ver prerrequisitos de asignaturas │
└─────────────────────────────────────┘
```

---

## 3. Especificación de Casos de Uso

### CU-01: Gestionar Carreras

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-01 |
| **Nombre** | Gestionar Carreras |
| **Actor** | Administrador |
| **Descripción** | Permite crear, consultar, modificar y eliminar carreras |
| **Precondiciones** | Estar autenticado como administrador |
| **Postcondiciones** | La carrera queda registrada en la base de datos |

**Flujo Principal:**
1. El administrador selecciona la opción "Gestionar Carreras"
2. El sistema muestra la lista de carreras
3. El administrador puede: agregar, modificar, eliminar o buscar una carrera

---

### CU-02: Gestionar Estudiantes

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-02 |
| **Nombre** | Gestionar Estudiantes |
| **Actor** | Administrador |
| **Descripción** | Permite registrar y administrar estudiantes |
| **Precondiciones** | Estar autenticado como administrador |
| **Postcondiciones** | El estudiante queda registrado y asociado a una carrera |

---

### CU-03: Gestionar Asignaturas

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-03 |
| **Nombre** | Gestionar Asignaturas |
| **Actor** | Administrador |
| **Descripción** | Permite crear y administrar el catálogo de asignaturas |
| **Precondiciones** | Estar autenticado como administrador |
| **Postcondiciones** | La asignatura queda registrada en el catálogo |

---

### CU-04: Gestionar Profesores

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-04 |
| **Nombre** | Gestionar Profesores |
| **Actor** | Administrador |
| **Descripción** | Permite registrar y administrar profesores |
| **Precondiciones** | Estar autenticado como administrador |
| **Postcondiciones** | El profesor queda registrado en el sistema |

---

### CU-05: Asignar Carga Académica

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-05 |
| **Nombre** | Asignar Carga Académica |
| **Actor** | Administrador |
| **Descripción** | Asigna qué profesor imparte qué asignatura en qué grupo |
| **Precondiciones** | Tener profesores y asignaturas registradas |
| **Postcondiciones** | La asignación queda registrada en la tabla Imparte |

---

### CU-06: Gestionar Inscripciones

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-06 |
| **Nombre** | Gestionar Inscripciones |
| **Actor** | Administrador |
| **Descripción** | Inscribe estudiantes a asignaturas, validando prerrequisitos |
| **Precondiciones** | Estar autenticado; tener estudiantes, asignaturas y carga académica |
| **Postcondiciones** | La inscripción queda registrada; trigger calcula nota definitiva |

---

### CU-07: Registrar Notas

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-07 |
| **Nombre** | Registrar Notas |
| **Actor** | Administrador, Profesor |
| **Descripción** | Registra las notas n1, n2, n3 de los estudiantes |
| **Precondiciones** | Tener inscripciones activas |
| **Postcondiciones** | Las notas quedan registradas; trigger calcula la definitiva |

---

### CU-08: Gestionar Prerrequisitos

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-08 |
| **Nombre** | Gestionar Prerrequisitos |
| **Actor** | Administrador |
| **Descripción** | Define qué asignaturas son prerrequisito de otras |
| **Precondiciones** | Tener al menos dos asignaturas registradas |
| **Postcondiciones** | Los prerrequisitos quedan registrados en la tabla Requiere |

---

### CU-09: Consultar Información

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-09 |
| **Nombre** | Consultar Información |
| **Actor** | Administrador, Profesor, Estudiante |
| **Descripción** | Permite consultar información según el rol del usuario |
| **Precondiciones** | Estar autenticado en el sistema |
| **Postcondiciones** | La información se muestra en pantalla |

---

### CU-10: Gestionar Pensum

| Campo | Descripción |
|-------|-------------|
| **ID** | CU-10 |
| **Nombre** | Gestionar Pensum |
| **Actor** | Administrador |
| **Descripción** | Define qué asignaturas pertenecen a qué carrera |
| **Precondiciones** | Tener carreras y asignaturas registradas |
| **Postcondiciones** | Las asignaturas quedan asociadas a la carrera en Incluye |

---

## 4. Matriz de Trazabilidad

| Caso de Uso | Admin | Profesor | Estudiante |
|-------------|-------|----------|-------------|
| CU-01 Gestionar Carreras | ● | | |
| CU-02 Gestionar Estudiantes | ● | | |
| CU-03 Gestionar Asignaturas | ● | | |
| CU-04 Gestionar Profesores | ● | | |
| CU-05 Asignar Carga Académica | ● | | |
| CU-06 Gestionar Inscripciones | ● | | |
| CU-07 Registrar Notas | ● | ● | |
| CU-08 Gestionar Prerrequisitos | ● | | |
| CU-09 Consultar Información | ● | ● | ● |
| CU-10 Gestionar Pensum | ● | | |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026