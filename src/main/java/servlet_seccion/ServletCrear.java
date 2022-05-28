
package servlet_seccion;

import datos.IDao;
import datos.JugadorDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Jugador;


@WebServlet(name = "ServletCrear", urlPatterns = {"/ServletCrear"})
public class ServletCrear extends HttpServlet {


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String opcion = request.getParameter("opcion");
       switch(opcion){
           case "jugador":
               crearJugador(request, response);
               break;
           case "bono":
               
               break;
           case "partido":
               
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
    

}
