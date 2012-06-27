package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.Servicios;
import pe.com.mmh.sisgap.utils.JDBCUtil;

@Stateless
public class ServiciosHigienicosFacade implements ServiciosHigienicosFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	
	private static final String SP_LST_SSHH = "{call PKG_ADMINISTRACION.SP_LST_SSHH(?)}";
	
    /* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.SeFacadeLocal#mostrarPlatilla(java.lang.String)
	 */
	
	@Override
	public List<Servicios> findAll() {
    	Connection connection = null;
    	Object objParams[]   = {};
    	List<Servicios> lst = new  ArrayList<Servicios>();
    	try {			
    		connection = getConnection();
			ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_LST_SSHH,objParams);
			Servicios serv= null;
			while (rs.next()) {
				serv= new Servicios();
				serv.setCodServicio(rs.getBigDecimal("codServicio"));
				serv.setNroServicio(rs.getLong("nroServicio"));
				serv.setNumTotal(rs.getBigDecimal("numTotal"));
				serv.setStrDescripcion(rs.getString("strDescripcion"));
				serv.setDatFechacrea(rs.getDate("datFechacrea"));
				serv.setNumEstado(rs.getBoolean("numEstado"));
				lst.add(serv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    
    	return lst;
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


	@Override
	public List<Servicios> findSSHH(String date, Long long1) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Servicios> findServiciosList(String date, Long long1) {
		// TODO Auto-generated method stub
		return null;
	}



}
