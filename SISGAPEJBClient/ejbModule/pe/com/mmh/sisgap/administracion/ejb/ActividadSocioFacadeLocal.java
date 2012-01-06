/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.ActividadSocio;

/**
 *
 * @author ANDREA
 */
@Local
public interface ActividadSocioFacadeLocal {

    void create(ActividadSocio actividadSocio);

    void edit(ActividadSocio actividadSocio);

    void remove(ActividadSocio actividadSocio);

    ActividadSocio find(Object id);

    List<ActividadSocio> findAll();

}
