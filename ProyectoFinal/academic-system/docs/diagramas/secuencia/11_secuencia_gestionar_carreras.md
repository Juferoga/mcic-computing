# Diagrama de Secuencia - Gestionar Carreras
## CU-01: Gestionar Carreras

---

## 1. Diagrama de Secuencia - Agregar Carrera

```
┌────────────┐     ┌──────────────┐     ┌─────────────┐     ┌────────────────┐     ┌────────────┐
│  Usuario   │     │ View/FXML    │     │ Controlador │     │ CarreraDAO    │     │  PostgreSQL│
└─────┬──────┘     └──────┬───────┘     └──────┬──────┘     └───────┬────────┘     └─────┬────┘
      │                    │                    │                    │                 │
      │ 1. Clic en         │                    │                    │                 │
      │    "Agregar        │                    │                    │                 │
      │    Carrera"        │                    │                    │                 │
      │───────────────────▶│                    │                    │                 │
      │                    │ 2. Mostrar         │                    │                 │
      │                    │    formulario     │                    │                 │
      │◀───────────────────│                    │                    │                 │
      │                    │                    │                    │                 │
      │ 3. Llenar datos    │                    │                    │                 │
      │    (ID, Nombre,    │                    │                    │                 │
      │    Registro)       │                    │                    │                 │
      │───────────────────▶│                    │                    │                 │
      │                    │ 4. Validar         │                    │                 │
      │                    │    datos           │                    │                 │
      │                    │───────────────────▶│                    │                 │
      │                    │                    │ 5. Crear           │                 │
      │                    │                    │    objeto          │                 │
      │                    │                    │    Carrera         │                 │
      │                    │                    │◀───────────────────│                 │
      │                    │                    │                    │                 │
      │                    │                    │ 6. guardar(carrera)│                │
      │                    │                    │───────────────────▶│                 │
      │                    │                    │                    │ 7. INSERT      │
      │                    │                    │                    │    SQL         │
      │                    │                    │                    │────────────────▶
      │                    │                    │                    │                 │
      │                    │                    │                    │ 8. Respuesta   │
      │                    │                    │                    │    (OK/Error)  │
      │                    │                    │                    │◀───────────────
      │                    │                    │ 9. Resultado       │                 │
      │                    │                    │◀───────────────────│                 │
      │                    │ 10. Mostrar        │                    │                 │
      │                    │     mensaje        │                    │                 │
      │◀───────────────────│                    │                    │                 │
      │                    │                    │                    │                 │
```

---

## 2. Descripción de Mensajes

| # | Mensaje | Descripción |
|---|---------|-------------|
| 1 | Clic en "Agregar Carrera" | El usuario solicita agregar una nueva carrera |
| 2 | Mostrar formulario | La vista presenta el formulario de ingreso |
| 3 | Llenar datos | El usuario ingresa ID, nombre y registro de calificación |
| 4 | Validar datos | El controlador verifica que los datos sean correctos |
| 5 | Crear objeto | Se instancia un objeto Carrera con los datos ingresados |
| 6 | guardar(carrera) | Se invoca el método del DAO para persistir |
| 7 | INSERT SQL | Se ejecuta la consulta en PostgreSQL |
| 8 | Respuesta | PostgreSQL retorna éxito o error |
| 9 | Resultado | El DAO retorna true/false al controlador |
| 10 | Mostrar mensaje | La vista informa al usuario el resultado |

---

## 3. Diagrama de Secuencia - Buscar Carrera

```
┌────────────┐     ┌──────────────┐     ┌─────────────┐     ┌────────────────┐     ┌────────────┐
│  Usuario   │     │ View/FXML    │     │ Controlador │     │ CarreraDAO    │     │  PostgreSQL│
└─────┬──────┘     └──────┬───────┘     └──────┬──────┘     └───────┬────────┘     └─────┬────┘
      │                    │                    │                    │                 │
      │ 1. Ingresar        │                    │                    │                 │
      │    ID de          │                    │                    │                 │
      │    carrera        │                    │                    │                 │
      │───────────────────▶│                    │                    │                 │
      │                    │ 2. buscar(ID)      │                    │                 │
      │                    │───────────────────▶│                    │                 │
      │                    │                    │ 3. SELECT SQL      │                 │
      │                    │                    │───────────────────▶│                 │
      │                    │                    │                    │ 4. Ejecutar    │
      │                    │                    │                    │    consulta    │
      │                    │                    │                    │────────────────▶
      │                    │                    │                    │                 │
      │                    │                    │                    │ 5. Resultado   │
      │                    │                    │                    │◀───────────────
      │                    │                    │ 6. Objeto          │                 │
      │                    │                    │    Carrera/null    │                 │
      │                    │                    │◀───────────────────│                 │
      │                    │ 7. Mostrar         │                    │                 │
      │                    │    datos           │                    │                 │
      │◀───────────────────│                    │                    │                 │
      │                    │                    │                    │                 │
```

---

## 4. Clases Participantes

| Clase | Rol |
|-------|-----|
| View/FXML | Interfaz gráfica (JavaFX) |
| ControladorCarrera | Coordina vista y modelo |
| CarreraDAO | Acceso a datos de PostgreSQL |
| Carrera | Modelo/Entidad del dominio |
| ConexionDB | Gestor de conexión (Singleton) |
| PostgreSQL | Motor de base de datos |

---

**Versión**: 1.0
**Fecha**: 9 de mayo de 2026