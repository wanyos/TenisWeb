
package datos;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import modelo.Objetos;
import modelo.Partido;


public class PartidoDao extends AbstractDao implements IDao {

    private final String mysql_insert = "insert into partido(fecha, id_pares, pagaj1, pagaj2, id_bono1, id_bono2) values (?,?,?,?,?,?)";
    private final String mysql_select = "select partido.id, partido.fecha, partido.id_pares, pares.jugador1, pares.jugador2, partido.pagaj1, partido.pagaj2, partido.id_bono1, partido.id_bono2"
                                        + " from partido inner join pares on partido.id_pares=pares.id";
    private final String mysql_update = "update from partido set fecha=?, id_pares=?, pagaj1=?, pagaj2=?, id_bono1=?, id_bono2=? where id=?)";
    private final String mysql_delete = "delete from partido where id=?";
    private final String mysql_get_obj = "select partido.id, partido.fecha, partido.id_pares, pares.jugador1, pares.jugador2, partido.pagaj1, partido.pagaj2, partido.id_bono1, partido.id_bono2 "
                                        + "from partido inner join pares on partido.id_pares=pares.id where partido.id=?;";
    private String mensaje_error;
    
    public PartidoDao(){}
    
    @Override
    public String getMensajeError() {
        return mensaje_error;
    }

    private void setMensajeError(String mensaje_error) {
        this.mensaje_error = mensaje_error;
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
            ps = cx.prepareStatement(mysql_select);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("partido.id");
                LocalDate fecha = rs.getDate("partido.fecha").toLocalDate();
                int id_pares = rs.getInt("partido.id_pares");
                String jugador1 = rs.getString("pares.jugador1");
                String jugador2 = rs.getString("pares.jugador2");
                int paga1 = rs.getInt("partido.pagaj1");
                int paga2 = rs.getInt("partido.pagaj2");
                int id_bono1 = rs.getInt("partido.id_bono1");
                int id_bono2 = rs.getInt("partido.id_bono2");
                Partido p = new Partido(id, fecha, id_pares, jugador1, jugador2, paga1, paga2, id_bono1, id_bono2);
                lista.add(p);
            }
        } catch (SQLException e) {
            this.setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return lista;
    }

    
    @Override
    public int crear(Objetos obj) {
        Partido p = (Partido) obj;
        int v = 0;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(mysql_insert);
            ps.setDate(1, Date.valueOf(p.getFecha()));
            ps.setInt(2, p.getIdPares());
            ps.setInt(3, p.getPagaj1());
            ps.setInt(4, p.getPagaj2());
            ps.setInt(5, p.getIdBono1());
            ps.setInt(6, p.getIdBono2());
            v = ps.executeUpdate();
        } catch(SQLException e){
            this.setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public int editar(Objetos obj) {
        Partido p = (Partido) obj;
        int v = 0;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(mysql_update);
            ps.setDate(1, Date.valueOf(p.getFecha()));
            ps.setInt(2, p.getIdPares());
            ps.setInt(3, p.getPagaj1());
            ps.setInt(4, p.getPagaj2());
            ps.setInt(5, p.getIdBono1());
            ps.setInt(6, p.getIdBono2());
            ps.setInt(7, p.getId());
            v = ps.executeUpdate();
        } catch(SQLException e){
            this.setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    @Override
    public int eliminar(Objetos obj) {
        Partido p = (Partido) obj;
        int v = 0;
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(mysql_delete);
            ps.setInt(1, p.getId());
            v = ps.executeUpdate();
        } catch(SQLException e){
            this.setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return v;
    }

    
    @Override
    public Objetos getObjeto(int id) {
       Partido p = null;
       try {
           cx = super.getConexion();
           ps = cx.prepareStatement(mysql_get_obj);
           ps.setInt(1, id);
           rs = ps.executeQuery();
           
           if(rs.next()){
                int idp = rs.getInt("partido.id");
                LocalDate fecha = rs.getDate("partido.fecha").toLocalDate();
                int id_pares = rs.getInt("partido.id_pares");
                String jugador1 = rs.getString("pares.jugador1");
                String jugador2 = rs.getString("pares.jugador2");
                int paga1 = rs.getInt("partido.pagaj1");
                int paga2 = rs.getInt("partido.pagaj2");
                int id_bono1 = rs.getInt("partido.id_bono1");
                int id_bono2 = rs.getInt("partido.id_bono2");
                p = new Partido(idp, fecha, id_pares, jugador1, jugador2, paga1, paga2, id_bono1, id_bono2);
           }
       } catch(SQLException e){
           this.setMensajeError(e.getMessage());
       } finally {
           super.cerrarObjetos();
       }
       return p;
    }
    
    
     public List<Objetos> getListaConsulta(String consulta, int idj, String fe) {
        String consult = getConsulta(consulta, idj, fe);
        List<Objetos> lista = new ArrayList<>();
        try {
            cx = super.getConexion();
            ps = cx.prepareStatement(consult);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("partido.id");
                LocalDate fecha = rs.getDate("partido.fecha").toLocalDate();
                int id_pares = rs.getInt("partido.id_pares");
                String jugador1 = rs.getString("pares.jugador1");
                String jugador2 = rs.getString("pares.jugador2");
                int paga1 = rs.getInt("partido.pagaj1");
                int paga2 = rs.getInt("partido.pagaj2");
                int id_bono1 = rs.getInt("partido.id_bono1");
                int id_bono2 = rs.getInt("partido.id_bono2");
                Partido p = new Partido(id, fecha, id_pares, jugador1, jugador2, paga1, paga2, id_bono1, id_bono2);
                lista.add(p);
            }
        } catch (SQLException e) {
            this.setMensajeError(e.getMessage());
        } finally {
            super.cerrarObjetos();
        }
        return lista;
    }
     
     private String getConsulta(String con, int id, String fecha){
         String f = '\u0022'+""+fecha+""+'\u0022';
         String fecha_ahora = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         String fecha_actual = '\u0022'+""+fecha_ahora+""+'\u0022';
        switch (con) {
            case "mysql_id_fecha":
            String mysql_id_fecha = "select partido.id, partido.fecha, partido.id_pares, pares.jugador1, pares.jugador2, partido.pagaj1, partido.pagaj2, partido.id_bono1, partido.id_bono2 "
                                  + "from partido inner join pares on partido.id_pares=pares.id where (pares.idj1="+id+" or pares.idj2="+id+") and "
                                  + "(partido.fecha between "+f+" and "+fecha_actual+");";
            return mysql_id_fecha;
            case "mysql_id":
            String mysql_id = "select partido.id, partido.fecha, partido.id_pares, pares.jugador1, pares.jugador2, partido.pagaj1, partido.pagaj2, partido.id_bono1, partido.id_bono2 "
                            + "from partido inner join pares on partido.id_pares=pares.id where pares.idj1="+id+" or pares.idj2="+id+";";
            return mysql_id;
            case "mysql_fecha":
            String mysql_fecha = "select partido.id, partido.fecha, partido.id_pares, pares.jugador1, pares.jugador2, partido.pagaj1, partido.pagaj2, partido.id_bono1, partido.id_bono2 "
                               + "from partido inner join pares on partido.id_pares=pares.id where partido.fecha between"+f+" and "+fecha_actual+";";
            return mysql_fecha;
            default:
                break;
        }
         return "";
     }

   
    
}
