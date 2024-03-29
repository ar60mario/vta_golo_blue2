/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.main;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ObtenerFacturaFrame extends javax.swing.JFrame {

    /**
     * Creates new form ObtenerFacturaFrame
     */
    public ObtenerFacturaFrame() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboLetra = new javax.swing.JComboBox();
        sucursalTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        fechaTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        volverBtn = new javax.swing.JButton();
        facturaChk = new javax.swing.JRadioButton();
        notaCreditoChk = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Letra Factura:");

        jLabel2.setText("Sucursal Factura:");

        jLabel3.setText("Número Factura:");

        jLabel4.setText("Fecha Factura:");

        comboLetra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        sucursalTxt.setText("jTextField1");

        numeroTxt.setText("jTextField2");

        fechaTxt.setText("jTextField3");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        facturaChk.setText("Factura");

        notaCreditoChk.setText("Nota de Crédito");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(facturaChk)
                    .addComponent(buscarBtn))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                        .addComponent(volverBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(notaCreditoChk)
                            .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volverBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sucursalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facturaChk)
                    .addComponent(notaCreditoChk))
                .addGap(18, 18, 18)
                .addComponent(buscarBtn)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        buscar();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        volver();
    }//GEN-LAST:event_volverBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ObtenerFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ObtenerFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ObtenerFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ObtenerFacturaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ObtenerFacturaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarBtn;
    private javax.swing.JComboBox comboLetra;
    private javax.swing.JRadioButton facturaChk;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton notaCreditoChk;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField sucursalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        sucursalTxt.setText("");
        numeroTxt.setText("");
        fechaTxt.setText("");
        comboLetra.removeAllItems();
        comboLetra.addItem("");
        comboLetra.addItem("A");
        comboLetra.addItem("B");
        facturaChk.setSelected(true);
        notaCreditoChk.setSelected(false);
    }

    private void buscar() {
        String tipo_cbte = "1";
        if (facturaChk.isSelected()) {
            if (comboLetra.getSelectedIndex() == 1) {
                tipo_cbte = "1"; //FC A
            } else {
                tipo_cbte = "6"; //FC B
            }
        } else if (comboLetra.getSelectedIndex() == 1) {
            tipo_cbte = "3"; // NC A
        } else {
            tipo_cbte = "8"; // NC B
        }
        String nro = "00000000";
        nro += numeroTxt.getText();
        int largo = nro.length();
        String cbte_nro = nro.substring(largo - 8, largo);
        String pto_vta = "0003";
        try {
            LibraryLoader.loadJacobLibrary();
            ActiveXComponent wsaa = new ActiveXComponent("WSAA");
            String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
            String userdir = "c:/certifgolo";
            Dispatch.call(wsaa, "Conectar",
                    new Variant("wsfe"),
                    new Variant(userdir + "/nuevo2020_795b6631abd56620.crt"),
                    new Variant(userdir + "/clave_privada_30709520748_202010302353.key"),
                    /*
                    new Variant(userdir + "/nuevo2018_2ec12fb3105c3016.crt"),
                    new Variant(userdir + "/clave_privada_30709520748_201811053644.key"),
                    */
                    new Variant(wsdl));
            String excepcion = Dispatch.get(wsaa, "Excepcion").toString();
            String token = Dispatch.get(wsaa, "Token").toString();
            String sign = Dispatch.get(wsaa, "Sign").toString();
            ActiveXComponent wsfev1 = new ActiveXComponent("WSFEv1");
            Dispatch.put(wsfev1, "Cuit", new Variant("30709520748"));
            Dispatch.put(wsfev1, "Token", new Variant(token));
            Dispatch.put(wsfev1, "Sign", new Variant(sign));
            String cache = "";
            wsdl = "https://servicios1.afip.gov.ar/wsfev1/service.asmx?WSDL";
            Variant cae2 = Dispatch.call(wsfev1, "CompConsultar",
                    new Variant(tipo_cbte),
                    new Variant(pto_vta),
                    new Variant(cbte_nro)
            );
            Variant fecha = Dispatch.call(wsfev1, "FechaCbte",
                    new Variant(tipo_cbte),
                    new Variant(pto_vta),
                    new Variant(cbte_nro)
            );
            System.out.println(cae2);
            System.out.println(fecha);
            excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
            //JOptionPane.showMessageDialog(this, "Ult.Comprb." + ult.toString());
            //String fechaWs = new SimpleDateFormat("yyyyMMdd").format(new Date());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();
        }
    }

    private void volver() {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        this.dispose();
    }
}
