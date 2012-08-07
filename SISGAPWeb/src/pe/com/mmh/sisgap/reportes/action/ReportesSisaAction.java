package pe.com.mmh.sisgap.reportes.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SocioFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Socio;

public class ReportesSisaAction extends GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ReportesSisaAction] Inicio - cargarAction");
		SocioFacadeLocal facadeSocio = (SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		List<Socio> lstSocio = facadeSocio.findAll();
		request.setAttribute("lstSocio", lstSocio);
		
		for(int a=0; a<lstSocio.size(); a++){
			System.out.println((a+1) +" - " + lstSocio.get(a).getTranIde()+ " - " + lstSocio.get(a).getTranCodigo().trim() + " - " + lstSocio.get(a).getTranRazonSocial());
		}
		
		System.out.println("[ReportesSisaAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward grabarVigilancia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ReportesSisaAction] Inicio - grabarVigilancia");
		
		String codSocio = request.getParameter("codigo");
		
		SisasFacadeLocal facadeSisa = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		facadeSisa.cargarVigilanciaTMP(codSocio);
		
		System.out.println("[ReportesSisaAction] Final - grabarVigilancia");
		return mapping.findForward("cargarAction");
	}
}
