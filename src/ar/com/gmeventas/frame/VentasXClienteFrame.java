/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.frame;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.entities.Rubro;
import ar.com.gmeventas.main.MainFrame;
import ar.com.gmeventas.services.ClienteService;
import ar.com.gmeventas.services.IvaVentasService;
import ar.com.gmeventas.services.RenglonFacturaService;
import ar.com.gmeventas.services.RubroService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcela
 */
public class VentasXClienteFrame extends javax.swing.JFrame {

    private List<Cliente> clientes = null;
    private Long filtroCliente;
    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public IvaVentas facturaIV;

    /**
     * Creates new form VentasXClienteFrame
     */
    public VentasXClienteFrame() {
        initComponents();
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

        volverBtn = new javax.swing.JButton();
        buscarClienteBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        codigoClienteTxt = new javax.swing.JTextField();
        nombreClienteTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fechaDesdeTxt = new javax.swing.JTextField();
        fechaHastaTxt = new javax.swing.JTextField();
        buscarMovimientosBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        buscarClienteBtn.setText("Buscar Cliente");
        buscarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Código Cliente:");

        codigoClienteTxt.setText("CODIGO CLIENTE");

        nombreClienteTxt.setText("NOMBRE CLIENTE");

        jLabel2.setText("Nombre Cliente:");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha hasta:");

        jLabel4.setText("Fecha desde:");

        fechaDesdeTxt.setText("FECHA DESDE");

        fechaHastaTxt.setText("FECHA HASTA");

        buscarMovimientosBtn.setText("Buscar Movimientos");
        buscarMovimientosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarMovimientosBtnActionPerformed(evt);
            }
        });

        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rubro", "Cant.FC.", "Cant.Unid", "Importe", "Incidencia"
            }
        ));
        jScrollPane1.setViewportView(tablaReporte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaDesdeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaHastaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarMovimientosBtn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buscarClienteBtn)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(volverBtn))
                                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(codigoClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteBtn)
                    .addComponent(nombreClienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(volverBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(fechaDesdeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHastaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarMovimientosBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void buscarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteBtnActionPerformed
        if (nombreClienteTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar mínimo una letra para buscar cliente");
        } else {
            clientes = null;
            try {
                clientes = new ClienteService().getClientesByFiltro(nombreClienteTxt.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar clientes BTN");
                Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (clientes != null) {
                for (Cliente cli : clientes) {
                    comboClientes.addItem(cli.getRazonSocial());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontro ningun cliente con esa solicitud");
            }
        }
    }//GEN-LAST:event_buscarClienteBtnActionPerformed

    private void buscarMovimientosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarMovimientosBtnActionPerformed
        Boolean volver = false;
        if (codigoClienteTxt.getText().isEmpty()) {
            volver = true;
        }
        if (fechaDesdeTxt.getText().isEmpty()) {
            volver = true;
        }
        if (fechaHastaTxt.getText().isEmpty()) {
            volver = true;
        }
        if (volver) {
            JOptionPane.showMessageDialog(this, "Debe completar un Codigo Cliente, y fechas desde y hasta");
        } else {
            generarInforme();
        }
    }//GEN-LAST:event_buscarMovimientosBtnActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        if (comboClientes.getSelectedIndex() > 0) {
            int itemSeleccionado = comboClientes.getSelectedIndex() - 1;
            if (itemSeleccionado >= 1) {
                codigoClienteTxt.setText(clientes.get(itemSeleccionado).getCodigo());
                nombreClienteTxt.setText(clientes.get(itemSeleccionado).getRazonSocial());
            }
        }
    }//GEN-LAST:event_comboClientesActionPerformed

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
            java.util.logging.Logger.getLogger(VentasXClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasXClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasXClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasXClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasXClienteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarClienteBtn;
    private javax.swing.JButton buscarMovimientosBtn;
    private javax.swing.JTextField codigoClienteTxt;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JTextField fechaDesdeTxt;
    private javax.swing.JTextField fechaHastaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreClienteTxt;
    private javax.swing.JTable tablaReporte;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        codigoClienteTxt.setText("");
        nombreClienteTxt.setText("");
        fechaDesdeTxt.setText("");
        fechaHastaTxt.setText("");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
    }

    private void generarInforme() {
        List<RenglonFactura> rf = null;
        List<IvaVentas> iv = null;
        Boolean volver = false;
        Date fd = null;
        Date fa = null;
        try {
            Cliente cliente = new ClienteService().getClienteByCodigo(codigoClienteTxt.getText());
            filtroCliente = cliente.getId();
        } catch (Exception ex) {
            volver = true;
            JOptionPane.showMessageDialog(this, "error leyendo el Cliente Seleccionado");
            Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<Rubro> rubros = new RubroService().getAllRubros();
        } catch (Exception ex) {
            volver = true;
            JOptionPane.showMessageDialog(this, "error leyendo Rubros");
            Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fd = sdf.parse(fechaDesdeTxt.getText());
            fa = sdf.parse(fechaHastaTxt.getText());
        } catch (ParseException ex) {
            volver = true;
            JOptionPane.showMessageDialog(this, "Verifique el formato de las fechas solicitadas");
            //Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!volver) {
            try {
                iv = new IvaVentasService().getAllIvaVentasByCodigoYFecha(filtroCliente, fa, fa);
            } catch (Exception ex) {
                volver = true;
                JOptionPane.showMessageDialog(this, "Error al buscar Facturas del Cliente solicitado");
                Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (iv != null) {

            } else {
                volver = true;
                JOptionPane.showMessageDialog(this, "No hay renglones en Facturas solicitadas");
            }
        }
        if (!volver) {
            try {
                rf = new RenglonFacturaService().getAllRenglonFacturaFromIvaVentas(facturaIV);
            } catch (Exception ex) {
                volver = true;
                JOptionPane.showMessageDialog(this, "Error leyendo Facturas solicitadas");
                Logger.getLogger(VentasXClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rf != null) {
                if (iv != null) {
                    for (IvaVentas iva : iv) {
                        for (RenglonFactura renglon: rf) {
                            System.out.print(renglon.getProducto().getDetalle());
                            System.out.print(" ");
                            System.out.print(renglon.getProducto().getRubro().getNombre());
                            System.out.println(" ");
                        }
                    }
                } else {
                    volver = true;
                    JOptionPane.showMessageDialog(this, "No hay Facturas solicitadas");
                }
            } else {
                volver = true;
                JOptionPane.showMessageDialog(this, "No hay renglones en Facturas solicitadas");
            }
        }
    }
}
