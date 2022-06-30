
package servlet_seccion;

import datos.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;


@WebServlet(name = "ServletModificar", urlPatterns = {"/ServletModificar"})
public class ServletModificar extends HttpServlet {

     private String id_select ;
     private List<String> mensaje;
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //se usa todo para el resto de objetos, solo necesita el objeto a mostrar
        id_select = request.getParameter("cbo_id");
        String nombre_etiqueta = request.getParameter("nombre_etiqueta");
        String opcion = request.getParameter("opcion");
        Objetos obj = getObjeto(nombre_etiqueta, id_select);
        String l = request.getParameter("lista_id");
        String aux = l.substring(1, l.length()-1);
        String [] lis = aux.split(",");
        List<String> lista_id = Arrays.asList(lis);
        
        request.setAttribute("objeto", obj);
        request.setAttribute("opcion", opcion);
        request.setAttribute("lista_id", lista_id);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre_etiqueta = request.getParameter("nombre_etiqueta");
        String opcion = request.getParameter("opcion");
        
        if(nombre_etiqueta.equalsIgnoreCase("Jugador")){
            if(opcion.equalsIgnoreCase("editar")){
              editarJugador(request, response);    
            } else {
              eliminarJugador(request, response);     
            }
            
        } else if(nombre_etiqueta.equalsIgnoreCase("Bono")){
             if(opcion.equalsIgnoreCase("editar")){
                editarBono(request, response);      
             } else {
                 eliminarBono(request, response);
             }
           
            
        } else if(nombre_etiqueta.equalsIgnoreCase("Partido")){
             if(opcion.equalsIgnoreCase("editar")){
                //editarPartido(request, response);
             } else {
                 //eliminarPartido(request, response);
             }
        }
    }

    
    private Objetos getObjeto(String nombre, String select) {
        Objetos obj = null;
        IDao interfaceDao = null;
        int id;

        switch (nombre) {
            case "Jugador":
                interfaceDao = new JugadorDao();
                break;
            case "Bono":
                interfaceDao = new BonoDao();
                break;
            case "Partdio":

                break;
        }
        if(interfaceDao != null){
             id = Integer.parseInt(select);
             obj = interfaceDao.getObjeto(id);
        }

        return obj;
    }
    
    
    
    private void editarJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(id_select);
        String nombre = request.getParameter("txt_nombre");
        String apellido = request.getParameter("txt_apellido");
        String comentario = request.getParameter("txt_comentario");
        
        Jugador j = new Jugador(id, nombre, apellido, comentario);
        IDao interfaceDao = new JugadorDao();
        editarObjeto(request, response, interfaceDao, j);
    }
   
    
    private void eliminarJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(id_select);
        Jugador j = new Jugador(id);
        IDao interfaceDao = new JugadorDao();
        eliminarObjeto(request, response, interfaceDao, j);
    }
    
    
    
     private void editarBono(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int id = Integer.parseInt(id_select);
         String f = request.getParameter("txt_fecha");
         String nombre = request.getParameter("txt_nombre");
         String idj = request.getParameter("txt_id_jugador");
         String h = request.getParameter("txt_horas");
         String es = request.getParameter("txt_estado");
         
         LocalDate fecha = LocalDate.parse(f, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         int id_jugador = Integer.parseInt(idj);
         int horas = Integer.parseInt(h);
         boolean estado = Boolean.parseBoolean(es);
       
         Bono b = new Bono(id, fecha, nombre, id_jugador, horas, estado);
         IDao interfaceDao = new BonoDao();
         editarObjeto(request, response, interfaceDao, b);
     }
    
     private void eliminarBono(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int id = Integer.parseInt(id_select);
         Bono b = new Bono(id);
         IDao interfaceDao = new BonoDao();
         eliminarObjeto(request, response, interfaceDao, b);
     }
     
//     private void editarPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         
//     }
    
 //    private void eliminarPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         
//     }
     
     
     private void editarObjeto (HttpServletRequest request, HttpServletResponse response, IDao interfaceDao, Objetos obj) throws ServletException, IOException {
         mensaje = new ArrayList<>();
         int v = interfaceDao.editar(obj);
    
        if(v == 0){
           crearMensajeErrorEditar(obj);
           mensaje.add(interfaceDao.getMensajeError());
        } else {
            crearMensajeCorrectoEditar(obj);
        }
         request.setAttribute("mensaje", mensaje);
         request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
     }
     
     private void crearMensajeErrorEditar(Objetos obj){
         if(obj instanceof Jugador){
              mensaje.add(" El jugador "+((Jugador) obj).getNombre()+" "+((Jugador) obj).getApellido()+" no ha sido editado...");
         } else if(obj instanceof Bono){
              mensaje.add(" El bono " + ((Bono) obj).getNombre() + " " + obj.getId() + " no ha sido editado...");
         } else if(obj instanceof Partido){
             
         }
     }
     
     private void crearMensajeCorrectoEditar(Objetos obj){
          if(obj instanceof Jugador){
              mensaje.add(" El jugador "+((Jugador) obj).getNombre()+" "+((Jugador) obj).getApellido()+" ha sido editado");
         } else if(obj instanceof Bono){
              mensaje.add(" El bono " + ((Bono) obj).getNombre() + " " + obj.getId() + " ha sido editado");
         } else if(obj instanceof Partido){
             
         }
     }
     
     
     private void eliminarObjeto(HttpServletRequest request, HttpServletResponse response, IDao interfaceDao, Objetos obj) throws ServletException, IOException {
         mensaje = new ArrayList<>();
         int v = interfaceDao.eliminar(obj);
    
        if(v == 0){
           mensaje.add(" El objeto no ha sido eliminado...");
           mensaje.add(interfaceDao.getMensajeError());
        } else {
            mensaje.add(" El objeto ha sido eliminado...");
        }
         request.setAttribute("mensaje", mensaje);
         request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
     }
     

}
