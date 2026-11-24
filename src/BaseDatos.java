import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BaseDatos {
    private final String url;

    public BaseDatos(String url) {
        this.url = url;
    }

    public void comprobarConexion() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(url)) {
            if (!conexion.isValid(2)) {
                throw new SQLException("La conexión no respondió");
            }
        }
    }
}
