import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public final class VistaDescuento extends JFrame {
    private final CalculadoraDescuento calculadora;
    private final JTextField monto = new JTextField(12);
    private final JTextArea mensajes = new JTextArea(7, 46);

    public VistaDescuento(CalculadoraDescuento calculadora) {
        super("Calculadora de descuentos");
        this.calculadora = calculadora;

        JButton calcular = new JButton("Calcular Descuento");
        calcular.addActionListener(evento -> calcularDescuento());

        JLabel etiqueta = new JLabel("Monto total ($)");
        etiqueta.setLabelFor(monto);
        JPanel entrada = new JPanel(new FlowLayout(FlowLayout.LEADING));
        entrada.add(etiqueta);
        entrada.add(monto);
        entrada.add(calcular);

        mensajes.setEditable(false);
        mensajes.setLineWrap(true);
        mensajes.setWrapStyleWord(true);
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panel.add(entrada, BorderLayout.NORTH);
        panel.add(new JScrollPane(mensajes), BorderLayout.CENTER);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(calcular);
        pack();
        setLocationRelativeTo(null);
    }

    private void calcularDescuento() {
        String texto = monto.getText().strip();
        if (!texto.matches("[0-9]+([.,][0-9]{1,2})?")) {
            mostrarMensaje("Ingresa un monto no negativo con hasta dos decimales, por ejemplo 12,50.");
            return;
        }

        BigDecimal total = new BigDecimal(texto.replace(',', '.'));
        BigDecimal totalFinal = calculadora.calcularTotalFinal(total);
        mostrarMensaje("Monto final: $" + totalFinal.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    public void comprobarConexion(BaseDatos baseDatos) {
        mostrarMensaje("Comprobando conexión con SQLite…");
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                try {
                    baseDatos.comprobarConexion();
                    return "Conexión con SQLite correcta.";
                } catch (SQLException error) {
                    return "Error al conectar con SQLite: " + error.getMessage();
                }
            }

            @Override
            protected void done() {
                try {
                    mostrarMensaje(get());
                } catch (InterruptedException error) {
                    Thread.currentThread().interrupt();
                    mostrarMensaje("Se interrumpió la comprobación de SQLite.");
                } catch (ExecutionException error) {
                    mostrarMensaje("Error al iniciar SQLite: " + error.getCause());
                }
            }
        }.execute();
    }

    private void mostrarMensaje(String mensaje) {
        mensajes.append(mensaje + "\n");
        mensajes.setCaretPosition(mensajes.getDocument().getLength());
    }
}
