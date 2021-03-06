package com.marketour.business;
// Generated 21/04/2015 08:03:17 PM by Hibernate Tools 4.0.0


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
 * ContraEntrega generated by hbm2java
 */
@Entity
@Table(name="ContraEntrega"
    ,catalog="devfloor_marketour"
)
public class ContraEntrega  implements java.io.Serializable {


     private int id;
     private MedioPago medioPago;
     private String direccion;

    public ContraEntrega() {
    }

	
    public ContraEntrega(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
    public ContraEntrega(MedioPago medioPago, String direccion) {
       this.medioPago = medioPago;
       this.direccion = direccion;
    }
   

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public MedioPago getMedioPago() {
        return this.medioPago;
    }
    
    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }




}


