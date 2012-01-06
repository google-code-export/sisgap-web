/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Socio;

/**
 *
 * @author ANDREA
 */
@Local
public interface ItemcobranzaFacadeLocal {

    void create(Itemcobranza itemcobranza);

    void edit(Itemcobranza itemcobranza);

    void remove(BigDecimal codigo);

    Itemcobranza find(Object id);

    List<Itemcobranza> findAll();
    
    List<Itemcobranza> buscarxNombre(String nombre);

}
