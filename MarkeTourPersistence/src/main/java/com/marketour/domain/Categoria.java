package com.marketour.domain;
// Generated 21/04/2015 08:03:17 PM by Hibernate Tools 4.0.0


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name="Categoria"
    ,catalog="devfloor_marketour"
)
public class Categoria  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private int estado;
     private Set<Producto> productos = new HashSet<Producto>(0);

    public Categoria() {
    }

	
    public Categoria(int estado) {
        this.estado = estado;
    }
    public Categoria(String descripcion, int estado, Set<Producto> productos) {
       this.descripcion = descripcion;
       this.estado = estado;
       this.productos = productos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="descripcion")
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

@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="categoria")
    public Set<Producto> getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }




}


