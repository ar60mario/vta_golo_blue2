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
public class Producto {
    private Long id;
    private Integer codigo;
    private Long codigoBarras;
    private String detalle;
    private Double precio;
    private Float impuesto;
    private Double sugerido;
    private Boolean inactivo;
    private Float stock;
    private Float stockMinimo;
    private Rubro rubro;
    private SubRubro subRubro;
    private Double costoP;
    private Double costoI;
    private Boolean auto;

    public Producto() {
    }

    public Producto(Long id, Integer codigo, Long codigoBarras, String detalle, Double precio, Float impuesto, Double sugerido, Boolean inactivo, Float stock, Float stockMinimo, Rubro rubro, SubRubro subRubro, Double costoP, Double costoI, Boolean auto) {
        this.id = id;
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.detalle = detalle;
        this.precio = precio;
        this.impuesto = impuesto;
        this.sugerido = sugerido;
        this.inactivo = inactivo;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.rubro = rubro;
        this.subRubro = subRubro;
        this.costoP = costoP;
        this.costoI = costoI;
        this.auto = auto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public Double getSugerido() {
        return sugerido;
    }

    public void setSugerido(Double sugerido) {
        this.sugerido = sugerido;
    }

    public Boolean getInactivo() {
        return inactivo;
    }

    public void setInactivo(Boolean inactivo) {
        this.inactivo = inactivo;
    }

    public Float getStock() {
        return stock;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public Float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public SubRubro getSubRubro() {
        return subRubro;
    }

    public void setSubRubro(SubRubro subRubro) {
        this.subRubro = subRubro;
    }

    public Double getCostoP() {
        return costoP;
    }

    public void setCostoP(Double costoP) {
        this.costoP = costoP;
    }

    public Double getCostoI() {
        return costoI;
    }

    public void setCostoI(Double costoI) {
        this.costoI = costoI;
    }

        public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

}
