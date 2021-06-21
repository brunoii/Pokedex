package baseDeDatos;
import java.sql.*;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author BIIbr
 */
public class Conexion {      
    private String url = "jdbc:mysql://localhost/db_pokedex"; 
    private String user = "root";
    private String pass = "";
    public Conexion(){       
        
    }
    public void agregarUsuario(Usuario usuario){ //metodo para registrar ususarios en la db              
        try{
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("insert into usuarios values(?,?,?)");
            pst.setString(1, "0");
            pst.setString(2, usuario.getNombre());
            pst.setString(3, usuario.getClave());
            pst.executeUpdate();
            
        }catch(SQLException ex){
        }
    }
    public int iniciarSesion(String usuario, String clave){//metodo para iniciar sesion
        int id = 0;
        try{            
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("select * from usuarios where Usuario = ? and Clave = ?");
            pst.setString(1, usuario);
            pst.setString(2, clave);
            ResultSet rs = pst.executeQuery();           
            if(rs.next()){                
                id = rs.getInt("ID");
            }
        }catch(SQLException ex){
        }
        return id;
    }
    public ArrayList<String> buscarTipos(){//metodo para buscar los tipos de pokemon en la db
        ArrayList<String> listaTipo = new ArrayList<>();
        try {
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("select * from tipos");
            ResultSet rs = pst.executeQuery(); 
            while(rs.next()){
                listaTipo.add(rs.getString("Nombre"));
            }            
        }catch (SQLException ex) {
        }
        return listaTipo;
    }
    public void agregarPokemon(Pokemon pokemon){//metodo para cargar pokemones en la db
        try{
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("insert into pokemones values(?,?,?)");
            pst.setString(1, "0");
            pst.setString(2, pokemon.getNombre());
            pst.setInt(3, pokemon.getNivel());
            pst.executeUpdate();
            
            pst = con.prepareStatement("select * from pokemones where Nombre = ?");
            pst.setString(1, pokemon.getNombre());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                //ingresar los tipos del pokemon
                pst = con.prepareStatement("insert into pokemontipos values(?,?,?)");                
                int t = pokemon.getTipos().size();
                for(int i=0 ; i<t ; i++){
                    pst.setString(1, "0");
                    pst.setString(2, pokemon.getTipos().get(i).getNombre());
                    pst.setInt(3, rs.getInt("ID"));
                    pst.executeUpdate();
                }                        
                
                //ingresar las habilidades del pokemon
                pst = con.prepareStatement("insert into habilidades values(?,?,?)");
                int h = pokemon.getHabilidades().size();
                for(int i=0 ; i<h ; i++){
                    pst.setString(1, "0");
                    pst.setString(2, pokemon.getHabilidades().get(i).getNombre());
                    pst.setInt(3, rs.getInt("ID"));
                    pst.executeUpdate();
                }
                
                //ingresar las evoluciones del pokemon
                pst = con.prepareStatement("insert into evoluciones values(?,?,?,?,?,?)");
                int e = pokemon.getEvoluciones().size();
                for(int i=0 ; i<e ; i++){
                    pst.setString(1, "0");
                    pst.setString(2, pokemon.getEvoluciones().get(i).getNombre());
                    pst.setInt(3, pokemon.getEvoluciones().get(i).getNivelEvolucion());
                    pst.setString(4, pokemon.getEvoluciones().get(i).getTipos().get(i).getNombre());
                    pst.setString(5, pokemon.getEvoluciones().get(i).getHabilidades().get(i).getNombre());
                    pst.setInt(6, rs.getInt("ID"));
                    pst.executeUpdate();   
                }
                
            }else{
            }            
        }catch(SQLException ex){
        }        
    }
    public ArrayList<Pokemon> buscarPokemones(){//Metodo para buscar los pokemones
            ArrayList<Pokemon> pokemones = new ArrayList<>();
            try {
                Connection con = DriverManager.getConnection(url,user,pass);
                PreparedStatement pst = con.prepareStatement("select * from pokemones");
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    //id pokemon
                    int id = rs.getInt("ID");
                    
                    //nombre pokemon
                    String nombre = rs.getString("Nombre");
                    
                    //nivel pokemon
                    int nivel = rs.getInt("Nivel");
                    
                    //parametro tipo
                    ArrayList<Tipo> tipos = new ArrayList<>();
                    pst = con.prepareStatement("select * from pokemontipos where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rst = pst.executeQuery();                    
                    while(rst.next()){
                        Tipo tipo = new Tipo(rst.getString("Nombre"));
                        tipos.add(tipo);
                    }                    
                    
                    //parametro habilidad
                    ArrayList<Habilidad> habilidades = new ArrayList<>();
                    pst = con.prepareStatement("select * from habilidades where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rsh = pst.executeQuery();
                    while(rsh.next()){
                        Habilidad habilidad = new Habilidad(rsh.getString("Nombre"));
                        habilidades.add(habilidad);
                    }
                    
                    //parametro evolucion
                    ArrayList<Evolucion> evoluciones = new ArrayList<>();
                    pst = con.prepareStatement("select * from evoluciones where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rse = pst.executeQuery();
                    while(rse.next()){
                        ArrayList<Tipo> tiposEvolucion = new ArrayList<>();
                        ArrayList<Habilidad> habilidadesEvolucion = new ArrayList<>();                    
                        String nombreEvolucion = rse.getString("Nombre");
                        int nivelRequerido = rse.getInt("NivelRequerido");
                        Tipo tipoEvolucion = new Tipo(rse.getString("Nombre"));
                        tiposEvolucion.add(tipoEvolucion);
                        Habilidad habilidadEvolucion = new Habilidad(rse.getString("Nombre"));
                        habilidadesEvolucion.add(habilidadEvolucion);
                        Evolucion evolucion = new Evolucion(nombreEvolucion, nivelRequerido, tiposEvolucion, habilidadesEvolucion);
                        
                        evoluciones.add(evolucion);
                    }                   
                    Pokemon pokemon = new Pokemon(nombre,tipos,habilidades,evoluciones);
                    pokemon.setId(id);
                    pokemones.add(pokemon);
                }
            }catch(Exception e) {
            }
            return pokemones;
    }
    public void agregarPokemonDeUsuario(Pokemon pokemon, int idUsuario, int nivel){//metodo para agregar los pokemones de los usuarios
        try {
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("insert into usuarios_pokemones values (?,?,?,?)");
            pst.setString(1, "0");
            pst.setInt(2, nivel);
            pst.setInt(3, idUsuario);
            pst.setInt(4, pokemon.getId());
            
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    public ArrayList<Pokemon> buscarPokemonesUsuarios(int idUsuario){//Metodo para buscar los pokemones del usuario
            ArrayList<Pokemon> pokemones = new ArrayList<>();
            try {
                Connection con = DriverManager.getConnection(url,user,pass);
                PreparedStatement pst = con.prepareStatement("select * from usuarios_pokemones where IDUsuario = ?");
                pst.setInt(1, idUsuario);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    //id pokemon
                    int idTablaUsuarios_Pokemon = rs.getInt("IDPokemon");
                    pst = con.prepareStatement("select * from pokemones where ID = ?");
                    pst.setInt(1, idTablaUsuarios_Pokemon);
                    ResultSet rsp = pst.executeQuery();
                    if(rsp.next()){
                        //id pokemon
                        int idTablaPokemon = rsp.getInt("ID");

                        //nombre pokemon
                        String nombre = rsp.getString("Nombre");

                        //nivel pokemon
                        int nivel = rsp.getInt("Nivel");

                        //parametro tipo
                        ArrayList<Tipo> tipos = new ArrayList<>();
                        pst = con.prepareStatement("select * from pokemontipos where IDPokemon = ?");
                        pst.setInt(1, idTablaPokemon);
                        ResultSet rst = pst.executeQuery();
                        if(rst.next()){
                            Tipo tipo = new Tipo(rst.getString("Nombre"));
                            tipos.add(tipo);
                        }                    

                        //parametro habilidad
                        ArrayList<Habilidad> habilidades = new ArrayList<>();
                        pst = con.prepareStatement("select * from habilidades where IDPokemon = ?");
                        pst.setInt(1, idTablaPokemon);
                        ResultSet rsh = pst.executeQuery();
                        if(rsh.next()){
                            Habilidad habilidad = new Habilidad(rsh.getString("Nombre"));
                            habilidades.add(habilidad);
                        }

                        //parametro evolucion
                        ArrayList<Evolucion> evoluciones = new ArrayList<>();
                        pst = con.prepareStatement("select * from evoluciones where IDPokemon = ?");
                        pst.setInt(1, idTablaPokemon);
                        ResultSet rse = pst.executeQuery();
                        if(rse.next()){
                            ArrayList<Tipo> tiposEvolucion = new ArrayList<>();
                            ArrayList<Habilidad> habilidadesEvolucion = new ArrayList<>();                    
                            String nombreEvolucion = rse.getString("Nombre");
                            int nivelRequerido = rse.getInt("NivelRequerido");
                            Tipo tipoEvolucion = new Tipo(rse.getString("Nombre"));
                            tiposEvolucion.add(tipoEvolucion);
                            Habilidad habilidadEvolucion = new Habilidad(rse.getString("Nombre"));
                            habilidadesEvolucion.add(habilidadEvolucion);
                            Evolucion evolucion = new Evolucion(nombreEvolucion, nivelRequerido, tiposEvolucion, habilidadesEvolucion);

                            evoluciones.add(evolucion);
                        }                   
                        Pokemon pokemon = new Pokemon(nombre,tipos,habilidades,evoluciones);
                        pokemon.setId(idTablaUsuarios_Pokemon);
                        pokemones.add(pokemon);                        
                    }
                }                    
            }catch(Exception e) {
            }
            return pokemones;
    }
    public String buscarNombreUsuario(int id){//metodo para buscar el nombre del usuario
        String nombre = "";
        try {
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("select * from usuarios where ID = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                nombre = rs.getString("Usuario");
            }
        } catch (Exception e) {
        }
        return nombre;
    }
    public Pokemon buscarPokemon(int id){//buscar pokemon
        Pokemon pokemon = new Pokemon();
        try {
            Connection con = DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("select * from pokemones where ID = ?");
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                    //id pokemon
                    int idP = rs.getInt("ID");
                    
                    //nombre pokemon
                    String nombre = rs.getString("Nombre");
                    
                    //nivel pokemon
                    int nivel = rs.getInt("Nivel");
                    
                    //parametro tipo
                    ArrayList<Tipo> tipos = new ArrayList<>();
                    pst = con.prepareStatement("select * from pokemontipos where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rst = pst.executeQuery();
                    if(rst.next()){
                        Tipo tipo = new Tipo(rst.getString("Nombre"));
                        tipos.add(tipo);
                    }                    
                    
                    //parametro habilidad
                    ArrayList<Habilidad> habilidades = new ArrayList<>();
                    pst = con.prepareStatement("select * from habilidades where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rsh = pst.executeQuery();
                    if(rsh.next()){
                        Habilidad habilidad = new Habilidad(rsh.getString("Nombre"));
                        habilidades.add(habilidad);
                    }
                    
                    //parametro evolucion
                    ArrayList<Evolucion> evoluciones = new ArrayList<>();
                    pst = con.prepareStatement("select * from evoluciones where IDPokemon = ?");
                    pst.setInt(1, id);
                    ResultSet rse = pst.executeQuery();
                    if(rse.next()){
                        ArrayList<Tipo> tiposEvolucion = new ArrayList<>();
                        ArrayList<Habilidad> habilidadesEvolucion = new ArrayList<>();                    
                        String nombreEvolucion = rse.getString("Nombre");
                        int nivelRequerido = rse.getInt("NivelRequerido");
                        Tipo tipoEvolucion = new Tipo(rse.getString("Nombre"));
                        tiposEvolucion.add(tipoEvolucion);
                        Habilidad habilidadEvolucion = new Habilidad(rse.getString("Nombre"));
                        habilidadesEvolucion.add(habilidadEvolucion);
                        Evolucion evolucion = new Evolucion(nombreEvolucion, nivelRequerido, tiposEvolucion, habilidadesEvolucion);
                        
                        evoluciones.add(evolucion);
                    }                   
                    Pokemon pok = new Pokemon(nombre,tipos,habilidades,evoluciones);
                    pok.setId(idP);
                    pokemon = pok;
            }
        } catch (Exception e) {
        }
        
        return pokemon;
    }
    public void modificarPokemon(Pokemon pokemon){//modificar pokemon
        try{
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("update pokemones set Nombre = ?, Nivel = ? where ID = ?");
            pst.setString(1, pokemon.getNombre());
            pst.setInt(2, pokemon.getNivel());
            pst.setInt(3, pokemon.getId());
            pst.executeUpdate();
            
            //modificar los tipos del pokemon
            pst = con.prepareStatement("update pokemontipos set Nombre = ? where IDPokemon = ?");
            pst.setString(1, pokemon.getTipos().get(0).getNombre());
            pst.setInt(2, pokemon.getId());
            pst.executeUpdate();
            
            //modificar las habilidades del pokemon
            pst = con.prepareStatement("update habilidades set Nombre = ? where IDPokemon = ?");
            pst.setString(1, pokemon.getHabilidades().get(0).getNombre());
            pst.setInt(2, pokemon.getId());
            pst.executeUpdate();
                
            //modificar las evoluciones del pokemon
            pst = con.prepareStatement("update evoluciones set Nombre = ? , NivelRequerido = ? , Tipo = ?, Habilidad = ? where IDPokemon = ?");
            pst.setString(1, pokemon.getEvoluciones().get(0).getNombre());
            pst.setInt(2, pokemon.getEvoluciones().get(0).getNivelEvolucion());
            pst.setString(3, pokemon.getEvoluciones().get(0).getTipos().get(0).getNombre());
            pst.setString(4, pokemon.getEvoluciones().get(0).getHabilidades().get(0).getNombre());
            pst.setInt(2, pokemon.getId());
            pst.executeUpdate();          
            
        }catch(SQLException ex){
        }   
    }
    //metodo para hacer una busqueda en tiempo real
    public String[] busqueda(String valor){        
        String[] linea = new String[7];
        int idPokemon = 0;
        try {            
            Connection con = (Connection) DriverManager.getConnection(url,user,pass);
            PreparedStatement pst = con.prepareStatement("select * from pokemones where Nombre like '%"+valor+"%'");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                idPokemon = rs.getInt("ID");
                linea[0] = rs.getString("ID");
                linea[1] = rs.getString("Nombre");
                linea[2] = rs.getString("Nivel");
                pst = con.prepareStatement("select * from pokemontipos where IDPokemon = ?");
                pst.setInt(1,idPokemon);
                ResultSet rsTipo = pst.executeQuery();
                if(rsTipo.next()){
                    linea[3] = rsTipo.getString("Nombre");
                }
                pst = con.prepareStatement("select * from habilidades where IDPokemon = ?");
                pst.setInt(1,idPokemon);
                ResultSet rsHabilidad = pst.executeQuery();
                if(rsHabilidad.next()){
                    linea[4] = rsHabilidad.getString("Nombre");
                }
                pst = con.prepareStatement("select * from evoluciones where IDPokemon = ?");
                pst.setInt(1,idPokemon);
                ResultSet rsEvo = pst.executeQuery();
                if(rsEvo.next()){
                    linea[5] = rsEvo.getString("Nombre");
                    linea[6] = rsEvo.getString("NivelRequerido");
                } 
            }
        } catch (Exception e) {
        }
        return linea;
    }    
}
