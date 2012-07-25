package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ServicioDetalle;
import pe.com.mmh.sisgap.domain.Servicios;

@Local
public interface ServiciosHigienicosFacadeLocal {

    void create(Servicios servicio);

    void edit(Servicios servicio);

    Servicios find(Object id);
    
    //ServicioDetalle findSrvDet(Object id);

	public List<Servicios> findAll();

	public List<Servicios> findSSHH(String date, Long long1);

	public List<Servicios> findServiciosList(String date, Long long1);
	
	void grabarServicios(Long codServicio,String descripcion, BigDecimal montototal, String fechadoc, List<ServicioDetalle> serviciodetalle);
	
	public void anularServicio(String codigoServicio);

}