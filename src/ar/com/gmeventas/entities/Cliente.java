package ar.com.gmeventas.entities;

public class Cliente {
    private Long id;
    private String codigo;
    private String razonSocial;
    private String cuit;
    private Domicilio domicilio;
    private Integer categoriaDeIva;
    private String telefono;
    private String celular;
    private String mail;
    private Boolean tieneDescuento;
    private Float descuento;
    private Double saldo;
    private Integer formaDePago;
    private String observaciones;
    private Boolean activo;
    private String tipo;
   
    /* categorias IVA
    1 Inscripto
    2 Monotributo
    3 Exento
    4 Consumidor Final.
    
    formas de pago
    1 Contado
    2 7 dias fecha factura
    3 14 dias fecha factura
    4 otro
     */
    /**
     * @return the id
     */
    public Cliente() {
    }

    public Cliente(Long id, String codigo, String razonSocial, String cuit, Domicilio domicilio, Integer categoriaDeIva, String telefono, String celular, String mail, Boolean tieneDescuento, Float descuento, Double saldo, Integer formaDePago, String observaciones, Boolean activo, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.categoriaDeIva = categoriaDeIva;
        this.telefono = telefono;
        this.celular = celular;
        this.mail = mail;
        this.tieneDescuento = tieneDescuento;
        this.descuento = descuento;
        this.saldo = saldo;
        this.formaDePago = formaDePago;
        this.observaciones = observaciones;
        this.activo = activo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCategoriaDeIva() {
        return categoriaDeIva;
    }

    public void setCategoriaDeIva(Integer categoriaDeIva) {
        this.categoriaDeIva = categoriaDeIva;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getTieneDescuento() {
        return tieneDescuento;
    }

    public void setTieneDescuento(Boolean tieneDescuento) {
        this.tieneDescuento = tieneDescuento;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(Integer formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}