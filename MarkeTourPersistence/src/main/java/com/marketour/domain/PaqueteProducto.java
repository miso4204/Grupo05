package com.marketour.domain;
// Generated 13/04/2015 12:03:59 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PaqueteProducto generated by hbm2java
 */
@Entity
@Table(name="PaqueteProducto"
    ,catalog="grupocre_marketour"
)
public class PaqueteProducto  implements java.io.Serializable {


     private Integer id;
     private Integer paquete;
     private Integer producto;

    public PaqueteProducto() {
    }

    public PaqueteProducto(Integer paquete, Integer producto) {
       this.paquete = paquete;
       this.producto = producto;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="paquete")
    public Integer getPaquete() {
        return this.paquete;
    }
    
    public void setPaquete(Integer paquete) {
        this.paquete = paquete;
    }

    
    @Column(name="producto")
    public Integer getProducto() {
        return this.producto;
    }
    
    public void setProducto(Integer producto) {
        this.producto = producto;
    }




}


