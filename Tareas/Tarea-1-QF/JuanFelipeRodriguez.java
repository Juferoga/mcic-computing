/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Tarea 1: Quadratic Function
 * 
 * Estudiante: Juan Felipe Rodríguez
 */

import java.util.Scanner;

public class JuanFelipeRodriguez {

    public static double evaluateQuadratic(double a, double b, double c, double x) {
        return a * Math.pow(x, 2) + b * x + c;
    }

    public static void solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            System.out.println("No es una ecuación cuadrática (a=0).");
            return;
        }

        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.printf("Las raíces son reales y diferentes: x1 = %.4f, x2 = %.4f%n", x1, x2);
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.printf("La raíz es real y única: x = %.4f%n", x);
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            System.out.printf("Las raíces son complejas: x1 = %.4f + %.4fi, x2 = %.4f - %.4fi%n", realPart, imaginaryPart, realPart, imaginaryPart);
        }
    }

    public static void runDemo() {
        System.out.println("\n--- Ejecutando Demo ---");
        
        double a1 = 1, b1 = -3, c1 = 2;
        double xValue = 5;
        System.out.println("Caso 1: f(x) = x^2 - 3x + 2");
        System.out.printf("Evaluando f(%.2f) = %.2f%n", xValue, evaluateQuadratic(a1, b1, c1, xValue));
        solveQuadratic(a1, b1, c1);
        
        double a2 = 1, b2 = -2, c2 = 1;
        System.out.println("\nCaso 2: f(x) = x^2 - 2x + 1");
        solveQuadratic(a2, b2, c2);
        
        double a3 = 1, b3 = 1, c3 = 1;
        System.out.println("\nCaso 3: f(x) = x^2 + x + 1");
        solveQuadratic(a3, b3, c3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ingresar datos manualmente");
        System.out.println("2. Correr demo con datos pre-creados");
        System.out.print("Opción: ");
        
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        if (choice == 1) {
            System.out.print("Ingrese el valor de a: ");
            double a = scanner.nextDouble();
            System.out.print("Ingrese el valor de b: ");
            double b = scanner.nextDouble();
            System.out.print("Ingrese el valor de c: ");
            double c = scanner.nextDouble();
            System.out.print("Ingrese el valor de x para evaluar f(x): ");
            double x = scanner.nextDouble();

            System.out.println("\n--- Resultados ---");
            System.out.printf("f(%.2f) = %.4f%n", x, evaluateQuadratic(a, b, c, x));
            System.out.printf("Resolviendo la ecuación: %.2fx^2 + %.2fx + %.2f = 0%n", a, b, c);
            solveQuadratic(a, b, c);
        } else if (choice == 2) {
            runDemo();
        } else {
            System.out.println("Opción no válida.");
        }
        
        scanner.close();
    }
}
