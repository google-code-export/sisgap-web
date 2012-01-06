/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.TipoSocio;

/**
 *
 * @author ANDREA
 */
@Local
public interface TipoSocioFacadeLocal {

    void create(TipoSocio tipoSocio);

    void edit(TipoSocio tipoSocio);

    void remove(TipoSocio tipoSocio);

    TipoSocio find(Object id);

    List<TipoSocio> findAll();

}
