public abstract class Operacion {
    protected Numero numero1;
    protected Numero numero2;

    public Operacion(Numero numero1, Numero numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public abstract Numero operar();
}
