/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;
import javax.ejb.Local;
import pe.com.mmh.sisgap.domain.Socio;

/**
 *
 * @author ANDREA
 */
@Local
public interface SocioFacadeLocal {

    void create(Socio socio);

    void edit(Socio socio);

    void remove(Socio socio);

    Socio find(Object id);

    List<Socio> findAll();
    
    List<Socio> buscarxNombre(String nombre);
    
    List<Socio> buscarxPuesto(String puesto);

	String generarNroCodigo(String tipocod);

}
