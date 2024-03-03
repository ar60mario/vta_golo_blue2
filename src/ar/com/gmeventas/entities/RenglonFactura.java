/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.gmeventas.entities;

/**
 *
 * @author Mar y Mar Informatica
 */
public class RenglonFactura {
    private Long id;
    private Integer itemNro;
    private Producto producto;
    private String descripcion;
    private Double gravado;
    private Float cantidad;
    private Double exento;
    private Double noGravado;
    private Double impuesto;
    private Double descuento;
    private Double iva;
    private Double total;
    private IvaVentas ivaVentas;
    private Double sugerido;
    private Double costoG;
    private Double costoI;

    public RenglonFactura() {
    }

    public RenglonFactura(Long id, Integer itemNro, Producto producto, String descripcion, Double gravado, Float cantidad, Double exento, Double noGravado, Double impuesto, Double descuento, Double iva, Double total, IvaVentas ivaVentas, Double sugerido, Double costoG, Double costoI) {
        this.id = id;
        this.itemNro = itemNro;
        this.producto = producto;
        this.descripcion = descripcion;
        this.gravado = gravado;
        this.cantidad = cantidad;
        this.exento = exento;
        this.noGravado = noGravado;
        this.impuesto = impuesto;
        this.descuento = descuento;
        this.iva = iva;
        this.total = total;
        this.ivaVentas = ivaVentas;
        this.sugerido = sugerido;
        this.costoG = costoG;
        this.costoI = costoI;
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

    public Double getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(Double noGravado) {
        this.noGravado = noGravado;
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

    public IvaVentas getIvaVentas() {
        return ivaVentas;
    }

    public void setIvaVentas(IvaVentas ivaVentas) {
        this.ivaVentas = ivaVentas;
    }

    public Double getSugerido() {
        return sugerido;
    }

    public void setSugerido(Double sugerido) {
        this.sugerido = sugerido;
    }

    public Double getCostoG() {
        return costoG;
    }

    public void setCostoG(Double costoG) {
        this.costoG = costoG;
    }

    public Double getCostoI() {
        return costoI;
    }

    public void setCostoI(Double costoI) {
        this.costoI = costoI;
    }

}