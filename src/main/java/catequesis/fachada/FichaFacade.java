/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.fachada;

import catequesis.controller.FichaController;
import catequesis.modelo.Ficha;
import catequesis.modelo.FormacionCristiana;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jmferreira
 */
@Stateless
public class FichaFacade extends AbstractFacade<Ficha> {

    private static final Logger LOG = Logger.getLogger(FichaFacade.class.getName());

    @PersistenceContext(unitName = "com.jmfa_catequesis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichaFacade() {
        super(Ficha.class);
    }

    public List<FormacionCristiana> findAllFormacionCristiana(Integer idFicha) {
        String sql = "SELECT a FROM FormacionCristiana a WHERE a.idFicha.idFicha=:xIdFicha ORDER BY a.idNivel.orden";
        Query q = em.createQuery(sql);
        q.setParameter("xIdFicha", idFicha);
        LOG.log(Level.INFO, "findAllFormacionCristiana: {0}", sql);
        List<FormacionCristiana> tr = q.getResultList();
        return tr;
    }
}
