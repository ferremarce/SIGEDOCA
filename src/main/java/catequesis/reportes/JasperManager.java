/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import util.JSFutil;

/**
 *
 * @author jm_acer
 */
public class JasperManager {

    String userHome;
    private final HttpServletResponse response;
    private final FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
    private ServletContext servletContext;
    private HttpServletRequest request;
    private String tituloReporte = "Parroquia Santuario Nuestra Señora del Rosario";
    private String subTituloReporte = "Pastoral de Catequesis";

    public JasperManager() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
        servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        userHome = System.getProperty("user.home");
        //System.out.println("dir-----" + userHome);

    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void generarReporte(Map params, String reportSource, String tipoReporte, List<?> dataList) {
        try {
            params.put(JRParameter.REPORT_LOCALE, JSFutil.getmyLocale());
            params.put(JRParameter.REPORT_TIME_ZONE, JSFutil.getMyTimeZone());
            params.put("tituloReporte", this.tituloReporte);
            params.put("subTituloReporte", this.subTituloReporte);
            JasperReport report = JasperCompileManager.compileReport(reportSource);
            baos = new ByteArrayOutputStream();

            JRBeanCollectionDataSource listaReporte = new JRBeanCollectionDataSource(dataList);
            JasperPrint print = JasperFillManager.fillReport(report, params, listaReporte);

            switch (tipoReporte) {
                case "VPREVIA":
                    break;
                case "PDF":
                    JasperExportManager.exportReportToPdfStream(print, baos);
                    response.reset();
                    response.setContentType("application/pdf");
                    response.setContentLength(baos.size());
                    response.setHeader("Content-disposition", "inline; filename=reporteFicha.pdf");
                    response.getOutputStream().write(baos.toByteArray());
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                    context.responseComplete();
                    break;
                case "XLS":
                    break;
                default:
                    break;
            }
        } catch (JRException | IOException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
