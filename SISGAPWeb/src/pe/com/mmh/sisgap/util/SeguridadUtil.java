package pe.com.mmh.sisgap.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.springframework.dao.DataAccessException;

import pe.com.mmh.sisgap.comun.constantes.ConstantesSesion;
import pe.com.mmh.sisgap.seguridad.action.LoginAction;
import pe.com.mmh.sisgap.seguridad.domain.UserInfo;
import pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema;


public class SeguridadUtil {		

	public static UserInfo obtenerUsuarioSesion(HttpServletRequest request) {		
		UserInfo usuario;
		HttpSession session;
		
		session = request.getSession();
		if(session != null) {
			usuario = (UserInfo) session.getAttribute(ConstantesSesion.USR_SESION);
			if(usuario != null) {
				return usuario;
			}
		}
		return new UserInfo();		
	}

	public static boolean isPaginaInicio(Action action) {
		boolean isPaginaInicio = true;
		if(!(action instanceof LoginAction)) {		
			isPaginaInicio = false;
		}		
		return isPaginaInicio;
	}
//	
//	/**
//	 * Obtiene una conexión directa desde del datasource utilizado en los reportes
//	 * @return
//	 * @throws DataAccessException
//	 * @throws SQLException
//	 * @throws NamingException
//	 */
	public static Connection getDataSouceConnection() throws DataAccessException, SQLException, NamingException{	    
        Context ctx = new InitialContext();
        DataSource ds =(DataSource) ctx.lookup("java:/jdbc/sisgapDS");
        Connection connection = ds.getConnection();                     
        return connection;
	}
		
}
