package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Vigilancia;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Local
public interface VigilanciaFacadeLocal {

	public String mostrarPlatilla(String fecha);

	public void registrarVigilancia(String periodo, String codigoSocio);

	public int registraFind(String periodo, String codigoSocio);
	
	public String getVigilancia(String periodo, String codigoSocio);

	public List<Vigilancia> findAll();

	public void updateVigilancia(String periodo, String codigo, String valuess, String fecIngreso, String recNumero);

	public List<Vigilancia> findVigilancia(String date, String long1);
	
	public ResultSet getTempVigilancia(String periodo,Integer codigo);
	
	public List<Vigilancia> findVigilanciaAsociado(String date, String long1);
	
	void eliminarVigilancia(String codigoVigilancia);
	
	public int cargarVigilanciaTMP(String codigo, String fecIni, String fecFin, Integer rpta);
}