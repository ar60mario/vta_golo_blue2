/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.frame;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.Configuracion;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.Producto;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.entities.RenglonNotaCredito;
import ar.com.gmeventas.main.MainFrame;
import ar.com.gmeventas.services.ClienteService;
import ar.com.gmeventas.services.ConfiguracionService;
import ar.com.gmeventas.services.FacturaService;
import ar.com.gmeventas.services.ProductoService;
import ar.com.gmeventas.services.RenglonFacturaService;
import ar.com.gmeventas.util.DesktopApi;
import ar.com.gmeventas.util.PDFBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import static java.lang.Math.rint;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class FacturaWebFrame extends javax.swing.JFrame {

    public List<RenglonFactura> renglonFactura = new ArrayList<RenglonFactura>();

    private DecimalFormat df_matriz = new DecimalFormat("00000000");
    private static final int qrTamAncho = 140;
    private static final int qrTamAlto = 140;
    private static final String formato = "png";
    private static final String ruta = "c:/qr/codigoQR";
    private static final String extension = ".png";
    private static SimpleDateFormat sdf_qr = new SimpleDateFormat("yyyy-MM-dd");
    private String url_qr = "https://www.afip.gob.ar/fe/qr/?p=";
    private String ver_qr = "1";
    private String fecha_qr;
    private String cuit_qr = "30709520748";
    private String puntoVenta_qr = "3";
    private String tipoComprobante_qr;
    private String numeroComprobante_qr;
    private String importe_qr;
    private String moneda_qr = "PES";
    private String cotiz_qr = "1";
    private String tipoDoc_qr;
    private String numeroDoc_qr;
    private String tipoCodigoAutoriz_qr = "E";
    private String nroCae_qr;

    private String textoFacturaPapel;
    private String tpd = "";
    private String fechaFacturaPapel;
    private String clienteFacturaPapel;
    private String codigoClienteFacturaPapel;
    private String direccionFacturaPapel;
    private String cuitFacturaPapel;
    private String condicionVentaFacturaPapel;
    private String vencimientoFacturaPapel;
    private String inscripcionClienteFacturaPapel;
    private String nombresColumnaFacturaPapel;
    private String letraFacturaPapel;
    private String sucursalFacturaPapel;
    private String numeroFacturaPapel = "0";
    private String[] renglones = null;
    private String texto1FacturaPapel;
    private String texto2FacturaPapel;
    private String texto3FacturaPapel;
    private String totalDeudaFacturaPapel;
    private String lineaTotalesFacturaPapel;
    private String totalPagarFacturaPapel;
    private String importeTotalFacturaPapel;
    private String cantidadesFacturaPapel;
    private Date fecha;
    private SimpleDateFormat sdf;
    private Cliente clienteFactura = null;
    private Producto producto = null;
    private DefaultTableModel tabla = null;
    private Double totalFactura = 0.00;
    private Double totalImpuesto = 0.00;
    private Double totalIva = 0.00;
    private Double totalGravado = 0.00;
    private Double totalNoGravado = 0.0;
    private Double gravado = 0.00;
    private Double noGravado = 0.00;
    private Double iva = 0.00;
    private Float porcentualIva;
    private Double impuesto = null;
    private Double totalLinea = 0.00;
    private Boolean tieneDto = false;
    private Cliente clienteSeleccionado;
    private Producto productoSeleccionado;
    private Float cantidad;
    private Integer categoriaIva = 4;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private DecimalFormat df1 = new DecimalFormat("#0");
    private Double saldoCliente = 0.00;
    private String filtro = "";
    private String letraFactura;
    private Integer sucursalFactura;
    private Integer numeroFactura;
    private Configuracion config = null;
    private Double precioFinal = 0.0;
    private Integer nro = 0;
    private Integer maxNro = 41;
    private Integer cantidadAtadosMassalin;
    private Integer cantidadAtadosNobleza;
    public Boolean encontrado;
    private String numCae = "0";
    private String texto1Cae = "";
    private String texto2Cae = "";
    private String vencCae = "";
    private String tipoComprob = "";
    private Integer tst = 0; // 1 esta en test

    /**
     * Creates new form FacturaFrame
     */
    public FacturaWebFrame() {
        getContentPane().setBackground(new java.awt.Color(135, 206, 235));
        initComponents();
        this.setLocationRelativeTo(null);
        limpiarCampos();
        bloquearCampos();
        tabla = (DefaultTableModel) tablaFactura.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFactura = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        codigoProductoTxt = new javax.swing.JTextField();
        codigoBarrasTxt = new javax.swing.JTextField();
        cantidadTxt = new javax.swing.JTextField();
        codigoTxt = new javax.swing.JTextField();
        razonSocialTxt = new javax.swing.JTextField();
        fechaTxt = new javax.swing.JTextField();
        ivaTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        agregarBtn = new javax.swing.JButton();
        totalTxt = new javax.swing.JTextField();
        terminarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        buscarClienteBtn = new javax.swing.JButton();
        incorporarAFacturaBtn = new javax.swing.JButton();
        eliminarItemBtn = new javax.swing.JButton();
        buscarClienteXNombre = new javax.swing.JButton();
        buscarProductoXNombreBtn = new javax.swing.JButton();
        descuentoGlobalLbl = new javax.swing.JLabel();
        descuentoGlobalTxt = new javax.swing.JTextField();
        descuentoLineaLbl = new javax.swing.JLabel();
        descuentoLineaTxt = new javax.swing.JTextField();
        comboClientes = new javax.swing.JComboBox();
        comboProductos = new javax.swing.JComboBox();
        nombreClienteABuscarTxt = new javax.swing.JTextField();
        nombreProductoABuscarTxt = new javax.swing.JTextField();
        descuentoBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        descuentoVolumenTxt = new javax.swing.JTextField();
        texto1PieFacturaTxt = new javax.swing.JTextField();
        texto2PieFacturaTxt = new javax.swing.JTextField();
        volverBtn = new javax.swing.JButton();
        nombreProductoConsultaTxt = new javax.swing.JTextField();
        precioProductoConsultaTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cantidadAtadosMassalinTxt = new javax.swing.JTextField();
        cantidadAtadosNoblezaTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cantidadItemsTxt = new javax.swing.JTextField();
        nuevaCantidadTxt = new javax.swing.JTextField();
        leerCantidadBtn = new javax.swing.JButton();
        grabarCantidadBtn = new javax.swing.JButton();
        leerPrecioBtn = new javax.swing.JButton();
        grabarPrecioBtn = new javax.swing.JButton();
        nuevoPrecioTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        importeMassalinTxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        importeNoblezaTxt = new javax.swing.JTextField();
        imprimeChk = new javax.swing.JCheckBox();
        pdfChk = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Golosol S.A. Factura Web");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cant", "Detalle", "P.Unit", "Gravado", "Impuesto", "IVA", "dto.", "SubTotal", "Sug."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaFactura);
        if (tablaFactura.getColumnModel().getColumnCount() > 0) {
            tablaFactura.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaFactura.getColumnModel().getColumn(1).setPreferredWidth(10);
            tablaFactura.getColumnModel().getColumn(2).setPreferredWidth(330);
            tablaFactura.getColumnModel().getColumn(3).setPreferredWidth(20);
            tablaFactura.getColumnModel().getColumn(4).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(25);
            tablaFactura.getColumnModel().getColumn(7).setPreferredWidth(20);
            tablaFactura.getColumnModel().getColumn(8).setPreferredWidth(30);
            tablaFactura.getColumnModel().getColumn(9).setPreferredWidth(20);
        }

        jLabel1.setText("Cliente:");

        jLabel2.setText("Iva:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Código:");

        jLabel6.setText("C.Barras:");

        jLabel7.setText("Cantidad:");

        codigoProductoTxt.setText("CODIGO P");
        codigoProductoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoProductoTxtKeyPressed(evt);
            }
        });

        codigoBarrasTxt.setText("COD BARRAS");
        codigoBarrasTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoBarrasTxtKeyPressed(evt);
            }
        });

        cantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadTxt.setText("CANT");
        cantidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadTxtKeyPressed(evt);
            }
        });

        codigoTxt.setText("CODIGO");
        codigoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoTxtKeyPressed(evt);
            }
        });

        razonSocialTxt.setText("RAZON SOCIAL");

        fechaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fechaTxt.setText("FECHA");

        ivaTxt.setText("IVA");

        jLabel8.setText("TOTAL FACTURA:");

        agregarBtn.setText("Agregar otro Item");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });
        agregarBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                agregarBtnKeyPressed(evt);
            }
        });

        totalTxt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        totalTxt.setForeground(new java.awt.Color(204, 0, 0));
        totalTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTxt.setText("TOTAL");

        terminarBtn.setText("Terminar Factura");
        terminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar Factura");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        buscarClienteBtn.setText("Buscar");
        buscarClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteBtnActionPerformed(evt);
            }
        });

        incorporarAFacturaBtn.setText("Incorporar a Factura");
        incorporarAFacturaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorporarAFacturaBtnActionPerformed(evt);
            }
        });

        eliminarItemBtn.setText("Eliminar Item seleccionado");
        eliminarItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarItemBtnActionPerformed(evt);
            }
        });

        buscarClienteXNombre.setText("Buscar x nombre");
        buscarClienteXNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteXNombreActionPerformed(evt);
            }
        });

        buscarProductoXNombreBtn.setText("Buscar x nombre");
        buscarProductoXNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoXNombreBtnActionPerformed(evt);
            }
        });

        descuentoGlobalLbl.setText("Descuento:");

        descuentoGlobalTxt.setText("DESCUENTO");

        descuentoLineaLbl.setText("Dscto:");

        descuentoLineaTxt.setText("DESCUENTO");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });
        comboClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboClientesKeyPressed(evt);
            }
        });

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductosActionPerformed(evt);
            }
        });
        comboProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboProductosKeyPressed(evt);
            }
        });

        nombreClienteABuscarTxt.setText("NOMBRE CLIENTE A BUSCAR");
        nombreClienteABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreClienteABuscarTxtKeyPressed(evt);
            }
        });

        nombreProductoABuscarTxt.setText("NOMBRE PRODUCTO A BUSCAR");
        nombreProductoABuscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreProductoABuscarTxtKeyPressed(evt);
            }
        });

        descuentoBtn.setText("Dto");

        jLabel12.setText("Descuento");

        descuentoVolumenTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        descuentoVolumenTxt.setText("DESCEUNTO");

        texto1PieFacturaTxt.setText("TEXTO 1 PIE FACTURA");

        texto2PieFacturaTxt.setText("TEXTO 2 PIE FACTURA");

        volverBtn.setText("Volver");
        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBtnActionPerformed(evt);
            }
        });

        nombreProductoConsultaTxt.setText("NOMBRE PRODUCTO CONSULTA");

        precioProductoConsultaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        precioProductoConsultaTxt.setText("PRECIO PROD CONSU");

        jLabel15.setText("Tab.Sarandi");

        jLabel17.setText("Nobleza:");

        cantidadAtadosMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosMassalinTxt.setText("Cant.Mass.");

        cantidadAtadosNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadAtadosNoblezaTxt.setText("Cant.Nobl.");

        jLabel18.setText("Cant. Items:");

        cantidadItemsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cantidadItemsTxt.setText("Cantidad Items");

        nuevaCantidadTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevaCantidadTxt.setText("NUE CANT");

        leerCantidadBtn.setText("Leer Cantidad");
        leerCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerCantidadBtnActionPerformed(evt);
            }
        });

        grabarCantidadBtn.setText("Grabar Cantidad");
        grabarCantidadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarCantidadBtnActionPerformed(evt);
            }
        });

        leerPrecioBtn.setText("Leer Precio");
        leerPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerPrecioBtnActionPerformed(evt);
            }
        });

        grabarPrecioBtn.setText("Grabar Precio");
        grabarPrecioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarPrecioBtnActionPerformed(evt);
            }
        });

        nuevoPrecioTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        nuevoPrecioTxt.setText("NUE PREC");
        nuevoPrecioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nuevoPrecioTxtKeyPressed(evt);
            }
        });

        jLabel19.setText("Importe:");

        importeMassalinTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeMassalinTxt.setText("IMP.MASS.");

        jLabel20.setText("Importe:");

        importeNoblezaTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        importeNoblezaTxt.setText("IMP.NOBL.");

        imprimeChk.setText("Imprime");

        pdfChk.setText("PDF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205)
                        .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarClienteXNombre)
                        .addGap(18, 18, 18)
                        .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(94, 94, 94))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarClienteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(descuentoGlobalLbl)
                        .addGap(18, 18, 18)
                        .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(volverBtn)
                        .addGap(0, 59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 111, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(102, 102, 102)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(descuentoLineaLbl)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(548, 548, 548)))
                                .addGap(0, 89, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cancelarBtn)
                            .addComponent(terminarBtn))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(249, 249, 249)
                                        .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buscarProductoXNombreBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(38, 38, 38)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(125, 125, 125)
                                                                        .addComponent(leerCantidadBtn)
                                                                        .addGap(18, 18, 18))
                                                                    .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(16, 16, 16))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(incorporarAFacturaBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(agregarBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(eliminarItemBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(leerPrecioBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 707, Short.MAX_VALUE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(descuentoBtn)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(grabarCantidadBtn))
                                            .addComponent(grabarPrecioBtn))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pdfChk)
                                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(imprimeChk))))))
                        .addGap(11, 11, 11))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(codigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razonSocialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteBtn)
                    .addComponent(descuentoGlobalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoGlobalLbl)
                    .addComponent(volverBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ivaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarClienteXNombre)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreClienteABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProductoABuscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProductoXNombreBtn)
                    .addComponent(jLabel6)
                    .addComponent(codigoBarrasTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descuentoBtn)
                    .addComponent(jLabel12)
                    .addComponent(descuentoVolumenTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codigoProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cantidadItemsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descuentoLineaLbl)
                    .addComponent(descuentoLineaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioProductoConsultaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leerCantidadBtn)
                    .addComponent(nuevaCantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grabarCantidadBtn)
                    .addComponent(imprimeChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBtn)
                    .addComponent(eliminarItemBtn)
                    .addComponent(incorporarAFacturaBtn)
                    .addComponent(grabarPrecioBtn)
                    .addComponent(nuevoPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leerPrecioBtn)
                    .addComponent(pdfChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2PieFacturaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(cantidadAtadosMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadAtadosNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(importeMassalinTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(importeNoblezaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteBtnActionPerformed
        buscar();
        cancelarBtn.setEnabled(true);
    }//GEN-LAST:event_buscarClienteBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
        // 0 = si; 1 = no
        if (escape == 0) {
            //guardarRepositorio();
            limpiarCampos();
            bloquearCampos();
            borrarTablaProductos();
            volverBtn.setEnabled(true);
            buscarClienteBtn.setEnabled(true);
            codigoTxt.requestFocus();
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(false);
            nuevoPrecioTxt.setEnabled(false);
            nombreClienteABuscarTxt.setEnabled(true);
            buscarClienteXNombre.setEnabled(true);
            comboClientes.setEnabled(true);
        } else {
            incorporarAFacturaBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(true);
            agregarBtn.setEnabled(true);
            agregarBtn.requestFocus();
            codigoBarrasTxt.setEnabled(false);
            codigoProductoTxt.setEnabled(false);
            cantidadTxt.setEnabled(false);
            nombreProductoABuscarTxt.setEnabled(false);
            comboProductos.setEnabled(false);
            grabarPrecioBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(false);
            nuevoPrecioTxt.setText("");
            nuevaCantidadTxt.setText("");
            nuevaCantidadTxt.setEnabled(true);
            nuevoPrecioTxt.setEnabled(true);
            leerPrecioBtn.setEnabled(true);
            leerCantidadBtn.setEnabled(true);
            terminarBtn.setEnabled(true);
            tablaFactura.setEnabled(true);
            //nombreProductoConsultaTxt.setEnabled(false);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        eliminarItemBtn.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void incorporarAFacturaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorporarAFacturaBtnActionPerformed
        if (!cantidadTxt.getText().isEmpty()) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                buscarProducto();
            } else {
                if (!codigoProductoTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                    codigoBarrasTxt.requestFocus();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
            cantidadTxt.requestFocus();
        }
    }//GEN-LAST:event_incorporarAFacturaBtnActionPerformed

    private void buscarClienteXNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteXNombreActionPerformed
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        llenarComboClientes();
        comboClientes.addFocusListener(null);
        comboClientes.showPopup();
    }//GEN-LAST:event_buscarClienteXNombreActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        if (evt.getModifiers() == 16) {
            if (comboClientes.getSelectedIndex() > 0) {
                nombreClienteABuscarTxt.setText("");
                try {
                    Integer seleccion = comboClientes.getSelectedIndex();
                    clienteSeleccionado = new ClienteService().getClientesByFiltro(filtro).get(seleccion - 1);
                    codigoTxt.setText(clienteSeleccionado.getCodigo());
                    buscar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar cliente");
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboClientesActionPerformed

    private void buscarProductoXNombreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoXNombreBtnActionPerformed
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        llenarComboProductos();
        comboProductos.addFocusListener(null);
        comboProductos.showPopup();
    }//GEN-LAST:event_buscarProductoXNombreBtnActionPerformed

    private void comboProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductosActionPerformed
        if (evt.getModifiers() == 16) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombreBtn.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltro(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosActionPerformed

    private void terminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarBtnActionPerformed
        int escape = 1; //JOptionPane.showConfirmDialog(null, "Quiere ingresar un texto antes de Imprimir?",
        //"Texto en el pie de Pedido",
        //JOptionPane.YES_NO_OPTION);
        if (escape == 0) {
            texto1PieFacturaTxt.setEnabled(true);
            texto2PieFacturaTxt.setEnabled(true);
            texto1PieFacturaTxt.requestFocus();
        } else {
            terminarBtn.setEnabled(false);
            terminarFactura();
        }
    }//GEN-LAST:event_terminarBtnActionPerformed

    private void volverBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBtnActionPerformed
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverBtnActionPerformed

    private void codigoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoTxt.getText().isEmpty()) {
                buscar();
                cancelarBtn.setEnabled(true);
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                FacturarFrame ff = new FacturarFrame();
                ff.setVisible(true);
                this.dispose();
            } else {
                if (evt.getKeyCode() != 8) {
                    if (!isNumeric(evt)) {
                        JOptionPane.showMessageDialog(this, "Solo números");
                        codigoTxt.setText("");
                    }

                }
            }
        }
    }//GEN-LAST:event_codigoTxtKeyPressed

    private void codigoProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoProductoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoProductoTxt.getText().isEmpty()) {
                consultarProducto();
                incorporarAFacturaBtn.setEnabled(true);
            } else {
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la carga factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape != 1) {
                    limpiarCampos();
                    bloquearCampos();
                    borrarTablaProductos();
                    volverBtn.setEnabled(true);
                    buscarClienteXNombre.setEnabled(true);
                    nombreClienteABuscarTxt.setEnabled(true);
                    comboClientes.setEnabled(true);
                    codigoTxt.requestFocus();
                } else {
                    codigoProductoTxt.setText("");
                    codigoProductoTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_codigoProductoTxtKeyPressed

    private void cantidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!cantidadTxt.getText().isEmpty()) {
                Long cax = Long.valueOf(cantidadTxt.getText());
                if (cax > 2000) {
                    cantidadTxt.setText("");
                    cantidadTxt.requestFocus();
                    return;
                }
                if (!codigoBarrasTxt.getText().isEmpty()) {
                    buscarProducto();
                } else {
                    if (!codigoProductoTxt.getText().isEmpty()) {
                        buscarProducto();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese Código Barras o Código producto");
                        codigoBarrasTxt.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad");
                cantidadTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                codigoBarrasTxt.setText("");
                codigoBarrasTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_cantidadTxtKeyPressed

    private void eliminarItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarItemBtnActionPerformed
        int selectRow = tablaFactura.getSelectedRow();
        if (selectRow != -1) {
            int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar Item?", "Atencion", JOptionPane.YES_NO_OPTION);
            // 0 = si; 1 = no
            if (escape == 0) {
                tabla.removeRow(selectRow);
                renglonFactura.remove(selectRow);
                calcularTotales();
                agregarBtn.requestFocus();
                if (nro > 0) {
                    terminarBtn.setEnabled(true);
                } else {
                    terminarBtn.setEnabled(false);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto para eliminar");
        }
    }//GEN-LAST:event_eliminarItemBtnActionPerformed

    private void agregarBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agregarBtnKeyPressed
        eliminarItemBtn.setEnabled(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        codigoBarrasTxt.setText("");
        agregarProducto();
    }//GEN-LAST:event_agregarBtnKeyPressed

    private void nombreClienteABuscarTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreClienteABuscarTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboClientes.removeAllItems();
            comboClientes.addItem("");
            llenarComboClientes();
            comboClientes.requestFocus();
            comboClientes.addFocusListener(null);
            comboClientes.showPopup();
        }
    }//GEN-LAST:event_nombreClienteABuscarTxtKeyPressed

    private void nombreProductoABuscarTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoABuscarTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!nombreProductoABuscarTxt.getText().isEmpty()) {
                comboProductos.removeAllItems();
                comboProductos.addItem("");
                llenarComboProductos();
                comboProductos.requestFocus();
                comboProductos.addFocusListener(null);
                comboProductos.showPopup();
            } else {
                codigoProductoTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_nombreProductoABuscarTxtKeyPressed

    private void codigoBarrasTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrasTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!codigoBarrasTxt.getText().isEmpty()) {
                consultarProductoBarras();

            } else {
                nombreProductoABuscarTxt.requestFocus();
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                int escape = JOptionPane.showConfirmDialog(null, "Esta seguro de abandonar la factura?", "Atencion", JOptionPane.YES_NO_OPTION);
                if (escape == 0) {
                    limpiarCampos();
                    bloquearCampos();
                    borrarTablaProductos();
                    volverBtn.setEnabled(true);
                    codigoTxt.requestFocus();
                } else {
                    codigoProductoTxt.setText("");
                    codigoBarrasTxt.setText("");
                    codigoBarrasTxt.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_codigoBarrasTxtKeyPressed

    private void leerCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
            nuevaCantidadTxt.setText(String.valueOf(rf.getCantidad().intValue()));
            leerCantidadBtn.setEnabled(false);
            grabarCantidadBtn.setEnabled(true);
            tablaFactura.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            leerPrecioBtn.setEnabled(false);
            nuevoPrecioTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }//GEN-LAST:event_leerCantidadBtnActionPerformed

    private void grabarCantidadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarCantidadBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            if (!nuevaCantidadTxt.getText().isEmpty()) {
                leerCantidadBtn.setEnabled(true);
                grabarCantidadBtn.setEnabled(false);
                RenglonFactura rf = renglonFactura.get(lin);
                Float cantidadAnterior = rf.getCantidad();
                Float cant = Float.valueOf(nuevaCantidadTxt.getText());
                rf.setCantidad(cant);
                Double precioUnitario = rf.getGravado() / cantidadAnterior;
                Double impuestoUnitario = rf.getImpuesto() / cantidadAnterior;
                renglonFactura.set(lin, rf);
                tablaFactura.setValueAt(cant.intValue(), lin, 1);

                calcularLinea(cant, precioUnitario, impuestoUnitario.floatValue());

                tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                nuevaCantidadTxt.setText("");

                rf.setGravado(gravado);
                rf.setImpuesto(impuesto);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaFactura.setValueAt(df.format(gravado), lin, 4);
                tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                tablaFactura.setValueAt(df.format(iva), lin, 6);
                tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
                leerPrecioBtn.setEnabled(true);
                nuevoPrecioTxt.setEnabled(true);
                nuevaCantidadTxt.setEnabled(true);
                leerCantidadBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                agregarBtn.setEnabled(true);
                tablaFactura.setEnabled(true);
                agregarBtn.requestFocus();
                calcularTotales();
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad");
                nuevaCantidadTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_grabarCantidadBtnActionPerformed

    private void leerPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerPrecioBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            RenglonFactura rf = renglonFactura.get(lin);
            int cod = rf.getProducto().getCodigo();
            Producto pro = null;
            try {
                pro = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            int codRub = pro.getRubro().getCodigo();
            if (codRub < 3) {
                JOptionPane.showMessageDialog(this, "No puede modificar Precio de este producto");
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                agregarBtn.requestFocus();
                return;
            }
            Float cant = rf.getCantidad();
            Double iva = rf.getIva() / cant;
            Double prec = rf.getGravado() / cant;
            nuevoPrecioTxt.setEnabled(true);
            nuevoPrecioTxt.setText(String.valueOf(df.format(prec + iva)));
            leerPrecioBtn.setEnabled(false);
            grabarPrecioBtn.setEnabled(true);
            nuevoPrecioTxt.requestFocus();
            tablaFactura.setEnabled(false);
            agregarBtn.setEnabled(false);
            eliminarItemBtn.setEnabled(false);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEnabled(false);
            terminarBtn.setEnabled(false);
        }
    }//GEN-LAST:event_leerPrecioBtnActionPerformed

    private void comboClientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboClientesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboClientes.getSelectedIndex() > 0) {
                nombreClienteABuscarTxt.setText("");
                try {
                    Integer seleccion = comboClientes.getSelectedIndex();
                    clienteSeleccionado = new ClienteService().getClientesByFiltro(filtro).get(seleccion - 1);
                    codigoTxt.setText(clienteSeleccionado.getCodigo());
                    buscar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar cliente");
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            comboClientes.removeAllItems();
        }
    }//GEN-LAST:event_comboClientesKeyPressed

    private void comboProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboProductos.getSelectedIndex() > 0) {
                nombreProductoABuscarTxt.setText("");
                nombreProductoABuscarTxt.setEnabled(false);
                buscarProductoXNombreBtn.setEnabled(false);
                try {
                    Integer seleccion = comboProductos.getSelectedIndex();
                    productoSeleccionado = new ProductoService().getProductosByFiltro(filtro).get(seleccion - 1);
                    codigoProductoTxt.setText(String.valueOf(productoSeleccionado.getCodigo()));
                    consultarProducto();
                    cantidadTxt.requestFocus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error - buscar producto");
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_comboProductosKeyPressed

    private void grabarPrecioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarPrecioBtnActionPerformed
        int lin = tablaFactura.getSelectedRow();
        if (lin > -1) {
            if (!nuevoPrecioTxt.getText().isEmpty()) {
                leerPrecioBtn.setEnabled(true);
                grabarPrecioBtn.setEnabled(false);
                leerCantidadBtn.setEnabled(true);
                tablaFactura.setEnabled(true);
                RenglonFactura rf = renglonFactura.get(lin);
                Float cant = rf.getCantidad();
                Double impuestoUnitario = rf.getImpuesto() / cant;
                Double nuevoImporte = Double
                        .valueOf(nuevoPrecioTxt.getText()
                                .replaceAll("\\,", "\\."))
                        / (1 + (porcentualIva / 100));

                calcularLinea(cant, nuevoImporte, impuestoUnitario.floatValue());

                rf.setGravado(gravado);
                rf.setIva(iva);
                rf.setTotal(totalLinea);
                tablaFactura.setValueAt(df.format(precioFinal), lin, 3);
                tablaFactura.setValueAt(df.format(gravado), lin, 4);
                tablaFactura.setValueAt(df.format(impuesto), lin, 5);
                tablaFactura.setValueAt(df.format(iva), lin, 6);
                tablaFactura.setValueAt(df.format(totalLinea), lin, 8);
                nuevoPrecioTxt.setText("");
                nuevoPrecioTxt.setEnabled(true);
                nuevaCantidadTxt.setEnabled(true);
                eliminarItemBtn.setEnabled(true);
                terminarBtn.setEnabled(true);
                agregarBtn.setEnabled(true);
                agregarBtn.requestFocus();
                calcularTotales();
            }
        }
    }//GEN-LAST:event_grabarPrecioBtnActionPerformed

    private void nuevoPrecioTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nuevoPrecioTxtKeyPressed
        if (evt.getKeyCode() != 8 && evt.getKeyCode() != 44 && evt.getKeyCode() != 110) {
            if (!isNumeric(evt)) {
                JOptionPane.showMessageDialog(this, "Solo números");
                nuevoPrecioTxt.setText("");
                nuevoPrecioTxt.requestFocus();
            }
        }
    }//GEN-LAST:event_nuevoPrecioTxtKeyPressed

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
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturaWebFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturaWebFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JButton buscarClienteBtn;
    private javax.swing.JButton buscarClienteXNombre;
    private javax.swing.JButton buscarProductoXNombreBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cantidadAtadosMassalinTxt;
    private javax.swing.JTextField cantidadAtadosNoblezaTxt;
    private javax.swing.JTextField cantidadItemsTxt;
    private javax.swing.JTextField cantidadTxt;
    private javax.swing.JTextField codigoBarrasTxt;
    private javax.swing.JTextField codigoProductoTxt;
    private javax.swing.JTextField codigoTxt;
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JComboBox comboProductos;
    private javax.swing.JButton descuentoBtn;
    private javax.swing.JLabel descuentoGlobalLbl;
    private javax.swing.JTextField descuentoGlobalTxt;
    private javax.swing.JLabel descuentoLineaLbl;
    private javax.swing.JTextField descuentoLineaTxt;
    private javax.swing.JTextField descuentoVolumenTxt;
    private javax.swing.JButton eliminarItemBtn;
    private javax.swing.JTextField fechaTxt;
    private javax.swing.JButton grabarCantidadBtn;
    private javax.swing.JButton grabarPrecioBtn;
    private javax.swing.JTextField importeMassalinTxt;
    private javax.swing.JTextField importeNoblezaTxt;
    private javax.swing.JCheckBox imprimeChk;
    private javax.swing.JButton incorporarAFacturaBtn;
    private javax.swing.JTextField ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leerCantidadBtn;
    private javax.swing.JButton leerPrecioBtn;
    private javax.swing.JTextField nombreClienteABuscarTxt;
    private javax.swing.JTextField nombreProductoABuscarTxt;
    private javax.swing.JTextField nombreProductoConsultaTxt;
    private javax.swing.JTextField nuevaCantidadTxt;
    private javax.swing.JTextField nuevoPrecioTxt;
    private javax.swing.JCheckBox pdfChk;
    private javax.swing.JTextField precioProductoConsultaTxt;
    private javax.swing.JTextField razonSocialTxt;
    private javax.swing.JTable tablaFactura;
    private javax.swing.JButton terminarBtn;
    private javax.swing.JTextField texto1PieFacturaTxt;
    private javax.swing.JTextField texto2PieFacturaTxt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton volverBtn;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        texto1PieFacturaTxt.setText("");
        texto2PieFacturaTxt.setText("");
        cantidadAtadosMassalinTxt.setText("");
        cantidadAtadosNoblezaTxt.setText("");
        ivaTxt.setText("");
        codigoBarrasTxt.setText("");
        codigoProductoTxt.setText("");
        codigoTxt.setText("");
        fechaTxt.setText("");
        razonSocialTxt.setText("");
        cantidadTxt.setText("");
        totalTxt.setText("");
        totalTxt.setText("0.00");
        comboClientes.removeAllItems();
        comboClientes.addItem("");
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        nombreClienteABuscarTxt.setText("");
        nombreProductoABuscarTxt.setText("");
        descuentoVolumenTxt.setText("0.00");
        descuentoLineaTxt.setText("");
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        nombreProductoConsultaTxt.setText("");
        precioProductoConsultaTxt.setText("");
        cantidadItemsTxt.setText("");
        descuentoGlobalTxt.setText("");
        nuevaCantidadTxt.setText("");
        nuevoPrecioTxt.setText("");
        nuevoPrecioTxt.setEnabled(false);
        importeMassalinTxt.setText("");
        importeNoblezaTxt.setText("");
        importeMassalinTxt.setEditable(false);
        importeNoblezaTxt.setEditable(false);
        imprimeChk.setSelected(true);
    }

    private void bloquearCampos() {
        agregarBtn.setEnabled(false);
        cancelarBtn.setEnabled(false);
        terminarBtn.setEnabled(false);
        incorporarAFacturaBtn.setEnabled(false);
        buscarProductoXNombreBtn.setEnabled(false);
        eliminarItemBtn.setEnabled(false);
        descuentoBtn.setEnabled(false);
        ivaTxt.setEditable(false);
        codigoBarrasTxt.setEnabled(false);
        codigoTxt.setEnabled(true);
        codigoTxt.setEditable(true);
        codigoProductoTxt.setEnabled(false);
        fechaTxt.setEditable(false);
        razonSocialTxt.setEditable(false);
        cantidadTxt.setEnabled(false);
        totalTxt.setEnabled(false);
        descuentoVolumenTxt.setEnabled(false);
        nombreProductoABuscarTxt.setEnabled(false);
        nombreProductoConsultaTxt.setEnabled(false);
        descuentoLineaTxt.setEnabled(false);
        comboProductos.setEnabled(false);
        precioProductoConsultaTxt.setEnabled(false);
        cantidadItemsTxt.setEditable(false);
        texto1PieFacturaTxt.setEnabled(false);
        texto2PieFacturaTxt.setEnabled(false);
        cantidadAtadosMassalinTxt.setEditable(false);
        cantidadAtadosNoblezaTxt.setEditable(false);
        descuentoGlobalTxt.setEditable(false);
        nuevaCantidadTxt.setEditable(false);
        nuevaCantidadTxt.setEnabled(false);
        leerCantidadBtn.setEnabled(false);
        grabarCantidadBtn.setEnabled(false);
        leerPrecioBtn.setEnabled(false);
        grabarPrecioBtn.setEnabled(false);
    }

    private void llenarComboClientes() {
        filtro = nombreClienteABuscarTxt.getText();
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            clientes = new ClienteService().getClientesByFiltro(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Clientes");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboClientes.getModel();
        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cli : clientes) {
                model.addElement(cli.getRazonSocial());
            }
            comboClientes.setModel(model);
        }
    }

    private void llenarComboProductos() {
        filtro = nombreProductoABuscarTxt.getText();
        List<Producto> productos = new ArrayList<Producto>();
        try {
            productos = new ProductoService().getProductosByFiltro(filtro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - leyendo Productos");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) comboProductos.getModel();
        if (productos != null && !productos.isEmpty()) {
            for (Producto pro : productos) {
                model.addElement(pro.getCodigo() + " " + pro.getDetalle());
            }
            comboProductos.setModel(model);
        }
    }

    private void buscar() {
        filtro = "";
        Long id = (long) 1;
        try {
            Configuracion conf = new ConfiguracionService().getFacturas(id);
            porcentualIva = conf.getIva();
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        fecha = Calendar.getInstance().getTime();
        Cliente cli = new Cliente();
        try {
            clienteFactura = new ClienteService().getClienteByCodigo(codigoTxt.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error - cliente");
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteFactura != null) {
            razonSocialTxt.setText(clienteFactura.getRazonSocial());
            fechaTxt.setText(sdf.format(fecha));
            if (clienteFactura.getCuit().length() != 13) {
                String tip = clienteFactura.getTipo();
                if (!tip.equals("96")) {
                    JOptionPane.showMessageDialog(this, "Debe verificar la CUIT "
                            + "del Cliente: \n" + clienteFactura.getRazonSocial()
                            + "\nCodigo: " + clienteFactura.getCodigo() + " "
                            + clienteFactura.getCuit().length() + ""
                            + clienteFactura.getCuit());
                    MainFrame mf = new MainFrame();
                    mf.setVisible(true);
                    this.dispose();
                }
            }
            if (clienteFactura.getCategoriaDeIva() != null) {
                categoriaIva = clienteFactura.getCategoriaDeIva();
                if (categoriaIva.equals(1)) {
                    ivaTxt.setText("Resp. Inscripto");
                }
                if (categoriaIva.equals(2)) {
                    ivaTxt.setText("Monotributo");
                }
                if (categoriaIva.equals(3)) {
                    ivaTxt.setText("Exento");
                }
                if (categoriaIva.equals(4)) {
                    ivaTxt.setText("Consumidor Final");
                }
            } else {
                ivaTxt.setText("Consumidor Final");
            }
            if (clienteFactura.getSaldo() != null) {
                saldoCliente = clienteFactura.getSaldo();
            } else {
                saldoCliente = 0.0;
            }
            Boolean terminar = false;
            agregarBtn.setEnabled(true);
            buscarClienteBtn.setEnabled(false);
            volverBtn.setEnabled(false);
            cancelarBtn.setEnabled(true);
            buscarClienteXNombre.setEnabled(false);
            nombreClienteABuscarTxt.setEnabled(false);
            comboClientes.setEnabled(false);
            nombreProductoConsultaTxt.setEditable(false);
            precioProductoConsultaTxt.setEditable(false);
            codigoTxt.setEditable(false);
            descuentoVolumenTxt.setEnabled(true);
            descuentoVolumenTxt.setEditable(false);
            totalTxt.setEnabled(true);
            totalTxt.setEditable(false);
            leerCantidadBtn.setEnabled(false);
            nuevaCantidadTxt.setEditable(true);
            nuevaCantidadTxt.setEnabled(false);
            agregarProducto();
        } else {
            JOptionPane.showMessageDialog(this, "Error - cliente no existe");
            buscarClienteBtn.setEnabled(true);
            codigoTxt.requestFocus();
        }
    }

    private void terminarFactura() {
        sucursalFacturaPapel = "1";
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            letraFactura = "A";
        } else {
            letraFactura = "B";
        }
        letraFacturaPapel = letraFactura;
        // presentacion web
        if (tst == 0) {
            try {
                LibraryLoader.loadJacobLibrary();
                ActiveXComponent wsaa = new ActiveXComponent("WSAA");

                String wsdl = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
                String userdir = "c:/certifgolo";
                Dispatch.call(wsaa, "Autenticar",
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
                Dispatch.call(wsfev1, "Conectar",
                        new Variant(cache),
                        new Variant(wsdl)
                );
                String tipo_cbte = "1";
                if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                    tipo_cbte = "1"; //Factura A
                    letraFacturaPapel = "A";
                } else {
                    tipo_cbte = "6"; //Factura B
                    letraFacturaPapel = "B";
                }
                tipoComprob = tipo_cbte;
                String pto_vta = "3"; // Sucursal declarada WS
                sucursalFacturaPapel = "0003";
                Variant ult = Dispatch.call(wsfev1, "CompUltimoAutorizado",
                        new Variant(tipo_cbte),
                        new Variant(pto_vta));
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                JOptionPane.showMessageDialog(this, "Ult.Comprb." + ult.toString());
                String fechaWs = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String concepto = "1";// producto 
                String tipoD = clienteFactura.getTipo();
                String cui = clienteFactura.getCuit();
                String cuit1;
                tpd = "80";
                if (tipoD.equals("96")) {
                    cuit1 = cui.trim();
                    tpd = "96";
                } else {
                    cuit1 = cui.substring(0, 2) + cui.substring(3, 11) + cui.substring(12, 13);
                }

                String tipo_doc = tipoD, nro_doc = cuit1; //tipo y numero
                int cbte_nro = Integer.parseInt(ult.toString()) + 1,
                        cbt_desde = cbte_nro,
                        cbt_hasta = cbte_nro;
                numeroFacturaPapel = String.valueOf(cbte_nro);
                int largo = ("00000000" + numeroFacturaPapel).length();
                numeroFacturaPapel = ("00000000" + numeroFacturaPapel).substring(largo - 8, largo);
                String imp_total = df.format(totalFactura).toString().replaceAll("\\,", "\\.");//"124.00";
                String imp_tot_conc = "0.00";
                String imp_neto = df.format(totalGravado).toString().replaceAll("\\,", "\\.");
                String imp_iva = df.format(totalIva).toString().replaceAll("\\,", "\\.");//"21.00"
                int internos = (int) rint(totalImpuesto * 100);
                String imp_trib = "", imp_op_ex = "0.00";
                if (internos > 0) {
                    imp_trib = df.format(totalImpuesto).toString().replaceAll("\\,", "\\.");
                } else {
                    imp_trib = "0.00";
                }
                String fecha_cbte = fechaWs, fecha_venc_pago = "";
                String fecha_serv_desde = "", fecha_serv_hasta = "";
                String moneda_id = "PES", moneda_ctz = "1.000";
                Variant ok = Dispatch.call(wsfev1, "CrearFactura",
                        new Variant(concepto), new Variant(tipo_doc),
                        new Variant(nro_doc), new Variant(tipo_cbte),
                        new Variant(pto_vta),
                        new Variant(cbt_desde), new Variant(cbt_hasta),
                        new Variant(imp_total), new Variant(imp_tot_conc),
                        new Variant(imp_neto), new Variant(imp_iva),
                        new Variant(imp_trib), new Variant(imp_op_ex),
                        new Variant(fecha_cbte), new Variant(fecha_venc_pago),
                        new Variant(fecha_serv_desde), new Variant(fecha_serv_hasta),
                        new Variant(moneda_id), new Variant(moneda_ctz));
                if (internos > 0) {
                    Variant tributo_id = new Variant(4),
                            tributo_desc = new Variant("Impuestos internos"),
                            tributo_base_imp = new Variant("0.00"),
                            tributo_alic = new Variant("0.00"),
                            tributo_importe = new Variant(df.format(totalImpuesto).toString().replaceAll("\\,", "\\."));
                    Dispatch.call(wsfev1, "AgregarTributo",
                            tributo_id, tributo_desc, tributo_base_imp,
                            tributo_alic, tributo_importe);
                }
                Variant iva_id = new Variant(5),
                        iva_base_imp = new Variant(df.format(totalGravado).toString().replaceAll("\\,", "\\.")),
                        iva_importe = new Variant(df.format(totalIva).toString().replaceAll("\\,", "\\."));
                Dispatch.call(wsfev1, "AgregarIva",
                        iva_id, iva_base_imp, iva_importe);
                Dispatch.put(wsfev1, "Reprocesar", new Variant(false));
                Variant cae = Dispatch.call(wsfev1, "CAESolicitar");
                String requ = Dispatch.get(wsfev1, "XmlRequest").toString();
                String resp = Dispatch.get(wsfev1, "XmlResponse").toString();
                excepcion = Dispatch.get(wsfev1, "Excepcion").toString();
                String errmsg = Dispatch.get(wsfev1, "ErrMsg").toString();
                String obs = Dispatch.get(wsfev1, "Obs").toString();
                String vto = Dispatch.get(wsfev1, "Vencimiento").toString();
                String resultado = Dispatch.get(wsfev1, "Resultado").toString();
                numCae = cae.toString();
                if (!resultado.equals("A")) {
                    JOptionPane.showMessageDialog(this, "Obs: " + obs + "\nError: " + errmsg);
                    return;
                }
                if (vto != "" && vto != null) {
                    vencCae = vto.substring(6, 8) + "/" + vto.substring(4, 6) + "/" + vto.substring(0, 4);
                }
                numCae = cae.toString();
                String ruta1 = "c:/comprobantes/" + tipoComprob
                        + letraFacturaPapel + sucursalFacturaPapel
                        + numeroFacturaPapel + ".xm1";
                String ruta2 = "c:/comprobantes/" + tipoComprob
                        + letraFacturaPapel + sucursalFacturaPapel
                        + numeroFacturaPapel + ".xm2";
                File archivo1 = new File(ruta1);
                File archivo2 = new File(ruta2);
                BufferedWriter bw1, bw2;
                bw1 = new BufferedWriter(new FileWriter(archivo1));
                bw2 = new BufferedWriter(new FileWriter(archivo2));
                bw1.write(requ);
                bw2.write(resp);
                bw1.close();
                bw2.close();
                int x = 0;
                if (tipoD.equals("96")) {
                    String s = "0000000000" + cuit1;
                    int lar = s.length();
                    cuit1 = s.substring(lar - 11, lar);
                }
                Integer suma1 = 0;
                Integer suma2 = 0;
                String cadena = cuit1 + "0" + tipo_cbte + "0003" + cae + vto;
                for (int i = 0; i < 39; i++) {
                    if (x == 0) {
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma1 += num;
                        x = 1;
                    } else {
                        int num = Integer.valueOf(cadena.substring(i, i + 1).toString());
                        suma2 += num;
                        x = 0;
                    }
                }
                suma1 = suma1 * 3;
                int total = suma1 + suma2;
                int dv = (int) (rint(total / 10 + .9) * 10);
                dv = dv - total;
                cadena += String.valueOf(dv);
                String txtCadenaRP = "";
                for (int i = 0; i < 40; i = i + 2) {
                    String charNum = cadena.substring(i, i + 2);
                    int numChar = Integer.valueOf(charNum);
                    if (numChar < 50) {
                        numChar += 48;
                    } else {
                        numChar += 142;
                    }
                    char c = (char) numChar;
                    txtCadenaRP = txtCadenaRP + c;
                }
                txtCadenaRP = (char) 40 + txtCadenaRP + (char) 41;
                texto2Cae = txtCadenaRP;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                e.printStackTrace();
                return;
            }
        }
        // fin presentacion web
        IvaVentas ivaVentas = new IvaVentas();
        ivaVentas.setLetraReferencia("");
        ivaVentas.setNumeroSucursalReferencia(3);
        ivaVentas.setNumeroFacturaReferencia(0);
        saldoCliente = clienteFactura.getSaldo();
        saldoCliente += totalFactura;
        clienteFactura.setSaldo(saldoCliente);
        try {
            new ClienteService().updateCliente(clienteFactura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long id = (long) 1;
        try {
            config = new ConfiguracionService().getFacturas(id);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            letraFactura = "A";
            // es inscriptp
            sucursalFactura = config.getSucursalA();
            numeroFactura = config.getNumeroFacturaA();
            numeroFactura += 1;
            config.setNumeroFacturaA(numeroFactura);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            letraFactura = "B";
            // el resto de las categorias
            sucursalFactura = config.getSucursalB();
            numeroFactura = config.getNumeroFacturaB();
            numeroFactura += 1;
            config.setNumeroFacturaB(numeroFactura);
            try {
                new ConfiguracionService().updateConfiguracion(config);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ivaVentas.setCliente(clienteFactura);
        ivaVentas.setDescuentoGlobal(0.0);
        ivaVentas.setExento(0.0);
        ivaVentas.setFecha(fecha);
        try {
            ivaVentas.setFechaCae(sdf.parse(vencCae));
        } catch (ParseException ex) {
            ivaVentas.setFechaCae(fecha);
        }
        ivaVentas.setCae(Long.valueOf(numCae));
        ivaVentas.setGravado(totalGravado);
        ivaVentas.setImpuesto(totalImpuesto);
        ivaVentas.setIva(totalIva);
        ivaVentas.setNoGravado(0.0);
        ivaVentas.setTotal(totalFactura);
        ivaVentas.setLetra(letraFacturaPapel);
        ivaVentas.setNumeroSucursal(Integer.valueOf(sucursalFacturaPapel));
        ivaVentas.setNumeroFactura(Integer.valueOf(numeroFacturaPapel));
//        try {
//            new IvaVentasBO().saveIvaVentas(ivaVentas);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        for (RenglonFactura reFa : renglonFactura) {
            reFa.setIvaVentas(ivaVentas);
            Integer cod = reFa.getProducto().getCodigo();
            try {
                producto = new ProductoService().getProductoByCodigo(cod);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Float stock = (float) 0.0;
            if (producto.getStock() != null) {
                stock = producto.getStock();
            } else {
                stock = (float) 0.0;
            }
            reFa.setProducto(producto);
            stock -= reFa.getCantidad();
            producto.setStock(stock);
//            try {
//                new RenglonFacturaService().saveRenglon(rf);
//            } catch (Exception ex) {
//                Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                new ProductoService().updateProducto(producto);
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            new FacturaService().saveFactura(ivaVentas, renglonFactura);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        CtaCteCliente ccc = new CtaCteCliente();
//        ccc.setCliente(clienteFactura);
//        ccc.setFactura(ivaVentas);
//        ccc.setFecha(fecha);
//        ccc.setDebe(totalFactura);
//        ccc.setHaber(0.0);
//        ccc.setTipo("FC");
//        ccc.setSaldo(saldoCliente);
//        try {
//            new CtaCteClienteService().saveCtaCteCliente(ccc);
//        } catch (Exception ex) {
//            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        generarFactura(ivaVentas);
        MainFrame ff = new MainFrame();
        ff.setVisible(true);
        this.dispose();
    }

    private void generarFactura(IvaVentas i) {
        renglones = new String[maxNro];
        textoFacturaPapel = "FACTURA";
        fechaFacturaPapel = sdf.format(fecha);
        clienteFacturaPapel = razonSocialTxt.getText();
        codigoClienteFacturaPapel = clienteFactura.getDomicilio().getNumero();
        direccionFacturaPapel = clienteFactura.getDomicilio().getCalle() + " " + clienteFactura.getDomicilio().getNumero() + " - " + clienteFactura.getDomicilio().getLocalidad();
        cuitFacturaPapel = clienteFactura.getCuit();
        String condVta = "";
        Date fechaVto = fecha;
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        if (clienteFactura.getFormaDePago().equals(1)) {
            condVta = "CONTADO               ";
        }
        if (clienteFactura.getFormaDePago().equals(2)) {
            condVta = "7 DIAS F.F            ";
            cal.add(Calendar.DATE, 7);
            fechaVto = cal.getTime();
        }
        if (clienteFactura.getFormaDePago().equals(3)) {
            condVta = "14 DIAS F.F.          ";
            cal.add(Calendar.DATE, 14);
            fechaVto = cal.getTime();
        }
        if (clienteFactura.getFormaDePago().equals(4)) {
            condVta = "OTRO                  ";
            fechaVto = null;
        }
        condicionVentaFacturaPapel = condVta;
        vencimientoFacturaPapel = sdf.format(fechaVto);
        String catego = "";
        if (categoriaIva.equals(1)) {
            catego = "Responsable Inscripto       ";
        }
        if (categoriaIva.equals(2)) {
            catego = "Monotributo                 ";
        }
        if (categoriaIva.equals(3)) {
            catego = "Exento                      ";
        }
        if (categoriaIva.equals(4)) {
            catego = "Consumidor Final            ";
        }
        inscripcionClienteFacturaPapel = catego;
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
            nombresColumnaFacturaPapel = "  IT CANT                   DETALLE                    P.UNIT.   GRAVADO      IVA       IMP.     TOTAL     SUG";
            //mbresColumnaFacturaPapel = "  IT CANT                   DETALLE                     P.UNIT.     DESC.    GRAVADO      IVA       IMP.    TOTAL      SUG";
        } else {
            //                                    1         2         3         4         5         6         7         8         9        10
            //                           1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            nombresColumnaFacturaPapel = "  IT CANT                   DETALLE                     P.UNIT.      IMPORTE       IMP.     TOTAL     SUG";
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        int maxTabla = tablaFactura.getRowCount();
        for (int r = 0; r < maxNro; r++) {
            if (r < maxTabla) {
                String str0 = String.valueOf(r + 1);
                int largo = str0.length();
                if (largo < 2) {
                    renglones[r] = " " + str0 + " ";
                } else {
                    renglones[r] = str0 + " ";
                }

                str0 = tablaFactura.getValueAt(r, 1).toString();
                largo = str0.length();
                if (largo == 1) {
                    renglones[r] = renglones[r] + "   " + str0;
                }
                if (largo == 2) {
                    renglones[r] = renglones[r] + "  " + str0;
                }
                if (largo == 3) {
                    renglones[r] = renglones[r] + " " + str0;
                }
                if (largo == 4) {
                    renglones[r] = renglones[r] + str0;
                }
                str0 = tablaFactura.getValueAt(r, 2).toString();
                String espacio = " ";
                largo = str0.length();
                if (largo > 42) {
                    str0 = str0.substring(0, 42);
                    tablaFactura.setValueAt(str0, r, 2);
                } else {
                    for (int l = largo; l < 42; l++) {
                        espacio += " ";
                    }
                }
                renglones[r] = renglones[r] + "  " + tablaFactura.getValueAt(r, 2) + espacio;
                if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
//                  aqui detalle de importes inscripto en IVA           *****
// Precio Unitario
                    str0 = tablaFactura.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
//                    str0 = tablaFactura.getValueAt(r, 7).toString();
//                    str0 = str0.replace(",", ".");
//                    doble = Double.valueOf(str0);
//                    largo = doble.intValue();
//                    espacio = "     ";
//                    largo = String.valueOf(largo).length();
//                    espacio = espacio.substring(largo);
//                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Gravado
                    str0 = tablaFactura.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Iva
                    str0 = tablaFactura.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "     ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaFactura.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
                } else {
                    // aqui detalle importes no inscriptos en IVA
// Precio Unitario
                    str0 = tablaFactura.getValueAt(r, 3).toString();
                    str0 = str0.replace(",", ".");
                    Double doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Descuento
//                    str0 = tablaFactura.getValueAt(r, 7).toString();
//                    str0 = str0.replace(",", ".");
//                    doble = Double.valueOf(str0);
//                    largo = doble.intValue();
//                    espacio = "     ";
//                    largo = String.valueOf(largo).length();
//                    espacio = espacio.substring(largo);
//                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Importet
                    str0 = tablaFactura.getValueAt(r, 4).toString();
                    str0 = str0.replace(",", ".");
                    Double calculo = Double.valueOf(str0);
                    str0 = tablaFactura.getValueAt(r, 6).toString();
                    str0 = str0.replace(",", ".");
                    calculo += Double.valueOf(str0);
                    str0 = String.valueOf(calculo);
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Impuesto
                    str0 = tablaFactura.getValueAt(r, 5).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "       ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
//  Total linea
                    str0 = tablaFactura.getValueAt(r, 8).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";
// Sugerido
                    str0 = tablaFactura.getValueAt(r, 9).toString();
                    str0 = str0.replace(",", ".");
                    doble = Double.valueOf(str0);
                    largo = doble.intValue();
                    espacio = "      ";
                    largo = String.valueOf(largo).length();
                    espacio = espacio.substring(largo);
                    renglones[r] = renglones[r] + espacio + df.format(doble) + " ";

                }
            } else {
                // agregar renglon en blanco
                renglones[r] = " ";
            }
        }
// Saldo Cliente
        String str0 = String.valueOf(saldoCliente - totalFactura);
        str0 = str0.replace(",", ".");
        Double doble = Double.valueOf(str0);
        int largo = doble.intValue();
        String espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        totalDeudaFacturaPapel = espacio + df.format(doble);
// Total Factura
        str0 = String.valueOf(totalFactura);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "        ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        importeTotalFacturaPapel = espacio + df.format(doble);
// Linea Totales
        if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
//            str0 = String.valueOf(totalImpuesto);
//            str0 = str0.replace(",", ".");
//            doble = Double.valueOf(str0);
//            largo = doble.intValue();
//            espacio = "                                                                            ";
//            largo = String.valueOf(largo).length();
//            espacio = espacio.substring(largo);
//            lineaTotalesFacturaPapel = espacio + df.format(doble);

            //} else {
            str0 = String.valueOf(totalGravado);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel = espacio + df.format(doble);
            str0 = String.valueOf(totalImpuesto);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "           ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel += espacio + df.format(doble);
            str0 = String.valueOf(totalIva);
            str0 = str0.replace(",", ".");
            doble = Double.valueOf(str0);
            largo = doble.intValue();
            espacio = "                                 ";
            largo = String.valueOf(largo).length();
            espacio = espacio.substring(largo);
            lineaTotalesFacturaPapel += espacio + df.format(doble);
        } else {
            lineaTotalesFacturaPapel = espacio;
        }
// Total a Pagar
        Double totalPagar = saldoCliente;
        str0 = String.valueOf(totalPagar);
        str0 = str0.replace(",", ".");
        doble = Double.valueOf(str0);
        largo = doble.intValue();
        espacio = "          ";
        largo = String.valueOf(largo).length();
        espacio = espacio.substring(largo);
        totalPagarFacturaPapel = espacio + df.format(doble);
// Cantidades atados
        cantidadesFacturaPapel = "                   CANT ATADOS NOBLEZA: " + String.valueOf(cantidadAtadosNobleza);
        cantidadesFacturaPapel += "              CANT.ATADOS TAB.SARANDI: " + String.valueOf(cantidadAtadosMassalin);
        //                                                                '
        texto1FacturaPapel = texto1PieFacturaTxt.getText();
        texto2FacturaPapel = texto2PieFacturaTxt.getText();
        texto3FacturaPapel = "-";
        texto1Cae = String.valueOf(numCae);

        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();
        double margin = 8;
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        if (pdfChk.isSelected()) {
            List<RenglonFactura> rf = null;
            List<RenglonNotaCredito> rnc = null;
            if (totalFactura < 0) {
                try {
                    rnc = new RenglonFacturaService().getAllRenglonNotaCreditoFromIvaVentas(i);
                } catch (Exception ex) {
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                pdf2(i, rnc);
            } else {
                try {
                    rf = new RenglonFacturaService().getAllRenglonFacturaFromIvaVentas(i);
                } catch (Exception ex) {
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                pdf(i, rf);
            }
        }
        if (imprimeChk.isSelected()) {
            pf.setPaper(paper);
            pj.setPrintable(new MyPrintable(), pf);
//        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (PrinterException e) {
                System.out.println(e);
            }
        }
//        }
    }

    private void pdf(IvaVentas iv, List<RenglonFactura> rf) {
        fecha_qr = sdf_qr.format(iv.getFecha());
        String cui = iv.getCliente().getCuit();
        String pri = "";
        String med = "";
        String fin = "";
        int lgo = cui.length();
        if (lgo != 13) {
            cui = "0000000000000" + cui;
            int lgo1 = cui.length();
            fin = cui.substring(lgo1 - 11, lgo1);
        }
        if (lgo > 11) {
            pri = cui.substring(0, 2);
            med = cui.substring(3, 11);
            fin = cui.substring(12, 13);
        }
        numeroDoc_qr = pri + med + fin;
        puntoVenta_qr = iv.getNumeroSucursal().toString();
        String letra = iv.getLetra();
        if (letra.equals("A")) {
            tipoComprobante_qr = "1";
        } else {
            tipoComprobante_qr = "6";
        }
        numeroComprobante_qr = iv.getNumeroFactura().toString();
        String nc = df_matriz.format(iv.getNumeroFactura());
        importe_qr = df.format(iv.getTotal());
        tipoDoc_qr = iv.getCliente().getTipo();
        nroCae_qr = iv.getCae().toString();
        String data = "{\"ver\":" + ver_qr
                + ",\"fecha\":\"" + fecha_qr + "\""
                + ",\"cuit\":" + cuit_qr
                + ",\"ptoVta\":" + puntoVenta_qr
                + ",\"tipoCmp\":" + tipoComprobante_qr
                + ",\"nroCmp\":" + numeroComprobante_qr
                + ",\"importe\":" + importe_qr
                + ",\"moneda\":\"" + moneda_qr + "\""
                + ",\"ctz\":" + cotiz_qr
                + ",\"tipoDocRec\":" + tipoDoc_qr
                + ",\"nroDocRec\":" + numeroDoc_qr
                + ",\"tipoCodAut\":\"" + tipoCodigoAutoriz_qr + "\""
                + ",\"codAut\":" + nroCae_qr + "}";
        try {
            generarQR(data, nc);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String code = iv.getCliente().getCodigo();
        //String numeroFactura = iv.getNumeroFactura().toString();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder().armarFcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder().armarFcB(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(this, "ERROR FILE 2274");
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 2278");
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 2282");
        }

    }

    private void generarQR(String data, String numeroFactura) throws Exception {
        String cadenaCodificada = Base64.getEncoder().encodeToString(data.getBytes());
        BitMatrix matriz;
        Writer writer = new QRCodeWriter();
        try {
            matriz = writer.encode(url_qr + cadenaCodificada, BarcodeFormat.QR_CODE, qrTamAncho, qrTamAlto);
        } catch (WriterException e) {
            e.printStackTrace(System.err);
            return;
        }
        BufferedImage imagen = new BufferedImage(qrTamAncho,
                qrTamAlto, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < qrTamAlto; y++) {
            for (int x = 0; x < qrTamAncho; x++) {
                int valor = (matriz.get(x, y) ? 0 : 1) & 0xff;
                imagen.setRGB(x, y, (valor == 0 ? 0 : 0xFFFFFF));
            }
        }
        //99
        FileOutputStream qrCode;
        String nf_qr = numeroFactura;
        qrCode = new FileOutputStream(ruta + nf_qr + extension);
        ImageIO.write(imagen, formato, qrCode);
        qrCode.close();
    }

    private void pdf2(IvaVentas iv, List<RenglonNotaCredito> rf) {
        fecha_qr = sdf_qr.format(iv.getFecha());
        String cui = iv.getCliente().getCuit();
        String pri = "";
        String med = "";
        String fin = "";
        int lgo = cui.length();
        if (lgo != 13) {
            cui = "0000000000000" + cui;
            int lgo1 = cui.length();
            fin = cui.substring(lgo1 - 11, lgo1);
        }
        if (lgo > 11) {
            pri = cui.substring(0, 2);
            med = cui.substring(3, 11);
            fin = cui.substring(12, 13);
        }
        numeroDoc_qr = pri + med + fin;
        puntoVenta_qr = iv.getNumeroSucursal().toString();
        String letra = iv.getLetra();
        if (letra.equals("A")) {
            tipoComprobante_qr = "3";
        } else {
            tipoComprobante_qr = "8";
        }
        numeroComprobante_qr = iv.getNumeroFactura().toString();
        String nc = df_matriz.format(iv.getNumeroFactura());
        importe_qr = df.format(iv.getTotal());
        tipoDoc_qr = iv.getCliente().getTipo();
        nroCae_qr = iv.getCae().toString();
        String data = "{\"ver\":" + ver_qr
                + ",\"fecha\":\"" + fecha_qr + "\""
                + ",\"cuit\":" + cuit_qr
                + ",\"ptoVta\":" + puntoVenta_qr
                + ",\"tipoCmp\":" + tipoComprobante_qr
                + ",\"nroCmp\":" + numeroComprobante_qr
                + ",\"importe\":" + importe_qr
                + ",\"moneda\":\"" + moneda_qr + "\""
                + ",\"ctz\":" + cotiz_qr
                + ",\"tipoDocRec\":" + tipoDoc_qr
                + ",\"nroDocRec\":" + numeroDoc_qr
                + ",\"tipoCodAut\":\"" + tipoCodigoAutoriz_qr + "\""
                + ",\"codAut\":" + nroCae_qr + "}";
        try {
            generarQR(data, nc);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        String code = iv.getCliente().getCodigo();
        //String numeroFactura = iv.getNumeroFactura().toString();
        Cliente cli = null;
        try {
            cli = new ClienteService().getClienteByCodigo(code);
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (categoriaIva.equals(1) || categoriaIva.equals(2)) {
                File pdf = new PDFBuilder().armarNcA(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            } else {
                File pdf = new PDFBuilder().armarNcB(cli, iv, rf);  //.armarF(cli, iv, rf);
                DesktopApi.open(pdf);
            }
            JOptionPane.showMessageDialog(this, "PDF GENERADO CORRECTAMENTE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err1");
            JOptionPane.showMessageDialog(this, "ERROR FILE 2382");
        } catch (DocumentException ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err2");
            JOptionPane.showMessageDialog(this, "ERROR DOCUMENT 2386");
        } catch (Exception ex) {
            Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err3");
            JOptionPane.showMessageDialog(this, "ERROR EXCEPTION 2390");
        }

    }

    private void agregarProducto() {
        agregarBtn.setEnabled(false);
        if (nro > 0) {
            terminarBtn.setEnabled(true);
        } else {
            terminarBtn.setEnabled(false);
        }
        incorporarAFacturaBtn.setEnabled(true);
        if (nro > 0) {
            cancelarBtn.setEnabled(true);
        }
        buscarProductoXNombreBtn.setEnabled(true);
        codigoProductoTxt.setEnabled(true);
        codigoBarrasTxt.setEnabled(true);
        cantidadTxt.setEnabled(true);
        nombreProductoABuscarTxt.setEnabled(true);
        nombreProductoConsultaTxt.setEnabled(true);
        comboProductos.setEnabled(true);
        codigoBarrasTxt.requestFocus();
    }

    private void buscarProducto() {
        filtro = "";
        if (nro < maxNro - 1) {
            agregarBtn.setEnabled(true);
        } else {
            agregarBtn.setEnabled(false);
        }
        terminarBtn.setEnabled(true);
        eliminarItemBtn.setEnabled(true);
        cancelarBtn.setEnabled(true);
        Producto pro = null;
        encontrado = false;
        if (!codigoBarrasTxt.getText().isEmpty()) {
            try {
                pro = new ProductoService().getProductoByCodigoBarras(Long.valueOf(codigoBarrasTxt.getText()));
                encontrado = true;
            } catch (Exception ex) {
                Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoBarrasTxt.requestFocus();
            }
        } else {
            if (!codigoProductoTxt.getText().isEmpty()) {
                try {
                    pro = new ProductoService().getProductoByCodigo(Integer.valueOf(codigoProductoTxt.getText()));
                    encontrado = true;
                } catch (Exception ex) {
                    Logger.getLogger(FacturaWebFrame.class.getName()).log(Level.SEVERE, null, ex);
                    codigoBarrasTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese Código de Producto o Código de Barras");
                codigoBarrasTxt.requestFocus();
            }
        }
        if (pro != null) {
            if (pro.getInactivo() != null) {
                if (!pro.getInactivo()) {
                    if (!(pro.getSubRubro().getCodigo().equals(5099))) {
                        cantidad = Float.valueOf(cantidadTxt.getText());
                        Double prec = pro.getPrecio();
                        Float impu = pro.getImpuesto();

                        calcularLinea(cantidad, prec, impu);

                        RenglonFactura rf = new RenglonFactura();
                        rf.setCantidad(cantidad);
                        rf.setDescripcion(pro.getDetalle());
                        rf.setDescuento(0.0);
                        rf.setExento(0.0);
                        rf.setGravado(gravado);
                        rf.setImpuesto(impuesto);
                        rf.setIva(iva);
                        rf.setNoGravado(noGravado);
                        rf.setProducto(pro);
                        rf.setSugerido(pro.getSugerido());
                        rf.setTotal(totalLinea);
                        if (pro.getCostoP() != null) {
                            rf.setCostoG(pro.getCostoP() * cantidad);
                        } else {
                            rf.setCostoG(0.00);
                        }
                        if (pro.getCostoI() != null) {
                            rf.setCostoI(pro.getCostoI() * cantidad);
                        } else {
                            rf.setCostoI(0.00);
                        }
                        nro += 1;
                        renglonFactura.add(rf);
                        calcularTotales();
                        Object[] fila = new Object[10];
                        fila[0] = pro.getCodigo();
                        fila[1] = df1.format(cantidad);
                        fila[2] = pro.getDetalle();
                        fila[3] = df.format(precioFinal);
                        fila[4] = df.format(gravado);
                        fila[5] = df.format(impuesto);
                        fila[6] = df.format(iva);
                        fila[7] = df.format(0.0);
                        fila[8] = df.format(totalLinea);
                        fila[9] = df.format(pro.getSugerido());
                        tabla.addRow(fila); // Agrego la fila a la tabla
                        Rectangle rect = tablaFactura.getCellRect(nro - 1, 0, true);
                        tablaFactura.scrollRectToVisible(rect);
                        tablaFactura.clearSelection();
                        tablaFactura.setRowSelectionInterval(nro - 1, nro - 1);
                        tablaFactura.setModel(tabla); // poner visible la tabla
                        codigoProductoTxt.setEnabled(false);
                        codigoBarrasTxt.setEnabled(false);
                        cantidadTxt.setEnabled(false);
                        incorporarAFacturaBtn.setEnabled(false);
                        cantidadTxt.setText("");
                        codigoProductoTxt.setText("");
                        buscarProductoXNombreBtn.setEnabled(false);
                        nombreProductoABuscarTxt.setEnabled(false);
                        comboProductos.setEnabled(false);
                        texto1PieFacturaTxt.setEnabled(true);
                        texto2PieFacturaTxt.setEnabled(true);
                        leerPrecioBtn.setEnabled(true);
                        leerCantidadBtn.setEnabled(true);
                        nuevoPrecioTxt.setEnabled(true);
                        nuevaCantidadTxt.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error - producto no disponible");
                        codigoProductoTxt.setText("");
                        cantidadTxt.setText("");
                        nombreProductoABuscarTxt.setText("");
                        agregarBtn.setEnabled(false);
                        codigoBarrasTxt.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                    codigoProductoTxt.setText("");
                    cantidadTxt.setText("");
                    buscarClienteBtn.setEnabled(false);
                    codigoBarrasTxt.requestFocus();
                    //codigoProductoTxt.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error - producto inactivo");
                codigoProductoTxt.setText("");
                cantidadTxt.setText("");
                buscarClienteBtn.setEnabled(false);
                codigoBarrasTxt.requestFocus();
                //codigoProductoTxt.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error - producto no existe");
            codigoProductoTxt.setText("");
            codigoBarrasTxt.requestFocus();
            //codigoProductoTxt.requestFocus();
        }
        comboProductos.removeAllItems();
        comboProductos.addItem("");
        agregarBtn.requestFocus();
    }

    private void borrarTablaProductos() {
        int cantidadRow = tablaFactura.getRowCount();
        DefaultTableModel model1 = (DefaultTableModel) tablaFactura.getModel();
        if (cantidadRow > 0) {
            for (int i = 0; i < cantidadRow; i++) {
                model1.removeRow(0);
            }
            tablaFactura.setModel(model1);
            nro = 0;
        }
        renglonFactura = new ArrayList<RenglonFactura>();
    }

    private void calcularLinea(Float cantid, Double precio, Float impuest) {
        precioFinal = rint(((precio
                * (1 + (porcentualIva / 100)))
                + impuest) * 100) / 100;

        // por cantidad
        totalLinea = rint((precioFinal * cantid) * 100) / 100;
        impuesto = rint(impuest * cantid * 100) / 100;
        Double calculo = (totalLinea - impuesto)
                / (1 + (porcentualIva / 100));
        gravado = rint((calculo) * 100) / 100;
        iva = rint((gravado * porcentualIva
                / 100) * 100) / 100;
    }

    private void consultarProducto() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoProductoTxt.getText().isEmpty()) {
            Integer codi = Integer.valueOf(codigoProductoTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigo(codi);
                if (!(prod.getSubRubro().getCodigo().equals(5099))) {
                    nombreProductoConsultaTxt.setText(prod.getDetalle());
                    Double precioProductoConsulta = prod.getPrecio();
                    precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                    if (prod.getImpuesto() != null) {
                        precioProductoConsulta += prod.getImpuesto();
                    }
                    precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                    cantidadTxt.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "No existe Producto");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private void calcularTotales() {
        totalGravado = 0.0;
        totalIva = 0.0;
        totalImpuesto = 0.0;
        totalFactura = 0.0;
        nro = 0;
        cantidadAtadosMassalin = 0;
        cantidadAtadosNobleza = 0;
        Double importeTotalMassalin = 0.0;
        Double importeTotalNobleza = 0.0;
        for (RenglonFactura renFa : renglonFactura) {
            totalFactura += renFa.getTotal();
            if (renFa.getProducto().getRubro().getCodigo().equals(1)) {
                cantidadAtadosMassalin += renFa.getCantidad().intValue();
                importeTotalMassalin += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
            }
            if (renFa.getProducto().getRubro().getCodigo().equals(2)) {
                cantidadAtadosNobleza += renFa.getCantidad().intValue();
                importeTotalNobleza += renFa.getTotal();
                totalImpuesto += renFa.getImpuesto();
            }
            nro += 1;
            renFa.setItemNro(nro);
        }
        totalGravado = rint(((totalFactura
                - totalImpuesto) / (1 + (porcentualIva / 100)))
                * 100) / 100;
        totalIva = rint(totalGravado
                * (porcentualIva / 100) * 100) / 100;
        cantidadAtadosNoblezaTxt.setText(String.valueOf(cantidadAtadosNobleza));
        importeNoblezaTxt.setText(String.valueOf(df.format(importeTotalNobleza)));
        cantidadAtadosMassalinTxt.setText(String.valueOf(cantidadAtadosMassalin));
        importeMassalinTxt.setText(String.valueOf(df.format(importeTotalMassalin)));
        cantidadItemsTxt.setText(String.valueOf(nro));
        totalTxt.setText(String.valueOf(df.format(totalFactura)));
    }

    private void consultarProductoBarras() {
        nombreProductoConsultaTxt.setEnabled(true);
        precioProductoConsultaTxt.setEnabled(true);
        if (!codigoBarrasTxt.getText().isEmpty()) {
            Long codigoBarras = Long.valueOf(codigoBarrasTxt.getText());
            try {
                Producto prod = new ProductoService().getProductoByCodigoBarras(codigoBarras);
                nombreProductoConsultaTxt.setText(prod.getDetalle());
                Double precioProductoConsulta = prod.getPrecio();
                precioProductoConsulta += precioProductoConsulta * porcentualIva / 100;
                if (prod.getImpuesto() != null) {
                    precioProductoConsulta += prod.getImpuesto();
                }
                precioProductoConsultaTxt.setText(String.valueOf(df.format(precioProductoConsulta)));
                incorporarAFacturaBtn.setEnabled(true);
                cantidadTxt.requestFocus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No existe Producto");
                //Logger.getLogger(FacturaFrame.class.getName()).log(Level.SEVERE, null, ex);
                codigoProductoTxt.setText("");
                codigoProductoTxt.requestFocus();
            }
        }
    }

    private boolean isNumeric(KeyEvent evt) {
        String cod = String.valueOf(evt.getKeyChar());
        try {
            Integer.parseInt(cod);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    class MyPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            g2.setPaint(Color.black);
            int row = 45;
            //                1234567890123456789012345678901234567890123456789012345678901234567890
            String espacio = "                                                         ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + textoFacturaPapel, 30, row);
            g2.drawString("Golosol S. A.", 50, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 8));
            row += 12;
            g2.drawString("Ruta 8 (101 Balbin) Nro.4211", 50, row);
            row += 12;
            g2.drawString("1653 - Villa Ballester - Prov. Buenos Aires", 50, row);
            row += 12;
            g2.drawString("Tel: 4849-1444 - e-mail: golosol10@yahoo.com.ar", 50, row);
            row += 12;
            g2.drawString("C.U.I.T.: 30-70952074-8", 50, row);
            row = 70;
            //         123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
            espacio = "                                                                           ";
            g2.drawString(espacio + letraFacturaPapel + " "
                    + sucursalFacturaPapel + "-"
                    + numeroFacturaPapel, 30, row);
            row += 15;
            g2.drawString(espacio + fechaFacturaPapel, 30, row);
            espacio = "            ";
            row += 50;
            g2.drawString("Razón Social: " + clienteFacturaPapel, 30, row);
            g2.drawString(codigoClienteFacturaPapel, 480, row);
            row += 15;
            espacio = "            ";
            g2.drawString("Dirección:    " + direccionFacturaPapel, 30, row);
            row += 15;
            if (tpd.equals("96")) {
                g2.drawString("DNI:          " + cuitFacturaPapel, 30, row);
            } else {
                g2.drawString("C.U.I.T.:     " + cuitFacturaPapel, 30, row);
            }
            g2.drawString(inscripcionClienteFacturaPapel, 360, row);
            row += 25;
            g2.drawString("Cond.Venta: " + condicionVentaFacturaPapel, 120, row);
            //g2.drawString("Ref.Interna:" + letraFactura + " " + sucursalFactura + "-" + numeroFactura, 350, row);
            //g2.drawString(vencimientoFacturaPapel, 400, row);
            row += 25;
            g2.drawString(nombresColumnaFacturaPapel, 30, row);
            row += 15;
            for (int x = 0; x < maxNro; x++) {
                if (renglones[x] != null) {
                    g2.drawString(renglones[x], 40, row);
                }
                row += 10;
            }
            row += 40;
            g2.drawString(lineaTotalesFacturaPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.BOLD, 11));
            g2.drawString(importeTotalFacturaPapel, 490, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            //row += 21;
            //g2.drawString("SALDO ANTERIOR: " + totalDeudaFacturaPapel, 403, row);
            //row += 10;
            //g2.drawString("SALDO TOTAL:    " + totalPagarFacturaPapel, 403, row);
            row += 15;
            espacio = "     ";
            g2.setFont(new Font("Monospaced", Font.BOLD, 9));
            g2.drawString(espacio + texto1FacturaPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto2FacturaPapel, 30, row);
            row += 10;
            g2.drawString(espacio + texto3FacturaPapel, 30, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            row += 15;
            g2.drawString(cantidadesFacturaPapel, 30, row);
            row += 15;
            g2.drawString(" CAE " + texto1Cae + "  Venc. CAE " + vencCae, 30, row);
            g2.setFont(new Font("PF Interleavev 2 of 5 Text", Font.PLAIN, 18));
            g2.drawString("           " + texto2Cae, 160, row);
            g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
            return PAGE_EXISTS;
        }
    }
}
