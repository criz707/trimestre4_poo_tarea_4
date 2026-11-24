import java.math.BigDecimal;

@FunctionalInterface
public interface ReglaDescuento {
    /** Devuelve el total después de aplicar la regla. */
    BigDecimal aplicarDescuento(BigDecimal total);
}
