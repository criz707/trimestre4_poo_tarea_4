import java.math.BigDecimal;

public final class DescuentoPrueba {
    public static void main(String[] args) {
        CalculadoraDescuento calculadora =
                new CalculadoraDescuento(ReglasDescuento.QUINCE_POR_CIENTO);

        verificar(calculadora, "9.99", "9.99");
        verificar(calculadora, "10.00", "10.00");
        verificar(calculadora, "10.01", "8.51");
        verificar(calculadora, "20.00", "17.00");
        verificar(new CalculadoraDescuento(total -> total), "20.00", "20.00");

        System.out.println("5 comprobaciones correctas");
    }

    private static void verificar(CalculadoraDescuento calculadora, String entrada, String esperado) {
        BigDecimal resultado = calculadora.calcularTotalFinal(new BigDecimal(entrada));
        if (resultado.compareTo(new BigDecimal(esperado)) != 0) {
            throw new AssertionError(entrada + ": se esperaba " + esperado + ", se obtuvo " + resultado);
        }
    }
}
