package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import oracle.jdbc.driver.OracleTypes;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.SisgapReunionesSocio;
import pe.com.mmh.sisgap.domain.SuministroLusReciboSocio;
import pe.com.mmh.sisgap.utils.JDBCUtil;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Stateless
public class ReunionesSocioFacade implements ReunionesSocioFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
	private static final String SP_REUNIONES_ALL          = "{call PKG_ADMINISTRACION.SP_REUNIONES_ALL(?)}";
	private static final String SP_LIST_ASAMBLEASSOCIOS   = "{call PKG_ADMINISTRACION.SP_LIST_ASAMBLEASSOCIOS(?,?)}";
	private static final String SP_INS_ASAMBLEA_SOCIOS    = "{call PKG_ADMINISTRACION.SP_INS_ASAMBLEA_SOCIOS(?,?,?,?,?,?)}";
	private static final String SP_MUESTRA_TEMP_ASAMBLEAS = "{call PKG_ADMINISTRACION.SP_MUESTRA_TEMP_ASAMBLEAS(?)}";
	private static final String SP_UPD_ASOCIADOREUNION    = "{call PKG_ADMINISTRACION.SP_UPD_ASOCIADOREUNION(?,?,?)}";
	
	
	private static final String view_buscar_asamblea_socio = "select * from view_buscar_asamblea_socio where codigoreuniones = %s ";

	@Override
	public List<SisgapReunionesSocio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<SisgapReunionesSocio> listarAsambleaSocio(BigDecimal codigo) {
		System.out.println("[ReunionesSocioFacade] Inicio - listarAsambleaSocio");
    	Connection connection = null;
    	CallableStatement cst = null;
		ResultSet rs;
		List<SisgapReunionesSocio> lstAsambleaSocio = null;
		SisgapReunionesSocio srs = null;
		try {
			connection = getConnection();
			lstAsambleaSocio = new ArrayList<SisgapReunionesSocio>();
			cst = connection.prepareCall(SP_LIST_ASAMBLEASSOCIOS);
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.setBigDecimal(2, codigo);
			cst.execute();
			rs = (ResultSet) cst.getObject(1);
			while(rs.next()){
				srs = new SisgapReunionesSocio();
				srs.setCodigocorrelativo(rs.getBigDecimal("CODIGOCORRELATIVO"));
				srs.setCodigoreuniones(rs.getBigDecimal("CODIGOREUNIONES"));
				srs.setCodigosocios(rs.getString("CODIGOSOCIOS"));
				srs.setFechaasamblea(rs.getDate("DAT_FECHA_SESION"));
				srs.setNombres(rs.getString("NOMBRES"));
				srs.setSector(rs.getString("SECTOR"));
				srs.setEstado(rs.getBigDecimal("ESTADO"));
				srs.setObservaciones(rs.getString("OBSERVACIONES"));
				srs.setFechaingreso(rs.getDate("FECHAINGRESO"));
				srs.setUsuario(rs.getString("USUARIO"));
				lstAsambleaSocio.add(srs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(connection!=null){connection.close();}					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("[ReunionesSocioFacade] Final - listarAsambleaSocio");
		return lstAsambleaSocio; 
	}

	
	public int buscarReunionesxCodigoxSocio(String codRecibo, String codSocio) {
    	Connection connection = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	
    	int rpta=0;
    	
    	List<SisgapReunionesSocio> lsReunionesSocio = null;
    	SisgapReunionesSocio reciboSocio = null;
    	
		try {
			connection = getConnection();
			
			lsReunionesSocio = new ArrayList<SisgapReunionesSocio>();
			pst = connection.prepareStatement(String.format(view_buscar_asamblea_socio, "'"+ codSocio.trim()+ /*"' AND codigoreuniones = '"+ codRecibo.trim() +*/"' "));
			rs = pst.executeQuery();
			if(rs.next()){
				rpta = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rpta;
		
	}
	
	@Override
	public void grabarAsambleaSocio(String codAsamblea, String codSocio, String fecAsamblea, String estado, String usuario, String observaciones) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
//    		connection.setAutoCommit(false);
    		
			cst = connection.prepareCall(SP_INS_ASAMBLEA_SOCIOS);
			
			cst.setBigDecimal("P_CODIGOREUNIONES", new BigDecimal(codAsamblea));
			cst.setString("P_CODIGOSOCIOS", codSocio.trim());
			//cst.setString("P_FECHAASAMBLEA", fecAsamblea);
			cst.setBigDecimal("P_ESTADO", new BigDecimal(estado));
			cst.setString("P_USUARIOS", usuario);
			cst.setString("P_OBSERVACIONES", observaciones.toUpperCase().trim());
			cst.registerOutParameter("P_CODIGOCORRELATIVO", Types.NUMERIC);
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
	public void eliminarAsociadoReunion(Long codigoModi, String codigoAsoc,	Long codigoCorr) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_ASOCIADOREUNION);
			
			cst.setLong("P_CODIGOREUNION", codigoModi);
			cst.setString("P_CODIGOASOCIADO", codigoAsoc);
			cst.setLong("P_CODIGOCORRELATIVO", codigoCorr);
			
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

	
	
	public ResultSet getTempAsambleas(){
		
    	Connection connection = null;
    	Object objParams[]   = {};		
		connection = getConnection();
		ResultSet rs = null;
		
		try {
			
			rs =  JDBCUtil.callSQLProcRS(connection,SP_MUESTRA_TEMP_ASAMBLEAS,objParams);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return rs;
		
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
