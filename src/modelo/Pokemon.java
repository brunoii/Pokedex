
package modelo;

import java.util.ArrayList;

/**
 *
 * @author BIIbr
 */
public class Pokemon {
    private int id;
    private String _nombre;
    private ArrayList<Tipo> _tipos;
    private int _nivel = 1;
    private ArrayList<Habilidad> _habilidades;
    private ArrayList<Evolucion> _evoluciones;
    
    public Pokemon(){
        
    }
    
    public Pokemon(String nombre, ArrayList<Tipo> tipos, ArrayList<Habilidad> habilidades, ArrayList<Evolucion> evoluciones){
        this._nombre = nombre;
        this._tipos = tipos;
        this._habilidades = habilidades;
        this._evoluciones = evoluciones;
    }    

    public String getNombre() {
        return _nombre;
    }

    public ArrayList<Tipo> getTipos() {
        return _tipos;
    }

    public int getNivel() {
        return _nivel;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return _habilidades;
    }

    public ArrayList<Evolucion> getEvoluciones() {
        return _evoluciones;
    }

    @Override
    public String toString() {
        return this._nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
