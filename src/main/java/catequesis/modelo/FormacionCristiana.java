/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "formacion_cristiana")
@NamedQueries({
    @NamedQuery(name = "FormacionCristiana.findAll", query = "SELECT f FROM FormacionCristiana f")})
public class FormacionCristiana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacion")
    private Integer idFormacion;
    @Column(name = "anho")
    private Integer anho;
    @Lob
    @Size(max = 65535)
    @Column(name = "informacion_parroquia")
    private String informacionParroquia;
    @Lob
    @Size(max = 65535)
    @Column(name = "responsable")
    private String responsable;
    @Lob
    @Size(max = 65535)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    @ManyToOne
    private NivelCatequesis idNivel;
    @JoinColumn(name = "id_capilla", referencedColumnName = "id_capilla")
    @ManyToOne
    private Capilla idCapilla;
    @JoinColumn(name = "id_ficha", referencedColumnName = "id_ficha")
    @ManyToOne
    private Ficha idFicha;

    public FormacionCristiana() {
    }

    public FormacionCristiana(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Integer getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getInformacionParroquia() {
        return informacionParroquia;
    }

    public void setInformacionParroquia(String informacionParroquia) {
        this.informacionParroquia = informacionParroquia;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public NivelCatequesis getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(NivelCatequesis idNivel) {
        this.idNivel = idNivel;
    }

    public Capilla getIdCapilla() {
        return idCapilla;
    }

    public void setIdCapilla(Capilla idCapilla) {
        this.idCapilla = idCapilla;
    }

    public Ficha getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Ficha idFicha) {
        this.idFicha = idFicha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacion != null ? idFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormacionCristiana)) {
            return false;
        }
        FormacionCristiana other = (FormacionCristiana) object;
        if ((this.idFormacion == null && other.idFormacion != null) || (this.idFormacion != null && !this.idFormacion.equals(other.idFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "catequesis.modelo.FormacionCristiana[ idFormacion=" + idFormacion + " ]";
    }
    
}
