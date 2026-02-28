/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Tarea 2: N2L (Numbers to Letters)
 * 
 * Estudiante: Juan Felipe Rodríguez
 */

import java.util.Scanner;

public class JuanFelipeRodriguez {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- Conversor de Números a Letras (0-999) ---");
        System.out.println("1. Ingresar número manualmente");
        System.out.println("2. Ejecutar Demo");
        System.out.print("Seleccione una opción: ");
        
        int opcion = 0;
        if (sc.hasNextInt()) {
            opcion = sc.nextInt();
        }

        if (opcion == 1) {
            System.out.print("Ingrese un número entre 0 y 999: ");
            if (sc.hasNextInt()) {
                int numero = sc.nextInt();
                if (numero < 0 || numero >= 1000) {
                    System.out.println("Error: Número fuera de rango (0-999).");
                } else {
                    System.out.println("Resultado: " + convertir(numero));
                }
            } else {
                System.out.println("Error: Entrada no válida.");
            }
        } else if (opcion == 2) {
            ejecutarDemo();
        } else {
            System.out.println("Opción no válida.");
        }
        
        sc.close();
    }

    public static void ejecutarDemo() {
        int[] ejemplos = {0, 5, 10, 15, 20, 21, 22, 23, 26, 30, 45, 100, 101, 120, 156, 200, 500, 700, 900, 999};
        System.out.println("\n--- Ejecutando Demo ---");
        for (int n : ejemplos) {
            System.out.printf("%3d -> %s%n", n, convertir(n));
        }
    }

    public static String convertir(int numero) {

        String[] unidades = {
            "", "uno", "dos", "tres", "cuatro", 
            "cinco", "seis", "siete", "ocho", "nueve"
        };

        String[] especiales = {
            "diez", "once", "doce", "trece", "catorce", 
            "quince", "dieciséis", "diecisiete", 
            "dieciocho", "diecinueve"
        };

        String[] decenas = {
            "", "", "veinte", "treinta", "cuarenta", 
            "cincuenta", "sesenta", "setenta", 
            "ochenta", "noventa"
        };

        String[] centenas = {
            "", "ciento", "doscientos", "trescientos", 
            "cuatrocientos", "quinientos", 
            "seiscientos", "setecientos", 
            "ochocientos", "novecientos"
        };

        if (numero == 0) return "cero";
        if (numero == 100) return "cien";

        String resultado = "";

        int c = numero / 100;
        int d = (numero % 100) / 10;
        int u = numero % 10;

        if (c > 0) {
            resultado += centenas[c] + " ";
        }

        int resto = numero % 100;

        if (resto >= 10 && resto <= 19) {
            resultado += especiales[resto - 10];
        } else if (resto >= 20 && resto <= 29) {
            if (resto == 20) {
                resultado += "veinte";
            } else {
                // para las tíldes
                switch (resto) {
                    case 21: resultado += "veintiuno"; break;
                    case 22: resultado += "veintidós"; break;
                    case 23: resultado += "veintitrés"; break;
                    case 26: resultado += "veintiséis"; break;
                    default: resultado += "veinti" + unidades[u]; break;
                }
            }
        } else {
            if (d > 1) {
                resultado += decenas[d];
                if (u > 0) {
                    resultado += " y " + unidades[u];
                }
            } else if (u > 0) {
                resultado += unidades[u];
            }
        }

        return resultado.trim();
    }
}