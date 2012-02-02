/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import oracle.jdbc.driver.OracleTypes;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ItemSumistroLuz;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.ReciboluzOrg;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuzDet;

/**
 *
 * @author ANDREA
 */
@Stateless
public class SuministroLuzFacade implements SuministroLuzFacadeLocal {
	
	private static final String SP_INS_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_INS_RECIBOORILUZ(?,?,?,?,?,?,?)}";
	private static final String SP_UPD_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_UPD_RECIBOORILUZ(?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_DEL_RECIBOORILUZ(?)}";
	private static final String SP_LST_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_LST_RECIBOORILUZ(?)}";
	private static final String SP_UPD_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_UPD_SUMISTROLUZ(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_INS_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_INS_SUMISTROLUZ(?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_DEL_SUMISTROLUZ(?)}";
	private static final String SP_LST_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_LST_SUMISTROLUZ(?,?)}";
	
	@Resource(mappedName="java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
    @PersistenceContext
    private EntityManager em;

    
    public void createResOri(ReciboluzOrg sumistro) {
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_INS_RECIBOORILUZ);
			

			cst.setBigDecimal("P_NUM_LECTURA_INICIAL", sumistro.getNumLecturaInicial());
			cst.setBigDecimal("P_NUM_LECTURA_FINAL", sumistro.getNumLecturaFinal());
			cst.setBigDecimal("P_NUM_MONTO", sumistro.getNumMonto());
			cst.setBigDecimal("P_NUM_COSTO_WATS", sumistro.getNumCostoWats());
			cst.setDate("P_FEC_PERIODO", new Date(sumistro.getFecPeriodo().getTime()));
			cst.setBigDecimal("P_NUM_PENDIENTE_FAC", sumistro.getNumPendienteFac());
			cst.setBigDecimal("P_NUM_ESTADO", sumistro.getNumEstado());
			
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
    
    public void updateResOri(ReciboluzOrg sumistro) {
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_RECIBOORILUZ);
			
			cst.setLong("P_COD_ORGRECIBO_LUZ", sumistro.getCodOrgreciboLuz());
			cst.setBigDecimal("P_NUM_LECTURA_INICIAL", sumistro.getNumLecturaInicial());
			cst.setBigDecimal("P_NUM_LECTURA_FINAL", sumistro.getNumLecturaFinal());
			cst.setBigDecimal("P_NUM_MONTO", sumistro.getNumMonto());
			cst.setBigDecimal("P_NUM_COSTO_WATS", sumistro.getNumCostoWats());
			cst.setDate("P_FEC_PERIODO", new Date(sumistro.getFecPeriodo().getTime()));
			cst.setBigDecimal("P_NUM_PENDIENTE_FAC", sumistro.getNumPendienteFac());
			cst.setBigDecimal("P_NUM_ESTADO", sumistro.getNumEstado());
			
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
    
    
    public void deleteResOri(ReciboluzOrg sumistro) {
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_DEL_RECIBOORILUZ);
			
			cst.setLong("P_COD_ORGRECIBO_LUZ", sumistro.getCodOrgreciboLuz());
			
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
    
    public List<ReciboluzOrg> ListReciboluzOrg(){
    	Connection connection = null;
    	CallableStatement cst = null;
		ResultSet rs;
		List<ReciboluzOrg> lstReciboluzOrg = null;
		ReciboluzOrg res = null;
		try {
			connection = getConnection();
			lstReciboluzOrg = new ArrayList<ReciboluzOrg>();
			cst = connection.prepareCall(SP_LST_RECIBOORILUZ);
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.execute();
			rs = (ResultSet) cst.getObject(1);
			
			while(rs.next()){
				res = new ReciboluzOrg();
				res.setCodOrgreciboLuz(rs.getLong("COD_ORGRECIBO_LUZ"));
				res.setNumLecturaInicial(rs.getBigDecimal("NUM_LECTURA_INICIAL"));
				res.setNumLecturaFinal(rs.getBigDecimal("NUM_LECTURA_FINAL"));
				res.setNumMonto(rs.getBigDecimal("NUM_MONTO"));
				res.setNumCostoWats(rs.getBigDecimal("NUM_COSTO_WATS"));
				res.setFecPeriodo(rs.getDate("FEC_PERIODO"));
				res.setNumPendienteFac(rs.getBigDecimal("NUM_PENDIENTE_FAC"));
				res.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				lstReciboluzOrg.add(res);
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
		
		return lstReciboluzOrg;    	
    }    
    
	@Override
	public void create(SumistroLuz sumistro) {
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_INS_SUMISTROLUZ);
			

			cst.setLong("p_COD_ORGRECIBO_LUZ", sumistro.getCodFacOri());
			cst.setBigDecimal("p_NUM_CONSUMO_MES", sumistro.getNumConsumoMes());
			cst.setBigDecimal("p_NUM_DEU_ANTE", sumistro.getNumDeuAnte());
			cst.setLong("p_COD_SUMISTRO_LUZ", sumistro.getCodSumistroLuz());
			cst.setBigDecimal("p_NUM_LEC_ACTUAL", sumistro.getNumLecActual());
			cst.setLong("p_COD_USURIO", sumistro.getCodUsuario());
			cst.setBigDecimal("p_NUM_ESTADO", sumistro.getNumEstado());
			cst.setBigDecimal("p_NUM_IGV", sumistro.getNumIgv());
			cst.setBigDecimal("p_NUM_TOTAL", sumistro.getNumTotal());
			cst.setLong("p_TRAN_IDE", sumistro.getCodSocio());
			cst.setBigDecimal("p_NUM_LEC_ANTERIOR", sumistro.getNumLecAnterior());
			cst.setDate("p_FEC_SUMISTRO", new Date(sumistro.getFecSumistro().getTime()));
			
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
	public void edit(SumistroLuz sumistro) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_SUMISTROLUZ);
			
			cst.setLong("p_COD_SUMISTRO_LUZ", sumistro.getCodSumistroLuz());
			cst.setLong("p_COD_ORGRECIBO_LUZ", sumistro.getCodFacOri());
			cst.setBigDecimal("p_NUM_CONSUMO_MES", sumistro.getNumConsumoMes());
			cst.setBigDecimal("p_NUM_DEU_ANTE", sumistro.getNumDeuAnte());
			cst.setLong("p_COD_SUMISTRO_LUZ", sumistro.getCodSumistroLuz());
			cst.setBigDecimal("p_NUM_LEC_ACTUAL", sumistro.getNumLecActual());
			cst.setLong("p_COD_USURIO", sumistro.getCodUsuario());
			cst.setBigDecimal("p_NUM_ESTADO", sumistro.getNumEstado());
			cst.setBigDecimal("p_NUM_IGV", sumistro.getNumIgv());
			cst.setBigDecimal("p_NUM_TOTAL", sumistro.getNumTotal());
			cst.setLong("p_TRAN_IDE", sumistro.getCodSocio());
			cst.setBigDecimal("p_NUM_LEC_ANTERIOR", sumistro.getNumLecAnterior());
			cst.setDate("p_FEC_SUMISTRO", new Date(sumistro.getFecSumistro().getTime()));
			
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
	public void remove(SumistroLuz sumistro) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_DEL_SUMISTROLUZ);
			
			cst.setLong("p_COD_SUMISTRO_LUZ", sumistro.getCodSumistroLuz());
			
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
	public SumistroLuz find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SumistroLuz> findAll(Integer codigoResOri) {
    	Connection connection = null;
    	CallableStatement cst = null;
		ResultSet rs;
		List<SumistroLuz> lstSumistroLuz = null;
		SumistroLuz res = null;
		try {
			connection = getConnection();
			lstSumistroLuz = new ArrayList<SumistroLuz>();
			cst = connection.prepareCall(SP_LST_SUMISTROLUZ);
			cst.registerOutParameter("LSTSUMISTROLUZ", OracleTypes.CURSOR);
			cst.setBigDecimal("P_COD_ORGRECIBO_LUZ", new BigDecimal(codigoResOri));
			cst.execute();
			
			rs = (ResultSet) cst.getObject(1);
			
			while(rs.next()){
				res = new SumistroLuz();
				res.setCodSumistroLuz(rs.getLong("COD_SUMISTRO_LUZ"));
				res.setNumLecAnterior(rs.getBigDecimal("NUM_LEC_ANTERIOR"));
				res.setNumLecActual(rs.getBigDecimal("NUM_LEC_ACTUAL"));
				res.setNumConsumoMes(rs.getBigDecimal("NUM_CONSUMO_MES"));
				res.setNumDeuAnte(rs.getBigDecimal("NUM_DEU_ANTE"));
				res.setNumIgv(rs.getBigDecimal("NUM_IGV"));
				res.setNumTotal(rs.getBigDecimal("NUM_TOTAL"));
				res.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				res.setCodSocio(rs.getLong("TRAN_IDE"));
				res.setFecSumistro(rs.getDate("FEC_SUMISTRO"));
				res.setCodUsuario(rs.getLong("COD_USURIO"));
				res.setCodFacOri(rs.getLong("COD_ORGRECIBO_LUZ"));
				lstSumistroLuz.add(res);

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
		
		return lstSumistroLuz;   
	}

	@Override
	public void grabarSumistro(Long numerodocumento, String totalfac,
			String codigoide, String cbtipodoc,
			Set<SumistroLuzDet> detallesuministro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal generarNrodocumento(String tipodoc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void anularFactura(String codigoFactura, String descripanulada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItemSumistroLuz> getItemsSuministro() {
    	Connection connection = null;
    	CallableStatement cst = null;
    	BigDecimal codigo = new BigDecimal(0);
    	List<ItemSumistroLuz> itemSumistroLuzs = null;

		return itemSumistroLuzs;
	}


	private Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
