/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.converter;

import catequesis.controller.TipoTreeController;
import catequesis.modelo.TipoTree;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converter del TipoTree.class
 * @author jmferreira
 */
@FacesConverter(forClass = TipoTree.class)
public class TipoTreeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        //Debe comparar con ---opciones--- que se carga en el ListItem de JSFutil
        if (value == null || value.length() == 0 || value.compareTo("------ Opciones ------") == 0) {
            return null;
        }
        TipoTreeController controller = (TipoTreeController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "TipoTreeController");
        return controller.getTipoTreeFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String && object.toString().compareTo("------ Opciones ------") == 0) {
            return null;
        }
        if (object instanceof TipoTree) {
            TipoTree o = (TipoTree) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoTreeController.class.getName());
        }
    }
}
