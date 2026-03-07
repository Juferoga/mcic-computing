/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Tarea 3: Conversión de Base
 * 
 * Estudiante: Juan Felipe Rodríguez
 */

import java.util.Scanner;

public class JuanFelipeRodriguezBase {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Conversor de Base 10 a Base 2-9 ---");
        System.out.println("1. Ingresar número y base manualmente");
        System.out.println("2. Ejecutar Demo");
        System.out.print("Seleccione una opción: ");

        int opcion = 0;
        if (sc.hasNextInt()) {
            opcion = sc.nextInt();
        }

        if (opcion == 1) {
            System.out.print("Ingrese un número en base 10: ");
            int numero = sc.nextInt();
            System.out.print("Ingrese la base destino (2-9): ");
            int base = sc.nextInt();
            if (base < 2 || base > 9) {
                System.out.println("Error: Base fuera de rango (2-9).");
            } else if (numero < 0) {
                System.out.println("Error: Número negativo no soportado.");
            } else {
                System.out.println("Resultado: " + convertirBase(numero, base));
            }
        } else if (opcion == 2) {
            ejecutarDemo();
        } else {
            System.out.println("Opción no válida.");
        }
        sc.close();
    }

    public static void ejecutarDemo() {
        int[] numeros = {220, 15, 100, 255, 7, 42};
        int[] bases = {2, 3, 4, 5, 8, 9};
        System.out.println("\n--- Ejecutando Demo ---");
        for (int i = 0; i < numeros.length; i++) {
            int n = numeros[i];
            int b = bases[i];
            System.out.printf("%d (base 10) -> base %d: %s\n", n, b, convertirBase(n, b));
        }
    }

    public static String convertirBase(int numero, int base) {
        if (numero == 0) return "0";
        StringBuilder resultado = new StringBuilder();
        int n = numero;
        while (n > 0) {
            resultado.insert(0, n % base);
            n /= base;
        }
        return resultado.toString();
    }
}
