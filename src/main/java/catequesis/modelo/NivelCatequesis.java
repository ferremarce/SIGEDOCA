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
@Table(name = "nivel_catequesis")
@NamedQueries({
    @NamedQuery(name = "NivelCatequesis.findAll", query = "SELECT n FROM NivelCatequesis n")})
public class NivelCatequesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel")
    private Integer idNivel;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion_nivel")
    private String descripcionNivel;
    @Column(name = "edad")
    private Integer edad;
    @JoinColumn(name = "id_etapa", referencedColumnName = "id_sub_tipo")
    @ManyToOne
    private SubTipo idEtapa;
    @OneToMany(mappedBy = "idNivel")
    private List<FormacionCristiana> formacionCristianaList;

    public NivelCatequesis() {
    }

    public NivelCatequesis(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public String getDescripcionNivel() {
        return descripcionNivel;
    }

    public void setDescripcionNivel(String descripcionNivel) {
        this.descripcionNivel = descripcionNivel;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public SubTipo getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(SubTipo idEtapa) {
        this.idEtapa = idEtapa;
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
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelCatequesis)) {
            return false;
        }
        NivelCatequesis other = (NivelCatequesis) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "catequesis.modelo.NivelCatequesis[ idNivel=" + idNivel + " ]";
    }
    
}
