package com.marketour.domain;
// Generated 2/05/2015 08:24:36 PM by Hibernate Tools 4.0.0


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
 * TipoContenido generated by hbm2java
 */
@Entity
@Table(name="TipoContenido"
    ,catalog="devfloor_marketour"
)
public class TipoContenido  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private int estado;
     private Set<Contenido> contenidos = new HashSet<Contenido>(0);

    public TipoContenido() {
    }

	
    public TipoContenido(int estado) {
        this.estado = estado;
    }
    public TipoContenido(String descripcion, int estado, Set<Contenido> contenidos) {
       this.descripcion = descripcion;
       this.estado = estado;
       this.contenidos = contenidos;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipoContenido")
    public Set<Contenido> getContenidos() {
        return this.contenidos;
    }
    
    public void setContenidos(Set<Contenido> contenidos) {
        this.contenidos = contenidos;
    }




}


