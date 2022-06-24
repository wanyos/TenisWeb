
package servlet_seccion;

import datos.BonoDao;
import datos.IDao;
import datos.JugadorDao;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bono;
import modelo.Jugador;


@WebServlet(name = "ServletCrear", urlPatterns = {"/ServletCrear"})
public class ServletCrear extends HttpServlet {


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String nombre_etiqueta = request.getParameter("nombre_etiqueta").toLowerCase();
       switch(nombre_etiqueta){
           case "jugador":
               crearJugador(request, response);
               break;
           case "bono":
               crearBono(request, response);
               break;
           case "partido":
               //crearPartido(request, response);
               break;
       }
    }

   
    private void crearJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txt_nombre");
        String apellido = request.getParameter("txt_apellido");
        String comentario = request.getParameter("txt_comentario");
        String mensaje = "";
        
        Jugador j = new Jugador(nombre, apellido, comentario);
        IDao interfaceDao = new JugadorDao();
        int v = interfaceDao.crear(j);
        
        if(v == 0){
            mensaje = "!!!Error, el jugador "+nombre+" "+apellido+" no ha sido creado...";
        } else {
            mensaje = " El jugador "+nombre+" "+apellido+" ha sido creado...";
        }
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }
    
    
    private void crearBono(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_bono = request.getParameter("txt_id");
        String fecha = request.getParameter("txt_fecha");
        String nombre = request.getParameter("txt_nombre");
        String id_jugador = request.getParameter("txt_id_jugador");
        String horas = request.getParameter("txt_horas");
        String estado = request.getParameter("txt_estado");
        String mensaje = "";

        int idb = Integer.parseInt(id_bono);
        LocalDate f = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int idj = Integer.parseInt(id_jugador);
        int h = Integer.parseInt(horas);

        boolean es = false;
        if (estado.equalsIgnoreCase("activo")) {
            es = true;
        }

        Bono b = new Bono(idb, f, nombre, idj, h, es);
        IDao interfaceDao = new BonoDao();
        int v = interfaceDao.crear(b);

        if (v == 0) {
            mensaje = "!!!Error, el bono " + nombre + " " + id_bono + " no ha sido creado...";
        } else {
            mensaje = " El bono " + nombre + " " + id_bono + " ha sido creado...";
        }
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }
    
    
     private void crearPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
     }

}
