package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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

import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ServicioDetalle;
import pe.com.mmh.sisgap.domain.Servicios;
import pe.com.mmh.sisgap.utils.JDBCUtil;

@Stateless
public class ServiciosHigienicosFacade implements ServiciosHigienicosFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	
	private static final String SP_LST_SSHH = "{call PKG_ADMINISTRACION.SP_LST_SSHH(?)}";
	private static final String SP_INS_SERVICIO = "{call PKG_ADMINISTRACION.SP_INS_SERVICIO(?,?,?,?,?,?,?)}";
	private static final String SP_INS_DETSERVICIO = "{call PKG_ADMINISTRACION.SP_INS_DETSERVICIO(?,?,?,?,?,?,?,?,?)}";
	
    /* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.SeFacadeLocal#mostrarPlatilla(java.lang.String)
	 */	
    @PersistenceContext
    private EntityManager em;

    public void create(Factura factura) {
        em.persist(factura);
    }

    public void edit(Factura factura) {
        em.merge(factura);
    }

	
	
    public Servicios find(Object id) {
        return em.find(Servicios.class, id);
    }
	
	@Override
	public List<Servicios> findAll() {
    	/*Connection connection = null;
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
				serv.setDatFechaserv(rs.getDate("datFechaserv"));
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
    
    	return lst;*/
		return em.createQuery("select object(o) from Servicios as o").getResultList();
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

	public void grabarFactura(Long codServicio, String descripcion, BigDecimal montototal, String fechadoc, List<ServicioDetalle> serviciodetalle) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;
    	
    	try {
    		
    		connection = getConnection();
//    		connection.setAutoCommit(false);
    		
			cst = connection.prepareCall(SP_INS_SERVICIO);
			
			cst.setBigDecimal("P_NRO_SERVICIO", new BigDecimal(0));
			cst.setBigDecimal("P_NUM_TOTAL", montototal);
			cst.setString("P_STR_DESCRIPCION", descripcion);
			cst.setBigDecimal("P_NUM_ESTADO", new BigDecimal(1));
			cst.setString("P_DAT_FECHASERV", fechadoc);
			cst.setString("P_STR_MONEDA", "S");
			cst.registerOutParameter("P_CODIGO_SRV", Types.DECIMAL);
			cst.execute();
			
			BigDecimal codigo = cst.getBigDecimal("P_CODIGO_SRV");
			
			cst = connection.prepareCall(SP_INS_DETSERVICIO);
			
			for (ServicioDetalle det : serviciodetalle) {
				
				cst.setBigDecimal("P_COD_SERVICIODETALLE", det.getCodServiciodetalle());
				cst.setBigDecimal("P_COD_SERVICIOITEM", det.getCodServicioitem());
				cst.setBigDecimal("P_COD_SERVICIO", codigo);				
				cst.setBigDecimal("P_NUM_COSTO", det.getNumCosto());
				cst.setBigDecimal("P_NUM_CANTIDAD", det.getNumCantidad());
				cst.setBoolean("P_NUM_ESTADO", det.getNumEstado());
				cst.setString("P_STR_MONEDA", det.getStrMoneda());
				cst.setBigDecimal("P_NUM_DEL", det.getNumDel());
				cst.setBigDecimal("P_NUM_AL", det.getNumAl());
				cst.execute();
				
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
	}



}
