/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.com.mmh.sisgap.domain.SectorSocio;

/**
 *
 * @author ANDREA
 */
@Stateless
public class SectorSocioFacade implements SectorSocioFacadeLocal {
    @PersistenceContext
    private EntityManager em;

    public void create(SectorSocio sectorSocio) {
        em.persist(sectorSocio);
    }

    public void edit(SectorSocio sectorSocio) {
        em.merge(sectorSocio);
    }

    public void remove(SectorSocio sectorSocio) {
        em.remove(em.merge(sectorSocio));
    }

    public SectorSocio find(Object id) {
        return em.find(SectorSocio.class, id);
    }

    public List<SectorSocio> findAll() {
        return em.createQuery("select object(o) from SectorSocio as o").getResultList();
    }

}
