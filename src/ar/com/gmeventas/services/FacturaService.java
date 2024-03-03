/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.services;

import ar.com.gmeventas.bo.IvaVentasBO;
import ar.com.gmeventas.bo.RenglonFacturaBO;
import ar.com.gmeventas.bo.RenglonNotaCreditoBO;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.entities.RenglonNotaCredito;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Mario
 */
public class FacturaService {

    public void saveFactura(IvaVentas iv, List<RenglonFactura> rf) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        IvaVentasBO ivaBO = new IvaVentasBO();
        IvaVentas ivaVentas = ivaBO.saveIvaVentas(iv);
        Boolean bolean = true;
        for (RenglonFactura renglon : rf) {
            renglon.setIvaVentas(ivaVentas);
            try {
                RenglonFacturaBO bo = new RenglonFacturaBO();
                bo.saveRenglon(renglon);
            } catch (Exception ex) {
                bolean = false;
                tx.rollback();
                throw new Exception(ex);
            }
        }
        if (bolean) {
            tx.commit();
        }
    }
    
    public void saveNotaCredito(IvaVentas iv, List<RenglonNotaCredito> rf) throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        IvaVentasBO ivaBO = new IvaVentasBO();
        IvaVentas ivaVentas = ivaBO.saveIvaVentas(iv);
        Boolean bolean = true;
        for (RenglonNotaCredito renglon : rf) {
            renglon.setIvaVentas(ivaVentas);
            try {
                RenglonNotaCreditoBO bo = new RenglonNotaCreditoBO();
                bo.saveRenglon(renglon);
            } catch (Exception ex) {
                bolean = false;
                tx.rollback();
                throw new Exception(ex);
            }
        }
        if (bolean) {
            tx.commit();
        }
    }
    
    
}
