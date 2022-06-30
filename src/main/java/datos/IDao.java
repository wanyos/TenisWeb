
package datos;

import java.util.List;
import modelo.Objetos;


public interface IDao {
    
    List<Objetos> select();
    int crear(Objetos obj);
    int editar(Objetos obj);
    int eliminar(Objetos obj);
    Objetos getObjeto(int id);
    String getMensajeError();
}
