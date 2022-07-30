
package modelo;

import java.util.List;


public class Jugador extends Objetos implements Comparable<Objetos> {

    private int id_jugador;
    private String nombre, apellido, comentario;

    public Jugador() { }
    
    public Jugador(int id_jugador, String nombre, String apellido, String comentario) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.comentario = comentario;
    }

    public Jugador(String nombre, String apellido, String comentario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.comentario = comentario;
    }

    public Jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    @Override
    public int getId() {
        return this.id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getComentario() {
        return comentario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    @Override
    public List<String> getNombresCampos(){
        List<String> lista_campos = super.getListaCampos("modelo.Jugador");
        return lista_campos;
    }

    

    @Override
    public int compareTo(Objetos o) {
       if(o.getId() > getId()){
            return -1;
        } else if(o.getId() < getId()){
            return 1;
        }
        return 0;
    }
    

}
