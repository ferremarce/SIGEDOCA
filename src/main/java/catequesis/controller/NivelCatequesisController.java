/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.NivelCatequesisFacade;
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
@Named(value = "NivelCatequesisController")
@SessionScoped
public class NivelCatequesisController implements Serializable {

    @Inject
    NivelCatequesisFacade nivelCatequesisFacade;

    /**
     * Creates a new instance of NivelCatequesisController
     */
    public NivelCatequesisController() {
    }

    public NivelCatequesisFacade getNivelCatequesisFacade() {
        return nivelCatequesisFacade;
    }

    public void setNivelCatequesisFacade(NivelCatequesisFacade nivelCatequesisFacade) {
        this.nivelCatequesisFacade = nivelCatequesisFacade;
    }

    public SelectItem[] getNivelCatequesisSet() {
        return JSFutil.getSelectItems(nivelCatequesisFacade.findAll(), Boolean.TRUE);
    }
}
