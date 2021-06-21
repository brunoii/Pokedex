
package Controlador;

import baseDeDatos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;

/**
 *
 * @author BIIbr
 */
public class Controlador implements ActionListener{
    private int _IdUsuarioLogueado;
    private Conexion _conexion;
    private VPrincipal _vistaPrincipal;
    private VAgregarPokemon _vistaAgregarPokemon;
    private VLogin _vistaLogin;
    private VListadoPokemon _vistaListadoPokemon;
    private VCargarPokemon _vistaCargaPokemon;
    private VRegistrarse _vistaRegistrarse;
    private VAgregarEvolucion _vistaAgregarEvolucion;
    private VListadoPokemonUsuario _vistaListadoPokemonUsuario;
    private VModificarPokemon _vistaModificarPokemon;
       
    
    public Controlador(){
        _conexion = new Conexion();
        _vistaPrincipal = new VPrincipal();
        _vistaPrincipal.vistaSesionCerrada();
    }
    
    public void ejecutar(){
        _vistaPrincipal.setControlador(this);
        _vistaPrincipal.ejecutar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Opcion agregar del menu pokemon
        if(e.getActionCommand().equals(_vistaPrincipal.OP_AGREGAR)){
            _vistaAgregarPokemon = new VAgregarPokemon();
            _vistaPrincipal.getEscritorio().add(_vistaAgregarPokemon);
            _vistaAgregarPokemon.setControlador(this);
            ArrayList<Pokemon> pokemones = new ArrayList<>();
            pokemones = _conexion.buscarPokemones();
            _vistaAgregarPokemon.ejecutar(pokemones);              
        }
        //Opcion mostrar todos del menu Buscar
        if(e.getActionCommand().equals(_vistaPrincipal.OP_MOSTRARTODOS)){
            _vistaListadoPokemon = new VListadoPokemon();
            _vistaPrincipal.getEscritorio().add(_vistaListadoPokemon);
            _vistaListadoPokemon.setControlador(this);
            ArrayList<Pokemon> pokemones = new ArrayList<>();
            ArrayList<String[]> lineas = new ArrayList<>();
            pokemones = _conexion.buscarPokemones();
            for(Pokemon pokemon : pokemones){           
                String linea[] = new String[7];
                linea[0] = String.valueOf(pokemon.getId());
                linea[1] = pokemon.getNombre();
                linea[2] = ""+pokemon.getNivel();
                linea[3] = pokemon.getTipos().get(0).getNombre();
                linea[4] = pokemon.getHabilidades().get(0).getNombre();
                linea[5] = pokemon.getEvoluciones().get(0).getNombre();
                linea[6] = ""+pokemon.getEvoluciones().get(0).getNivelEvolucion();
                lineas.add(linea);
            }              
           
            _vistaListadoPokemon.ejecutar(lineas,_conexion.buscarTipos());
        }
        //Opcion cargar del menu Buscar
        if(e.getActionCommand().equals(_vistaPrincipal.OP_CREAR)){
            _vistaCargaPokemon = new VCargarPokemon();
            _vistaPrincipal.getEscritorio().add(_vistaCargaPokemon);
            _vistaCargaPokemon.setControlador(this);
            _vistaCargaPokemon.ejecutar(_conexion.buscarTipos());
        }
        //Opcion Iniciar sesion del menu Ingresar
        if(e.getActionCommand().equals(_vistaPrincipal.OP_INICIARSESION)){
            _vistaLogin = new VLogin();
            _vistaPrincipal.getEscritorio().add(_vistaLogin);
            _vistaLogin.setControlador(this);
            _vistaLogin.ejecutar();
        }
        //Boton Iniciar de la ventana login 
        if(e.getActionCommand().equals(_vistaLogin.BTN_INGRESAR)){
            String usuario = _vistaLogin.getTxtUsuario().getText().trim();
            String clave = _vistaLogin.getTxtClave().getText().trim();            
            _IdUsuarioLogueado = _conexion.iniciarSesion(usuario, clave);
            if(_IdUsuarioLogueado!=0){
                _vistaPrincipal.vistaSesionIniciada();
                _vistaPrincipal.setTitle("POKEDEX - USUARIO: " + usuario);
                _vistaLogin.dispose();
                
            }else{
                JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos");
                _vistaLogin.getTxtUsuario().setText("");
                _vistaLogin.getTxtClave().setText("");         
            }                 
        }
        //Opcion Registrar del menu Ingresar
        if(e.getActionCommand().equals(_vistaPrincipal.OP_REGISTRASE)){
            _vistaRegistrarse = new VRegistrarse();
            _vistaPrincipal.getEscritorio().add(_vistaRegistrarse);
            _vistaRegistrarse.setControlador(this);
            _vistaRegistrarse.ejecutar();             
        }
        //Boton registrarse de la vista registrar
        if(e.getActionCommand().equals(_vistaRegistrarse.BTN_REGISTRAR)){
            Usuario usuario = new Usuario(_vistaRegistrarse.getTxtUsuario().getText().trim(),_vistaRegistrarse.getTxtClave().getText().trim());
            _conexion.agregarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Registro realizado correctamente");
            _vistaRegistrarse.dispose();            
        }
        //Opcion cerrar sesion del menu ingresar
        if(e.getActionCommand().equals(_vistaPrincipal.OP_CERRARSESION)){
            _vistaPrincipal.vistaSesionCerrada();
            _vistaPrincipal.setTitle("POKEDEX");
            _vistaLogin.dispose();
        }
        //Boton agregar tipo de la vista cargar pokemon
        if(e.getActionCommand().equals(_vistaCargaPokemon.BTN_AGREGARTIPO)){   
            String linea[] = new String[1];
            linea[0] = _vistaCargaPokemon.getCbTipo().getSelectedItem().toString(); 
            _vistaCargaPokemon.cargarTablaTipo(linea);            
        }
        //Boton agregar habilidad de la vista cargar pokemon
        if(e.getActionCommand().equals(_vistaCargaPokemon.BTN_AGREGARHABILIDAD)){   
            String linea[] = new String[1];
            linea[0] = _vistaCargaPokemon.getTxtNombreHabilidad().getText().trim();
            _vistaCargaPokemon.cargarTablaHabilidad(linea);
        }
        //Boton agregar evolucion de la vista cargar pokemon
        if(e.getActionCommand().equals(_vistaCargaPokemon.BTN_AGREGAREVOLUCION)){               
            _vistaAgregarEvolucion = new VAgregarEvolucion();
            _vistaPrincipal.getEscritorio().add(_vistaAgregarEvolucion);
            _vistaAgregarEvolucion.setControlador(this);
            _vistaAgregarEvolucion.ejecutar(_conexion.buscarTipos());
        }
        //Boton agregar tipo de la vista agregar evolucion
        if(e.getActionCommand().equals(_vistaAgregarEvolucion.BTN_AGREGARTIPOEVOLUCION)){   
            String linea[] = new String[1];
            linea[0] = _vistaAgregarEvolucion.getCbTipo().getSelectedItem().toString(); 
            _vistaAgregarEvolucion.cargarTablaTipo(linea);            
        }
        //Boton agregar habilidad de la vista agregar evolucion
        if(e.getActionCommand().equals(_vistaAgregarEvolucion.BTN_AGREGARHABILIDADEVOLUCION)){   
            String linea[] = new String[1];
            linea[0] = _vistaAgregarEvolucion.getTxtNombreHabilidad().getText().trim();
            _vistaAgregarEvolucion.cargarTablaHabilidad(linea);
        }
        //Boton agregar evolucion de la vista agregar evolucion
        if(e.getActionCommand().equals(_vistaAgregarEvolucion.BTN_AGREGAREVOLUCIONES)){   
            String linea[] = new String[4];
            linea[0] = _vistaAgregarEvolucion.getTxtNombrePokemon().getText().trim();
            linea[1] = String.valueOf(_vistaAgregarEvolucion.getSpNivelNecesario().getValue());
            linea[2] = String.valueOf(_vistaAgregarEvolucion.getTablaHabilidad().getValueAt(0,0));
            linea[3] = String.valueOf(_vistaAgregarEvolucion.getTablaTipo().getValueAt(0,0));            
            _vistaCargaPokemon.cargarTablaEvolucion(linea);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            _vistaAgregarEvolucion.dispose();
        }
        //Boton guardar de la vista cargar pokemon
        if(e.getActionCommand().equals(_vistaCargaPokemon.BTN_GUARDAR)){
            ArrayList<Tipo> tipos = new ArrayList<>();
            ArrayList<Habilidad> habilidades = new ArrayList<>();            
            ArrayList<Evolucion> evoluciones = new ArrayList<>();
            
            //parametro nombre pokemon  
            String nombre = _vistaCargaPokemon.getTxtNombrePokemon().getText().trim();    
            
            //parametro tipo pokemon
            int t = _vistaCargaPokemon.getTablaTipo().getRowCount();
            for(int i=0 ; i<t ; i++){
                Tipo tmp = new Tipo(String.valueOf(_vistaCargaPokemon.getTablaTipo().getValueAt(i, 0)));
                tipos.add(tmp);
            }
            
            //parametro habilidad pokemon
            int h = _vistaCargaPokemon.getTablaHabilidad().getRowCount();
            for(int i=0 ; i<h ; i++){
                Habilidad tmp = new Habilidad(String.valueOf(_vistaCargaPokemon.getTablaHabilidad().getValueAt(i, 0)));
                habilidades.add(tmp);
            }
            
            //parametro evoluciones pokemon
            int n = _vistaCargaPokemon.getTablaEvolucion().getRowCount();
            for(int i=0 ; i<n ; i++){                
                ArrayList<Tipo> tiposEvoluciones = new ArrayList<>();
                ArrayList<Habilidad> habilidadesEvoluciones = new ArrayList<>();
                String nombretmp = String.valueOf(_vistaCargaPokemon.getTablaEvolucion().getValueAt(i, 0));
                int niveltmp = Integer.valueOf(String.valueOf(_vistaCargaPokemon.getTablaEvolucion().getValueAt(i, 1)));
                Tipo tipoTmp = new Tipo(String.valueOf(_vistaCargaPokemon.getTablaEvolucion().getValueAt(i, 2)));
                tiposEvoluciones.add(tipoTmp);
                Habilidad habilidadTmp = new Habilidad(String.valueOf(_vistaCargaPokemon.getTablaEvolucion().getValueAt(i, 3)));
                habilidadesEvoluciones.add(habilidadTmp);
                Evolucion tmp = new Evolucion(nombretmp,niveltmp,tiposEvoluciones,habilidadesEvoluciones);
                evoluciones.add(tmp);                
            }
            Pokemon pokemon = new Pokemon(nombre,tipos,habilidades,evoluciones);
            _conexion.agregarPokemon(pokemon);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            _vistaCargaPokemon.dispose();
        }       
        //Boton agregar de la vista agregar pokemon
        if(e.getActionCommand().equals(_vistaAgregarPokemon.BTN_AGREGAR)){ 
            Pokemon pokemon = new Pokemon();
            pokemon = (Pokemon) _vistaAgregarPokemon.getCbPokemon().getSelectedItem();
            int nivel = (int) _vistaAgregarPokemon.getSpNivelActual().getValue();
            _conexion.agregarPokemonDeUsuario(pokemon,_IdUsuarioLogueado,nivel);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            _vistaAgregarPokemon.dispose();
        }    
        //Opcion mostrar todos del menu Pokemon
        if(e.getActionCommand().equals(_vistaPrincipal.OP_MOSTRAROBTENIDOS)){
            _vistaListadoPokemonUsuario = new VListadoPokemonUsuario();
            _vistaPrincipal.getEscritorio().add(_vistaListadoPokemonUsuario);
            _vistaListadoPokemonUsuario.setControlador(this);
            ArrayList<Pokemon> pokemones = new ArrayList<>();
            ArrayList<String[]> lineas = new ArrayList<>();
            pokemones = _conexion.buscarPokemonesUsuarios(_IdUsuarioLogueado);
            for(Pokemon pokemon : pokemones){           
                String linea[] = new String[7];
                linea[0] = String.valueOf(pokemon.getId());
                linea[1] = pokemon.getNombre();
                linea[2] = ""+pokemon.getNivel();
                linea[3] = pokemon.getTipos().get(0).getNombre();
                linea[4] = pokemon.getHabilidades().get(0).getNombre();
                linea[5] = pokemon.getEvoluciones().get(0).getNombre();
                linea[6] = ""+pokemon.getEvoluciones().get(0).getNivelEvolucion();
                lineas.add(linea);
            }
            _vistaListadoPokemonUsuario.setLblNombreUsuario(_conexion.buscarNombreUsuario(_IdUsuarioLogueado));
            _vistaListadoPokemonUsuario.ejecutar(lineas,_conexion.buscarTipos());
        }
        //Boton modificar de la vista listar pokemones
        if(e.getActionCommand().equals(_vistaListadoPokemon.BTN_MODIFICAR)){
            _vistaModificarPokemon = new VModificarPokemon();
            _vistaPrincipal.getEscritorio().add(_vistaModificarPokemon);
            _vistaModificarPokemon.setControlador(this);
            _vistaModificarPokemon.ejecutar(_conexion.buscarTipos());
            int idPokemon = _vistaListadoPokemon.recuperarId();
            Pokemon pokemon = _conexion.buscarPokemon(idPokemon);
            _vistaModificarPokemon.getTxtNombrePokemon().setText(pokemon.getNombre());
            _vistaModificarPokemon.getTxtNombreHabilidad().setText(pokemon.getHabilidades().get(0).getNombre());
            String linea[] = new String[4];
            linea[0] = String.valueOf(pokemon.getEvoluciones().get(0).getNombre());
            linea[1] = String.valueOf(pokemon.getEvoluciones().get(0).getNivelEvolucion());
            linea[2] = pokemon.getTipos().get(0).getNombre();
            linea[3] = pokemon.getHabilidades().get(0).getNombre(); 
            _vistaModificarPokemon.cargarTablaEvolucion(linea);
        }
        //Boton agregar habilidad de la vista modificar pokemon
        if(e.getActionCommand().equals(_vistaModificarPokemon.BTN_AGREGARHABILIDADMODIFICACION)){   
            String linea[] = new String[1];
            linea[0] = _vistaModificarPokemon.getTxtNombreHabilidad().getText().trim();
            _vistaModificarPokemon.cargarTablaHabilidad(linea);
        }
        //Boton agregar evolucion de la vista modificar pokemon
        if(e.getActionCommand().equals(_vistaModificarPokemon.BTN_AGREGAREVOLUCIONMODIFICACION)){               
            _vistaAgregarEvolucion = new VAgregarEvolucion();
            _vistaPrincipal.getEscritorio().add(_vistaAgregarEvolucion);
            _vistaAgregarEvolucion.setControlador(this);
            _vistaAgregarEvolucion.ejecutar(_conexion.buscarTipos());
        }
        //Boton agregar tipo de la vista modificar pokemon
        if(e.getActionCommand().equals(_vistaModificarPokemon.BTN_AGREGARTIPOMODIFICACION)){   
            String linea[] = new String[1];
            linea[0] = _vistaModificarPokemon.getCbTipo().getSelectedItem().toString(); 
            _vistaModificarPokemon.cargarTablaTipo(linea);            
        }
        //Boton guardar de la vista modificar pokemon
        if(e.getActionCommand().equals(_vistaModificarPokemon.BTN_GUARDARMODIFICACION)){
            int idPokemon = _vistaListadoPokemon.recuperarId();
            ArrayList<Tipo> tipos = new ArrayList<>();
            ArrayList<Habilidad> habilidades = new ArrayList<>();            
            ArrayList<Evolucion> evoluciones = new ArrayList<>();
            
            //parametro nombre pokemon  
            String nombre = _vistaModificarPokemon.getTxtNombrePokemon().getText().trim();    
            
            //parametro tipo pokemon
            int t = _vistaModificarPokemon.getTablaTipo().getRowCount();
            for(int i=0 ; i<t ; i++){
                Tipo tmp = new Tipo(String.valueOf(_vistaModificarPokemon.getTablaTipo().getValueAt(i, 0)));
                tipos.add(tmp);
            }
            
            //parametro habilidad pokemon
            int h = _vistaModificarPokemon.getTablaHabilidad().getRowCount();
            for(int i=0 ; i<h ; i++){
                Habilidad tmp = new Habilidad(String.valueOf(_vistaModificarPokemon.getTablaHabilidad().getValueAt(i, 0)));
                habilidades.add(tmp);
            }
            
            //parametro evoluciones pokemon
            int n = _vistaModificarPokemon.getTablaEvolucion().getRowCount();
            for(int i=0 ; i<n ; i++){                
                ArrayList<Tipo> tiposEvoluciones = new ArrayList<>();
                ArrayList<Habilidad> habilidadesEvoluciones = new ArrayList<>();
                String nombretmp = String.valueOf(_vistaModificarPokemon.getTablaEvolucion().getValueAt(i, 0));
                int niveltmp = Integer.valueOf(String.valueOf(_vistaModificarPokemon.getTablaEvolucion().getValueAt(i, 1)));
                Tipo tipoTmp = new Tipo(String.valueOf(_vistaModificarPokemon.getTablaEvolucion().getValueAt(i, 2)));
                tiposEvoluciones.add(tipoTmp);
                Habilidad habilidadTmp = new Habilidad(String.valueOf(_vistaModificarPokemon.getTablaEvolucion().getValueAt(i, 3)));
                habilidadesEvoluciones.add(habilidadTmp);
                Evolucion tmp = new Evolucion(nombretmp,niveltmp,tiposEvoluciones,habilidadesEvoluciones);
                evoluciones.add(tmp);                
            }
            Pokemon pokemon = new Pokemon(nombre,tipos,habilidades,evoluciones);
            pokemon.setId(idPokemon);
            
            _conexion.modificarPokemon(pokemon);
            JOptionPane.showMessageDialog(null, "Registro realizado correctamente");
            _vistaModificarPokemon.dispose();
        }
        //Boton buscar de la vista mostrar todos
        if(e.getActionCommand().equals(_vistaListadoPokemon.BTN_BUSCAR)){
            String valor = _vistaListadoPokemon.getTxtNombrePokemon().getText();             
            _vistaListadoPokemon.cargarBusqueda(_conexion.busqueda(valor));           
        }
    }    
    
}
