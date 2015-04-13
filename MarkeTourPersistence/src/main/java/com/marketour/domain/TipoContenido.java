package com.marketour.domain;
// Generated 13/04/2015 12:03:59 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoContenido generated by hbm2java
 */
@Entity
@Table(name="TipoContenido"
    ,catalog="grupocre_marketour"
)
public class TipoContenido  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private int estado;

    public TipoContenido() {
    }

	
    public TipoContenido(int estado) {
        this.estado = estado;
    }
    public TipoContenido(String descripcion, int estado) {
       this.descripcion = descripcion;
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

    
    @Column(name="descripcion", length=50)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="estado", nullable=false)
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }




}


