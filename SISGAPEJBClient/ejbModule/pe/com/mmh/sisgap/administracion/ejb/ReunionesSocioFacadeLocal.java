package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.SisgapReunionesSocio;

@Local
public interface ReunionesSocioFacadeLocal {

	public List<SisgapReunionesSocio> findAll();
	
	List<SisgapReunionesSocio> listarAsambleaSocio(BigDecimal codigo);
	
	public int buscarReunionesxCodigoxSocio(String codRecibo, String codSocio);
	
	public void grabarAsambleaSocio(String codAsamblea, String codSocio, String fecAsamblea, String estado, String usuario, String observaciones);

	void eliminarAsociadoReunion(Long codigoModi, String codigoAsoc, Long codigoCorr);
	
	public ResultSet getTempAsambleas();
}