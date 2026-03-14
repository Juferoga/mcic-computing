/**
 * Universidad Distrital Francisco José de Caldas
 * Maestría en Ciencias de la Información y las Comunicaciones
 * Informática - Taller 1: Menu operaciones básicas
 * 
 * Estudiante: Juan Felipe Rodríguez
 */
package Talleres.marzo13;

import java.util.Scanner;

public class JuanFelipeRodriguezOperaciones {
    public static void main(String[] args) {
        // opciones de selección
        int opcion = 0;
        double num1 = 0, num2 = 0, resultado = 0;
        String repetir = "n";
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- -------------------- ---");
        System.out.println("--- Operaciones Básicas ---");
        System.out.println("--- -------------------- ---");
        do {
            System.out.println("\nSeleccione una operación:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Resolver ecuación cuadrática");
            System.out.println("6. Convertir número a letras");
            System.out.println("7. Convertir de base 10 a otra base");
            System.out.println("8. Convertir de otra base a decimal");
            System.out.println("9. Calculadora de bases");
            System.out.print("Ingrese el número de la operación que desea realizar: ");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el primer número: ");
                        if (sc.hasNextDouble()) {
                            num1 = sc.nextDouble();
                            System.out.print("Ingrese el segundo número: ");
                            if (sc.hasNextDouble()) {
                                num2 = sc.nextDouble();
                                resultado = sumar(num1, num2);
                                System.out.printf("Resultado: %.2f + %.2f = %.2f%n", num1, num2, resultado);
                            } else {
                                System.out.println("Error: Debe ingresar un número válido para el segundo número.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido para el primer número.");
                            sc.next();
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese el primer número: ");
                        if (sc.hasNextDouble()) {
                            num1 = sc.nextDouble();
                            System.out.print("Ingrese el segundo número: ");
                            if (sc.hasNextDouble()) {
                                num2 = sc.nextDouble();
                                resultado = restar(num1, num2);
                                System.out.printf("Resultado: %.2f - %.2f = %.2f%n", num1, num2, resultado);
                            } else {
                                System.out.println("Error: Debe ingresar un número válido para el segundo número.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido para el primer número.");
                            sc.next();
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el primer número: ");
                        if (sc.hasNextDouble()) {
                            num1 = sc.nextDouble();
                            System.out.print("Ingrese el segundo número: ");
                            if (sc.hasNextDouble()) {
                                num2 = sc.nextDouble();
                                resultado = multiplicar(num1, num2);
                                System.out.printf("Resultado: %.2f x %.2f = %.2f%n", num1, num2, resultado);
                            } else {
                                System.out.println("Error: Debe ingresar un número válido para el segundo número.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido para el primer número.");
                            sc.next();
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese el primer número: ");
                        if (sc.hasNextDouble()) {
                            num1 = sc.nextDouble();
                            System.out.print("Ingrese el segundo número: ");
                            if (sc.hasNextDouble()) {
                                num2 = sc.nextDouble();
                                if (num2 != 0) {
                                    resultado = dividir(num1, num2);
                                    System.out.printf("Resultado: %.2f ÷ %.2f = %.2f%n", num1, num2, resultado);
                                } else {
                                    System.out.println("Error: No se puede dividir por cero.");
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número válido para el segundo número.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido para el primer número.");
                            sc.next();
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el coeficiente a: ");
                        if (sc.hasNextDouble()) {
                            double a = sc.nextDouble();
                            System.out.print("Ingrese el coeficiente b: ");
                            if (sc.hasNextDouble()) {
                                double b = sc.nextDouble();
                                System.out.print("Ingrese el coeficiente c: ");
                                if (sc.hasNextDouble()) {
                                    double c = sc.nextDouble();
                                    solveQuadratic(a, b, c);
                                } else {
                                    System.out.println("Error: Debe ingresar un número válido para c.");
                                    sc.next();
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número válido para b.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido para a.");
                            sc.next();
                        }
                        break;
                    case 6:
                        System.out.print("Ingrese un número entre 0 y 999: ");
                        if (sc.hasNextInt()) {
                            int numero = sc.nextInt();
                            if (numero >= 0 && numero < 1000) {
                                System.out.println("Resultado: " + convertirNumeroALetras(numero));
                            } else {
                                System.out.println("Error: Número fuera de rango (0-999).");
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número entero.");
                            sc.next();
                        }
                        break;
                    case 7:
                        System.out.print("Ingrese un número en base 10: ");
                        if (sc.hasNextInt()) {
                            int numero = sc.nextInt();
                            System.out.print("Ingrese la base destino (2-10): ");
                            if (sc.hasNextInt()) {
                                int base = sc.nextInt();
                                if (base >= 2 && base <= 10 && numero >= 0) {
                                    System.out.println("Resultado: " + convertirBase10ABase(numero, base));
                                } else {
                                    System.out.println("Error: Base fuera de rango (2-10) o número negativo.");
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número entero para la base.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número entero.");
                            sc.next();
                        }
                        break;
                    case 8:
                        System.out.print("Ingrese el número (sin espacios): ");
                        String numeroStr = sc.next();
                        System.out.print("Ingrese la base (2-10): ");
                        if (sc.hasNextInt()) {
                            int base = sc.nextInt();
                            if (base >= 2 && base <= 10 && numeroStr.matches("[0-" + (base-1) + "]+")) {
                                System.out.println("Resultado: " + convertirABase10(numeroStr, base));
                            } else {
                                System.out.println("Error: Base fuera de rango o número contiene dígitos inválidos.");
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número entero para la base.");
                            sc.next();
                        }
                        break;
                    case 9:
                        System.out.print("Ingrese la base del primer número (2-10): ");
                        if (sc.hasNextInt()) {
                            int base1 = sc.nextInt();
                            if (base1 < 2 || base1 > 10) {
                                System.out.println("Error: Base del primer número fuera de rango (2-10).");
                                break;
                            }
                            System.out.print("Ingrese el primer número en base " + base1 + ": ");
                            String num1Str = sc.next();
                            if (!num1Str.matches("[0-" + (base1-1) + "]+")) {
                                System.out.println("Error: El número contiene dígitos inválidos para la base " + base1 + ".");
                                break;
                            }
                            System.out.print("Ingrese la base del segundo número (2-10): ");
                            if (sc.hasNextInt()) {
                                int base2 = sc.nextInt();
                                if (base2 < 2 || base2 > 10) {
                                    System.out.println("Error: Base del segundo número fuera de rango (2-10).");
                                    break;
                                }
                                System.out.print("Ingrese el segundo número en base " + base2 + ": ");
                                String num2Str = sc.next();
                                if (!num2Str.matches("[0-" + (base2-1) + "]+")) {
                                    System.out.println("Error: El número contiene dígitos inválidos para la base " + base2 + ".");
                                    break;
                                }
                                System.out.print("Ingrese la operación (+, -, *, /): ");
                                String operacion = sc.next();
                                if (!operacion.matches("[+\\-*/]")) {
                                    System.out.println("Error: Operación no válida.");
                                    break;
                                }
                                int num1Dec = convertirABase10(num1Str, base1);
                                int num2Dec = convertirABase10(num2Str, base2);
                                int resultadoInt = 0;
                                switch (operacion) {
                                    case "+":
                                        resultadoInt = num1Dec + num2Dec;
                                        break;
                                    case "-":
                                        resultadoInt = num1Dec - num2Dec;
                                        break;
                                    case "*":
                                        resultadoInt = num1Dec * num2Dec;
                                        break;
                                    case "/":
                                        if (num2Dec != 0) {
                                            resultadoInt = num1Dec / num2Dec;
                                        } else {
                                            System.out.println("Error: División por cero.");
                                            break;
                                        }
                                        break;
                                }
                                System.out.print("Ingrese la base de salida (2-10): ");
                                if (sc.hasNextInt()) {
                                    int baseSalida = sc.nextInt();
                                    if (baseSalida >= 2 && baseSalida <= 10) {
                                        String resultadoBase = convertirBase10ABase(resultadoInt, baseSalida);
                                        System.out.println("Resultado: " + resultadoBase + " (base " + baseSalida + ")");
                                    } else {
                                        System.out.println("Error: Base de salida fuera de rango (2-10).");
                                    }
                                } else {
                                    System.out.println("Error: Debe ingresar un número entero para la base de salida.");
                                    sc.next();
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número entero para la base del segundo número.");
                                sc.next();
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número entero para la base del primer número.");
                            sc.next();
                        }
                        break;
                    default:
                        System.out.println("Error: Opción no válida. Por favor seleccione una opción entre 1 y 9.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número entero para seleccionar la operación.");
                sc.next();
            }

            System.out.print("\n¿Desea realizar otra operación? (s/n): ");
            repetir = sc.next();
        } while (repetir.equalsIgnoreCase("s"));
        sc.close();
    }

    public static double sumar(double a, double b) {
        return a + b;
    }

    public static double restar(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new IllegalArgumentException("No se puede dividir por cero.");
        }
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

    public static String convertirNumeroALetras(int numero) {
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

    public static String convertirBase10ABase(int numero, int base) {
        if (numero == 0) return "0";
        StringBuilder resultado = new StringBuilder();
        int n = numero;
        while (n > 0) {
            resultado.insert(0, n % base);
            n /= base;
        }
        return resultado.toString();
    }

    public static int convertirABase10(String numero, int base) {
        int resultado = 0;
        int longitud = numero.length();
        for (int i = 0; i < longitud; i++) {
            int digito = Character.getNumericValue(numero.charAt(i));
            resultado += digito * Math.pow(base, longitud - 1 - i);
        }
        return resultado;
    }
}
