public class Numero {
    private int valor;
    private int base;

    public Numero(int valor, int base) {
        if (base < 2 || base > 10) {
            throw new IllegalArgumentException("La base debe estar entre 2 y 10.");
        }
        if (!esValidoEnBase(valor, base)) {
            throw new IllegalArgumentException("El valor no es válido para la base especificada.");
        }
        this.valor = valor;
        this.base = base;
    }

    public Numero(int valor) {
        if (!esValidoEnBase(valor, 10)) {
            throw new IllegalArgumentException("El valor no es válido para la base 10.");
        }
        this.valor = valor;
        this.base = 10;
    }

    public Numero() {
        this.valor = 0;
        this.base = 10;
    }

    public int getValor() {
        return valor;
    }

    public int getBase() {
        return base;
    }

    public int aBase10() {
        int resultado = 0;
        int multiplicador = 1;
        int temp = valor;
        if (temp == 0) return 0;
        while (temp > 0) {
            int digito = temp % 10;
            resultado += digito * multiplicador;
            multiplicador *= base;
            temp /= 10;
        }
        return resultado;
    }

    public static Numero deBase10(int valorBase10, int base) {
        if (base < 2 || base > 10) {
            throw new IllegalArgumentException("La base debe estar entre 2 y 10.");
        }
        if (valorBase10 == 0) {
            return new Numero(0, base);
        }
        int valorEnBase = 0;
        int multiplicador = 1;
        int temp = Math.abs(valorBase10);
        while (temp > 0) {
            int digito = temp % base;
            valorEnBase += digito * multiplicador;
            multiplicador *= 10;
            temp /= base;
        }
        return new Numero(valorEnBase, base);
    }

    private boolean esValidoEnBase(int valor, int base) {
        if (valor < 0) return false;
        int temp = valor;
        if (temp == 0) return true;
        while (temp > 0) {
            int digito = temp % 10;
            if (digito >= base) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d (base %d)", valor, base);
    }
}
