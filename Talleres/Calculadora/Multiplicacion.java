public class Multiplicacion extends Operacion {
    public Multiplicacion(Numero numero1, Numero numero2) {
        super(numero1, numero2);
    }

    @Override
    public Numero operar() {
        int resultado = numero1.aBase10() * numero2.aBase10();
        return Numero.deBase10(resultado, numero1.getBase());
    }
}
