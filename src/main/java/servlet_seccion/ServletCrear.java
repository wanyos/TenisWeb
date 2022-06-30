package servlet_seccion;

import datos.BonoDao;
import datos.IDao;
import datos.JugadorDao;
import datos.ParesDao;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bono;
import modelo.Jugador;
import modelo.Objetos;
import modelo.Partido;


@WebServlet(name = "ServletCrear", urlPatterns = {"/ServletCrear"})
public class ServletCrear extends HttpServlet {

    private List<String> mensaje;

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //crear lista id_pares
        String crear = request.getParameter("crear");

        if (crear.equals("lista_id_pares")) {
            List<Integer> lista_id_pares = this.crearListIdPares();
            request.setAttribute("lista_id_pares", lista_id_pares);
        } else if(crear.equalsIgnoreCase("buscar_objeto_pares")){
            String valor = request.getParameter("cbo_id_pares");
            Objetos obj = getObjetoId(valor);
            request.setAttribute("objeto_pares", obj);
        }
        
        request.getRequestDispatcher("comunes/crear/form_crear_partido_aux.jsp").forward(request, response);
    }

    private List<Integer> crearListIdPares() {
        //Obtener la lista de todos los id de pares
        //mandarsela al jsp para que la muestre en combo de id_pares
        List<Integer> lista_pares;
        ParesDao abstractDao = new ParesDao();
        lista_pares = abstractDao.getListaId();
        return lista_pares;
    }
    
    private Objetos getObjetoId(String v) {
        Objetos obj = null;
        //IDao interfaceDao = new ParesDao();
        //int id = Integer.parseInt(v);
        //obj = interfaceDao.getObjeto(id);
        return obj;
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
        crearObjeto(request, response, interfaceDao, b);
    }

    private void crearPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void crearObjeto(HttpServletRequest request, HttpServletResponse response, IDao interfaceDao, Objetos obj) throws ServletException, IOException {
        mensaje = new ArrayList<>();
        int v = interfaceDao.crear(obj);
        if (v == 0) {
            crearMensajeError(obj);
            mensaje.add(interfaceDao.getMensajeError());
        } else {
            crearMensajeCorrecto(obj);
        }
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
    }

    private void crearMensajeError(Objetos obj) {
        if (obj instanceof Jugador) {
            mensaje.add(" El jugador " + ((Jugador) obj).getNombre() + " " + ((Jugador) obj).getApellido() + " no ha sido creado...");
        } else if (obj instanceof Bono) {
            mensaje.add(" El bono nombre: " + ((Bono) obj).getNombre() + " - id_bono: " + obj.getId() + " no ha sido creado...");
        } else if (obj instanceof Partido) {

        }
    }

    private void crearMensajeCorrecto(Objetos obj) {
        if (obj instanceof Jugador) {
            mensaje.add(" El jugador " + ((Jugador) obj).getNombre() + " " + ((Jugador) obj).getApellido() + " ha sido creado...");
        } else if (obj instanceof Bono) {
            mensaje.add(" El bono nombre: " + ((Bono) obj).getNombre() + " - id_bono: " + obj.getId() + " ha sido creado...");
        } else if (obj instanceof Partido) {

        }
    }

}
