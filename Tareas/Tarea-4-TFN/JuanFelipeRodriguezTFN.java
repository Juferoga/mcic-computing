/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Tarea 4: Teorema Fundamental de la Numeración
 * 
 * Estudiante: Juan Felipe Rodríguez
 */

import java.util.Scanner;

public class JuanFelipeRodriguezTFN {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--- Conversor a Base Decimal (TFN) ---");
		System.out.println("1. Ingresar número y base manualmente");
		System.out.println("2. Ejecutar Demo");
		System.out.print("Seleccione una opción: ");

		int opcion = 0;
		if (sc.hasNextInt()) {
			opcion = sc.nextInt();
		}

		if (opcion == 1) {
			System.out.print("Ingrese el número (sin espacios): ");
			String numero = sc.next();
			System.out.print("Ingrese la base (2-9): ");
			int base = sc.nextInt();
			if (base < 2 || base > 9) {
				System.out.println("Error: Base fuera de rango (2-9).");
			} else if (!numero.matches("[0-" + (base-1) + "]+")) {
				System.out.println("Error: El número contiene dígitos inválidos para la base.");
			} else {
				System.out.println("Resultado: " + convertirADecimal(numero, base));
			}
		} else if (opcion == 2) {
			ejecutarDemo();
		} else {
			System.out.println("Opción no válida.");
		}
		sc.close();
	}

	public static void ejecutarDemo() {
		String[] numeros = {"11011100", "2026", "10101", "77", "1234"};
		int[] bases = {2, 10, 2, 8, 5};
		System.out.println("\n--- Ejecutando Demo ---");
		for (int i = 0; i < numeros.length; i++) {
			String n = numeros[i];
			int b = bases[i];
			System.out.printf("%s (base %d) -> base 10: %d\n", n, b, convertirADecimal(n, b));
		}
	}

	public static int convertirADecimal(String numero, int base) {
		int resultado = 0;
		int longitud = numero.length();
		for (int i = 0; i < longitud; i++) {
			int digito = Character.getNumericValue(numero.charAt(i));
			resultado += digito * Math.pow(base, longitud - 1 - i);
		}
		return resultado;
	}
}
