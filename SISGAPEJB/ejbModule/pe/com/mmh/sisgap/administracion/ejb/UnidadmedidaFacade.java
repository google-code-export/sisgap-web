/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.com.mmh.sisgap.domain.Unidadmedida;

/**
 *
 * @author ANDREA
 */
@Stateless
public class UnidadmedidaFacade implements UnidadmedidaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Unidadmedida unidadmedida) {
        em.persist(unidadmedida);
    }

    public void edit(Unidadmedida unidadmedida) {
        em.merge(unidadmedida);
    }

    public void remove(Unidadmedida unidadmedida) {
        em.remove(em.merge(unidadmedida));
    }

    public Unidadmedida find(Object id) {
        return em.find(Unidadmedida.class, id);
    }

    public List<Unidadmedida> findAll() {
        return em.createQuery("select object(o) from Unidadmedida as o").getResultList();
    }

}
