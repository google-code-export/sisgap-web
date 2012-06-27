package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.ServicioItem;

@Local
public interface ServiciosItemFacadeLocal {

	public List<ServicioItem> findAll();	

}