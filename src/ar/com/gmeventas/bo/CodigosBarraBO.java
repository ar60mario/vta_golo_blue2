/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.CodigosBarraDAO;
import ar.com.gmeventas.entities.CodigoBarras;
import ar.com.gmeventas.entities.Producto;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class CodigosBarraBO {

    private final CodigosBarraDAO dao = new CodigosBarraDAO();

    private static final Logger logger = Logger.getLogger("CodigosBarraBO");

    public CodigoBarras saveCodigoBarras(CodigoBarras cb) throws Exception {
        try {
            dao.save(cb);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return cb;
    }

    public void deleteCodigoBarras(CodigoBarras cb) throws Exception {
        try {
            dao.delete(cb);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return;
    }
    
    public List<CodigoBarras> getAllCodigos() throws Exception {
        List<CodigoBarras> ctaCte = null;
        try{
            ctaCte = dao.getAllCodigos();
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return ctaCte;
    }
    
    public Boolean getSinCodigo(Producto p) throws Exception {
        Boolean ctaCte = false;
        try{
            ctaCte = dao.getSinCodigo(p);
        }catch(HibernateException ex){
            //throw new Exception(ex);
        }
        return ctaCte;
    }
    
    public CodigoBarras getCodigoBarrasByCB(Long c) throws Exception {
        CodigoBarras cb = null;
        try {
            cb = dao.getCodigoBarrasByCB(c);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return cb;
    }
}
