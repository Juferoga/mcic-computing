# Sistema de Gestión Académica

## Descripción del Proyecto

Sistema de gestión académica desarrollado en Java con conexión a PostgreSQL. Permite la administración de carreras, estudiantes, asignaturas, profesores, inscripciones y calificaciones.

## Requisitos del Sistema

- **Java**: JDK 17 o superior
- **PostgreSQL**: 15 o superior (contenedorizado via Docker)
- **Maven**: 3.8 o superior
- **Docker y Docker Compose**

## Estructura del Proyecto

```
academic-system/
├── app/                      # Código fuente Java
│   └── (estructura Maven)
├── db_academico/             # Configuración de base de datos
│   ├── docker-compose.yml    # Contenedor PostgreSQL
│   └── init-scripts/         # Scripts de inicialización SQL
├── docs/                     # Documentación del proyecto
└── README.md                 # Este archivo
```

## Arquitectura

El proyecto implementa los siguientes patrones de diseño:

- **MVC (Model-View-Controller)**: Separación de responsabilidades en la aplicación
- **DAO (Data Access Object)**: Abstracción del acceso a datos
- **Singleton**: Gestión de conexiones a la base de datos

### Tecnología Backend

- **Lenguaje**: Java 17
- **Framework**: JavaFX para interfaz gráfica
- **Gestor de dependencias**: Maven
- **Driver**: PostgreSQL JDBC 42.7.3

### Tecnología Base de Datos

- **Motor**: PostgreSQL 15 (Alpine)
- **Contenedor**: Docker Compose
- **Esquema**: `gestion_academica`

## Esquema de Base de Datos

### Tablas Principales

| Tabla | Descripción |
|-------|-------------|
| `Carreras` | Catálogo de carreras académicas |
| `Estudiantes` | Registro de estudiantes |
| `Asignaturas` | Catálogo de asignaturas/materias |
| `Profesores` | Registro de profesores |
| `Imparte` | Asignación de asignaturas a profesores por grupo |
| `Inscribe` | Inscripciones con calificaciones (n1, n2, n3) |
| `Incluye` | Relación carrera-asignatura (pensum) |
| `Requiere` | Prerrequisitos entre asignaturas |

### Procedimientos Almacenados y Triggers

- **Trigger `trg_calculo_notas`**: Calcula automáticamente la nota definitiva con la fórmula:
  ```
  Definitiva = (n1 × 0.35) + (n2 × 0.35) + (n3 × 0.30)
  ```

- **Procedimiento `sp_inscribir_estudiante`**: Valida prerrequisitos antes de realizar una inscripción

## Seguridad (RBAC)

El sistema implementa Control de Acceso Basado en Roles a nivel de PostgreSQL:

| Rol | Privilegios |
|-----|-------------|
| `rol_admin_academico` | Control total sobre todas las tablas |
| `rol_profesor` | Consultas generales, actualización de notas |
| `rol_estudiante` | Solo lectura de información básica |

### Usuarios de Aplicación

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| `app_admin` | `Admin_Secret2026*` | Administrador |
| `app_profesor` | `Prof_Secret2026*` | Profesor |
| `app_estudiante` | `Est_Secret2026*` | Estudiante |

## Configuración e Instalación

### 1. Levantar la Base de Datos

```bash
cd db_academico
docker-compose up -d
```

La base de datos estará disponible en:
- **Host**: `localhost`
- **Puerto**: `5432`
- **Base de datos**: `db_sistema_academico`
- **Usuario**: `app_admin`

### 2. Compilar el Proyecto Java

```bash
cd app
mvn clean package
```

### 3. Ejecutar la Aplicación

```bash
mvn javafx:run
```

## Datos de Prueba (Mock Data)

El script de inicialización incluye datos precargados para demostración:

### Carreras
- `SIS` - Ingeniería de Sistemas
- `IND` - Ingeniería Industrial

### Asignaturas
- `PROG1` - Programación Básica (3 créditos)
- `PROG2` - Programación Orientada a Objetos (3 créditos)
- `BD1` - Bases de Datos (3 créditos)

### Profesores
- `P001` - Alan Turing
- `P002` - Grace Hopper

### Estudiantes
- `E100` - Carlos Martínez
- `E101` - Ana Rojas

## Funcionalidades

- ✅ Gestión de carreras
- ✅ Gestión de estudiantes
- ✅ Gestión de asignaturas
- ✅ Gestión de profesores
- ✅ Asignación de carga académica (Imparte)
- ✅ Inscripción de estudiantes a asignaturas
- ✅ Registro y cálculo automático de calificaciones
- ✅ Validación de prerrequisitos
- ✅ Control de acceso por roles

## Entrega

- **Fecha límite**: 29 de mayo de 2026, 18:00 horas
- **Formato**: Código fuente + Informe con diagramas UML

## Documentación Requerida

El informe debe incluir:
- Diagrama de Casos de Uso
- Diagrama de Clases
- Diagrama de Interacción (Secuencia)
- Manual de usuario
- Código fuente documentado

## Licencia

Este proyecto es de uso académico.