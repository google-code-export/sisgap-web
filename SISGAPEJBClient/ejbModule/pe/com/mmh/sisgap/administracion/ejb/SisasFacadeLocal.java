package pe.com.mmh.sisgap.administracion.ejb;

import javax.ejb.Local;

import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Local
public interface SisasFacadeLocal {

	public String mostrarPlatilla(String fecha);

}