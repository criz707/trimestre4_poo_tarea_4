import java.math.BigDecimal;
import java.util.Objects;

public final class CalculadoraDescuento {
    private final ReglaDescuento regla;

    public CalculadoraDescuento(ReglaDescuento regla) {
        this.regla = Objects.requireNonNull(regla, "La regla es obligatoria");
    }

    public BigDecimal calcularTotalFinal(BigDecimal total) {
        Objects.requireNonNull(total, "El total es obligatorio");
        if (total.signum() < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
        return regla.aplicarDescuento(total);
    }
}
