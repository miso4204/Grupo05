package com.marketour.domain;
// Generated 20/04/2015 12:40:07 PM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contenido generated by hbm2java
 */
@Entity
@Table(name="Contenido"
    ,catalog="devfloor_marketour"
)
public class Contenido  implements java.io.Serializable {


     private Integer id;
     private Producto producto;
     private TipoContenido tipoContenido;
     private String contenido;
     private int estado;

    public Contenido() {
    }

	
    public Contenido(Producto producto, String contenido, int estado) {
        this.producto = producto;
        this.contenido = contenido;
        this.estado = estado;
    }
    public Contenido(Producto producto, TipoContenido tipoContenido, String contenido, int estado) {
       this.producto = producto;
       this.tipoContenido = tipoContenido;
       this.contenido = contenido;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producto", nullable=false)
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipo")
    public TipoContenido getTipoContenido() {
        return this.tipoContenido;
    }
    
    public void setTipoContenido(TipoContenido tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    
    @Column(name="contenido", nullable=false, length=65535)
    public String getContenido() {
        return this.contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    @Column(name="estado", nullable=false)
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }




}


