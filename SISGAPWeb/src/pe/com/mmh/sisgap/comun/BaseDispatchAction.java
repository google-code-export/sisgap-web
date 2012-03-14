/*
 * Creado el 07-abr-2005
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */ 
package pe.com.mmh.sisgap.comun;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.*;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author cgomez
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class BaseDispatchAction extends DispatchAction{
	// --------------------------------------------------------- Constantes
	public static String PACKAGE = "pe.com.mmh.sisgap";
	/**
     * The request attributes key under which the synchronizer token is stored.
	 */
	protected static final String REQUEST_TOKEN_KEY =
		org.apache.struts.taglib.html.Constants.TOKEN_KEY;
	/**
     * The session attributes key under which the form that results from the current
     * synchronized action is stored.
	 */
	private static final String FORM_KEY = PACKAGE + ".BaseAction.FORM";

	/**
     * The session attributes key under which the exception that results from the current
     * synchronized action is stored.
	 */
	private static final String EXCEPTION_KEY = PACKAGE + ".BaseAction.EXCEPTION";

	/**
     * The session attributes key under which the forward that results from the current
     * synchronized action is stored.
	 */
	private static final String FORWARD_KEY = PACKAGE + ".BaseAction.FORWARD";

	/**
     * The session attributes key under which the token that identifies the current
     * synchronized action is stored.
	 */
	private static final String CURRENT_TOKEN_KEY = PACKAGE + ".BaseAction.CURRENT_TOKEN";
	/**
	 * Session key for Alert Message 
	 */
	private static final String ALERT_KEY = PACKAGE + ".BaseAction.ALERT";	

	//inicializacion del ejbLocator

	//metodos de acceso rapido a variables de sesion

	
	protected String generateId(HttpServletRequest request, String key){
		return key + request.getSession().getId();
	}
	/**
	 *  
	 */
    protected ActionForward dispatchMethod(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response,
            String name) throws Exception {
    	
//    	 Make sure we have a valid method name to call.
        // This may be null if the user hacks the query string.
        if (name == null) {
            return this.unspecified(mapping, form, request, response);
        }

        // Identify the method object to be dispatched to
        Method method = null;
        try {
            method = getMethod(name);
        } catch(NoSuchMethodException e) {
            String message =
                    messages.getMessage("dispatch.method", mapping.getPath(), name);
            //log.error(message, e);
            throw e;
        }
        //check if this method is marked as SynAction
        	//controlar syncronizacion.            
        	HttpSession session = request.getSession();
        	synchronized(session.getId()) {
    			// Get the request token.
    			String requestToken = request.getParameter(REQUEST_TOKEN_KEY);
    			// Get the current token.
    			String currentToken = (String) session.getAttribute(CURRENT_TOKEN_KEY);
    			// If the request token is the current token then recover the
    			// results from the previous synchronized action.
    			if (currentToken != null && currentToken.equals(requestToken)) {                                        
                    return recover(mapping, form, request, response);
    			}

    			// If token is invalid, process the synchronization error.

    			// Reset session attributes.
    			session.removeAttribute(FORM_KEY);
    			session.removeAttribute(EXCEPTION_KEY);
    			session.removeAttribute(FORWARD_KEY);			

    			// Store the current token in the session.
    			session.setAttribute(CURRENT_TOKEN_KEY, requestToken);
    			ActionForward forward = null;
    			try {
    				if (log.isDebugEnabled()) {
    					log.debug("syncronized method- Thread: " + Thread.currentThread());
    				}

    				// Execute synchronized action.
    				forward = invokeActionMethod(method,new Object[]{mapping, form, request, response},mapping, name);

    				// Keep the form resulting from the action.
    				session.setAttribute(FORM_KEY, form);

    				// Keep the forward resulting from the action.
    				session.setAttribute(FORWARD_KEY, forward);
    				//Keep exception 
    				
    				return forward;
    				
    			} catch (Exception e) {
    				// Keep the exceptions resulting from the action and rethrow them.
    				session.setAttribute(EXCEPTION_KEY, e);
    				throw e;
    			}    				
        	}//fin de sincronizacion
        }

		
	/**
	 * Recover the results of the synchronized action.
	 *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
	 *
     * @exception Exception if an error occurs
	 */
	protected ActionForward recover(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
	throws Exception {

		if (log.isDebugEnabled()) {
			String msg = "recover - Thread: " + Thread.currentThread();
			log.debug(msg);
		}
		//System.out.println.out.println("recovering previous response...");

		HttpSession session = request.getSession();
		// Recover the exception and set it in the request.
		Exception e = (Exception) session.getAttribute(EXCEPTION_KEY);

		// Recover the form.
		form = (ActionForm) session.getAttribute(FORM_KEY);

		// Put back the form in the appropriate context.
		if ("request".equals(mapping.getScope())) {
			request.setAttribute(mapping.getAttribute(), form);
		} else {
			// Put back a copy to protect the form from being repopulated
			// by upcoming requests.
			// Note: This might be avoided in future releases of Struts if a flag
			// could be set to prevent auto-population under certain circumstances.
			if (form != null) {
				session.setAttribute(mapping.getAttribute(), BeanUtils.cloneBean(form));
			}
		}

		// Recover and return the forward.
		return (ActionForward) session.getAttribute(FORWARD_KEY);
	}	
	
	/**
	 * Execute the action in case of a synchronization error.
	 *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
	 *
     * @exception Exception if an error occurs
	 */


	protected ActionForward invokeActionMethod(Method method, Object[] args, ActionMapping mapping, String name)
	throws Exception{
        ActionForward forward = null;
        try {            
            forward = (ActionForward) method.invoke(this, args);

        } catch(ClassCastException e) {
            String message =
                    messages.getMessage("dispatch.return", mapping.getPath(), name);
            //log.error(message, e);
            throw e;

        } catch(IllegalAccessException e) {
            String message =
                    messages.getMessage("dispatch.error", mapping.getPath(), name);
            //log.error(message, e);
            throw e;

        } catch(InvocationTargetException e) {
            // Rethrow the target exception if possible so that the
            // exception handling machinery can deal with it
            Throwable t = e.getTargetException();
            if (t instanceof Exception) {
                throw ((Exception) t);
            } else {
                String message =
                        messages.getMessage("dispatch.error", mapping.getPath(), name);
                //log.error(message, e);
                throw new ServletException(t);
            }
        }
        // Return the returned ActionForward instance
        return (forward);		
	}
    
    protected int getIdSistema(HttpServletRequest request ) throws Exception
    {
        return Integer.parseInt(request.getSession().getServletContext().getInitParameter("SISTEMA_ID"));
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
