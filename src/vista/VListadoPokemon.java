package vista;

import Controlador.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BIIbr
 */
public class VListadoPokemon extends javax.swing.JInternalFrame {
    private int id = 0;
    private DefaultTableModel datosTabla;
    public static final String BTN_BUSCAR = "Buscar pokemones por filtros";
    public static final String BTN_MODIFICAR = "Modificar un pokemon de la db"; 
    public static final String BTN_ELIMINAR = "Eliminar un pokemon de la db"; 

    public VListadoPokemon() {
        initComponents();
    }
    public void ejecutar(ArrayList<String[]> lineas, ArrayList<String> listaTipo){     
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        this.configurarTabla();
        this.cargarTabla(lineas);
        this.cargarTipos(listaTipo);
        this.setTitle("Listado de Pokemones");
        this.setVisible(true);
        
    }
    
    public void setControlador(Controlador controlador){        
        this.btnModificar.setActionCommand(BTN_MODIFICAR);
        this.btnEliminar.setActionCommand(BTN_ELIMINAR);
        this.btnBuscar.setActionCommand(BTN_BUSCAR);
        this.btnBuscar.addActionListener(controlador);        
        this.btnModificar.addActionListener(controlador);
        this.btnEliminar.addActionListener(controlador);
    }
    public void configurarTabla(){//metodo para la configuracion de la tabla
        datosTabla = new DefaultTableModel(); 
        datosTabla.addColumn("ID");
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Nivel actual");
        datosTabla.addColumn("Tipo");
        datosTabla.addColumn("Habilidad");
        datosTabla.addColumn("Evolucion");
        datosTabla.addColumn("Nivel Requerido");
        tablaPokemon.setModel(datosTabla);
    }
    public void cargarBusqueda(String[] valor){//metodo busqueda        
        
        DefaultTableModel tb = (DefaultTableModel) tablaPokemon.getModel();//para limpiar la tabla
        int a = tablaPokemon.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
        tb.removeRow(tb.getRowCount()-1);
        }
               
        datosTabla.addRow(valor);  
        tablaPokemon.setModel(datosTabla);
    }
    public void cargarTabla(ArrayList<String[]> lineas){//metodo para agregar los pokemones a la tabla    
        for(String[] linea : lineas){           
            datosTabla.addRow(linea);  
        }    
        tablaPokemon.setModel(datosTabla);
    }
    public void cargarTipos(ArrayList<String> listaTipo){//metodo para cargar los tipos en el cb
        cbTipo.removeAllItems();   
        for (String tipo : listaTipo) {
            cbTipo.addItem(tipo);
        }       
    }       
    public int recuperarId(){  
        return id;
        
        
    }    
    public JTable getTablaPokemon() {
        return tablaPokemon;
    }

    public JTextField getTxtNombrePokemon() {
        return txtNombrePokemon;
    }
                                            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPokemon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombrePokemon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaPokemon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaPokemon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPokemonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPokemon);

        jLabel1.setText("Pokemones almacenados: ");

        jLabel2.setText("Filtros:");

        jLabel3.setText("Nombre: ");

        jLabel4.setText("Tipo:");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscar.setText("Buscar");

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnModificar.setText("Modificar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombrePokemon, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addGap(197, 197, 197)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombrePokemon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaPokemonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPokemonMouseClicked
       btnEliminar.setVisible(true);
       btnModificar.setVisible(true);
       id = Integer.valueOf(String.valueOf(tablaPokemon.getValueAt(tablaPokemon.getSelectedRow(), 0)));
    }//GEN-LAST:event_tablaPokemonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPokemon;
    private javax.swing.JTextField txtNombrePokemon;
    // End of variables declaration//GEN-END:variables
}
