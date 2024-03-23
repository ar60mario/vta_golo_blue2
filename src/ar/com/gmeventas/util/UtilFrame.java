/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author argia
 */
public class UtilFrame {
    public static JTable limpiarTabla(JTable tabla) {
        int rows = tabla.getRowCount();
        if (rows > 0) {
            DefaultTableModel tbl = (DefaultTableModel) tabla.getModel();
            for (int i = 0; i < rows; i++) {
                tbl.removeRow(0);
            }
            tabla.setModel(tbl);
        }
        return tabla;
    }
    
    public static String fecha(String fe) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int largo = fe.length();
        String fe1 = fe;
        String str = sdf.format(new Date());
        if (fe.equals("")) {
            fe = sdf.format(new Date());
        } else {
            if (largo == 2) {
                fe = fe1 + str.substring(2, 10);
            } else {
                if (largo == 5) {
                    fe = fe1 + str.substring(5, 10);
                }
            }
        }
        return fe;
    }
}
