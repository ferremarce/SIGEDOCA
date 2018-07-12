/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.fachada;

import catequesis.modelo.DetalleCapilla;
import catequesis.modelo.Parroquia;
import static catequesis.modelo.Parroquia_.idDiocesis;
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
public class DetalleCapillaFacade extends AbstractFacade<DetalleCapilla> {

    private static final Logger LOG = Logger.getLogger(SubTipoFacade.class.getName());

    @PersistenceContext(unitName = "com.jmfa_catequesis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCapillaFacade() {
        super(DetalleCapilla.class);
    }

    public List<DetalleCapilla> getDetalleCapilla(Integer anho) {
        String sql = "SELECT a FROM DetalleCapilla a WHERE a.anho=:xAnho ORDER BY a.anho DESC";
        Query q = em.createQuery(sql);
        q.setParameter("xAnho", anho);
        LOG.log(Level.INFO, "getDetalleCapilla: {0}", sql);
        List<DetalleCapilla> tr = q.getResultList();
        return tr;
    }

}
