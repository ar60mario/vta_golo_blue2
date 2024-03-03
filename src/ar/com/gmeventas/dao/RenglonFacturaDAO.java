/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.dao;

import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class RenglonFacturaDAO extends GenericDAO {

    public List<RenglonFactura> getRenglonFacturaFromIvaVentas(IvaVentas idIva) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        List<RenglonFactura> renglones = null;
        StringBuffer sb = new StringBuffer();
        sb.append("from RenglonFactura rf ");
        sb.append("where rf.ivaVentas = :idIva ");
        Query query = session.createQuery(sb.toString());
        query.setParameter("idIva", idIva);
        renglones = (List<RenglonFactura>) query.list();
        return renglones;
    }

    public List<RenglonFactura> getRenglonesEntreFechas(Date d1, Date d2) {
        List<RenglonFactura> renglones = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(RenglonFactura.class);
        Criteria criteria2 = criteria.createCriteria("ivaVentas").add(Restrictions.between("fecha", d1, d2));
        renglones = criteria.list();
        return renglones;
    }
}
