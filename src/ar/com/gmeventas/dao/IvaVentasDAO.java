/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.dao;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcela
 */
public class IvaVentasDAO extends GenericDAO{
    public List<IvaVentas> getFacturasEntreFechas(Date fd, Date fa) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        fact = (List<IvaVentas>) 
                session.createCriteria(IvaVentas.class)
                        .add(Restrictions.between("fecha", fd, fa))
                        //.add(Restrictions.gt("total", 0.00))
                        .addOrder(Order.asc("fecha"))
                        .addOrder(Order.asc("letra"))
                        .addOrder(Order.asc("numeroFactura"))
                        .list();
        return fact;
    }
    
    public List<IvaVentas> getFacturasByFecha(Date fd) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        fact = (List<IvaVentas>) 
                session.createCriteria(IvaVentas.class)
                        .add(Restrictions.between("fecha", fd, fd))
                        //.add(Restrictions.gt("total", 0.00))
                        //.addOrder(Order.asc("fecha"))
                        .addOrder(Order.asc("letra"))
                        .addOrder(Order.asc("numeroFactura"))
                        .list();
        return fact;
    }
    
    public List<IvaVentas> getNcEntreFechas(Date fd, Date fa) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        fact = (List<IvaVentas>) 
                session.createCriteria(IvaVentas.class)
                        .add(Restrictions.between("fecha", fd, fa))
                        .add(Restrictions.lt("total", 0.00))
                        .addOrder(Order.asc("fecha"))
                        .addOrder(Order.asc("letra"))
                        .addOrder(Order.asc("numeroFactura"))
                        .list();
        return fact;
    }
    
    public IvaVentas getFacturaByLetraNumero(String letra, Integer numero){
        IvaVentas factura = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        factura = (IvaVentas)
                session.createCriteria(IvaVentas.class)
                .add(Restrictions.and(Restrictions.eq("letra", letra)
                        ,Restrictions.eq("numeroFactura", numero)))
                .uniqueResult();
        return factura;
    }
    
    public List<IvaVentas> getFacturasByCliEntreFechas(Cliente c,Date fd, Date fa) {
        List<IvaVentas> fact = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        fact = (List<IvaVentas>) 
                session.createCriteria(IvaVentas.class)
                        .add(Restrictions.between("fecha", fd, fa))
                        .add(Restrictions.eq("cliente", c))
                        .addOrder(Order.asc("fecha"))
                        .addOrder(Order.asc("letra"))
                        .addOrder(Order.asc("numeroFactura"))
                        .list();
        return fact;
    }
}
