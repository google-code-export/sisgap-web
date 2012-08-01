package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Sisa;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Local
public interface SisasFacadeLocal {

	public String mostrarPlatilla(String fecha);

	public void registrarSisa(String periodo, String codigoSocio);

	public int registraFind(String periodo, String codigoSocio);
	
	public String getSisa(String periodo, String codigoSocio);

	public List<Sisa> findAll();

	public void updateSisa(String periodo, String codigo, String valuess);

	public List<Sisa> findSisa(String date, String long1);
	
	public ResultSet getTempSisa(String periodo,Integer codigo);
	
	void eliminarSisa(String codigoSisa);
}