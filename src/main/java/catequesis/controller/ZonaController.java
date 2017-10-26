/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.ZonaFacade;
import catequesis.modelo.Zona;
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
@Named(value = "ZonaController")
@SessionScoped
public class ZonaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CapillaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    ZonaFacade zonaFacade;

    private Zona zona;
    private List<Zona> listaZona;

    /**
     * Creates a new instance of ZonaController
     */
    public ZonaController() {
    }

    public ZonaFacade getZonaFacade() {
        return zonaFacade;
    }

    public void setZonaFacade(ZonaFacade zonaFacade) {
        this.zonaFacade = zonaFacade;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Zona> getListaZona() {
        return listaZona;
    }

    public void setListaZona(List<Zona> listaZona) {
        this.listaZona = listaZona;
    }

    public SelectItem[] getZonaSet() {
        return this.getZonaItemsAvailableSelectOne(false);
    }

    private SelectItem[] getZonaItemsAvailableSelectOne(boolean habilitado) {
        return JSFutil.getSelectItems(zonaFacade.findAll(), habilitado);
    }

    public String doListaForm() {
        this.listaZona = new ArrayList<>();
        return "/pages/ListarZona";
    }

    public String doCrearForm() {
        this.zona = new Zona();
        return "/pages/CrearZona";
    }

    public String doEditarForm(Integer id) {
        this.zona = zonaFacade.find(id);
        return "/pages/CrearZona";
    }

    public String doRefrescar() {
        this.listaZona = zonaFacade.findAll();
        if (this.listaZona.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaZona.size() + " registros recuperados");
        }
        return "";
    }

    public String doGuardar() {
        if (this.zona.getIdZona() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        return doListaForm();
    }

    public String doBorrar(Integer id) {
        this.zona = zonaFacade.find(id);
        persist(PersistAction.DELETE);
        return doListaForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                zonaFacade.create(zona);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                zonaFacade.edit(zona);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                zonaFacade.remove(zona);
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

}
