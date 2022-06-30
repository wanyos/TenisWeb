
package modelo;

import java.util.List;


public class Pares extends Objetos {
    
    private int id_pares;
    private String nombre_j1;
    private int id_j1;
    private String nombre_j2;
    private int id_j2;

    public Pares(int id_pares, String nombre_j1, int id_j1, String nombre_j2, int id_j2) {
        this.id_pares = id_pares;
        this.nombre_j1 = nombre_j1;
        this.id_j1 = id_j1;
        this.nombre_j2 = nombre_j2;
        this.id_j2 = id_j2;
    }

    public Pares(int id_pares) {
        this.id_pares = id_pares;
    }

    @Override
    public int getId() {
        return this.id_pares;
    }

    public void setIdPares(int id_pares) {
        this.id_pares = id_pares;
    }

    public String getNombreJ1() {
        return nombre_j1;
    }

    public void setNombreJ1(String nombre_j1) {
        this.nombre_j1 = nombre_j1;
    }

    public int getIdJ1() {
        return id_j1;
    }

    public void setIdJ1(int id_j1) {
        this.id_j1 = id_j1;
    }

    public String getNombreJ2() {
        return nombre_j2;
    }

    public void setNombreJ2(String nombre_j2) {
        this.nombre_j2 = nombre_j2;
    }

    public int getIdJ2() {
        return id_j2;
    }

    public void setIdJ2(int id_j2) {
        this.id_j2 = id_j2;
    }

    @Override
    public List<String> getNombresCampos() {
        List<String> lista_campos = super.getListaCampos("modelo.Pares");
        return lista_campos;
    }

    
    
    
    
}
