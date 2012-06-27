package pe.com.mmh.sisgap.administracion.ejb;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import pe.com.mmh.sisgap.domain.ServicioItem;



@Stateless
public class ServiciosItemFacade implements ServiciosItemFacadeLocal {
	
	//private static final String SP_LST_SERVICIOITEM = "{call PKG_ADMINISTRACION.SP_LST_SERVICIOITEM(?)}";
	
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
    }
	
    /*
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
*/
}
