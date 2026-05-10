# Diagrama de Actividades - Gestionar Profesores
## CU-04: Gestionar Profesores

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
                    │ "Gestionar Profesores" │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar Panel de     │
                    │  Gestión de Profesores │
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
   │ Mostrar form  │    │ Ingresar ID  │    │ Seleccionar   │
   │ de nuevo      │    │ de profesor  │    │ profesor de   │
   │ profesor      │    │ a buscar     │    │ la lista       │
   └───────┬───────┘    └───────┬───────┘    └───────┬───────┘
           │                    │                    │
           ▼                    ▼                    ▼
   ┌───────────────┐    ┌───────────────┐    ┌───────────────┐
   │ Llenar datos: │    │ Buscar en    │    │ Confirmar     │
   │ - ID          │    │ base de datos│    │ eliminación   │
   │ - Nombre      │    └───────┬───────┘    └───────┬───────┘
   │ - Dirección   │            │                    │
   │ - Teléfono    │            ▼                    ▼
   │ - Profesión   │    ┌───────────────┐    ┌───────────────┐
   └───────┬───────┘    │ ¿Profesor    │    │ Eliminar de   │
           │            │ encontrado?   │    │ base de datos │
           ▼            └───────┬───────┘    └───────┬───────┘
   ┌───────────────┐            │                    │
   │ Validar datos │     ┌──────┴──────┐             │
   │ (ID único,    │     │             │             │
   │ nombre no     │     ▼             ▼             │
   │ vacío)       │   SÍ             NO              │
   └───────┬───────┘    │             │             │
           │            ▼             ▼             ▼
           ▼        ┌───────────┐ ┌───────────┐ ┌───────────┐
   ┌───────────────┐ │ Mostrar  │ │ Mostrar   │ │ Mostrar   │
   │ Guardar en   │ │ datos del│ │ mensaje   │ │ mensaje   │
   │ base de datos│ │ profesor │ │ "No       │ │ "Profesor │
   └───────┬───────┘ └───────────┘ │ encontrado"│ │eliminado" │
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
│ "Profesor │ │ "Error al │
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
| A1 | Seleccionar opción | El usuario hace clic en "Gestionar Profesores" |
| A2 | Mostrar panel | Se carga la interfaz con la lista de profesores |
| A3 | Seleccionar acción | El usuario elige entre agregar, consultar, modificar o eliminar |
| A4 | Mostrar formulario | Se presenta el formulario para ingreso de datos |
| A5 | Ingresar datos | El usuario llena: ID, nombre, dirección, teléfono, profesión |
| A6 | Validar datos | Se verifican: ID único, nombre no vacío, campos opcionales permitidos |
| A7 | Guardar en BD | Se persiste la información en PostgreSQL |
| A8 | Buscar en BD | Se consulta el profesor por ID |
| A9 | Mostrar resultado | Se exhiben los datos encontrados o mensaje de error |
| A10 | Seleccionar de lista | El usuario selecciona un profesor existente |
| A11 | Confirmar eliminación | Se pide confirmación antes de eliminar |
| A12 | Eliminar de BD | Se ejecuta el DELETE en la base de datos |

---

## 3. Reglas de Negocio Aplicadas

1. **ID único**: No pueden existir dos profesores con el mismo ID
2. **Nombre obligatorio**: El campo nombre no puede estar vacío
3. **Campos opcionales**: Dirección, teléfono y profesión son opcionales
4. **Integridad referencial**: Al eliminar, se eliminan también sus asignaciones en Imparte

---

## 4. Excepciones

| Código | Descripción | Acción |
|--------|-------------|--------|
| E01 | ID de profesor ya existe | Mostrar mensaje de error |
| E02 | Nombre de profesor vacío | Resaltar campo obligatorio |
| E03 | Error de conexión a BD | Mostrar mensaje de error |
| E04 | Profesor con carga académica | Advertir y confirmar eliminación en cascada |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026