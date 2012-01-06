
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

//import pe.com.equifax.paramweb.comun.constantes.Constantes;
//import pe.com.equifax.paramweb.comun.constantes.ConstantesSesion;
//import pe.com.equifax.paramweb.core.domain.Accion;
//import pe.com.equifax.paramweb.core.domain.Auditoria;
//import pe.com.equifax.paramweb.factory.ServiceFactory;
//import pe.com.equifax.paramweb.seguridad.action.LoginAction;
//import pe.com.equifax.paramweb.seguridad.domain.UserInfo;
//import pe.com.equifax.paramweb.system.propiedades.PropiedadesSistema;
//import pe.com.equifax.paramweb.util.json.JSONObject;

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
//	public static String obtenerCodigoUsuarioSesion(HttpServletRequest request) {		
//		return obtenerUsuarioSesion(request).getCodPersonal();		
//	}	
//	
//	
//	public static boolean existeSessionAjax(HttpServletRequest request,HttpServletResponse response){
//		if(request.getAttribute("expireSession")!=null){
//		    JSONObject jsonObj=new JSONObject();    
//		    try{
//			jsonObj.put("expire", "S");			
//			PrintWriter out = response.getWriter();				
//			out.print(jsonObj.toString());
//		    }catch(Exception e){
//		    	    e.printStackTrace();
//		         }		
//			return false;
//	    }
//		return true;
//	}
//	
//	
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
//	/**
//	 * Obtiene una conexión utilizada para JDBC
//	 * @return
//	 * @throws Exception 
//	 */
//	public static Connection getConnection() throws Exception{	    
//	
//	        Connection connection = ServiceFactory.getSeguridadService().getConnection();                
//	        return connection;
//		
//	}
//	
//	public static void auditar(Integer idAccion, String detalle,HttpServletRequest request) throws Exception{
//		
//		UserInfo usuario= obtenerUsuarioSesion(request);
//		
//		Accion accion= new Accion();
//		accion.setId(idAccion);
//		
//		Auditoria auditoria= new Auditoria();		
//		auditoria.setUsuario(usuario.getCodPersonal());
//		auditoria.setIp(usuario.getIp());
//		auditoria.setFecha(FechaUtil.getCurrentTimestamp());
//		auditoria.setAccion(accion);
//		auditoria.setDetalle(detalle);		
//		ServiceFactory.getAuditoriaService().auditar(auditoria);
//		
//	}
//	
//	public static String  obtenerMensaje(String key){
//		ResourceBundle resourceBundle=ResourceBundle.getBundle(Constantes.RUTA_RESOURCE_BUNDLE);
//		return resourceBundle.getString(key);
//	}
		
}
