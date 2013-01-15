package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
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

import oracle.jdbc.driver.OracleTypes;

import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.Vigilancia;
import pe.com.mmh.sisgap.utils.JDBCUtil;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Stateless
public class VigilanciaFacade implements VigilanciaFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	
	private static final String SP_PLANTILLA_VIGILANCIA = "{call PKG_ADMINISTRACION.SP_PLANTILLA_VIGILANCIA(?,?)}";
	private static final String SP_REGISTRAR_VIGILANCIA = "{call PKG_ADMINISTRACION.SP_REGISTRAR_VIGILANCIA(?,?)}";
	private static final String SP_BUSCAR_VIGILANCIA = "{call PKG_ADMINISTRACION.SP_BUSCAR_VIGILANCIA(?,?,?)}";
	private static final String SP_GET_VIGILANCIA = "{call PKG_ADMINISTRACION.SP_GET_VIGILANCIA(?,?,?)}";
	private static final String SP_GET_VIGILANCIA_ALL = "{call PKG_ADMINISTRACION.SP_GET_VIGILANCIA_ALL(?)}";
	private static final String SP_ACTUALIZAR_VIGILANCIA = "{call PKG_ADMINISTRACION.SP_ACTUALIZAR_VIGILANCIA(?,?,?,?,?)}";
	private static final String SP_GET_VIGILANCIA_ID = "{call PKG_ADMINISTRACION.SP_GET_VIGILANCIA_ID(?,?,?)}";
	private static final String SP_MUESTRA_TEMP = "{call PKG_ADMINISTRACION.SP_MUESTRA_TEMP(?,?,?)}";
	private static final String SP_UPD_VIGILANCIAANULADA = "{call PKG_ADMINISTRACION.SP_UPD_VIGILANCIAANULADA(?)}";
	private static final String SP_DETALLE_ESTADO_CUENTA = "{call PKG_ADMINISTRACION.SP_DETALLE_ESTADO_CUENTA(?,?,?,?)}";
	private static final String SP_VIGILANCIA_ASOCIADO = "{call PKG_ADMINISTRACION.SP_VIGILANCIA_ASOCIADO(?,?,?)}";
	
	
    /* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.VigilanciaFacadeLocal#mostrarPlatilla(java.lang.String)
	 */
    @Override
	public String mostrarPlatilla(String fecha) {
    	Connection connection = null;
    	Object objParams[]   = {fecha};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			rsdcPlatilla=JDBCUtil.callSQLProcRSString(connection,SP_PLANTILLA_VIGILANCIA,objParams);
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
    	
	public void registrarVigilancia(String periodo, String codigoSocio) {
    	Connection connection = null;
    	Object objParams[]   = {codigoSocio,periodo};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			JDBCUtil.callSQLProcExecute(connection,SP_REGISTRAR_VIGILANCIA,objParams);
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
			return (Integer) JDBCUtil.callSQLProcObject(connection,SP_BUSCAR_VIGILANCIA,objParams);
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
	
	public String getVigilancia(String periodo, String codigoSocio) {
    	Connection connection = null;
    	Object objParams[]   = {codigoSocio.trim(),periodo};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			return JDBCUtil.callSQLProcRSString(connection,SP_GET_VIGILANCIA,objParams);
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
	public List<Vigilancia> findAll() {
    	Connection connection = null;
    	Object objParams[]   = {};
    	List<Vigilancia> lst = new  ArrayList<Vigilancia>();
    	try {			
    		connection = getConnection();
			ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_GET_VIGILANCIA_ALL,objParams);
			Vigilancia sisa= null;
			while (rs.next()) {
				sisa= new Vigilancia();
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
	public void updateVigilancia(String periodo, String codigo, String valuess, String fecIngreso, String recNumero) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	if(fecIngreso.trim().equals("")){
    		fecIngreso = null;
    	}
    	if(recNumero.trim().equals("")){
    		recNumero = null;
    	}
    	Object objParams[]   = {codigo.trim(),periodo,fecIngreso,recNumero,valuess};
    	String rsdcPlatilla = null;
    	try {			
    		connection = getConnection();
			JDBCUtil.callSQLProcExecute(connection,SP_ACTUALIZAR_VIGILANCIA,objParams);
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
	public List<Vigilancia> findVigilancia(String date, String long1) {
    	Connection connection = null;
    	Object objParams[]   = {long1,date};
    	List<Vigilancia> lst = new  ArrayList<Vigilancia>();
    	try {			
    		connection = getConnection();
			ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_GET_VIGILANCIA_ID,objParams);
			Vigilancia sisa= null;
			while (rs.next()) {
				sisa= new Vigilancia();
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
	
	public ResultSet getTempVigilancia(String periodo, Integer codigo){
		
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
	public List<Vigilancia> findVigilanciaAsociado(String date, String long1) {
    	Connection connection = null;
    	/*Object objParams[] = {date,long1};
    	Vigilancia sisa = null;
    	List<Vigilancia> lstVigilancia = new  ArrayList<Vigilancia>();*/
    	
    	
    	CallableStatement cst = null;
		ResultSet rs;
		List<Vigilancia> lstVigilancia = null;
		Vigilancia sisa = null;
    	try {			
    		connection = getConnection();
			//ResultSet rs =  JDBCUtil.callSQLProcRS(connection,SP_VIGILANCIA_ASOCIADO,objParams);
			
			lstVigilancia = new ArrayList<Vigilancia>();
			cst = connection.prepareCall(SP_VIGILANCIA_ASOCIADO);
			cst.setString("P_PERIODO", date);
			cst.setString("P_CODIGO", long1);
			cst.registerOutParameter("LSTVIGILANCIA", OracleTypes.CURSOR);
			cst.execute();
			
			rs = (ResultSet) cst.getObject("LSTVIGILANCIA");
			
			while (rs.next()) {
				sisa = new Vigilancia();
			    sisa.setCodigo(rs.getLong("codigo"));
			    sisa.setPuesto(rs.getString("puesto"));
			    sisa.setNombre(rs.getString("nombres"));
			    sisa.setGirocomer(rs.getString("girocomer"));
			    sisa.setSector(rs.getString("sector"));
				sisa.setPerido(rs.getDate("periodo"));
				sisa.setTotaldias(rs.getInt("dia"));
				sisa.setNumrecibo(rs.getString("numrecibo"));
				sisa.setFechaIngreso(rs.getDate("fechaingreso"));
				sisa.setValor(rs.getDouble("valor"));
				sisa.setDeuda(rs.getDouble("deuda"));				
				lstVigilancia.add(sisa);
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
    
    	return lstVigilancia;
	}	

	
	@Override
	public void eliminarVigilancia(String codigoVigilancia) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;

    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_VIGILANCIAANULADA);
			
			cst.setLong("P_COD_VIGILANCIA", new Long(codigoVigilancia));
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
	public int cargarVigilanciaTMP(String codigo, String fecIni, String fecFin, Integer rpta) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	//Object objParams[]   = {codigo.trim(),fecIni,fecFin,rpta};
    	//String rsdcPlatilla = null;
    	Integer eRpta=0;
    	try {			
    		connection = getConnection();
			//JDBCUtil.callSQLProcExecute(connection,SP_DETALLE_ESTADO_CUENTA,objParams);    	      
			CallableStatement cs =  connection.prepareCall(SP_DETALLE_ESTADO_CUENTA);
			cs.setString(1, codigo.trim());
			cs.setString(2, fecIni);
			cs.setString(3, fecFin);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			cs.execute();
    		eRpta = cs.getInt(4);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{			
				try {
					if(connection!=null){connection.close();}					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	return eRpta;
	}

}
