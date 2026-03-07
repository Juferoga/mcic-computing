/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Taller 1: Tabla de multiplicar por número
 * 
 * Estudiante: Juan Felipe Rodríguez
 */
package Talleres.marzo6;

import java.util.Scanner;

public class JuanFelipeRodriguezTablaMultiplicar {
    public static void main(String[] args) {
        int numeroTabla = 0;
        String repetir = "n";
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- -------------------- ---");
        System.out.println("--- Tabla de Multiplicar ---");
        System.out.println("--- -------------------- ---");

        do {
            System.out.print("\nIngrese un número para mostrar su tabla de multiplicar: ");
            if (sc.hasNextInt()) {
                numeroTabla = sc.nextInt();
                System.out.println("Tabla de multiplicar del " + numeroTabla + ":\n");
                if (numeroTabla < 0) {
                    System.out.println("Error: El número debe ser positivo.");
                } else {
                    for (int i = 1; i <= 10; i++) {
                        System.out.printf("%d x %d = %d%n", numeroTabla, i, numeroTabla * i);
                    }
                }
            } else {
                System.out.println("Error: Debe ingresar un número entero.");
                sc.next();
            }

            System.out.print("\n¿Desea ver la tabla de otro número? (s/n): ");
            repetir = sc.next();
        } while (repetir.equalsIgnoreCase("s"));

        sc.close();
    }
}
