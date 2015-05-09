package com.marketour.domain;
// Generated 2/05/2015 08:24:36 PM by Hibernate Tools 4.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Tour generated by hbm2java
 */
@Entity
@Table(name="Tour"
    ,catalog="devfloor_marketour"
)
public class Tour  implements java.io.Serializable {


     private int id;
     private Ciudad ciudadByDestino;
     private Ciudad ciudadByOrigen;
     private Producto producto;
     private Date fechaEntrada;
     private Date fechaSalida;

    public Tour() {
    }

	
    public Tour(Producto producto) {
        this.producto = producto;
    }
    public Tour(Ciudad ciudadByDestino, Ciudad ciudadByOrigen, Producto producto, Date fechaEntrada, Date fechaSalida) {
       this.ciudadByDestino = ciudadByDestino;
       this.ciudadByOrigen = ciudadByOrigen;
       this.producto = producto;
       this.fechaEntrada = fechaEntrada;
       this.fechaSalida = fechaSalida;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="producto"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="destino")
    public Ciudad getCiudadByDestino() {
        return this.ciudadByDestino;
    }
    
    public void setCiudadByDestino(Ciudad ciudadByDestino) {
        this.ciudadByDestino = ciudadByDestino;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="origen")
    public Ciudad getCiudadByOrigen() {
        return this.ciudadByOrigen;
    }
    
    public void setCiudadByOrigen(Ciudad ciudadByOrigen) {
        this.ciudadByOrigen = ciudadByOrigen;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaEntrada", length=19)
    public Date getFechaEntrada() {
        return this.fechaEntrada;
    }
    
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaSalida", length=19)
    public Date getFechaSalida() {
        return this.fechaSalida;
    }
    
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }




}


