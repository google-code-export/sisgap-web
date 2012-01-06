package pe.com.mmh.sisgap.administracion.action;


import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarSociosActionForm;
import pe.com.mmh.sisgap.administracion.ejb.SocioFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Socio;


public class GestionarSociosAction extends GrandActionAbstract{

	
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		GestionarSociosActionForm frm=(GestionarSociosActionForm)form;	
		SocioFacadeLocal socioLocal=(SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		List<Socio> list=socioLocal.findAll();
		request.setAttribute("lstSocios", list);
		return mapping.findForward("cargarAction");			
	}	

	
	public ActionForward listarClientes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
//		GestionarSociosActionForm frm=(GestionarSociosActionForm)form;
//
//		int paginaActual=this.getPaginaActual(request);
//		
//		Cliente filter= new Cliente();
//		if(frm.getTxtCodigo()!=null && !frm.getTxtCodigo().trim().equals(""))
//			filter.setCodigo(frm.getTxtCodigo());
//		
//		if(frm.getTxtNombre()!=null && !frm.getTxtNombre().trim().equals(""))
//			filter.setNombre(frm.getTxtNombre());
//		
//		if(frm.getCmbAccion()!=null && !frm.getCmbAccion().trim().equals("")){
//					
//			if(!frm.getCmbAccion().equals(Constantes.CLIENTE_ID_TODOS))
//				filter.setAccion(frm.getCmbAccion());
//			}
//		
//		Paginador paginador=ServiceFactory.getClienteService().buscarClientes(filter,paginaActual);
//		
//		@SuppressWarnings("unchecked")
//		List<Cliente> listaClientes=paginador.getLista();
//		for (Cliente c:listaClientes) {
//		if(c.getAccion()!=null){
//			if(c.getAccion().equals(Constantes.CLIENTE_ID_NINGUNA))
//				 c.setAccion(Constantes.CLIENTE_NINGUNA);
//			else
//				if(c.getAccion().equals(Constantes.CLIENTE_ID_NUEVO))
//					 c.setAccion(Constantes.CLIENTE_NUEVO);
//				else
//					if(c.getAccion().equals(Constantes.CLIENTE_ID_ACTUALIZADO))
//						 c.setAccion(Constantes.CLIENTE_ACTUALIZADO);					
//			}		
//		}
//		paginador.setLista(listaClientes);
//		
//		request.setAttribute("listaCliente",paginador.getLista() );	
//		request.setAttribute("total",paginador.getTotal() );	
//		request.setAttribute("paginacion",PropiedadesSistema.getInstance().getCantidadRegistros());	
//			
//		request.setAttribute("accion", frm.getCmbAccion());
//		request.setAttribute("codigo", frm.getTxtCodigo());
//		request.setAttribute("nombre", frm.getTxtNombre());
		return mapping.findForward("gestionarSocio");			
	}

	public ActionForward cargarArchivo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//		GestionarSociosActionForm uploadForm = (GestionarSociosActionForm) form;
//       
//        FormFile formFile = null;
//        try {
//            formFile = uploadForm.getFile();          
//            
//            if(formFile.getFileName().equals(""))
//            		throw new ServiceException("Seleccione un archivo de texto");
//          
//            if(!formFile.getFileName().contains(".txt")
//            		&& !formFile.getFileName().contains(".TXT"))
//            	throw new ServiceException("Este archivo no es un archivo de texto (*.txt)");    
//            	
//            if(!formFile.getContentType().equals("text/plain"))
//            	throw new ServiceException("Este archivo no es un archivo de texto (*.txt)");
//
//            ServiceFactory.getClienteService().cargarClientesDesdeArchivoTexto(formFile.getInputStream(),SeguridadUtil.obtenerUsuarioSesion(request).getCodPersonal(),request);
//
//          
//            uploadForm.setTxtCodigo("");
//            uploadForm.setTxtNombre("");
//           // uploadForm.setCmbAccion(Constantes.TODOS);
//    		request.setAttribute("mensaje", "Archivo subido satisfactoriamente");
//    		return listarClientes(mapping, uploadForm, request, response);
//        } 
//        catch (ServiceException se) {
//			se.printStackTrace();
//			request.setAttribute("error", se.getMessage());
//	
//        }
//    
//        catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("error","Ocurrio un error: "+ e.getMessage());
//			
//		}
        
      
        return mapping.findForward("gestionarSocio");
    }

}
