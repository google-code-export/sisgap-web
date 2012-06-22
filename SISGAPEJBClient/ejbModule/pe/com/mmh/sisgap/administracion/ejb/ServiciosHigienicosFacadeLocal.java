package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Local;

import pe.com.mmh.sisgap.domain.Sisa;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Local
public interface ServiciosHigienicosFacadeLocal {

	public List<Sisa> findAll();

	public List<Sisa> findSisa(String date, Long long1);
	

}