
package datos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Objetos;
import modelo.Pares;

public class ParesDao extends AbstractDao implements IDao {

    private String mensaje;
    private final String select_mysql = "select * from pares";
    private final String insert_mysql = "insert into pares (jugador1, idj1, jugador2, idj2) values(?,?,?,?)";
    private final String get_mysql_id = "select * from pares where id=?";
    private final String delete_mysql = "delete from pares where id=?";
    
    
    public ParesDao(){}
    
    
    private void setMensajeError(String m){
        this.mensaje = m;
    }
    
    
    @Override
    public String getMensajeError() {
        return mensaje;
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

    @Override
    public List<Objetos> select() {
        List<Objetos> lista = new ArrayList<>();

        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(select_mysql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String j1 = rs.getString("jugador1");
                int idj1 = rs.getInt("idj1");
                String j2 = rs.getString("jugador2");
                int idj2 = rs.getInt("idj2");
                Pares p = new Pares(id, j1, idj1, j2, idj2);
                lista.add(p);
            }

        } catch (SQLException e) {
            setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return lista;
    }

    @Override
    public int crear(Objetos obj) {
        int v = 0;
        Pares p = (Pares) obj;
        
        try {
            cx = super.getConexion();
            ps =cx.prepareStatement(insert_mysql);
            ps.setString(1, p.getNombreJ1());
            ps.setInt(2, p.getIdJ1());
            ps.setString(3, p.getNombreJ2());
            ps.setInt(4, p.getIdJ2());
            v = ps.executeUpdate();
            
        } catch (SQLException e){
            setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    @Override
    public int editar(Objetos obj) {
       return 0;
    }

    @Override
    public int eliminar(Objetos obj) {
        int v = 0;
        Pares p = (Pares) obj;
        
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(delete_mysql);
            ps.setInt(1, p.getId());
            v = ps.executeUpdate();
            
        } catch (SQLException e){
            setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    @Override
    public Objetos getObjeto(int id) {
        Objetos p = null;

        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(get_mysql_id);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idp = rs.getInt("id");
                String j1 = rs.getString("jugador1");
                int idj1 = rs.getInt("idj1");
                String j2 = rs.getString("jugador2");
                int idj2 = rs.getInt("idj2");
                p = new Pares(idp, j1, idj1, j2, idj2);
            }

        } catch (SQLException e) {
            setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return p;
    }

        
}
