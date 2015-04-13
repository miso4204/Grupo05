package com.marketour.domain;
// Generated 13/04/2015 12:03:59 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ContraEntrega generated by hbm2java
 */
@Entity
@Table(name="ContraEntrega"
    ,catalog="grupocre_marketour"
)
public class ContraEntrega  implements java.io.Serializable {


     private int id;
     private String direccion;

    public ContraEntrega() {
    }

	
    public ContraEntrega(int id) {
        this.id = id;
    }
    public ContraEntrega(int id, String direccion) {
       this.id = id;
       this.direccion = direccion;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="direccion")
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }




}

