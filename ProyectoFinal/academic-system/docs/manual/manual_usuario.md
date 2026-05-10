# Manual de Usuario
## Sistema de Gestión Académica

---

## Tabla de Contenidos

1. [Introducción](#1-introducción)
2. [Requisitos del Sistema](#2-requisitos-del-sistema)
3. [ Instalación y Configuración](#3-instalación-y-configuración)
4. [Inicio de Sesión](#4-inicio-de-sesión)
5. [Guía de Uso por Módulo](#5-guía-de-uso-por-módulo)
6. [Glosario de Términos](#6-glosario-de-términos)

---

## 1. Introducción

### 1.1 Propósito del Sistema

El **Sistema de Gestión Académica** es una aplicación diseñada para automatizar los procesos administrativos relacionados con la gestión de carreras, estudiantes, asignaturas, profesores, inscripciones y calificaciones en una institución educativa.

### 1.2 Funcionalidades Principales

- ✅ Gestión de carreras académicas
- ✅ Registro y administración de estudiantes
- ✅ Catálogo de asignaturas
- ✅ Administración de profesores
- ✅ Asignación de carga académica (profesor-asignatura-grupo)
- ✅ Inscripción de estudiantes con validación de prerrequisitos
- ✅ Registro de calificaciones con cálculo automático de nota definitiva
- ✅ Gestión del pensum por carrera

### 1.3 Roles de Usuario

| Rol | Descripción | Permisos |
|-----|-------------|----------|
| **Administrador** | Gestiona toda la información del sistema | Acceso completo a todas las funciones |
| **Profesor** | Dicta clases y registra notas | Puede consultar y modificar notas de sus grupos |
| **Estudiante** | Consulta su información académica | Solo puede ver sus datos y notas |

---

## 2. Requisitos del Sistema

### 2.1 Requisitos de Hardware

| Componente | Requisito Mínimo | Recomendado |
|------------|------------------|-------------|
| Procesador | Intel Core i3 o equivalente | Intel Core i5 o superior |
| Memoria RAM | 4 GB | 8 GB |
| Espacio en disco | 500 MB | 1 GB |
| Resolución de pantalla | 1024x768 | 1366x768 o superior |

### 2.2 Requisitos de Software

| Software | Versión Requerida |
|----------|-------------------|
| Sistema Operativo | Windows 10 / macOS / Linux |
| Java | JDK 17 o superior |
| PostgreSQL | 15 o superior |
| Docker | 20.10 o superior (para base de datos contenida) |

---

## 3. Instalación y Configuración

### 3.1 Pasos de Instalación

#### Paso 1: Levantar la Base de Datos

```bash
cd db_academico
docker-compose up -d
```

#### Paso 2: Compilar el Proyecto

```bash
cd app
mvn clean package
```

#### Paso 3: Ejecutar la Aplicación

```bash
mvn javafx:run
```

### 3.2 Credenciales de Acceso

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| `app_admin` | `Admin_Secret2026*` | Administrador |
| `app_profesor` | `Prof_Secret2026*` | Profesor |
| `app_estudiante` | `Est_Secret2026*` | Estudiante |

---

## 4. Inicio de Sesión

### 4.1 Pantalla de Login

Al ejecutar la aplicación, se mostrará la pantalla de inicio de sesión:

```
┌─────────────────────────────────────────┐
│         SISTEMA DE GESTIÓN ACADÉMICA     │
├─────────────────────────────────────────┤
│                                         │
│  Usuario: [_______________]              │
│                                         │
│  Contraseña: [_______________]          │
│                                         │
│         [ INICIAR SESIÓN ]              │
│                                         │
└─────────────────────────────────────────┘
```

### 4.2 Seleccionar Perfil

Después de iniciar sesión, el sistema mostrará el menú principal según el rol:

- **Administrador**: Acceso a todos los menús
- **Profesor**: Menú de registro de notas y consulta
- **Estudiante**: Solo consulta de información personal

---

## 5. Guía de Uso por Módulo

### 5.1 Módulo de Carreras

#### Acceder al módulo

```
Menú Principal → Administración → Carreras
```

#### Funcionalidades disponibles

| Función | Descripción |
|---------|-------------|
| **Agregar** | Crear una nueva carrera |
| **Consultar** | Ver todas las carreras o buscar por ID |
| **Modificar** | Editar información de una carrera |
| **Eliminar** | Eliminar una carrera (solo si no tiene estudiantes) |

#### Agregar una nueva carrera

1. Haga clic en el botón **"Agregar"**
2. Complete el formulario:
   - **ID**: Código único de la carrera (ej: "SIS")
   - **Nombre**: Nombre completo (ej: "Ingeniería de Sistemas")
   - **Registro de Calificación**: Número de resolución (opcional)
3. Haga clic en **"Guardar"**

#### Eliminar una carrera

> **⚠️ Restricción**: No se puede eliminar una carrera que tenga estudiantes asociados.

---

### 5.2 Módulo de Estudiantes

#### Acceder al módulo

```
Menú Principal → Administración → Estudiantes
```

#### Funcionalidades disponibles

| Función | Descripción |
|---------|-------------|
| **Agregar** | Registrar un nuevo estudiante |
| **Consultar** | Ver lista o buscar por código/nombre |
| **Modificar** | Editar información del estudiante |
| **Eliminar** | Eliminar estudiante y sus inscripciones |

#### Agregar un nuevo estudiante

1. Haga clic en el botón **"Agregar"**
2. Complete el formulario:
   - **Código**: Identificador único (ej: "E100")
   - **Nombre**: Nombre completo del estudiante
   - **Dirección**: Dirección de residencia
   - **Teléfono**: Número de contacto
   - **Fecha de Nacimiento**: Seleccione en el calendario
   - **Carrera**: Seleccione de la lista desplegable
3. Haga clic en **"Guardar"**

---

### 5.3 Módulo de Asignaturas

#### Acceder al módulo

```
Menú Principal → Administración → Asignaturas
```

#### Funcionalidades disponibles

| Función | Descripción |
|---------|-------------|
| **Agregar** | Crear una nueva asignatura |
| **Consultar** | Ver todas las asignaturas |
| **Modificar** | Editar información |
| **Eliminar** | Eliminar (solo si no es prerrequisito ni está en pensum) |

#### Agregar una nueva asignatura

1. Haga clic en el botón **"Agregar"**
2. Complete el formulario:
   - **Código**: Identificador único (ej: "PROG1")
   - **Nombre**: Nombre de la materia
   - **Intensidad Horaria**: Horas semanales (0-20)
   - **Créditos**: Número de créditos (1-10)
3. Haga clic en **"Guardar"**

---

### 5.4 Módulo de Profesores

#### Acceder al módulo

```
Menú Principal → Administración → Profesores
```

#### Funcionalidades disponibles

| Función | Descripción |
|---------|-------------|
| **Agregar** | Registrar un nuevo profesor |
| **Consultar** | Ver lista de profesores |
| **Modificar** | Editar información |
| **Eliminar** | Eliminar (elimina su carga académica) |

---

### 5.5 Módulo de Carga Académica (Imparte)

#### Acceder al módulo

```
Menú Principal → Administración → Carga Académica
```

#### Propósito

Asignar qué profesor imparte qué asignatura, en qué grupo y horario.

#### Asignar carga académica

1. Haga clic en el botón **"Asignar Carga"**
2. Complete el formulario:
   - **Profesor**: Seleccione de la lista
   - **Asignatura**: Seleccione de la lista
   - **Grupo**: Número de grupo (ej: 1, 2, 3)
   - **Horario**: Horario de la clase (ej: "Lunes 8-10am")
3. Haga clic en **"Guardar"**

> **⚠️ Importante**: Solo los grupos con carga académica asignada estarán disponibles para inscripción de estudiantes.

---

### 5.6 Módulo de Inscripciones

#### Acceder al módulo

```
Menú Principal → Académico → Inscripciones
```

#### Inscribir a un estudiante

1. Busque al estudiante por su código
2. Seleccione la asignatura y grupo disponibles
3. El sistema **validará automáticamente los prerrequisitos**
4. Si cumple los requisitos, proceda con la inscripción

#### Validación de prerrequisitos

```
Ejemplo: Para inscribirse en "Programación Orientada a Objetos" (PROG2),
el estudiante debe haber aprobado "Programación Básica" (PROG1)
con una nota definitiva mayor o igual a 3.0
```

#### Error por prerrequisito no aprobado

Si el estudiante no ha aprobado el prerrequisito, el sistema mostrará:

```
❌ Error: "Fallo de inscripción: El estudiante E101 no ha aprobado
el prerrequisito PROG1"
```

---

### 5.7 Módulo de Registro de Notas

#### Acceder al módulo

```
Menú Principal → Académico → Notas
```

#### Registrar calificaciones

1. Seleccione la **asignatura** y el **grupo**
2. El sistema mostrará la lista de estudiantes inscritos
3. Para cada estudiante, ingrese:
   - **n1**: Nota del primer corte (35%)
   - **n2**: Nota del segundo corte (35%)
   - **n3**: Nota del tercer corte (30%)
4. Haga clic en **"Guardar"**

#### Cálculo automático de la nota definitiva

El sistema calcula automáticamente la nota definitiva mediante la fórmula:

```
definitiva = (n1 × 0.35) + (n2 × 0.35) + (n3 × 0.30)
```

**Ejemplo:**

| Nota | Valor | Porcentaje | Contribución |
|------|-------|------------|---------------|
| n1 | 4.0 | 35% | 1.40 |
| n2 | 3.5 | 35% | 1.225 |
| n3 | 4.2 | 30% | 1.26 |
| **Definitiva** | | | **3.885** |

---

### 5.8 Módulo de Prerrequisitos

#### Acceder al módulo

```
Menú Principal → Administración → Prerrequisitos
```

#### Agregar prerrequisito

1. Seleccione la **asignatura** que requiere el prerrequisito
2. Seleccione la **asignatura prerrequisito**
3. Haga clic en **"Agregar"**

> **⚠️ Restricción**: Una asignatura no puede ser prerrequisito de sí misma.

---

### 5.9 Módulo de Pensum

#### Acceder al módulo

```
Menú Principal → Administración → Pensum
```

#### Agregar asignatura al pensum

1. Seleccione la **carrera**
2. Seleccione la **asignatura**
3. Haga clic en **"Agregar al Pensum"**

---

## 6. Glosario de Términos

| Término | Definición |
|---------|-------------|
| **Carrera** | Programa académico que ofrece la institución (ej: Ingeniería de Sistemas) |
| **Asignatura** | Materia o curso que se dicta dentro de una carrera |
| **Prerrequisito** | Asignatura que debe aprobarse antes de cursar otra |
| **Pensum** | Plan de estudios que define las asignaturas de una carrera |
| **Carga Académica** | Asignación de un profesor a una asignatura en un grupo específico |
| **Inscripción** | Registro de un estudiante en una asignatura/grupo específico |
| **Nota Definitiva** | Promedio ponderado de las tres notas parciales |
| **Grupo** | Sección de una asignatura que dicta un profesor en un horario específico |
| **Créditos** | Valor académico de una asignatura |
| **Intensidad Horaria** | Número de horas semanales de una asignatura |

---

## 7. Solución de Problemas

### 7.1 Problemas Comunes

| Problema | Posible Solución |
|----------|------------------|
| No se conecta a la base de datos | Verificar que el contenedor Docker esté corriendo |
| Error al guardar "código duplicado" | El código ya existe, use uno diferente |
| Error al eliminar carrera | Verificar que no tenga estudiantes asociados |
| Error de prerrequisito | El estudiante no ha aprobado el prerrequisito requerido |
| Notas fuera de rango | Las notas deben estar entre 0.0 y 5.0 |

### 7.2 Contacto de Soporte

Para asistencia técnica, comuníquese con el administrador del sistema.

---

**Versión del Documento**: 1.0
**Fecha de creación**: 9 de mayo de 2026
**Sistema de Gestión Académica**