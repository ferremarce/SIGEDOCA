/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.fachada;

import catequesis.modelo.FormacionCristiana;
import catequesis.modelo.NivelCatequesis;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jmferreira
 */
@Stateless
public class FormacionCristianaFacade extends AbstractFacade<FormacionCristiana> {

    @Inject
    NivelCatequesisFacade nivelCatequesisFacade;
    @Inject
    FichaFacade fichaFacade;

    @PersistenceContext(unitName = "com.jmfa_catequesis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormacionCristianaFacade() {
        super(FormacionCristiana.class);
    }

    public void createFichaFormacion(Integer idFicha) {
        FormacionCristiana fc;
        for (NivelCatequesis nc : nivelCatequesisFacade.findAll()) {
            fc = new FormacionCristiana();
            fc.setIdFicha(fichaFacade.find(idFicha));
            fc.setIdNivel(nc);
            this.create(fc);
        }
    }

    public List<FormacionCristiana> listaFormacionTemplate() {
        List<FormacionCristiana> lista = new ArrayList<>();
        FormacionCristiana fc;
        for (NivelCatequesis nc : nivelCatequesisFacade.findAll()) {
            fc = new FormacionCristiana();
            fc.setIdNivel(nc);
            lista.add(fc);
        }
        return lista;
    }
}
