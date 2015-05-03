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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * MedioPago generated by hbm2java
 */
@Entity
@Table(name="MedioPago"
    ,catalog="devfloor_marketour"
)
public class MedioPago  implements java.io.Serializable {


     private Integer id;
     private FormaPago formaPago;
     private Cliente cliente;
     private Integer estado;
     private Pse pse;
     private Set<Compra> compras = new HashSet<Compra>(0);
     private TarjetaCredito tarjetaCredito;
     private ContraEntrega contraEntrega;

    public MedioPago() {
    }

    public MedioPago(FormaPago formaPago, Cliente cliente, Integer estado, Pse pse, Set<Compra> compras, TarjetaCredito tarjetaCredito, ContraEntrega contraEntrega) {
       this.formaPago = formaPago;
       this.cliente = cliente;
       this.estado = estado;
       this.pse = pse;
       this.compras = compras;
       this.tarjetaCredito = tarjetaCredito;
       this.contraEntrega = contraEntrega;
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
    @JoinColumn(name="forma")
    public FormaPago getFormaPago() {
        return this.formaPago;
    }
    
    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cliente")
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Column(name="estado")
    public Integer getEstado() {
        return this.estado;
    }
    
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="medioPago")
    public Pse getPse() {
        return this.pse;
    }
    
    public void setPse(Pse pse) {
        this.pse = pse;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="medioPago")
    public Set<Compra> getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="medioPago")
    public TarjetaCredito getTarjetaCredito() {
        return this.tarjetaCredito;
    }
    
    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="medioPago")
    public ContraEntrega getContraEntrega() {
        return this.contraEntrega;
    }
    
    public void setContraEntrega(ContraEntrega contraEntrega) {
        this.contraEntrega = contraEntrega;
    }




}


