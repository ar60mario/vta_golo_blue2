/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.main;

import ar.com.gmeventas.entities.Configuracion;
import ar.com.gmeventas.frame.AbmClienteFrame;
import ar.com.gmeventas.frame.AbmProductoFrame;
import ar.com.gmeventas.frame.AbmProveedorFrame;
import ar.com.gmeventas.frame.AbmRubroFrame;
import ar.com.gmeventas.frame.AbmSubRubroFrame;
import ar.com.gmeventas.frame.AbmTipoDocForm;
import ar.com.gmeventas.frame.CobrarFrame;
import ar.com.gmeventas.frame.ComunicacionAfipFrame;
import ar.com.gmeventas.frame.ConfiguracionFrame;
import ar.com.gmeventas.frame.DuplicadoFacturaFrame;
import ar.com.gmeventas.frame.DuplicadoNcFrame;
import ar.com.gmeventas.frame.FacturaWebFrame;
import ar.com.gmeventas.frame.FacturarFrame;
import ar.com.gmeventas.frame.ImportarClienteFrame;
import ar.com.gmeventas.frame.ImportarLibroIvaFrame;
import ar.com.gmeventas.frame.ImportarProductoFrame;
import ar.com.gmeventas.frame.IngresarCodigosBarraFrame;
import ar.com.gmeventas.frame.NcFcFrame;
import ar.com.gmeventas.frame.NotaCreditoWebFrame;
import ar.com.gmeventas.frame.PedidoFrame;
import ar.com.gmeventas.frame.PreciosEntreCodigosFrame;
import ar.com.gmeventas.frame.RegenerarFcNcFrame;
import ar.com.gmeventas.frame.VentasCigarrillosByPeriodoFrame;
import ar.com.gmeventas.frame.VerComparativaFacturasEntreFechasFrame;
import ar.com.gmeventas.frame.VerIvaVentasFrame;
import ar.com.gmeventas.frame.VerVentasXClienteFrame;
import ar.com.gmeventas.services.ConfiguracionService;
import ar.com.gmeventas.util.LectorDeExcel;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class MainFrame extends javax.swing.JFrame {

    private String filtro = "";

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        iniciarConfiguracionBtn.setVisible(false);
        notaCreditoBtn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salirBtn = new javax.swing.JButton();
        pedidoBtn = new javax.swing.JButton();
        facturaBtn = new javax.swing.JButton();
        cobrarBtn = new javax.swing.JButton();
        iniciarConfiguracionBtn = new javax.swing.JButton();
        notaCreditoBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        abmClientesMnu = new javax.swing.JMenuItem();
        abmProductosMnu = new javax.swing.JMenuItem();
        abmProveedoreswMnu = new javax.swing.JMenuItem();
        abmRubroMnu = new javax.swing.JMenuItem();
        abmSubRubroMnu = new javax.swing.JMenuItem();
        abmPreciosEntreCodMnu = new javax.swing.JMenuItem();
        abmTipoDocMnu = new javax.swing.JMenuItem();
        codigosBarraMnu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        comunicacionAfipMnu = new javax.swing.JMenuItem();
        notaCreditoReFacturarMnu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ivaVentasMnu = new javax.swing.JMenuItem();
        ventasXClienteMnu = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        ventasCigByPeriodoMnu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        duplicadoFcMenu = new javax.swing.JMenuItem();
        duplicadoNcMenu = new javax.swing.JMenuItem();
        generarPdfMnu = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        salirMnu = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        versionMnu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GOLOSOL . Sistema de Ventas");

        salirBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        salirBtn.setText("Salir");
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });

        pedidoBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pedidoBtn.setText("Pedido");
        pedidoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidoBtnActionPerformed(evt);
            }
        });

        facturaBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        facturaBtn.setText("Factura");
        facturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturaBtnActionPerformed(evt);
            }
        });

        cobrarBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cobrarBtn.setText("Cobrar");
        cobrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarBtnActionPerformed(evt);
            }
        });

        iniciarConfiguracionBtn.setText("i");
        iniciarConfiguracionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarConfiguracionBtnActionPerformed(evt);
            }
        });

        notaCreditoBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        notaCreditoBtn.setText("Nota Crédito");
        notaCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoBtnActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenu7.setText("ABM");
        jMenu7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        abmClientesMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmClientesMnu.setText("Clientes");
        abmClientesMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmClientesMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmClientesMnu);

        abmProductosMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmProductosMnu.setText("Productos");
        abmProductosMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmProductosMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmProductosMnu);

        abmProveedoreswMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmProveedoreswMnu.setText("Proveedores");
        abmProveedoreswMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmProveedoreswMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmProveedoreswMnu);

        abmRubroMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmRubroMnu.setText("Rubro");
        abmRubroMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmRubroMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmRubroMnu);

        abmSubRubroMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmSubRubroMnu.setText("SubRubro");
        abmSubRubroMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmSubRubroMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmSubRubroMnu);

        abmPreciosEntreCodMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmPreciosEntreCodMnu.setText("Precios entre Códigos");
        abmPreciosEntreCodMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmPreciosEntreCodMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmPreciosEntreCodMnu);

        abmTipoDocMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        abmTipoDocMnu.setText("Tipo Documento");
        abmTipoDocMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmTipoDocMnuActionPerformed(evt);
            }
        });
        jMenu7.add(abmTipoDocMnu);

        codigosBarraMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        codigosBarraMnu.setText("Ingresar Códigos De Barra");
        codigosBarraMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigosBarraMnuActionPerformed(evt);
            }
        });
        jMenu7.add(codigosBarraMnu);

        jMenu1.add(jMenu7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Herramientas");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem1.setText("Configuración");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenu8.setText("Importacón");
        jMenu8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jMenuItem2.setText("Productos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuItem3.setText("Clientes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenuItem4.setText("Proveedores");
        jMenu8.add(jMenuItem4);

        jMenuItem5.setText("Libro Iva");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenu2.add(jMenu8);

        comunicacionAfipMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comunicacionAfipMnu.setText("Prueba Comunicación Afip");
        comunicacionAfipMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comunicacionAfipMnuActionPerformed(evt);
            }
        });
        jMenu2.add(comunicacionAfipMnu);

        notaCreditoReFacturarMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        notaCreditoReFacturarMnu.setText("Nc - ReFacturar");
        notaCreditoReFacturarMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaCreditoReFacturarMnuActionPerformed(evt);
            }
        });
        jMenu2.add(notaCreditoReFacturarMnu);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Informes");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        ivaVentasMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ivaVentasMnu.setText("Libro Iva Ventas");
        ivaVentasMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaVentasMnuActionPerformed(evt);
            }
        });
        jMenu3.add(ivaVentasMnu);

        ventasXClienteMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ventasXClienteMnu.setText("Ventas x Cliente");
        ventasXClienteMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasXClienteMnuActionPerformed(evt);
            }
        });
        jMenu3.add(ventasXClienteMnu);

        jMenuItem6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuItem6.setText("Ver Comparativa Facturas entre fechas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        ventasCigByPeriodoMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ventasCigByPeriodoMnu.setText("Compra - Ventas Cigarrillos x Período");
        ventasCigByPeriodoMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasCigByPeriodoMnuActionPerformed(evt);
            }
        });
        jMenu3.add(ventasCigByPeriodoMnu);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Ventas");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        duplicadoFcMenu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        duplicadoFcMenu.setText("Duplicado Factura");
        duplicadoFcMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicadoFcMenuActionPerformed(evt);
            }
        });
        jMenu4.add(duplicadoFcMenu);

        duplicadoNcMenu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        duplicadoNcMenu.setText("Duplicado Nota Crédito");
        duplicadoNcMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicadoNcMenuActionPerformed(evt);
            }
        });
        jMenu4.add(duplicadoNcMenu);

        generarPdfMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        generarPdfMnu.setText("Generar PDF de Fc/Nc");
        generarPdfMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarPdfMnuActionPerformed(evt);
            }
        });
        jMenu4.add(generarPdfMnu);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Compras");
        jMenu5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu5);

        salirMnu.setText("Salir");
        salirMnu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        salirMnu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMnuMouseClicked(evt);
            }
        });
        jMenuBar1.add(salirMnu);

        jMenu6.setText("?");

        versionMnu.setText("Versión");
        versionMnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versionMnuActionPerformed(evt);
            }
        });
        jMenu6.add(versionMnu);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(pedidoBtn)
                .addGap(18, 18, 18)
                .addComponent(facturaBtn)
                .addGap(18, 18, 18)
                .addComponent(notaCreditoBtn)
                .addGap(18, 18, 18)
                .addComponent(cobrarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(salirBtn)
                .addGap(18, 18, 18)
                .addComponent(iniciarConfiguracionBtn)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pedidoBtn)
                    .addComponent(facturaBtn)
                    .addComponent(notaCreditoBtn)
                    .addComponent(cobrarBtn)
                    .addComponent(salirBtn)
                    .addComponent(iniciarConfiguracionBtn))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        int a = JOptionPane.showConfirmDialog(this, "Confirme SALIR del programa", "Atención", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_salirBtnActionPerformed

    private void pedidoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidoBtnActionPerformed
        PedidoFrame pf = new PedidoFrame();
        pf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pedidoBtnActionPerformed

    private void iniciarConfiguracionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarConfiguracionBtnActionPerformed
        iniciarConfiguracion();
    }//GEN-LAST:event_iniciarConfiguracionBtnActionPerformed

    private void facturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturaBtnActionPerformed
        FacturaWebFrame ff = new FacturaWebFrame();
        //FacturarFrame ff = new FacturarFrame();
        ff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_facturaBtnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ConfiguracionFrame cf = new ConfiguracionFrame();
        cf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
        CobrarFrame cf = new CobrarFrame();
        cf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cobrarBtnActionPerformed

    private void abmClientesMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmClientesMnuActionPerformed
        AbmClienteFrame abmCliente = new AbmClienteFrame();
        abmCliente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmClientesMnuActionPerformed

    private void abmProductosMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmProductosMnuActionPerformed
        AbmProductoFrame productoFrame = new AbmProductoFrame(filtro);
        productoFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmProductosMnuActionPerformed

    private void abmProveedoreswMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmProveedoreswMnuActionPerformed
        AbmProveedorFrame proveedorFrame = new AbmProveedorFrame();
        proveedorFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmProveedoreswMnuActionPerformed

    private void abmRubroMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmRubroMnuActionPerformed
        AbmRubroFrame abmRubro = new AbmRubroFrame();
        abmRubro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmRubroMnuActionPerformed

    private void abmSubRubroMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmSubRubroMnuActionPerformed
        AbmSubRubroFrame abmSubRubro = new AbmSubRubroFrame();
        abmSubRubro.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmSubRubroMnuActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        seleccionarArchivoImportarProductos();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        seleccionarArchivoImportacionClientes();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void abmPreciosEntreCodMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmPreciosEntreCodMnuActionPerformed
        PreciosEntreCodigosFrame pecf = new PreciosEntreCodigosFrame();
        pecf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmPreciosEntreCodMnuActionPerformed

    private void ivaVentasMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaVentasMnuActionPerformed
        VerIvaVentasFrame vivf = new VerIvaVentasFrame();
        vivf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ivaVentasMnuActionPerformed

    private void abmTipoDocMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abmTipoDocMnuActionPerformed
        AbmTipoDocForm atdf = new AbmTipoDocForm();
        atdf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_abmTipoDocMnuActionPerformed

    private void salirMnuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMnuMouseClicked
        System.exit(0);
    }//GEN-LAST:event_salirMnuMouseClicked

    private void ventasXClienteMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasXClienteMnuActionPerformed
        VerVentasXClienteFrame vvxcf = new VerVentasXClienteFrame();
        vvxcf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ventasXClienteMnuActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        seleccionarArchivoImportarIva();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void notaCreditoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoBtnActionPerformed
        NotaCreditoWebFrame ncwf = new NotaCreditoWebFrame();
        ncwf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_notaCreditoBtnActionPerformed

    private void duplicadoFcMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicadoFcMenuActionPerformed
        DuplicadoFacturaFrame dff = new DuplicadoFacturaFrame();
        dff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_duplicadoFcMenuActionPerformed

    private void comunicacionAfipMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comunicacionAfipMnuActionPerformed
        ComunicacionAfipFrame caf = new ComunicacionAfipFrame();
        caf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_comunicacionAfipMnuActionPerformed

    private void notaCreditoReFacturarMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaCreditoReFacturarMnuActionPerformed
        NcFcFrame nff = new NcFcFrame();
        nff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_notaCreditoReFacturarMnuActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        VerComparativaFacturasEntreFechasFrame vcfeff = new VerComparativaFacturasEntreFechasFrame();
        vcfeff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void duplicadoNcMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicadoNcMenuActionPerformed
        DuplicadoNcFrame dncf = new DuplicadoNcFrame();
        dncf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_duplicadoNcMenuActionPerformed

    private void ventasCigByPeriodoMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasCigByPeriodoMnuActionPerformed
        listCigByPeriodo();
    }//GEN-LAST:event_ventasCigByPeriodoMnuActionPerformed

    private void codigosBarraMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigosBarraMnuActionPerformed
        codigosBarra();
    }//GEN-LAST:event_codigosBarraMnuActionPerformed

    private void generarPdfMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarPdfMnuActionPerformed
        reGenerarFcNc();
    }//GEN-LAST:event_generarPdfMnuActionPerformed

    private void versionMnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versionMnuActionPerformed
        VersionFrame vf = new VersionFrame();
        vf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_versionMnuActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abmClientesMnu;
    private javax.swing.JMenuItem abmPreciosEntreCodMnu;
    private javax.swing.JMenuItem abmProductosMnu;
    private javax.swing.JMenuItem abmProveedoreswMnu;
    private javax.swing.JMenuItem abmRubroMnu;
    private javax.swing.JMenuItem abmSubRubroMnu;
    private javax.swing.JMenuItem abmTipoDocMnu;
    private javax.swing.JButton cobrarBtn;
    private javax.swing.JMenuItem codigosBarraMnu;
    private javax.swing.JMenuItem comunicacionAfipMnu;
    private javax.swing.JMenuItem duplicadoFcMenu;
    private javax.swing.JMenuItem duplicadoNcMenu;
    private javax.swing.JButton facturaBtn;
    private javax.swing.JMenuItem generarPdfMnu;
    private javax.swing.JButton iniciarConfiguracionBtn;
    private javax.swing.JMenuItem ivaVentasMnu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JButton notaCreditoBtn;
    private javax.swing.JMenuItem notaCreditoReFacturarMnu;
    private javax.swing.JButton pedidoBtn;
    private javax.swing.JButton salirBtn;
    private javax.swing.JMenu salirMnu;
    private javax.swing.JMenuItem ventasCigByPeriodoMnu;
    private javax.swing.JMenuItem ventasXClienteMnu;
    private javax.swing.JMenuItem versionMnu;
    // End of variables declaration//GEN-END:variables

    private void iniciarConfiguracion() {
        Configuracion conf = new Configuracion();
        conf.setIva((float) 21);
        conf.setNumeroFacturaA(1);
        conf.setSucursalA(1);
        conf.setNumeroFacturaB(1);
        conf.setSucursalB(1);
        conf.setNumeroPedido(1);
        conf.setNumeroRecibo(1);
        try {
            new ConfiguracionService().saveConfiguracion(conf);
            JOptionPane.showMessageDialog(this, "El Archivo Configuración fue Creado Correctamente.");
        } catch (Exception ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "El Archivo Configuración Creado con Anterioridad.",
                    "Atencion",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void seleccionarArchivoImportarIva() {
        JFileChooser selector = new JFileChooser();
        selector.showOpenDialog(this);
        File archivo = selector.getSelectedFile();
        if (archivo != null) {
            if (LectorDeExcel.validarExtension(archivo)) {
                ImportarLibroIvaFrame ipf = new ImportarLibroIvaFrame(archivo);
                ipf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El formato elegido no está soportado.",
                        "Atencion",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void seleccionarArchivoImportarProductos() {
        JFileChooser selector = new JFileChooser();
        selector.showOpenDialog(this);
        File archivo = selector.getSelectedFile();
        if (archivo != null) {
            if (LectorDeExcel.validarExtension(archivo)) {
                ImportarProductoFrame ipf = new ImportarProductoFrame(archivo);
                ipf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El formato elegido no está soportado.",
                        "Atencion",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void seleccionarArchivoImportacionClientes() {
        JFileChooser selector = new JFileChooser();
        selector.showOpenDialog(this);
        File archivo = selector.getSelectedFile();
        if (archivo != null) {
            if (LectorDeExcel.validarExtension(archivo)) {
                ImportarClienteFrame icf = new ImportarClienteFrame(archivo);
                icf.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El formato elegido no está soportado.",
                        "Atencion",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void listCigByPeriodo() {
        VentasCigarrillosByPeriodoFrame vcpf = new VentasCigarrillosByPeriodoFrame();
        vcpf.setVisible(true);
        this.dispose();
    }

    private void codigosBarra() {
        IngresarCodigosBarraFrame icbf = new IngresarCodigosBarraFrame();
        icbf.setVisible(true);
        this.dispose();
    }

    private void reGenerarFcNc() {
        RegenerarFcNcFrame rfnf = new RegenerarFcNcFrame();
        rfnf.setVisible(true);
        this.dispose();
    }
}
