/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.gmeventas.services;

import ar.com.gmeventas.bo.DomicilioBO;
import ar.com.gmeventas.entities.Domicilio;
import ar.com.gmeventas.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mcvalls
 */
public class DomicilioService {
    
    public Domicilio saveDomicilio(Domicilio domicilio) throws Exception{
        
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        try{
            domicilio = new DomicilioBO().saveDomicilio(domicilio);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        
        return domicilio;
    }
    
    public Domicilio updateDomicilio(Domicilio domicilio) throws Exception{
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        try{
            domicilio = new DomicilioBO().updateDomicilio(domicilio);
            tx.commit();
        }catch(Exception ex){
            tx.rollback();
            throw new Exception(ex);
        }
        
        return domicilio;
    }
    
}
