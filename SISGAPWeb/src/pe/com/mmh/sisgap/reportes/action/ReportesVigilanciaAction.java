package pe.com.mmh.sisgap.reportes.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.VigilanciaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SocioFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.comun.servlet.ReportsServlet;
import pe.com.mmh.sisgap.domain.Socio;

public class ReportesVigilanciaAction extends GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ReportesSisaAction] Inicio - cargarAction");
		SocioFacadeLocal facadeSocio = (SocioFacadeLocal) lookup(ConstantesJNDI.SOCIOFACADE);
		List<Socio> lstSocio = facadeSocio.findAll();
		request.setAttribute("lstSocio", lstSocio);
		
		/*for(int a=0; a<lstSocio.size(); a++){
			System.out.println((a+1) +" - " + lstSocio.get(a).getTranIde()+ " - " + lstSocio.get(a).getTranCodigo().trim() + " - " + lstSocio.get(a).getTranRazonSocial());
		}*/
		
		request.getSession().setAttribute("Respuesta", null);
		System.out.println("[ReportesSisaAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward grabarVigilancia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ReportesSisaAction] Inicio - grabarVigilancia");
		
		String codSocio = request.getParameter("codigo");
		String fecIni = request.getParameter("fechaIni");
		String fecFin = request.getParameter("fechaFin");
		int rpta=0;
		fecIni = "01/"+fecIni;
		fecFin = "01/"+fecFin;
		
		VigilanciaFacadeLocal facadeSisa = (VigilanciaFacadeLocal) lookup(ConstantesJNDI.VIGILANCIAFACADE);
		rpta = facadeSisa.cargarVigilanciaTMP(codSocio,fecIni,fecFin,rpta);
		
		request.getSession().setAttribute("Respuesta", rpta);
		request.getSession().setAttribute("fechaIni", fecIni);
		request.getSession().setAttribute("fechaFin", fecFin);
		
		/*request.getSession().setAttribute("reporte", "LISTADO_GENERAL_VIGILANCIA");
		
		ReportsServlet rs = new ReportsServlet();
		rs.service(null, null);*/
		
		System.out.println("Respuesta: "+rpta);
		System.out.println("[ReportesSisaAction] Final - grabarVigilancia");
		return mapping.findForward("cargarAction");
	}
}
