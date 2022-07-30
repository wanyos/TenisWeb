
package modelo;

import java.util.List;


public class Pares extends Objetos {
    
    private int id_pares;
    private String nombreJ1;
    private int idJ1;
    private String nombreJ2;
    private int idJ2;

    
    
    public Pares() { }

 
    public Pares(int id_pares, String nombre_j1, int id_j1, String nombre_j2, int id_j2) {
        this.id_pares = id_pares;
        this.nombreJ1 = nombre_j1;
        this.idJ1 = id_j1;
        this.nombreJ2 = nombre_j2;
        this.idJ2 = id_j2;
    }

    public Pares(String nombreJ1, int idJ1, String nombreJ2, int idJ2) {
        this.nombreJ1 = nombreJ1;
        this.idJ1 = idJ1;
        this.nombreJ2 = nombreJ2;
        this.idJ2 = idJ2;
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
        return nombreJ1;
    }

    public void setNombreJ1(String nombre_j1) {
        this.nombreJ1 = nombre_j1;
    }

    public int getIdJ1() {
        return idJ1;
    }

    public void setIdJ1(int id_j1) {
        this.idJ1 = id_j1;
    }

    public String getNombreJ2() {
        return nombreJ2;
    }

    public void setNombreJ2(String nombre_j2) {
        this.nombreJ2 = nombre_j2;
    }

    public int getIdJ2() {
        return idJ2;
    }

    public void setIdJ2(int id_j2) {
        this.idJ2 = id_j2;
    }

    @Override
    public List<String> getNombresCampos() {
        List<String> lista_campos = super.getListaCampos("modelo.Pares");
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
