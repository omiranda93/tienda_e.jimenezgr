/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shkire
 */
@Entity
@Table(name = "PRODUCTO_TIENE_IMAGEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoTieneImagen.findAll", query = "SELECT p FROM ProductoTieneImagen p"),
    @NamedQuery(name = "ProductoTieneImagen.findByProducto", query = "SELECT p FROM ProductoTieneImagen p WHERE p.productoTieneImagenPK.producto = :producto"),
    @NamedQuery(name = "ProductoTieneImagen.findByUri", query = "SELECT p FROM ProductoTieneImagen p WHERE p.productoTieneImagenPK.uri = :uri")})
public class ProductoTieneImagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoTieneImagenPK productoTieneImagenPK;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto1;

    public ProductoTieneImagen() {
    }

    public ProductoTieneImagen(ProductoTieneImagenPK productoTieneImagenPK) {
        this.productoTieneImagenPK = productoTieneImagenPK;
    }

    public ProductoTieneImagen(String producto, String uri) {
        this.productoTieneImagenPK = new ProductoTieneImagenPK(producto, uri);
    }

    public ProductoTieneImagenPK getProductoTieneImagenPK() {
        return productoTieneImagenPK;
    }

    public void setProductoTieneImagenPK(ProductoTieneImagenPK productoTieneImagenPK) {
        this.productoTieneImagenPK = productoTieneImagenPK;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoTieneImagenPK != null ? productoTieneImagenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoTieneImagen)) {
            return false;
        }
        ProductoTieneImagen other = (ProductoTieneImagen) object;
        if ((this.productoTieneImagenPK == null && other.productoTieneImagenPK != null) || (this.productoTieneImagenPK != null && !this.productoTieneImagenPK.equals(other.productoTieneImagenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.ProductoTieneImagen[ productoTieneImagenPK=" + productoTieneImagenPK + " ]";
    }
    
}
