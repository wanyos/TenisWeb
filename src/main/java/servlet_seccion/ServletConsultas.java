
package servlet_seccion;

import datos.IDao;
import datos.JugadorDao;
import datos.PartidoDao;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Objetos;
import modelo.Partido;


@WebServlet(name = "ServletConsultas", urlPatterns = {"/ServletConsultas"})
public class ServletConsultas extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IDao interfaceDao = new JugadorDao();
        List<Objetos> lista_jugadores = interfaceDao.select();
        
        request.getSession().setAttribute("lista_jugadores", lista_jugadores);
        request.getRequestDispatcher("consultas.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Objetos> lista_partidos = null;
        String jugador = request.getParameter("cbo_jugador");
        String fecha = request.getParameter("txt_fecha");
        int id_jugador = getIdJugador(jugador);

        String consulta = getConsulta(id_jugador, fecha);
        if (consulta != null) {
            lista_partidos = getListaPartidos(consulta, id_jugador, fecha);
        }
        
        List<String> nombre_campos = getColumnas();
        
        request.setAttribute("columnas", nombre_campos);
        request.setAttribute("lista_partidos", lista_partidos);
        request.getRequestDispatcher("consultas.jsp").forward(request, response);
    }
    
    
    private String getConsulta(int id_jugador, String f){
         if (id_jugador > 0 && (f != null && !f.isEmpty())) {
            //lista con todos los partidos del jugador desde la fecha
            return "mysql_id_fecha";  
            
        } else if (id_jugador > 0 && (f == null || f.isEmpty())) {
            //lista con todos los partidos del jugador
            return "mysql_id";
            
        } else if (id_jugador <= 0 && (f != null && !f.isEmpty())) {
            //todos los partidos desde esa fecha
            return "mysql_fecha";
        } 
        return null;
    }
    
    
    private List<Objetos> getListaPartidos(String consulta, int idj, String fe) {
        PartidoDao pdao = new PartidoDao();
        List<Objetos> lista_partidos = pdao.getListaConsulta(consulta, idj, fe);
        Collections.sort(lista_partidos);
        return lista_partidos;
    }
    
    
    
    private int getIdJugador(String id) {
        int v = 0;
        if(valorCorrecto(id)){
            v = Integer.parseInt(id);
        }
        return v;
    }
    
    
     private boolean valorCorrecto(String v) {
        //solo admite numeros
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(v);
        return mat.matches();
    }
     
     
      /**
     * Retorna el nombre de las columnas para la tabla listar
     * Usa los atributos de cada clase para nombrar a las columnas
     * @param nombre
     * @return 
     */
    private List<String> getColumnas() {
        List<String> lista;
        Objetos obj = new Partido();
        lista = obj.getNombresCampos();
        return lista;
    }


}
