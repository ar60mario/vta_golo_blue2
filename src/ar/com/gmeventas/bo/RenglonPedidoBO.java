/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.RenglonPedidoDAO;
import ar.com.gmeventas.entities.RenglonPedido;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonPedidoBO {
    
    private final RenglonPedidoDAO dao = new RenglonPedidoDAO();

    private static final Logger logger = Logger.getLogger("RenglonPedidoBO");
    
    public RenglonPedido saveRenglonPedido(RenglonPedido renglon) throws Exception {
        try {
            dao.save(renglon);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglon;
    }
    
}
