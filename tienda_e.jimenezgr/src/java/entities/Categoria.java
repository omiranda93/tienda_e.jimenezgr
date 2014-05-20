/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Shkire
 */
@Entity
@Table(name = "CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByClave", query = "SELECT c FROM Categoria c WHERE c.clave = :clave"),
    @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Categoria.findByEssuper", query = "SELECT c FROM Categoria c WHERE c.essuper = :essuper")})
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLAVE")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESSUPER")
    private Serializable essuper;
    @JoinTable(name = "CATEGORIA_TIENE_HIJAS", joinColumns = {
        @JoinColumn(name = "HIJA", referencedColumnName = "CLAVE")}, inverseJoinColumns = {
        @JoinColumn(name = "MADRE", referencedColumnName = "CLAVE")})
    @ManyToMany
    private Collection<Categoria> categoriaCollection;
    @ManyToMany(mappedBy = "categoriaCollection")
    private Collection<Categoria> categoriaCollection1;
    @JoinTable(name = "PRODUCTO_TIENE_CATEGORIA", joinColumns = {
        @JoinColumn(name = "CATEGORIA", referencedColumnName = "CLAVE")}, inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTO", referencedColumnName = "NOMBRE")})
    @ManyToMany
    private Collection<Producto> productoCollection;

    public Categoria() {
    }

    public Categoria(String clave) {
        this.clave = clave;
    }

    public Categoria(String clave, String nombre, Serializable essuper) {
        this.clave = clave;
        this.nombre = nombre;
        this.essuper = essuper;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Serializable getEssuper() {
        return essuper;
    }

    public void setEssuper(Serializable essuper) {
        this.essuper = essuper;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    @XmlTransient
    public Collection<Categoria> getCategoriaCollection1() {
        return categoriaCollection1;
    }

    public void setCategoriaCollection1(Collection<Categoria> categoriaCollection1) {
        this.categoriaCollection1 = categoriaCollection1;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Categoria[ clave=" + clave + " ]";
    }
    
}
