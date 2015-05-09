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
 * Pse generated by hbm2java
 */
@Entity
@Table(name="PSE"
    ,catalog="devfloor_marketour"
)
public class Pse  implements java.io.Serializable {


     private int id;
     private MedioPago medioPago;
     private String url;

    public Pse() {
    }

	
    public Pse(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
    public Pse(MedioPago medioPago, String url) {
       this.medioPago = medioPago;
       this.url = url;
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

    
    @Column(name="url")
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }




}


