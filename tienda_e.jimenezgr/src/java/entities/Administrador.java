/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

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
@Table(name = "ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByCredencialusuario", query = "SELECT a FROM Administrador a WHERE a.credencialusuario = :credencialusuario"),
    @NamedQuery(name = "Administrador.findByCredencialcontrasenia", query = "SELECT a FROM Administrador a WHERE a.credencialcontrasenia = :credencialcontrasenia"),
    @NamedQuery(name = "Administrador.findByNombre", query = "SELECT a FROM Administrador a WHERE a.nombre = :nombre")})
public class Administrador implements Serializable {
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

    public Administrador() {
    }

    public Administrador(String credencialusuario) {
        this.credencialusuario = credencialusuario;
    }

    public Administrador(String credencialusuario, String credencialcontrasenia, String nombre) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credencialusuario != null ? credencialusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.credencialusuario == null && other.credencialusuario != null) || (this.credencialusuario != null && !this.credencialusuario.equals(other.credencialusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Administrador[ credencialusuario=" + credencialusuario + " ]";
    }
    
}
