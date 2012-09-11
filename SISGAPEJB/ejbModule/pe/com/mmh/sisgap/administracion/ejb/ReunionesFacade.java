package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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

import pe.com.mmh.sisgap.domain.ReciboluzOrg;
import pe.com.mmh.sisgap.domain.SisgapReuniones;
import pe.com.mmh.sisgap.domain.SisgapReunionesSocio;
import pe.com.mmh.sisgap.domain.SuministroLusReciboSocio;
import pe.com.mmh.sisgap.utils.JDBCUtil;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

@Stateless
public class ReunionesFacade implements ReunionesFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
	private static final String SP_REUNIONES_ALL = "{call PKG_ADMINISTRACION.SP_REUNIONES_ALL(?)}";
	private static final String SP_INS_ASAMBLEAS = "{call PKG_ADMINISTRACION.SP_INS_ASAMBLEAS(?,?,?,?,?,?)}";
	private static final String SP_LIST_ASAMBLEA = "{call PKG_ADMINISTRACION.SP_LST_ASAMBLEAS(?,?)}";
	
	
	@Override
	public List<SisgapReuniones> findAll() {
    	Connection connection = null;
    	Object objParams[]   = {};
    	List<SisgapReuniones> lst = new  ArrayList<SisgapReuniones>();
    	try {
    		connection = getConnection();
    		ResultSet rs = JDBCUtil.callSQLProcRS(connection,SP_REUNIONES_ALL,objParams);
    		SisgapReuniones reuniones = null;
    		while (rs.next()){
    			reuniones = new SisgapReuniones();
    			reuniones.setNumCodReuniones(rs.getBigDecimal("num_cod_reuniones"));
    			reuniones.setDatFechaSesion(rs.getDate("dat_fecha_sesion"));
    			reuniones.setStrLugar(rs.getString("str_lugar"));
    			reuniones.setStrAgenda(rs.getString("str_agenda"));
    			reuniones.setStrAcuerdos(rs.getString("str_acuerdos"));
    			reuniones.setStrObservaciones(rs.getString("str_observaciones"));
    			lst.add(reuniones);
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
	public List<SisgapReuniones> listarAsamblea(BigDecimal codigo) {
		System.out.println("[ReunionesFacade] Inicio - listarAsamblea");
    	Connection connection = null;
    	CallableStatement cst = null;
		ResultSet rs;
		List<SisgapReuniones> lstAsamblea = null;
		SisgapReuniones srs = null;
		try {
			connection = getConnection();
			lstAsamblea = new ArrayList<SisgapReuniones>();
			cst = connection.prepareCall(SP_LIST_ASAMBLEA);
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.setBigDecimal(2, codigo);
			cst.execute();
			rs = (ResultSet) cst.getObject(1);
			while(rs.next()){
				srs = new SisgapReuniones();
				srs.setNumCodReuniones(rs.getBigDecimal("NUM_COD_REUNIONES"));
				srs.setDatFechaSesion(rs.getDate("DAT_FECHA_SESION"));
				srs.setStrLugar(rs.getString("STR_LUGAR"));
				srs.setStrAgenda(rs.getString("STR_AGENDA"));
				srs.setStrAcuerdos(rs.getString("STR_ACUERDOS"));
				srs.setStrObservaciones(rs.getString("STR_OBSERVACIONES"));
				srs.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				srs.setDatFechaIngreso(rs.getDate("DAT_FECHA_INGRESO"));
				srs.setStrUsuario(rs.getString("STR_USUARIO"));
				lstAsamblea.add(srs);
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
		
		System.out.println("[ReunionesFacade] Final - listarAsamblea");
		return lstAsamblea; 
	}
 
	@Override
    public SisgapReuniones buscarAsamblea(SisgapReuniones asamblea) {
    	System.out.println("[ReunionesFacade] Inicio - buscarAsamblea");
    	Connection connection = null;
    	CallableStatement cst = null;
    	ResultSet rs = null;
    	SisgapReuniones srs = null;
    	try {
    		connection = getConnection();
			cst = connection.prepareCall(SP_LIST_ASAMBLEA);
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.setBigDecimal(2, asamblea.getNumCodReuniones());
			cst.execute();
			rs = (ResultSet) cst.getObject(1);
			if (rs.next()){
				srs = new SisgapReuniones();
				srs.setNumCodReuniones(rs.getBigDecimal("NUM_COD_REUNIONES"));
				srs.setDatFechaSesion(rs.getDate("DAT_FECHA_SESION"));
				srs.setStrLugar(rs.getString("STR_LUGAR"));
				srs.setStrAgenda(rs.getString("STR_AGENDA"));
				srs.setStrAcuerdos(rs.getString("STR_ACUERDOS"));
				srs.setStrObservaciones(rs.getString("STR_OBSERVACIONES"));
				srs.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				srs.setDatFechaIngreso(rs.getDate("DAT_FECHA_INGRESO"));
				srs.setStrUsuario(rs.getString("STR_USUARIO"));
			}
			
			
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
    	
    	System.out.println("[ReunionesFacade] Final - buscarAsamblea");
		return srs;
	}
	
	@Override
	public void grabarAsambleas(String fechaAsamblea, String lugarAsamblea, String agendaAsamblea, String acuerdoAsamblea, String observaciones) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {

    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_INS_ASAMBLEAS);
			
			cst.setString("P_FECHAASAMBLEA", fechaAsamblea);
			cst.setString("P_LUGARASAMBLEA", lugarAsamblea.toUpperCase());
			cst.setString("P_AGENDAASAMBLEA", agendaAsamblea.toUpperCase());
			cst.setString("P_ACUERDOASAMBLEA", acuerdoAsamblea.toUpperCase());
			cst.setString("P_OBSERVACIONES", observaciones.toUpperCase());
			cst.registerOutParameter("P_COD_REUNIONES", Types.NUMERIC);
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
