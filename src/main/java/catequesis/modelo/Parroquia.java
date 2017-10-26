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
import javax.persistence.Lob;
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
@Table(name = "parroquia")
@NamedQueries({
    @NamedQuery(name = "Parroquia.findAll", query = "SELECT p FROM Parroquia p")})
public class Parroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parroquia")
    private Integer idParroquia;
    @Lob
    @Size(max = 65535)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "direccion")
    private String direccion;
    @Lob
    @Size(max = 65535)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "correo")
    private String correo;
    @Lob
    @Size(max = 65535)
    @Column(name = "datos_adicionales")
    private String datosAdicionales;
    @OneToMany(mappedBy = "idParroquia")
    private List<Capilla> capillaList;
    @JoinColumn(name = "id_diocesis", referencedColumnName = "id_diocesis")
    @ManyToOne
    private Diocesis idDiocesis;

    public Parroquia() {
    }

    public Parroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public Integer getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
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

    public List<Capilla> getCapillaList() {
        return capillaList;
    }

    public void setCapillaList(List<Capilla> capillaList) {
        this.capillaList = capillaList;
    }

    public Diocesis getIdDiocesis() {
        return idDiocesis;
    }

    public void setIdDiocesis(Diocesis idDiocesis) {
        this.idDiocesis = idDiocesis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParroquia != null ? idParroquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parroquia)) {
            return false;
        }
        Parroquia other = (Parroquia) object;
        if ((this.idParroquia == null && other.idParroquia != null) || (this.idParroquia != null && !this.idParroquia.equals(other.idParroquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "catequesis.modelo.Parroquia[ idParroquia=" + idParroquia + " ]";
    }
    
}
