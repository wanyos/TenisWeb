
package servlet_seccion;

import datos.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;


@WebServlet(name = "ServletEditar", urlPatterns = {"/ServletEditar"})
public class ServletEditar extends HttpServlet {

     private String id_select ;

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se usa todo para el resto de objetos, solo necesita el objeto a mostrar
        id_select = request.getParameter("cbo_id");
        String nombre_etiqueta = request.getParameter("nombre_etiqueta");
        Objetos obj = getObjeto(nombre_etiqueta, id_select);
        List<Integer> lista_id = getListaId(obj);
       
        request.setAttribute("objeto", obj);
        request.setAttribute("opcion", "editar");
        request.setAttribute("lista_id", lista_id);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        
        if(opcion.equalsIgnoreCase("Jugador")){
            editarJugador(request, response);
        } else if(opcion.equalsIgnoreCase("Bono")){
            //editarBono(request, response);
        } else if(opcion.equalsIgnoreCase("Partido")){
            //editarPartido(request, response);
        }
    }

    
    private Objetos getObjeto(String nombre, String select) {
        Objetos obj = null;
        IDao interfaceDao = null;
        int id;

        switch (nombre) {
            case "Jugador":
                id = Integer.parseInt(select);
                interfaceDao = new JugadorDao();
                obj = interfaceDao.getObjeto(id);
                break;
            case "Bono":

                break;
            case "Partdio":

                break;
        }

        return obj;
    }
    
    
    private List<Integer> getListaId(Objetos obj) {
        List<Integer> lista = new ArrayList<>();
        AbstractDao ab = null;

        if (obj instanceof Jugador) {
            ab = new JugadorDao();
        } else if (obj instanceof Bono) {
            //ab = new BonoDao();
        } else if (obj instanceof Partido) {
            //ab = new PartidoDao();
        }
        if (ab != null) {
            lista = ab.getListaId();
        }

        return lista;
    }
    
    private void editarJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(id_select);
        String nombre = request.getParameter("txt_nombre");
        String apellido = request.getParameter("txt_apellido");
        String comentario = request.getParameter("txt_comentario");
        
        Jugador j = new Jugador(id, nombre, apellido, comentario);
        IDao interfaceDao = new JugadorDao();
        int v = interfaceDao.editar(j);
        String mensaje;
        if(v == 0){
            mensaje = "!!!Error el jugador "+nombre+" "+apellido+" no ha sido editado...";
        } else {
            mensaje = "El jugador "+nombre+" "+apellido+" ha sido editado";
        }
         request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }
    
//     private void editarBono(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         
//     }
     
//     private void editarPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         
//     }

}
