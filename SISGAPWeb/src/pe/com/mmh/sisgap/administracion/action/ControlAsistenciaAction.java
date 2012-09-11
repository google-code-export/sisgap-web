package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.ReunionesFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ReunionesSocioFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.SisgapReuniones;
import pe.com.mmh.sisgap.domain.SisgapReunionesSocio;

public class ControlAsistenciaAction extends GrandActionAbstract{//GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ControlAsistenciaAction] Inicio - cargarAction");
		
		ReunionesFacadeLocal ReunionesfacadeLocal = (ReunionesFacadeLocal) lookup(ConstantesJNDI.REUNIONESFACADE);
		List<SisgapReuniones> lst = ReunionesfacadeLocal.findAll();
		request.setAttribute("lstRes", lst);
		
		System.out.println("[ControlAsistenciaAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}

	public ActionForward registrarAsamblea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ControlAsistenciaAction] Inicio - registrarAsamblea");
		String fecAsamblea = request.getParameter("fecAsambleax");
		String lugAsamblea = request.getParameter("lugarAsambleax");
		String ageAsamblea = request.getParameter("agendaAsambleax");
		String acuAsamblea = request.getParameter("acuerdosAsambleax");
		String obsAsamblea = request.getParameter("observacionesAsambleax");
		
		ReunionesFacadeLocal ReunionesfacadeLocal = (ReunionesFacadeLocal) lookup(ConstantesJNDI.REUNIONESFACADE);
		ReunionesfacadeLocal.grabarAsambleas(fecAsamblea, lugAsamblea, ageAsamblea, acuAsamblea, obsAsamblea);
		
		
		List<SisgapReuniones> lst = ReunionesfacadeLocal.findAll();
		request.setAttribute("lstRes", lst);
		
		System.out.println("[ControlAsistenciaAction] Final - registrarAsamblea");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward modificarReunion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ControlAsistenciaAction] Inicio - modificarReunion");
		
		
		System.out.println("[ControlAsistenciaAction] Final - modificarReunion");
		return mapping.findForward("modificarReunion");
	}
	
	public ActionForward mostrarItemsAsamblea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[ControlAsistenciaAction] Inicio - mostrarItemsAsambleas");
		ReunionesFacadeLocal facadeLocal = (ReunionesFacadeLocal) lookup(ConstantesJNDI.REUNIONESFACADE);
		ReunionesSocioFacadeLocal reunionesfacadeLocal = (ReunionesSocioFacadeLocal) lookup(ConstantesJNDI.REUNIONESSOCIOFACADE);
		String codigoModi = request.getParameter("codigoModi");
		
		List<SisgapReunionesSocio> lstAsambleaSocio = reunionesfacadeLocal.listarAsambleaSocio(new BigDecimal(codigoModi));
		
		SisgapReuniones lstAsambleaRes = new SisgapReuniones();
		lstAsambleaRes.setNumCodReuniones(new BigDecimal(codigoModi));
		SisgapReuniones lstAsambleaRes1 = facadeLocal.buscarAsamblea(lstAsambleaRes);
		request.setAttribute("lstAsambleaRes1", lstAsambleaRes1);
		
		request.setAttribute("lstAsambleaSocio", lstAsambleaSocio);
		for (int b=0; b<lstAsambleaSocio.size(); b++){
			System.out.println("Valor ["+b+"] Codigo Socios: "+lstAsambleaSocio.get(b).getCodigosocios());
		}
		
		System.out.println("[ControlAsistenciaAction] Final - mostrarItemsAsambleas");
		
		return mapping.findForward("agregarAsistenciaSocio");
	}
	
	public ActionForward grabarAsambleaSocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ControlAsistenciaAction] Inicio - grabarAsambleaSocio");
		String asambl = request.getParameter("codigoasamblea");
		String fechas = request.getParameter("fechaasamblea");
		String codigo = request.getParameter("codigosociox");
		String socios = request.getParameter("sociox");
		String direcc = request.getParameter("direccionx");
		String estado = request.getParameter("estadox");
		String observ = request.getParameter("observacionesx");
		String usuari = "ADMIN";
		
		//TEMPORAL
		estado = "1";
		
		ReunionesSocioFacadeLocal asambleaSocioFacadeLocal = (ReunionesSocioFacadeLocal) lookup(ConstantesJNDI.REUNIONESSOCIOFACADE);
		asambleaSocioFacadeLocal.grabarAsambleaSocio(asambl, codigo, fechas, estado, usuari, observ);
		
		//Recogiendo Datos nuevamente
		ReunionesFacadeLocal facadeLocal = (ReunionesFacadeLocal) lookup(ConstantesJNDI.REUNIONESFACADE);
		ReunionesSocioFacadeLocal reunionesfacadeLocal = (ReunionesSocioFacadeLocal) lookup(ConstantesJNDI.REUNIONESSOCIOFACADE);
		
		List<SisgapReunionesSocio> lstAsambleaSocio = reunionesfacadeLocal.listarAsambleaSocio(new BigDecimal(asambl));
		
		SisgapReuniones lstAsambleaRes = new SisgapReuniones();
		lstAsambleaRes.setNumCodReuniones(new BigDecimal(asambl));
		SisgapReuniones lstAsambleaRes1 = facadeLocal.buscarAsamblea(lstAsambleaRes);
		request.setAttribute("lstAsambleaRes1", lstAsambleaRes1);
		
		request.setAttribute("lstAsambleaSocio", lstAsambleaSocio);
		
		System.out.println("[ControlAsistenciaAction] Final - grabarAsambleaSocio");
		return mapping.findForward("agregarAsistenciaSocio");
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
