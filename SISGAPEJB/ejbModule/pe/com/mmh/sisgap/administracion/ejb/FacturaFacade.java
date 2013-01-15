/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.xml.rpc.Call;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.SisgapReuniones;
import pe.com.mmh.sisgap.domain.Socio;
import pe.com.mmh.sisgap.domain.Vigilancia;

/**
 *
 * @author ANDREA
 */
@Stateless
public class FacturaFacade implements FacturaFacadeLocal {
	
	private static final String SP_INS_FACTURA = "{call PKG_ADMINISTRACION.SP_INS_FACTURA(?,?,?,?,?,?,?)}";
	private static final String SP_INS_DETFACTURA = "{call PKG_ADMINISTRACION.SP_INS_DETFACTURA(?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_FACTURA = "{call PKG_ADMINISTRACION.SP_DEL_FACTURA(?)}";
	private static final String SP_LST_GENERARNRODOC = "{call PKG_ADMINISTRACION.SP_LST_GENERARNRODOC(?,?)}";
	private static final String SP_UPD_FACTURAANULADA = "{call PKG_ADMINISTRACION.SP_UPD_FACTURAANULADA(?,?)}";
	private static final String SP_UPD_FACTURACANCELADA = "{call PKG_ADMINISTRACION.SP_UPD_FACTURACANCELADA(?)}";
	private static final String SP_LST_FACTURA = "{call PKG_ADMINISTRACION.SP_LST_FACTURA(?,?)}";
	private static final String SP_UPD_FACTURAIMPRESA = "{call PKG_ADMINISTRACION.SP_UPD_FACTURAIMPRESA(?,?)}";
	private static final String SP_UPD_NROFACTURASOCIO = "{call PKG_ADMINISTRACION.SP_UPD_NROFACTURASOCIO(?,?)}";
	private static final String SP_EDIT_ITEMFACTURA = "{call PKG_ADMINISTRACION.SP_EDIT_ITEMFACTURA(?,?)}";
	
	@Resource(mappedName="java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
    @PersistenceContext
    private EntityManager em;

    public void create(Factura factura) {
        em.persist(factura);
    }

    public void edit(Factura factura) {
        em.merge(factura);
    }

    public void remove(Factura factura) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();    		
			cst = connection.prepareCall(SP_DEL_FACTURA);			
			cst.setLong("P_COD_FACTURA", factura.getCodFactura());
			cst.execute();		
			
		} catch (Exception e) {
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

    public Factura find(Object id) {
        return em.find(Factura.class, id);
    }

    public List<Factura> findAlls() {
    	
    	// Ordenando por tipo documento (1).
    	// Ordenando por nro doc interno (2).
    	// Ordenando por socio (3).
    	// Ordenando por puesto (4).
    	// Ordenando por actividad (5).
    	// Ordenando por fecha de creacion (6).
    	// Ordenando por total (7).
    	// Ordenando por estado (8).
        return em.createQuery("select object(o) from Factura as o order by 6 desc").getResultList();
//    	return facturas;
    }
    
    public List<Factura> findAll(Long estado) {
    	
    	// Ordenando por tipo documento (1).
    	// Ordenando por nro doc interno (2).
    	// Ordenando por socio (3).
    	// Ordenando por puesto (4).
    	// Ordenando por actividad (5).
    	// Ordenando por fecha de creacion (6).
    	// Ordenando por total (7).
    	// Ordenando por estado (8).
        ////return em.createQuery("select object(o) from Factura as o order by 6 desc").getResultList();
//    	return facturas;
    	Connection connection = null;
    	CallableStatement cst = null;
    	ResultSet rs;
    	Factura srs = null;
    	List<Factura> lstFac = new  ArrayList<Factura>();
    	try {
    		connection = getConnection();
    		cst = connection.prepareCall(SP_LST_FACTURA);
    		
    		cst.registerOutParameter("LSTFACTURA", OracleTypes.CURSOR);
    		cst.setLong("P_ESTADO", estado);
			cst.execute();
			
			rs = (ResultSet) cst.getObject("LSTFACTURA");            			
			
			while (rs.next()) {
				srs = new Factura();
				srs.setCodFactura(rs.getLong("COD_FACTURA"));
				srs.setStrTipodoc(rs.getString("STR_TIPODOC"));
				srs.setNumNrodoc(rs.getBigDecimal("NUM_NRODOC"));
				srs.setNroFactura(rs.getString("NRO_FACTURA"));
				srs.setNombresocio(rs.getString("SOCIO"));
				srs.setTranPuesto(rs.getString("PUESTO"));
				srs.setActiTranNombre(rs.getString("ACTIVIDAD"));
				srs.setDatFechacred(rs.getDate("DAT_FECHACRED"));
				srs.setNumTotal(rs.getBigDecimal("NUM_TOTAL"));
				srs.setNumEstado(rs.getBigDecimal("NUM_ESTADO"));
				lstFac.add(srs);
			}
    		
    	} catch (Exception e) {
    		
    	}
    	
    	return lstFac;
    }

    
    
	@Override
	public void grebarFactura(Long numerodocumento, String fechadocumento, String totalfac, String codigoide,
			String cbtipodoc, List<Detallefactura> detallefactura, String acuenta) {
		// TODO Auto-generated method stub
		System.out.println("[FacturaFacade] Inicio - grebarFactura");
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
//    		connection.setAutoCommit(false);
    		
			cst = connection.prepareCall(SP_INS_FACTURA);
			
			cst.setLong("NUMERO_FAC", new Long(numerodocumento));
			//cst.setString("NUMERO_FAC", numerodocumento);
			cst.setString("FECHA_FAC", fechadocumento);
			cst.setBigDecimal("P_TRAN_IDE", new BigDecimal(codigoide));
			cst.setBigDecimal("P_NUM_TOTAL", new BigDecimal(totalfac));
			cst.setString("P_STR_TIPODOC", cbtipodoc);
			cst.setBigDecimal("P_NRO_ACTA", new BigDecimal(acuenta));
			cst.registerOutParameter("P_CODIGO_FAC", Types.DECIMAL);
			cst.execute();
			
			BigDecimal codigo = cst.getBigDecimal("P_CODIGO_FAC");
			
			if (!(Integer.valueOf(codigo.toString())==0)){
				cst = connection.prepareCall(SP_INS_DETFACTURA);
				for (Detallefactura det : detallefactura) {
					cst.setLong("P_COD_ITEMCOBRANZA", det.getId().getCodItemcobranza());
					cst.setBigDecimal("P_COD_FACTURA", codigo);				
					cst.setBigDecimal("P_NUM_COSTO", det.getNumCosto());				
					cst.setString("P_STR_DESCRIPCION", det.getStrDescripcion());				
					cst.setString("P_STR_TIPOCOBRANZA", det.getStrTipocobranza());
					cst.setString("P_STR_MONEDA", det.getStrMoneda());
					cst.setBigDecimal("P_NUM_CANTIDAD", det.getNumCantidad());
					cst.setBigDecimal("P_NUM_ACUENTA", det.getNumAcuenta());		
					cst.execute();
				}
			}
		
		System.out.println("[FacturaFacade] Final - grebarFactura");
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
	public void actualizaNroFactura(String nroDocReal, String nroDocInte) {
		System.out.println("[FacturaFacade] Inicio - actualizaNroFactura");
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
//    		connection.setAutoCommit(false);
    		
			cst = connection.prepareCall(SP_UPD_NROFACTURASOCIO);
			
			cst.setInt("P_NRO_REAL", Integer.parseInt(nroDocReal));
			cst.setInt("P_COD_FACTURA", Integer.parseInt(nroDocInte));
			cst.execute();
			System.out.println("[FacturaFacade] P_Nro_Real : "+nroDocReal+" - P_COD_FACTURA : "+nroDocInte);
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
		System.out.println("[FacturaFacade] Final - actualizaNroFactura");
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
	public BigDecimal generarNrodocumento(String tipodoc) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	BigDecimal codigo = new BigDecimal(0);
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_LST_GENERARNRODOC);
			
			cst.setString("P_STR_TIPODOC", tipodoc);
			cst.registerOutParameter("P_COD_FACTURA", Types.DECIMAL);
			cst.execute();
			
			codigo = cst.getBigDecimal("P_COD_FACTURA");
			
		
			
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
		return codigo;
	}

	@Override
	public void anularFactura(String codigoFactura, String descripanulada) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;

    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_FACTURAANULADA);
			
			cst.setLong("P_COD_FACTURA", new Long(codigoFactura));
			cst.setString("P_STR_DESC", descripanulada);
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
	public void cancelarFactura(String codigoFactura) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;

    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_FACTURACANCELADA);
			
			cst.setLong("P_COD_FACTURA", new Long(codigoFactura));
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
	public void impresaFactura(String codigoFactura, Long nroFactura) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_FACTURAIMPRESA);
			
			cst.setLong("P_COD_FACTURA", new Long(codigoFactura));
			cst.setLong("P_NRO_FACTURA", new Long(nroFactura));
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
	public List<Detallefactura> editarDetalle(Long codigoFact, Long codigoItem) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	ResultSet rs;
    	Factura fac = null;
    	List<Detallefactura> lstDetFac = new  ArrayList<Detallefactura>();
    	try {    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_EDIT_ITEMFACTURA);
			cst.setLong("P_COD_ITEM", new Long(codigoFact));
			cst.setLong("P_COD_FACT", new Long(codigoItem));
			cst.registerOutParameter("LSTDETALLE", OracleTypes.CURSOR);
			cst.execute();
			
			rs = (ResultSet) cst.getObject("LSTVIGILANCIA");
			
			while (rs.next()) {
				
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
    	
    	return lstDetFac;
	}

}
