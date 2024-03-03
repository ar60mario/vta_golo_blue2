/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.services;

import ar.com.gmeventas.bo.CodigosBarraBO;
import ar.com.gmeventas.entities.CodigoBarras;
import ar.com.gmeventas.entities.Producto;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Marcela
 */
public class CodigosBarraService {
    
    public List<CodigoBarras> getAllCodigos() throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<CodigoBarras> ctaCte = null;
        try{
            ctaCte = new CodigosBarraBO().getAllCodigos();
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return ctaCte;
    }
    
    public CodigoBarras save(CodigoBarras cb)  throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            cb = new CodigosBarraBO().saveCodigoBarras(cb);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return cb;
    }
    
    public void delete(CodigoBarras cb)  throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            new CodigosBarraBO().deleteCodigoBarras(cb);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return;
    }
    
    public Boolean getSinCodigo(Producto p)  throws Exception {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Boolean cb = false;
        try{
            cb = new CodigosBarraBO().getSinCodigo(p);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            //throw new Exception(ex);
        }
        return cb;
    }
    
    public CodigoBarras getCodigoBarrasByCB(Long c)  throws Exception {
        CodigoBarras cb = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            cb = new CodigosBarraBO().getCodigoBarrasByCB(c);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        return cb;
    }
}
