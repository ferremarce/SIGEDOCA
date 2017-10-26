/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "MainController")
@SessionScoped
public class MainController implements Serializable {

    /**
     * Creates a new instance of MainController
     */
    public MainController() {
    }

    public String doGetAnhoActual() {
        Calendar c = Calendar.getInstance();
        c.setTime(JSFutil.getFechaHoraActual());
        return "" + c.get(Calendar.YEAR);
    }
}
