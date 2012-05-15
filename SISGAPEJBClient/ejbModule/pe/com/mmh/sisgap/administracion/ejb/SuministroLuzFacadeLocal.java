package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ItemSumistroLuz;
import pe.com.mmh.sisgap.domain.ReciboluzOrg;
import pe.com.mmh.sisgap.domain.Socio;
import pe.com.mmh.sisgap.domain.SuministroLusReciboSocio;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuzDet;

@Local
public interface SuministroLuzFacadeLocal {
	
    void create(SumistroLuz sumistro);

    void edit(SumistroLuz sumistro);

    void remove(SumistroLuz sumistro);

    SumistroLuz find(Object id);

    List<SumistroLuz> findAll(Integer codigoResOri);
    
    void grabarSumistro(Long numerodocumento,String totalfac,	String codigoide, String cbtipodoc,Set<SumistroLuzDet> detallesuministro);

    BigDecimal generarNrodocumento(String tipodoc);

	void anularFactura(String codigoFactura, String descripanulada);
	
	List<ItemSumistroLuz> getItemsSuministro();
	
	void createResOri(ReciboluzOrg sumistro);
	
	void updateResOri(ReciboluzOrg sumistro);
	
	void deleteResOri(ReciboluzOrg sumistro);
	
	List<ReciboluzOrg> ListReciboluzOrg();

	ReciboluzOrg buscarRecibo(ReciboluzOrg res);

	void grabarItemReciboLuzSocio(SuministroLusReciboSocio srs);

	List<SuministroLusReciboSocio> listarItemReciboLuzSocio(Long codigo);

	void eliminarItemReciboLuzSocio(Long correlativo, Long codigoSocio,
			Long codigoRecibo);

	void actualizarItemReciboLuzSocio(SuministroLusReciboSocio srs);

	void pagarItemReciboLuzSocio(Long long1, Long long2, Long long3);
	
	void imprimirItemReciboLuzSocio(Long long1, Long long2, Long long3);
	
	List<SuministroLusReciboSocio> buscarReciboxCodigo(String codSocio);
	
	int buscarReciboxCodigoxSocio(String codSocio, String codRecibo);
}
