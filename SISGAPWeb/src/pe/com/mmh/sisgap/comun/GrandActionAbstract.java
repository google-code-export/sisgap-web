package pe.com.mmh.sisgap.comun;
/**
 * Molde para los Action 
 */

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pe.com.mmh.sisgap.comun.constantes.ConstantesSesion;
import pe.com.mmh.sisgap.util.FechaUtil;
import pe.com.mmh.sisgap.util.SeguridadUtil;
import pe.com.mmh.sisgap.util.json.JSONObject;




public abstract class GrandActionAbstract extends Action
{
	private static HashMap paginasMap = new HashMap();
	private StringBuffer sbpag;
	protected Class clazz = this.getClass();
	protected Class types[] = {ActionMapping.class, ActionForm.class,HttpServletRequest.class, HttpServletResponse.class};	
	protected HashMap methods = new HashMap();
	public Logger logger = null;
	
	public GrandActionAbstract(){		
	}


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{	
		if (logger==null) {
			logger = 	Logger.getLogger(this.getClass());
		}
		StringBuffer sb = new StringBuffer();

		sb.delete(0,sb.length());
		sb.append("[remoteIP=").append(request.getRemoteAddr()).append("]");
		NDC.push(sb.toString());

		ActionForward forward = null;
		try {
		/*********************************************************
		 *             VERIFICANDO USUARIO EN SESION
		 *********************************************************/
		
		if(!SeguridadUtil.isPaginaInicio(this)) {
			logger.debug("Verificando Usuario en sesion");
			if(SeguridadUtil.obtenerUsuarioSesion(request)==null) {
				logger.debug("No hay usuario en sesion");
				
				//------AGREGADO PARA VALIDACIONES EN SESSION AJAX-------------
				if(request.getParameter("ajax")!=null)
				  { String ajax=request.getParameter("ajax").toUpperCase().trim();
				    if(ajax.equals("S")){				    	   
				    	    logger.debug("---Método invocado desde Ajax ---");
				    	    logger.debug("Metodo:"+request.getParameter("metodo"));
				    	    logger.debug("Expire Session");
				    	    JSONObject jsonObj=new JSONObject();    
						    try{
						    //Este valor se envía a la pagina que llama	
							jsonObj.put("expire", "S");			
							PrintWriter out = response.getWriter();				
							out.print(jsonObj.toString());
						    }catch(Exception e){
						    	    logger.debug("Ocurrió un error JSON"+e.getMessage());
						    	    throw e;
						         }	
						  return null; 
				    }
				    	
				  }	
				//--------------------------------------------------------------
			
				return mapping.findForward("sesion.invalida");
				
			}
		}	
			
		
		/************************************************************/
		
		//try {//ahora lo pongo arriba

			if(logger.isDebugEnabled()){
				logger.debug("CharacterEncoding : " + request.getCharacterEncoding());
				logger.debug("Locale : " + request.getLocale());
			}

		//Validación mediante validate
			if( form != null) {
				ActionErrors errors = form.validate(mapping, request);
				if( errors != null && errors.size()>0 )
				{   ActionMessages msgs =errors;
					saveMessages(request,msgs);
					//inicializarValores(mapping, form,  request, response);
					return mapping.getInputForward();
				}
			}
			/**
			 * ***************************************************
			 * procesamiento del metodo pedido por el usuario
			 * ***************************************************
			 **/
			String metodoPedido = request.getParameter("metodo");			
			
			if (logger.isDebugEnabled())
				logger.debug("metodo pedido = [" + metodoPedido +"]");
			if (metodoPedido==null || metodoPedido.equalsIgnoreCase("execute"))
				metodoPedido="procesar";
			
			//Busco el metodo en la caché o en la clase
			Method method = null;
			try{
				method = getMethod(metodoPedido);
			}catch(java.lang.NoSuchMethodException sme){
				return mapping.findForward("sesion.invalida");
			}
					
			if (logger.isInfoEnabled())
				{
				sb.delete(0,sb.length());
				sb.append("___Inicio de Action.method=").append(clazz.getName());
				sb.append(".").append(metodoPedido);
				logger.info(sb.toString());
				}

			//llamar al método requerido
			Object args[] = { mapping, form, request, response};
			
			/**Sólo busca los métodos públicos
			 * Obs el método invoke, arroja 3 Excepciones
			 *  IllegalAccessException
			 *  IllegalArgumentException
			 *  InvocationTargetException 
			 */			
			
			forward = (ActionForward) method.invoke(this, args);//Invoco al metodo

			if (logger.isInfoEnabled())
				{				
				if(forward!=null){
					sb.delete(0,sb.length());
					sb.append("_Página a mostrar=").append(forward.getPath());
					logger.info(sb.toString());	
				}
				sb.delete(0,sb.length());	
				sb.append("___Fin    de Action.method=").append(clazz.getName());
				sb.append(".").append(metodoPedido);
				logger.info(sb.toString());
				}
			/**
			 * ******************
			 * código POST-ACTION
			 * ******************
			 **/

		}
		catch (InvocationTargetException ite) {
			Throwable te = ite.getTargetException();			
//			if (te instanceof ArrayRuleException)
//			{  //Manejo de errores definidas por el usuario mediante el ArrayRuleException
//				
//				if(logger.isDebugEnabled())
//					logger.debug("Error Esperado : [ dentro del bloque InvocationTargetException: Una exception de la Clase ArrayRuleException.] ");
//				
//				ArrayRuleException re=(ArrayRuleException)te;
//				ActionMessages errors = new ActionMessages();
//				ActionMessage[] arrAE = re.getActionMessagesArray();
//				for (int i = 0; i < arrAE.length; i++)
//				{
//					errors.add(Constantes.ACTION_MESSAGE_ID,arrAE[i]);
//				}
//				this.saveMessages(request, errors);
//				
//				//----------------------------------------------------
//				String forwardName = re.getForwardNameAlterno();
//				if(forwardName!=null){
//					forward = mapping.findForward(forwardName);
//				}else{
//					forward = mapping.getInputForward();
//					if (forward.getPath()==null)
//						{
//							logger.error("Action no tiene INPUT forward");
//							throw new RuntimeException("Action no tiene INPUT forward");
//						}
//				}
//				/*if (mapping.getInput() != null && !mapping.getInput().equalsIgnoreCase("")){		
//					forward = mapping.getInputForward();
//				}
//				else {		
//				   //	forward = mapping.findForward((String)request.getAttribute(Constantes.FIND_FORWARD));
//				}*/
//			}
//			else
//			{  //Otro tipo de errores no definidos por el usuario
//				if(logger.isDebugEnabled())
//					logger.error("Error Inesperado en el Sistema : [ Error dentro del bloque InvocationTargetException ]");
//				forward = mapping.findForward("errorGenerico.server");
//				request.setAttribute("objetoError", ite);
//				logger.error("", te);
//			}
			ite.printStackTrace();
		}		
		catch (Exception e) {
			 /* Para las demás Excepciones arrojadas por el método invoke
		     * IllegalAccessException
			 * IllegalArgumentException	
			 */ 
			logger.error("Error Inesperado en el Sistema : [ Error dentro del bloque Exception ] : " + e.toString());
			request.setAttribute("objetoError", e);
			forward = mapping.findForward("errorGenerico.server");		
		}
		finally	{
			NDC.remove();
		}
		request.getSession().setAttribute(ConstantesSesion.FECHA_HORA_SERVIDOR_APLICACIONES,FechaUtil.TimestampToStringAAAAMMDDhhmmss(FechaUtil.getCurrentTimestamp()));
		return forward;
	}


	/** @modelguid {65688257-DDB3-4077-A5EC-BF7CB875201C} */
	protected Method getMethod(String name)
		throws NoSuchMethodException
		{
		//buscar si el método requerido existe en la clase
		//primero se busca en hashmap (caché) para optimizar performance
		synchronized (methods) {
			//busco en el HashMap(caché)
			Method method = (Method) methods.get(name);
			if (method == null)
			{  //Si no existe lo busco en la clase
				method = clazz.getMethod(name, types);
				methods.put(name, method);
			}
			return (method);
		}

	}

	/**
	 * Metodo que inicializa valores para mostrar en una página JSP
	 * @modelguid {691C005C-DCAA-45B7-A89B-501EF5BBA30C}
	 */
	protected void inicializarValores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
	}
	
	/*
	 * Método que obtiene la pagina actual para usarlo con displayTag
	 * */
	public int getPaginaActual(HttpServletRequest request){
	      int page=0;
	        Enumeration<?> paramNames = request.getParameterNames();
	        while (paramNames.hasMoreElements()) {
	            String name = (String)paramNames.nextElement();
	            if (name != null && name.startsWith("d-") && name.endsWith("-p")) {
	                String pageValue = request.getParameter(name);
	                if (pageValue != null) {
	                    page = Integer.parseInt(pageValue)-1;
	                }
	            }
	        }
	        return page;
	    }
	
	public Object lookup(String JNDIName){
		try {
			return new InitialContext().lookup(JNDIName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JNDIName;
	}
	
	

}