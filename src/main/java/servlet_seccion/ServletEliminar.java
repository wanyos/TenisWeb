
package servlet_seccion;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import datos.*;
import java.util.ArrayList;


@WebServlet(name = "ServletEliminar", urlPatterns = {"/ServletEliminar"})
public class ServletEliminar extends HttpServlet {

    private String id_select ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //se usa todo para el resto de objetos, solo necesita el objeto a mostrar
        id_select = request.getParameter("cbo_id");
        String nombre_etiqueta = request.getParameter("nombre_etiqueta");
        Objetos obj = getObjeto(nombre_etiqueta, id_select);
        List<Integer> lista_id = getListaId(obj);
       
        request.setAttribute("objeto", obj);
        request.setAttribute("opcion", "eliminar");
        request.setAttribute("lista_id", lista_id);
        request.getRequestDispatcher("generico.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
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

}
