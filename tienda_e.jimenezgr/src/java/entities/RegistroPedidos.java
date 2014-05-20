/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shkire
 */
@Entity
@Table(name = "REGISTRO_PEDIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroPedidos.findAll", query = "SELECT r FROM RegistroPedidos r"),
    @NamedQuery(name = "RegistroPedidos.findByNumero", query = "SELECT r FROM RegistroPedidos r WHERE r.registroPedidosPK.numero = :numero"),
    @NamedQuery(name = "RegistroPedidos.findByProducto", query = "SELECT r FROM RegistroPedidos r WHERE r.registroPedidosPK.producto = :producto"),
    @NamedQuery(name = "RegistroPedidos.findByCantidad", query = "SELECT r FROM RegistroPedidos r WHERE r.cantidad = :cantidad")})
public class RegistroPedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroPedidosPK registroPedidosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "NOMBRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto1;
    @JoinColumn(name = "NUMERO", referencedColumnName = "NUMERO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public RegistroPedidos() {
    }

    public RegistroPedidos(RegistroPedidosPK registroPedidosPK) {
        this.registroPedidosPK = registroPedidosPK;
    }

    public RegistroPedidos(RegistroPedidosPK registroPedidosPK, int cantidad) {
        this.registroPedidosPK = registroPedidosPK;
        this.cantidad = cantidad;
    }

    public RegistroPedidos(int numero, String producto) {
        this.registroPedidosPK = new RegistroPedidosPK(numero, producto);
    }

    public RegistroPedidosPK getRegistroPedidosPK() {
        return registroPedidosPK;
    }

    public void setRegistroPedidosPK(RegistroPedidosPK registroPedidosPK) {
        this.registroPedidosPK = registroPedidosPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroPedidosPK != null ? registroPedidosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPedidos)) {
            return false;
        }
        RegistroPedidos other = (RegistroPedidos) object;
        if ((this.registroPedidosPK == null && other.registroPedidosPK != null) || (this.registroPedidosPK != null && !this.registroPedidosPK.equals(other.registroPedidosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.RegistroPedidos[ registroPedidosPK=" + registroPedidosPK + " ]";
    }
    
}
