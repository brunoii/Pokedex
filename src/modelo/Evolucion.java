package modelo;

import java.util.ArrayList;

/**
 *
 * @author BIIbr
 */
public class Evolucion {
    private String _nombre;
    private int _nivelEvolucion;
    private ArrayList<Tipo> _tipos;
    private ArrayList<Habilidad> _habilidades;
    
    
    public Evolucion(){
        
    }
    
    public Evolucion(String nombre, int nivelEvolucion, ArrayList<Tipo> tipos, ArrayList<Habilidad> habilidades){
        this._nombre = nombre;
        this._nivelEvolucion = nivelEvolucion;
        this._tipos = tipos;
        this._habilidades = habilidades;
        
    }    

    public String getNombre() {
        return _nombre;
    }

    public int getNivelEvolucion() {
        return _nivelEvolucion;
    }

    public ArrayList<Tipo> getTipos() {
        return _tipos;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return _habilidades;
    }
    
}
