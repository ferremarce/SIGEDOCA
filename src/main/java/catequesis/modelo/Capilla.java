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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "capilla")
@NamedQueries({
    @NamedQuery(name = "Capilla.findAll", query = "SELECT c FROM Capilla c")})
public class Capilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capilla")
    private Integer idCapilla;
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
    @JoinColumn(name = "id_parroquia", referencedColumnName = "id_parroquia")
    @ManyToOne
    private Parroquia idParroquia;
    @JoinColumn(name = "id_zona", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idZona;
    @OneToMany(mappedBy = "idCapilla")
    private List<FormacionCristiana> formacionCristianaList;

    public Capilla() {
    }

    public Capilla(Integer idCapilla) {
        this.idCapilla = idCapilla;
    }

    public Integer getIdCapilla() {
        return idCapilla;
    }

    public void setIdCapilla(Integer idCapilla) {
        this.idCapilla = idCapilla;
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

    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Parroquia idParroquia) {
        this.idParroquia = idParroquia;
    }

    public SubTipo getIdZona() {
        return idZona;
    }

    public void setIdZona(SubTipo idZona) {
        this.idZona = idZona;
    }

    public List<FormacionCristiana> getFormacionCristianaList() {
        return formacionCristianaList;
    }

    public void setFormacionCristianaList(List<FormacionCristiana> formacionCristianaList) {
        this.formacionCristianaList = formacionCristianaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapilla != null ? idCapilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capilla)) {
            return false;
        }
        Capilla other = (Capilla) object;
        if ((this.idCapilla == null && other.idCapilla != null) || (this.idCapilla != null && !this.idCapilla.equals(other.idCapilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre+" ["+this.idZona.getDescripcionSubTipo()+"]";
    }
    
}
