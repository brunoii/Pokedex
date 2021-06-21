package principal;

import Controlador.*;
import baseDeDatos.Conexion;


/**
 *
 * @author BIIbr
 */
public class Main {

   
    public static void main(String[] args) {   
        //Conexion con = new Conexion();
        Controlador controlador = new Controlador();
        controlador.ejecutar();
    }
    
}
