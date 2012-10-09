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

import pe.com.mmh.sisgap.domain.ServicioDetalle;
import pe.com.mmh.sisgap.domain.Servicios;
import pe.com.mmh.sisgap.utils.JDBCUtil;

@Stateless
public class ServiciosHigienicosFacade implements ServiciosHigienicosFacadeLocal {
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;

	private static final String SP_INS_SERVICIO = "{call PKG_ADMINISTRACION.SP_INS_SERVICIO(?,?,?,?,?,?,?)}";
	private static final String SP_INS_DETSERVICIO = "{call PKG_ADMINISTRACION.SP_INS_DETSERVICIO(?,?,?,?,?,?,?,?,?,?)}";
	private static final String SP_UPD_DELSERVICIO = "{call PKG_ADMINISTRACION.SP_UPD_DELSERVICIO(?)}";
	
    /* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.SeFacadeLocal#mostrarPlatilla(java.lang.String)
	 */	
    @PersistenceContext
    private EntityManager em;

    public void create(Servicios servicios) {
        em.persist(servicios);
    }

    public void edit(Servicios servicios) {
        em.merge(servicios);
    }

    public Servicios find(Object id) {
        return em.find(Servicios.class, id);
    }
	
    /*
     * (non-Javadoc)
     * @see pe.com.mmh.sisgap.administracion.ejb.ServiciosHigienicosFacadeLocal#findAll()
     * by 6 Fecha Servicio
     * by 7 Descripcion
     */
	public List<Servicios> findAll() {
		return em.createQuery("select object(o) from Servicios as o order by DAT_FECHASERV desc, STR_DESCRIPCION asc").getResultList();
	}

    /*public ServicioDetalle findSrvDet(Object id){
	return em.find(ServicioDetalle.class, id);
	}*/
	
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

	public void grabarServicios(Long codServicio, String descripcion, BigDecimal montototal, String fechadoc, List<ServicioDetalle> serviciodetalle) {
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
				//cst.setBigDecimal("P_COD_SERVICIOITEM", det.getCodServicioitem());
				cst.setBigDecimal("P_COD_SERVICIOITEM", det.getCodServiciodetalle());
				cst.setBigDecimal("P_COD_SERVICIO", codigo);				
				cst.setBigDecimal("P_NUM_COSTO", det.getNumCosto());
				cst.setBigDecimal("P_NUM_CANTIDAD", det.getNumCantidad());
				cst.setBoolean("P_NUM_ESTADO", det.getNumEstado());
				cst.setString("P_STR_MONEDA", det.getStrMoneda());
				cst.setBigDecimal("P_NUM_DEL", det.getNumDel());
				cst.setBigDecimal("P_NUM_AL", det.getNumAl());
				cst.registerOutParameter("P_COD_SERVICIO_DETALLE", Types.DECIMAL);
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

	@Override
	public void anularServicio(String codigoServicio) {
		// TODO Auto-generated method stub
    	Connection connection = null;
    	CallableStatement cst = null;

    	
    	try {
    		
    		connection = getConnection();
    		
			cst = connection.prepareCall(SP_UPD_DELSERVICIO);
			
			cst.setLong("P_COD_SERVICIO", new Long(codigoServicio));
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

	

}
