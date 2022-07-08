package servlet_seccion;


import datos.*;
import modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletCrear", urlPatterns = {"/ServletCrear"})
public class ServletCrear extends HttpServlet {

    private List<String> mensaje;
    private List<Integer> lista_id_pares;
    private List<Objetos> lista_jugadores;
    private List<Bono> lista_bonos_j1, lista_bonos_j2;
    private Objetos obj_pares;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String id_jugador = request.getHeader("id_jugador");
        if (id_jugador != null) {
            //obtener los bonos del jugador seleccionado
            String nombre_select = request.getHeader("nombre_select");
            int idj = Integer.parseInt(id_jugador);
            if (nombre_select.equals("select_j1")) {
                lista_bonos_j1 = this.getListaBonos(idj);
                request.setAttribute("bonosj1", lista_bonos_j1);
            } else {
                lista_bonos_j2 = this.getListaBonos(idj);
                request.setAttribute("bonosj2", lista_bonos_j2);
            }
           //response.sendRedirect("comunes/crear/form_crear_partido.jsp");
           
//            response.setContentType("text/html; charset=utf-8" );
//            PrintWriter out = response.getWriter();
//            
//            out.println("<select name='cbo_bonos_j1'>");
//            if (lista_bonos_j1 != null) {
//                for (Bono aux : lista_bonos_j1) {
//                    out.println("<option>id: " + aux.getId() + " - " + "Horas: " + aux.getHoras() + "</option>");
//                }
//            }
//            if (lista_bonos_j2 != null) {
//                for (Bono aux : lista_bonos_j2) {
//                    out.println("<option>id: " + aux.getId() + " - " + "Horas: " + aux.getHoras() + "</option>");
//                }
//            }
//            out.println("</select>");
//            out.close();
        }
        
        String crear = request.getParameter("id_pares");
        if (crear != null && crear.equals("buscar_objeto_pares")) {
            String valor = request.getParameter("cbo_id_pares");
            obj_pares = null;
            if (valorCorrecto(valor)) {
                obj_pares = getObjetoId(valor);
                
            }
            request.setAttribute("obj_pares", obj_pares);
        } 
        
        String inicio = request.getParameter("inicio");
         if(inicio != null && inicio.equals("cargar_listas")){
            lista_id_pares = this.getListIdPares();
            lista_jugadores = this.getListaJugadores();
            request.getSession().setAttribute("lista_id_pares", lista_id_pares);
            request.getSession().setAttribute("lista_jugadores", lista_jugadores);
        }
        request.getRequestDispatcher("comunes/crear/form_crear_partido.jsp").forward(request, response);
    }
    
    /**
     * Lista con todos los jugadores
     * @return 
     */
    private List<Objetos> getListaJugadores(){
        IDao interfaceDao = new JugadorDao();
        List<Objetos> lista = interfaceDao.select();
        return lista;
    }

    /**
     * Lista con los id del objeto pares
     * @return 
     */
    private List<Integer> getListIdPares() {
        List<Integer> lista_pares;
        ParesDao abstractDao = new ParesDao();
        lista_pares = abstractDao.getListaId();
        return lista_pares;
    }

    /**
     * Objeto pares con id del parametro
     * @param v
     * @return 
     */
    private Objetos getObjetoId(String v) {
        Objetos obj = null;
        IDao interfaceDao = new ParesDao();
        int id = Integer.parseInt(v);
        obj = interfaceDao.getObjeto(id);
        return obj;
    }
    
    private List<Bono> getListaBonos(int idj){
        List<Bono> lista_bonos;
        BonoDao bDao = new BonoDao();
        lista_bonos = bDao.select(idj);
        if(lista_bonos.isEmpty()){
            return null;
        }
        return lista_bonos;
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre_etiqueta = request.getParameter("nombre_etiqueta").toLowerCase();
        switch (nombre_etiqueta) {
            case "jugador":
                crearJugador(request, response);
                break;
            case "bono":
                crearBono(request, response);
                break;
            case "partido":
                crearPartido(request, response);
                break;
        }
    }

   

    private void crearJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("txt_nombre");
        String apellido = request.getParameter("txt_apellido");
        String comentario = request.getParameter("txt_comentario");

        Jugador j = new Jugador(nombre, apellido, comentario);
        IDao interfaceDao = new JugadorDao();
        crearObjeto(request, response, interfaceDao, j);
    }

    private void crearBono(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_bono = request.getParameter("txt_id");
        String fecha = request.getParameter("txt_fecha");
        String nombre = request.getParameter("txt_nombre");
        String id_jugador = request.getParameter("txt_id_jugador");
        String horas = request.getParameter("txt_horas");
        String estado = request.getParameter("txt_estado");

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
        //fecha = txt_fecha, jugador1 = cbo_jugador1, jugador2 = cbo_jugador, paga1 = cbo_paga1, paga2 = cbo_paga2
        String fecha = request.getParameter("txt_fecha");
        String idj1 = request.getParameter("cbo_jugador1").trim();
        String idj2 = request.getParameter("cbo_jugador2").trim();
        String paga1 = request.getParameter("cbo_paga1").trim();
        String paga2 = request.getParameter("cbo_paga2").trim();
        
        //si no existen los pares hay que crearlos, si al crearlos ya existen devolvera error, obtener los pares 
        if(obj_pares == null){
           crearPares(idj1, idj2); 
        }
        LocalDate f = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        
         
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
    
    
    private void crearPares(String id_j1, String id_j2){
        Jugador j1 = getJugador(id_j1);
        Jugador j2 = getJugador(id_j2);
        
        Pares p = new Pares(j1.getNombre(), j1.getId(), j2.getNombre(), j2.getId());
        IDao interfaceDao = new ParesDao();
        interfaceDao.crear(p);
        obj_pares = p; 
    }
    
     private Jugador getJugador(String id){
        Jugador ju = null;
        int idj1 = Integer.parseInt(id);
        IDao interfaceDao = new JugadorDao();
        ju = (Jugador) interfaceDao.getObjeto(idj1);
        return ju;
    }
    
     private boolean valorCorrecto(String v) {
        //solo admite numeros
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(v);
        return mat.matches();
    }

}
