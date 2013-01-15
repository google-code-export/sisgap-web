package pe.com.mmh.sisgap.administracion.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import pe.com.mmh.sisgap.administracion.ejb.VigilanciaFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Vigilancia;

public class RegistroSisaAction extends GrandActionAbstract {
	

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisaAction] Inicio - cargarAction");
		/*VigilanciaFacadeLocal facadeLocal = (VigilanciaFacadeLocal) lookup(ConstantesJNDI.VIGILANCIAFACADE);
		List<Vigilancia> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);
		
		for(int a=0; a<list.size(); a++){
			System.out.println(list.get(a).getCodigo()+ " - " + list.get(a).getTranCodigo().trim() + " - " + list.get(a).getEstado());
		}
		*/
		System.out.println("[RegistroSisaAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}

	public ActionForward irGrabar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisaAction] Inicio - irGrabar");

		
/*		ServiciosItemFacadeLocal SIfacadeLocal = (ServiciosItemFacadeLocal) lookup(ConstantesJNDI.SERVICIOSITEM);
		List<ServicioItem> list = SIfacadeLocal.findAll();
		request.setAttribute("lstSI", list);*/
		
		System.out.println("[RegistroSisaAction] Final - irGrabar");
		return mapping.findForward("agregarSisas");
	}
	
	public ActionForward blankpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisaAction] Inicio - blankPage");

		System.out.println("[RegistroSisaAction] Final - blankPage");
		return mapping.findForward("blankpage");
	}

	
	public ActionForward updateSisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisaAction] Inicio - updateSisa");

		System.out.println("[RegistroSisaAction] Final - updateSisa");		
		return mapping.findForward("cargarAction");
	}
	
	//SP_UPD_SISAANULADA
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[RegistroSisaAction] Inicio - eliminar");
/*		VigilanciaFacadeLocal facadeLocal = (VigilanciaFacadeLocal) lookup(ConstantesJNDI.VIGILANCIAFACADE);

		String codigoSisa = request.getParameter("codigoSisa");
		
		if(codigoSisa!=null){
			
			facadeLocal.eliminarSisa(codigoSisa);
			
		}
		
		List<Vigilancia> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);*/
		System.out.println("[RegistroSisaAction] Final - eliminar");
		return mapping.findForward("cargarAction");
	}
	
	
}
