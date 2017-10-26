/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.TipoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "TipoController")
@SessionScoped
public class TipoController implements Serializable {

    @Inject
    TipoFacade tipoFacade;

    /**
     * Creates a new instance of TipoController
     */
    public TipoController() {
    }

    public TipoFacade getTipoFacade() {
        return tipoFacade;
    }

    public void setTipoFacade(TipoFacade tipoFacade) {
        this.tipoFacade = tipoFacade;
    }

    public SelectItem[] getTipoSet() {
        return JSFutil.getSelectItems(tipoFacade.findAll(), Boolean.FALSE);
    }
}
