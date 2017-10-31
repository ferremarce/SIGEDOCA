/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.fachada;

import catequesis.modelo.Capilla;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jmferreira
 */
@Stateless
public class CapillaFacade extends AbstractFacade<Capilla> {

    @PersistenceContext(unitName = "com.jmfa_catequesis_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapillaFacade() {
        super(Capilla.class);
    }

    public List<Capilla> getAllCapilla(String nombre) {
        Query q = em.createQuery("SELECT a FROM Capilla a WHERE a.nombre LIKE :xNombre ORDER BY a.nombre");
        q.setParameter("xNombre", "%" + nombre + "%");
        List<Capilla> tr = q.getResultList();
        return tr;

    }

}
