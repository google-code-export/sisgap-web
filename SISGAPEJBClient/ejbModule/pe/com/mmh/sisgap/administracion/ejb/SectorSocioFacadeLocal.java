/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.SectorSocio;

/**
 *
 * @author ANDREA
 */
@Local
public interface SectorSocioFacadeLocal {

    void create(SectorSocio sectorSocio);

    void edit(SectorSocio sectorSocio);

    void remove(SectorSocio sectorSocio);

    SectorSocio find(Object id);

    List<SectorSocio> findAll();

}
