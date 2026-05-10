# Diagrama Relacional
## Sistema de Gestión Académica

### Esquema: `gestion_academica`

---

## 1. Tablas Base

### 1.1 Carreras
```sql
CREATE TABLE Carreras (
    id_carr VARCHAR(10) PRIMARY KEY,
    nom_carr VARCHAR(100) NOT NULL,
    reg_calif VARCHAR(50)
);
```

### 1.2 Estudiantes
```sql
CREATE TABLE Estudiantes (
    cod_e VARCHAR(15) PRIMARY KEY,
    nom_e VARCHAR(100) NOT NULL,
    dir_e VARCHAR(150),
    tel_e VARCHAR(20),
    fech_nac DATE,
    id_carr VARCHAR(10) REFERENCES Carreras(id_carr) ON DELETE RESTRICT
);
```

### 1.3 Asignaturas
```sql
CREATE TABLE Asignaturas (
    cod_a VARCHAR(10) PRIMARY KEY,
    nom_a VARCHAR(100) NOT NULL,
    int_h INT,
    creditos INT
);
```

### 1.4 Profesores
```sql
CREATE TABLE Profesores (
    id_p VARCHAR(15) PRIMARY KEY,
    nom_p VARCHAR(100) NOT NULL,
    dir_p VARCHAR(150),
    tel_p VARCHAR(20),
    profesion VARCHAR(100)
);
```

---

## 2. Tablas de Relación

### 2.1 Imparte (Profesor-Asignatura-Grupo)
```sql
CREATE TABLE Imparte (
    id_p VARCHAR(15) REFERENCES Profesores(id_p),
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    grupo INT,
    horario VARCHAR(50),
    PRIMARY KEY (id_p, cod_a, grupo)
);
```

### 2.2 Inscribe (Estudiante-Asignatura-Profesor-Grupo)
```sql
CREATE TABLE Inscribe (
    cod_e VARCHAR(15) REFERENCES Estudiantes(cod_e),
    cod_a VARCHAR(10),
    id_p VARCHAR(15),
    grupo INT,
    n1 NUMERIC(3,2) DEFAULT 0.00,
    n2 NUMERIC(3,2) DEFAULT 0.00,
    n3 NUMERIC(3,2) DEFAULT 0.00,
    def NUMERIC(3,2) DEFAULT 0.00,
    FOREIGN KEY (id_p, cod_a, grupo) REFERENCES Imparte(id_p, cod_a, grupo),
    PRIMARY KEY (cod_e, cod_a, id_p, grupo)
);
```

### 2.3 Incluye (Carrera-Asignatura - Pensum)
```sql
CREATE TABLE Incluye (
    ic_d VARCHAR(10) REFERENCES Carreras(id_carr),
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    PRIMARY KEY (ic_d, cod_a)
);
```

### 2.4 Requiere (Prerrequisitos)
```sql
CREATE TABLE Requiere (
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    cod_a_r VARCHAR(10) REFERENCES Asignaturas(cod_a),
    PRIMARY KEY (cod_a, cod_a_r)
);
```

---

## 3. Representación Gráfica

```
┌─────────────────┐       ┌─────────────────┐
│   CARRERAS      │       │   ESTUDIANTES   │
├─────────────────┤       ├─────────────────┤
│ PK id_carr      │◄──────│ FK id_carr      │
│ nom_carr        │       │ PK cod_e        │
│ reg_calif       │       │ nom_e           │
└────────┬────────┘       │ dir_e           │
         │               │ tel_e           │
         │               │ fech_nac        │
         │               └────────┬────────┘
         │                        │
         │                        │
         ▼                        │
┌─────────────────┐               │
│    INCLUYE      │               │
├─────────────────┤               │
│ PK FK id_carr   │               │
│ PK FK cod_a     │               │
└────────┬────────┘               │
         │                        │
         ▼                        │
┌─────────────────┐       ┌─────────────────┐
│  ASIGNATURAS    │       │   REQUIERE     │
├─────────────────┤       ├─────────────────┤
│ PK cod_a        │◄──────│ PK FK cod_a    │
│ nom_a           │       │ PK FK cod_a_r  │
│ int_h           │       └────────┬────────┘
│ creditos        │                │
└────────┬────────┘                │
         │                         │
         │                         ▼
         │                ┌─────────────────┐
         │                │    IMPARTE     │
         │                ├─────────────────┤
         │                │ PK FK id_p     │
         │                │ PK FK cod_a    │
         │                │ PK grupo       │
         │                │ horario        │
         │                └────────┬────────┘
         │                         │
         │                         │
         ▼                         │
┌─────────────────┐                │
│    INSCRIBE     │                │
├─────────────────┤                │
│ PK FK cod_e    │◄───────────────┘
│ PK FK (cod_a,  │
│     id_p, grupo│
│ n1             │
│ n2             │
│ n3             │
│ def            │
└─────────────────┘

┌─────────────────┐
│   PROFESORES   │
├─────────────────┤
│ PK id_p        │
│ nom_p          │
│ dir_p          │
│ tel_p          │
│ profesion      │
└─────────────────┘
```

---

## 4. Integridad Referencial

| Tabla Hija | Columna(s) FK | Tabla Padre | Acción ON DELETE |
|------------|---------------|-------------|------------------|
| Estudiantes | id_carr | Carreras | RESTRICT |
| Imparte | id_p | Profesores | CASCADE |
| Imparte | cod_a | Asignaturas | CASCADE |
| Inscribe | cod_e | Estudiantes | CASCADE |
| Inscribe | (id_p, cod_a, grupo) | Imparte | CASCADE |
| Incluye | ic_d | Carreras | CASCADE |
| Incluye | cod_a | Asignaturas | CASCADE |
| Requiere | cod_a | Asignaturas | CASCADE |
| Requiere | cod_a_r | Asignaturas | CASCADE |

---

## 5. Índices Recomendados

```sql
-- Índice para buscar estudiantes por carrera
CREATE INDEX idx_estudiante_carrera ON Estudiantes(id_carr);

-- Índice para buscar inscripciones por estudiante
CREATE INDEX idx_inscribe_estudiante ON Inscribe(cod_e);

-- Índice para buscar asignaturas por prerrequisito
CREATE INDEX idx_requiere_prerrequisito ON Requiere(cod_a_r);

-- Índice para buscar carga académica por profesor
CREATE INDEX idx_imparte_profesor ON Imparte(id_p);
```

---

## 6. Triggers y Funciones

### 6.1 Trigger para cálculo automático de nota definitiva

```sql
CREATE OR REPLACE FUNCTION fn_calcular_definitiva()
RETURNS TRIGGER AS $$
BEGIN
    NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calculo_notas
BEFORE INSERT OR UPDATE OF n1, n2, n3 ON Inscribe
FOR EACH ROW
EXECUTE FUNCTION fn_calcular_definitiva();
```

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026