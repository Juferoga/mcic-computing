# Calculadora en Java (Taller)

## Objetivo
Desarrollar una calculadora modular en Java aplicando los principios SOLID. El sistema debe permitir operar números en cualquier base menor o igual a 10, con validaciones adecuadas.

## Estructura de Clases

- **Numero**: Representa un número en una base específica.
  - Atributos: `valor`, `base`
  - Métodos: `aBase10()`, `deBase10()` (conversiones entre bases, validando restricciones)

- **Operacion (abstracta)**: Clase base para operaciones aritméticas.
  - Atributos: dos instancias de `Numero`
  - Método abstracto: `operar()`

- **Suma, Resta, Multiplicacion, Division**: Heredan de `Operacion` e implementan `operar()`.

## Organización de archivos
- Cada clase en su propio archivo dentro de la carpeta `Calculadora`.
- Incluir pruebas o ejemplos de uso.

```mermaid
classDiagram
    class Numero {
        - int valor
        - int base
        + Numero(int valor, int base)
        + Numero(int valor)
        + Numero()
        + int aBase10()
        + static Numero deBase10(int valorBase10, int base)
        + String toString()
    }

    class Operacion {
        <<abstract>>
        - Numero numero1
        - Numero numero2
        + Operacion(Numero numero1, Numero numero2)
        + abstract Numero operar()
    }

    class Suma {
        + Numero operar()
    }
    class Resta {
        + Numero operar()
    }
    class Multiplicacion {
        + Numero operar()
    }
    class Division {
        + Numero operar()
    }

    class CalculadoraGUI {
        - JTextField pantalla
        - JComboBox<Integer> comboBaseEntrada
        - JComboBox<Integer> comboBaseSalida
        - JButton[] botonesNumeros
        + CalculadoraGUI()
        + void actionPerformed(ActionEvent e)
        + static void main(String[] args)
    }

    Numero *.. Operacion : "usado por"
    Operacion <|-- Suma
    Operacion <|-- Resta
    Operacion <|-- Multiplicacion
    Operacion <|-- Division
    CalculadoraGUI ..> Operacion : "invoca"
    CalculadoraGUI ..> Numero : "crea/usa"
```