/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.dao;

import ar.com.gmeventas.entities.CodigoBarras;
import ar.com.gmeventas.entities.Producto;
import ar.com.gmeventas.util.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcela
 */
public class CodigosBarraDAO  extends GenericDAO{
    
    public List<CodigoBarras> getAllCodigos() {
        List<CodigoBarras> codigos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from CodigoBarras co ");
        sb.append("order by co.producto, co.codigo asc");
        Query query = session.createQuery(sb.toString());
        codigos = (List<CodigoBarras>) query.list();
        return codigos;
    }
   
    public Boolean getSinCodigo(Producto p) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CodigoBarras.class);
        criteria.add(Restrictions.eq("producto", p));
        List<Producto> z = (List<Producto>) criteria.list();
        return z.get(0) != null;
    }
    
    public CodigoBarras getCodigoBarrasByCB(Long c){
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CodigoBarras.class);
        criteria.add(Restrictions.eq("codigo", c));
        CodigoBarras cb = (CodigoBarras) criteria.uniqueResult();
        return cb;
    }
}
