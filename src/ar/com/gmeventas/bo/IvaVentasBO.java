/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.IvaVentasDAO;
import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.IvaVentas;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class IvaVentasBO {

    private final IvaVentasDAO dao = new IvaVentasDAO();

    private static final Logger logger = Logger.getLogger("IvaVentasBO");

    public IvaVentas saveIvaVentas(IvaVentas ivaVentas) throws Exception {
        try {
            dao.save(ivaVentas);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return ivaVentas;
    }

    public List<IvaVentas> getAllIvaVentas() throws Exception {

        List<IvaVentas> listIvaVentas = null;

        try {
            listIvaVentas = dao.getAll(IvaVentas.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listIvaVentas;

    }

    public List<IvaVentas> getAllIvaVentasByCodigoYFecha() throws Exception {
        List<IvaVentas> listIvaVentas = null;
        try {
            listIvaVentas = dao.getAll(IvaVentas.class);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listIvaVentas;
    }

    public List<IvaVentas> getFacturasEntreFechas(Date fd, Date fa) throws Exception {
        List<IvaVentas> fact = null;
        try {
            fact = (List<IvaVentas>) dao.getFacturasEntreFechas(fd, fa);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fact;
    }
    //
    public List<IvaVentas> getFacturasByFecha(Date fd) throws Exception {
        List<IvaVentas> fact = null;
        try {
            fact = (List<IvaVentas>) dao.getFacturasByFecha(fd);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fact;
    }
    
    public List<IvaVentas> getNcEntreFechas(Date fd, Date fa) throws Exception {
        List<IvaVentas> fact = null;
        try {
            fact = (List<IvaVentas>) dao.getNcEntreFechas(fd, fa);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fact;
    }
    
    public void saveListaFacturas(List<IvaVentas> facturas) throws Exception {
        if(facturas != null && !facturas.isEmpty()){
            for(IvaVentas iv: facturas){
                try{
                    dao.save(iv);
                }catch(HibernateException ex){
                    throw new Exception("Ha ocurrido un problema intentando guardar Factura.\nPor favor intente nuevamente mas tarde.");
                }
            }
        }
    }
    
    public IvaVentas getFacturaByLetraNumero(String letra, Integer numero) throws Exception {
        IvaVentas factura = null;
        try {
            factura = dao.getFacturaByLetraNumero(letra, numero);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return factura;
    }
    //
    public List<IvaVentas> getFacturasByCliEntreFechas(Cliente c,Date fd, Date fa) throws Exception {
        List<IvaVentas> fact = null;
        try {
            fact = (List<IvaVentas>) dao.getFacturasByCliEntreFechas(c, fd, fa);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return fact;
    }
}
