package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.SisgapReuniones;

@Local
public interface ReunionesFacadeLocal {

	public List<SisgapReuniones> findAll();
	
	public void grabarAsambleas(String fechaAsamblea, String lugarAsamblea, String agendaAsamblea, String acuerdoAsamblea, String observacionesAsamblea);
	
	public void actualizarAsambleas(BigDecimal codigoReuniones, String fechaAsamblea, String lugarAsamblea, String agendaAsamblea, String acuerdoAsamblea, String observacionesAsamblea, String usuario);
	
	List<SisgapReuniones> listarAsamblea(BigDecimal codigo);
	
	void eliminarAsamblea(String codigo);
	
	public SisgapReuniones buscarAsamblea(SisgapReuniones codigo);

}