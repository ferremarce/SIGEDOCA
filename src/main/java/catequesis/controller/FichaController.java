/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.FichaFacade;
import catequesis.fachada.FormacionCristianaFacade;
import catequesis.modelo.Ficha;
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

    private Ficha ficha;
    private List<Ficha> listaFicha;
    private List<FormacionCristiana> listaFormacionCristiana;

    /**
     * Creates a new instance of FichaController
     */
    public FichaController() {
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
        this.listaFicha = new ArrayList<>();
        return "/pages/ListarFicha";
    }

    public String doCrearForm() {
        System.out.println("------"+JSFutil.getmyLocale());

        this.ficha = new Ficha();
        return "/pages/CrearFicha";
    }

    public String doEditarForm(Integer id) {
        this.ficha = fichaFacade.find(id);
        if (this.ficha.getFormacionCristianaList().isEmpty()) {
            formacionCristianaFacade.createFichaFormacion(this.ficha.getIdFicha());
        }
        this.ficha = fichaFacade.find(id);
        this.listaFormacionCristiana = fichaFacade.findAllFormacionCristiana(id);
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
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                fichaFacade.edit(ficha);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                fichaFacade.remove(ficha);
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
