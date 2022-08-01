
package modelo;

import java.time.LocalDate;
import java.util.List;


public class Partido extends Objetos {
    
    private int idPartido;
    private LocalDate fecha;
    private int idPares;
    private String jugador1, jugador2;
    private int pagaj1, pagaj2;
    private int idBono1, idBono2;
    private String comentario;

    public Partido(){}
    
     public Partido(int id, LocalDate fecha, int id_pares, String jugador1, String jugador2, int pagaj1, int pagaj2, int id_bono1, int id_bono2, String comentario) {
        this.idPartido = id;
        this.fecha = fecha;
        this.idPares = id_pares;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.pagaj1 = pagaj1;
        this.pagaj2 = pagaj2;
        this.idBono1 = id_bono1;
        this.idBono2 = id_bono2;
        this.comentario = comentario;
    }
    
    public Partido(int id_partido, LocalDate fecha, int id_pares, int pagaj1, int pagaj2, int id_bono1, int id_bono2, String comentario) {
        this.idPartido = id_partido;
        this.fecha = fecha;
        this.idPares = id_pares;
        this.pagaj1 = pagaj1;
        this.pagaj2 = pagaj2;
        this.idBono1 = id_bono1;
        this.idBono2 = id_bono2;
        this.comentario = comentario;
    }

    public Partido(LocalDate fecha, int id_pares, int pagaj1, int pagaj2, int id_bono1, int id_bono2, String comentario) {
        this.fecha = fecha;
        this.idPares = id_pares;
        this.pagaj1 = pagaj1;
        this.pagaj2 = pagaj2;
        this.idBono1 = id_bono1;
        this.idBono2 = id_bono2;
        this.comentario = comentario;
    }
 

    public Partido(int id_partido) {
        this.idPartido = id_partido;
    }

    @Override
    public int getId() {
        return idPartido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdPares() {
        return idPares;
    }

    public int getPagaj1() {
        return pagaj1;
    }

    public int getPagaj2() {
        return pagaj2;
    }

    public String getJugador1() {
        return jugador1;
    }
    
    public String getComentario(){
        return comentario;
    }
    

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public int getIdBono1() {
        return idBono1;
    }

    public void setIdBono1(int id_bono1) {
        this.idBono1 = id_bono1;
    }
    
    public int getIdBono2() {
        return idBono2;
    }

    public void setIdBono2(int id_bono2) {
        this.idBono2 = id_bono2;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdPares(int id_pares) {
        this.idPares = id_pares;
    }

    public void setPagaj1(int pagaj1) {
        this.pagaj1 = pagaj1;
    }

    public void setPagaj2(int pagaj2) {
        this.pagaj2 = pagaj2;
    }
    
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    
   
    
    
    @Override
    public List<String> getNombresCampos(){
        List<String> lista_campos = super.getListaCampos("modelo.Partido");
        return lista_campos;
    }

    @Override
    public int compareTo(Objetos o) {
       Partido aux = (Partido) o;
       if(aux.getFecha().isAfter(getFecha())){
           return 1;
       } else if(aux.getFecha().isBefore(getFecha())){
           return -1;
       }
        return 0;
    }
    
    
}
