# Diagrama de Clases UML
## Sistema de Gestión Académica

---

## 1. Diagrama General

```
┌─────────────────────────────────────────────────────────────────────────┐
│                           MODELO (DOMINIO)                              │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌──────────────┐       ┌──────────────┐       ┌──────────────┐        │
│  │   Carrera    │       │  Estudiante  │       │ Asignatura   │        │
│  ├──────────────┤       ├──────────────┤       ├──────────────┤        │
│  │ - id: String │       │ - codE: String│       │ - codA: String│        │
│  │ - nombre: String│    │ - nomE: String│       │ - nombre: String│      │
│  │ - registroCalif│      │ - dirE: String│       │ - intHoraria: int│     │
│  ├──────────────┤       │ - telE: String│       │ - creditos: int│        │
│  │ + getters()  │       │ - fechaNac: Date│     ├──────────────┤        │
│  │ + setters()  │       │ - idCarr: String│      │ + getters()  │        │
│  └──────────────┘       ├──────────────┤       │ + setters()  │        │
│                         │ + getters()  │       └──────────────┘        │
│                         │ + setters()  │                               │
│                         └──────────────┘                               │
│                                                                         │
│  ┌──────────────┐       ┌──────────────┐                               │
│  │   Profesor   │       │  Inscripcion  │                              │
│  ├──────────────┤       ├──────────────┤                               │
│  │ - id: String │       │ - n1: double │                              │
│  │ - nombre: String│    │ - n2: double │                              │
│  │ - dir: String │       │ - n3: double │                              │
│  │ - tel: String │       │ - def: double│                              │
│  │ - profesion: String│  ├──────────────┤                               │
│  ├──────────────┤       │ + getters()  │                               │
│  │ + getters()  │       │ + setters()  │                               │
│  │ + setters()  │       │ + calcularDef()│                             │
│  └──────────────┘       └──────────────┘                               │
│                                                                         │
│  ┌──────────────┐       ┌──────────────┐                               │
│  │   Imparte    │       │   Requiere    │                              │
│  ├──────────────┤       ├──────────────┤                               │
│  │ - grupo: int │       │ - codA: String│                               │
│  │ - horario: String│   │ - codAReq: String│                           │
│  ├──────────────┤       ├──────────────┤                               │
│  │ + getters()  │       │ + getters()  │                               │
│  │ + setters()  │       │ + setters()  │                               │
│  └──────────────┘       └──────────────┘                               │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘

                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         DAO (DATA ACCESS)                              │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐     │
│  │  CarreraDAO      │  │ EstudianteDAO     │  │ AsignaturaDAO   │     │
│  ├──────────────────┤  ├──────────────────┤  ├──────────────────┤     │
│  │ + insertar()     │  │ + insertar()     │  │ + insertar()     │     │
│  │ + modificar()    │  │ + modificar()    │  │ + modificar()    │     │
│  │ + eliminar()     │  │ + eliminar()     │  │ + eliminar()     │     │
│  │ + buscarTodos()  │  │ + buscarTodos()  │  │ + buscarTodos()  │     │
│  │ + buscarPorId()  │  │ + buscarPorId() │  │ + buscarPorId()  │     │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘     │
│                                                                         │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐     │
│  │  ProfesorDAO     │  │  ImparteDAO      │  │  InscribeDAO     │     │
│  ├──────────────────┤  ├──────────────────┤  ├──────────────────┤     │
│  │ + insertar()     │  │ + insertar()     │  │ + insertar()     │     │
│  │ + modificar()    │  │ + modificar()    │  │ + modificar()    │     │
│  │ + eliminar()     │  │ + eliminar()     │  │ + eliminar()     │     │
│  │ + buscarTodos()  │  │ + buscarTodos()  │  │ + buscarTodos()  │     │
│  │ + buscarPorId()  │  │ + buscarPorId()  │  │ + buscarPorId()  │     │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘     │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘

                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         SERVICIOS                                       │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌─────────────────────────────────────────────────────────────────┐   │
│  │                    UniversidadService                           │   │
│  ├─────────────────────────────────────────────────────────────────┤   │
│  │ + gestionarCarreras(): void                                    │   │
│  │ + gestionarEstudiantes(): void                                  │   │
│  │ + gestionarAsignaturas(): void                                  │   │
│  │ + gestionarProfesores(): void                                  │   │
│  │ + gestionarInscripciones(): void                               │   │
│  │ + registrarNotas(): void                                       │   │
│  │ + validarPrerrequisitos(codE, codA): boolean                   │   │
│  └─────────────────────────────────────────────────────────────────┘   │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘

                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                      CONTROLADORES (CONTROLLER)                         │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐      │
│  │ ControladorCarrera│  │ControladorEstudiante│ │ControladorAsignatura│  │
│  ├──────────────────┤  ├──────────────────┤  ├──────────────────┤    │
│  │ - dao: CarreraDAO │  │ - dao: EstudianteDAO│ │ - dao: AsignaturaDAO│  │
│  ├──────────────────┤  ├──────────────────┤  ├──────────────────┤    │
│  │ + guardar()      │  │ + guardar()      │  │ + guardar()      │    │
│  │ + eliminar()      │  │ + eliminar()      │  │ + eliminar()      │    │
│  │ + listar()        │  │ + listar()        │  │ + listar()        │    │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘      │
│                                                                         │
│  ┌──────────────────┐  ┌──────────────────┐                          │
│  │ControladorProfesor│  │ControladorInscripcion│                      │
│  ├──────────────────┤  ├──────────────────┤                            │
│  │ - dao: ProfesorDAO│  │ - dao: InscribeDAO │                        │
│  ├──────────────────┤  ├──────────────────┤                            │
│  │ + guardar()      │  │ + guardar()      │                            │
│  │ + eliminar()      │  │ + eliminar()      │                            │
│  │ + listar()        │  │ + listar()        │                            │
│  │ + asignarCarga()  │  │ + actualizarNotas()│                           │
│  └──────────────────┘  └──────────────────┘                              │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘

                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         VISTA (UI - JavaFX)                             │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐                │
│  │ VentanaPrincipal │ │ PanelCarreras │ │ PanelEstudiantes│             │
│  ├──────────────┤  ├──────────────┤  ├──────────────┤                │
│  │ - menuBar    │  │ - tableView  │  │ - tableView  │                │
│  │ - panelCentral│ │ - btnAgregar │  │ - btnAgregar │                │
│  │ - statusBar  │  │ - btnEditar  │  │ - btnEditar  │                │
│  └──────────────┘  └──────────────┘  └──────────────┘                │
│                                                                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐                │
│  │PanelAsignaturas│ │ PanelProfesores│ │ PanelInscripciones│           │
│  ├──────────────┤  ├──────────────┤  ├──────────────┤                │
│  │ - tableView  │  │ - tableView  │  │ - tableView  │                │
│  │ - btnAgregar │  │ - btnAgregar │  │ - btnAgregar │                │
│  │ - btnEditar  │  │ - btnEditar  │  │ - btnEditar  │                │
│  └──────────────┘  └──────────────┘  └──────────────┘                │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## 2. Clases del Modelo (POJOs)

### 2.1 Carrera
```java
public class Carrera {
    private String id;
    private String nombre;
    private String registroCalificacion;

    // Constructores, Getters, Setters
}
```

### 2.2 Estudiante
```java
public class Estudiante {
    private String codE;
    private String nomE;
    private String dirE;
    private String telE;
    private LocalDate fechNac;
    private String idCarr;

    // Constructores, Getters, Setters
}
```

### 2.3 Asignatura
```java
public class Asignatura {
    private String codA;
    private String nombre;
    private int intensidadHoraria;
    private int creditos;

    // Constructores, Getters, Setters
}
```

### 2.4 Profesor
```java
public class Profesor {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String profesion;

    // Constructores, Getters, Setters
}
```

### 2.5 Imparte
```java
public class Imparte {
    private String idProfesor;
    private String codAsignatura;
    private int grupo;
    private String horario;

    // Constructores, Getters, Setters
}
```

### 2.6 Inscripcion
```java
public class Inscripcion {
    private String codEstudiante;
    private String codAsignatura;
    private String idProfesor;
    private int grupo;
    private double n1;
    private double n2;
    private double n3;
    private double definitiva;

    // Constructores, Getters, Setters
    // + calcularDefinitiva(): void
}
```

---

## 3. Clases de Acceso a Datos (DAO)

### 3.1 Interfaz Genérica
```java
public interface GenericDAO<T> {
    boolean insertar(T entidad);
    boolean modificar(T entidad);
    boolean eliminar(String id);
    List<T> buscarTodos();
    T buscarPorId(String id);
}
```

### 3.2 CarreraDAO
```java
public class CarreraDAO implements GenericDAO<Carrera> {
    private Connection conexion;

    @Override
    public boolean insertar(Carrera c) { ... }
    @Override
    public boolean modificar(Carrera c) { ... }
    @Override
    public boolean eliminar(String id) { ... }
    @Override
    public List<Carrera> buscarTodos() { ... }
    @Override
    public Carrera buscarPorId(String id) { ... }
}
```

---

## 4. Conexión (Singleton)

```java
public class ConexionDB {
    private static Connection instancia = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/db_sistema_academico";
    private static final String USER = "app_admin";
    private static final String PASSWORD = "Admin_Secret2026*";

    private ConexionDB() {}

    public static Connection getConexion() {
        if (instancia == null) {
            // Cargar driver y establecer conexión
        }
        return instancia;
    }
}
```

---

## 5. Relaciones entre Clases

```
┌────────────────┐         ┌────────────────┐
│   Estudiante   │         │    Carrera    │
├────────────────┤         ├────────────────┤
│ - idCarr: String│─────────│ - id: String  │
└────────────────┘    N:1  └────────────────┘

┌────────────────┐         ┌────────────────┐
│    Imparte     │         │   Asignatura   │
├────────────────┤         ├────────────────┤
│ - codA: String │─────────│ - codA: String │
└────────────────┘    N:1 └────────────────┘

┌────────────────┐         ┌────────────────┐
│    Imparte     │         │   Profesor     │
├────────────────┤         ├────────────────┤
│ - idP: String  │─────────│ - id: String   │
└────────────────┘    N:1 └────────────────┘

┌────────────────┐         ┌────────────────┐
│   Inscripcion  │         │    Imparte    │
├────────────────┤         ├────────────────┤
│ - (idP,codA,   │─────────│ - (idP,codA,   │
│   grupo)       │    N:1 │   grupo)       │
└────────────────┘         └────────────────┘
```

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026