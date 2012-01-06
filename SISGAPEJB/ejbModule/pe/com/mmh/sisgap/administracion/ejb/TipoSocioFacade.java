/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.com.mmh.sisgap.domain.TipoSocio;

/**
 *
 * @author ANDREA
 */
@Stateless
public class TipoSocioFacade implements TipoSocioFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(TipoSocio tipoSocio) {
        em.persist(tipoSocio);
    }

    public void edit(TipoSocio tipoSocio) {
        em.merge(tipoSocio);
    }

    public void remove(TipoSocio tipoSocio) {
        em.remove(em.merge(tipoSocio));
    }

    public TipoSocio find(Object id) {
        return em.find(TipoSocio.class, id);
    }

    public List<TipoSocio> findAll() {
        return em.createQuery("select object(o) from TipoSocio as o").getResultList();
    }

}
