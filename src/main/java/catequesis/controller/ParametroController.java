/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.ParametroFacade;
import catequesis.modelo.Parametro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.inject.Inject;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "ParametrosController")
@SessionScoped
public class ParametroController implements Serializable {

    private static final Logger LOG = Logger.getLogger(ParametroController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    @Inject
    ParametroFacade parametrosFacade;
    private Parametro parametro;
    private List<Parametro> listaParametro;

    /**
     * Creates a new instance of ParametrosController
     */
    public ParametroController() {
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

    public List<Parametro> getListaParametro() {
        return listaParametro;
    }

    public void setListaParametro(List<Parametro> listaParametro) {
        this.listaParametro = listaParametro;
    }

    public String doListaForm() {
        this.listaParametro = parametrosFacade.findAll();
        return "/pages/EditarParametro";
    }

    
}
