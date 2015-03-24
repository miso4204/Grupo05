/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "qrd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qrd.findAll", query = "SELECT q FROM Qrd q"),
    @NamedQuery(name = "Qrd.findById", query = "SELECT q FROM Qrd q WHERE q.id = :id"),
    @NamedQuery(name = "Qrd.findByFecha", query = "SELECT q FROM Qrd q WHERE q.fecha = :fecha"),
    @NamedQuery(name = "Qrd.findByDescripcion", query = "SELECT q FROM Qrd q WHERE q.descripcion = :descripcion"),
    @NamedQuery(name = "Qrd.findBySolucion", query = "SELECT q FROM Qrd q WHERE q.solucion = :solucion"),
    @NamedQuery(name = "Qrd.findByEstado", query = "SELECT q FROM Qrd q WHERE q.estado = :estado")})
public class Qrd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1024)
    @Column(name = "solucion")
    private String solucion;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "idQRD")
    private Collection<Compra> compraCollection;
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    @ManyToOne
    private Producto idProducto;

    public Qrd() {
    }

    public Qrd(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qrd)) {
            return false;
        }
        Qrd other = (Qrd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Qrd[ id=" + id + " ]";
    }
    
}
