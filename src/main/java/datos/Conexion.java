
package datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {

    //private final String URL = "jdbc:mysql://localhost:3306/modelo_tenis?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/modelo_tenis?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String USER = "root";
    private String PASSWORD = "1712@fll";
    private Connection cx;

    public Conexion() {

    }

    public Connection getConexion() {
        try {
            if (cx == null || cx.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cx = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

}
