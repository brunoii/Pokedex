/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import java.util.ArrayList;
import modelo.Pokemon;
import modelo.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BIIbr
 */
public class ConexionTest {
    
    public ConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of agregarUsuario method, of class Conexion.
     */
    @Test
    public void testAgregarUsuario() {
        System.out.println("agregarUsuario");
        Usuario usuario = new Usuario("nombre","pasword");
        Conexion instance = new Conexion();
        instance.agregarUsuario(usuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciarSesion method, of class Conexion.
     */
    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String usuario = "";
        String clave = "";
        Conexion instance = new Conexion();
        int expResult = 0;
        int result = instance.iniciarSesion(usuario, clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarTipos method, of class Conexion.
     */
    @Test
    public void testBuscarTipos() {
        System.out.println("buscarTipos");
        Conexion instance = new Conexion();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.buscarTipos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPokemon method, of class Conexion.
     */
    @Test
    public void testAgregarPokemon() {
        System.out.println("agregarPokemon");
        Pokemon pokemon = null;
        Conexion instance = new Conexion();
        instance.agregarPokemon(pokemon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPokemones method, of class Conexion.
     */
    @Test
    public void testBuscarPokemones() {
        System.out.println("buscarPokemones");
        Conexion instance = new Conexion();
        ArrayList<Pokemon> expResult = null;
        ArrayList<Pokemon> result = instance.buscarPokemones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPokemonDeUsuario method, of class Conexion.
     */
    @Test
    public void testAgregarPokemonDeUsuario() {
        System.out.println("agregarPokemonDeUsuario");
        Pokemon pokemon = null;
        int idUsuario = 0;
        int nivel = 0;
        Conexion instance = new Conexion();
        instance.agregarPokemonDeUsuario(pokemon, idUsuario, nivel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPokemonesUsuarios method, of class Conexion.
     */
    @Test
    public void testBuscarPokemonesUsuarios() {
        System.out.println("buscarPokemonesUsuarios");
        int idUsuario = 0;
        Conexion instance = new Conexion();
        ArrayList<Pokemon> expResult = null;
        ArrayList<Pokemon> result = instance.buscarPokemonesUsuarios(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarNombreUsuario method, of class Conexion.
     */
    @Test
    public void testBuscarNombreUsuario() {
        System.out.println("buscarNombreUsuario");
        int id = 0;
        Conexion instance = new Conexion();
        String expResult = "";
        String result = instance.buscarNombreUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPokemon method, of class Conexion.
     */
    @Test
    public void testBuscarPokemon() {
        System.out.println("buscarPokemon");
        int id = 0;
        Conexion instance = new Conexion();
        Pokemon expResult = null;
        Pokemon result = instance.buscarPokemon(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPokemon method, of class Conexion.
     */
    @Test
    public void testModificarPokemon() {
        System.out.println("modificarPokemon");
        Pokemon pokemon = null;
        Conexion instance = new Conexion();
        instance.modificarPokemon(pokemon);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
