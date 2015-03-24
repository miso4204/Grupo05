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
@Table(name = "publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p"),
    @NamedQuery(name = "Publicacion.findById", query = "SELECT p FROM Publicacion p WHERE p.id = :id"),
    @NamedQuery(name = "Publicacion.findByTitulo", query = "SELECT p FROM Publicacion p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Publicacion.findByDescripcion", query = "SELECT p FROM Publicacion p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Publicacion.findByContenido", query = "SELECT p FROM Publicacion p WHERE p.contenido = :contenido"),
    @NamedQuery(name = "Publicacion.findByFechaPublicacion", query = "SELECT p FROM Publicacion p WHERE p.fechaPublicacion = :fechaPublicacion")})
public class Publicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1024)
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "fechaPublicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @OneToMany(mappedBy = "idPublicacion")
    private Collection<Publicacionproducto> publicacionproductoCollection;
    @OneToMany(mappedBy = "idPublicacion")
    private Collection<Proveedorpublicacion> proveedorpublicacionCollection;
    @OneToMany(mappedBy = "idPublicacion")
    private Collection<Publicacionvideo> publicacionvideoCollection;
    @JoinColumn(name = "idTipoContenido", referencedColumnName = "id")
    @ManyToOne
    private Tipocontenido idTipoContenido;
    @OneToMany(mappedBy = "idPublicacion")
    private Collection<Publicacionimagen> publicacionimagenCollection;

    public Publicacion() {
    }

    public Publicacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @XmlTransient
    public Collection<Publicacionproducto> getPublicacionproductoCollection() {
        return publicacionproductoCollection;
    }

    public void setPublicacionproductoCollection(Collection<Publicacionproducto> publicacionproductoCollection) {
        this.publicacionproductoCollection = publicacionproductoCollection;
    }

    @XmlTransient
    public Collection<Proveedorpublicacion> getProveedorpublicacionCollection() {
        return proveedorpublicacionCollection;
    }

    public void setProveedorpublicacionCollection(Collection<Proveedorpublicacion> proveedorpublicacionCollection) {
        this.proveedorpublicacionCollection = proveedorpublicacionCollection;
    }

    @XmlTransient
    public Collection<Publicacionvideo> getPublicacionvideoCollection() {
        return publicacionvideoCollection;
    }

    public void setPublicacionvideoCollection(Collection<Publicacionvideo> publicacionvideoCollection) {
        this.publicacionvideoCollection = publicacionvideoCollection;
    }

    public Tipocontenido getIdTipoContenido() {
        return idTipoContenido;
    }

    public void setIdTipoContenido(Tipocontenido idTipoContenido) {
        this.idTipoContenido = idTipoContenido;
    }

    @XmlTransient
    public Collection<Publicacionimagen> getPublicacionimagenCollection() {
        return publicacionimagenCollection;
    }

    public void setPublicacionimagenCollection(Collection<Publicacionimagen> publicacionimagenCollection) {
        this.publicacionimagenCollection = publicacionimagenCollection;
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
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Publicacion[ id=" + id + " ]";
    }
    
}
