# Diagrama de Actividades - Gestionar Estudiantes
## CU-02: Gestionar Estudiantes

---

## 1. Diagrama de Actividades

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         INICIO DEL PROCESO                                  │
└────────────────────────────────┬────────────────────────────────────────────┘
                                 │
                                 ▼
                    ┌────────────────────────┐
                    │  Seleccionar opción   │
                    │ "Gestionar Estudiantes"│
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar Panel de     │
                    │  Gestión de Estudiantes│
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  ¿Qué acción desea    │
                    │       realizar?       │
                    └───────────┬────────────┘
                                │
           ┌────────────────────┼────────────────────┐
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │   AGREGAR     │    │   CONSULTAR   │    │   ELIMINAR    │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Mostrar form  │    │ Ingresar     │    │ Seleccionar   │
   │ de nuevo      │    │ código de    │    │ estudiante    │
   │ estudiante    │    │ estudiante   │    │ de la lista   │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Llenar datos: │    │ Buscar en    │    │ Confirmar     │
   │ - Código      │    │ base de datos│    │ eliminación   │
   │ - Nombre      │    └───────┬───────┘    └───────┬───────┘
   │ - Dirección   │            │                    │
   │ - Teléfono    │            ▼                    ▼
   │ - Fecha nac.  │    ┌───────────────┐    ┌───────────────┐
   │ - Carrera     │    │ ¿Estudiante  │    │ Eliminar de   │
   └───────┬───────┘    │ encontrado?   │    │ base de datos │
           │            └───────┬───────┘    └───────┬───────┘
           ▼                    │                    │
   ┌───────────────┐     ┌──────┴──────┐             │
   │ Validar datos │     │             │             │
   │ (código único,│     ▼             ▼             │
   │ carrera       │   SÍ             NO            │
   │ existente)    │    │             │             │
   └───────┬───────┘    ▼             ▼             ▼
           │            ┌─────────┐ ┌─────────┐ ┌─────────┐
           ▼            │Mostrar  │ │ Mostrar │ │ Mostrar │
   ┌───────────────┐    │ datos  │ │ mensaje │ │ mensaje │
   │ Guardar en   │    │estudian.│ │ "No     │ │ "Est.   │
   │ base de datos│    └─────────┘ │ encontrado"│ │eliminado"│
   └───────┬───────┘               └─────────┘ └─────────┘
           │
           ▼
   ┌───────────────┐
   │ ¿Guardado    │
   │ exitosamente?│
   └───────┬───────┘
      ┌────┴────┐
      │         │
      ▼         ▼
    SÍ         NO
      │         │
      ▼         ▼
┌───────────┐ ┌───────────┐
│ Mostrar   │ │ Mostrar   │
│ mensaje   │ │ mensaje   │
│ "Estudiante│ │ "Error al│
│ creado"   │ │ guardar"  │
└───────────┘ └───────────┘
      │
      ▼
┌───────────────────────────────────────────┐
│            FIN DEL PROCESO                 │
└───────────────────────────────────────────┘
```

---

## 2. Actividades del Flujo

| ID | Actividad | Descripción |
|----|-----------|-------------|
| A1 | Seleccionar opción | El usuario hace clic en "Gestionar Estudiantes" |
| A2 | Mostrar panel | Se carga la interfaz con la lista de estudiantes |
| A3 | Seleccionar acción | El usuario elige entre agregar, consultar, modificar o eliminar |
| A4 | Mostrar formulario | Se presenta el formulario para ingreso de datos |
| A5 | Ingresar datos | El usuario llena: código, nombre, dirección, teléfono, fecha nacimiento, carrera |
| A6 | Validar datos | Se verifican: código único, carrera existe, campos obligatorios |
| A7 | Guardar en BD | Se persiste la información en PostgreSQL |
| A8 | Buscar en BD | Se consulta el estudiante por código |
| A9 | Mostrar resultado | Se exhiben los datos encontrados o mensaje de error |
| A10 | Seleccionar de lista | El usuario selecciona un estudiante existente |
| A11 | Confirmar eliminación | Se pide confirmación antes de eliminar |
| A12 | Eliminar de BD | Se ejecuta el DELETE en la base de datos |

---

## 3. Reglas de Negocio Aplicadas

1. **Código único**: No pueden existir dos estudiantes con el mismo código
2. **Nombre obligatorio**: El campo nombre no puede estar vacío
3. **Carrera válida**: El ID de carrera debe existir en la tabla Carreras
4. **Fecha de nacimiento**: Debe ser una fecha válida y no futura
5. **Integridad referencial**: Al eliminar, se elimina también sus inscripciones

---

## 4. Excepciones

| Código | Descripción | Acción |
|--------|-------------|--------|
| E01 | Código de estudiante ya existe | Mostrar mensaje de error |
| E02 | Carrera no existe | Mostrar mensaje y lista de carreras válidas |
| E03 | Fecha de nacimiento inválida | Resaltar campo y mostrar formato requerido |
| E04 | Error de conexión a BD | Mostrar mensaje de error y reintentar |
| E05 | Eliminación con inscripciones | Confirmar eliminación en cascada |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026