/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Taller 1: Números primos
 * 
 * Estudiante: Juan Felipe Rodríguez
 */
package Talleres.marzo6;

import java.util.Scanner;

public class JuanFelipeRodriguezFactorialDeNumero {
    public static void main(String[] args) {
        int numeroPrimo = 0;
        String repetir = "n";
        Scanner sc = new Scanner(System.in);
        System.out.println("--- ---------------------- ---");
        System.out.println("--- Factorial de un número ---");
        System.out.println("--- ---------------------- ---");

        do {
            System.out.print("\nIngrese un número para calcular su factorial: ");
            if (sc.hasNextInt()) {
                numeroPrimo = sc.nextInt();
                if (numeroPrimo < 0) {
                    System.out.println(numeroPrimo + " no es un número válido para calcular su factorial.");
                } else {
                    long factorial = 1;
                    for (int i = 1; i <= numeroPrimo; i++) {
                        factorial *= i;
                    }
                    System.out.println("El factorial de " + numeroPrimo + " es: " + factorial);
                }
            } else {
                System.out.println("Error: Debe ingresar un número entero.");
                sc.next();
            }
            System.out.print("\n¿Desea calcular el factorial de otro número? (s/n): ");
            repetir = sc.next();
        } while (repetir.equalsIgnoreCase("s"));

        sc.close();
    }
}