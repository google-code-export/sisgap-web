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
import java.text.DecimalFormat;
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
import pe.com.mmh.sisgap.domain.Socio;
import pe.com.mmh.sisgap.domain.SuministroLusReciboSocio;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuzDet;

/**
 *
 * @author ANDREA
 */
@Stateless
public class SuministroLuzFacade implements SuministroLuzFacadeLocal {
	
	private static final String SP_INS_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_INS_RECIBOORILUZ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_UPD_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_UPD_RECIBOORILUZ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_DEL_RECIBOORILUZ(?)}";
	private static final String SP_LST_RECIBOORILUZ = "{call PKG_ADMINISTRACION.SP_LST_RECIBOORILUZ(?)}";
	private static final String SP_UPD_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_UPD_SUMISTROLUZ(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_INS_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_INS_SUMISTROLUZ(?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_DEL_SUMISTROLUZ(?)}";
	private static final String SP_LST_SUMISTROLUZ = "{call PKG_ADMINISTRACION.SP_LST_SUMISTROLUZ(?,?)}";
	private static final String SP_FIN_SUMISTROLUZORI = "{call PKG_ADMINISTRACION.SP_FIN_SUMISTROLUZORI(?,?)}";
	
	private static final String SP_INS_SUMINISTROLUZSOCIO = "{call PKG_SISGAP_RECIBOLUZ_SOCIO.SP_INS_SUMINISTROLUZSOCIO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_LIST_SUMINISTROLUZSOCIOS = "{call PKG_SISGAP_RECIBOLUZ_SOCIO.SP_LIST_SUMINISTROLUZSOCIOS(?,?)}";
	private static final String SP_DEL_SUMINISTROLUZSOCIO = "{call PKG_SISGAP_RECIBOLUZ_SOCIO.SP_DEL_SUMINISTROLUZSOCIO(?,?,?)}";
	private static final String SP_UPD_SUMINISTROLUZSOCIO = "{call PKG_SISGAP_RECIBOLUZ_SOCIO.SP_UPD_SUMINISTROLUZSOCIO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_SUMINISTROLUZSOCIOPAGAR = "{call PKG_ADMINISTRACION.SP_SUMINISTROLUZSOCIOPAGAR(?,?,?)}";
	private static final String SP_SUMINISTROLUZSOCIOIMPRIMIR = "{call PKG_ADMINISTRACION.SP_SUMINISTROLUZSOCIOIMPRIMIR(?,?,?)}";
	
	//private static final String SP_LST_RECIBOLUZSOCIO = "{call PKG_ADMINISTRACION.SP_LST_SUMISTROLUZxCODSOCIO(?,?,?)";
	
	private static final String view_buscar_recibo_socio = "select * from view_buscar_recibo_socio where tran_codigo = %s order by fechacarga desc ";
	//private static final String view_buscar_recibo_socio = "SELECT t.*, t.rowid FROM (select * from view_buscar_recibo_socio where tran_codigo = %s order by fechacarga desc) t WHERE rownum < 3 ";
	
	private static final String view_buscar_recibo_luzsocio = "select * from view_buscar_recibo_socio where codigosocio = %s order by fechacarga desc ";
	
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
			

			cst.setBigDecimal("p_NUM_LECTURA_INICIAL", sumistro.getNumLecturaInicial());
			cst.setBigDecimal("p_NUM_LECTURA_FINAL", sumistro.getNumLecturaFinal());
			cst.setBigDecimal("p_NUM_MONTO", sumistro.getNumMonto());
			cst.setBigDecimal("p_NUM_COSTO_WATS", sumistro.getNumCostoWats());
			cst.setDate("p_FEC_PERIODO", new Date(sumistro.getFecPeriodo().getTime()));
			cst.setBigDecimal("p_NUM_PENDIENTE_FAC", sumistro.getNumPendienteFac());
			cst.setBigDecimal("p_NUM_ESTADO", sumistro.getNumEstado());
			
			cst.setBigDecimal("p_REPOMANCNX",sumistro.getRepomancnx());
			cst.setBigDecimal("p_CARGOFIJO",sumistro.getCargofijo());
			cst.setBigDecimal("p_ALUMPUBLIC",sumistro.getAlumpublic());
			cst.setBigDecimal("p_SUBTOTALMES",sumistro.getSubtotalmes());
			cst.setBigDecimal("p_IGV",sumistro.getIgv());
			cst.setBigDecimal("p_TOTALMESACT",sumistro.getTotalmesact());
			cst.setBigDecimal("p_APORTELEY",sumistro.getAporteley());
			cst.setBigDecimal("p_CUOTACONV",sumistro.getCuotaconv());
			cst.setBigDecimal("p_REDONMESACT",sumistro.getRedonmesact());
			cst.setBigDecimal("p_REDONMESANT",sumistro.getRedonmesant());
			cst.setBigDecimal("p_INTERESCONVENIO",sumistro.getInteresconvenio());
			cst.setBigDecimal("p_ENERGACTFRAPTAACTUAL",sumistro.getEnergactfraptaactual());
			cst.setBigDecimal("p_ENERGACTFRAPTAANTERI",sumistro.getEnergactfraptaanteri());
			cst.setBigDecimal("p_ENERGACTFRAPTADIFER",sumistro.getEnergactfraptadifer());
			cst.setBigDecimal("p_ENERGACTFRAPTAFACTOR",sumistro.getEnergactfraptafactor());
			cst.setBigDecimal("p_ENERGACTFRAPTACONSU",sumistro.getEnergactfraptaconsu());
			cst.setBigDecimal("p_ENERGACTFRAPTACONFA",sumistro.getEnergactfraptaconfa());
			cst.setBigDecimal("p_ENERGACTFRAPTAPREUNI",sumistro.getEnergactfraptapreuni());
			cst.setBigDecimal("p_ENERGACTFRAPTATOTAL",sumistro.getEnergactfraptatotal());
			cst.setBigDecimal("p_ENERGACTHORPTAACTU",sumistro.getEnergacthorptaactu());
			cst.setBigDecimal("p_ENERGACTHORPTAANT",sumistro.getEnergacthorptaant());
			cst.setBigDecimal("p_ENERGACTHORPTADIF",sumistro.getEnergacthorptadif());
			cst.setBigDecimal("p_ENERGACTHORPTAFAC",sumistro.getEnergacthorptafac());
			cst.setBigDecimal("p_ENERGACTHORPTACONS",sumistro.getEnergacthorptacons());
			cst.setBigDecimal("p_ENERGACTHORPTACONFAC",sumistro.getEnergacthorptaconfac());
			cst.setBigDecimal("p_ENERGACTHORPTAPREUNI",sumistro.getEnergacthorptapreuni());
			cst.setBigDecimal("p_ENERGACTHORPTATOTAL",sumistro.getEnergacthorptatotal());
			cst.setBigDecimal("p_ENERGREACINICIAL",sumistro.getEnergreacinicial());
			cst.setBigDecimal("p_ENERGREACANTERI",sumistro.getEnergreacanteri());
			cst.setBigDecimal("p_ENERGREACDIFERE",sumistro.getEnergreacdifere());
			cst.setBigDecimal("p_ENERGREACFACTOR",sumistro.getEnergreacfactor());
			cst.setBigDecimal("p_ENERGREACCONSU",sumistro.getEnergreacconsu());
			cst.setBigDecimal("p_ENERGREACFACCONS",sumistro.getEnergreacfaccons());
			cst.setBigDecimal("p_ENERGREACPREUNI",sumistro.getEnergreacpreuni());
			cst.setBigDecimal("p_ENERGREACTOTAL",sumistro.getEnergreactotal());
			cst.setBigDecimal("p_POTENCIAFPINI",sumistro.getPotenciafpini());
			cst.setBigDecimal("p_POTENCIAFPANTE",sumistro.getPotenciafpante());
			cst.setBigDecimal("p_POTENCIAFPDIF",sumistro.getPotenciafpdif());
			cst.setBigDecimal("p_POTENCIAFPFAC",sumistro.getPotenciafpfac());
			cst.setBigDecimal("p_POTENCIAFPCONS",sumistro.getPotenciafpcons());
			cst.setBigDecimal("p_POTENCIAHPACT",sumistro.getPotenciahpact());
			cst.setBigDecimal("p_POTENCIAHPANT",sumistro.getPotenciahpant());
			cst.setBigDecimal("p_POTENCIAHPDIF",sumistro.getPotenciahpdif());
			cst.setBigDecimal("p_POTENCIAHPFAC",sumistro.getPotenciahpfac());
			cst.setBigDecimal("p_POTENCIAHPCONS",sumistro.getPotenciahpcons());
			cst.setBigDecimal("p_POTUSOREDDISTCONFAC",sumistro.getPotusoreddistconfac());
			cst.setBigDecimal("p_POTUSOREDDISTPREUNI",sumistro.getPotusoreddistpreuni());
			cst.setBigDecimal("p_POTGENFPCONFAC",sumistro.getPotgenfpconfac());
			cst.setBigDecimal("p_POTGENFPPREUNI",sumistro.getPotgenfppreuni());
			cst.setBigDecimal("p_POTUSOREDDISTTOTAL",sumistro.getPotusoreddisttotal());
			cst.setBigDecimal("p_POTGENFPTOTAL",sumistro.getPotgenfptotal());
			
			
			cst.setDate("p_FECVENCIMIENTO", new Date(sumistro.getFecVencimiento().getTime()));
			cst.setDate("p_FECEMISION", new Date(sumistro.getFecEmision().getTime()));
			
			
			
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
			
			cst.setLong("p_COD_ORGRECIBO_LUZ", sumistro.getCodOrgreciboLuz());
			cst.setBigDecimal("p_NUM_LECTURA_INICIAL", sumistro.getNumLecturaInicial());
			cst.setBigDecimal("p_NUM_LECTURA_FINAL", sumistro.getNumLecturaFinal());
			cst.setBigDecimal("p_NUM_MONTO", sumistro.getNumMonto());
			cst.setBigDecimal("p_NUM_COSTO_WATS", sumistro.getNumCostoWats());
			cst.setDate("p_FEC_PERIODO", new Date(sumistro.getFecPeriodo().getTime()));
			cst.setBigDecimal("p_NUM_PENDIENTE_FAC", sumistro.getNumPendienteFac());
			cst.setBigDecimal("p_NUM_ESTADO", sumistro.getNumEstado());
			
			cst.setBigDecimal("p_REPOMANCNX",sumistro.getRepomancnx());
			cst.setBigDecimal("p_CARGOFIJO",sumistro.getCargofijo());
			cst.setBigDecimal("p_ALUMPUBLIC",sumistro.getAlumpublic());
			cst.setBigDecimal("p_SUBTOTALMES",sumistro.getSubtotalmes());
			cst.setBigDecimal("p_IGV",sumistro.getIgv());
			cst.setBigDecimal("p_TOTALMESACT",sumistro.getTotalmesact());
			cst.setBigDecimal("p_APORTELEY",sumistro.getAporteley());
			cst.setBigDecimal("p_CUOTACONV",sumistro.getCuotaconv());
			cst.setBigDecimal("p_REDONMESACT",sumistro.getRedonmesact());
			cst.setBigDecimal("p_REDONMESANT",sumistro.getRedonmesant());
			cst.setBigDecimal("p_INTERESCONVENIO",sumistro.getInteresconvenio());
			cst.setBigDecimal("p_ENERGACTFRAPTAACTUAL",sumistro.getEnergactfraptaactual());
			cst.setBigDecimal("p_ENERGACTFRAPTAANTERI",sumistro.getEnergactfraptaanteri());
			cst.setBigDecimal("p_ENERGACTFRAPTADIFER",sumistro.getEnergactfraptadifer());
			cst.setBigDecimal("p_ENERGACTFRAPTAFACTOR",sumistro.getEnergactfraptafactor());
			cst.setBigDecimal("p_ENERGACTFRAPTACONSU",sumistro.getEnergactfraptaconsu());
			cst.setBigDecimal("p_ENERGACTFRAPTACONFA",sumistro.getEnergactfraptaconfa());
			cst.setBigDecimal("p_ENERGACTFRAPTAPREUNI",sumistro.getEnergactfraptapreuni());
			cst.setBigDecimal("p_ENERGACTFRAPTATOTAL",sumistro.getEnergactfraptatotal());
			cst.setBigDecimal("p_ENERGACTHORPTAACTU",sumistro.getEnergacthorptaactu());
			cst.setBigDecimal("p_ENERGACTHORPTAANT",sumistro.getEnergacthorptaant());
			cst.setBigDecimal("p_ENERGACTHORPTADIF",sumistro.getEnergacthorptadif());
			cst.setBigDecimal("p_ENERGACTHORPTAFAC",sumistro.getEnergacthorptafac());
			cst.setBigDecimal("p_ENERGACTHORPTACONS",sumistro.getEnergacthorptacons());
			cst.setBigDecimal("p_ENERGACTHORPTACONFAC",sumistro.getEnergacthorptaconfac());
			cst.setBigDecimal("p_ENERGACTHORPTAPREUNI",sumistro.getEnergacthorptapreuni());
			cst.setBigDecimal("p_ENERGACTHORPTATOTAL",sumistro.getEnergacthorptatotal());
			cst.setBigDecimal("p_ENERGREACINICIAL",sumistro.getEnergreacinicial());
			cst.setBigDecimal("p_ENERGREACANTERI",sumistro.getEnergreacanteri());
			cst.setBigDecimal("p_ENERGREACDIFERE",sumistro.getEnergreacdifere());
			cst.setBigDecimal("p_ENERGREACFACTOR",sumistro.getEnergreacfactor());
			cst.setBigDecimal("p_ENERGREACCONSU",sumistro.getEnergreacconsu());
			cst.setBigDecimal("p_ENERGREACFACCONS",sumistro.getEnergreacfaccons());
			cst.setBigDecimal("p_ENERGREACPREUNI",sumistro.getEnergreacpreuni());
			cst.setBigDecimal("p_ENERGREACTOTAL",sumistro.getEnergreactotal());
			cst.setBigDecimal("p_POTENCIAFPINI",sumistro.getPotenciafpini());
			cst.setBigDecimal("p_POTENCIAFPANTE",sumistro.getPotenciafpante());
			cst.setBigDecimal("p_POTENCIAFPDIF",sumistro.getPotenciafpdif());
			cst.setBigDecimal("p_POTENCIAFPFAC",sumistro.getPotenciafpfac());
			cst.setBigDecimal("p_POTENCIAFPCONS",sumistro.getPotenciafpcons());
			cst.setBigDecimal("p_POTENCIAHPACT",sumistro.getPotenciahpact());
			cst.setBigDecimal("p_POTENCIAHPANT",sumistro.getPotenciahpant());
			cst.setBigDecimal("p_POTENCIAHPDIF",sumistro.getPotenciahpdif());
			cst.setBigDecimal("p_POTENCIAHPFAC",sumistro.getPotenciahpfac());
			cst.setBigDecimal("p_POTENCIAHPCONS",sumistro.getPotenciahpcons());
			cst.setBigDecimal("p_POTUSOREDDISTCONFAC",sumistro.getPotusoreddistconfac());
			cst.setBigDecimal("p_POTUSOREDDISTPREUNI",sumistro.getPotusoreddistpreuni());
			cst.setBigDecimal("p_POTGENFPCONFAC",sumistro.getPotgenfpconfac());
			cst.setBigDecimal("p_POTGENFPPREUNI",sumistro.getPotgenfppreuni());
			cst.setBigDecimal("p_POTUSOREDDISTTOTAL",sumistro.getPotusoreddisttotal());
			cst.setBigDecimal("p_POTGENFPTOTAL",sumistro.getPotgenfptotal());

			cst.setDate("p_FECVENCIMIENTO", new Date(sumistro.getFecVencimiento().getTime()));
			cst.setDate("p_FECEMISION", new Date(sumistro.getFecEmision().getTime()));
			
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
			
			cst.setLong("p_COD_ORGRECIBO_LUZ", sumistro.getCodOrgreciboLuz());
			
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
				
				res.setRepomancnx(rs.getBigDecimal("REPOMANCNX"));
				res.setCargofijo(rs.getBigDecimal("CARGOFIJO"));
				res.setAlumpublic(rs.getBigDecimal("ALUMPUBLIC"));
				res.setSubtotalmes(rs.getBigDecimal("SUBTOTALMES"));
				res.setIgv(rs.getBigDecimal("IGV"));
				res.setTotalmesact(rs.getBigDecimal("TOTALMESACT"));
				res.setAporteley(rs.getBigDecimal("APORTELEY"));
				res.setCuotaconv(rs.getBigDecimal("CUOTACONV"));
				res.setRedonmesact(rs.getBigDecimal("REDONMESACT"));
				res.setRedonmesant(rs.getBigDecimal("REDONMESANT"));
				res.setInteresconvenio(rs.getBigDecimal("INTERESCONVENIO"));
				res.setEnergactfraptaactual(rs.getBigDecimal("ENERGACTFRAPTAACTUAL"));
				res.setEnergactfraptaanteri(rs.getBigDecimal("ENERGACTFRAPTAANTERI"));
				res.setEnergactfraptadifer(rs.getBigDecimal("ENERGACTFRAPTADIFER"));
				res.setEnergactfraptafactor(rs.getBigDecimal("ENERGACTFRAPTAFACTOR"));
				res.setEnergactfraptaconsu(rs.getBigDecimal("ENERGACTFRAPTACONSU"));
				res.setEnergactfraptaconfa(rs.getBigDecimal("ENERGACTFRAPTACONFA"));
				res.setEnergactfraptapreuni(rs.getBigDecimal("ENERGACTFRAPTAPREUNI"));
				res.setEnergactfraptatotal(rs.getBigDecimal("ENERGACTFRAPTATOTAL"));
				res.setEnergacthorptaactu(rs.getBigDecimal("ENERGACTHORPTAACTU"));
				res.setEnergacthorptaant(rs.getBigDecimal("ENERGACTHORPTAANT"));
				res.setEnergacthorptadif(rs.getBigDecimal("ENERGACTHORPTADIF"));
				res.setEnergacthorptafac(rs.getBigDecimal("ENERGACTHORPTAFAC"));
				res.setEnergacthorptacons(rs.getBigDecimal("ENERGACTHORPTACONS"));
				res.setEnergacthorptaconfac(rs.getBigDecimal("ENERGACTHORPTACONFAC"));
				res.setEnergacthorptapreuni(rs.getBigDecimal("ENERGACTHORPTAPREUNI"));
				res.setEnergacthorptatotal(rs.getBigDecimal("ENERGACTHORPTATOTAL"));
				res.setEnergreacinicial(rs.getBigDecimal("ENERGREACINICIAL"));
				res.setEnergreacanteri(rs.getBigDecimal("ENERGREACANTERI"));
				res.setEnergreacdifere(rs.getBigDecimal("ENERGREACDIFERE"));
				res.setEnergreacfactor(rs.getBigDecimal("ENERGREACFACTOR"));
				res.setEnergreacconsu(rs.getBigDecimal("ENERGREACCONSU"));
				res.setEnergreacfaccons(rs.getBigDecimal("ENERGREACFACCONS"));
				res.setEnergreacpreuni(rs.getBigDecimal("ENERGREACPREUNI"));
				res.setEnergreactotal(rs.getBigDecimal("ENERGREACTOTAL"));
				res.setPotenciafpini(rs.getBigDecimal("POTENCIAFPINI"));
				res.setPotenciafpante(rs.getBigDecimal("POTENCIAFPANTE"));
				res.setPotenciafpdif(rs.getBigDecimal("POTENCIAFPDIF"));
				res.setPotenciafpfac(rs.getBigDecimal("POTENCIAFPFAC"));
				res.setPotenciafpcons(rs.getBigDecimal("POTENCIAFPCONS"));
				res.setPotenciahpact(rs.getBigDecimal("POTENCIAHPACT"));
				res.setPotenciahpant(rs.getBigDecimal("POTENCIAHPANT"));
				res.setPotenciahpdif(rs.getBigDecimal("POTENCIAHPDIF"));
				res.setPotenciahpfac(rs.getBigDecimal("POTENCIAHPFAC"));
				res.setPotenciahpcons(rs.getBigDecimal("POTENCIAHPCONS"));
				res.setPotusoreddistconfac(rs.getBigDecimal("POTUSOREDDISTCONFAC"));
				res.setPotusoreddistpreuni(rs.getBigDecimal("POTUSOREDDISTPREUNI"));
				res.setPotusoreddisttotal(rs.getBigDecimal("POTUSOREDDISTTOTAL"));
				res.setPotgenfpconfac(rs.getBigDecimal("POTGENFPCONFAC"));
				res.setPotgenfppreuni(rs.getBigDecimal("POTGENFPPREUNI"));
				res.setPotgenfptotal(rs.getBigDecimal("POTGENFPTOTAL"));

				res.setFecVencimiento(rs.getDate("FECVENCIMIENTO"));
				res.setFecEmision(rs.getDate("FECEMISION"));

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
			cst.setBigDecimal("p_COD_ORGRECIBO_LUZ", new BigDecimal(codigoResOri));
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

    public ReciboluzOrg buscarRecibo(ReciboluzOrg sumistro) {
    	System.out.println("[SuministroLuzFacade] Inicio - buscarRecibo");
    	Connection connection = null;
    	CallableStatement cst = null;
    	ResultSet rs = null;
    	ReciboluzOrg res = null;
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_FIN_SUMISTROLUZORI);
			
			cst.registerOutParameter("LSTSUMISTROLUZORI", OracleTypes.CURSOR);
			cst.setBigDecimal("p_COD_ORGRECIBO_LUZ", new BigDecimal(sumistro.getCodOrgreciboLuz()));
			cst.execute();
			
			rs = (ResultSet) cst.getObject("LSTSUMISTROLUZORI");
			
			if(rs.next()){
				res = new ReciboluzOrg();
				res.setCodOrgreciboLuz(rs.getLong("COD_ORGRECIBO_LUZ"));
				res.setNumLecturaInicial(rs.getBigDecimal("NUM_LECTURA_INICIAL"));
				res.setNumLecturaFinal(rs.getBigDecimal("NUM_LECTURA_FINAL"));
				res.setNumMonto(rs.getBigDecimal("NUM_MONTO"));
				res.setNumCostoWats(rs.getBigDecimal("NUM_COSTO_WATS"));
				res.setFecPeriodo(rs.getDate("FEC_PERIODO"));
				res.setNumPendienteFac(rs.getBigDecimal("NUM_PENDIENTE_FAC"));
				res.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				res.setRepomancnx(rs.getBigDecimal("REPOMANCNX"));
				res.setCargofijo(rs.getBigDecimal("CARGOFIJO"));
				res.setEnergactfraptatotal(rs.getBigDecimal("ENERGACTFRAPTATOTAL"));
				res.setEnergacthorptatotal(rs.getBigDecimal("ENERGACTHORPTATOTAL"));
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
    	
    	System.out.println("[SuministroLuzFacade] Final - buscarRecibo");
    	return res;
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

	@Override
	public void grabarItemReciboLuzSocio(SuministroLusReciboSocio srs) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {

    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_INS_SUMINISTROLUZSOCIO);
			
			cst.setLong("p_CORRELATIVO",new Long(0));
			cst.setLong("p_CODIGOSOCIO",srs.getCodigosocio());
			cst.setLong("p_CODIGORECIBO",srs.getCodigorecibo());

			cst.setBigDecimal("p_LECTURAINI",srs.getLecturaini());
			cst.setBigDecimal("p_LECTURAFIN",srs.getLecturafin());
			cst.setBigDecimal("p_CONSUMOMES",srs.getConsumomes());
			cst.setBigDecimal("p_CAGOFIJO",srs.getCagofijo());
			cst.setBigDecimal("p_ALUPUBLIC",srs.getAlupublic());
			cst.setBigDecimal("p_CARGOENER",srs.getCargoener());
			cst.setBigDecimal("p_SUBTOTALMES",srs.getSubtotalmes());
			cst.setBigDecimal("p_IGV",srs.getIgv());
			cst.setBigDecimal("p_TOTALMES",srs.getTotalmes());
			cst.setBigDecimal("p_USOEQUIPO",srs.getUsoequipo());
			cst.setBigDecimal("p_SERVMANTO",srs.getServmanto());
			cst.setBigDecimal("p_APORTELEY",srs.getAporteley());
			cst.setBigDecimal("p_RECARGO",srs.getRecargo());
			cst.setBigDecimal("p_REDONDEO",srs.getRedondeo());
			cst.setBigDecimal("p_TOTAL",srs.getTotal());
			cst.setBigDecimal("P_DEUDAANT",srs.getDeudaant());
			
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
	public List<SuministroLusReciboSocio> listarItemReciboLuzSocio(Long codigo) {
		// TODO Auto-generated method stub SP_LIST_SUMINISTROLUZSOCIOS
		System.out.println("[SuministroLuzFacade] Inicio - listarItemReciboLuzSocio");
    	Connection connection = null;
    	CallableStatement cst = null;
		ResultSet rs;
		List<SuministroLusReciboSocio> lstSuministroLusReciboSocio = null;
		SuministroLusReciboSocio srs = null;
		try {
			connection = getConnection();
			lstSuministroLusReciboSocio = new ArrayList<SuministroLusReciboSocio>();
			cst = connection.prepareCall(SP_LIST_SUMINISTROLUZSOCIOS);
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.setLong(2, codigo);
			cst.execute();
			rs = (ResultSet) cst.getObject(1);
			
			DecimalFormat df = new DecimalFormat("#####.00");
			
			while(rs.next()){
				
				srs = new SuministroLusReciboSocio();
				srs.setNombres(rs.getString("NOMBRES"));
				srs.setLecturaini(rs.getBigDecimal("LECTURAINI"));
				srs.setLecturafin(rs.getBigDecimal("LECTURAFIN"));
				srs.setConsumomes(rs.getBigDecimal("CONSUMOMES"));
				srs.setCagofijo(rs.getBigDecimal("CAGOFIJO"));
				srs.setAlupublic(rs.getBigDecimal("ALUPUBLIC"));
				srs.setCargoener(rs.getBigDecimal("CARGOENER")); //Es como se presentaba anteriormente, sin decimales
				srs.setTotalmes(rs.getBigDecimal("TOTALMES"));
				srs.setIgv(rs.getBigDecimal("IGV"));
				srs.setSubtotalmes(rs.getBigDecimal("SUBTOTALMES"));
				srs.setUsoequipo(rs.getBigDecimal("USOEQUIPO"));
				srs.setServmanto(rs.getBigDecimal("SERVMANTO"));
				srs.setAporteley(rs.getBigDecimal("APORTELEY"));
				srs.setRecargo(rs.getBigDecimal("RECARGO"));
				srs.setRedondeo(rs.getBigDecimal("REDONDEO"));
				srs.setTotal(rs.getBigDecimal("TOTAL"));
				srs.setCorrelativo(rs.getLong("CORRELATIVO"));
				srs.setCodigosocio(rs.getLong("CODIGOSOCIO"));
				srs.setCodigorecibo(rs.getLong("CODIGORECIBO"));
				srs.setEstado(rs.getBigDecimal("ESTADO"));
				srs.setDeudaant(rs.getBigDecimal("DEUDAANT"));
				srs.setFechacarga(rs.getTimestamp("FECHACARGA"));
				srs.setPuesto(rs.getBigDecimal("TRAN_PUESTO"));
				srs.setImpreso(rs.getBigDecimal("IMPRESO"));
				lstSuministroLusReciboSocio.add(srs);
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
		
		System.out.println("[SuministroLuzFacade] Final - listarItemReciboLuzSocio");
		return lstSuministroLusReciboSocio; 
	}

	@Override
	public void eliminarItemReciboLuzSocio(Long correlativo, Long codigoSocio,
			Long codigoRecibo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_DEL_SUMINISTROLUZSOCIO);
			
			cst.setLong("p_CORRELATIVO", correlativo);
			cst.setLong("p_CODIGOSOCIO", codigoSocio);
			cst.setLong("p_CODIGORECIBO", codigoRecibo);
			
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
	public void actualizarItemReciboLuzSocio(SuministroLusReciboSocio srs) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {

    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_SUMINISTROLUZSOCIO);
			
			cst.setLong("p_CORRELATIVO",srs.getCorrelativo());
			cst.setLong("p_CODIGOSOCIO",srs.getCodigosocio());
			cst.setLong("p_CODIGORECIBO",srs.getCodigorecibo());

			cst.setBigDecimal("p_LECTURAINI",srs.getLecturaini());
			cst.setBigDecimal("p_LECTURAFIN",srs.getLecturafin());
			cst.setBigDecimal("p_CONSUMOMES",srs.getConsumomes());
			cst.setBigDecimal("p_CAGOFIJO",srs.getCagofijo());
			cst.setBigDecimal("p_ALUPUBLIC",srs.getAlupublic());
			cst.setBigDecimal("p_CARGOENER",srs.getCargoener());
			cst.setBigDecimal("p_SUBTOTALMES",srs.getSubtotalmes());
			cst.setBigDecimal("p_IGV",srs.getIgv());
			cst.setBigDecimal("p_TOTALMES",srs.getTotalmes());
			cst.setBigDecimal("p_USOEQUIPO",srs.getUsoequipo());
			cst.setBigDecimal("p_SERVMANTO",srs.getServmanto());
			cst.setBigDecimal("p_APORTELEY",srs.getAporteley());
			cst.setBigDecimal("p_RECARGO",srs.getRecargo());
			cst.setBigDecimal("p_REDONDEO",srs.getRedondeo());
			cst.setBigDecimal("p_TOTAL",srs.getTotal());
			cst.setBigDecimal("p_DEUDAANT",srs.getDeudaant());
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
	public void pagarItemReciboLuzSocio(Long correlativo, Long codigoSocio, Long codigoRecibo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_SUMINISTROLUZSOCIOPAGAR);
			
			cst.setLong("p_CORRELATIVO", correlativo);
			cst.setLong("p_CODIGOSOCIO", codigoSocio);
			cst.setLong("p_CODIGORECIBO", codigoRecibo);
			
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
	public void imprimirItemReciboLuzSocio(Long correlativo, Long codigoSocio, Long codigoRecibo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_SUMINISTROLUZSOCIOIMPRIMIR);
			
			cst.setLong("p_CORRELATIVO", correlativo);
			cst.setLong("p_CODIGOSOCIO", codigoSocio);
			cst.setLong("p_CODIGORECIBO", codigoRecibo);
			
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

	
	public List<SuministroLusReciboSocio> buscarReciboxCodigo(String codSocio) {
    	Connection connection = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	List<SuministroLusReciboSocio> lsSuministroLuzSocio = null;
    	SuministroLusReciboSocio reciboSocio = null;
		try {
			connection = getConnection();
			lsSuministroLuzSocio = new ArrayList<SuministroLusReciboSocio>();
			pst = connection.prepareStatement(String.format(view_buscar_recibo_socio, "'"+ codSocio.trim()+"'")); //Integer.parseInt(codSocio.trim())+"'"));
			rs = pst.executeQuery();
			
			while(rs.next()){
				reciboSocio = new SuministroLusReciboSocio();
				reciboSocio.setCodigosocio(rs.getLong("codigosocio"));
				reciboSocio.setCodigorecibo(rs.getLong("codigorecibo"));
				reciboSocio.setLecturaini(new BigDecimal(rs.getString("lecturaini")));
				reciboSocio.setLecturafin(new BigDecimal(rs.getString("lecturafin")));
				reciboSocio.setTotal(new BigDecimal(rs.getString("total")));
				reciboSocio.setEstado(new BigDecimal(rs.getString("estado")));
				reciboSocio.setDeudaant(new BigDecimal(rs.getString("deudaant")));
				reciboSocio.setFechacarga(rs.getTimestamp("fechacarga"));
				
				lsSuministroLuzSocio.add(reciboSocio);
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
		
		return lsSuministroLuzSocio;
		
	}

	
	public int buscarReciboxCodigoxSocio(String codRecibo, String codSocio) {
    	Connection connection = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	
    	int rpta=0;
    	
    	List<SuministroLusReciboSocio> lsSuministroLuzSocio = null;
    	SuministroLusReciboSocio reciboSocio = null;
		try {
			connection = getConnection();
			lsSuministroLuzSocio = new ArrayList<SuministroLusReciboSocio>();
			pst = connection.prepareStatement(String.format(view_buscar_recibo_socio, "'"+ codSocio.trim()+ "' AND codigorecibo = '"+ codRecibo.trim() +"' "));
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

	public int buscarReciboxCodigoxSocioxPeriodo(String periodo, String codSocio) {
    	Connection connection = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	
    	int rpta=0;
    	
    	List<SuministroLusReciboSocio> lsSuministroLuzSocio = null;
    	SuministroLusReciboSocio reciboSocio = null;
		try {
			connection = getConnection();
			lsSuministroLuzSocio = new ArrayList<SuministroLusReciboSocio>();
			pst = connection.prepareStatement(String.format(view_buscar_recibo_socio, "'"+ codSocio.trim()+ "' AND fec_periodo = add_months(to_date('"+ periodo.trim() +"'),-1)"));
			rs = pst.executeQuery();
			if(!rs.next()){
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
	
	public List<SuministroLusReciboSocio> buscarReciboLuzxCodigoSocio(String codSocio, String codRecibo) {
		System.out.println("[SuministroLuzFacade] Inicio - buscarReciboLuzxCodigoSocio");
		Connection connection = null;
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	List<SuministroLusReciboSocio> lsSuministroLuzSocio = null;
    	SuministroLusReciboSocio reciboSocio = null;
		try {
			connection = getConnection();
			lsSuministroLuzSocio = new ArrayList<SuministroLusReciboSocio>();
			//pst = connection.prepareStatement(String.format(view_buscar_recibo_socio, "'"+ codSocio.trim()+"'")); //Integer.parseInt(codSocio.trim())+"'"));
			pst = connection.prepareStatement(String.format(view_buscar_recibo_luzsocio, "'"+ codSocio.trim()+ "' AND codigorecibo = '"+ codRecibo.trim() +"' "));
			rs = pst.executeQuery();
			
			System.out.println("[SuministroLuzFacade] QUERY : " + view_buscar_recibo_luzsocio + " " + codSocio.trim()+ "' AND codigorecibo = '"+ codRecibo.trim());
			
			while(rs.next()){
				reciboSocio = new SuministroLusReciboSocio();
				reciboSocio.setCodigosocio(rs.getLong("codigosocio"));
				reciboSocio.setCodigorecibo(rs.getLong("codigorecibo"));
				reciboSocio.setLecturaini(new BigDecimal(rs.getString("lecturaini")));
				reciboSocio.setLecturafin(new BigDecimal(rs.getString("lecturafin")));
				reciboSocio.setTotal(new BigDecimal(rs.getString("total")));
				reciboSocio.setEstado(new BigDecimal(rs.getString("estado")));
				reciboSocio.setDeudaant(new BigDecimal(rs.getString("deudaant")));
				reciboSocio.setFechacarga(rs.getTimestamp("fechacarga"));
				
				lsSuministroLuzSocio.add(reciboSocio);
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
		
		System.out.println("[SuministroLuzFacade] Final - buscarReciboLuzxCodigoSocio");
		return lsSuministroLuzSocio;
		
	}
	
}
