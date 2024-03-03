/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.entities;

/**
 *
 * @author Mario
 */
public class CodigoBarras {
    private Long id;
    private Long codigo;
    private Producto producto;

    public CodigoBarras() {
    }

    public CodigoBarras(Long id, Long codigo, Producto producto) {
        this.id = id;
        this.codigo = codigo;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
