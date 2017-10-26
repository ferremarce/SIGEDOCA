/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.modelo;

import java.io.Serializable;
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
    @Column(name = "id_capilla")
    private Integer idCapilla;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion_capilla")
    private String descripcionCapilla;
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    @ManyToOne
    private Zona idZona;

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

    public String getDescripcionCapilla() {
        return descripcionCapilla;
    }

    public void setDescripcionCapilla(String descripcionCapilla) {
        this.descripcionCapilla = descripcionCapilla;
    }

    public Zona getIdZona() {
        return idZona;
    }

    public void setIdZona(Zona idZona) {
        this.idZona = idZona;
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
        return "catequesis.modelo.Capilla[ idCapilla=" + idCapilla + " ]";
    }

}
