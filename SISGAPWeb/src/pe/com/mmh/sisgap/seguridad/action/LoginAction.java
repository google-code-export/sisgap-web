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
		   
			return mapping.findForward("ingresarSistema");					
	}
		
}
