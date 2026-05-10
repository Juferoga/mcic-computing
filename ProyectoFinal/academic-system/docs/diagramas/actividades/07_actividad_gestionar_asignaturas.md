# Diagrama de Actividades - Gestionar Asignaturas
## CU-03: Gestionar Asignaturas

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
                    │"Gestionar Asignaturas"│
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar Panel de     │
                    │  Gestión de Asignaturas│
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
   │ de nueva      │    │ código de    │    │ asignatura    │
   │ asignatura    │    │ asignatura    │    │ de la lista   │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Llenar datos: │    │ Buscar en    │    │ Confirmar     │
   │ - Código      │    │ base de datos│    │ eliminación   │
   │ - Nombre      │    └───────┬───────┘    └───────┬───────┘
   │ - Intensidad  │            │                    │
   │ - Créditos    │            ▼                    ▼
   └───────┬───────┘    ┌───────────────┐    ┌───────────────┐
           │            │ ¿Asignatura  │    │ Eliminar de   │
           ▼            │ encontrada?   │    │ base de datos │
   ┌───────────────┐    └───────┬───────┘    └───────┬───────┘
   │ Validar datos │            │                    │
   │ (código único,│     ┌──────┴──────┐             │
   │ nombre no     │     │             │             │
   │ vacío)       │     ▼             ▼             │
   └───────┬───────┘  SÍ             NO              │
           │            │             │             │
           ▼            ▼             ▼             ▼
   ┌───────────────┐ ┌───────────┐ ┌───────────┐ ┌───────────┐
   │ Guardar en   │ │ Mostrar  │ │ Mostrar   │ │ Mostrar   │
   │ base de datos│ │ datos de │ │ mensaje   │ │ mensaje   │
   └───────┬───────┘ │ asignat. │ │ "No       │ │ "Asignat. │
           │        └───────────┘ │ encontrada"│ │ eliminada"│
           │                     └───────────┘ └───────────┘
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
│ "Asignatura│ │ "Error al│
│ creada"   │ │ guardar"  │
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
| A1 | Seleccionar opción | El usuario hace clic en "Gestionar Asignaturas" |
| A2 | Mostrar panel | Se carga la interfaz con la lista de asignaturas |
| A3 | Seleccionar acción | El usuario elige entre agregar, consultar, modificar o eliminar |
| A4 | Mostrar formulario | Se presenta el formulario para ingreso de datos |
| A5 | Ingresar datos | El usuario llena: código, nombre, intensidad horaria, créditos |
| A6 | Validar datos | Se verifican: código único, nombre no vacío, valores numéricos válidos |
| A7 | Guardar en BD | Se persiste la información en PostgreSQL |
| A8 | Buscar en BD | Se consulta la asignatura por código |
| A9 | Mostrar resultado | Se exhiben los datos encontrados o mensaje de error |
| A10 | Seleccionar de lista | El usuario selecciona una asignatura existente |
| A11 | Confirmar eliminación | Se pide confirmación antes de eliminar |
| A12 | Eliminar de BD | Se ejecuta el DELETE en la base de datos |

---

## 3. Reglas de Negocio Aplicadas

1. **Código único**: No pueden existir dos asignaturas con el mismo código
2. **Nombre obligatorio**: El campo nombre no puede estar vacío
3. **Intensidad horaria**: Debe ser un número entero positivo (0-20)
4. **Créditos**: Debe ser un número entero positivo (1-10)
5. **Integridad referencial**: No se puede eliminar si tiene prerrequisitos o está en el pensum

---

## 4. Excepciones

| Código | Descripción | Acción |
|--------|-------------|--------|
| E01 | Código de asignatura ya existe | Mostrar mensaje de error |
| E02 | Nombre de asignatura vacío | Resaltar campo obligatorio |
| E03 | Intensidad horaria inválida | Mostrar rango válido (0-20) |
| E04 | Créditos inválidos | Mostrar rango válido (1-10) |
| E05 | Asignatura con prerrequisitos | Advertir sobre relaciones existentes |
| E06 | Error de conexión a BD | Mostrar mensaje de error |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026