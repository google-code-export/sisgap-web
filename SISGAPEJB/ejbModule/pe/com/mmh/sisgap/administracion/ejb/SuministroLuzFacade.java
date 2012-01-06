/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ItemSumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.SumistroLuzDet;

/**
 *
 * @author ANDREA
 */
@Stateless
public class SuministroLuzFacade implements SuministroLuzFacadeLocal {
	
	private static final String SP_INS_FACTURA = "{call PKG_ADMINISTRACION.SP_INS_FACTURA(?,?,?,?,?)}";
	private static final String SP_INS_DETFACTURA = "{call PKG_ADMINISTRACION.SP_INS_DETFACTURA(?,?,?,?,?,?,?,?)}";
	private static final String SP_DEL_FACTURA = "{call PKG_ADMINISTRACION.SP_DEL_FACTURA(?)}";
	private static final String SP_LST_GENERARNRODOC = "{call PKG_ADMINISTRACION.SP_LST_GENERARNRODOC(?,?)}";
	private static final String SP_UPD_FACTURAANULADA = "{call PKG_ADMINISTRACION.SP_UPD_FACTURAANULADA(?,?)}";
	
	@Resource(mappedName="java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
    @PersistenceContext
    private EntityManager em;

	@Override
	public void create(SumistroLuz sumistro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(SumistroLuz sumistro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(SumistroLuz sumistro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SumistroLuz find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SumistroLuz> findAll() {
		// TODO Auto-generated method stub
		return null;
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
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_LST_GENERARNRODOC);
			
			cst.setString("P_STR_TIPODOC", "");
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
		return itemSumistroLuzs;
	}

//    public void create(Factura factura) {
//        em.persist(factura);
//    }
//
//    public void edit(Factura factura) {
//        em.merge(factura);
//    }
//
//    public void remove(Factura factura) {
//		// TODO Auto-generated method stub
//    	Connection connection = null;
//    	CallableStatement cst = null;
//    	
//    	try {
//    		
//    		connection = getConnection();    		
//			cst = connection.prepareCall(SP_DEL_FACTURA);			
//			cst.setLong("P_COD_FACTURA", factura.getCodFactura());
//			cst.execute();		
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{			
//				try {
//					if(cst!=null){cst.close();}
//					if(connection!=null){connection.close();}					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//    }
//
//    public Factura find(Object id) {
//        return em.find(Factura.class, id);
//    }
//
//    public List<Factura> findAll() {
//        return em.createQuery("select object(o) from Factura as o").getResultList();
//    }
//
//	@Override
//	public void grebarFactura(Long numerodocumento, String totalfac, String codigoide,
//			String cbtipodoc, Set<Detallefactura> detallefactura) {
//		// TODO Auto-generated method stub
//    	Connection connection = null;
//    	CallableStatement cst = null;
//    	
//    	try {
//    		
//    		connection = getConnection();
////    		connection.setAutoCommit(false);
//    		
//			cst = connection.prepareCall(SP_INS_FACTURA);
//			
//			cst.setLong("NUMERO_FAC", new Long(numerodocumento));
//			cst.setBigDecimal("P_TRAN_IDE", new BigDecimal(codigoide));
//			cst.setBigDecimal("P_NUM_TOTAL", new BigDecimal(totalfac));
//			cst.setString("P_STR_TIPODOC", cbtipodoc);
//			cst.registerOutParameter("P_CODIGO_FAC", Types.DECIMAL);
//			cst.execute();
//			
//			BigDecimal codigo = cst.getBigDecimal("P_CODIGO_FAC");
//			
//			cst = connection.prepareCall(SP_INS_DETFACTURA);
//			
//			for (Detallefactura det : detallefactura) {
//				
//			
//				
//				cst.setLong("P_COD_ITEMCOBRANZA", det.getId().getCodItemcobranza());
//				cst.setBigDecimal("P_COD_FACTURA", codigo);				
//				cst.setBigDecimal("P_NUM_COSTO", det.getNumCosto());				
//				cst.setString("P_STR_DESCRIPCION", det.getStrDescripcion());				
//				cst.setString("P_STR_TIPOCOBRANZA", det.getStrTipocobranza());
//				cst.setString("P_STR_MONEDA", det.getStrMoneda());
//				cst.setBigDecimal("P_NUM_CANTIDAD", det.getNumCantidad());
//				cst.setBigDecimal("P_NUM_ACUENTA", det.getNumAcuenta());		
//				
//				
//				cst.execute();
//				
//			}
//		
//			
//		} catch (Exception e) {
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally{			
//				try {
//					if(cst!=null){cst.close();}
//					if(connection!=null){connection.close();}					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//	}
//
	private Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//
//	@Override
//	public BigDecimal generarNrodocumento(String tipodoc) {
//		// TODO Auto-generated method stub
//		// TODO Auto-generated method stub
//    	Connection connection = null;
//    	CallableStatement cst = null;
//    	BigDecimal codigo = new BigDecimal(0);
//    	
//    	try {
//    		
//    		connection = getConnection();
//    		
//			cst = connection.prepareCall(SP_LST_GENERARNRODOC);
//			
//			cst.setString("P_STR_TIPODOC", tipodoc);
//			cst.registerOutParameter("P_COD_FACTURA", Types.DECIMAL);
//			cst.execute();
//			
//			codigo = cst.getBigDecimal("P_COD_FACTURA");
//			
//		
//			
//		} catch (Exception e) {
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally{			
//				try {
//					if(cst!=null){cst.close();}
//					if(connection!=null){connection.close();}					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//		return codigo;
//	}
//
//	@Override
//	public void anularFactura(String codigoFactura, String descripanulada) {
//		// TODO Auto-generated method stub
//    	Connection connection = null;
//    	CallableStatement cst = null;
//
//    	
//    	try {
//    		
//    		connection = getConnection();
//    		
//			cst = connection.prepareCall(SP_UPD_FACTURAANULADA);
//			
//			cst.setLong("P_COD_FACTURA", new Long(codigoFactura));
//			cst.setString("P_STR_DESC", descripanulada);
//			cst.execute();
//
//		} catch (Exception e) {
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally{			
//				try {
//					if(cst!=null){cst.close();}
//					if(connection!=null){connection.close();}					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//	}
}
