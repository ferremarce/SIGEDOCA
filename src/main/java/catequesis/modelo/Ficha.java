/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "ficha")
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f")})
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ficha")
    private Integer idFicha;
    @Column(name = "anho_incorporacion")
    private Integer anhoIncorporacion;
    @Size(max = 255)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 255)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 255)
    @Column(name = "padre")
    private String padre;
    @Size(max = 255)
    @Column(name = "madre")
    private String madre;
    @Size(max = 255)
    @Column(name = "ci")
    private String ci;
    @Size(max = 255)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "parroquia_bautismo")
    private String parroquiaBautismo;
    @Size(max = 255)
    @Column(name = "diocesis_bautismo")
    private String diocesisBautismo;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fecha_bautismo")
    @Temporal(TemporalType.DATE)
    private Date fechaBautismo;
    @Size(max = 255)
    @Column(name = "libro_bautismo")
    private String libroBautismo;
    @Size(max = 255)
    @Column(name = "folio_bautismo")
    private String folioBautismo;
    @Size(max = 255)
    @Column(name = "padrino_bautismo")
    private String padrinoBautismo;
    @Size(max = 255)
    @Column(name = "madrina_bautismo")
    private String madrinaBautismo;
    @Size(max = 255)
    @Column(name = "observaciones_bautismo")
    private String observacionesBautismo;
    @OneToMany(mappedBy = "idFicha")
    private List<FormacionCristiana> formacionCristianaList;

    public Ficha() {
    }

    public Ficha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getAnhoIncorporacion() {
        return anhoIncorporacion;
    }

    public void setAnhoIncorporacion(Integer anhoIncorporacion) {
        this.anhoIncorporacion = anhoIncorporacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getParroquiaBautismo() {
        return parroquiaBautismo;
    }

    public void setParroquiaBautismo(String parroquiaBautismo) {
        this.parroquiaBautismo = parroquiaBautismo;
    }

    public String getDiocesisBautismo() {
        return diocesisBautismo;
    }

    public void setDiocesisBautismo(String diocesisBautismo) {
        this.diocesisBautismo = diocesisBautismo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaBautismo() {
        return fechaBautismo;
    }

    public void setFechaBautismo(Date fechaBautismo) {
        this.fechaBautismo = fechaBautismo;
    }

    public String getLibroBautismo() {
        return libroBautismo;
    }

    public void setLibroBautismo(String libroBautismo) {
        this.libroBautismo = libroBautismo;
    }

    public String getFolioBautismo() {
        return folioBautismo;
    }

    public void setFolioBautismo(String folioBautismo) {
        this.folioBautismo = folioBautismo;
    }

    public String getPadrinoBautismo() {
        return padrinoBautismo;
    }

    public void setPadrinoBautismo(String padrinoBautismo) {
        this.padrinoBautismo = padrinoBautismo;
    }

    public String getMadrinaBautismo() {
        return madrinaBautismo;
    }

    public void setMadrinaBautismo(String madrinaBautismo) {
        this.madrinaBautismo = madrinaBautismo;
    }

    public String getObservacionesBautismo() {
        return observacionesBautismo;
    }

    public void setObservacionesBautismo(String observacionesBautismo) {
        this.observacionesBautismo = observacionesBautismo;
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
        hash += (idFicha != null ? idFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.idFicha == null && other.idFicha != null) || (this.idFicha != null && !this.idFicha.equals(other.idFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombres + " " + this.apellidos;
    }

    public FormacionCristiana ultimaFormacionCristiana() {
        FormacionCristiana ultima = null;
        for (FormacionCristiana fc : formacionCristianaList) {
            if (ultima == null) {
                ultima = fc;
            } else if (ultima.getAnho() == null) {
                ultima = fc;
            } else if (ultima.getAnho() != null && fc.getAnho() != null && ultima.getAnho().compareTo(fc.getAnho()) <0) {
                ultima = fc;
            }
        }
        return ultima;
    }
}
