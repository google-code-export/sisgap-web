/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.com.mmh.sisgap.domain.ActividadSocio;

/**
 *
 * @author ANDREA
 */
@Stateless
public class ActividadSocioFacade implements ActividadSocioFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(ActividadSocio actividadSocio) {
        em.persist(actividadSocio);
    }

    public void edit(ActividadSocio actividadSocio) {
        em.merge(actividadSocio);
    }

    public void remove(ActividadSocio actividadSocio) {
        em.remove(em.merge(actividadSocio));
    }

    public ActividadSocio find(Object id) {
        return em.find(ActividadSocio.class, id);
    }

    public List<ActividadSocio> findAll() {
        return em.createQuery("select object(o) from ActividadSocio as o").getResultList();
    }

}
