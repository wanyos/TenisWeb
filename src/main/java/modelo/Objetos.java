
package modelo;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.*;


public abstract class Objetos implements Comparable<Objetos> {
    
       
    public abstract List<String> getNombresCampos();
    
    public abstract int getId();
    
     @Override
    public abstract int compareTo(Objetos o);
    
    /**
     * Retorna una lista con los nombre de los atributos de la clase del parametro
     * Se usa para crear las cabeceras de las tablas que listan objetos
     * @param paquete_clase
     * @return 
     */
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
