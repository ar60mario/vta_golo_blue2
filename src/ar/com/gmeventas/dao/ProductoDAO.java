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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mario
 */
public class ProductoDAO extends GenericDAO {

    public Producto getByCodigo(Integer codigo) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("codigo", codigo));

        Producto producto = (Producto) criteria.uniqueResult();
        return producto;
    }

    public List<Producto> getAllProductosOrdenado(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod ");
        if (!filtro.equals("*")) {
            sb.append("where prod.detalle like :filtro ");
        }
        sb.append("order by prod.detalle asc");

        Query query = session.createQuery(sb.toString());
        if (!filtro.equals("*")) {
            query.setParameter("filtro", "%" + filtro + "%");
        }
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getAllOrdByCodigo(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod ");
        if (!filtro.equals("*")) {
            sb.append("where prod.detalle like :filtro ");
        }
        sb.append("order by prod.codigo asc");

        Query query = session.createQuery(sb.toString());
        if (!filtro.equals("*")) {
            query.setParameter("filtro", "%" + filtro + "%");
        }
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getAllOrdByRubro(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod ");
        if (!filtro.equals("*")) {
            sb.append("where prod.detalle like :filtro ");
        }
        sb.append("order by prod.rubro, prod.subRubro  asc");

        Query query = session.createQuery(sb.toString());
        if (!filtro.equals("*")) {
            query.setParameter("filtro", "%" + filtro + "%");
        }
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getProductosByFiltro(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod ");
        sb.append("where prod.detalle like :filtro ");
        sb.append("order by prod.detalle asc");

        Query query = session.createQuery(sb.toString());
        query.setParameter("filtro", "%" + filtro + "%");
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getProductosSinCodigoBarras(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod where ");
        if (!filtro.equals("*")) {
            sb.append("prod.detalle like :filtro and ");
        }
        sb.append(" prod.codigoBarras = '0' ");
        sb.append("order by prod.detalle asc");

        Query query = session.createQuery(sb.toString());
        if (!filtro.equals("*")) {
            query.setParameter("filtro", "%" + filtro + "%");
        }
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getAllProductosEnCero(String filtro) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod where ");
        if (!filtro.equals("*")) {
            sb.append("prod.detalle like :filtro and ");
        }
        sb.append("prod.precio = 0.0 ");
        sb.append("order by prod.detalle asc");

        Query query = session.createQuery(sb.toString());
        if (!filtro.equals("*")) {
            query.setParameter("filtro", "%" + filtro + "%");
        }
        productos = (List<Producto>) query.list();
        return productos;
    }

    public List<Producto> getProductosInactivos() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.eq("inactivo", Boolean.TRUE));
        criteria.addOrder(Order.asc("detalle"));

        return (List<Producto>) criteria.list();
    }

    public Producto getByCodigoBarras(Long codigoBarras) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(CodigoBarras.class);
        criteria.add(Restrictions.eq("codigo", codigoBarras));
        CodigoBarras cb = (CodigoBarras) criteria.uniqueResult();
        session = HibernateUtils.getSessionFactory().getCurrentSession();
        Integer co = cb.getProducto().getCodigo();
        Criteria cr = session.createCriteria(Producto.class);
        cr.add(Restrictions.eq("codigo", co));
        Producto producto = (Producto) cr.uniqueResult();
        return producto;
    }
    
    public List<Producto> getProductosEntreCodigos(Integer de, Integer a) {
        List<Producto> productos = null;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();

        StringBuffer sb = new StringBuffer();
        sb.append("from Producto prod where ");
        sb.append("prod.codigo >= :de and ");
        sb.append("prod.codigo <= :a ");
        sb.append("order by prod.codigo asc");

        Query query = session.createQuery(sb.toString());
        query.setParameter("de", de);
        query.setParameter("a", a);
        productos = (List<Producto>) query.list();
        return productos;
    }
    
}
