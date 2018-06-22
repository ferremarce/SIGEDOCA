/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.CapillaFacade;
import catequesis.fachada.DetalleCapillaFacade;
import catequesis.modelo.Capilla;
import catequesis.modelo.DetalleCapilla;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "DetalleCapillaController")
@SessionScoped
public class DetalleCapillaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(DetalleCapillaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    DetalleCapillaFacade detalleCapillaFacade;
    @Inject
    CapillaFacade capillaFacade;

    private DetalleCapilla detalleCapilla;
    private List<DetalleCapilla> listaDatelleCapilla;
    private List<DetalleCapilla> selectedDatelleCapilla;
    private Integer anho;
    private Date fechaInicioRetiro;
    private Date fechaFinRetiro;
    private Date fechaSacramentoConfirmacion;
    private Date fechaSacramentoBautismo;
    private Date fechaSacramentoComunion;
    private Date fechaSacramentoConfesion;
    private String celebranteConfirmacion;

    /**
     * Creates a new instance of DetalleCapillaController
     */
    public DetalleCapillaController() {
    }

    public List<DetalleCapilla> getSelectedDatelleCapilla() {
        return selectedDatelleCapilla;
    }

    public void setSelectedDatelleCapilla(List<DetalleCapilla> selectedDatelleCapilla) {
        this.selectedDatelleCapilla = selectedDatelleCapilla;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
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

    public Date getFechaSacramentoConfesion() {
        return fechaSacramentoConfesion;
    }

    public void setFechaSacramentoConfesion(Date fechaSacramentoConfesion) {
        this.fechaSacramentoConfesion = fechaSacramentoConfesion;
    }

    public String getCelebranteConfirmacion() {
        return celebranteConfirmacion;
    }

    public void setCelebranteConfirmacion(String celebranteConfirmacion) {
        this.celebranteConfirmacion = celebranteConfirmacion;
    }

    public List<DetalleCapilla> getListaDatelleCapilla() {
        return listaDatelleCapilla;
    }

    public void setListaDatelleCapilla(List<DetalleCapilla> listaDatelleCapilla) {
        this.listaDatelleCapilla = listaDatelleCapilla;
    }

    public String doCrearForm() {
        this.listaDatelleCapilla = null;
        this.selectedDatelleCapilla = null;
        this.anho = Year.now().getValue();
        this.limpiarCampos();
        return "/pages/CargarDetalleCapilla";
    }

    private void limpiarCampos() {
        this.fechaFinRetiro = null;
        this.fechaInicioRetiro = null;
        this.fechaSacramentoBautismo = null;
        this.fechaSacramentoComunion = null;
        this.fechaSacramentoConfesion = null;
        this.fechaSacramentoConfirmacion = null;
        this.celebranteConfirmacion = "";
    }

    public void doProcesarAnho() {
        List<Capilla> listaCapilla = capillaFacade.getAllCapilla("");
        List<DetalleCapilla> listaDetalleCapillaAnho = detalleCapillaFacade.getDetalleCapilla(anho);
        this.listaDatelleCapilla = new ArrayList<>();
        Boolean existe;
        for (Capilla c : listaCapilla) {
            existe = Boolean.FALSE;
            for (DetalleCapilla dc : listaDetalleCapillaAnho) {
                if (c.getIdCapilla().compareTo(dc.getIdCapilla().getIdCapilla()) == 0) {
                    this.listaDatelleCapilla.add(dc);
                    existe = Boolean.TRUE;
                    break;
                }
            }
            if (!existe) {
                DetalleCapilla det = new DetalleCapilla();
                det.setIdCapilla(c);
                det.setAnho(anho);
                this.listaDatelleCapilla.add(det);
            }
        }
        JSFutil.addSuccessMessage("Se han recuperado "+this.listaDatelleCapilla.size()+" Capillas");
    }

    public void doUpdate() {

        if (this.fechaSacramentoComunion == null && this.fechaInicioRetiro == null && this.fechaFinRetiro == null && this.fechaSacramentoConfirmacion == null && this.celebranteConfirmacion.isEmpty()) {
            JSFutil.addErrorMessage("Es necesario al menos uno de los datos de fecha para continuar con el proceso");
            return;
        }
        if (this.selectedDatelleCapilla.isEmpty()) {
            JSFutil.addErrorMessage("Es necesario seleccionar al menos una Capilla de la lista para continuar con el proceso");
            return;

        }
        for (DetalleCapilla dc : selectedDatelleCapilla) {
            if (this.fechaSacramentoComunion != null) {
                dc.setFechaSacramentoComunion(fechaSacramentoComunion);
            }
            if (this.fechaInicioRetiro != null) {
                dc.setFechaInicioRetiro(fechaInicioRetiro);
            }
            if (this.fechaFinRetiro != null) {
                dc.setFechaFinRetiro(fechaFinRetiro);
            }
            if (this.fechaSacramentoConfirmacion != null) {
                dc.setFechaSacramentoConfirmacion(fechaSacramentoConfirmacion);
            }
            if (!this.celebranteConfirmacion.isEmpty()) {
                dc.setCelebranteConfirmacion(celebranteConfirmacion);
            }
            try {
                if (dc.getIdDetalleCapilla() == null) {
                    detalleCapillaFacade.create(dc);
                } else {
                    detalleCapillaFacade.edit(dc);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JSFutil.addErrorMessage(msg);
                } else {
                    JSFutil.addErrorMessage(ex, this.bundle.getString("UpdateError"));
                }
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        JSFutil.addSuccessMessage(this.bundle.getString("UpdateSuccess")+". Se han actualizado: "+this.selectedDatelleCapilla.size());
        this.selectedDatelleCapilla = new ArrayList<>();
        this.doProcesarAnho();
        this.limpiarCampos();
    }
}
