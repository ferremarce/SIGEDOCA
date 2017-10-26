/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.ParroquiaFacade;
import catequesis.modelo.Parroquia;
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
@Named(value = "ParroquiaController")
@SessionScoped
public class ParroquiaController implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(ParroquiaController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());
    
    @Inject
    ParroquiaFacade parroquiaFacade;
    
    private Parroquia parroquia;
    private List<Parroquia> listaParroquia;

    /**
     * Creates a new instance of ParroquiaController
     */
    public ParroquiaController() {
    }
    
    public Parroquia getParroquia() {
        return parroquia;
    }
    
    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }
    
    public List<Parroquia> getListaParroquia() {
        return listaParroquia;
    }
    
    public void setListaParroquia(List<Parroquia> listaParroquia) {
        this.listaParroquia = listaParroquia;
    }
    
    public String doListaForm() {
        this.listaParroquia = new ArrayList<>();
        return "/pages/ListarParroquia";
    }
    
    public String doCrearForm() {
        this.parroquia = new Parroquia();
        return "/pages/CrearParroquia";
    }
    
    public String doEditarForm(Integer id) {
        this.parroquia = parroquiaFacade.find(id);
        return "/pages/CrearParroquia";
    }
    
    public String doRefrescar() {
        this.listaParroquia = parroquiaFacade.findAll();
        if (this.listaParroquia.isEmpty()) {
            JSFutil.addErrorMessage("No hay resultados...");
        } else {
            JSFutil.addSuccessMessage(this.listaParroquia.size() + " registros recuperados");
        }
        return "";
    }
    
    public String doGuardar() {
        if (this.parroquia.getIdParroquia() != null) {
            persist(PersistAction.UPDATE);
        } else {
            persist(PersistAction.CREATE);
        }
        return doListaForm();
    }
    
    public String doBorrar(Integer id) {
        this.parroquia = parroquiaFacade.find(id);
        persist(PersistAction.DELETE);
        return doListaForm();
    }
    
    private void persist(PersistAction persistAction) {
        try {
            if (persistAction.compareTo(PersistAction.CREATE) == 0) {
                parroquiaFacade.create(parroquia);
            } else if (persistAction.compareTo(PersistAction.UPDATE) == 0) {
                parroquiaFacade.edit(parroquia);
            } else if (persistAction.compareTo(PersistAction.DELETE) == 0) {
                parroquiaFacade.remove(parroquia);
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
