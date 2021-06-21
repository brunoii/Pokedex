package vista;

import Controlador.*;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;

/**
 *
 * @author BIIbr
 */
public class VPrincipal extends javax.swing.JFrame {
    public static final String OP_INICIARSESION = "Iniciar sesion"; 
    public static final String OP_CERRARSESION = "Cerrar la sesion"; 
    public static final String OP_REGISTRASE = "Crear un nuevo usuario"; 
    public static final String OP_AGREGAR = "Agregar un nuevo pokemon"; 
    public static final String OP_MOSTRAROBTENIDOS = "Listar los pokemones obtenidos para el usuario"; 
    public static final String OP_MOSTRARTODOS = "Listar todos los pokemones cargados en la db"; 
    public static final String OP_CREAR = "Crear un nuevo pokemon en la db"; 
    
    
    public VPrincipal() {
        initComponents();
    }
    public void ejecutar(){
        this.setTitle("POKEDEX");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.vistaSesionCerrada();
    }
    
    public void setControlador(Controlador controlador){
        this.opIniciarSesion.setActionCommand(OP_INICIARSESION);
        this.opCerrarSesion.setActionCommand(OP_CERRARSESION);
        this.opRegistrarse.setActionCommand(OP_REGISTRASE);
        this.opAgregar.setActionCommand(OP_AGREGAR);
        this.opListaObtenidos.setActionCommand(OP_MOSTRAROBTENIDOS);
        this.opMostrarTodos.setActionCommand(OP_MOSTRARTODOS);
        this.opCargar.setActionCommand(OP_CREAR);
        this.opIniciarSesion.addActionListener(controlador);
        this.opCerrarSesion.addActionListener(controlador);
        this.opRegistrarse.addActionListener(controlador);
        this.opAgregar.addActionListener(controlador);    
        this.opListaObtenidos.addActionListener(controlador);
        this.opMostrarTodos.addActionListener(controlador);
        this.opCargar.addActionListener(controlador);
    }
    public void vistaSesionCerrada(){
        opCerrarSesion.setVisible(false);
        opRegistrarse.setVisible(true);
        opIniciarSesion.setVisible(true);
        opAgregar.setVisible(false);
        opListaObtenidos.setVisible(false);
        opCargar.setVisible(false);
    }
    public void vistaSesionIniciada(){
        opCerrarSesion.setVisible(true);
        opRegistrarse.setVisible(false);
        opIniciarSesion.setVisible(false);
        opAgregar.setVisible(true);
        opListaObtenidos.setVisible(true);
        opCargar.setVisible(true);
    }
    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public JMenuItem getOpCerrarSesion() {
        return opCerrarSesion;
    }

    public JMenuItem getOpIniciarSesion() {
        return opIniciarSesion;
    }

    public JMenuItem getOpRegistrarse() {
        return opRegistrarse;
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuIngresar = new javax.swing.JMenu();
        opIniciarSesion = new javax.swing.JMenuItem();
        opCerrarSesion = new javax.swing.JMenuItem();
        opRegistrarse = new javax.swing.JMenuItem();
        menuPokemon = new javax.swing.JMenu();
        opAgregar = new javax.swing.JMenuItem();
        opListaObtenidos = new javax.swing.JMenuItem();
        menuBuscar = new javax.swing.JMenu();
        opMostrarTodos = new javax.swing.JMenuItem();
        opCargar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 650));
        setResizable(false);

        escritorio.setPreferredSize(new java.awt.Dimension(800, 650));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        menuIngresar.setText("Ingresar");

        opIniciarSesion.setText("Iniciar Sesion");
        menuIngresar.add(opIniciarSesion);

        opCerrarSesion.setText("Cerrar Sesion");
        menuIngresar.add(opCerrarSesion);

        opRegistrarse.setText("Registrarse");
        menuIngresar.add(opRegistrarse);

        jMenuBar1.add(menuIngresar);

        menuPokemon.setText("Pokemon");

        opAgregar.setText("Agregar");
        menuPokemon.add(opAgregar);

        opListaObtenidos.setText("Mostrar obtenidos");
        menuPokemon.add(opListaObtenidos);

        jMenuBar1.add(menuPokemon);

        menuBuscar.setText("Buscar");

        opMostrarTodos.setText("Mostrar todos");
        menuBuscar.add(opMostrarTodos);

        opCargar.setText("Cargar");
        menuBuscar.add(opCargar);

        jMenuBar1.add(menuBuscar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuBuscar;
    private javax.swing.JMenu menuIngresar;
    private javax.swing.JMenu menuPokemon;
    private javax.swing.JMenuItem opAgregar;
    private javax.swing.JMenuItem opCargar;
    private javax.swing.JMenuItem opCerrarSesion;
    private javax.swing.JMenuItem opIniciarSesion;
    private javax.swing.JMenuItem opListaObtenidos;
    private javax.swing.JMenuItem opMostrarTodos;
    private javax.swing.JMenuItem opRegistrarse;
    // End of variables declaration//GEN-END:variables
}
