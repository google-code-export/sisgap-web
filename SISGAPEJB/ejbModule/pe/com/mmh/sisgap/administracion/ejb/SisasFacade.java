package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.Sisa;
import pe.com.mmh.sisgap.utils.JDBCUtil;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Stateless
public class SisasFacade implements SisasFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	
	private static final String SP_PLANTILLA_SISA = "{call PKG_ADMINISTRACION.SP_PLANTILLA_SISA(?,?)}";
	private static final String SP_REGISTRAR_SISA = "{call PKG_ADMINISTRACION.SP_REGISTRAR_SISA(?,?)}";
	private static final String SP_BUSCAR_SISA = "{call PKG_ADMINISTRACION.SP_BUSCAR_SISA(?,?,?)}";
	private static final String SP_GET_SISA = "{call PKG_ADMINISTRACION.SP_GET_SISA(?,?,?)}";
	private static final String SP_GET_SISA_ALL = "{call PKG_ADMINISTRACION.SP_GET_SISA_ALL(?)}";
	private static final String SP_ACTUALIZAR_SISA = "{call PKG_ADMINISTRACION.SP_ACTUALIZAR_SISA(?,?,?)}";
	private static final String SP_GET_SISA_ID = "{call PKG_ADMINISTRACION.SP_GET_SISA_ID(?,?,?)}";
	private static final String SP_MUESTRA_TEMP = "{call PKG_ADMINISTRACION.SP_MUESTRA_TEMP(?,?,?)}";
	private static final String SP_UPD_SISAANULADA = "{call PKG_ADMINISTRACION.SP_UPD_SISAANULADA(?)}";
	private static final String SP_DETALLE_ESTADO_CUENTA = "{call PKG_ADMINISTRACION.SP_DETALLE_ESTADO_CUENTA(?)}";
	
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
    	
	public void registrarSisa(String periodo, String codigoSocio) {
    	Connection connection = null;
    	Object objParams[]   = {codigoSocio,periodo};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			JDBCUtil.callSQLProcExecute(connection,SP_REGISTRAR_SISA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    
    }
	
	public int registraFind(String periodo,String codigoSocio) {
    	Connection connection = null;
    	Object objParams[]   = {codigoSocio,periodo};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			return (Integer) JDBCUtil.callSQLProcObject(connection,SP_BUSCAR_SISA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    
    	return -1;
    }
	
	public String getSisa(String periodo, String codigoSocio) {
    	Connection connection = null;
    	Object objParams[]   = {codigoSocio.trim(),periodo};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			return JDBCUtil.callSQLProcRSString(connection,SP_GET_SISA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    
    	return null;
    }
	
	@Override
	public List<Sisa> findAll() {
    	Connection connection = null;
    	Object objParams[]   = {};
    	List<Sisa> lst = new  ArrayList<Sisa>();
    	try {			
    		connection = getConnection();
			ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_GET_SISA_ALL,objParams);
			Sisa sisa= null;
			while (rs.next()) {
				sisa= new Sisa();
				sisa.setNombre(rs.getString("tran_razon_social"));
				sisa.setPerido(rs.getDate("periodo"));
				sisa.setPuesto(rs.getString("tran_puesto"));
				sisa.setCodigo(rs.getLong("codigo"));
				sisa.setTotaldias(rs.getInt("totaldias"));
				sisa.setTotalpagos(rs.getInt("totalpagos"));
				sisa.setEstado(rs.getInt("estado"));
				sisa.setTranCodigo(rs.getString("tran_codigo"));
				lst.add(sisa);
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
	
	
	@Override
	public void updateSisa(String periodo, String codigo, String valuess) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	Object objParams[]   = {codigo.trim(),periodo,valuess};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			JDBCUtil.callSQLProcExecute(connection,SP_ACTUALIZAR_SISA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
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
	public List<Sisa> findSisa(String date, String long1) {
    	Connection connection = null;
    	Object objParams[]   = {long1,date};
    	List<Sisa> lst = new  ArrayList<Sisa>();
    	try {			
    		connection = getConnection();
			ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_GET_SISA_ID,objParams);
			Sisa sisa= null;
			while (rs.next()) {
				sisa= new Sisa();
				sisa.setNombre(rs.getString("tran_razon_social"));
				sisa.setPerido(rs.getDate("periodo"));
				sisa.setPuesto(rs.getString("tran_puesto"));
				sisa.setCodigo(rs.getLong("tran_ide"));
				sisa.setTotaldias(rs.getInt("totaldias"));
				sisa.setTotalpagos(rs.getInt("totalpagos"));
				sisa.setEstado(rs.getInt("estado"));
				sisa.setTranCodigo(rs.getString("tran_codigo"));				
				lst.add(sisa);
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
	
	public ResultSet getTempSisa(String periodo,Integer codigo){
		
    	Connection connection = null;
    	Object objParams[]   = {periodo,codigo};		
		connection = getConnection();
		ResultSet rs = null;
		
		try {
			
			rs =  JDBCUtil.callSQLProcRS(connection,SP_MUESTRA_TEMP,objParams);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return rs;
		
	}

	@Override
	public void eliminarSisa(String codigoSisa) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;

    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_SISAANULADA);
			
			cst.setLong("P_COD_SISA", new Long(codigoSisa));
			cst.execute();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{			
				try {
					if(cst!=null){cst.close();}
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void cargarVigilanciaTMP(String codigo) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	Object objParams[]   = {codigo.trim()};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			JDBCUtil.callSQLProcExecute(connection,SP_DETALLE_ESTADO_CUENTA,objParams);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
