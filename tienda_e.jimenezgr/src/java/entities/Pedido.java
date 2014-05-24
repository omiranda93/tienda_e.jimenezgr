/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByNumero", query = "SELECT p FROM Pedido p WHERE p.numero = :numero"),
    @NamedQuery(name = "Pedido.findByPendiente", query = "SELECT p FROM Pedido p WHERE p.pendiente = :pendiente"),
    @NamedQuery(name = "Pedido.findByEstado", query = "SELECT p FROM Pedido p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pedido.findByNombre", query = "SELECT p FROM Pedido p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pedido.findByDireccion", query = "SELECT p FROM Pedido p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Pedido.findByTelefono", query = "SELECT p FROM Pedido p WHERE p.telefono = :telefono")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PENDIENTE")
    private Boolean pendiente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @JoinColumn(name = "USUARIO", referencedColumnName = "CREDENCIALUSUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<RegistroPedidos> registroPedidosCollection;

    public Pedido() {
        this.registroPedidosCollection = new ArrayList <RegistroPedidos> ();
    }

    public Pedido(Integer numero) {
        this.numero = numero;
    }

    public Pedido(Integer numero, Boolean pendiente, String estado, String nombre, String direccion, String telefono, Usuario usuario) {
        this.numero = numero;
        this.pendiente = pendiente;
        this.estado = estado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.registroPedidosCollection = new ArrayList <RegistroPedidos> ();
    }
    
    public void setPedido(Pedido ped){
        setPendiente(ped.getPendiente());
        setEstado(ped.getEstado());
        setDireccion(ped.getDireccion());
        setNombre(ped.getNombre());
        setRegistroPedidosCollection(ped.getRegistroPedidosCollection());
        setTelefono(ped.getTelefono());
        setUsuario(ped.getUsuario());
    }
    


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Boolean getPendiente() {
        return pendiente;
    }

    public void setPendiente(Boolean pendiente) {
        this.pendiente = pendiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<RegistroPedidos> getRegistroPedidosCollection() {
        return registroPedidosCollection;
    }

    public void setRegistroPedidosCollection(Collection<RegistroPedidos> registroPedidosCollection) {
        this.registroPedidosCollection = registroPedidosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Pedido[ numero=" + numero + " ]";
    }
    
    public double precioTotal(){
        double total=0;
        for (RegistroPedidos registro:registroPedidosCollection){
            total=total+(registro.getProducto1().getPrecio()*registro.getCantidad());
        }
        return total;
    }
    
    public int totalProductos(){
        int total=0;
        for (RegistroPedidos registro:registroPedidosCollection){
            total=total+registro.getCantidad();
        }
        return total;
    }
}
