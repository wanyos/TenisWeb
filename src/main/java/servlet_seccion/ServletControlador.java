package servlet_seccion;



import datos.AbstractDao;
import datos.BonoDao;
import datos.IDao;
import datos.JugadorDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.*;

@WebServlet(urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String opcion = request.getParameter("opcion").toLowerCase();
       String nombre_etiqueta = request.getParameter("nombre_etiqueta");
       List<Integer> lista_id;
    
        switch (opcion) {
            case "listar":
                List<String> colum = getColumnas(nombre_etiqueta);
                List<Objetos> datos = getListaDatos(nombre_etiqueta);

                request.setAttribute("opcion", "listar");
                request.setAttribute("columnas", colum);
                request.setAttribute("datos", datos);
                break;
            case "crear":
                request.setAttribute("opcion", "crear");
                break;
            case "editar":
                request.setAttribute("opcion", "editar");
                lista_id = getListaId(nombre_etiqueta);
                request.setAttribute("lista_id", lista_id);
                break;
            case "eliminar":
                request.setAttribute("opcion", "eliminar");
                lista_id = getListaId(nombre_etiqueta);
                request.setAttribute("lista_id", lista_id);
                break;
       }
       request.getRequestDispatcher("generico.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }
    
    
    /**
     * Retorna el nombre de las columnas para la tabla listar
     * Usa los atributos de cada clase para nombrar a las columnas
     * @param nombre
     * @return 
     */
    private List<String> getColumnas(String nombre) {
        List<String> lista = new ArrayList<>();
        Objetos obj = null;
        if (nombre.equalsIgnoreCase("jugador")) {
            obj = new Jugador();
        } else if (nombre.equalsIgnoreCase("bono")) {
            obj = new Bono();
        } else if (nombre.equalsIgnoreCase("partido")) {
            obj = new Partido();
        }
        if (obj != null) {
            lista = obj.getNombresCampos();
        }
        return lista;
    }
    
    
    /**
     * Crea la lista con los datos de cada opcion para listar
     * @param nombre
     * @return 
     */
    private List<Objetos> getListaDatos(String nombre) {
        List<Objetos> lista = new ArrayList<>();
        IDao interfaceDao = null;
        
         if (nombre.equalsIgnoreCase("jugador")) {
            interfaceDao = new JugadorDao();
        } else if (nombre.equalsIgnoreCase("bono")) {
            interfaceDao = new BonoDao();
        } else if (nombre.equalsIgnoreCase("partido")) {
            //interfaceDao = new PartidoDao();
        }
         if(interfaceDao != null){
             lista = interfaceDao.select();
         }
        return lista;
    }
    
    
    /**
     * Crea una lista con los id de los objetos de la opción escogida, estos estan en la bd
     * Se usa para la vista de editar y eliminar donde escogiendo el id vemos los datos del objeto
     * @param nombre
     * @return 
     */
    private List<Integer> getListaId(String nombre) {
        AbstractDao ab = null;
        List<Integer> lista = null;
        
         if (nombre.equalsIgnoreCase("jugador")) {
            ab = new JugadorDao();
        } else if (nombre.equalsIgnoreCase("bono")) {
            ab = new BonoDao();
        } else if (nombre.equalsIgnoreCase("partido")) {
            //ab = new PartidoDao();
        }
         if(ab != null){
             lista = ab.getListaId();
         }
        
        return lista;
    }
    
    
   

}

