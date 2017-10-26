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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @Column(name = "id_ficha")
    private Integer idFicha;
    @Column(name = "anho_incorporacion")
    private Integer anhoIncorporacion;
    @Lob
    @Size(max = 65535)
    @Column(name = "apellidos")
    private String apellidos;
    @Lob
    @Size(max = 65535)
    @Column(name = "nombres")
    private String nombres;
    @Lob
    @Size(max = 65535)
    @Column(name = "padre")
    private String padre;
    @Lob
    @Size(max = 65535)
    @Column(name = "madre")
    private String madre;
    @Lob
    @Size(max = 65535)
    @Column(name = "ci")
    private String ci;
    @Lob
    @Size(max = 65535)
    @Column(name = "telefono")
    private String telefono;
    @Lob
    @Size(max = 65535)
    @Column(name = "parroquia_bautismo")
    private String parroquiaBautismo;
    @Lob
    @Size(max = 65535)
    @Column(name = "diocesis_bautismo")
    private String diocesisBautismo;
    @Lob
    @Size(max = 65535)
    @Column(name = "fecha_bautismo")
    private String fechaBautismo;
    @Column(name = "libro_bautismo")
    private Integer libroBautismo;
    @Column(name = "folio_bautismo")
    private Integer folioBautismo;
    @Lob
    @Size(max = 65535)
    @Column(name = "padrino_bautismo")
    private String padrinoBautismo;
    @Lob
    @Size(max = 65535)
    @Column(name = "madrina_bautismo")
    private String madrinaBautismo;
    @Lob
    @Size(max = 65535)
    @Column(name = "observaciones_bautismo")
    private String observacionesBautismo;

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

    public String getFechaBautismo() {
        return fechaBautismo;
    }

    public void setFechaBautismo(String fechaBautismo) {
        this.fechaBautismo = fechaBautismo;
    }

    public Integer getLibroBautismo() {
        return libroBautismo;
    }

    public void setLibroBautismo(Integer libroBautismo) {
        this.libroBautismo = libroBautismo;
    }

    public Integer getFolioBautismo() {
        return folioBautismo;
    }

    public void setFolioBautismo(Integer folioBautismo) {
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
        return "catequesis.modelo.Ficha[ idFicha=" + idFicha + " ]";
    }
    
}
