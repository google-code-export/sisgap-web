package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.FacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SuministroLuzFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ReciboluzOrg;

public class SuministroLuzAction extends GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		return mapping.findForward("cargarAction");
	}
	

	@SuppressWarnings("deprecation")
	public ActionForward registrarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");
		
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		facadeLocal.createResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	public ActionForward actualizarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");

		res.setCodOrgreciboLuz(new Long(codigoModi));
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		facadeLocal.updateResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	
	public ActionForward eliminarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		res.setCodOrgreciboLuz(new Long(codigoModi));
		facadeLocal.deleteResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	public ActionForward listarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");

	}
	
}
