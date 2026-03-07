/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Taller 1: Números primos
 * 
 * Estudiante: Juan Felipe Rodríguez
 */
package Talleres.marzo6;

import java.util.Scanner;

public class JuanFelipeRodriguezNumerosPrimos {
    public static void main(String[] args) {
        int numeroPrimo = 0;
        String repetir = "n";
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- -------------------- ---");
        System.out.println("--- Números primos ---");
        System.out.println("--- -------------------- ---");

        do {
            System.out.print("\nIngrese un número para saber si es primo: ");
            if (sc.hasNextInt()) {
                numeroPrimo = sc.nextInt();
                if (numeroPrimo < 2) {
                    System.out.println(numeroPrimo + " no es un número primo.");
                } else {
                    boolean esPrimo = true;
                    for (int i = 2; i <= Math.sqrt(numeroPrimo); i++) {
                        if (numeroPrimo % i == 0) {
                            System.out.println("El " + numeroPrimo + " ES divisible por " + i + "\n");
                            esPrimo = false;
                            break;
                        }
                        System.out.println("El " + numeroPrimo + " NO ES divisible por " + i + "\n");
                    }
                    if (esPrimo) {
                        System.out.println(numeroPrimo + " es un número primo.");
                    } else {
                        System.out.println(numeroPrimo + " no es un número primo.");
                    }
                }
            } else {
                System.out.println("Error: Debe ingresar un número entero.");
                sc.next();
            }
            System.out.print("\n¿Desea verificar otro número? (s/n): ");
            repetir = sc.next();
        } while (repetir.equalsIgnoreCase("s"));

        sc.close();
    }
}
