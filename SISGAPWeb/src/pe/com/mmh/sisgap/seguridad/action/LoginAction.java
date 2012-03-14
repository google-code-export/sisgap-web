package pe.com.mmh.sisgap.seguridad.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.comun.BaseDispatchAction;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.seguridad.action.form.LoginActionForm;



import com.infocorp.segap.negocio.LoginException;

//import pe.com.equifax.paramweb.comun.GrandActionAbstract;
//import pe.com.equifax.paramweb.comun.constantes.Constantes;
//import pe.com.equifax.paramweb.factory.ServiceFactory;
//import pe.mmh.sisgap.paramweb.seguridad.action.form.LoginActionForm;
//import pe.com.equifax.paramweb.seguridad.domain.UserInfo;
//import pe.com.equifax.paramweb.util.SeguridadUtil;

public class LoginAction extends BaseDispatchAction{

  /**
   * Permite Ingresar al Sistema
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
	public ActionForward ingresarSistema(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		LoginActionForm frm=(LoginActionForm)form;
		String userName=frm.getTxtUserName().trim();
		String password=frm.getTxtPassword().trim();
				
		try{			
//			UserInfo user=ServiceFactory.getSeguridadService().ingresarSistema(userName, password, request);
//							
//			//si debe cambiar contraseÑa direccionar a cambio de contrase?a
//			if(user.isDebeCambiarPwd()){
//				this.saveToken(request);
//				return cargarCambiarPassword(mapping,form,request,response);								
//			}else{//direccionar a pagina principal
//				if(user.isSugerirCambiarPwd())
//					request.setAttribute(Constantes.ALERT_MSG,"Faltan " 
//							+ user.getExpiracionPwd() + " días para la expiración de la contraseña.");				
				return mapping.findForward("inicio");
//			}
		}
	    catch(Exception ex){
//			ex.printStackTrace();
//			request.setAttribute(Constantes.EXCEPTION,ex);
//			this.saveToken(request);
			return mapping.findForward("ingresarSistema");
		}finally{          
			frm.setTxtPassword("");
		}	

	}
	 
	/**
	 * Carga la pantalla de Cambiar Password
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  cargarCambiarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
//		UserInfo user = SeguridadUtil.obtenerUsuarioSesion(request);
//		if(user==null){
//			ResourceBundle rb = ResourceBundle.getBundle("pe.com.equifax.paramweb.resources.mensajes");
//			request.setAttribute(Constantes.ALERT_MSG, rb.getString("GENERI.ERR.001"));
//            this.saveToken(request);
//			return mapping.findForward("ingresarSistema");
//		}
		return mapping.findForward("cargarCambiarPassword");
	}
	
	/**
	 * Cambia un password
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward  cambiarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		LoginActionForm frm=(LoginActionForm)form;							
//		UserInfo user = SeguridadUtil.obtenerUsuarioSesion(request);
//		if(user==null){//usuario no logeado. no puede cambiar contraseña
//			ResourceBundle rb = ResourceBundle.getBundle("pe.com.equifax.paramweb.resources.mensajes");
//			request.setAttribute(Constantes.ALERT_MSG, rb.getString("GENERI.ERR.001"));
//            this.saveToken(request);
//			return mapping.findForward("ingresarSistema");
//		}
//		 
//		try{			
//			//proceder a iniciar sesion
//		     ServiceFactory.getSeguridadService().cambiarPasswordPassword(user, frm.getTxtPassword(), frm.getTxtNuevoPassword(),request);				
//			 user.setDebeCambiarPwd(false);		
//			//direccionar a pagina principal			
//			return mapping.findForward("inicio");									
//		}catch(LoginException lex){
//			request.setAttribute(Constantes.ALERT_MSG, lex.getMessage());
//			logger.error("Ocurrió un error", lex);
//			return mapping.findForward("cargarCambiarPassword");
//		}catch(Exception ex){
//            ex.printStackTrace();
//            logger.error("Ocurrió un error", ex);
//			request.setAttribute(Constantes.EXCEPTION,ex);
//			return mapping.findForward("cargarCambiarPassword");
//		}finally{
//			frm.setTxtPassword("");
//		}
//		
//	}
	
	/**
	 * Muestra pantalla de Inicio
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward regresarInicio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("inicio");
	}
	
  /**
   * Cierra sesion del usuario
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
	public ActionForward cerrarSesion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		  // Si NO ha presionadp el botón CerrarSesion		
//		   if(request.getParameter("boton")==null){
//			   //Le envío un mensaje
//			   ResourceBundle rb = ResourceBundle.getBundle("pe.com.equifax.paramweb.resources.mensajes");
//			   request.setAttribute(Constantes.ALERT_MSG, rb.getString("GENERI.ERR.001"));
//		   }
//			//proceder a iniciar sesion
//			HttpSession session = request.getSession();
//			session = request.getSession(false);
//			if(session != null) {
//				session.setMaxInactiveInterval(1);
//				session.invalidate();
//			}									            			
//			
			return mapping.findForward("ingresarSistema");					
	}
		
}
