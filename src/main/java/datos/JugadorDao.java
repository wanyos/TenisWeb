
package datos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Jugador;
import modelo.Objetos;


public class JugadorDao extends AbstractDao implements IDao {

    private final String select_mysql = "select * from jugador";
    private final String insert_mysql = "insert into jugador(nombre, apellido, comentario) values(?,?,?)";
    private final String update_mysql = "update jugador set nombre=?, apellido=?, comentario=? where id=?";
    private final String delete_mysql = "delete from jugador where id=?";
    private final String get_mysql_id = "select * from jugador where id=?";
    private String mensaje_error;
    
    
    public JugadorDao(){}

    @Override
    public String getMensajeError() {
        return mensaje_error;
    }

    private void setMensajeError(String mensaje_error) {
        this.mensaje_error = mensaje_error;
    }
    
    
    
    @Override
    public List<Objetos> select() {
        List<Objetos> lista = new ArrayList<>();
        Objetos obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(select_mysql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String comentario = rs.getString("comentario");
                obj = new Jugador(id,nombre,apellido,comentario);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            setMensajeError(ex.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return lista;
    }

    
    @Override
    public int crear(Objetos obj) {
        int v = 0;
        Jugador j = (Jugador) obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(insert_mysql);
            ps.setString(1, j.getNombre());
            ps.setString(2, j.getApellido());
            ps.setString(3, j.getComentario());
            v = ps.executeUpdate();
        } catch (SQLException ex) {
             setMensajeError(ex.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public int editar(Objetos obj) {
        int v = 0;
        Jugador j = (Jugador) obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(update_mysql);
            ps.setString(1, j.getNombre());
            ps.setString(2, j.getApellido());
            ps.setString(3, j.getComentario());
            ps.setInt(4, j.getId());
            v = ps.executeUpdate();
        } catch (SQLException ex) {
             setMensajeError(ex.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public int eliminar(Objetos obj) {
        int v = 0;
        Jugador j = (Jugador) obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(delete_mysql);
            ps.setInt(1, j.getId());
            v = ps.executeUpdate();
        } catch (SQLException ex) {
             setMensajeError(ex.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public Objetos getObjeto(int id) {
        Jugador j = null;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(get_mysql_id);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                int id_j = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String comentario = rs.getString("comentario");
                j = new Jugador(id_j, nombre, apellido, comentario);
            }
            
        } catch (SQLException ex) {
             setMensajeError(ex.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return j;
    }

    
    @Override
    public List<Integer> getListaId() {
        List<Integer> lista = new ArrayList<>();
        List<Objetos> lista_obj = this.select();

        if (lista_obj != null && !lista_obj.isEmpty()) {
            for (Objetos aux : lista_obj) {
                lista.add(aux.getId());
            }
        }
        return lista;
    }

}
