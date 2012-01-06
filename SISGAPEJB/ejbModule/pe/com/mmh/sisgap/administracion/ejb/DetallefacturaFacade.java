/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.com.mmh.sisgap.domain.Detallefactura;

/**
 *
 * @author ANDREA
 */
@Stateless
public class DetallefacturaFacade implements DetallefacturaFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(Detallefactura detallefactura) {
        em.persist(detallefactura);
    }

    public void edit(Detallefactura detallefactura) {
        em.merge(detallefactura);
    }

    public void remove(Detallefactura detallefactura) {
        em.remove(em.merge(detallefactura));
    }

    public Detallefactura find(Object id) {
        return em.find(Detallefactura.class, id);
    }

    public List<Detallefactura> findAll() {
        return em.createQuery("select object(o) from Detallefactura as o").getResultList();
    }

}
