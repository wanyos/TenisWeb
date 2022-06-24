
package datos;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Bono;
import modelo.Objetos;

public class BonoDao extends AbstractDao implements IDao {
    
    private final String select_mysql = "select * from bono";
    private final String insert_mysql = "insert into bono(id, fecha, nombre, id_jugador, horas, estado) values(?,?,?,?,?,?)";
    private final String update_mysql = "update bono set fecha=?, nombre=?, id_jugador=?, horas=?, estado=? where id=?";
    private final String delete_mysql = "delete from bono where id=?";
    private final String get_mysql_id = "select * from bono where id=?";
    
    public BonoDao(){}

    
    @Override
    public List<Objetos> select() {
        List<Objetos> lista = new ArrayList<>();

        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(select_mysql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String nombre = rs.getString("nombre");
                int id_jugador = rs.getInt("id_jugador");
                int horas = rs.getInt("horas");
                boolean estado = rs.getBoolean("estado");
                Bono b = new Bono(id, fecha, nombre, id_jugador, horas, estado);
                lista.add(b);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return lista;
    }

    
    @Override
    public int crear(Objetos obj) {
        int v = 0;
        Bono b = (Bono) obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(insert_mysql);
            ps.setInt(1, b.getId());
            ps.setDate(2, Date.valueOf(b.getFecha()));
            ps.setString(3, b.getNombre());
            ps.setInt(4, b.getIdJugador());
            ps.setInt(5, b.getHoras());
            ps.setBoolean(6, b.isEstado());
            v = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public int editar(Objetos obj) {
       int v = 0;
       Bono b = (Bono) obj;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(update_mysql);
            ps.setDate(1, Date.valueOf(b.getFecha()));
            ps.setString(2, b.getNombre());
            ps.setInt(3, b.getIdJugador());
            ps.setInt(4, b.getHoras());
            ps.setBoolean(5, b.isEstado());
            ps.setInt(6, b.getId());
            v = ps.executeUpdate();
       } catch (SQLException e){
           System.out.println(e.getMessage());
       } finally {
           super.cerrarObjetos();
       }
       return v;
    }

    
       @Override
    public int eliminar(Objetos obj) {
        int v = 0;
        Bono b = (Bono) obj;

        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(delete_mysql);
            ps.setInt(1, b.getId());
            v = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public Objetos getObjeto(int id) {
       Bono b = null;
       try {
           cx = super.getConexion();
           ps = cx.prepareStatement(get_mysql_id);
           ps.setInt(1, id);
           rs = ps.executeQuery();
           
           if(rs.next()){
               int idb = rs.getInt("id");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String nombre = rs.getString("nombre");
                int id_jugador = rs.getInt("id_jugador");
                int horas = rs.getInt("horas");
                boolean estado = rs.getBoolean("estado");
               b = new Bono(idb, fecha, nombre, id_jugador, horas, estado);
           }
           
       } catch (SQLException e){
           System.out.println(e.getMessage());
       } finally {
           super.cerrarObjetos();
       }
       
       return b;
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
