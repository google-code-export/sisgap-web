package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.ServicioItem;
import pe.com.mmh.sisgap.domain.Itemcobranza;



@Stateless
public class ServiciosItemFacade implements ServiciosItemFacadeLocal {
	
	private static final String view_buscar_item_servicio = "select * from view_buscar_item_servicio where cod_servicioitem like %s ";
	//private static final String SP_LST_SERVICIOITEM = "{call PKG_ADMINISTRACION.SP_LST_SERVICIOITEM(?)}";
	//private static final String SP_DEL_ITEMCOB = "{call PKG_ADMINISTRACION.SP_DEL_ITEMCOB(?)}";
	
	@Resource(mappedName = "java:/jdbc/sisgapDS")
	private DataSource dataSource;
	
    @PersistenceContext
    private EntityManager em;

    public void create(ServicioItem serviciositem) {
        em.persist(serviciositem);
    }
	
	/* (non-Javadoc)
	 * @see pe.com.mmh.sisgap.administracion.ejb.SeFacadeLocal#mostrarPlatilla(java.lang.String)
	 */
	
    public ServicioItem find(Object id) {
        return em.find(ServicioItem.class, id);
    }

    public List<ServicioItem> findAll() {
    	return em.createQuery("select object(o) from ServicioItem as o").getResultList();
        /*
         * return em.createQuery("select object(o) from Itemcobranza as o where o.numCodItemPadre = 1000").getResultList();
         */
    }
	
    public List<ServicioItem> buscarxNombre(BigDecimal codigo){
    	Connection connection = null;
		PreparedStatement pst;
		ResultSet rs;
		List<ServicioItem> lsItemServicio = null;
		ServicioItem itemservicio = null;
		try {
			connection = getConnection();
			lsItemServicio = new ArrayList<ServicioItem>();
			pst=connection.prepareStatement(String.format(view_buscar_item_servicio, "'"+codigo+"'"));
			rs = pst.executeQuery();
			
			while(rs.next()){
				//     COD_SERVICIOITEM,STR_DESCRIPCION,NUM_COSTO,NUM_ESTADO,DAT_FECHACREA
				itemservicio = new ServicioItem();
				itemservicio.setCodServicioitem(rs.getBigDecimal("COD_SERVICIOITEM"));
				itemservicio.setStrDescripcion(rs.getString("STR_DESCRIPCION"));
				itemservicio.setNumCosto(rs.getBigDecimal("NUM_COSTO"));
				itemservicio.setNumEstado(rs.getBoolean("NUM_ESTADO"));
				itemservicio.setDatFechacrea(rs.getDate("DAT_FECHACREA"));
				lsItemServicio.add(itemservicio);
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
		
		return lsItemServicio;    	
    }

    
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
