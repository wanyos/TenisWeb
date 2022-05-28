
package modelo;

import java.time.LocalDate;
import java.util.List;


public class Bono extends Objetos {
    
    private int id_bono;
    private LocalDate fecha;
    private String nombre;
    private int id_jugador;
    private int horas;
    private boolean estado;

    public Bono(){}
    
    public Bono(int id_bono, LocalDate fecha, String nombre, int id_jugador, int horas, boolean estado) {
        this.id_bono = id_bono;
        this.fecha = fecha;
        this.nombre = nombre;
        this.id_jugador = id_jugador;
        this.horas = horas;
        this.estado = estado;
    }

    public Bono(int id_bono) {
        this.id_bono = id_bono;
    }

    @Override
    public int getId() {
        return id_bono;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdJugador() {
        return id_jugador;
    }

    public int getHoras() {
        return horas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setIdBono(int id_bono) {
        this.id_bono = id_bono;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdJugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public List<String> getNombresCampos(){
        List<String> lista_campos = super.getListaCampos("modelo.Bono");
        return lista_campos;
    }
    
    
}
