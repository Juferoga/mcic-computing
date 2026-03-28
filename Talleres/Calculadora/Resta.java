public class Resta extends Operacion {
    public Resta(Numero numero1, Numero numero2) {
        super(numero1, numero2);
    }

    @Override
    public Numero operar() {
        int resultado = numero1.aBase10() - numero2.aBase10();
        if (resultado < 0) {
            throw new ArithmeticException("Resultado negativo no representable con Numero.");
        }
        return Numero.deBase10(resultado, numero1.getBase());
    }
}
