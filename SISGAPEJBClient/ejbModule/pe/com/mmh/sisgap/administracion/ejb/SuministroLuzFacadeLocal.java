package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ItemSumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuzDet;

@Local
public interface SuministroLuzFacadeLocal {
	
    void create(SumistroLuz sumistro);

    void edit(SumistroLuz sumistro);

    void remove(SumistroLuz sumistro);

    SumistroLuz find(Object id);

    List<SumistroLuz> findAll();
    
    void grabarSumistro(Long numerodocumento,String totalfac,	String codigoide, String cbtipodoc,Set<SumistroLuzDet> detallesuministro);

    BigDecimal generarNrodocumento(String tipodoc);

	void anularFactura(String codigoFactura, String descripanulada);
	
	List<ItemSumistroLuz> getItemsSuministro();
	
}
