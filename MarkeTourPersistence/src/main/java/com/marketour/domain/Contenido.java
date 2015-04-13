package com.marketour.domain;
// Generated 13/04/2015 12:03:59 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contenido generated by hbm2java
 */
@Entity
@Table(name="Contenido"
    ,catalog="grupocre_marketour"
)
public class Contenido  implements java.io.Serializable {


     private Integer id;
     private int producto;
     private String contenido;
     private Integer tipo;
     private int estado;

    public Contenido() {
    }

	
    public Contenido(int producto, String contenido, int estado) {
        this.producto = producto;
        this.contenido = contenido;
        this.estado = estado;
    }
    public Contenido(int producto, String contenido, Integer tipo, int estado) {
       this.producto = producto;
       this.contenido = contenido;
       this.tipo = tipo;
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

    
    @Column(name="producto", nullable=false)
    public int getProducto() {
        return this.producto;
    }
    
    public void setProducto(int producto) {
        this.producto = producto;
    }

    
    @Column(name="contenido", nullable=false, length=65535)
    public String getContenido() {
        return this.contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    @Column(name="tipo")
    public Integer getTipo() {
        return this.tipo;
    }
    
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="estado", nullable=false)
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }




}


