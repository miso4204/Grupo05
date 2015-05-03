package com.marketour.domain;
// Generated 2/05/2015 08:24:36 PM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * TarjetaCredito generated by hbm2java
 */
@Entity
@Table(name="TarjetaCredito"
    ,catalog="devfloor_marketour"
)
public class TarjetaCredito  implements java.io.Serializable {


     private int id;
     private MedioPago medioPago;
     private String numero;
     private String nombre;
     private String codigo;
     private String vencimiento;

    public TarjetaCredito() {
    }

	
    public TarjetaCredito(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
    public TarjetaCredito(MedioPago medioPago, String numero, String nombre, String codigo, String vencimiento) {
       this.medioPago = medioPago;
       this.numero = numero;
       this.nombre = nombre;
       this.codigo = codigo;
       this.vencimiento = vencimiento;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="medioPago"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public MedioPago getMedioPago() {
        return this.medioPago;
    }
    
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    
    @Column(name="numero", length=50)
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    @Column(name="nombre", length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="codigo", length=50)
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    @Column(name="vencimiento", length=50)
    public String getVencimiento() {
        return this.vencimiento;
    }
    
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }




}


