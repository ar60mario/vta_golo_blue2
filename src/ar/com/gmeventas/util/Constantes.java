/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.util;

/**
 *
 * @author Marcela
 */
public class Constantes {

    public static final int MAX_RESULTS = 20;

    // Constantes de archivos
    public static final String EXTENSION_EXCEL_1 = "xls";
//    public static final String EXTENSION_EXCEL_2 = "xlsx";
//    public static final String EXTENSION_EXCEL_3 = "xlsm";
//    public static final String EXTENSION_EXCEL_4 = "xlsb";
//    public static final String EXTENSION_EXCEL_5 = "xlm";
//    public static final String EXTENSION_EXCEL_6 = "xlsm";

    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private static int r = 90;
    private static final int g = 180;
    private static final int b = 180;
    
    public static int getR(){
        return r;
    }
    
    public static int getG(){
        return g;
    }
    
    public static int getB(){
        return b;
    }
}
