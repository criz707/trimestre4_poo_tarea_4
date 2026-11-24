import javax.swing.SwingUtilities;

public final class Aplicacion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraDescuento calculadora =
                    new CalculadoraDescuento(ReglasDescuento.QUINCE_POR_CIENTO);
            VistaDescuento vista = new VistaDescuento(calculadora);
            vista.setVisible(true);
            vista.comprobarConexion(new BaseDatos("jdbc:sqlite:descuentos.db"));
        });
    }
}
