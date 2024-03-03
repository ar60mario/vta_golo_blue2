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
public class RenglonPedido {
    private Long id;
    private Integer itemNro;
    private Producto producto;
    private String descripcion;
    private Double gravado;
    private Double noGravado;
    private Float cantidad;
    private Double exento;
    private Double impuesto;
    private Double descuento;
    private Double iva;
    private Double total;
    private Pedido pedido;
    private Double sugerido;

    public RenglonPedido() {
    }

    public RenglonPedido(Long id, Integer itemNro, Producto producto, String descripcion, Double gravado, Double noGravado, Float cantidad, Double exento, Double impuesto, Double descuento, Double iva, Double total, Pedido pedido, Double sugerido) {
        this.id = id;
        this.itemNro = itemNro;
        this.producto = producto;
        this.descripcion = descripcion;
        this.gravado = gravado;
        this.noGravado = noGravado;
        this.cantidad = cantidad;
        this.exento = exento;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.iva = iva;
        this.total = total;
        this.pedido = pedido;
        this.sugerido = sugerido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemNro() {
        return itemNro;
    }

    public void setItemNro(Integer itemNro) {
        this.itemNro = itemNro;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getGravado() {
        return gravado;
    }

    public void setGravado(Double gravado) {
        this.gravado = gravado;
    }

    public Double getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(Double noGravado) {
        this.noGravado = noGravado;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Double getExento() {
        return exento;
    }

    public void setExento(Double exento) {
        this.exento = exento;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getSugerido() {
        return sugerido;
    }

    public void setSugerido(Double sugerido) {
        this.sugerido = sugerido;
    }

}
