public class Suma extends Operacion {
    public Suma(Numero numero1, Numero numero2) {
        super(numero1, numero2);
    }

    @Override
    public Numero operar() {
        int resultado = numero1.aBase10() + numero2.aBase10();
        // El resultado se da en base del primer número por defecto
        return Numero.deBase10(resultado, numero1.getBase());
    }
}
