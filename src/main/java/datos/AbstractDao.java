
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AbstractDao {
    
    protected Connection cx;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    protected Connection getConexion(){
        Conexion c = new Conexion();
        cx = c.getConexion();
        return cx;
    }
    
    protected void cerrarObjetos() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cx != null) {
                cx.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract List<Integer> getListaId();
    
}
