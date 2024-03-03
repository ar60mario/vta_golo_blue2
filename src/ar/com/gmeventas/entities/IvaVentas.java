
package ar.com.gmeventas.entities;

import java.util.Date;

public class IvaVentas {
    private Long id;
    private Date fecha;
    private String letra;
    private Integer numeroSucursal;
    private Integer numeroFactura;
    private Cliente cliente;
    private Double gravado;
    private Double exento;
    private Double noGravado;
    private Double impuesto;
    private Double iva;
    private Double total;
    private Double descuentoGlobal;
    private Date fechaCae;
    private Long cae;
    private String letraReferencia;
    private Integer numeroSucursalReferencia;
    private Integer numeroFacturaReferencia;

    public IvaVentas() {
    }

    public IvaVentas(Long id, Date fecha, String letra, Integer numeroSucursal, Integer numeroFactura, Cliente cliente, Double gravado, Double exento, Double noGravado, Double impuesto, Double iva, Double total, Double descuentoGlobal, Date fechaCae, Long cae, String letraReferencia, Integer numeroSucursalReferencia, Integer numeroFacturaReferencia) {
        this.id = id;
        this.fecha = fecha;
        this.letra = letra;
        this.numeroSucursal = numeroSucursal;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.gravado = gravado;
        this.exento = exento;
        this.noGravado = noGravado;
        this.impuesto = impuesto;
        this.iva = iva;
        this.total = total;
        this.descuentoGlobal = descuentoGlobal;
        this.fechaCae = fechaCae;
        this.cae = cae;
        this.letraReferencia = letraReferencia;
        this.numeroSucursalReferencia = numeroSucursalReferencia;
        this.numeroFacturaReferencia = numeroFacturaReferencia;
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

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
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

    public Date getFechaCae() {
        return fechaCae;
    }

    public void setFechaCae(Date fechaCae) {
        this.fechaCae = fechaCae;
    }

    public Long getCae() {
        return cae;
    }

    public void setCae(Long cae) {
        this.cae = cae;
    }

    public String getLetraReferencia() {
        return letraReferencia;
    }

    public void setLetraReferencia(String letraReferencia) {
        this.letraReferencia = letraReferencia;
    }

    public Integer getNumeroSucursalReferencia() {
        return numeroSucursalReferencia;
    }

    public void setNumeroSucursalReferencia(Integer numeroSucursalReferencia) {
        this.numeroSucursalReferencia = numeroSucursalReferencia;
    }

    public Integer getNumeroFacturaReferencia() {
        return numeroFacturaReferencia;
    }

    public void setNumeroFacturaReferencia(Integer numeroFacturaReferencia) {
        this.numeroFacturaReferencia = numeroFacturaReferencia;
    }
    
}