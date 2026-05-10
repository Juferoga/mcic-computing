# Diagrama de Clases UML - Mermaid
## Sistema de Gestión Académica

### Descripción
Diagrama de clases que representa la arquitectura en capas del sistema: Modelo (dominio), DAO (acceso a datos), Servicios, Controladores y Vista (JavaFX).

```mermaid
classDiagram
    namespace Modelo {
        class Carrera {
            -String id
            -String nombre
            -String registroCalificacion
            +getters()
            +setters()
        }

        class Estudiante {
            -String codE
            -String nomE
            -String dirE
            -String telE
            -LocalDate fechaNac
            -String idCarr
            +getters()
            +setters()
        }

        class Asignatura {
            -String codA
            -String nombre
            -int intensidadHoraria
            -int creditos
            +getters()
            +setters()
        }

        class Profesor {
            -String id
            -String nombre
            -String direccion
            -String telefono
            -String profesion
            +getters()
            +setters()
        }

        class Imparte {
            -int grupo
            -String horario
            +getters()
            +setters()
        }

        class Inscripcion {
            -double n1
            -double n2
            -double n3
            -double definitiva
            +getters()
            +setters()
            +calcularDefinitiva()
        }

        class Requiere {
            -String codA
            -String codAReq
            +getters()
            +setters()
        }
    }

    namespace DAO {
        class GenericDAO~T~ {
            <<interface>>
            +boolean insertar(T entidad)
            +boolean modificar(T entidad)
            +boolean eliminar(String id)
            +List~T~ buscarTodos()
            +T buscarPorId(String id)
        }

        class CarreraDAO
        class EstudianteDAO
        class AsignaturaDAO
        class ProfesorDAO
        class ImparteDAO
        class InscribeDAO
    }

    namespace Servicios {
        class UniversidadService {
            +gestionarCarreras()
            +gestionarEstudiantes()
            +gestionarAsignaturas()
            +gestionarProfesores()
            +gestionarInscripciones()
            +registrarNotas()
            +validarPrerrequisitos(codE, codA) boolean
        }
    }

    namespace Controladores {
        class ControladorCarrera {
            -CarreraDAO dao
            +guardar()
            +eliminar()
            +listar()
        }

        class ControladorEstudiante {
            -EstudianteDAO dao
            +guardar()
            +eliminar()
            +listar()
        }

        class ControladorAsignatura {
            -AsignaturaDAO dao
            +guardar()
            +eliminar()
            +listar()
        }

        class ControladorProfesor {
            -ProfesorDAO dao
            +guardar()
            +eliminar()
            +listar()
            +asignarCarga()
        }

        class ControladorInscripcion {
            -InscribeDAO dao
            +guardar()
            +eliminar()
            +listar()
            +actualizarNotas()
        }
    }

    namespace Vista {
        class VentanaPrincipal {
            -menuBar
            -panelCentral
            -statusBar
        }

        class PanelCarreras {
            -tableView
            -btnAgregar
            -btnEditar
        }

        class PanelEstudiantes {
            -tableView
            -btnAgregar
            -btnEditar
        }

        class PanelAsignaturas {
            -tableView
            -btnAgregar
            -btnEditar
        }

        class PanelProfesores {
            -tableView
            -btnAgregar
            -btnEditar
        }

        class PanelInscripciones {
            -tableView
            -btnAgregar
            -btnEditar
        }
    }

    %% Relaciones del Modelo
    Estudiante "N" --> "1" Carrera : idCarr
    Imparte "N" --> "1" Asignatura : codA
    Imparte "N" --> "1" Profesor : idP
    Inscripcion "N" --> "1" Imparte : "(idP, codA, grupo)"

    %% DAO implementa interfaz genérica
    GenericDAO <|.. CarreraDAO
    GenericDAO <|.. EstudianteDAO
    GenericDAO <|.. AsignaturaDAO
    GenericDAO <|.. ProfesorDAO
    GenericDAO <|.. ImparteDAO
    GenericDAO <|.. InscribeDAO

    %% DAOs dependen de Modelo
    CarreraDAO ..> Carrera
    EstudianteDAO ..> Estudiante
    AsignaturaDAO ..> Asignatura
    ProfesorDAO ..> Profesor
    ImparteDAO ..> Imparte
    InscribeDAO ..> Inscripcion

    %% Controladores dependen de DAOs
    ControladorCarrera ..> CarreraDAO
    ControladorEstudiante ..> EstudianteDAO
    ControladorAsignatura ..> AsignaturaDAO
    ControladorProfesor ..> ProfesorDAO
    ControladorInscripcion ..> InscribeDAO

    %% Servicios dependen de Controladores
    UniversidadService ..> ControladorCarrera
    UniversidadService ..> ControladorEstudiante
    UniversidadService ..> ControladorAsignatura
    UniversidadService ..> ControladorProfesor
    UniversidadService ..> ControladorInscripcion

    %% Vista depende de paneles
    VentanaPrincipal ..> PanelCarreras
    VentanaPrincipal ..> PanelEstudiantes
    VentanaPrincipal ..> PanelAsignaturas
    VentanaPrincipal ..> PanelProfesores
    VentanaPrincipal ..> PanelInscripciones
```

### Conexión a Base de Datos (Singleton)

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

**Versión**: 1.0 (Mermaid)
**Fecha**: 9 de mayo de 2026
**Autor**: Proyecto Académico
