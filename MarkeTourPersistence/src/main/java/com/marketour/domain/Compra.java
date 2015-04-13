package com.marketour.domain;
// Generated 13/04/2015 12:03:59 AM by Hibernate Tools 4.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Compra generated by hbm2java
 */
@Entity
@Table(name="Compra"
    ,catalog="grupocre_marketour"
)
public class Compra  implements java.io.Serializable {


     private Integer id;
     private Integer cliente;
     private Date fecha;
     private Integer calificacion;
     private Integer medioPago;
     private Integer estado;

    public Compra() {
    }

    public Compra(Integer cliente, Date fecha, Integer calificacion, Integer medioPago, Integer estado) {
       this.cliente = cliente;
       this.fecha = fecha;
       this.calificacion = calificacion;
       this.medioPago = medioPago;
       this.estado = estado;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="cliente")
    public Integer getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Integer cliente) {
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

    
    @Column(name="medioPago")
    public Integer getMedioPago() {
        return this.medioPago;
    }
    
    public void setMedioPago(Integer medioPago) {
        this.medioPago = medioPago;
    }

    
    @Column(name="estado")
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }




}


