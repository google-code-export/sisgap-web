package pe.com.mmh.sisgap.administracion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Servicios;

@Local
public interface ServiciosHigienicosFacadeLocal {

	public List<Servicios> findAll();

	public List<Servicios> findSSHH(String date, Long long1);

	public List<Servicios> findServiciosList(String date, Long long1);
	

}