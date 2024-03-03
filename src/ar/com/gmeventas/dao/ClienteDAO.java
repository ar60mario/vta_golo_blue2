/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.gmeventas.dao;

import ar.com.gmeventas.entities.Cliente;
import ar.com.gmeventas.util.HibernateUtils;
//import org.hibernate.classic.Session;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mar y Mar Informatica
 */
public class ClienteDAO extends GenericDAO{

    public Cliente getByCuit(String cuit) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("cuit", cuit));
        Cliente cliente = (Cliente) criteria.uniqueResult();
        return cliente;
    }

    public Cliente getByCodigo(String codigo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("codigo", codigo));
        
        Cliente cliente = (Cliente) criteria.uniqueResult();
        return cliente;
    }
    
    public List<Cliente> getAllClientesOrdenado() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.addOrder(Order.asc("razonSocial"));
        return (List<Cliente>) criteria.list();
    }
    
    public List<Cliente> getClientesByFiltro(String filtro) {
        List<Cliente> clientes = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        
        StringBuffer sb = new StringBuffer();
        sb.append("from Cliente clie ");
        sb.append("where clie.razonSocial like :filtro ");
        sb.append("order by clie.razonSocial asc");
        
        Query query = session.createQuery(sb.toString());
        query.setParameter("filtro", "%"+filtro+"%");
        
        clientes = (List<Cliente>) query.list();
                
        return clientes;
    }
}
