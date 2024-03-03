/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.bo;

import ar.com.gmeventas.dao.RenglonFacturaDAO;
import ar.com.gmeventas.entities.IvaVentas;
import ar.com.gmeventas.entities.RenglonFactura;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;

/**
 *
 * @author Mario
 */
public class RenglonFacturaBO {

    private final RenglonFacturaDAO dao = new RenglonFacturaDAO();

    private static final Logger logger = Logger.getLogger("RenglonFacturaBO");

    public RenglonFactura saveRenglon(RenglonFactura renglonFactura) throws Exception {
        try {
            dao.save(renglonFactura);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return renglonFactura;
    }

    public List<RenglonFactura> getAllRenglonFacturaFromIvaVentas(IvaVentas idIvaVentas) throws Exception {
        List<RenglonFactura> listRenglonFactura = null;
        try {
            listRenglonFactura = dao.getRenglonFacturaFromIvaVentas(idIvaVentas);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonFactura;
    }
    //getRenglonesEntreFechas(Date d1, Date d2)
    public List<RenglonFactura> getRenglonesEntreFechas(Date d1, Date d2) throws Exception {
        List<RenglonFactura> listRenglonFactura = null;
        try {
            listRenglonFactura = dao.getRenglonesEntreFechas(d1, d2);
        } catch (HibernateException ex) {
            throw new Exception(ex);
        }
        return listRenglonFactura;
    }
    /*
     public void saveFacturaYRenglones(IvaVentas iv, List<RenglonFactura> rf) throws Exception {
     dao.save(iv)
     if(listaClientes != null && !listaClientes.isEmpty()){
     for(Cliente cliente : listaClientes){
     Domicilio domicilio = cliente.getDomicilio();
     try{
     domicilio = db.saveDomicilio(domicilio);
     cliente.setDomicilio(domicilio);
     dao.save(cliente);
     }catch(HibernateException ex){
     throw new Exception("Ha ocurrido un problema intentando guardar el Cliente.\nPor favor intente nuevamente mas tarde.");
     }
     }
     }
     }
     */
}
