/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ito.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pipo0
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mercancias.findAll", query = "SELECT m FROM Mercancias m")
    , @NamedQuery(name = "Mercancias.findById", query = "SELECT m FROM Mercancias m WHERE m.id = :id")
    , @NamedQuery(name = "Mercancias.findByNombreProducto", query = "SELECT m FROM Mercancias m WHERE m.nombreProducto = :nombreProducto")
    , @NamedQuery(name = "Mercancias.findByCiudadDestino", query = "SELECT m FROM Mercancias m WHERE m.ciudadDestino = :ciudadDestino")
    , @NamedQuery(name = "Mercancias.findByDireccion", query = "SELECT m FROM Mercancias m WHERE m.direccion = :direccion")
    , @NamedQuery(name = "Mercancias.findByFechaSalida", query = "SELECT m FROM Mercancias m WHERE m.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Mercancias.findByPrecio", query = "SELECT m FROM Mercancias m WHERE m.precio = :precio")
    , @NamedQuery(name = "Mercancias.findByEstadoEnvio", query = "SELECT m FROM Mercancias m WHERE m.estadoEnvio = :estadoEnvio")})
public class Mercancias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 20)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Size(max = 20)
    @Column(name = "ciudad_destino")
    private String ciudadDestino;
    @Size(max = 20)
    private String direccion;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.TIME)
    private Date fechaSalida;
    private Integer precio;
    @Size(max = 10)
    @Column(name = "estado_envio")
    private String estadoEnvio;
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personas destinatarioId;
    @JoinColumn(name = "usuario_registro_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuarios usuarioRegistroId;

    public Mercancias() {
    }

    public Mercancias(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Personas getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Personas destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Usuarios getUsuarioRegistroId() {
        return usuarioRegistroId;
    }

    public void setUsuarioRegistroId(Usuarios usuarioRegistroId) {
        this.usuarioRegistroId = usuarioRegistroId;
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
        if (!(object instanceof Mercancias)) {
            return false;
        }
        Mercancias other = (Mercancias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ito.entity.Mercancias[ id=" + id + " ]";
    }
    
}
