
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
        String nombre_etiqueta = request.getParameter("nombre_etiqueta").toLowerCase();
        String opcion, l, aux;
        List<String> lista_id;
        Objetos obj;
        
        if (nombre_etiqueta.equals("partido")) {
            id_select = request.getParameter("txt_id_partido");
        } else {
            l = request.getParameter("lista_id");
            aux = l.substring(1, l.length() - 1);
            String[] lis = aux.split(",");
            lista_id = Arrays.asList(lis);
            request.setAttribute("lista_id", lista_id);
        }
        obj = getObjeto(nombre_etiqueta, id_select);
        opcion = request.getParameter("opcion");
        request.setAttribute("objeto", obj);
        request.setAttribute("opcion", opcion);
        if(obj == null){
            request.setAttribute("mensaje", "!!!El objeto es nulo o vacio...");
        }
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
             } else {
                 eliminarPartido(request, response);
             }
        }
    }

    
    private Objetos getObjeto(String nombre, String select) {
        Objetos obj = null;
        IDao interfaceDao = null;
        int id;

        switch (nombre) {
            case "jugador":
                interfaceDao = new JugadorDao();
                break;
            case "bono":
                interfaceDao = new BonoDao();
                break;
            case "partido":
                interfaceDao = new PartidoDao();
                break;
        }
        if (interfaceDao != null) {
            try {
                id = Integer.parseInt(select);
            } catch (NumberFormatException e) {
                return null;
            }
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
        IDao interfaceDao = new JugadorDao();
        Objetos j = interfaceDao.getObjeto(id);
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
         IDao interfaceDao = new BonoDao(); 
         Objetos b = interfaceDao.getObjeto(id);
         eliminarObjeto(request, response, interfaceDao, b);
     }
     
    
     private void eliminarPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int id = Integer.parseInt(id_select);
         IDao interfaceDao = new PartidoDao();
         Objetos p = interfaceDao.getObjeto(id);
         eliminarObjeto(request, response, interfaceDao, p);
     }
     
     
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
             mensaje.add(" El partido " + ((Partido) obj).getId() + " " + ((Partido) obj).getFecha() + " no ha sido editado...");
         }
     }
     
     private void crearMensajeCorrectoEditar(Objetos obj){
          if(obj instanceof Jugador){
              mensaje.add(" El jugador "+((Jugador) obj).getNombre()+" "+((Jugador) obj).getApellido()+" ha sido editado");
         } else if(obj instanceof Bono){
              mensaje.add(" El bono " + ((Bono) obj).getNombre() + " " + obj.getId() + " ha sido editado");
         } else if(obj instanceof Partido){
              mensaje.add(" El partido " + ((Partido) obj).getId() + " " + ((Partido) obj).getFecha() + " ha sido editado");
         }
     }
     
     
     private void eliminarObjeto(HttpServletRequest request, HttpServletResponse response, IDao interfaceDao, Objetos obj) throws ServletException, IOException {
         mensaje = new ArrayList<>();
         int v = interfaceDao.eliminar(obj);
    
        if(v == 0){
           mensaje.add(" El objeto no ha sido eliminado...");
           mensaje.add(interfaceDao.getMensajeError());
        } else {
            if(obj instanceof Partido){
                //actualizar bonos si hubiera devolviendo las horas
                actualizarBono(obj);
            }
            mensaje.add(" El objeto ha sido eliminado...");
        }
         request.setAttribute("mensaje", mensaje);
         request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
     }
     
     
    private void actualizarBono(Objetos obj) {
        Partido p = (Partido) obj;
        int paga1 = p.getPagaj1();
        int idb1 = p.getIdBono1();
        int paga2 = p.getPagaj2();
        int idb2 = p.getIdBono2();

        if (paga1 > 0 && idb1 > 0) {
            updateBono(idb1, paga1);
        }
        if (paga2 > 0 && idb2 > 0) {
            updateBono(idb2, paga2);
        }
    }
    
    private void updateBono(int id_bono, int horas_pagadas){
        IDao interfaceDao = new BonoDao();
        Bono b = (Bono) interfaceDao.getObjeto(id_bono);
        int horas = b.getHoras();
        b.setHoras(horas + horas_pagadas);
        //si el bono estaba en false va a pasar a true aunque solo sea por el incremento de esta hora
        b.setEstado(true);
        interfaceDao.editar(b);
    }
     

}
