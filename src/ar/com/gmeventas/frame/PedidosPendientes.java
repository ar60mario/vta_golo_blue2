/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.frame;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.Pedido;
import ar.com.gmeventas.services.ClienteService;
import ar.com.gmeventas.services.PedidoService;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class PedidosPendientes extends javax.swing.JFrame {

    public String filtro = "";
    private SimpleDateFormat sdf;
    private Date fecha;
    private Date fechaHoy;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private List<Cliente> clientesActivos = null;

    /**
     * Creates new form PedidosPendientes
     */
    public PedidosPendientes() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiarCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        desdeFechaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        volverBtn = new javax.swing.JButton();
        facturarBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        filtroClientesTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox();
        pendientesRBtn = new javax.swing.JRadioButton();
        todosRBtn = new javax.swing.JRadioButton();
        cargarPedidosBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Desde Fecha:");

        desdeFechaTxt.setText("FECHA");

        jLabel2.setText("dd/MM/aaaa");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        facturarBtn.setText("Facturar");
        facturarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarBtnActionPerformed(evt);
            }
        });

        modificarBtn.setText("Modificar");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Fecha", "Cliente", "Importe", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaPedidos);

        jLabel3.setText("Cliente:");

        filtroClientesTxt.setText("FILTRO CLIENTES");
        filtroClientesTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filtroClientesTxtKeyPressed(evt);
            }
        });

        jLabel4.setText("Indique Nombre o deje en blanco para todos");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        pendientesRBtn.setText("Pendientes de Facturar");
        pendientesRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendientesRBtnActionPerformed(evt);
            }
        });

        todosRBtn.setText("Todos");
        todosRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todosRBtnActionPerformed(evt);
            }
        });

        cargarPedidosBtn.setText("Cargar Pedidos");
        cargarPedidosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarPedidosBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(filtroClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(cargarPedidosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(facturarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(modificarBtn)
                                .addGap(170, 170, 170)
                                .addComponent(volverBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pendientesRBtn)
                                .addGap(18, 18, 18)
                                .addComponent(todosRBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(filtroClientesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarPedidosBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(desdeFechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pendientesRBtn)
                    .addComponent(todosRBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverBtn)
                    .addComponent(facturarBtn)
                    .addComponent(modificarBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        FacturarFrame ff = new FacturarFrame();
        ff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void facturarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarBtnActionPerformed
        facturarPedido();
    }//GEN-LAST:event_facturarBtnActionPerformed

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        modificarPedido();
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void filtroClientesTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroClientesTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!filtroClientesTxt.getText().isEmpty()) {
                llenarComboClientes();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                FacturarFrame ff = new FacturarFrame();
                ff.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_filtroClientesTxtKeyPressed

    private void pendientesRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendientesRBtnActionPerformed
        if (pendientesRBtn.isSelected()) {
            todosRBtn.setSelected(false);
        } else {
            todosRBtn.setSelected(true);
        }
    }//GEN-LAST:event_pendientesRBtnActionPerformed

    private void todosRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todosRBtnActionPerformed
        if (todosRBtn.isSelected()) {
            pendientesRBtn.setSelected(false);
        } else {
            pendientesRBtn.setSelected(true);
        }
    }//GEN-LAST:event_todosRBtnActionPerformed

    private void cargarPedidosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPedidosBtnActionPerformed

        Long codigoCliente = 4664L;
        List<Pedido> pedidos = null;
        try {
            pedidos = new PedidoService().getAllPedidosByCodigoYFecha(codigoCliente, fecha, fechaHoy);
        } catch (Exception ex) {
            Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pedidos != null) {
            DefaultTableModel tabla = (DefaultTableModel) tablaPedidos.getModel();
            for (Pedido pe : pedidos) {
                if (!pe.getFacturado() && pendientesRBtn.isSelected()) {
                    Object[] fila = new Object[5];
                    fila[0] = pe.getNumeroPedido();
                    fila[1] = pe.getFecha();
                    fila[2] = pe.getCliente().getRazonSocial();
                    fila[3] = pe.getTotal();
                    if (pe.getFacturado() != null) {
                        if (pe.getFacturado()) {
                            fila[4] = "Facturado";
                        } else {
                            fila[4] = "Disponible";
                        }
                    } else {
                        fila[4] = "Disponible";
                    }
                    tabla.addRow(fila);
                }
            }
            tablaPedidos.setModel(tabla);
        } else {
            JOptionPane.showMessageDialog(this, "No hay pedidos disponibles para esta Solicitud");
        }
    }//GEN-LAST:event_cargarPedidosBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PedidosPendientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarPedidosBtn;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JTextField desdeFechaTxt;
    private javax.swing.JButton facturarBtn;
    private javax.swing.JTextField filtroClientesTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JRadioButton pendientesRBtn;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JRadioButton todosRBtn;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void facturarPedido() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modificarPedido() {
        Pedido pedido = this.pedidoSeleccionado();
    }

    private void limpiarCampos() {
        filtroClientesTxt.setText("");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        pendientesRBtn.setSelected(true);
        todosRBtn.setSelected(false);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        fechaHoy = fecha;
        desdeFechaTxt.setText(sdf.format(fecha));
    }

    private void llenarComboClientes() {
        filtro = filtroClientesTxt.getText();
        try {
            clientesActivos = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            Logger.getLogger(PedidosPendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboClientes.getModel();
        if (!clientesActivos.isEmpty() && clientesActivos != null) {
            for (Cliente cli : clientesActivos) {
                model.addElement(cli.getRazonSocial());
            }
            comboClientes.setModel(model);
        }
    }

    private Pedido pedidoSeleccionado() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Pedido pedidoSeleccionado = null;
        return pedidoSeleccionado;
    }

}