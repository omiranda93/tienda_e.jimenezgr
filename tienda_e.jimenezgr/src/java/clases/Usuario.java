/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCredencialusuario", query = "SELECT u FROM Usuario u WHERE u.credencialusuario = :credencialusuario"),
    @NamedQuery(name = "Usuario.findByCredencialcontrasenia", query = "SELECT u FROM Usuario u WHERE u.credencialcontrasenia = :credencialcontrasenia"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CREDENCIALUSUARIO")
    private String credencialusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CREDENCIALCONTRASENIA")
    private String credencialcontrasenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Pedido> pedidoCollection;

    public Usuario() {
    }

    public Usuario(String credencialusuario) {
        this.credencialusuario = credencialusuario;
    }

    public Usuario(String credencialusuario, String credencialcontrasenia, String nombre) {
        this.credencialusuario = credencialusuario;
        this.credencialcontrasenia = credencialcontrasenia;
        this.nombre = nombre;
    }

    public String getCredencialusuario() {
        return credencialusuario;
    }

    public void setCredencialusuario(String credencialusuario) {
        this.credencialusuario = credencialusuario;
    }

    public String getCredencialcontrasenia() {
        return credencialcontrasenia;
    }

    public void setCredencialcontrasenia(String credencialcontrasenia) {
        this.credencialcontrasenia = credencialcontrasenia;
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

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credencialusuario != null ? credencialusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.credencialusuario == null && other.credencialusuario != null) || (this.credencialusuario != null && !this.credencialusuario.equals(other.credencialusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Usuario[ credencialusuario=" + credencialusuario + " ]";
    }
    
}
