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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id"),
    @NamedQuery(name = "Compra.findByFechaPublicacion", query = "SELECT c FROM Compra c WHERE c.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Compra.findByCalificacion", query = "SELECT c FROM Compra c WHERE c.calificacion = :calificacion"),
    @NamedQuery(name = "Compra.findByEstado", query = "SELECT c FROM Compra c WHERE c.estado = :estado")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechaPublicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "idQRD", referencedColumnName = "id")
    @ManyToOne
    private Qrd idQRD;
    @JoinColumn(name = "idFactura", referencedColumnName = "id")
    @ManyToOne
    private Factura idFactura;
    @JoinColumn(name = "idMedioPago", referencedColumnName = "id")
    @ManyToOne
    private Mediopago idMedioPago;
    @OneToMany(mappedBy = "idCompra")
    private Collection<Clientecompra> clientecompraCollection;

    public Compra() {
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Qrd getIdQRD() {
        return idQRD;
    }

    public void setIdQRD(Qrd idQRD) {
        this.idQRD = idQRD;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Mediopago getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(Mediopago idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    @XmlTransient
    public Collection<Clientecompra> getClientecompraCollection() {
        return clientecompraCollection;
    }

    public void setClientecompraCollection(Collection<Clientecompra> clientecompraCollection) {
        this.clientecompraCollection = clientecompraCollection;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compra[ id=" + id + " ]";
    }
    
}
