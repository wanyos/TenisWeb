
package modelo;

import java.time.LocalDate;
import java.util.List;


public class Partido extends Objetos {
    
    private int id_partido;
    private LocalDate fecha;
    private int id_pares, pagaj1, pagaj2;

    public Partido(){}
    
    public Partido(int id_partido, LocalDate fecha, int id_pares, int pagaj1, int pagaj2) {
        this.id_partido = id_partido;
        this.fecha = fecha;
        this.id_pares = id_pares;
        this.pagaj1 = pagaj1;
        this.pagaj2 = pagaj2;
    }

    public Partido(LocalDate fecha, int id_pares, int pagaj1, int pagaj2) {
        this.fecha = fecha;
        this.id_pares = id_pares;
        this.pagaj1 = pagaj1;
        this.pagaj2 = pagaj2;
    }

    public Partido(int id_partido) {
        this.id_partido = id_partido;
    }

    @Override
    public int getId() {
        return id_partido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getIdPares() {
        return id_pares;
    }

    public int getPagaj1() {
        return pagaj1;
    }

    public int getPagaj2() {
        return pagaj2;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdPares(int id_pares) {
        this.id_pares = id_pares;
    }

    public void setPagaj1(int pagaj1) {
        this.pagaj1 = pagaj1;
    }

    public void setPagaj2(int pagaj2) {
        this.pagaj2 = pagaj2;
    }
    
    
    @Override
    public List<String> getNombresCampos(){
        List<String> lista_campos = super.getListaCampos("modelo.Partido");
        return lista_campos;
    }
}
