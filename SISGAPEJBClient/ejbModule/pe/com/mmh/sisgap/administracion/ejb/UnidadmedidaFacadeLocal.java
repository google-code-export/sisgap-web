/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.Unidadmedida;

/**
 *
 * @author ANDREA
 */
@Local
public interface UnidadmedidaFacadeLocal {

    void create(Unidadmedida unidadmedida);

    void edit(Unidadmedida unidadmedida);

    void remove(Unidadmedida unidadmedida);

    Unidadmedida find(Object id);

    List<Unidadmedida> findAll();

}
