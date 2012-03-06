package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.utils.JDBCUtil;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Stateless
public class SisasFacade implements SisasFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	
	private static final String SP_PLANTILLA_SISA = "{call PKG_ADMINISTRACION.SP_PLANTILLA_SISA(?,?)}";
	
	
    /* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal#mostrarPlatilla(java.lang.String)
	 */
    @Override
	public String mostrarPlatilla(String fecha) {
    	Connection connection = null;
    	Object objParams[]   = {fecha};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			rsdcPlatilla=JDBCUtil.callSQLProcRSString(connection,SP_PLANTILLA_SISA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
    	return rsdcPlatilla;
    }

	
	
	
	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
