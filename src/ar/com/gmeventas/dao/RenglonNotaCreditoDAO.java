/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.dao;

import ar.com.gmeventas.entities.IvaCompras;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.entities.RenglonNotaCredito;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Mario
 */
public class RenglonNotaCreditoDAO extends GenericDAO{
    
    
    public List<RenglonNotaCredito> getRenglonNotaCreditoFromIvaVentas(IvaVentas idIva) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        List<RenglonNotaCredito> renglones = null;
        StringBuffer sb = new StringBuffer();
        sb.append("from RenglonNotaCredito rf ");
        sb.append("where rf.ivaVentas = :idIva ");
//        sb.append("and rf.fechaPeriodo = :anio ");
//        sb.append("order by ic.fechaPeriodo asc");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("idIva",  idIva );
//        query.setParameter("anio", anio);
        
        renglones = (List<RenglonNotaCredito>) query.list();
                
        return renglones;
    }
    
}
