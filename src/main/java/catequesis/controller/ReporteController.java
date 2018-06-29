/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.fachada.FichaFacade;
import catequesis.modelo.Ficha;
import catequesis.reportes.FuenteReporte;
import catequesis.reportes.JasperManager;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jmferreira
 */
@Named(value = "ReporteController")
@SessionScoped
public class ReporteController implements Serializable {

    @Inject
    FichaFacade fichaFacade;

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }

    public void generarReporte(Integer id) throws IOException {
        JasperManager jm = new JasperManager();
        List<Ficha> lista = new ArrayList<>();
        lista.add(fichaFacade.find(id));
        
        //Obtener la ruta del logo para la impresion
        String pathRoot = jm.getServletContext().getRealPath("/");
        pathRoot = pathRoot.replace('\\', '/');
        String imagenSource = pathRoot + "img/logo.jpg";
        Map<String, Object> params = new HashMap<>();
        params.put("imagenPath", imagenSource);
        //params.put("author", JSFutil.);
        String tipoReporte = "PDF";
        String idFuenteReporte = "1";
        FuenteReporte fr = new FuenteReporte(Integer.valueOf(idFuenteReporte));
        String reportSource = pathRoot + "reportes/template/" + fr.getNombreReporte();
        jm.generarReporte(params, reportSource, tipoReporte, lista);
    }
}
