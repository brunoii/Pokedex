package modelo;

import java.util.ArrayList;

/**
 *
 * @author BIIbr
 */
public class Usuario {
    private String _nombre;
    private String _clave;
    private ArrayList<Pokemon> pokemones;
    
    public Usuario(){
        
    }
    
    public Usuario(String nombre, String clave){
        this._nombre = nombre;
        this._clave = clave;    
        this.pokemones = new ArrayList<>();
    }
    public void agregarPokemon(Pokemon pokemon){
        pokemones.add(pokemon);
    }
    public String getNombre() {
        return _nombre;
    }

    public String getClave() {
        return _clave;
    }

    public ArrayList<Pokemon> getPokemones() {
        return pokemones;
    }
    
    
}
