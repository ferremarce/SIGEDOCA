/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.fachada;

import catequesis.modelo.Parroquia;
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
public class ParroquiaFacade extends AbstractFacade<Parroquia> {

    private static final Logger LOG = Logger.getLogger(SubTipoFacade.class.getName());
    @PersistenceContext(unitName = "com.jmfa_catequesis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParroquiaFacade() {
        super(Parroquia.class);
    }

    public List<Parroquia> findAllbyDiocesis(Integer idDiocesis) {
        String sql = "SELECT a FROM Parroquia a WHERE a.idDiocesis.idDiocesis=:xIdDiocesis ORDER BY a.nombre";
        Query q = em.createQuery(sql);
        q.setParameter("xIdDiocesis", idDiocesis);
        LOG.log(Level.INFO, "findAllbyDiocesis: {0}", sql);
        List<Parroquia> tr = q.getResultList();
        return tr;
    }

}
