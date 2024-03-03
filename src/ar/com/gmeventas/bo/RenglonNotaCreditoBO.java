/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.RenglonFacturaDAO;
import ar.com.gmeventas.dao.RenglonNotaCreditoDAO;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.entities.RenglonNotaCredito;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonNotaCreditoBO {

    private final RenglonNotaCreditoDAO dao = new RenglonNotaCreditoDAO();

    private static final Logger logger = Logger.getLogger("RenglonFacturaBO");

    public RenglonNotaCredito saveRenglon(RenglonNotaCredito renglonFactura) throws Exception {
        try {
            dao.save(renglonFactura);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglonFactura;
    }

    public List<RenglonNotaCredito> getAllRenglonNotaCreditoFromIvaVentas(IvaVentas idIvaVentas) throws Exception {
        List<RenglonNotaCredito> listRenglonFactura = null;
        try {
            listRenglonFactura = dao.getRenglonNotaCreditoFromIvaVentas(idIvaVentas);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonFactura;
    }

}
