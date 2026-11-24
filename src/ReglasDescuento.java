import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ReglasDescuento {
    private static final BigDecimal UMBRAL = new BigDecimal("10.00");
    private static final BigDecimal FACTOR_CON_DESCUENTO = new BigDecimal("0.85");

    public static final ReglaDescuento QUINCE_POR_CIENTO = total ->
            total.compareTo(UMBRAL) > 0
                    ? total.multiply(FACTOR_CON_DESCUENTO).setScale(2, RoundingMode.HALF_UP)
                    : total;

    private ReglasDescuento() {
    }
}
