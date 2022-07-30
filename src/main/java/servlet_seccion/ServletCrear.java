package servlet_seccion;


import datos.*;
import modelo.*;
import java.io.IOException;
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
    private Objetos obj_pares;
    private Bono bono_actualizar1, bono_actualizar2;
    private int horasp1, horasp2, id_bono1, id_bono2;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bono> lista_bonos_j1 = null, lista_bonos_j2 = null;
        String id_jugador = request.getParameter("id_jugador");
        if (id_jugador != null) {
            //obtener los bonos del jugador seleccionado
            String nombre_select = request.getParameter("nombre_select");
            String id_otro_jugador = request.getParameter("id_otro_jugador");
            Jugador ju1, ju2;
            
            if (nombre_select.equals("select_j1")) {
                ju1 = this.getJugador(id_jugador);
                ju2 = this.getJugador(id_otro_jugador);
            } else {
                ju2 = this.getJugador(id_jugador);
                ju1 = this.getJugador(id_otro_jugador);
            }
            lista_bonos_j1 = (List<Bono>)(List<?>)this.getListaBonos(ju1, -1);
            lista_bonos_j2 = (List<Bono>)(List<?>)this.getListaBonos(ju2, -1);
             request.setAttribute("bonosj1", lista_bonos_j1);
             request.setAttribute("bonosj2", lista_bonos_j2);
             request.setAttribute("jugador1", ju1);  
             request.setAttribute("jugador2", ju2);
        }
        
        String crear = request.getParameter("id_pares");
        if (crear != null && crear.equals("buscar_objeto_pares")) {
            String valor = request.getParameter("cbo_id_pares");
            obj_pares = null;
            if (valorCorrecto(valor)) {
                obj_pares = getObjetoId(valor);
                Pares p = (Pares) obj_pares;
                lista_bonos_j1 = (List<Bono>)(List<?>)this.getListaBonos(null, p.getIdJ1());
                lista_bonos_j2 = (List<Bono>)(List<?>)this.getListaBonos(null, p.getIdJ2());
            }
            request.setAttribute("bonosj1", lista_bonos_j1);
            request.setAttribute("bonosj2", lista_bonos_j2);
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
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         mensaje = new ArrayList<>(); //crear el array para guardar mensajes de error o exito
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

        Objetos j = new Jugador(nombre, apellido, comentario);
        IDao interfaceDao = new JugadorDao();
        crearObjeto(interfaceDao, j);
        
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
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

        Objetos b = new Bono(idb, f, nombre, idj, h, es);
        IDao interfaceDao = new BonoDao();
        crearObjeto(interfaceDao, b);
        
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
    }

    
    private void crearPartido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fecha = request.getParameter("txt_fecha");
        String idj1 = request.getParameter("cbo_jugador1");
        String idj2 = request.getParameter("cbo_jugador2");
        String paga1 = request.getParameter("cbo_paga1");
        String paga2 = request.getParameter("cbo_paga2");
        String horas_p1 = request.getParameter("cbo_horas_p1");
        String horas_p2 = request.getParameter("cbo_horas_p2");
        
        //obtener los pares o crearlos, no se puede seguir sin obj_pares
        //gestión pago
        //uno de los dos jugadores ha de pagar minimo una hora, no se puede seguir
        //si existe bono hay que gestionar el pago del bono, si no es correcto no seguir
        
        LocalDate fe = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //obj_pares = (Objetos) request.getSession().getAttribute("obj_pares");
        if(!gestionPares(idj1, idj2)){
            mensaje.add(" !!!Error - Se ha producido un error al crear el objeto pares...");
            
        } else if (!gestionPago(paga1, horas_p1, paga2, horas_p2)){
             mensaje.add(" !!!Error - Se ha producido un error en el proceso de pago...");
             
        } else if (comprobarActualizarBono()){
            int id_pares = obj_pares.getId();
            Objetos p = new Partido(fe, id_pares, horasp1, horasp2, id_bono1, id_bono2);
            IDao interfaceDao = new PartidoDao();
            crearObjeto(interfaceDao, p);
        } else {
             mensaje.add(" !!!Error - Se ha producido un error y no es posible continuar...");
             mensaje.add(" El error posiblemente se ha originado en el pago con bono...");
        }
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("comunes/alerta.jsp").forward(request, response);
    }
    
    /**
     * Gestiona el objeto pares, el objeto ha de existir
     * @param idj1
     * @param idj2
     * @return 
     */
    private boolean gestionPares(String idj1, String idj2) {
        int v = 0;
        if (obj_pares == null) {
            obj_pares = getObjetoPares(idj1, idj2);
            if (obj_pares == null) {
                v = crearPares(idj1, idj2);
            } else {
                return true;
            }
        } else if(obj_pares != null){
            return true;
        }
         return v == 1;
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
     * Objeto pares id del parametro
     * @param v
     * @return 
     */
    private Objetos getObjetoId(String v) {
        Objetos obj;
        IDao interfaceDao = new ParesDao();
        int id = Integer.parseInt(v);
        obj = interfaceDao.getObjeto(id);
        return obj;
    }
    
    /**
     * Con ids de los juggadores
     * @param idj1
     * @param idj2
     * @return 
     */
    private Objetos getObjetoPares(String idj1, String idj2) {
        Objetos obj;
        int id_j1 = Integer.parseInt(idj1);
        int id_j2 = Integer.parseInt(idj2);
        ParesDao pares = new ParesDao();
        obj = pares.getParesIdJu(id_j1, id_j2);
        return obj;
    }

    
    private List<Objetos> getListaBonos(Jugador j, int idj) {
        List<Objetos> lista_bonos = null;
        BonoDao bDao = new BonoDao();
        if (j != null) {   
           lista_bonos = bDao.select(j.getId());
        } else if(j == null && idj > -1){
            lista_bonos = bDao.select(idj);
        }
        if (lista_bonos == null || lista_bonos.isEmpty()) {
                return null;
            }
        return lista_bonos;
    }
    
   

    private void crearObjeto(IDao interfaceDao, Objetos obj) throws ServletException, IOException {
        int v;
//        if (obj instanceof Partido) {
//            if (!comprobarActualizarBono()) {
//                mensaje.add("!!!Error se ha producido un error en el pago del bono...");
//                return;
//            }
//        }
        v = interfaceDao.crear(obj);
        if (v == 1) {
//            if (obj instanceof Jugador) {
//                limpiarTablaDuplicados(obj);
//            }
            crearMensajeCorrecto(obj);
        } else {
            crearMensajeError(obj);
            mensaje.add(interfaceDao.getMensajeError());
        }
    }

    
    private void crearMensajeError(Objetos obj) {
        if (obj instanceof Jugador) {
            mensaje.add(" El jugador " + ((Jugador) obj).getNombre() + " " + ((Jugador) obj).getApellido() + " no ha sido creado...");
        } else if (obj instanceof Bono) {
            mensaje.add(" El bono nombre: " + ((Bono) obj).getNombre() + " - id_bono: " + obj.getId() + " no ha sido creado...");
        } else if (obj instanceof Partido) {
           mensaje.add(" El partido Fecha: " + ((Partido) obj).getFecha() + " - id_pares: " + ((Partido) obj).getIdPares() + " no ha sido creado...");
        } 
    }

    private void crearMensajeCorrecto(Objetos obj) {
        if (obj instanceof Jugador) {
            mensaje.add(" El jugador " + ((Jugador) obj).getNombre() + " " + ((Jugador) obj).getApellido() + " ha sido creado...");
        } else if (obj instanceof Bono) {
            mensaje.add(" El bono nombre: " + ((Bono) obj).getNombre() + " - id_bono: " + obj.getId() + " ha sido creado...");
        } else if (obj instanceof Partido) {
           mensaje.add(" El partido id: " + ((Partido) obj).getFecha() + " - id_pares: " + ((Partido) obj).getIdPares() + " ha sido creado...");
        } 
    }
    
    
    private int crearPares(String id_j1, String id_j2){
        int v;
        Jugador j1 = getJugador(id_j1);
        Jugador j2 = getJugador(id_j2);
        
        Pares p = new Pares(j1.getNombre(), j1.getId(), j2.getNombre(), j2.getId());
        IDao interfaceDao = new ParesDao();
        v = interfaceDao.crear(p);
        obj_pares = p;
        return v;
    }
    
     private Jugador getJugador(String id){
        Jugador ju;
        int idj1 = -1;
        if(id != null && valorCorrecto(id)){
           idj1 = Integer.parseInt(id);    
        }
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
     
     
     /**
      * La opcion pago puede tener valor si/no/id_bono
      * @param paga1
      * @param horas_p1
      * @param paga2
      * @param horas_p2
      * @return 
      */
    private boolean gestionPago(String paga1, String horas_p1, String paga2, String horas_p2) {
        int h1 = getNumero(horas_p1);
        int h2 = getNumero(horas_p2);
        if ((h1 == -1 || h2 == -1) || (h1 == 0 && h2 == 0)) {
            mensaje.add(" !!!Error - Alguno de los jugadores debe pagar al menos una hora...");
            return false;
        }
        if (!prepararPagoBono(1, paga1, h1)) {
            mensaje.add(" !!!Error - Se ha producido un error en el pago con bono del jugador 1...");
            return false;
        }
        if (!prepararPagoBono(2, paga2, h2)) {
            mensaje.add(" !!!Error - Se ha producido un error en el pago con bono del jugador 2...");
            return false;
        }

        if (paga1.equalsIgnoreCase("si")) {
            horasp1 = h1;
        }
        if (paga2.equalsIgnoreCase("si")) {
            horasp2 = h2;
        }
        return true;
    }
     
     
     private int getNumero(String n){
         int v = -1;
         try {
             v = Integer.parseInt(n);
         } catch(NumberFormatException e){
             return v;
         }
         return v;
     }
     
          
    private boolean prepararPagoBono(int jugador, String id_bono, int horas) {
        int idb;
        Bono b;
        if (valorCorrecto(id_bono)) {
            idb = getNumero(id_bono);

            if (idb == -1) {
                mensaje.add(" El id_bono no es correcto...");
                return false;
            }
            IDao interfaceDao = new BonoDao();
            b = (Bono) interfaceDao.getObjeto(idb);
            int horas_disponibles = b.getHoras();
            if (horas_disponibles == 0 || horas_disponibles - horas < 0) {
                mensaje.add(" !!!Error - La cantidad de horas a descontar del bono no es posible...");
                return false;
            } else {
                if (jugador == 1) {
                    bono_actualizar1 = b;
                    bono_actualizar1.setHoras(horas_disponibles - horas);
                    horasp1 = horas;
                    id_bono1 = b.getId();
                } else {
                    bono_actualizar2 = b;
                    bono_actualizar2.setHoras(horas_disponibles - horas);
                    horasp2 = horas;
                    id_bono2 = b.getId();
                }
                 cambiarEstadoBono(b);
            }
        }
        return true;
    }
        
         
         
    private void cambiarEstadoBono(Bono b){
        int horas_disponibles = b.getHoras();
        if(horas_disponibles == 0){
            b.setEstado(false);
        }
    }
    
    private int actualizarBono() {
        int v = 0;
        if (bono_actualizar1 != null) {
            IDao interfaceDao = new BonoDao();
            v = interfaceDao.editar(bono_actualizar1);
        }
        if (bono_actualizar2 != null) {
            IDao interfaceDao = new BonoDao();
            v = interfaceDao.editar(bono_actualizar2);
        }
        return v;
    }
    
    /**
     * Comprueba si hay algún bono en espera para actualizar sus horas
     * @return 
     */
    private boolean comprobarActualizarBono() {
        int v;
        if (bono_actualizar1 != null || bono_actualizar2 != null) {
            v = actualizarBono();
            if(v == 0){
                return false;
            }
        }
        return true;
    }
    
//    private void limpiarTablaDuplicados(Objetos obj){
//        JugadorDao jdao = new JugadorDao();
//        jdao.limpiarDuplicados(obj);
//    }


}
