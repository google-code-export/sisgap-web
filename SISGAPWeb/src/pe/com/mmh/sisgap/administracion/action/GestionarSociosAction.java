package pe.com.mmh.sisgap.administracion.action;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarSociosActionForm;
import pe.com.mmh.sisgap.administracion.ejb.ActividadSocioFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SocioFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.TipoSocioFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.ActividadSocio;
import pe.com.mmh.sisgap.domain.Socio;
import pe.com.mmh.sisgap.domain.TipoSocio;


public class GestionarSociosAction extends GrandActionAbstract{

	
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		System.out.println("[GestionarSociosAction] Inicio - cargarAction");
		GestionarSociosActionForm frm=(GestionarSociosActionForm)form;	
		SocioFacadeLocal socioLocal=(SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		List<Socio> list=socioLocal.findAll();
		request.setAttribute("lstSocios", list);
		System.out.println("[GestionarSociosAction] Final - cargarAction");
		return mapping.findForward("cargarAction");			
	}	

	public ActionForward irGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("[GestionarSociosAction] Inicio - irGrabar");	
		SocioFacadeLocal socioLocal=(SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		List<Socio> list=socioLocal.findAll();
		request.setAttribute("lstSocios", list);
		
		ActividadSocioFacadeLocal actividadSocio= (ActividadSocioFacadeLocal) lookup(ConstantesJNDI.ACTIVIDADSOCIOFACADE);
		List<ActividadSocio> lstActividad=actividadSocio.findAll();
		request.setAttribute("lstActividadSocio", lstActividad);
		
		TipoSocioFacadeLocal tipoSocios=(TipoSocioFacadeLocal) lookup(ConstantesJNDI.TIPOSOCIOFACADE);
		List<TipoSocio> lstTipo=tipoSocios.findAll();
		request.setAttribute("lstTipoSocios", lstTipo);
		
		System.out.println("[GestionarSociosAction] Final - irGrabar");
		return mapping.findForward("agregarClientes");
	}
	
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarSociosAction] Inicio - grabar");
		String txtRazonSocial = request.getParameter("txtRazonSocial");
		String fechaconstitucion = request.getParameter("fechaconstitucion");
		String cbActividadSocio = request.getParameter("cbActividadSocio");
		String cbTipoSocio = request.getParameter("cbTipoSocio");
		String txtSector = request.getParameter("txtSector");
		String txtPuesto = request.getParameter("txtPuesto");
		String cbestado = request.getParameter("cbestado");
		if (txtPuesto.trim().equals(""))
			txtPuesto = "0000";
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaDocumento = "";
		if (fechaconstitucion!=null)
			fechaDocumento = sdf.format(fechaconstitucion);*/
		
		//Conversion de Fecha
		DateFormat formato;
		Date fecha;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		fecha = (Date) formato.parse(fechaconstitucion);
		//Objeto ActividadSocio
		ActividadSocioFacadeLocal actividadFacade = (ActividadSocioFacadeLocal) lookup(ConstantesJNDI.ACTIVIDADSOCIOFACADE);
		ActividadSocio act;
		act = actividadFacade.find(Long.valueOf(cbActividadSocio));
		
		//Objeto TipoSocio
		TipoSocioFacadeLocal tipoFacade = (TipoSocioFacadeLocal) lookup(ConstantesJNDI.TIPOSOCIOFACADE); 
		TipoSocio tip;
		tip = tipoFacade.find(Long.valueOf(cbTipoSocio));
		
		//Generando Numero de Cliente
		SocioFacadeLocal socioFacade = (SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);		
		String numerocliente = socioFacade.generarNroCodigo(tip.getTipoTranCodigo());
		
		Socio soc = new Socio();
		soc.setTranCodigo(numerocliente);
		soc.setTranRazonSocial(txtRazonSocial);
		soc.setTranFechaConstitucion(fecha);
		soc.setSisgapActividadSocio(act);
		soc.setSisgapTipoSocio(tip);
		soc.setStrSector(txtSector.trim());
		soc.setTranPuesto(txtPuesto.trim());
		String estado="";
		if (cbestado.equals("1"))
			estado = "Activo";
		else
			estado = "Inactivo";
		
		soc.setTranEstado(estado);
		
		//SocioFacadeLocal socioFacade = (SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		socioFacade.create(soc);
		
		List<Socio> lstSocio = socioFacade.findAll();
		request.setAttribute("lstSocio", lstSocio);

		System.out.println("[GestionarSociosAction] Final - grabar");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward listarClientes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		System.out.println("[GestionarSociosAction] Inicio - listarClientes");
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
		System.out.println("[GestionarSociosAction] Final - listarClientes");
		return mapping.findForward("gestionarSocio");			
	}

	public ActionForward cargarArchivo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		System.out.println("[GestionarSociosAction] Inicio - cargarArchivo");
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
        
		System.out.println("[GestionarSociosAction] Final - cargarArchivo");
        return mapping.findForward("gestionarSocio");
    }

}
