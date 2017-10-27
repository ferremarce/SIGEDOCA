/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.DiocesisFacade;
import catequesis.modelo.Diocesis;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import util.JSFutil;
import util.JSFutil.PersistAction;

/**
 *
 * @author jmferreira
 */
@Named(value = "DiocesisController")
@SessionScoped
public class DiocesisController implements Serializable {

    private static final Logger LOG = Logger.getLogger(DiocesisController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    DiocesisFacade diocesisFacade;

    private Diocesis diocesis;
    private List<Diocesis> listaDiocesis;

    /**
     * Creates a new instance of DiocesisController
     */
    public DiocesisController() {
    }

    public Diocesis getDiocesis() {
        return diocesis;
    }

    public void setDiocesis(Diocesis diocesis) {
        this.diocesis = diocesis;
    }

    public List<Diocesis> getListaDiocesis() {
        return listaDiocesis;
    }

    public void setListaDiocesis(List<Diocesis> listaDiocesis) {
        this.listaDiocesis = listaDiocesis;
    }

    public DiocesisFacade getDiocesisFacade() {
        return diocesisFacade;
    }

    public void setDiocesisFacade(DiocesisFacade diocesisFacade) {
        this.diocesisFacade = diocesisFacade;
    }

    public String doListaForm() {
        this.listaDiocesis = new ArrayList<>();
        return "/pages/ListarDiocesis";
    }

    public String doCrearForm() {
        this.diocesis = new Diocesis();
        return "/pages/CrearDiocesis";
    }

    public String doEditarForm(Integer id) {
        this.diocesis = diocesisFacade.find(id);
        return "/pages/CrearDiocesis";
    }

    public String doRefrescar() {
        this.listaDiocesis = diocesisFacade.findAll();
        if (this.listaDiocesis.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaDiocesis.size() + " registros recuperados");
        }
        return "";
    }

    public String doGuardar() {
        if (this.diocesis.getIdDiocesis() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        return doListaForm();
    }

    public String doBorrar(Integer id) {
        this.diocesis = diocesisFacade.find(id);
        persist(PersistAction.DELETE);
        return doListaForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                diocesisFacade.create(diocesis);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                diocesisFacade.edit(diocesis);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                diocesisFacade.remove(diocesis);
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

    public SelectItem[] getDiocesisSet() {
        return JSFutil.getSelectItems(diocesisFacade.findAll(), Boolean.TRUE);
    }

}
