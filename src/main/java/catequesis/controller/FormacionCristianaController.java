/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.FormacionCristianaFacade;
import catequesis.modelo.FormacionCristiana;
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
@Named(value = "FormacionCristianaController")
@SessionScoped
public class FormacionCristianaController implements Serializable {

    private static final Logger LOG = Logger.getLogger(FormacionCristianaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    FormacionCristianaFacade formacionCristianaFacade;

    private FormacionCristiana formacionCristiana;
    private List<FormacionCristiana> listaFormacionCristiana;

    /**
     * Creates a new instance of FormacionCristianaController
     */
    public FormacionCristianaController() {
    }

    public FormacionCristiana getFormacionCristiana() {
        return formacionCristiana;
    }

    public void setFormacionCristiana(FormacionCristiana formacionCristiana) {
        this.formacionCristiana = formacionCristiana;
    }

    public List<FormacionCristiana> getListaFormacionCristiana() {
        return listaFormacionCristiana;
    }

    public void setListaFormacionCristiana(List<FormacionCristiana> listaFormacionCristiana) {
        this.listaFormacionCristiana = listaFormacionCristiana;
    }

    public FormacionCristianaFacade getFormacionCristianaFacade() {
        return formacionCristianaFacade;
    }

    public void setFormacionCristianaFacade(FormacionCristianaFacade formacionCristianaFacade) {
        this.formacionCristianaFacade = formacionCristianaFacade;
    }

    public String doListaForm() {
        this.listaFormacionCristiana = new ArrayList<>();
        return "/pages/ListarFormacionCristiana";
    }

    public String doCrearForm() {
        this.formacionCristiana = new FormacionCristiana();
        return "/pages/CrearFormacionCristiana";
    }

    public String doEditarForm(Integer id) {
        this.formacionCristiana = formacionCristianaFacade.find(id);
        return "/pages/CrearFormacionCristiana";
    }

    public String doRefrescar() {
        this.listaFormacionCristiana = formacionCristianaFacade.findAll();
        if (this.listaFormacionCristiana.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaFormacionCristiana.size() + " registros recuperados");
        }
        return "";
    }

    public String doGuardar() {
        if (this.formacionCristiana.getIdFormacion() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        return doListaForm();
    }

    public void doGuardar(FormacionCristiana fc) {
        this.formacionCristiana = fc;
        this.doGuardar();
    }

    public String doBorrar(Integer id) {
        this.formacionCristiana = formacionCristianaFacade.find(id);
        persist(PersistAction.DELETE);
        return doListaForm();
    }

    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                formacionCristianaFacade.create(formacionCristiana);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                formacionCristianaFacade.edit(formacionCristiana);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                formacionCristianaFacade.remove(formacionCristiana);
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

    public SelectItem[] getFormacionCristianaSet() {
        return JSFutil.getSelectItems(formacionCristianaFacade.findAll(), Boolean.TRUE);
    }

}
