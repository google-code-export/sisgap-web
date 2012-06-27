package pe.com.mmh.sisgap.administracion.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.ServiciosHigienicosFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ServiciosItemFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.ServicioItem;
import pe.com.mmh.sisgap.domain.Servicios;

public class ServiciosHigienicosAction extends GrandActionAbstract {

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - cargarAction");
		
		ServiciosHigienicosFacadeLocal SSHHfacadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		List<Servicios> list = SSHHfacadeLocal.findAll();
		request.setAttribute("lstSSHH", list);

		System.out.println("[ServiciosHigienicos] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}

	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - irGrabar");
		
		ServiciosItemFacadeLocal SIfacadeLocal = (ServiciosItemFacadeLocal) lookup(ConstantesJNDI.SERVICIOSITEM);
		List<ServicioItem> list = SIfacadeLocal.findAll();
		request.setAttribute("lstSI", list);
		
		System.out.println("[ServiciosHigienicos] Final - irGrabar");
		return mapping.findForward("agregarServicios");
	}
	
	public ActionForward blankpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - blankPage");
		
		/*SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);*/

		System.out.println("[ServiciosHigienicos] Final - blankPage");
		return mapping.findForward("cargarAction");
	}

}
