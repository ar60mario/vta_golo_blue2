/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.gmeventas.frame;

import ar.com.gmeventas.entities.Configuracion;
import ar.com.gmeventas.entities.IvaCompras;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.Proveedor;
import ar.com.gmeventas.services.ConfiguracionService;
import ar.com.gmeventas.services.IvaComprasServices;
import ar.com.gmeventas.services.IvaVentasService;
import ar.com.gmeventas.services.ProveedorService;
import ar.com.gmeventas.util.Constantes;
import static java.lang.Math.rint;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class NuevoIvaComprasFrame extends javax.swing.JFrame {
    private String filtro;
    private Proveedor proveedorSeleccionado = null;
    private Integer codigoProveedor;
    private boolean guardar;
    private float iva = 0;
    private Configuracion conf = null;
    private Double gravado;
    private Double noGravado;
    private Double impuestoInterno;
    private Double percepcionIIBB;
    private Double percepcionIva;
    private Double importeIva;
    private Double otro;
    private Double importeTotal;
    private DecimalFormat df = new DecimalFormat("#0.00");

    /**
     * Creates new form NuevoIvaVentas
     */
    public NuevoIvaComprasFrame() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(Constantes.getR(),
                Constantes.getG(), Constantes.getB()));
        limpiarCampos();
        cargarConfiguracion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nroSucursalTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        nroFacturTxt = new javax.swing.JTextField();
        fechaPeriodoTxt = new javax.swing.JTextField();
        nombreProveedorTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fechaFacturaTxt = new javax.swing.JTextField();
        comboLetraFactura = new javax.swing.JComboBox();
        buscarPorNombreBtn = new javax.swing.JButton();
        comboProveedor = new javax.swing.JComboBox();
        gravadoTxt = new javax.swing.JTextField();
        impuestoInternoTxt = new javax.swing.JTextField();
        percepcionIIBBTxt = new javax.swing.JTextField();
        percepcionIvaTxt = new javax.swing.JTextField();
        otroTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        codigoProveedorBuscadoTxt = new javax.swing.JTextField();
        razonSocialProveedorBuscadoTxt = new javax.swing.JTextField();
        codigoProveedorTxt = new javax.swing.JTextField();
        buscarProveedorXCodigoBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        noGravadoTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        chekBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        guardarBtn.setText("Guardar");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha Periodo:");

        jLabel2.setText("Numero:");

        jLabel3.setText("Codigo Proveedor:");

        jLabel5.setText("Gravado:");

        jLabel6.setText("Imp. Interno:");

        jLabel7.setText("Percep.II BB:");

        jLabel8.setText("Percep. IVA:");

        jLabel9.setText("Otro:");

        jLabel10.setText("IVA:");

        jLabel11.setText("Total:");

        nroSucursalTxt.setText("Nro Sucursal");

        jLabel12.setText("-");

        nroFacturTxt.setText("Nro Factura");

        fechaPeriodoTxt.setText("FECHA PERIODO");

        nombreProveedorTxt.setText("NOMBRE PROVEEDOR A BUSCAR");
        nombreProveedorTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProveedorTxtActionPerformed(evt);
            }
        });

        jLabel13.setText("Fecha Factura:");

        fechaFacturaTxt.setText("FECHA FACTURA");

        comboLetraFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "A", "C", "M" }));
        comboLetraFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLetraFacturaActionPerformed(evt);
            }
        });

        buscarPorNombreBtn.setText("Buscar x Nombre");
        buscarPorNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPorNombreBtnActionPerformed(evt);
            }
        });

        comboProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProveedorActionPerformed(evt);
            }
        });

        gravadoTxt.setText("GRAVADO");

        impuestoInternoTxt.setText("IMP.INTERNO");

        percepcionIIBBTxt.setText("PERC.II BB");

        percepcionIvaTxt.setText("PERCEP IVA");

        otroTxt.setText("OTRO");

        ivaTxt.setText("IVA");

        totalTxt.setText("TOTAL");

        jLabel4.setText("Proveedor:");

        codigoProveedorBuscadoTxt.setText("CODIGO PROVEEDOR");

        razonSocialProveedorBuscadoTxt.setText("RAZON SOCIAL PROVEEDOR");

        codigoProveedorTxt.setText("CODIGO PROVEEDOR");

        buscarProveedorXCodigoBtn.setText("Buscar");
        buscarProveedorXCodigoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProveedorXCodigoBtnActionPerformed(evt);
            }
        });

        jLabel14.setText("No Gravado:");

        noGravadoTxt.setText("NO GRAVADO");

        jLabel15.setText("Prov. A Buscar");

        chekBtn.setText("Chek");
        chekBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekBtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Volver");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(guardarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volverBtn)
                        .addGap(208, 208, 208))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addGap(1, 1, 1))
                                .addComponent(jLabel14))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoProveedorTxt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarProveedorXCodigoBtn)
                                .addGap(267, 267, 267))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboLetraFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nroSucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nroFacturTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(noGravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(115, 115, 115)
                                        .addComponent(buscarPorNombreBtn))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(106, 106, 106)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(codigoProveedorBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(razonSocialProveedorBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 34, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(otroTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(percepcionIvaTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(impuestoInternoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chekBtn))
                                    .addComponent(percepcionIIBBTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreProveedorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel4)))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPeriodoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fechaPeriodoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(fechaFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLetraFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(nroSucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nroFacturTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(codigoProveedorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProveedorXCodigoBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreProveedorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(noGravadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarPorNombreBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(impuestoInternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(percepcionIIBBTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(percepcionIvaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(chekBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(otroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoProveedorBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razonSocialProveedorBuscadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn)
                    .addComponent(volverBtn))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreProveedorTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProveedorTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProveedorTxtActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        this.guardar();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        AbmIvaComprasFrame av = new AbmIvaComprasFrame();
        av.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void comboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProveedorActionPerformed
        if (comboProveedor.getSelectedIndex() > 0){
            nombreProveedorTxt.setText("");
            Integer seleccion = comboProveedor.getSelectedIndex();
            try {
                proveedorSeleccionado = new ProveedorService().getProveedoresByFiltro(filtro).get(seleccion -1);
            } catch (Exception ex) {
                Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            codigoProveedorBuscadoTxt.setText(String.valueOf(proveedorSeleccionado.getCodigo()));
            razonSocialProveedorBuscadoTxt.setText(proveedorSeleccionado.getRazonSocial());
        }
    }//GEN-LAST:event_comboProveedorActionPerformed

    private void buscarPorNombreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPorNombreBtnActionPerformed
        buscarProveedorByFiltro();
    }//GEN-LAST:event_buscarPorNombreBtnActionPerformed

    private void buscarProveedorXCodigoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProveedorXCodigoBtnActionPerformed
        codigoProveedor = Integer.valueOf(codigoProveedorTxt.getText());
        try {
            proveedorSeleccionado = new ProveedorService().getProveedorByCodigo(codigoProveedor);
            codigoProveedorBuscadoTxt.setText(codigoProveedorTxt.getText());
            razonSocialProveedorBuscadoTxt.setText(proveedorSeleccionado.getRazonSocial());
        } catch (Exception ex) {
//            Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Debe ingresar un codigo valido");
        }
    }//GEN-LAST:event_buscarProveedorXCodigoBtnActionPerformed

    private void comboLetraFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLetraFacturaActionPerformed
        gravadoTxt.setText("");
        noGravadoTxt.setText("");
        impuestoInternoTxt.setText("");
        percepcionIIBBTxt.setText("");
        percepcionIvaTxt.setText("");
        otroTxt.setText("");
        ivaTxt.setText("");
        totalTxt.setText("");
        if (comboLetraFactura.getSelectedIndex()>0){
            if (comboLetraFactura.getSelectedItem() == "A" || comboLetraFactura.getSelectedItem() == "M") {
                gravadoTxt.setEditable(true);
                noGravadoTxt.setEditable(false);
                impuestoInternoTxt.setEditable(true);
                percepcionIIBBTxt.setEditable(true);
                percepcionIvaTxt.setEditable(true);
                otroTxt.setEditable(true);
                ivaTxt.setEditable(true);
                totalTxt.setEditable(true);
            } else {
                gravadoTxt.setEditable(false);
                noGravadoTxt.setEditable(true);
                impuestoInternoTxt.setEditable(false);
                percepcionIIBBTxt.setEditable(false);
                percepcionIvaTxt.setEditable(false);
                otroTxt.setEditable(true);
                ivaTxt.setEditable(false);
                totalTxt.setEditable(true);
            }
        }
    }//GEN-LAST:event_comboLetraFacturaActionPerformed

    private void chekBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekBtnActionPerformed
        calcularTotales();
    }//GEN-LAST:event_chekBtnActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoIvaComprasFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarPorNombreBtn;
    private javax.swing.JButton buscarProveedorXCodigoBtn;
    private javax.swing.JButton chekBtn;
    private javax.swing.JTextField codigoProveedorBuscadoTxt;
    private javax.swing.JTextField codigoProveedorTxt;
    private javax.swing.JComboBox comboLetraFactura;
    private javax.swing.JComboBox comboProveedor;
    private javax.swing.JTextField fechaFacturaTxt;
    private javax.swing.JTextField fechaPeriodoTxt;
    private javax.swing.JTextField gravadoTxt;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JTextField impuestoInternoTxt;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField noGravadoTxt;
    private javax.swing.JTextField nombreProveedorTxt;
    private javax.swing.JTextField nroFacturTxt;
    private javax.swing.JTextField nroSucursalTxt;
    private javax.swing.JTextField otroTxt;
    private javax.swing.JTextField percepcionIIBBTxt;
    private javax.swing.JTextField percepcionIvaTxt;
    private javax.swing.JTextField razonSocialProveedorBuscadoTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        guardar = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (proveedorSeleccionado.getActivo()){
            IvaCompras ic = new IvaCompras();
            try {
                ic.setFechaFactura(sdf.parse(fechaFacturaTxt.getText()));
                ic.setFechaPeriodo(sdf.parse(fechaPeriodoTxt.getText()));
            } catch (ParseException ex) {
                guardar = false;
                Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (guardar){
                ic.setLetra((String) comboLetraFactura.getSelectedItem());
                ic.setNumeroSucursal(Integer.valueOf(nroSucursalTxt.getText()));
                ic.setNumeroFactura(Integer.valueOf(nroFacturTxt.getText()));
                if (gravadoTxt.getText().isEmpty()){
                    ic.setGravado(0.0);
                }else{
                    ic.setGravado(Double.valueOf(gravadoTxt.getText()));
                }
                if (noGravadoTxt.getText().isEmpty()){
                    ic.setNoGravado(0.0);
                }else{
                    ic.setNoGravado(Double.valueOf(noGravadoTxt.getText()));
                }
                if (impuestoInternoTxt.getText().isEmpty()){
                    ic.setImpuestoInterno(0.0);
                }else{
                    ic.setImpuestoInterno(Double.valueOf(impuestoInternoTxt.getText()));
                }
                if (percepcionIIBBTxt.getText().isEmpty()){
                    ic.setPercepcionIiBb(0.0);
                }else{
                    ic.setPercepcionIiBb(Double.valueOf(percepcionIIBBTxt.getText()));
                }
                if (percepcionIvaTxt.getText().isEmpty()){
                    ic.setPercepcionIva(0.0);
                }else{
                    ic.setPercepcionIva(Double.valueOf(percepcionIvaTxt.getText()));
                }
                if (otroTxt.getText().isEmpty()){
                    ic.setOtro(0.0);
                }else{
                    ic.setOtro(Double.valueOf(otroTxt.getText()));
                }
                if (ivaTxt.getText().isEmpty()){
                    ic.setIva(0.0);
                }else{
                    ic.setIva(Double.valueOf(ivaTxt.getText()));
                }
                if (totalTxt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this,"No esta permitido cargar una Factura sin importe TOTAL");
                }else{
                    ic.setTotal(Double.valueOf(totalTxt.getText()));
                }
                ic.setProveedor(proveedorSeleccionado);
                try {
                    new IvaComprasServices().saveIvaCompras(ic);
                } catch (Exception ex) {
                    Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(this,"El proveedor Seleccionado esta Inactivo");
        }
        limpiarCampos();
    }

    private void limpiarCampos() {
        fechaFacturaTxt.setText("");
        fechaPeriodoTxt.setText("");
        nroSucursalTxt.setText("");
        nroFacturTxt.setText("");
        nombreProveedorTxt.setText("");
        gravadoTxt.setText("");
        noGravadoTxt.setText("");
        impuestoInternoTxt.setText("");
        percepcionIIBBTxt.setText("");
        percepcionIvaTxt.setText("");
        otroTxt.setText("");
        ivaTxt.setText("");
        totalTxt.setText("");
        comboProveedor.removeAllItems();
        comboProveedor.addItem("");
        codigoProveedorBuscadoTxt.setText("");
        razonSocialProveedorBuscadoTxt.setText("");
        codigoProveedorTxt.setText("");
        // aqui se bloquean los campos de importe
        gravadoTxt.setEditable(false);
        noGravadoTxt.setEditable(false);
        impuestoInternoTxt.setEditable(false);
        percepcionIIBBTxt.setEditable(false);
        percepcionIvaTxt.setEditable(false);
        otroTxt.setEditable(false);
        ivaTxt.setEditable(false);
        totalTxt.setEditable(false);
    }

    private void buscarProveedorByFiltro() {
        comboProveedor.removeAllItems();
        comboProveedor.addItem("");
        llenarComboProveedor();
    }

    private void llenarComboProveedor() {
        filtro = nombreProveedorTxt.getText();
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        try {
            proveedores = new ProveedorService().getProveedoresByFiltro(filtro);
        } catch (Exception ex) {
            Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboProveedor.getModel();
        if (!proveedores.isEmpty() && proveedores != null){
            for (Proveedor prove: proveedores){
                model.addElement(prove.getRazonSocial());
            }
            comboProveedor.setModel(model);
        }
    }

    private void cargarConfiguracion() {
        try {
            conf = new ConfiguracionService().getFacturas((long) 1);
        } catch (Exception ex) {
            Logger.getLogger(NuevoIvaComprasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        iva = conf.getIva();
    }

    private void calcularTotales() {
        if (!gravadoTxt.getText().isEmpty()) {
            importeIva = Double.valueOf(gravadoTxt.getText()) * iva;
            importeIva = rint(importeIva) / 100;
        } else {
            importeIva = 0.0;
        }
        ivaTxt.setText((String.valueOf(df.format(importeIva))).replace(",", "."));
        if (!gravadoTxt.getText().isEmpty()) {
            gravado = Double.valueOf(gravadoTxt.getText());
        } else {
            gravado = 0.0;
        }
        if (!noGravadoTxt.getText().isEmpty()){
            noGravado = Double.valueOf(noGravadoTxt.getText());
        } else {
            noGravado = 0.0;
        }
        if (!impuestoInternoTxt.getText().isEmpty()){
            impuestoInterno = Double.valueOf(impuestoInternoTxt.getText());
        } else {
            impuestoInterno = 0.0;
        }
        if (!percepcionIIBBTxt.getText().isEmpty()){
            percepcionIIBB = Double.valueOf(percepcionIIBBTxt.getText());
        } else {
            percepcionIIBB = 0.0;
        }
        if (!percepcionIvaTxt.getText().isEmpty()) {
            percepcionIva = Double.valueOf(percepcionIvaTxt.getText());
        } else {
            percepcionIva = 0.0;
        }
        if (!otroTxt.getText().isEmpty()) {
            otro = Double.valueOf(otroTxt.getText());
        } else {
            otro = 0.0;
        }
        importeTotal = gravado;
        importeTotal += importeIva;
        importeTotal += noGravado;
        importeTotal += impuestoInterno;
        importeTotal += percepcionIIBB;
        importeTotal += percepcionIva;
        importeTotal += otro;
        totalTxt.setText((df.format(importeTotal)).replace(",", "."));
    }
}
