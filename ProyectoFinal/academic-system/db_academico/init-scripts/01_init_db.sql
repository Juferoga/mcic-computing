-- ==========================================
-- INICIALIZACIÓN BASE DE DATOS ACADÉMICA
-- Script ejecutado automáticamente por Docker
-- ==========================================

-- ==========================================
-- 1. CREACIÓN DEL ESQUEMA Y TABLAS
-- ==========================================
CREATE SCHEMA IF NOT EXISTS gestion_academica;
SET search_path TO gestion_academica;

-- Tabla Carreras
CREATE TABLE IF NOT EXISTS Carreras (
    id_carr VARCHAR(10) PRIMARY KEY,
    nom_carr VARCHAR(100) NOT NULL,
    reg_calif VARCHAR(50)
);

-- Tabla Estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes (
    cod_e VARCHAR(15) PRIMARY KEY,
    nom_e VARCHAR(100) NOT NULL,
    dir_e VARCHAR(150),
    tel_e VARCHAR(20),
    fech_nac DATE,
    id_carr VARCHAR(10) REFERENCES Carreras(id_carr) ON DELETE RESTRICT
);

-- Tabla Asignaturas
CREATE TABLE IF NOT EXISTS Asignaturas (
    cod_a VARCHAR(10) PRIMARY KEY,
    nom_a VARCHAR(100) NOT NULL,
    int_h INT,
    creditos INT
);

-- Tabla Profesores
CREATE TABLE IF NOT EXISTS Profesores (
    id_p VARCHAR(15) PRIMARY KEY,
    nom_p VARCHAR(100) NOT NULL,
    dir_p VARCHAR(150),
    tel_p VARCHAR(20),
    profesion VARCHAR(100)
);

-- Tabla Imparte (Asignación de carga académica al profesor)
CREATE TABLE IF NOT EXISTS Imparte (
    id_p VARCHAR(15) REFERENCES Profesores(id_p),
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    grupo INT,
    horario VARCHAR(50),
    PRIMARY KEY (id_p, cod_a, grupo)
);

-- Tabla Inscribe (Estudiantes matriculados con notas)
CREATE TABLE IF NOT EXISTS Inscribe (
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

-- Tabla Incluye (Pensum: Relación Carrera - Asignatura)
CREATE TABLE IF NOT EXISTS Incluye (
    ic_d VARCHAR(10) REFERENCES Carreras(id_carr),
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    PRIMARY KEY (ic_d, cod_a)
);

-- Tabla Requiere (Prerrequisitos de materias - Recursiva)
CREATE TABLE IF NOT EXISTS Requiere (
    cod_a VARCHAR(10) REFERENCES Asignaturas(cod_a),
    cod_a_r VARCHAR(10) REFERENCES Asignaturas(cod_a),
    PRIMARY KEY (cod_a, cod_a_r)
);

-- ==========================================
-- 2. LÓGICA DE NEGOCIO (TRIGGERS Y SP)
-- ==========================================

-- Trigger para cálculo automático de nota definitiva
CREATE OR REPLACE FUNCTION fn_calcular_definitiva()
RETURNS TRIGGER AS $$
BEGIN
    NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_calculo_notas ON Inscribe;
CREATE TRIGGER trg_calculo_notas
BEFORE INSERT OR UPDATE OF n1, n2, n3 ON Inscribe
FOR EACH ROW
EXECUTE FUNCTION fn_calcular_definitiva();

-- Procedimiento almacenado para inscripción con validación de prerrequisitos
CREATE OR REPLACE PROCEDURE sp_inscribir_estudiante(
    p_cod_e VARCHAR,
    p_cod_a VARCHAR,
    p_id_p VARCHAR,
    p_grupo INT
) LANGUAGE plpgsql AS $$
DECLARE
    v_prereq VARCHAR;
    v_aprobada BOOLEAN;
BEGIN
    FOR v_prereq IN SELECT cod_a_r FROM Requiere WHERE cod_a = p_cod_a LOOP
        SELECT EXISTS (
            SELECT 1 FROM Inscribe
            WHERE cod_e = p_cod_e AND cod_a = v_prereq AND def >= 3.0
        ) INTO v_aprobada;

        IF NOT v_aprobada THEN
            RAISE EXCEPTION 'Fallo de inscripción: El estudiante % no ha aprobado el prerrequisito %', p_cod_e, v_prereq;
        END IF;
    END LOOP;

    INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo)
    VALUES (p_cod_e, p_cod_a, p_id_p, p_grupo);

    RAISE NOTICE 'Inscripción realizada con éxito.';
END;
$$;

-- ==========================================
-- 3. DATOS DE PRUEBA (MOCK DATA)
-- ==========================================

-- Carreras
INSERT INTO Carreras (id_carr, nom_carr, reg_calif) VALUES
    ('SIS', 'Ingeniería de Sistemas', 'RES-2026-001'),
    ('IND', 'Ingeniería Industrial', 'RES-2026-002')
ON CONFLICT (id_carr) DO NOTHING;

-- Asignaturas
INSERT INTO Asignaturas (cod_a, nom_a, int_h, creditos) VALUES
    ('PROG1', 'Programación Básica', 4, 3),
    ('PROG2', 'Programación Orientada a Objetos', 4, 3),
    ('BD1', 'Bases de Datos', 4, 3)
ON CONFLICT (cod_a) DO NOTHING;

-- Prerrequisitos
INSERT INTO Requiere (cod_a, cod_a_r) VALUES
    ('PROG2', 'PROG1')
ON CONFLICT (cod_a, cod_a_r) DO NOTHING;

-- Pensum (Incluye)
INSERT INTO Incluye (ic_d, cod_a) VALUES
    ('SIS', 'PROG1'),
    ('SIS', 'PROG2'),
    ('SIS', 'BD1')
ON CONFLICT DO NOTHING;

-- Profesores
INSERT INTO Profesores (id_p, nom_p, dir_p, tel_p, profesion) VALUES
    ('P001', 'Alan Turing', 'Calle 123', '3001112233', 'Matemático'),
    ('P002', 'Grace Hopper', 'Carrera 45', '3109998877', 'Científica de Computación')
ON CONFLICT (id_p) DO NOTHING;

-- Imparte (Carga académica)
INSERT INTO Imparte (id_p, cod_a, grupo, horario) VALUES
    ('P001', 'PROG1', 1, 'Lunes 8-10am'),
    ('P002', 'PROG2', 1, 'Miércoles 10-12m')
ON CONFLICT DO NOTHING;

-- Estudiantes
INSERT INTO Estudiantes (cod_e, nom_e, dir_e, tel_e, fech_nac, id_carr) VALUES
    ('E100', 'Carlos Martínez', 'Av Siempre Viva', '3205556677', '2000-05-15', 'SIS'),
    ('E101', 'Ana Rojas', 'Calle Falsa 123', '3002223344', '2001-08-20', 'SIS')
ON CONFLICT (cod_e) DO NOTHING;

-- Inscripciones con notas (para demostrar el trigger)
INSERT INTO Inscribe (cod_e, cod_a, id_p, grupo, n1, n2, n3)
VALUES ('E100', 'PROG1', 'P001', 1, 4.0, 3.5, 4.2)
ON CONFLICT DO NOTHING;

-- ==========================================
-- 4. SEGURIDAD DCL Y ROLES
-- ==========================================

-- Crear roles
DO $$
BEGIN
    CREATE ROLE rol_admin_academico;
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

DO $$
BEGIN
    CREATE ROLE rol_profesor;
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

DO $$
BEGIN
    CREATE ROLE rol_estudiante;
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

-- Permisos para ADMIN
GRANT USAGE ON SCHEMA gestion_academica TO rol_admin_academico;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA gestion_academica TO rol_admin_academico;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA gestion_academica TO rol_admin_academico;

-- Permisos para PROFESOR
GRANT USAGE ON SCHEMA gestion_academica TO rol_profesor;
GRANT SELECT ON Estudiantes, Asignaturas, Carreras, Imparte, Incluye, Requiere TO rol_profesor;
GRANT SELECT, UPDATE (n1, n2, n3) ON Inscribe TO rol_profesor;

-- Permisos para ESTUDIANTE
GRANT USAGE ON SCHEMA gestion_academica TO rol_estudiante;
GRANT SELECT ON Asignaturas, Carreras, Imparte TO rol_estudiante;
GRANT SELECT ON Inscribe TO rol_estudiante;

-- Crear usuarios
DO $$
BEGIN
    CREATE USER app_admin WITH PASSWORD 'Admin_Secret2026*';
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

DO $$
BEGIN
    CREATE USER app_profesor WITH PASSWORD 'Prof_Secret2026*';
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

DO $$
BEGIN
    CREATE USER app_estudiante WITH PASSWORD 'Est_Secret2026*';
EXCEPTION WHEN duplicate_object THEN
    NULL;
END
$$;

-- Asignar roles a usuarios
GRANT rol_admin_academico TO app_admin;
GRANT rol_profesor TO app_profesor;
GRANT rol_estudiante TO app_estudiante;

-- Confirmar éxito
DO $$
BEGIN
    RAISE NOTICE 'Base de datos académica inicializada correctamente';
END
$$;