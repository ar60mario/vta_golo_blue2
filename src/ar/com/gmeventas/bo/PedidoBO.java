/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.PedidoDAO;
import ar.com.gmeventas.entities.Pedido;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcela
 */
public class PedidoBO {
    
    private final PedidoDAO dao = new PedidoDAO();
    
    private static final Logger logger = Logger.getLogger("PedidoBO");
    
    
    public Pedido savePedido(Pedido pedido) throws Exception{
        try{
          dao.save(pedido);
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return pedido;
    }

    public List<Pedido> getAllPedidos() throws Exception {
        
        List<Pedido> listPedidos = null;
        
        try{
            listPedidos = dao.getAll(Pedido.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
        
    }

    public List<Pedido> getAllPedidosByCodigoYFecha() throws Exception {
        List<Pedido> listPedidos = null;
        
        try{
            listPedidos = dao.getAll(Pedido.class);			
        }catch(HibernateException ex){
            throw new Exception(ex);
        }
        return listPedidos;
    }
    
}
