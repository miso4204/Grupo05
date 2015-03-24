/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByValor", query = "SELECT p FROM Producto p WHERE p.valor = :valor")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Promocion> promocionCollection;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Publicacionproducto> publicacionproductoCollection;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Qrd> qrdCollection;
    @OneToMany(mappedBy = "idFavorito")
    private Collection<Favorito> favoritoCollection;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Facturaproducto> facturaproductoCollection;
    @OneToMany(mappedBy = "idSimilar")
    private Collection<Productosimilar> productosimilarCollection;
    @OneToMany(mappedBy = "idProducto")
    private Collection<Productosimilar> productosimilarCollection1;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Collection<Promocion> getPromocionCollection() {
        return promocionCollection;
    }

    public void setPromocionCollection(Collection<Promocion> promocionCollection) {
        this.promocionCollection = promocionCollection;
    }

    @XmlTransient
    public Collection<Publicacionproducto> getPublicacionproductoCollection() {
        return publicacionproductoCollection;
    }

    public void setPublicacionproductoCollection(Collection<Publicacionproducto> publicacionproductoCollection) {
        this.publicacionproductoCollection = publicacionproductoCollection;
    }

    @XmlTransient
    public Collection<Qrd> getQrdCollection() {
        return qrdCollection;
    }

    public void setQrdCollection(Collection<Qrd> qrdCollection) {
        this.qrdCollection = qrdCollection;
    }

    @XmlTransient
    public Collection<Favorito> getFavoritoCollection() {
        return favoritoCollection;
    }

    public void setFavoritoCollection(Collection<Favorito> favoritoCollection) {
        this.favoritoCollection = favoritoCollection;
    }

    @XmlTransient
    public Collection<Facturaproducto> getFacturaproductoCollection() {
        return facturaproductoCollection;
    }

    public void setFacturaproductoCollection(Collection<Facturaproducto> facturaproductoCollection) {
        this.facturaproductoCollection = facturaproductoCollection;
    }

    @XmlTransient
    public Collection<Productosimilar> getProductosimilarCollection() {
        return productosimilarCollection;
    }

    public void setProductosimilarCollection(Collection<Productosimilar> productosimilarCollection) {
        this.productosimilarCollection = productosimilarCollection;
    }

    @XmlTransient
    public Collection<Productosimilar> getProductosimilarCollection1() {
        return productosimilarCollection1;
    }

    public void setProductosimilarCollection1(Collection<Productosimilar> productosimilarCollection1) {
        this.productosimilarCollection1 = productosimilarCollection1;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Producto[ id=" + id + " ]";
    }
    
}
