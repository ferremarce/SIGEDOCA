/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.modelo;

import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "diocesis")
@NamedQueries({
    @NamedQuery(name = "Diocesis.findAll", query = "SELECT d FROM Diocesis d")})
public class Diocesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diocesis")
    private Integer idDiocesis;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 255)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "correo")
    private String correo;
    @Size(max = 3000)
    @Column(name = "datos_adicionales")
    private String datosAdicionales;
    @OneToMany(mappedBy = "idDiocesis")
    private List<Parroquia> parroquiaList;

    public Diocesis() {
    }

    public Diocesis(Integer idDiocesis) {
        this.idDiocesis = idDiocesis;
    }

    public Integer getIdDiocesis() {
        return idDiocesis;
    }

    public void setIdDiocesis(Integer idDiocesis) {
        this.idDiocesis = idDiocesis;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public List<Parroquia> getParroquiaList() {
        return parroquiaList;
    }

    public void setParroquiaList(List<Parroquia> parroquiaList) {
        this.parroquiaList = parroquiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiocesis != null ? idDiocesis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diocesis)) {
            return false;
        }
        Diocesis other = (Diocesis) object;
        if ((this.idDiocesis == null && other.idDiocesis != null) || (this.idDiocesis != null && !this.idDiocesis.equals(other.idDiocesis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "catequesis.modelo.Diocesis[ idDiocesis=" + idDiocesis + " ]";
    }
    
}
