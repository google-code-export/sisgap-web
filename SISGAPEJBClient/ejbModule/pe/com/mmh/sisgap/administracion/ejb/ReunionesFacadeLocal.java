package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.SisgapReuniones;
import pe.com.mmh.sisgap.domain.SisgapReunionesSocio;

@Local
public interface ReunionesFacadeLocal {

	public List<SisgapReuniones> findAll();
	
	public void grabarAsambleas(String fechaAsamblea, String lugarAsamblea, String agendaAsamblea, String acuerdoAsamblea, String observacionesAsamblea);
	
	List<SisgapReuniones> listarAsamblea(BigDecimal codigo);
	
	public SisgapReuniones buscarAsamblea(SisgapReuniones codigo);

}