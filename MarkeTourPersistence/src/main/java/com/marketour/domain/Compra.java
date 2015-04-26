package com.marketour.domain;
// Generated 21/04/2015 08:03:17 PM by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Compra generated by hbm2java
 */
@Entity
@Table(name="Compra"
    ,catalog="devfloor_marketour"
)
public class Compra  implements java.io.Serializable {

	
     private Integer id;
     private MedioPago medioPago;
     private Cliente cliente;
     private Date fecha;
     private Integer calificacion;
     private Integer estado;
     private Set<ItemCompra> itemCompras = new HashSet<ItemCompra>(0);

    public Compra() {
    }

    public Compra(MedioPago medioPago, Cliente cliente, Date fecha, Integer calificacion, Integer estado, Set<ItemCompra> itemCompras) {
       this.medioPago = medioPago;
       this.cliente = cliente;
       this.fecha = fecha;
       this.calificacion = calificacion;
       this.estado = estado;
       this.itemCompras = itemCompras;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="medioPago")
    public MedioPago getMedioPago() {
        return this.medioPago;
    }
    
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

@ManyToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="cliente")
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha", length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="calificacion")
    public Integer getCalificacion() {
        return this.calificacion;
    }
    
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    
    @Column(name="estado")
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

@OneToMany(cascade= { CascadeType.ALL })
@JoinColumn(name="compra", referencedColumnName = "id")
    public Set<ItemCompra> getItemCompras() {
        return this.itemCompras;
    }
    
    public void setItemCompras(Set<ItemCompra> itemCompras) {
        this.itemCompras = itemCompras;
    }




}


