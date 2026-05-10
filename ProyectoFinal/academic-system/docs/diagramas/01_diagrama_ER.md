# Diagrama Entidad-Relación (ER)
## Sistema de Gestión Académica

### 1. Representación Visual

```
                    +------------------+
                    |    CARRERAS      |
                    +------------------+
                    | PK id_carr       |
                    | nom_carr         |
                    | reg_calif        |
                    +----------+-------+
                               |
                               | 1:N
                               |
                    +----------+---------------+
                    |         INCLUYE         |
                    +---------------------------+
                     | PK FK ic_d (Carreras)    |
                    | PK FK cod_a (Asignaturas)|
                    +----------+---------------+
                               |
                               | N:1
                               v
         +---------------------+---------------------+
         |                                         |
         |  +------------------+   +-----------+  |
         |  |   ASIGNATURAS     |   | REQUIERE  |  |
         |  +------------------+   +-----------+  |
         |  | PK cod_a         |   | PK cod_a  |--|--+
         |  | nom_a            |   | PK cod_a_r|  |  |
         |  | int_h            |   +-----------+  |  |
         |  | creditos         |                   |  |
         |  +--------+---------+                   |  |
         |           |                             |  |
         |           | 1:N                         | 1:N
         |           |                             |  |
+--------+-----------+-------------+--------------+  |
|                                         |         |
|  +------------------+   +-----------+  |         |
|  |    IMPARTE       |   | INSCRIBE  |  |         |
|  +------------------+   +-----------+  |         |
|  | PK FK id_p       |<--| PK FK cod_e|----------+
|  | PK FK cod_a      |   | PK cod_a  |<-+
|  | PK grupo         |   | PK id_p   |  |
|  | horario          |   | PK grupo  |  |1:N
|  +--------+---------+   | n1        |  |
           |               | n2        |  |
           |1:N            | n3        |  |
           v               | def       |  |
+----------+-------------+ +-----------+  |
|                         |              |
|  +------------------+   |              |
|  |   PROFESORES     |   |              |
|  +------------------+   |              |
|  | PK id_p          |   |              |
|  | nom_p            |<--+              |
|  | dir_p            |                  |
|  | tel_p            |                  |
|  | profesion        |                  |
|  +------------------+                  |
|                                        |
|  +------------------+                  |
|  |   ESTUDIANTES    |<-----------------+
|  +------------------+
|  | PK cod_e         |
|  | nom_e            |
|  | dir_e            |
|  | tel_e            |
|  | fech_nac         |
|  | FK id_carr       |
|  +------------------+
```

### 2. Entidades y Atributos

#### 2.1 CARRERAS
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| id_carr | VARCHAR(10) | Identificador único de carrera | PK, NOT NULL |
| nom_carr | VARCHAR(100) | Nombre de la carrera | NOT NULL |
| reg_calif | VARCHAR(50) | Registro de calificación | Opcional |

#### 2.2 ESTUDIANTES
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| cod_e | VARCHAR(15) | Código único del estudiante | PK, NOT NULL |
| nom_e | VARCHAR(100) | Nombre completo del estudiante | NOT NULL |
| dir_e | VARCHAR(150) | Dirección de residencia | Opcional |
| tel_e | VARCHAR(20) | Teléfono de contacto | Opcional |
| fech_nac | DATE | Fecha de nacimiento | Opcional |
| id_carr | VARCHAR(10) | Carrera a la que pertenece | FK → Carreras |

#### 2.3 ASIGNATURAS
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| cod_a | VARCHAR(10) | Código único de asignatura | PK, NOT NULL |
| nom_a | VARCHAR(100) | Nombre de la asignatura | NOT NULL |
| int_h | INT | Intensidad horaria semanal | Opcional |
| creditos | INT | Número de créditos | Opcional |

#### 2.4 PROFESORES
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| id_p | VARCHAR(15) | Identificador único del profesor | PK, NOT NULL |
| nom_p | VARCHAR(100) | Nombre completo del profesor | NOT NULL |
| dir_p | VARCHAR(150) | Dirección de residencia | Opcional |
| tel_p | VARCHAR(20) | Teléfono de contacto | Opcional |
| profesion | VARCHAR(100) | Profesión del docente | Opcional |

#### 2.5 IMPARTE (Relación)
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| id_p | VARCHAR(15) | Profesor que imparte | FK → Profesores, PK |
| cod_a | VARCHAR(10) | Asignatura que imparte | FK → Asignaturas, PK |
| grupo | INT | Número de grupo | PK, NOT NULL |
| horario | VARCHAR(50) | Horario de la clase | Opcional |

#### 2.6 INSCRIBE (Relación)
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| cod_e | VARCHAR(15) | Estudiante Inscrito | FK → Estudiantes, PK |
| cod_a | VARCHAR(10) | Asignatura cursada | FK → Imparte, PK |
| id_p | VARCHAR(15) | Profesor que imparte | FK → Imparte, PK |
| grupo | INT | Grupo asignado | FK → Imparte, PK |
| n1 | NUMERIC(3,2) | Nota del primer corte | DEFAULT 0.00 |
| n2 | NUMERIC(3,2) | Nota del segundo corte | DEFAULT 0.00 |
| n3 | NUMERIC(3,2) | Nota del tercer corte | DEFAULT 0.00 |
| def | NUMERIC(3,2) | Nota definitiva | DEFAULT 0.00 |

#### 2.7 INCLUYE (Relación)
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| ic_d | VARCHAR(10) | Carrera del pensum | FK → Carreras, PK |
| cod_a | VARCHAR(10) | Asignatura del pensum | FK → Asignaturas, PK |

#### 2.8 REQUIERE (Relación)
| Atributo | Tipo | Descripción | Restricciones |
|----------|------|-------------|----------------|
| cod_a | VARCHAR(10) | Asignatura que requiere | FK → Asignaturas, PK |
| cod_a_r | VARCHAR(10) | Asignatura prerequisito | FK → Asignaturas, PK |

### 3. Relaciones entre Entidades

| Relación | Entidad 1 | Entidad 2 | Cardinalidad | Descripción |
|----------|-----------|-----------|--------------|-------------|
| Pertenece | Estudiantes | Carreras | N:1 | Un estudiante pertenece a una carrera |
| Imparte | Profesores | Asignaturas | N:M | Un profesor puede impartir varias asignaturas en diferentes grupos |
| Inscribe | Estudiantes | Imparte | N:M | Un estudiante se puede inscribir a varias asignaturas |
| Incluye | Carreras | Asignaturas | N:M | Una carrera incluye varias asignaturas en su pensum |
| Requiere | Asignaturas | Asignaturas | N:M | Una asignatura puede requerir prerrequisitos |

### 4. Reglas de Negocio (Restricciones)

1. **Integridad referencial**: No se puede eliminar una carrera si hay estudiantes asociados
2. **Prerrequisitos**: Un estudiante no puede inscribirse en una asignatura si no ha aprobado sus prerrequisitos (definitiva >= 3.0)
3. **Cálculo automático**: La nota definitiva se calcula automáticamente mediante trigger: `def = (n1 * 0.35) + (n2 * 0.35) + (n3 * 0.30)`
4. **Notas válidas**: Las notas n1, n2, n3 deben estar entre 0.0 y 5.0
5. **Grupo único**: La combinación (id_p, cod_a, grupo) debe ser única en la tabla Imparte

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026
**Autor**: Proyecto Académico