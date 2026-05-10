# Diagrama de Actividades - Registrar Notas
## CU-07: Registrar Notas

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
                    │   "Registrar Notas"    │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar Panel de     │
                    │  Registro de Notas    │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Seleccionar          │
                    │  Asignatura y Grupo   │
                    └───────────┬────────────┘
                                │
                                ▼
                    ┌────────────────────────┐
                    │  Mostrar lista de     │
                    │  estudiantes inscritos│
                    │  con sus notas        │
                    └───────────┬────────────┘
                                │
                                ▼
        ┌─────────────────────────────────────────────────────────┐
        │  PARA CADA ESTUDIANTE:                                  │
        ├─────────────────────────────────────────────────────────┤
        │                                                         │
        │  ┌────────────────┐    ┌────────────────┐             │
        │  │ Seleccionar   │    │ ¿Desea modificar│             │
        │  │ estudiante    │───▶│ las notas?     │             │
        │  └────────────────┘    └───────┬────────┘             │
        │                                 │                      │
        │                    ┌────────────┴───────────┐         │
        │                    │                        │         │
        │                   SÍ                       NO         │
        │                    │                        │         │
        │                    ▼                        │         │
        │  ┌─────────────────────────────────────────┐│         │
        │  │ Ingresar notas:                         ││         │
        │  │ - n1 (corte 1 - 35%)                   ││         │
        │  │ - n2 (corte 2 - 35%)                   ││         │
        │  │ - n3 (corte 3 - 30%)                   ││         │
        │  └────────────────┬────────────────────────┘│         │
        │                   │                           │         │
        │                   ▼                           │         │
        │  ┌─────────────────────────────────────────┐│         │
        │  │ Validar notas (entre 0.0 y 5.0)        ││         │
        │  └────────────────┬────────────────────────┘│         │
        │                   │                           │         │
        │                   ▼                           │         │
        │  ┌─────────────────────────────────────────┐│         │
        │  │ Actualizar en BD (UPDATE Inscribe)      ││         │
        │  │ - Se actualizan n1, n2, n3              ││         │
        │  │ - El TRIGGER calcula def automáticamente│          │
        │  └────────────────┬────────────────────────┘│         │
        │                   │                           │         │
        │                   ▼                           │         │
        │         ┌─────────────────────┐              │         │
        │         │ ¿Actualización     │              │         │
        │         │ exitosa?            │              │         │
        │         └──────────┬──────────┘              │         │
        │                    │                        │         │
        │           ┌────────┴────────┐               │         │
        │           │                │               │         │
        │          SÍ               NO                │         │
        │           │                │               │         │
        │           ▼                ▼               │         │
        │   ┌───────────┐   ┌───────────────┐         │         │
        │   │ Mostrar   │   │ Mostrar       │         │         │
        │   │ nota      │   │ mensaje de    │         │         │
        │   │ definit.  │   │ error         │         │         │
        │   │ calculada │   │               │         │         │
        │   └───────────┘   └───────────────┘         │         │
        │                                            │         │
        └────────────────────────────────────────────┘         │
                                │                                │
                                ▼                                │
                    ┌────────────────────────┐                  │
                    │  ¿Continuar con otro   │                  │
                    │      estudiante?       │                  │
                    └───────────┬────────────┘                  │
                                │                                │
                    ┌────────────┴───────────┐                   │
                    │                        │                   │
                   SÍ                       NO                   │
                    │                        │                   │
                    ▼                        ▼                   │
        ┌──────────────────┐    ┌──────────────────────────────┐ │
        │ Repetir para     │    │       FIN DEL PROCESO        │ │
        │ siguiente        │    └──────────────────────────────┘ │
        │ estudiante       │                                        │
        └──────────────────┘                                        │
```

---

## 2. Actividades del Flujo

| ID | Actividad | Descripción |
|----|-----------|-------------|
| A1 | Seleccionar opción | El usuario hace clic en "Registrar Notas" |
| A2 | Mostrar panel | Se carga la interfaz de registro de notas |
| A3 | Seleccionar asignatura/grupo | El usuario elige la asignatura y grupo |
| A4 | Cargar estudiantes | Se muestran los estudiantes inscritos |
| A5 | Seleccionar estudiante | El usuario elige un estudiante para calificar |
| A6 | Ingresar notas | El usuario ingresa n1, n2, n3 |
| A7 | Validar notas | Se verifica que estén entre 0.0 y 5.0 |
| A8 | Actualizar en BD | Se ejecuta UPDATE en la tabla Inscribe |
| A9 | Trigger calcula definitiva | El trigger `trg_calculo_notas` ejecuta: def = (n1*0.35)+(n2*0.35)+(n3*0.30) |
| A10 | Mostrar resultado | Se exhibe la nota definitiva calculada |
| A11 | Continuar o terminar | El usuario decide si continuar con otro estudiante |

---

## 3. Reglas de Negocio Aplicadas

1. **Rango de notas**: Las notas n1, n2, n3 deben estar entre 0.0 y 5.0
2. **Cálculo automático**: La nota definitiva se calcula mediante trigger en PostgreSQL
3. **Fórmula de cálculo**: `definitiva = (n1 × 0.35) + (n2 × 0.35) + (n3 × 0.30)`
4. **Precisión**: Las notas se almacenan con 2 decimales (NUMERIC(3,2))
5. **Permisos**: Solo el profesor asignado puede modificar las notas de su grupo

---

## 4. Flujo del Trigger

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                    TRIGGER: trg_calculo_notas                               │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  SE DISPARA: ANTES de INSERT o UPDATE de n1, n2, n3 en la tabla Inscribe  │
│                                                                             │
│  FUNCIÓN: fn_calcular_definitiva()                                         │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                                                                     │   │
│  │  NEW.def := (NEW.n1 * 0.35) + (NEW.n2 * 0.35) + (NEW.n3 * 0.30);   │   │
│  │                                                                     │   │
│  │  RETURN NEW;                                                        │   │
│  │                                                                     │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
│  EJEMPLO:                                                                   │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │  n1 = 4.0, n2 = 3.5, n3 = 4.2                                      │   │
│  │                                                                     │   │
│  │  def = (4.0 * 0.35) + (3.5 * 0.35) + (4.2 * 0.30)                  │   │
│  │  def = 1.40 + 1.225 + 1.26                                          │   │
│  │  def = 3.885                                                          │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 5. Excepciones

| Código | Descripción | Acción |
|--------|-------------|--------|
| E01 | Nota fuera de rango (0.0-5.0) | Mostrar mensaje de validación |
| E02 | Estudiante no inscrito | Mostrar mensaje indicando que no hay inscripción |
| E03 | Profesor no autorizado | Verificar que el usuario sea el profesor del grupo |
| E04 | Error de conexión a BD | Mostrar mensaje de error |
| E05 | Transacción fallida | Realizar rollback y mostrar error |

---

## 6. Permisos por Rol

| Rol | Acción Permitida |
|-----|------------------|
| Administrador | Puede modificar todas las notas |
| Profesor | Solo puede modificar notas de sus grupos ( tabla Imparte) |
| Estudiante | Solo puede ver sus propias notas |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026