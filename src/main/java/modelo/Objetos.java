
package modelo;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.*;


public abstract class Objetos {
    
       
    public abstract List<String> getNombresCampos();
    
    public abstract int getId();
    
    
    protected List<String> getListaCampos(String paquete_clase) {
        List<String> lista_campos = new ArrayList<>();
        Class userClass;
        try {
            userClass = Class.forName(paquete_clase);
            Field[] userFields = userClass.getDeclaredFields();
            for (Field aux : userFields) {
                lista_campos.add(aux.getName());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_campos;
    }
    
         
        
}
