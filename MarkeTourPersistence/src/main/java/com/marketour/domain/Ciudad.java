package com.marketour.domain;
// Generated 12/04/2015 01:17:24 PM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ciudad generated by hbm2java
 */
@Entity
@Table(name="Ciudad"
    ,catalog="grupocre_marketour"
)
public class Ciudad  implements java.io.Serializable {


     private Integer id;
     private String descripcion;
     private int departamento;
     private String codigo;
     private int estado;

    public Ciudad() {
    }

	
    public Ciudad(int departamento, String codigo, int estado) {
        this.departamento = departamento;
        this.codigo = codigo;
        this.estado = estado;
    }
    public Ciudad(String descripcion, int departamento, String codigo, int estado) {
       this.descripcion = descripcion;
       this.departamento = departamento;
       this.codigo = codigo;
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

    
    @Column(name="descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="departamento", nullable=false)
    public int getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    
    @Column(name="codigo", nullable=false, length=50)
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    @Column(name="estado", nullable=false)
    public int getEstado() {
        return this.estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }




}


