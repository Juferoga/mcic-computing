# Historia de Usuario - HU07

## Registrar Notas

---

### Información General

| Campo | Valor |
|-------|-------|
| **ID** | HU07 |
| **Nombre** | Registrar Notas |
| **Módulo** | Académico |
| **Prioridad** | Crítica |
| **Estimación** | 8 puntos |
| **Sprint** | 2 |
| **Dependencias** | HU06 (Gestionar Inscripciones) |

---

### Descripción

| Tipo | Descripción |
|------|-------------|
| **Como** | Administrador / Profesor |
| **Quiero** | Registrar las calificaciones (n1, n2, n3) de los estudiantes |
| **Para** | Calcular automáticamente la nota definitiva y mantener el historial académico actualizado |

---

### Subtareas

```
[TAREAS]
├── T-01: Crear formulario de registro de notas
├── T-02: Seleccionar asignatura y grupo
├── T-03: Mostrar estudiantes inscritos con notas actuales
├── T-04: Seleccionar estudiante para editar
├── T-05: Validar rango de notas (0.0 - 5.0)
├── T-06: Implementar actualización de notas en BD
├── T-07: Verificar que el trigger calcula la definitiva
├── T-08: Mostrar nota definitiva calculada
├── T-09: Implementar permisos por rol (Profesor solo sus grupos)
├── T-10: Probar integración con Trigger de PostgreSQL
└── T-11: Crear pruebas unitarias
```

---

### Criterios de Aceptación

#### Criterio 1: Registrar Notas

| ID | Descripción | Validación |
|----|-------------|------------|
| CA-01 | Seleccionar asignatura y grupo | Dropdown de carga académica |
| CA-02 | Mostrar tabla de estudiantes inscritos | Con columnas: nombre, n1, n2, n3, def |
| CA-03 | Editar notas de cada estudiante | Campos editables |
| CA-04 | Validar notas entre 0.0 y 5.0 | Error si está fuera de rango |
| CA-05 | Guardar notas en PostgreSQL | UPDATE en tabla Inscribe |
| CA-06 | Trigger calcula definitiva automáticamente | Verificar campo def |

#### Criterio 2: Ver Notas

| ID | Descripción | Validación |
|----|-------------|------------|
| CB-01 | Ver notas de estudiantes propios | Para rol profesor |
| CB-02 | Ver notas propias | Para rol estudiante |
| CB-03 | Ver nota definitiva con fórmula aplicada | Mostrar cálculo |

#### Criterio 3: Permisos

| ID | Descripción | Validación |
|----|-------------|------------|
| CC-01 | Administrador puede editar todas las notas | Acceso total |
| CC-02 | Profesor solo puede editar notas de sus grupos | WHERE en SQL |
| CC-03 | Estudiante solo puede ver sus notas | Restricción SELECT |

---

### Flujo de Prueba

```
ESCENARIO: Registrar notas de un corte

DADO que estoy en el panel de registro de notas
Y selecciono "Programación Básica" - Grupo 1
CUANDO muestro la lista de estudiantes
Y selecciono al estudiante "Carlos Martínez"
Y ingreso las notas:
  - n1: 4.0
  - n2: 3.5
  - n3: (pendiente, dejar en 0)
Y hago clic en "Guardar"
ENTONCES el sistema:
1. Ejecuta UPDATE Inscribe SET n1=4.0, n2=3.5 WHERE cod_e='E100'
2. El trigger trg_calculo_notas se dispara
3. Calcula: def = (4.0*0.35) + (3.5*0.35) + (0*0.30) = 2.625
4. Muestra la nota definitiva calculada

ESCENARIO: Validación de rango

DADO que estoy editando las notas de un estudiante
CUANDO ingreso n1 = 5.5 (mayor a 5.0)
ENTONCES el sistema muestra error: "La nota debe estar entre 0.0 y 5.0"
Y no permite guardar

ESCENARIO: Profesor modifica notas de otro grupo

DADO que el profesor "Alan Turing" dicta solo "Programación Básica" grupo 1
CUANDO intenta acceder a notas de "Programación II" grupo 1
ENTONCES el sistema muestra error: "No tiene permisos para este grupo"
```

---

### Fórmula de Cálculo (Implementada en Trigger)

```
definitiva = (n1 × 0.35) + (n2 × 0.35) + (n3 × 0.30)

Ejemplo:
n1 = 4.0, n2 = 3.5, n3 = 4.2
def = (4.0 × 0.35) + (3.5 × 0.35) + (4.2 × 0.30)
def = 1.40 + 1.225 + 1.26
def = 3.885 ≈ 3.89
```

---

### Validaciones de Negocio

| # | Regla | Tipo |
|---|-------|------|
| VN-01 | Notas entre 0.0 y 5.0 | Rango válido |
| VN-02 | def se calcula automáticamente | Trigger |
| VN-03 | Profesor solo edita sus grupos | Seguridad |
| VN-04 | Estudiante solo ve sus notas | Seguridad |

---

### Pantallas Relacionadas

- Panel de Registro de Notas (FXML)
- Tabla de estudiantes con notas
- Formulario de edición de notas

---

### Notas Técnicas

- Tabla objetivo: `gestion_academica.Inscribe`
- Trigger: `trg_calculo_notas` (BEFORE INSERT OR UPDATE)
- Función: `fn_calcular_definitiva()`
- Permisos PostgreSQL: `GRANT UPDATE (n1, n2, n3) ON Inscribe TO rol_profesor`

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Responsable**: Equipo de Desarrollo