/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gmeventas.entities;

import java.util.Date;

/**
 *
 * @author Mario
 */
public class Pedido {

    private Long id;
    private Date fecha;
    private String letra;
    private Integer numeroPedido;
    private Cliente cliente;
    private Double gravado;
    private Double exento;
    private Double noGravado;
    private Double impuesto;
    private Double iva;
    private Double total;
    private Double descuentoGlobal;
    private Boolean facturado;
    private Boolean anulado;

    public Pedido() {
    }

    public Pedido(Long id, Date fecha, String letra, Integer numeroPedido, Cliente cliente, Double gravado, Double exento, Double noGravado, Double impuesto, Double iva, Double total, Double descuentoGlobal, Boolean facturado, Boolean anulado) {
        this.id = id;
        this.fecha = fecha;
        this.letra = letra;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.gravado = gravado;
        this.exento = exento;
        this.noGravado = noGravado;
        this.impuesto = impuesto;
        this.iva = iva;
        this.total = total;
        this.descuentoGlobal = descuentoGlobal;
        this.facturado = facturado;
        this.anulado = anulado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getGravado() {
        return gravado;
    }

    public void setGravado(Double gravado) {
        this.gravado = gravado;
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

    public Double getDescuentoGlobal() {
        return descuentoGlobal;
    }

    public void setDescuentoGlobal(Double descuentoGlobal) {
        this.descuentoGlobal = descuentoGlobal;
    }

    public Boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

}