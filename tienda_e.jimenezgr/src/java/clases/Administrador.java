/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shkire
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByCredencialUsuario", query = "SELECT a FROM Administrador a WHERE a.credencialUsuario = :credencialUsuario"),
    @NamedQuery(name = "Administrador.findByCredencialContrasenia", query = "SELECT a FROM Administrador a WHERE a.credencialContrasenia = :credencialContrasenia"),
    @NamedQuery(name = "Administrador.findByNombre", query = "SELECT a FROM Administrador a WHERE a.nombre = :nombre")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CredencialUsuario")
    private String credencialUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CredencialContrasenia")
    private String credencialContrasenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre")
    private String nombre;

    public Administrador() {
    }

    public Administrador(String credencialUsuario) {
        this.credencialUsuario = credencialUsuario;
    }

    public Administrador(String credencialUsuario, String credencialContrasenia, String nombre) {
        this.credencialUsuario = credencialUsuario;
        this.credencialContrasenia = credencialContrasenia;
        this.nombre = nombre;
    }

    public String getCredencialUsuario() {
        return credencialUsuario;
    }

    public void setCredencialUsuario(String credencialUsuario) {
        this.credencialUsuario = credencialUsuario;
    }

    public String getCredencialContrasenia() {
        return credencialContrasenia;
    }

    public void setCredencialContrasenia(String credencialContrasenia) {
        this.credencialContrasenia = credencialContrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credencialUsuario != null ? credencialUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.credencialUsuario == null && other.credencialUsuario != null) || (this.credencialUsuario != null && !this.credencialUsuario.equals(other.credencialUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Administrador[ credencialUsuario=" + credencialUsuario + " ]";
    }
    
}
