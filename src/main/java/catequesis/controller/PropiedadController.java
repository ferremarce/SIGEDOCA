/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.clases.Parametro;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.JSFutil;

/**
 *
 * @author jmferreira
 */
@Named(value = "PropiedadController")
@SessionScoped
public class PropiedadController implements Serializable {

    private static final Logger LOG = Logger.getLogger(PropiedadController.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("propiedades.bundle", JSFutil.getmyLocale());

    Properties prop = new Properties();
    List<Parametro> listaParametro;

    /**
     * Creates a new instance of ParametroController
     */
    public PropiedadController() {
    }

    public List<Parametro> getListaParametro() {
        return listaParametro;
    }

    public void setListaParametro(List<Parametro> listaParametro) {
        this.listaParametro = listaParametro;
    }

    public String editarParametroForm() {
        this.cargarPropiedades();
        return "/pages/EditarPropiedades";
    }

    private void cargarPropiedades() {
        try {
            prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/propiedades/config.properties");
            prop.load(in);
            in.close();
            Enumeration enuProp = prop.propertyNames();
            this.listaParametro = new ArrayList<>();
            Parametro param;
            while (enuProp.hasMoreElements()) {
                String propertyName = (String) enuProp.nextElement();
                String propertyValue = prop.getProperty(propertyName);
                param = new Parametro(propertyName, propertyValue);
                this.listaParametro.add(param);
            }
        } catch (IOException ex) {
            Logger.getLogger(PropiedadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {
        this.cargarPropiedades();
    }

    public void doGuardar() {
        try {
            Properties props = new Properties();
            for (Parametro param : this.listaParametro) {
                if (param.getValue().isEmpty()) {
                    JSFutil.addErrorMessage("Uno o más propiedades tienen valores nulos. Por favor verifique y vuelva a intentar");
                    return;
                }
                props.setProperty(param.getKey(), param.getValue());
            }
            ClassLoader classLoader = getClass().getClassLoader();
            File f = new File(classLoader.getResource("/propiedades/config.properties").getFile());
            OutputStream out = new FileOutputStream(f);
            props.store(out, "This is an optional header comment string");
            out.close();
            JSFutil.addSuccessMessage("Se ha guardado exitosamente");
        } catch (IOException e) {
            JSFutil.addErrorMessage("No se ha guardado...");
            e.printStackTrace();
        }
    }
}
