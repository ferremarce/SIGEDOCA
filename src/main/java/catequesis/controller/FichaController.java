/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.FichaFacade;
import catequesis.fachada.FormacionCristianaFacade;
import catequesis.fachada.NivelCatequesisFacade;
import catequesis.modelo.Capilla;
import catequesis.modelo.DetalleCapilla;
import catequesis.modelo.Ficha;
import catequesis.modelo.FormacionCristiana;
import catequesis.modelo.NivelCatequesis;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Inject;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "FichaController")
@SessionScoped
public class FichaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(FichaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    FichaFacade fichaFacade;
    @Inject
    FormacionCristianaFacade formacionCristianaFacade;
    @Inject
    NivelCatequesisFacade nivelCatequesisFacade;

    private Ficha ficha;
    private List<Ficha> listaFicha;
    private List<FormacionCristiana> listaFormacionCristiana;
    private String criterio;
    private Integer anhoProceso;
    private Capilla capillaProceso;
    private NivelCatequesis nivelCatequesisProceso;
    private List<Ficha> selectedFichaProceso;
    private Boolean hizoConfirmacion;

    /**
     * Creates a new instance of FichaController
     */
    public FichaController() {
    }

    public Boolean getHizoConfirmacion() {
        return hizoConfirmacion;
    }

    public void setHizoConfirmacion(Boolean hizoConfirmacion) {
        this.hizoConfirmacion = hizoConfirmacion;
    }

    public List<Ficha> getSelectedFichaProceso() {
        return selectedFichaProceso;
    }

    public void setSelectedFichaProceso(List<Ficha> selectedFichaProceso) {
        this.selectedFichaProceso = selectedFichaProceso;
    }

    public Integer getAnhoProceso() {
        return anhoProceso;
    }

    public void setAnhoProceso(Integer anhoProceso) {
        this.anhoProceso = anhoProceso;
    }

    public Capilla getCapillaProceso() {
        return capillaProceso;
    }

    public void setCapillaProceso(Capilla capillaProceso) {
        this.capillaProceso = capillaProceso;
    }

    public NivelCatequesis getNivelCatequesisProceso() {
        return nivelCatequesisProceso;
    }

    public void setNivelCatequesisProceso(NivelCatequesis nivelCatequesisProceso) {
        this.nivelCatequesisProceso = nivelCatequesisProceso;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<FormacionCristiana> getListaFormacionCristiana() {
        return listaFormacionCristiana;
    }

    public void setListaFormacionCristiana(List<FormacionCristiana> listaFormacionCristiana) {
        this.listaFormacionCristiana = listaFormacionCristiana;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public List<Ficha> getListaFicha() {
        return listaFicha;
    }

    public void setListaFicha(List<Ficha> listaFicha) {
        this.listaFicha = listaFicha;
    }

    public String doListaForm() {
        if (this.listaFicha == null) {
            this.listaFicha = new ArrayList<>();
        }
        return "/pages/ListarFicha";
    }

    public String doProcesarForm() {
        this.listaFicha = new ArrayList<>();
        this.anhoProceso = Year.now().getValue();
        this.capillaProceso = null;
        this.nivelCatequesisProceso = null;
        return "/pages/ProcesarFichas";
    }

    public String doCrearForm() {
        System.out.println("------" + JSFutil.getmyLocale());
        this.ficha = new Ficha();
        this.listaFormacionCristiana = formacionCristianaFacade.listaFormacionTemplate();
        this.hizoConfirmacion = Boolean.FALSE;
        return "/pages/CrearFicha";
    }

    public String doEditarForm(Integer id) {
        this.ficha = fichaFacade.find(id);
        if (this.ficha.getFormacionCristianaList().isEmpty()) {
            formacionCristianaFacade.createFichaFormacion(this.ficha.getIdFicha());
        }
        this.listaFormacionCristiana = fichaFacade.findAllFormacionCristiana(id);
        this.hizoConfirmacion = Boolean.FALSE;
        return "/pages/CrearFicha";
    }

    public String doRefrescar() {
        this.listaFicha = fichaFacade.findAll();
        if (this.listaFicha.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaFicha.size() + " registros recuperados");
        }
        return "";
    }

    public void doGuardar() {
        if (this.ficha.getIdFicha() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        //return doListaForm();
    }

    public String doBorrar(Integer id) {
        this.ficha = fichaFacade.find(id);
        persist(PersistAction.DELETE);
        return doListaForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                fichaFacade.create(ficha);
                this.doGuardarFormacionCristiana();
                this.doEditarForm(this.ficha.getIdFicha());

            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                fichaFacade.edit(ficha);
                this.doGuardarFormacionCristiana();
                this.doEditarForm(this.ficha.getIdFicha());
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                fichaFacade.remove(ficha);
                this.doRefrescar();
            }
            JSFutil.addSuccessMessage(this.bundle.getString("UpdateSuccess"));
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

    private void doGuardarFormacionCristiana() {
        for (FormacionCristiana fc : this.listaFormacionCristiana) {
            if (fc.getIdFormacion() == null) {
                fc.setIdFicha(ficha);
                formacionCristianaFacade.create(fc);
            } else {
                formacionCristianaFacade.edit(fc);
            }
        }
    }

    public String doBuscar() {
        if (this.criterio.isEmpty()) {
            JSFutil.addErrorMessage("No hay criterios para buscar...");
            return "";
        }
        this.listaFicha = fichaFacade.findAllFicha(criterio.replace(" ", "%"));
        if (this.listaFicha.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaFicha.size() + " registros recuperados");
        }
        return "";
    }

    public String doRecuperarFichaProceso() throws InterruptedException {
        Thread.sleep(3000);
        this.listaFicha = fichaFacade.findAllFichaProceso(anhoProceso, capillaProceso.getIdCapilla(), nivelCatequesisProceso.getIdNivel());
        if (this.listaFicha.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaFicha.size() + " registros recuperados");
        }
        this.selectedFichaProceso = null;
        return "";
    }

    public NivelCatequesis doNivelAnterior() {
        Integer id = this.nivelCatequesisProceso.getIdNivel();
        return nivelCatequesisFacade.find(id - 1);
    }

    public void doProcesarInscripcion() {
        if (this.selectedFichaProceso.isEmpty()) {
            JSFutil.addErrorMessage("Debe seleccionar al menos un elemento de la lista para procesar la inscripción.");
            return;
        }
        for (Ficha f : this.selectedFichaProceso) {
            for (FormacionCristiana fc : f.getFormacionCristianaList()) {
                if (fc.getIdNivel().getIdNivel().compareTo(nivelCatequesisProceso.getIdNivel()) == 0) {
                    fc.setAnho(anhoProceso);
                    fc.setIdCapilla(capillaProceso);
                    fc.setIdNivel(nivelCatequesisProceso);
                    fc.setIdFicha(f);
                    formacionCristianaFacade.edit(fc);
                    System.out.println("Actualizado: " + f.getNombres() + " " + f.getApellidos() + " a " + anhoProceso + " " + capillaProceso.getNombre() + " " + nivelCatequesisProceso.getDescripcionNivel());
                    break;
                }
            }
        }
        JSFutil.addSuccessMessage(this.bundle.getString("UpdateSuccess"));
    }

    public void doActivarConfirmacion(FormacionCristiana fc) {
        if (fc.getAnho()!=null) {
            for (DetalleCapilla det : fc.getIdCapilla().getDetalleCapillaList()) {
                if (det.getAnho().compareTo(fc.getAnho()) == 0) {
                    this.ficha.setIdDetalleCapilla(det);
                    break;
                }
            }
        } else {
            ficha.setIdDetalleCapilla(null);
        }
    }
}
