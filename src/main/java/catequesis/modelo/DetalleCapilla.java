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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.Index;

/**
 *
 * @author jmferreira
 */
@Entity
@Table(name = "detalle_capilla")
@Index(name = "IDX_MYIDX1", columnNames = { "idCapilla", "anho"})
@NamedQueries({
    @NamedQuery(name = "DetalleCapilla.findAll", query = "SELECT d FROM DetalleCapilla d")})
public class DetalleCapilla implements Serializable {

    @OneToMany(mappedBy = "idDetalleCapilla")
    private List<Ficha> fichaList;

    @Column(name = "fecha_sacramento_confesion")
    @Temporal(TemporalType.DATE)
    private Date fechaSacramentoConfesion;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_capilla")
    private Integer idDetalleCapilla;
    @Column(name = "anho")
    private Integer anho;
    @Size(max = 255)
    @Column(name = "delegado")
    private String delegado;
    @Size(max = 255)
    @Column(name = "info_fecha_retiro")
    private String infoFechaRetiro;
    @Size(max = 255)
    @Column(name = "celebrante_confirmacion")
    private String celebranteConfirmacion;
    @Column(name = "fecha_inicio_retiro")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioRetiro;
    @Column(name = "fecha_fin_retiro")
    @Temporal(TemporalType.DATE)
    private Date fechaFinRetiro;
    @Column(name = "fecha_sacramento_confirmacion")
    @Temporal(TemporalType.DATE)
    private Date fechaSacramentoConfirmacion;
    @Column(name = "fecha_sacramento_bautismo")
    @Temporal(TemporalType.DATE)
    private Date fechaSacramentoBautismo;
    @Column(name = "fecha_sacramento_comunion")
    @Temporal(TemporalType.DATE)
    private Date fechaSacramentoComunion;
    @JoinColumn(name = "id_capilla", referencedColumnName = "id_capilla")
    @ManyToOne
    private Capilla idCapilla;

    public DetalleCapilla() {
    }

    public DetalleCapilla(Integer idDetalleCapilla) {
        this.idDetalleCapilla = idDetalleCapilla;
    }

    public Integer getIdDetalleCapilla() {
        return idDetalleCapilla;
    }

    public void setIdDetalleCapilla(Integer idDetalleCapilla) {
        this.idDetalleCapilla = idDetalleCapilla;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getDelegado() {
        return delegado;
    }

    public void setDelegado(String delegado) {
        this.delegado = delegado;
    }

    public String getInfoFechaRetiro() {
        return infoFechaRetiro;
    }

    public void setInfoFechaRetiro(String infoFechaRetiro) {
        this.infoFechaRetiro = infoFechaRetiro;
    }

    public String getCelebranteConfirmacion() {
        return celebranteConfirmacion;
    }

    public void setCelebranteConfirmacion(String celebranteConfirmacion) {
        this.celebranteConfirmacion = celebranteConfirmacion;
    }

    public Date getFechaInicioRetiro() {
        return fechaInicioRetiro;
    }

    public void setFechaInicioRetiro(Date fechaInicioRetiro) {
        this.fechaInicioRetiro = fechaInicioRetiro;
    }

    public Date getFechaFinRetiro() {
        return fechaFinRetiro;
    }

    public void setFechaFinRetiro(Date fechaFinRetiro) {
        this.fechaFinRetiro = fechaFinRetiro;
    }

    public Date getFechaSacramentoConfirmacion() {
        return fechaSacramentoConfirmacion;
    }

    public void setFechaSacramentoConfirmacion(Date fechaSacramentoConfirmacion) {
        this.fechaSacramentoConfirmacion = fechaSacramentoConfirmacion;
    }

    public Date getFechaSacramentoBautismo() {
        return fechaSacramentoBautismo;
    }

    public void setFechaSacramentoBautismo(Date fechaSacramentoBautismo) {
        this.fechaSacramentoBautismo = fechaSacramentoBautismo;
    }

    public Date getFechaSacramentoComunion() {
        return fechaSacramentoComunion;
    }

    public void setFechaSacramentoComunion(Date fechaSacramentoComunion) {
        this.fechaSacramentoComunion = fechaSacramentoComunion;
    }

    public Capilla getIdCapilla() {
        return idCapilla;
    }

    public void setIdCapilla(Capilla idCapilla) {
        this.idCapilla = idCapilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCapilla != null ? idDetalleCapilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCapilla)) {
            return false;
        }
        DetalleCapilla other = (DetalleCapilla) object;
        if ((this.idDetalleCapilla == null && other.idDetalleCapilla != null) || (this.idDetalleCapilla != null && !this.idDetalleCapilla.equals(other.idDetalleCapilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.anho+" "+this.fechaSacramentoConfirmacion;
    }

    public Date getFechaSacramentoConfesion() {
        return fechaSacramentoConfesion;
    }

    public void setFechaSacramentoConfesion(Date fechaSacramentoConfesion) {
        this.fechaSacramentoConfesion = fechaSacramentoConfesion;
    }

    public List<Ficha> getFichaList() {
        return fichaList;
    }

    public void setFichaList(List<Ficha> fichaList) {
        this.fichaList = fichaList;
    }

}
