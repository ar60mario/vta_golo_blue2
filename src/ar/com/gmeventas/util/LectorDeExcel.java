/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.util;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.Domicilio;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.Producto;
import ar.com.gmeventas.entities.Rubro;
import ar.com.gmeventas.entities.SubRubro;
import ar.com.gmeventas.services.ClienteService;
import ar.com.gmeventas.services.RubroService;
import ar.com.gmeventas.services.SubRubroService;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Marcela
 */
public class LectorDeExcel {

    public static List<IvaVentas> importarIvaVentas(File file) throws IOException, BiffException, Exception {
        Workbook archivoExcel = Workbook.getWorkbook(file);
        int cantidadFilas = archivoExcel.getSheet(0).getRows();
        Sheet hoja = archivoExcel.getSheet(0);
        List<IvaVentas> libroIva = new ArrayList<IvaVentas>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 1; i < cantidadFilas; i++) {
            try {
                IvaVentas iv = new IvaVentas();
                iv.setLetra(hoja.getCell(0, i).getContents());
                iv.setNumeroSucursal(Integer.valueOf(hoja.getCell(1, i).getContents()));
                iv.setNumeroFactura(Integer.valueOf(hoja.getCell(2, i).getContents()));
                iv.setFecha(sdf.parse(hoja.getCell(3, i).getContents()));
                String cui = hoja.getCell(4, i).getContents();
                Cliente cli = null;
                if (cui != "00-00000000-0") {
                    cli = new ClienteService().getClienteByCuit(cui);
                } else {
                    String cod = "1";
                    cli = new ClienteService().getClienteByCodigo(cod);
                }
                System.out.println(cli);
                if (cli != null) {
                    iv.setCliente(cli);
                } else {
                    iv.setCliente(null);
                }
                iv.setCae(Long.valueOf(hoja.getCell(5,i).getContents()));
                iv.setFechaCae(sdf.parse(hoja.getCell(6,i).getContents()));
                iv.setTotal(Double.valueOf(hoja.getCell(7, i).getContents()));
                iv.setImpuesto(Double.valueOf(hoja.getCell(8, i).getContents()));
                iv.setGravado(Double.valueOf(hoja.getCell(9, i).getContents()));
                iv.setIva(Double.valueOf(hoja.getCell(10, i).getContents()));
                libroIva.add(iv);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en linea: " + String.valueOf(i + 1));
                throw new Exception(ex);
            }
        }
        return libroIva;
    }

    public static List<Producto> leerExcelProducto(File file) throws IOException, BiffException, Exception {
        Workbook archivoExcel = Workbook.getWorkbook(file);
        int cantidadFilas = archivoExcel.getSheet(0).getRows();
        Sheet hoja = archivoExcel.getSheet(0);
        List<Producto> listaProductos = new ArrayList<Producto>();
        Boolean salir = false;
        for (int i = 1; i < cantidadFilas; i++) {
            try {
                Producto prod = new Producto();
                prod.setRubro(new Rubro());
                prod.setSubRubro(new SubRubro());
                prod.setInactivo(false);
                /*
                 0- Codigo
                 1- Es Unidad
                 2- Codigo Relacionado
                 3- Codigo Barras
                 4- Detalle
                 5- Precio
                 6- Impuesto
                 7- Sugerido
                 8- Rubro
                 9- Sub Rubro
                 */
                prod.setCodigo(Integer.valueOf(hoja.getCell(0, i).getContents()));
                if (!hoja.getCell(1, i).getContents().isEmpty()) {
                    prod.setCodigoBarras(Long.valueOf(hoja.getCell(1, i).getContents()));
                } else {
                    prod.setCodigoBarras(0L);
                }
                prod.setDetalle(hoja.getCell(2, i).getContents());
                prod.setPrecio(Double.valueOf(hoja.getCell(3, i).getContents().replaceAll("\\,", "\\.")));
                prod.setImpuesto(Float.valueOf(hoja.getCell(4, i).getContents().replaceAll("\\,", "\\.")));
                prod.setSugerido(Double.valueOf(hoja.getCell(5, i).getContents().replaceAll("\\,", "\\.")));
                Rubro rubro = prod.getRubro();
                RubroService rubroService = new RubroService();
                Integer rub;
                if (hoja.getCell(6, i).getContents() != null) {
                    rub = Integer.valueOf(hoja.getCell(6, i).getContents());
                    rubro = rubroService.getRubroByCodigo(rub);
                    if (rubro == null) {
                        throw new Exception("Producto en fila " + i + " no existe, Rubro.");
                    }
                } else {
                    throw new Exception("Producto en fila " + i + " no existe, Rubro.");
                }
                SubRubro subRubro = prod.getSubRubro();
                SubRubroService subRubroService = new SubRubroService();
                Integer subRub;
                if (hoja.getCell(7, i).getContents() != null) {
                    subRub = Integer.valueOf(hoja.getCell(7, i).getContents());
                    subRubro = subRubroService.getSubRubroByCodigo(subRub);
                    if (subRubro == null) {
                        int x = i + 1;
                        JOptionPane.showMessageDialog(null, "Producto en fila " + x + " no existe Sub-Rubro.");
                        break;
                    }
                } else {
                    throw new Exception("Producto en fila " + i + " no existe, Sub-Rubro.");
                }

                prod.setRubro(rubro);
                prod.setSubRubro(subRubro);
                prod.setInactivo(false);
                prod.setStock((Float.valueOf("0.0")));
                prod.setStockMinimo(Float.valueOf("0.0"));
                listaProductos.add(prod);
                salir = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en linea: " + String.valueOf(i + 1));
                throw new Exception(ex);
            }
            if (salir) {
                break;
            }
        }
        if (salir) {
            listaProductos = null;
        }
        return listaProductos;
    }

    public static boolean validarExtension(File archivo) {
        String[] splitNombreArchivo = archivo.getName().split("\\.");
        String extension = splitNombreArchivo[splitNombreArchivo.length - 1];
        if (extension.equalsIgnoreCase(Constantes.EXTENSION_EXCEL_1)) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Cliente> leerExcelCliente(File file) throws IOException, BiffException, Exception {
        Workbook archivoExcel = Workbook.getWorkbook(file);
        int cantidadFilas = archivoExcel.getSheet(0).getRows();
        Sheet hoja = archivoExcel.getSheet(0);
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        for (int i = 1; i < cantidadFilas; i++) {
            try {
                Cliente cliente = new Cliente();
                cliente.setCodigo(hoja.getCell(0, i).getContents());
                cliente.setRazonSocial(hoja.getCell(1, i).getContents());
                cliente.setCuit(hoja.getCell(2, i).getContents());
                cliente.setDomicilio(new Domicilio());
                cliente.getDomicilio().setCalle(hoja.getCell(3, i).getContents());
                cliente.getDomicilio().setNumero(hoja.getCell(4, i).getContents());
                cliente.getDomicilio().setPiso(hoja.getCell(5, i).getContents());
                cliente.getDomicilio().setDepartamento(hoja.getCell(6, i).getContents());
                cliente.getDomicilio().setCodigoPostal(hoja.getCell(7, i).getContents());
                cliente.getDomicilio().setLocalidad(hoja.getCell(8, i).getContents());
                cliente.setTelefono(hoja.getCell(9, i).getContents());
                cliente.setMail(hoja.getCell(10, i).getContents());
                cliente.setFormaDePago(Integer.valueOf(hoja.getCell(11, i).getContents()));
                cliente.setCategoriaDeIva(Integer.valueOf(hoja.getCell(12, i).getContents()));
                cliente.setDescuento((float) 0);
                cliente.setTieneDescuento(false);
                cliente.setSaldo(0.0);
                cliente.setActivo(true);
                cliente.setTipo("80");
                listaClientes.add(cliente);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }
        return listaClientes;
    }

}
