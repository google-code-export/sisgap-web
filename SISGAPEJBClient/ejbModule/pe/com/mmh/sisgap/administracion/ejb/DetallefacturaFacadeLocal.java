/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.Detallefactura;

/**
 *
 * @author ANDREA
 */
@Local
public interface DetallefacturaFacadeLocal {

    void create(Detallefactura detallefactura);

    void edit(Detallefactura detallefactura);

    void remove(Detallefactura detallefactura);

    Detallefactura find(Object id);

    List<Detallefactura> findAll();

}
