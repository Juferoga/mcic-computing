public class Division extends Operacion {
    public Division(Numero numero1, Numero numero2) {
        super(numero1, numero2);
    }

    @Override
    public Numero operar() {
        int divisor = numero2.aBase10();
        if (divisor == 0) {
            throw new ArithmeticException("División por cero.");
        }
        int resultado = numero1.aBase10() / divisor;
        return Numero.deBase10(resultado, numero1.getBase());
    }
}
